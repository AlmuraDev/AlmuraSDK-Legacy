/*
 * This file is part of AlmuraSDK, All Rights Reserved.
 *
 * Copyright (c) 2015 AlmuraDev <http://beta.almuramc.com/>
 */
package com.almuradev.almurasdk;

import com.almuradev.almurasdk.client.gui.BufferedTexture;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
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
import java.util.*;
import java.util.List;

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

    public static final ResourceLocation LOCATION_GUI_SPRITE_SHEET = new ResourceLocation(AlmuraSDK.MOD_ID, "textures/gui/gui.png");

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
}
