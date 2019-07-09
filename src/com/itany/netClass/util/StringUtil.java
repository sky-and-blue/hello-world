package com.itany.netClass.util;

import java.util.UUID;

public class StringUtil {

    public static String renameFileName(String fileName) { // abc.jpg
        int dotIndex = fileName.lastIndexOf(".");
        String suffix = fileName.substring(dotIndex);
        return UUID.randomUUID().toString() + suffix;
    }
}
