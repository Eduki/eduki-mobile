package com.huskysoft.eduki.data;

public class Quiz {

    private int id;
    private String title;

    public Quiz(int id, String title) {
        this.id = id;
        this.title = title;
    }
    
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
