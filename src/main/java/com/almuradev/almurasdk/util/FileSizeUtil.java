package com.almuradev.almurasdk.util;

public class FileSizeUtil {

    private static final String[] SIZE_SUFFIXES = { "B", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB" };

    public static String format(long value) {
        if (value < 0) return "-" + format(-value);
        if (value == 0) return "0B";

        final int mag = (int) (Math.log10(value) / Math.log10(1024));
        final float adjustedSize = (float) value / (1L << (mag * 10));

        final String format = (mag > 2 ? "%.2f" : "%.0f") + "%s";
        return String.format(format, adjustedSize, SIZE_SUFFIXES[mag]);
    }
}
