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
package com.almuradev.almurasdk.util;

import com.google.common.collect.Maps;
import net.minecraft.util.EnumChatFormatting;

import java.util.Map;

public enum Colors {
    BLACK(EnumChatFormatting.BLACK.getFormattingCode(), 0),
    DARK_BLUE(EnumChatFormatting.DARK_BLUE.getFormattingCode(), 1),
    DARK_GREEN(EnumChatFormatting.DARK_GREEN.getFormattingCode(), 2),
    DARK_AQUA(EnumChatFormatting.DARK_AQUA.getFormattingCode(), 3),
    DARK_RED(EnumChatFormatting.DARK_RED.getFormattingCode(), 4),
    DARK_PURPLE(EnumChatFormatting.DARK_PURPLE.getFormattingCode(), 5),
    GOLD(EnumChatFormatting.GOLD.getFormattingCode(), 6),
    GRAY(EnumChatFormatting.GRAY.getFormattingCode(), 7),
    DARK_GRAY(EnumChatFormatting.DARK_GRAY.getFormattingCode(), 8),
    BLUE(EnumChatFormatting.BLUE.getFormattingCode(), 9),
    GREEN(EnumChatFormatting.GREEN.getFormattingCode(), 10),
    AQUA(EnumChatFormatting.AQUA.getFormattingCode(), 11),
    RED(EnumChatFormatting.RED.getFormattingCode(), 12),
    LIGHT_PURPLE(EnumChatFormatting.LIGHT_PURPLE.getFormattingCode(), 13),
    YELLOW(EnumChatFormatting.YELLOW.getFormattingCode(), 14),
    WHITE(EnumChatFormatting.WHITE.getFormattingCode(), 15),
    OBFUSCATED(EnumChatFormatting.OBFUSCATED.getFormattingCode(), 16, true),
    BOLD(EnumChatFormatting.BOLD.getFormattingCode(), 17, true),
    STRIKETHROUGH(EnumChatFormatting.STRIKETHROUGH.getFormattingCode(), 18, true),
    UNDERLINE(EnumChatFormatting.UNDERLINE.getFormattingCode(), 19, true),
    ITALIC(EnumChatFormatting.ITALIC.getFormattingCode(), 20, true),
    RESET(EnumChatFormatting.RESET.getFormattingCode(), 21);

    public static final char CHAR_COLOR_BEGIN = '§';
    public static final int INTEGER_COLOR_NONE = -1;
    private static final Map<Integer, Colors> COLOR_BY_ID;
    private static final Map<Character, Colors> COLOR_BY_CHAR;
    private final char chatCode;
    private final int chatIntCode, guiColorCode;
    private final boolean isFormattingColor;

    static {
        COLOR_BY_ID = Maps.newHashMap();
        COLOR_BY_CHAR = Maps.newHashMap();

        for (Colors color : values()) {
            COLOR_BY_ID.put(color.chatIntCode, color);
            COLOR_BY_CHAR.put(color.chatCode, color);
        }
    }

    Colors(char chatCode, int chatIntCode) {
        this.chatCode = chatCode;
        this.chatIntCode = chatIntCode;
        this.isFormattingColor = false;
        this.guiColorCode = INTEGER_COLOR_NONE;
    }

    Colors(char chatCode, int chatIntCode, int guiColorCode) {
        this.chatCode = chatCode;
        this.chatIntCode = chatIntCode;
        this.guiColorCode = guiColorCode;
        this.isFormattingColor = false;
    }

    Colors(char chatCode, int chatIntCode, boolean isFormattingColor) {
        this.chatCode = chatCode;
        this.chatIntCode = chatIntCode;
        this.isFormattingColor = isFormattingColor;
        this.guiColorCode = INTEGER_COLOR_NONE;
    }

    public char getChatCode() {
        return chatCode;
    }

    public int getChatIntCode() {
        return chatIntCode;
    }

    public int getGuiColorCode() {
        return guiColorCode;
    }

    public boolean isFormattingColor() {
        return isFormattingColor;
    }

    public boolean isColor() {
        return !this.isFormattingColor && this != RESET;
    }

    @Override
    public String toString() {
        return new String(new char[]{CHAR_COLOR_BEGIN, chatCode});
    }
}
