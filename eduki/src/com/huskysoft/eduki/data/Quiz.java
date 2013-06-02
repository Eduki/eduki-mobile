package com.huskysoft.eduki.data;

/**
 * @author Rafael Vertido Quiz represents a quiz with an id, title, and
 *         course_id of the course it is associated with
 */

public class Quiz {

    private int id;
    private int course_id;
    private String title;

    /**
     * Constructs a new quiz based on an ID, title, and course id
     * 
     * @param id quiz id
     * @param course_id course id
     * @param title quiz title
     */
    public Quiz(int id, int course_id, String title) {
        this.id = id;
        this.course_id = course_id;
        this.title = title;
    }
    
    /** @return the string representation of this quiz */
    public String toString() {
        if(getTitle().length() > 35) {
            return getTitle().substring(0, 36) + "...";
        }
        return getTitle();
    }
    
    /** @return id of the quiz */
    public int getId() {
        return id;
    }
    
    /** @return id of the course associated with this quiz */
    public int getCourseId() {
        return course_id;
    }
    
    /** @return title of the quiz */
    public String getTitle() {
        return title.toUpperCase();
    }
}
