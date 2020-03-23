package com.example.roommatefinder;

import java.io.Serializable;

public class DetailedSearchModel implements Serializable {
    private String name;
    private String email;
    private String cost;
    private int images;
    public DetailedSearchModel(String name, String email,String cost, int images) {
        this.name = name;
        this.email = email;
        this.cost = cost;
        this.images = images;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getcost() {
        return cost;
    }

    public void setcost(String cost) {
        this.cost = cost;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public int getImages() {
        return images;
    }
    public void setImages(int images) {
        this.images = images;
    }
}