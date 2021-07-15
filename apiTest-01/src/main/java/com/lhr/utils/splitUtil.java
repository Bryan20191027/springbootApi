package com.lhr.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class splitUtil {
    /*public static List<String> photosUrlListResult(String str) {
        List<String> urlList = new ArrayList<>();
        Pattern pattern = Pattern.compile("https?://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            urlList.add(matcher.group());
        }
        return urlList;
    }*/

    public static List<String> photosUrlListResult(List<LinkedHashMap<String,String>> mapList) {
        List<String> urlList2 = new ArrayList<>();
        for(LinkedHashMap<String,String> mapSimple:mapList){
            urlList2.add(mapSimple.get("url"));
        }
        return urlList2;
    }

    public static List<String> telResult(String str){
        List<String> resultList=new ArrayList<>();
        String[] strList= str.split(";");
        for(String strSimple:strList){
            resultList.add(strSimple);
        }
        return resultList;
    }
}
