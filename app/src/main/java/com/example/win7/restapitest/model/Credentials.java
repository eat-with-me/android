package com.example.win7.restapitest.model;


import com.google.gson.annotations.SerializedName;

public class Credentials {

    @SerializedName("user")
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Credentials(String email, String password, String confirmPassword) {
       user = new User(email, password, confirmPassword);
    }

    public String getPassword(){
        return user.getPassword();
    }

    public String getEmail(){
        return user.getEmail();
    }
}



