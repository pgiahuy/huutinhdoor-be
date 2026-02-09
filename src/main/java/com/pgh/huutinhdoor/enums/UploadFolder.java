package com.pgh.huutinhdoor.enums;

public enum UploadFolder {
    PRODUCT("products"),
    PROJECT("projects"),
    USER("users"),
    EXPENSE("expenses");

    private final String path;

    UploadFolder(String path) {
        this.path = path;
    }

    public String getPath() {
        return  path;
    }
}
