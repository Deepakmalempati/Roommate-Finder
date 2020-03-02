package com.example.roommatefinder;

import java.util.ArrayList;

public class HomervModel {
    public static class ChoiceInfo{
        public String title;
        public String location;
        public int cost;


        public ChoiceInfo(String title, String location,int cost ){
            this.title=title;
            this.location = location;
            this.cost = cost;
        }
    }

    public ArrayList<HomervModel.ChoiceInfo> choiceList;
    private static HomervModel theModel=null;

    private HomervModel(){
        choiceList=new ArrayList<HomervModel.ChoiceInfo>();
        loadModel();
    }


    public static HomervModel getSingleton(){
        if(theModel==null)
            theModel=new HomervModel();
        return theModel;
    }

    private void loadModel(){

        choiceList.add(new HomervModel.ChoiceInfo("Looking for roommate", "Maryville",290));
        choiceList.add(new HomervModel.ChoiceInfo("Room Available", "Kansas city",290));
        choiceList.add(new HomervModel.ChoiceInfo("Male Roommate required", "Saint Joseph",290));


    }


}
