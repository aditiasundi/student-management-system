package com.sms.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidationTest {

    @Test
    public void testIsValidEmail() {
        assertTrue(Validation.isValidEmail("test@example.com"));
        assertTrue(Validation.isValidEmail("user.name+tag@domain.co.uk"));
        assertFalse(Validation.isValidEmail(null));
        assertFalse(Validation.isValidEmail(""));
        assertFalse(Validation.isValidEmail("invalid-email"));
    }

    @Test
    public void testIsValidPhone() {
        assertTrue(Validation.isValidPhone("12345678"));
        assertTrue(Validation.isValidPhone("9876543210"));
        assertFalse(Validation.isValidPhone(null));
        assertFalse(Validation.isValidPhone(""));
        assertFalse(Validation.isValidPhone("123456")); // too short (7-15)
        assertFalse(Validation.isValidPhone("abcdefgh")); // non-digits
    }

    @Test
    public void testIsNotEmpty() {
        assertTrue(Validation.isNotEmpty("hello"));
        assertTrue(Validation.isNotEmpty(" a "));
        assertFalse(Validation.isNotEmpty(null));
        assertFalse(Validation.isNotEmpty(""));
        assertFalse(Validation.isNotEmpty("   "));
    }
}
