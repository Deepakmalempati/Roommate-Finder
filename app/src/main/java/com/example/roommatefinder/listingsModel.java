package com.example.roommatefinder;

import java.util.ArrayList;

public class listingsModel {
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

    public ArrayList<ChoiceInfo> choiceList;
    private static listingsModel theModel=null;

    private listingsModel(){
        choiceList=new ArrayList<ChoiceInfo>();
        loadModel();
    }


    public static listingsModel getSingleton(){
        if(theModel==null)
            theModel=new listingsModel();
        return theModel;
    }

    private void loadModel(){

        choiceList.add(new ChoiceInfo("Looking for roommate", "Maryville",290));
        choiceList.add(new ChoiceInfo("Female roommate", "Maryville",290));
        choiceList.add(new ChoiceInfo("Male roommate", "Maryville",290));


    }

    public void reset(){
        choiceList.clear();
        loadModel();
    }
}
