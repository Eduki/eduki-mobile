
package com.huskysoft.eduki.data;

/**
 * @author Cody Thomas Enrollment represents the course enrollments
 */

public class Enrollment {
    private int id;
    private int user_id;
    private int course_id;

    /**
     * Constructs a new enrollment
     * 
     * @param id
     * @param userId
     * @param courseId
     */
    public Enrollment(int id, int userId, int courseId) {
        this.id = id;
        this.user_id = userId;
        this.course_id = courseId;
    }

    /**
     * @return The id of the enrollment
     */
    public int getId() {
        return id;
    }

    /**
     * @return The user id of the enrollment
     */
    public int getUserId() {
        return user_id;
    }

    /**
     * @return The course id of the enrollment
     */
    public int getCourseId() {
        return course_id;
    }
}
