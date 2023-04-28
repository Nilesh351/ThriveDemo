package com.thrive.common.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;

import com.google.common.collect.Lists;

public class CommomUtils {
	
    private static final Logger LOGGER = LogManager.getLogger(CommomUtils.class);
	
    private static Platform platform;
    
    public static Platform getCurrentPlatform () {
        if (platform == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win")) {
                platform = Platform.WINDOWS;
            } else if (operSys.contains("nix") || operSys.contains("nux")
                    || operSys.contains("aix")) {
                platform = Platform.LINUX;
            } else if (operSys.contains("mac")) {
                platform = Platform.MAC;
            }
        }
        return platform;
    }
    
    public static String escapeSpecialCharacters(String input) {
        List<String> specialCharacters = Lists.newArrayList("\\","^","$","{","}","[","]","(",")",".","*","+","?","|","<",">","-","&","%");
        return Arrays.stream(input.split("")).map((c) -> {
                    if (specialCharacters.contains(c)) return "\\" + c;
                    else return c;
        }).collect(Collectors.joining());
    }
    
    public static String escapeSpecialChar(String inputString) {
        String regex = "[\\[+\\]+:{}^~?\\\\/()><=\"!]";
        return StringUtils.replaceAll(inputString, regex, "\\\\$0");
    }
    


}
