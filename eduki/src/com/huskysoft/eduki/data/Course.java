package com.huskysoft.eduki.data;

/**
 * 
 * @author Cody Thomas
 * Course represents a course with an id and a title
 *
 */

public class Course {
    private int id;
    private String title;
    
    /** @return The string representation of the course */
    public String toString() {
        return getTitle();
    }
    
    /** @return The id of the course */
    public int getId() {
        return id;
    }
    /** @return The title of the course */
    public String getTitle() {
        return title;
    }
}
