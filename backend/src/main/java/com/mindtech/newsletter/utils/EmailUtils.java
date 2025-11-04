package com.mindtech.newsletter.utils;

import java.util.regex.Pattern;

import static org.apache.logging.log4j.util.Strings.isNotBlank;

public class EmailUtils {

    private static final Pattern PADRAO_EMAIL_VALIDO = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    public static boolean isValidEmail(String email){
        if(isNotBlank(email)){
            return PADRAO_EMAIL_VALIDO.matcher(email.trim()).matches();
        }
        return false;
    }
}