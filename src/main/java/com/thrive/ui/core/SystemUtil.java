package com.thrive.ui.core;

public class SystemUtil {
    private static final String osName = System.getProperty("os.name").toLowerCase();
    
    private static final String MAC_OS = "mac os x";
    private static final String WINDOWS = "windows";
    private static final String LINUX = "linux";
    
    protected static boolean isMacOs() {
        return osName.startsWith(MAC_OS);
    }
    
    protected static boolean isWindowsOS() {
        return osName.startsWith(WINDOWS);
    }
    
    protected static boolean isLinuxOS() {
        return osName.startsWith(LINUX);
    }
}