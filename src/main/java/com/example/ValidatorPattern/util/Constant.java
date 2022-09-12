package com.example.ValidatorPattern.util;

import java.util.regex.Pattern;

public class Constant {

    public static final Pattern EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern NAME_REGEX = Pattern.compile("^[a-zA-Z]+$");

}
