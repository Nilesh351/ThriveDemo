package com.thrive.ui.core;

public enum TestRunMode {
	
    Local("local"),
    Grid("grid");
	
    private String value;

    private TestRunMode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
