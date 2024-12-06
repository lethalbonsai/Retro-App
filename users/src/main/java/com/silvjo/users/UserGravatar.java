package com.silvjo.users;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserGravatar {

    public static String getGravatarUrl(String email) {
        return "https://www.gravatar.com/avatar/" + md5Hex(email) + "?d=identicon";
    }

    private static String hex(byte[] array) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            stringBuilder.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return stringBuilder.toString();
    }

    private static String md5Hex(String message) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            return hex(messageDigest.digest(message.getBytes("CP1252")));
        }catch (NoSuchAlgorithmException exception) {
        }catch (UnsupportedEncodingException exception) {
        }
        return "23bb62a7d0ca63c9a804908e57bf6bd4";
    }
}
