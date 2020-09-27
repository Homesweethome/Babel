package com.library.babel;

public class Element {
    private String title;
    private String level;

    public Element(String title, String level) {
        this.title = title;
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public String getLevel() {
        return level;
    }
}
