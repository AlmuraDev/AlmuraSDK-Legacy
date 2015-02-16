/**
 * This file is part of Almura-SDK, All Rights Reserved.
 *
 * Copyright (c) 2015 AlmuraDev <http://github.com/AlmuraDev/>
 */
package com.almuradev.almurasdk.gui;

import com.almuradev.almurasdk.AlmuraSDK;
import com.almuradev.almurasdk.Filesystem;
import com.almuradev.almurasdk.gui.components.UIBackground;
import com.google.common.base.Optional;
import net.malisis.core.client.gui.GuiTexture;
import net.malisis.core.client.gui.MalisisGui;
import net.malisis.core.client.gui.component.UIComponent;
import net.malisis.core.client.gui.icon.GuiIcon;
import net.malisis.core.renderer.animation.Animation;
import net.malisis.core.renderer.animation.transformation.SizeTransform;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.IOException;

public abstract class SimpleGui extends MalisisGui {

    public static final GuiIcon ICON_EMPTY;
    public static final GuiIcon ICON_BAR;
    public static final GuiIcon ICON_HEART;
    public static final GuiIcon ICON_ARMOR;
    public static final GuiIcon ICON_HUNGER;
    public static final GuiIcon ICON_STAMINA;
    public static final GuiIcon ICON_XP;
    public static final GuiIcon ICON_PLAYER;
    public static final GuiIcon ICON_COMPASS;
    public static final GuiIcon ICON_MAP;
    public static final GuiIcon ICON_WORLD;
    public static final GuiIcon ICON_CLOCK;
    public static final GuiIcon ICON_CLOSE_NORMAL;
    public static final GuiIcon ICON_CLOSE_HOVER;
    public static final GuiIcon ICON_CLOSE_PRESSED;
    private static GuiTexture TEXTURE_DEFAULT;
    protected final Optional<SimpleGui> parent;
    protected final Optional<UIBackground> background;

    static {
        try {
            final ResourceLocation loc = Filesystem.registerTexture(AlmuraSDK.MOD_ID, "textures/gui/gui.png", Filesystem.CONFIG_GUI_SPRITESHEET_PATH);
            final Dimension dim = Filesystem.getImageDimension(Filesystem.CONFIG_GUI_SPRITESHEET_PATH);
            TEXTURE_DEFAULT = new GuiTexture(loc, dim.width, dim.height);
        } catch (IOException e) {
            TEXTURE_DEFAULT = new GuiTexture(null);
        }

        ICON_EMPTY = TEXTURE_DEFAULT.getIcon(283, 141, 1, 1);
        ICON_BAR = TEXTURE_DEFAULT.getIcon(0, 126, 256, 14);
        ICON_HEART = TEXTURE_DEFAULT.getIcon(149, 62, 26, 26);
        ICON_ARMOR = TEXTURE_DEFAULT.getIcon(64, 63, 20, 27);
        ICON_HUNGER = TEXTURE_DEFAULT.getIcon(198, 96, 28, 29);
        ICON_STAMINA = TEXTURE_DEFAULT.getIcon(99, 93, 32, 31);
        ICON_XP = TEXTURE_DEFAULT.getIcon(169, 98, 24, 24);
        ICON_PLAYER = TEXTURE_DEFAULT.getIcon(67, 92, 28, 32);
        ICON_COMPASS = TEXTURE_DEFAULT.getIcon(118, 66, 30, 26);
        ICON_MAP = TEXTURE_DEFAULT.getIcon(0, 95, 32, 26);
        ICON_WORLD = TEXTURE_DEFAULT.getIcon(133, 93, 32, 32);
        ICON_CLOCK = TEXTURE_DEFAULT.getIcon(86, 64, 28, 26);
        ICON_CLOSE_NORMAL = TEXTURE_DEFAULT.getIcon(239, 69, 45, 19);
        ICON_CLOSE_HOVER = TEXTURE_DEFAULT.getIcon(239, 88, 45, 19);
        ICON_CLOSE_PRESSED = TEXTURE_DEFAULT.getIcon(239, 107, 45, 19);
    }

    /**
     * Creates a gui with a parent screen that does not have a background
     *
     * @param parent the {@link SimpleGui} that we came from
     */
    public SimpleGui(SimpleGui parent) {
        this(parent, false);
    }

    /**
     * Creates a gui with a parent screen that can show a background
     *
     * @param parent the {@link SimpleGui} that we came from
     * @param backgroundEnabled true to show an animated {@link UIBackground}, false if not
     */
    public SimpleGui(SimpleGui parent, boolean backgroundEnabled) {
        renderer.setDefaultTexture(TEXTURE_DEFAULT);
        this.parent = Optional.fromNullable(parent);
        mc = Minecraft.getMinecraft();

        if (backgroundEnabled) {
            background = Optional.of(new UIBackground(this));
            background.get().register(this);
            addToScreen(background.get());
            animate(background.get().animation);
        } else {
            background = Optional.absent();
        }
    }

    /**
     * Gets if the background of this {@link SimpleGui} is enabled or not
     * @return true if enabled, false if not
     */
    public boolean isBackgroundEnabled() {
        return background.isPresent();
    }

    public static int getPaddedX(UIComponent component, int padding) {
        if (component == null) {
            return 0;
        }
        return component.getX() + component.getWidth() + padding;
    }

    public static int getPaddedY(UIComponent component, int padding) {
        if (component == null) {
            return 0;
        }
        return component.getY() + component.getHeight() + padding;
    }

    protected abstract void setup();

    /**
     * Closes the current screen and displays the parent screen
     */
    @Override
    public void close() {
        Keyboard.enableRepeatEvents(false);
        if (mc.thePlayer != null) {
            mc.thePlayer.closeScreen();
        }
        mc.displayGuiScreen(parent.isPresent() ? parent.get() : null);
        mc.setIngameFocus();
    }

    @Override
    public void setWorldAndResolution(Minecraft minecraft, int width, int height) {
        if (background.isPresent() && (this.width != width || this.height != height)) {
            background.get().animation =
                    new Animation(background.get(),
                                  new SizeTransform((int) (width * UIBackground.ZOOM_LEVEL), (int) (height * UIBackground.ZOOM_LEVEL), width,
                                                    height)
                                          .forTicks(UIBackground.ANIMATION_SPEED));
            animate(background.get().animation);
        }
        super.setWorldAndResolution(minecraft, width, height);
    }
}
