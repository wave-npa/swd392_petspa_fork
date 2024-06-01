package org.petspa.petcaresystem.utils;

import java.util.regex.Pattern;

public class Validate {
    private static final String PHONE_REGEX = "^(?:\\+?\\d{1,3})?[\\s.-]?(?:\\(\\d{3}\\))?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$";
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

    public static boolean isValidPhoneNumber(String phone) {
        return PHONE_PATTERN.matcher(phone).matches();
    }
}
