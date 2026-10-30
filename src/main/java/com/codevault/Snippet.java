package com.codevault;

/**
 * Represents a single code snippet in the CodeVault system.
 */
public class Snippet {
    private int id;
    private String title;
    private String language;
    private String code;

    /**
     * Parameterized constructor to initialize a code snippet.
     *
     * @param id       Unique identifier for the snippet
     * @param title    Title or brief description of the snippet
     * @param language Programming language (e.g., Java, Python, C++)
     * @param code     Source code content
     */
    public Snippet(int id, String title, String language, String code) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
               "Title: " + title + "\n" +
               "Language: " + language + "\n" +
               "Code:\n" + code;
    }
}
