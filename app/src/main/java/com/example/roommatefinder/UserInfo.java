package com.example.roommatefinder;

public class UserInfo {
    private String name;
    private String email;
    private String phno;

    public UserInfo(String name, String email, String phno) {
        this.name = name;
        this.email = email;
        this.phno = phno;
    }

    public UserInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }
}
