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

import net.minecraft.util.EnumChatFormatting;

public final class Colors {
    public static final Color BLACK = new Color(EnumChatFormatting.BLACK.getFormattingCode(), 0, 0);
    public static final Color DARK_BLUE = new Color(EnumChatFormatting.DARK_BLUE.getFormattingCode(), 1, 170);
    public static final Color DARK_GREEN = new Color(EnumChatFormatting.DARK_GREEN.getFormattingCode(), 2, 43520);
    public static final Color DARK_AQUA = new Color(EnumChatFormatting.DARK_AQUA.getFormattingCode(), 3, 43690);
    public static final Color DARK_RED = new Color(EnumChatFormatting.DARK_RED.getFormattingCode(), 4, 11141120);
    public static final Color DARK_PURPLE = new Color(EnumChatFormatting.DARK_PURPLE.getFormattingCode(), 5, 11141290);
    public static final Color GOLD = new Color(EnumChatFormatting.GOLD.getFormattingCode(), 6, 16755200);
    public static final Color GRAY = new Color(EnumChatFormatting.GRAY.getFormattingCode(), 7, 11184810);
    public static final Color DARK_GRAY = new Color(EnumChatFormatting.DARK_GRAY.getFormattingCode(), 8, 5592405);
    public static final Color BLUE = new Color(EnumChatFormatting.BLUE.getFormattingCode(), 9, 5592575);
    public static final Color GREEN = new Color(EnumChatFormatting.GREEN.getFormattingCode(), 10, 5635925);
    public static final Color AQUA = new Color(EnumChatFormatting.AQUA.getFormattingCode(), 11, 5636095);
    public static final Color RED = new Color(EnumChatFormatting.RED.getFormattingCode(), 12, 16733525);
    public static final Color LIGHT_PURPLE = new Color(EnumChatFormatting.LIGHT_PURPLE.getFormattingCode(), 13, 16733695);
    public static final Color YELLOW = new Color(EnumChatFormatting.YELLOW.getFormattingCode(), 14, 16777045);
    public static final Color WHITE = new Color(EnumChatFormatting.WHITE.getFormattingCode(), 15, 16777215);
    public static final Color OBFUSCATED = new Color(EnumChatFormatting.OBFUSCATED.getFormattingCode(), 16, true);
    public static final Color BOLD = new Color(EnumChatFormatting.BOLD.getFormattingCode(), 17, true);
    public static final Color STRIKETHROUGH = new Color(EnumChatFormatting.STRIKETHROUGH.getFormattingCode(), 18, true);
    public static final Color UNDERLINE = new Color(EnumChatFormatting.UNDERLINE.getFormattingCode(), 19, true);
    public static final Color ITALIC = new Color(EnumChatFormatting.ITALIC.getFormattingCode(), 20, true);
    public static final Color RESET = new Color(EnumChatFormatting.RESET.getFormattingCode(), 21);
}
