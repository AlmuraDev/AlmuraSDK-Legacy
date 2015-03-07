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

public class Color {

    public static final char CHAR_COLOR_BEGIN = '§';
    public static final char CHAR_COLOR_BLACK = '0';
    public static final int INTEGER_COLOR_BLACK = 0;
    private final String name;
    private final char chatCode;
    private final int chatIntCode, guiColorCode;
    private final boolean isFormattingColor;

    public Color(String name, char chatCode, int chatIntCode) {
        this.name = name;
        this.chatCode = chatCode;
        this.chatIntCode = chatIntCode;
        this.isFormattingColor = false;
        this.guiColorCode = INTEGER_COLOR_BLACK;
    }

    public Color(String name, char chatCode, int chatIntCode, int guiColorCode) {
        this.name = name;
        this.chatCode = chatCode;
        this.chatIntCode = chatIntCode;
        this.guiColorCode = guiColorCode;
        this.isFormattingColor = false;
    }

    public Color(String name, char chatCode, int chatIntCode, boolean isFormattingColor) {
        this.name = name;
        this.chatCode = chatCode;
        this.chatIntCode = chatIntCode;
        this.isFormattingColor = isFormattingColor;
        this.guiColorCode = INTEGER_COLOR_BLACK;
    }

    public Color(String name, int guiColorCode) {
        this.name = name;
        this.chatCode = CHAR_COLOR_BLACK;
        this.chatIntCode = INTEGER_COLOR_BLACK;
        this.isFormattingColor = false;
        this.guiColorCode = guiColorCode;
    }

    public String getName() {
        return name;
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
        return !this.isFormattingColor;
    }

    @Override
    public String toString() {
        return new String(new char[]{CHAR_COLOR_BEGIN, chatCode});
    }
}
