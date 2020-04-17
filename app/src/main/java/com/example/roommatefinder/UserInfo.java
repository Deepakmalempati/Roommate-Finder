package com.example.roommatefinder;

public class UserInfo {
    private String email;
    private String name;
    private String dob;
    private String place;
    private String phno;

    public UserInfo(String email, String name, String dob, String place, String phno) {
        this.email = email;
        this.name = name;
        this.dob = dob;
        this.place = place;
        this.phno = phno;
    }

    public UserInfo() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }
}
