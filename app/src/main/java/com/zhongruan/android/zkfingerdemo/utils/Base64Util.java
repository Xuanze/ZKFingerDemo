package com.zhongruan.android.zkfingerdemo.utils;

import android.support.v4.view.MotionEventCompat;

public class Base64Util {
    private static final char[] base64EncodeChars;

    static {
        base64EncodeChars = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    }

    private Base64Util() {
    }

    public static String encode(byte[] data) {
        int i;
        StringBuffer sb = new StringBuffer();
        int len = data.length;
        int i2 = 0;
        while (i2 < len) {
            i = i2 + 1;
            int b1 = data[i2] & MotionEventCompat.ACTION_MASK;
            if (i == len) {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[(b1 & 3) << 4]);
                sb.append("==");
                break;
            }
            i2 = i + 1;
            int b2 = data[i] & MotionEventCompat.ACTION_MASK;
            if (i2 == len) {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[((b1 & 3) << 4) | ((b2 & 240) >>> 4)]);
                sb.append(base64EncodeChars[(b2 & 15) << 2]);
                sb.append("=");
                i = i2;
                break;
            }
            i = i2 + 1;
            int b3 = data[i2] & MotionEventCompat.ACTION_MASK;
            sb.append(base64EncodeChars[b1 >>> 2]);
            sb.append(base64EncodeChars[((b1 & 3) << 4) | ((b2 & 240) >>> 4)]);
            sb.append(base64EncodeChars[((b2 & 15) << 2) | ((b3 & 192) >>> 6)]);
            sb.append(base64EncodeChars[b3 & 63]);
            i2 = i;
        }
        i = i2;
        return sb.toString();
    }
}
