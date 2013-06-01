package com.huskysoft.eduki.data;

public class Enrollment {
    private int id;
    private int userId;
    private int courseId;
    
    public Enrollment(int id, int userId, int courseId) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
    }
    
    public int getId() {
        return id;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public int getCourseId() {
        return courseId;
    }
}
