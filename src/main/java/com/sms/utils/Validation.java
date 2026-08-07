package com.sms.utils;

import java.util.regex.Pattern;

/**
 * Basic validation utilities used across the app.
 */
public class Validation {
    private static final Pattern EMAIL = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern PHONE = Pattern.compile("^\\d{7,15}$");

    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        return EMAIL.matcher(email).matches();
    }

    public static boolean isValidPhone(String phone) {
        if (phone == null) return false;
        return PHONE.matcher(phone).matches();
    }

    public static boolean isNotEmpty(String s) { return s != null && !s.trim().isEmpty(); }
}
