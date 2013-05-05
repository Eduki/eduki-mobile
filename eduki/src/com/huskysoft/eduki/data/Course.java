
package com.huskysoft.eduki.data;

/**
 * @author Cody Thomas Course represents a course with an id and a title
 */

public class Course {
    private int id;
    private String title;

    /**
     * Constructs a new course based on a course id and a course title
     * 
     * @param id course id
     * @param title	course title
     */
    public Course(int id, String title) {
        this.id = id;
        this.title = title;
    }
    
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
