package com.huskysoft.eduki.data;

public class Lesson {
    private int id;
    private String title;
    private String content;
    
    public String toString() {
        return getTitle();
    }
    
    public int getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getContent() {
    	return content;
    }
}
