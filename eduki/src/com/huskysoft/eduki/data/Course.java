
package com.huskysoft.eduki.data;

import java.util.Locale;

/**
 * @author Cody Thomas Course represents a course with an id and a title
 */

public class Course {
    private int id;
    private String title;
    private String description;

    /**
     * Constructs a new course based on a course id and a course title
     * 
     * @param id course id
     * @param title	course title
     */
    public Course(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
    
    /** @return The string representation of the course */
    public String toString() {
        if(getTitle().length() > 35) {
            return getTitle().substring(0, 36) + "...";
        }
        return getTitle();
    }

    /** @return The id of the course */
    public int getId() {
        return id;
    }

    /** @return The title of the course */
    public String getTitle() {
        return title.toUpperCase(Locale.getDefault());
    }
    
    public String getDescription() {
        return description;
    }
}
