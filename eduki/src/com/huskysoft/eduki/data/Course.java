package com.huskysoft.eduki.data;

public class Course {
    private int id;
    private String title;
    
    public String toString() {
        return getTitle();
    }
    
    public int getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
}
