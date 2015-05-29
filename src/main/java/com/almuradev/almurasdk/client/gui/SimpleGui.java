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
package com.almuradev.almurasdk.client.gui;

import com.almuradev.almurasdk.AlmuraSDK;
import com.almuradev.almurasdk.FileSystem;
import com.google.common.base.Optional;
import net.malisis.core.client.gui.Anchor;
import net.malisis.core.client.gui.GuiTexture;
import net.malisis.core.client.gui.MalisisGui;
import net.malisis.core.client.gui.component.UIComponent;
import net.malisis.core.client.gui.icon.GuiIcon;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

public abstract class SimpleGui extends MalisisGui {

    public static final ResourceLocation LOCATION_GUI_SPRITE_SHEET;

    public static final GuiTexture TEXTURE_SPRITESHEET;

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
    protected final Optional<SimpleGui> parent;

    static {
        try {
            FileSystem.registerTexture(AlmuraSDK.MOD_ID, "textures/gui/gui.png", "/assets/almurasdk/textures/gui/gui.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOCATION_GUI_SPRITE_SHEET = new ResourceLocation(AlmuraSDK.MOD_ID, "textures/gui/gui.png");
        TEXTURE_SPRITESHEET = new GuiTexture(LOCATION_GUI_SPRITE_SHEET, 284, 142);

        ICON_EMPTY = TEXTURE_SPRITESHEET.getIcon(283, 141, 1, 1);
        ICON_BAR = TEXTURE_SPRITESHEET.getIcon(0, 126, 256, 14);
        ICON_HEART = TEXTURE_SPRITESHEET.getIcon(149, 62, 26, 26);
        ICON_ARMOR = TEXTURE_SPRITESHEET.getIcon(64, 63, 20, 27);
        ICON_HUNGER = TEXTURE_SPRITESHEET.getIcon(198, 96, 28, 29);
        ICON_STAMINA = TEXTURE_SPRITESHEET.getIcon(99, 93, 32, 31);
        ICON_XP = TEXTURE_SPRITESHEET.getIcon(169, 98, 24, 24);
        ICON_PLAYER = TEXTURE_SPRITESHEET.getIcon(67, 92, 28, 32);
        ICON_COMPASS = TEXTURE_SPRITESHEET.getIcon(118, 66, 30, 26);
        ICON_MAP = TEXTURE_SPRITESHEET.getIcon(0, 95, 32, 26);
        ICON_WORLD = TEXTURE_SPRITESHEET.getIcon(133, 93, 32, 32);
        ICON_CLOCK = TEXTURE_SPRITESHEET.getIcon(86, 64, 28, 26);
        ICON_CLOSE_NORMAL = TEXTURE_SPRITESHEET.getIcon(239, 69, 45, 19);
        ICON_CLOSE_HOVER = TEXTURE_SPRITESHEET.getIcon(239, 88, 45, 19);
        ICON_CLOSE_PRESSED = TEXTURE_SPRITESHEET.getIcon(239, 107, 45, 19);
    }

    /**
     * Creates a gui with an absent parent
     */
    public SimpleGui() {
        this(null);
    }

    /**
     * Creates a gui with a parent
     *
     * @param parent the {@link SimpleGui} that we came from
     */
    public SimpleGui(SimpleGui parent) {
        this.parent = Optional.fromNullable(parent);
        renderer.setDefaultTexture(TEXTURE_SPRITESHEET);
        mc = Minecraft.getMinecraft();
    }

    public static int getPaddedX(UIComponent<?> component, int padding) {
        return getPaddedX(component, padding, Anchor.LEFT);
    }

    public static int getPaddedX(UIComponent<?> component, int padding, int anchor) {
        if (component == null) {
            throw new IllegalArgumentException("Component cannot be null!");
        } else if (anchor == Anchor.BOTTOM || anchor == Anchor.TOP) {
            throw new IllegalArgumentException("Anchor must be Anchor.LEFT or Anchor.RIGHT.");
        } else if (anchor == Anchor.RIGHT) {
            return component.getX() - component.getWidth() - padding;
        } else {
            return component.getX() + component.getWidth() + padding;
        }
    }

    public static int getPaddedY(UIComponent<?> component, int padding) {
        if (component == null) {
            return 0;
        }
        return component.getY() + component.getHeight() + padding;
    }

    public static int getPaddedY(UIComponent<?> component, int padding, int anchor) {
        if (component == null) {
            throw new IllegalArgumentException("Component cannot be null!");
        } else if (anchor == Anchor.LEFT || anchor == Anchor.RIGHT) {
            throw new IllegalArgumentException("Anchor must be Anchor.TOP or Anchor.BOTTOM.");
        } else if (anchor == Anchor.BOTTOM) {
            return component.getY() - component.getHeight() - padding;
        } else {
            return component.getY() + component.getHeight() + padding;
        }
    }

    /**
     * Closes this {@link com.almuradev.almurasdk.client.gui.SimpleGui} and displays the parent, if present.
     */
    @Override
    public final void close() {
        Keyboard.enableRepeatEvents(false);
        if (mc.thePlayer != null) {
            mc.thePlayer.closeScreen();
        }

        onClose();

        mc.displayGuiScreen(parent.isPresent() ? parent.get() : null);
        if (!parent.isPresent()) {
            mc.setIngameFocus();
        }
    }

    public void onClose() {

    }
}

