package com.lhr.utils;

import java.util.regex.Pattern;

public class typeEsitimate {
    public static Boolean typeJudge(String str){
        String pattern="^(090)[12345]\\d{2}$";
        return str!="090201"&&str.matches(pattern);
    }
}
