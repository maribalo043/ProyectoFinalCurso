package com.mario.proyect.Validator;

import java.util.regex.Pattern;
 
public class BCryptUtils {
 
    // Pattern for bcrypt hash format
    private static final Pattern BCRYPT_PATTERN = Pattern.compile("\\$2[aby]?\\$\\d{2}\\$[./A-Za-z0-9]{53}");
 
    public static boolean isBCryptHash(String str) {
        if (str == null) {
            return false;
        }
        return BCRYPT_PATTERN.matcher(str).matches();
    }
}