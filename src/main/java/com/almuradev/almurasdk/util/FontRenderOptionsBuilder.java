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
