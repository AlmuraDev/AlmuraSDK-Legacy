package com.almuradev.almurasdk.util;

public class Color {
    public static final char CHAR_COLOR_BEGIN = '§';
    public static final char CHAR_COLOR_BLACK = '0';
    public static final int INTEGER_COLOR_BLACK = 0;
    private final char chatCode;
    private final int chatIntCode, guiColorCode;
    private final boolean isFormattingColor;

    public Color(char chatCode, int chatIntCode) {
        this.chatCode = chatCode;
        this.chatIntCode = chatIntCode;
        this.isFormattingColor = false;
        this.guiColorCode = INTEGER_COLOR_BLACK;
    }

    public Color(char chatCode, int chatIntCode, int guiColorCode) {
        this.chatCode = chatCode;
        this.chatIntCode = chatIntCode;
        this.guiColorCode = guiColorCode;
        this.isFormattingColor = false;
    }

    public Color(char chatCode, int chatIntCode, boolean isFormattingColor) {
        this.chatCode = chatCode;
        this.chatIntCode = chatIntCode;
        this.isFormattingColor = isFormattingColor;
        this.guiColorCode = INTEGER_COLOR_BLACK;
    }

    public Color(int guiColorCode) {
        this.chatCode = CHAR_COLOR_BLACK;
        this.chatIntCode = INTEGER_COLOR_BLACK;
        this.isFormattingColor = false;
        this.guiColorCode = guiColorCode;
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
