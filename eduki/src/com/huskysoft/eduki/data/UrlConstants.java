
package com.huskysoft.eduki.data;

/**
 * @author Cody Thomas, Rafael Vertido Class UrlConstants maintains a collection
 *         of variables representing appropriate api urls
 */
public final class UrlConstants {
    public static final String BASE = "http://eduki.herokuapp.com/api/";
    public static final String COURSES = BASE + "courses";
    public static final String URL_AUTH = "";
    public static final String LESSONS = BASE + "lessons";
    public static final String QUIZZES = BASE + "quizzes";
    public static final String USER_BASE = BASE + "users";

    /** @return URL for getting all lessons in a course */
    public static String getAllLessonsURL(int course_id) {
        return COURSES + "/" + course_id + "/lessons";
    }

    /** @return URL for a specific single lesson */
    public static String getSingleLessonURL(int lesson_id) {
        return LESSONS + "/" + lesson_id;
    }

    /** @return URL for a specific single course */
    public static String getSingleCourseURL(int course_id) {
        return COURSES + "/" + course_id;
    }

    /** @return URL for all quizzes tied to a course */
    public static String getAllQuizzesURL(int course_id) {
        return COURSES + "/" + course_id + "/quizzes";
    }

    /** @return URL for a single quiz */
    public static String getSingleQuizURL(int quiz_id) {
        return QUIZZES + "/" + quiz_id;
    }

    /** @return URL for a single enrollments
     */
    public static String getEnrollmentsURL(int user_id) {
        return USER_BASE + "/" + user_id + "/enrollments";
    }
    
    /** @return URL for getting all courses of a user */
    public static String getUserCoursesURL(int user_id) {
        return USER_BASE + "/" + user_id + "/courses";
    }
}
