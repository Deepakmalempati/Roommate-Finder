package com.example.roommatefinder;

//import java.util.ArrayList;
//
//public class HomervModel {
//    public static class ChoiceInfo{
//        public String title;
//        public String location;
//        public int cost;
//
//
//        public ChoiceInfo(String title, String location,int cost ){
//            this.title=title;
//            this.location = location;
//            this.cost = cost;
//        }
//    }
//
//    public ArrayList<HomervModel.ChoiceInfo> choiceList;
//    private static HomervModel theModel=null;
//
//    private HomervModel(){
//        choiceList=new ArrayList<HomervModel.ChoiceInfo>();
//        loadModel();
//    }
//
//
//    public static HomervModel getSingleton(){
//        if(theModel==null)
//            theModel=new HomervModel();
//        return theModel;
//    }
//
//    private void loadModel(){
//
//        choiceList.add(new HomervModel.ChoiceInfo("Looking for roommate", "Maryville",290));
//        choiceList.add(new HomervModel.ChoiceInfo("Room Available", "Kansas city",290));
//        choiceList.add(new HomervModel.ChoiceInfo("Male Roommate required", "Saint Joseph",290));
//
//
//    }
//
//
//}

import java.io.Serializable;
public class HomervModel implements Serializable {
    private String name;
    private String email;
    private String cost;
    private int images;
    public HomervModel(String name, String email,String cost, int images) {
        this.name = name;
        this.email = email;
        this.cost = cost;
        this.images = images;
    }
    public String getcost() {
        return cost;
    }

    public void setcost(String cost) {
        this.cost = cost;
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

    public int getImages() {
        return images;
    }
    public void setImages(int images) {
        this.images = images;
    }
}
