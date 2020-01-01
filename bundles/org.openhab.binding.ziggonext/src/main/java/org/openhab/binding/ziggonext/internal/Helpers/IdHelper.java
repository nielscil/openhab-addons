package org.openhab.binding.ziggonext.internal.Helpers;

public class IdHelper {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String CreateId(int length) {
        String result = "";

        for (int i = 0; i < length; i++) {
            result += CHARACTERS.charAt((int) Math.floor(Math.random() * CHARACTERS.length()));
        }

        return result;
    }
}
