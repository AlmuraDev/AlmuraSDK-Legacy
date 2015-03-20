/*
 * This file is part of AlmuraSDK, licensed under the MIT License (MIT).
 *
 * Copyright (c) AlmuraDev <http://github.com/AlmuraDev/AlmuraSDK/>
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
package com.almuradev.almurasdk.client.util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.malisis.core.renderer.icon.MalisisIcon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

@SideOnly(Side.CLIENT)
public class ExternalIcon extends MalisisIcon {

    private final Path root;

    public ExternalIcon(String key, Path root) {
        super(key);
        this.root = root;
    }

    public boolean hasCustomLoader(IResourceManager manager, ResourceLocation location) {
        return true;
    }

    public boolean load(IResourceManager manager, ResourceLocation location) {
        final Path texturePath = Paths.get(root.toString(), location.getResourcePath() + ".png");

        try {
            BufferedImage[] textures = new BufferedImage[1 + Minecraft.getMinecraft().gameSettings.mipmapLevels];
            textures[0] = ImageIO.read(Files.newInputStream(texturePath));
            loadSprite(textures, null, Minecraft.getMinecraft().gameSettings.anisotropicFiltering > 1.0F);
            return false;
        } catch (RuntimeException | IOException ignored) {
        }

        return true;
    }
}
