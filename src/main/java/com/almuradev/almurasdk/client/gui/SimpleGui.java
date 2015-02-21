/*
 * This file is part of AlmuraSDK, All Rights Reserved.
 *
 * Copyright (c) 2015 AlmuraDev <http://beta.almuramc.com/>
 */
package com.almuradev.almurasdk.client.gui;

import com.almuradev.almurasdk.FileSystem;
import com.google.common.base.Optional;
import net.malisis.core.client.gui.GuiTexture;
import net.malisis.core.client.gui.MalisisGui;
import net.malisis.core.client.gui.icon.GuiIcon;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.GuiOpenEvent;
import org.lwjgl.input.Keyboard;

public abstract class SimpleGui extends MalisisGui {
    public static final GuiTexture TEXTURE_SPRITESHEET = new GuiTexture(FileSystem.LOCATION_GUI_SPRITE_SHEET, 248, 142);

    public static final GuiIcon ICON_EMPTY = TEXTURE_SPRITESHEET.getIcon(283, 141, 1, 1);
    public static final GuiIcon ICON_BAR = TEXTURE_SPRITESHEET.getIcon(0, 126, 256, 14);
    public static final GuiIcon ICON_HEART = TEXTURE_SPRITESHEET.getIcon(149, 62, 26, 26);
    public static final GuiIcon ICON_ARMOR = TEXTURE_SPRITESHEET.getIcon(64, 63, 20, 27);
    public static final GuiIcon ICON_HUNGER = TEXTURE_SPRITESHEET.getIcon(198, 96, 28, 29);
    public static final GuiIcon ICON_STAMINA = TEXTURE_SPRITESHEET.getIcon(99, 93, 32, 31);
    public static final GuiIcon ICON_XP = TEXTURE_SPRITESHEET.getIcon(169, 98, 24, 24);
    public static final GuiIcon ICON_PLAYER = TEXTURE_SPRITESHEET.getIcon(67, 92, 28, 32);
    public static final GuiIcon ICON_COMPASS = TEXTURE_SPRITESHEET.getIcon(118, 66, 30, 26);
    public static final GuiIcon ICON_MAP = TEXTURE_SPRITESHEET.getIcon(0, 95, 32, 26);
    public static final GuiIcon ICON_WORLD = TEXTURE_SPRITESHEET.getIcon(133, 93, 32, 32);
    public static final GuiIcon ICON_CLOCK = TEXTURE_SPRITESHEET.getIcon(86, 64, 28, 26);
    public static final GuiIcon ICON_CLOSE_NORMAL = TEXTURE_SPRITESHEET.getIcon(239, 69, 45, 19);
    public static final GuiIcon ICON_CLOSE_HOVER = TEXTURE_SPRITESHEET.getIcon(239, 88, 45, 19);
    public static final GuiIcon ICON_CLOSE_PRESSED = TEXTURE_SPRITESHEET.getIcon(239, 107, 45, 19);
    protected final Optional<SimpleGui> parent;

    /**
     * Creates a gui
     *
     * @param parent the {@link SimpleGui} that we came from
     */
    public SimpleGui(SimpleGui parent) {
        this.parent = Optional.fromNullable(parent);
        renderer.setDefaultTexture(TEXTURE_SPRITESHEET);
        mc = Minecraft.getMinecraft();
    }

    /**
     * Used to construct this {@link com.almuradev.almurasdk.client.gui.SimpleGui}. Must be called before displaying
     * to the screen.
     */
    protected abstract void buildGui();

    /**
     * Closes this {@link com.almuradev.almurasdk.client.gui.SimpleGui} and displays the parent, if present.
     */
    @Override
    public final void close() {
        Keyboard.enableRepeatEvents(false);
        if (mc.thePlayer != null) {
            mc.thePlayer.closeScreen();
        }
        mc.displayGuiScreen(parent.isPresent() ? parent.get() : null);
        if (!parent.isPresent()) {
            mc.setIngameFocus();
        }
    }
}

