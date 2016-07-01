package com.ht.fyforandroid.net.threadpoolnet;


public class Utils {
    /**
     * @param value
     * @param defaultValue
     * @return integer
     * @throws
     * @Title: convertToInt
     * @Description: 对象转化为整数数字类型
     */
    public final static int convertToInt(Object value, int defaultValue) {
        if (value == null || "".equals(value.toString().trim())) {
            return defaultValue;
        }
        try {
            return Integer.valueOf(value.toString());
        } catch (Exception e) {
            try {
                return Double.valueOf(value.toString()).intValue();
            } catch (Exception e1) {
                return defaultValue;
            }
        }
    }


    public static String UrlEncodeUnicode(final String s) {
        if (s == null) {
            return null;
        }
        final int length = s.length();
        final StringBuilder builder = new StringBuilder(length); // buffer
        for (int i = 0; i < length; i++) {
            final char ch = s.charAt(i);
            if ((ch & 0xff80) == 0) {
                if (IsSafe(ch)) {
                    builder.append(ch);
                } else if (ch == ' ') {
                    builder.append('+');
                } else {
                    builder.append('%');
                    builder.append(IntToHex((ch >> 4) & 15));
                    builder.append(IntToHex(ch & 15));
                }
            } else {
                builder.append("%u");
                builder.append(IntToHex((ch >> 12) & 15));
                builder.append(IntToHex((ch >> 8) & 15));
                builder.append(IntToHex((ch >> 4) & 15));
                builder.append(IntToHex(ch & 15));
            }
        }
        return builder.toString();
    }

    static char IntToHex(final int n) {
        if (n <= 9) {
            return (char) (n + 0x30);
        }
        return (char) ((n - 10) + 0x61);
    }

    static boolean IsSafe(final char ch) {
        if ((((ch >= 'a') && (ch <= 'z')) || ((ch >= 'A') && (ch <= 'Z'))) || ((ch >= '0') && (ch <= '9'))) {
            return true;
        }
        switch (ch) {
            case '\'':
            case '(':
            case ')':
            case '*':
            case '-':
            case '.':
            case '_':
            case '!':
                return true;
        }
        return false;
    }
}
