
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
    private String body_markdown;

    /**
     * Constructs a new Lesson based on an ID, title, course id, and body
     * 
     * @param id lesson id
     * @param title lesson title
     * @param course_id course id
     * @param body content of the specific lesson
     */
    public Lesson(int id, String title, int course_id, String body, String bmark) {
        this.id = id;
        this.title = title;
        this.course_id = course_id;
        this.body = body;
        this.body_markdown = bmark;
    }

    /**
     * Constructs a new Lesson based on an ID, title, course id, and body
     * 
     * @param id lesson id
     * @param title lesson title
     * @param course_id course id
     * @param body content of the specific lesson
     */
    public Lesson(int id, String title, int course_id, String body) {
        this(id, title, course_id, body, body);
    }

    /** @return the string representation of this lesson */
    public String toString() {
        if (getTitle().length() > 35) {
            return getTitle().substring(0, 36) + "...";
        }
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
        return title.toUpperCase();
    }

    /** @return the content of the lesson */
    public String getBody() {
        return body;
    }

    /** @return the content of the lesson */
    public String getBodyMarkdown() {
        return body_markdown;
    }
}
