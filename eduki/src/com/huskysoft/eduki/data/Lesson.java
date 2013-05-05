
package com.huskysoft.eduki.data;

/**
 * @author Rafael Vertido Lesson represents a lesson with an id, title, and
 *         body, and also its corresponding course's id
 */

public class Lesson {
    private int id;
    private int course_id;
    private String title;
    private String body;

    /**
     * Constructs a new Lesson based on an ID, title, course id, and body
     * 
     * @param id lesson id
     * @param title lesson title
     * @param course_id course id
     * @param body content of the specific lesson
     */
    public Lesson(int id, String title, int course_id, String body) {
        this.id = id;
        this.title = title;
        this.course_id = course_id;
        this.body = body;
    }

    /** @return the string representation of this lesson */
    public String toString() {
        return getTitle();
    }

    /** @return the id of the lesson */
    public int getId() {
        return id;
    }

    /** @return the id of the course this lesson is associated with */
    public int getCourseId() {
        return course_id;
    }

    /** @return the title of the lesson */
    public String getTitle() {
        return title;
    }

    /** @return the content of the lesson */
    public String getBody() {
        return body;
    }
}
