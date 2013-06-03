
package com.huskysoft.eduki.data;

/**
 * @author Cody Thomas User represents a user of the app
 */

public class User {

    private int id;
    private String email;
    private String first_name;
    private String last_name;

    /**
     * Constructs a user based on the params
     * 
     * @param id
     * @param email
     * @param fname
     * @param lname
     */
    public User(int id, String email, String fname, String lname) {
        this.id = id;
        this.email = email;
        this.first_name = fname;
        this.last_name = lname;
    }

    /**
     * @return The id of the user
     */
    public int getId() {
        return id;
    }

    /**
     * @return The last name of the user
     */
    public String getLastName() {
        return last_name;
    }

    /**
     * @return The email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return The first name of the user
     */
    public String getFirstName() {
        return first_name;
    }
}
