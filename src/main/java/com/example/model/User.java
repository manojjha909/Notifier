package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by manojjha on 7/29/17.
 */
@Document
public class User {

//    @Id
//    private int id;

    @Id
    private String userName;
    private String password;

    public User(){
    }

    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
}
