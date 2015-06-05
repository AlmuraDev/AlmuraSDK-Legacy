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
package com.almuradev.almurasdk.util;

import net.malisis.core.renderer.font.FontRenderOptions;

public class FontRenderOptionsBuilder {

    private float fontScale;
    private int color;
    private boolean shadow, bold, italic, strikethrough, spansMultipleLines;

    public FontRenderOptionsBuilder fontScale(float fontScale) {
        this.fontScale = fontScale;
        return this;
    }

    public FontRenderOptionsBuilder color(int color) {
        this.color = color;
        return this;
    }

    public FontRenderOptionsBuilder shadow(boolean shadow) {
        this.shadow = shadow;
        return this;
    }

    public FontRenderOptionsBuilder bold(boolean bold) {
        this.bold = bold;
        return this;
    }

    public FontRenderOptionsBuilder italic(boolean italic) {
        this.italic = italic;
        return this;
    }

    public FontRenderOptionsBuilder strikethrough(boolean strikethrough) {
        this.strikethrough = strikethrough;
        return this;
    }

    public FontRenderOptionsBuilder spansMultipleLines(boolean spansMultipleLines) {
        this.spansMultipleLines = spansMultipleLines;
        return this;
    }

    public FontRenderOptionsBuilder from(FontRenderOptions existing) {
        this.fontScale = existing.fontScale;
        this.color = existing.color;
        this.shadow = existing.shadow;
        this.bold = existing.bold;
        this.italic = existing.italic;
        this.strikethrough = existing.strikethrough;
        this.spansMultipleLines = existing.multiLines;
        return this;
    }

    public FontRenderOptions build() {
        final FontRenderOptions options = new FontRenderOptions();
        options.fontScale = fontScale;
        options.color = color;
        options.shadow = shadow;
        options.bold = bold;
        options.italic = italic;
        options.strikethrough = strikethrough;
        options.multiLines = spansMultipleLines;
        return options;
    }

    public static FontRenderOptionsBuilder builder() {
        return new FontRenderOptionsBuilder();
    }
}
