package com.huskysoft.eduki.data;

public class Enrollment {
    private int id;
    private int user_id;
    private int course_id;
    
    public Enrollment(int id, int userId, int courseId) {
        this.id = id;
        this.user_id = userId;
        this.course_id = courseId;
    }
    
    public int getId() {
        return id;
    }
    
    public int getUserId() {
        return user_id;
    }
    
    public int getCourseId() {
        return course_id;
    }
}
