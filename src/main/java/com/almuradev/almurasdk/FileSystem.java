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
package com.almuradev.almurasdk;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public final class FileSystem {

    public static final DirectoryStream.Filter<Path> FILTER_DIRECTORIES_ONLY = new DirectoryStream.Filter<Path>() {
        @Override
        public boolean accept(Path entry) throws IOException {
            return Files.isDirectory(entry);
        }
    };

    public static final DirectoryStream.Filter<Path> FILTER_FILES_ONLY = new DirectoryStream.Filter<Path>() {
        @Override
        public boolean accept(Path entry) throws IOException {
            return !Files.isDirectory(entry);
        }
    };

    public static final DirectoryStream.Filter<Path> FILTER_IMAGE_FILES_ONLY = new DirectoryStream.Filter<Path>() {
        @Override
        public boolean accept(Path entry) throws IOException {
            return entry.getFileName().toString().endsWith(".png") || entry.getFileName().toString().endsWith(".jpg");
        }
    };

    public static final DirectoryStream.Filter<Path> FILTER_YAML_FILES_ONLY = new DirectoryStream.Filter<Path>() {
        @Override
        public boolean accept(Path entry) throws IOException {
            return entry.getFileName().toString().endsWith(".yml");
        }
    };

    public static Collection<URL> getURLs(Path path, String blob) {
        final List<URL> result = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, blob)) {
            for (Path entry : stream) {
                result.add(entry.toUri().toURL());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return Collections.unmodifiableCollection(result);
    }

    public static Collection<Path> getPaths(Path path, String blob) {
        final List<Path> result = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, blob)) {
            for (Path entry : stream) {
                result.add(entry);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return Collections.unmodifiableCollection(result);
    }

    public static void writeTo(String fileName, InputStream stream, Path newDir) throws IOException {
        final ReadableByteChannel read = Channels.newChannel(stream);

        final FileOutputStream write = new FileOutputStream(new File(newDir.toFile(), fileName));
        write.getChannel().transferFrom(read, 0, Long.MAX_VALUE);
        write.close();
    }

    public static Dimension getImageDimension(InputStream stream) throws IOException {
        Dimension dim = null;

        ImageInputStream img = ImageIO.createImageInputStream(stream);
        try {
            final Iterator<ImageReader> readers = ImageIO.getImageReaders(img);
            if (readers.hasNext()) {
                ImageReader reader = readers.next();
                try {
                    reader.setInput(img);
                    dim = new Dimension(reader.getWidth(0), reader.getHeight(0));
                } finally {
                    reader.dispose();
                }
            }
        } finally {
            if (img != null) {
                img.close();
            }
        }

        return dim;
    }

    public static Dimension getImageDimension(Path path) throws IOException {
        return getImageDimension(Files.newInputStream(path));
    }

    @SideOnly(Side.CLIENT)
    public static ResourceLocation registerTexture(String modid, String key, Path path) throws IOException {
        return registerTexture(modid, key, Files.newInputStream(path));
    }

    @SideOnly(Side.CLIENT)
    public static ResourceLocation registerTexture(String modid, String key, String path) throws IOException {
        return registerTexture(modid, key, FileSystem.class.getResourceAsStream(path));
    }

    @SideOnly(Side.CLIENT)
    public static ResourceLocation registerTexture(String modid, String key, InputStream stream) throws IOException {
        final BufferedImage image = ImageIO.read(stream);
        final ResourceLocation location = new ResourceLocation(modid, key);
        Minecraft.getMinecraft().getTextureManager().loadTexture(location, new BufferedTexture(location, image));
        return location;
    }

    @SideOnly(Side.CLIENT)
    private static final class BufferedTexture extends SimpleTexture {

        private final BufferedImage image;

        public BufferedTexture(ResourceLocation key, BufferedImage image) {
            super(key);
            this.image = image;
        }

        @Override
        public void loadTexture(IResourceManager p_110551_1_) throws IOException {
            TextureUtil.uploadTextureImageAllocate(getGlTextureId(), image, false, false);
        }
    }
}
