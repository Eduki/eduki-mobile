package com.huskysoft.eduki.data;

public class User {

    private int id;
    private String email;
    private String first_name;
    private String last_name;
    
    public User(int id, String email, String fname, String lname) {
        this.id = id;
        this.email = email;
        this.first_name = fname;
        this.last_name = lname;       
    }
    
    public int getId() {
        return id;
    }
    
    public String getLastName() {
        return last_name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getFirstName() {
        return first_name;
    }
}
