/*
 * This file is part of AlmuraSDK, licensed under the MIT License (MIT).
 *
 * Copyright (c) AlmuraDev <http://beta.almuramc.com/>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.almuradev.almurasdk.client;

import com.almuradev.almura.Almura;
import com.almuradev.almura.Configuration;
import com.almuradev.almura.Filesystem;
import net.malisis.core.renderer.icon.MalisisIcon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExternalIcon extends MalisisIcon {

    public ExternalIcon(String texturePath) {
        super(texturePath);
    }

    @Override
    public boolean hasCustomLoader(IResourceManager manager, ResourceLocation location) {
        String path = location.getResourcePath();
        final String[] tokens = path.split("/");
        final String domainName = tokens[0];
        return !domainName.equalsIgnoreCase("Minecraft") || super.load(manager, location);
    }

    @Override
    public boolean load(IResourceManager manager, ResourceLocation location) {
        final String domainName = location.getResourceDomain();
        if (domainName.equalsIgnoreCase("Minecraft")) {
            return super.load(manager, location);
        }
        final String textureName = location.getResourcePath();
        final Path texturePath = Paths.get(Filesystem.CONFIG_VERSION_PATH.toString(), "images" + File.separator + textureName + ".png");

        final int mipmapLevels = Minecraft.getMinecraft().gameSettings.mipmapLevels;
        final boolean anisotropic = Minecraft.getMinecraft().gameSettings.anisotropicFiltering > 1.0F;

        try {
            BufferedImage[] textures = new BufferedImage[1 + mipmapLevels];
            textures[0] = ImageIO.read(Files.newInputStream(texturePath));
            loadSprite(textures, null, anisotropic);
            return false;
        } catch (RuntimeException e) {
            if (e.getMessage().equalsIgnoreCase("broken aspect ratio and not an animation")) {
                if (Configuration.DEBUG_MODE || Configuration.DEBUG_PACKS_MODE) {
                    Almura.LOGGER
                            .error("Failed to load icon [" + textureName
                                            + ".png] in [" + Filesystem.CONFIG_IMAGES_PATH
                                            + "]. Aspect ratio is broken, make sure it is a power of 2 and has an equivalent width and height.",
                                    e);
                } else {
                    Almura.LOGGER.warn(
                            "Failed to load icon [" + textureName
                                    + ".png] in [" + Filesystem.CONFIG_IMAGES_PATH
                                    + "]. Aspect ratio is broken, make sure it is a power of 2 and has an equivalent width and height.");
                }
            } else {
                if (Configuration.DEBUG_MODE || Configuration.DEBUG_PACKS_MODE) {
                    Almura.LOGGER.error("Failed to load icon [" + textureName + ".png] in [" + Filesystem.CONFIG_IMAGES_PATH + "].", e);
                } else {
                    Almura.LOGGER.warn(
                            "Failed to load icon [" + textureName + ".png] in [" + Filesystem.CONFIG_IMAGES_PATH + "].");
                }
            }
        } catch (IOException e1) {
            if (Configuration.DEBUG_MODE || Configuration.DEBUG_PACKS_MODE) {
                Almura.LOGGER.error("Failed to load icon [" + textureName + ".png] in [" + Filesystem.CONFIG_IMAGES_PATH + "].", e1);
            } else {
                Almura.LOGGER.warn(
                        "Failed to load icon [" + textureName + ".png] in [" + Filesystem.CONFIG_IMAGES_PATH + "].");
            }
        }

        return true;
    }
}
