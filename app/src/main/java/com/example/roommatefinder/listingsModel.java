package com.example.roommatefinder;

import java.util.ArrayList;

import static com.example.roommatefinder.NewPostingActivity.listdataplace;
import static com.example.roommatefinder.NewPostingActivity.listdataprice;
import static com.example.roommatefinder.NewPostingActivity.listdatatitle;

public class listingsModel {
    public static class ChoiceInfo{
        public String title;
        public String location;
        public String cost;


        public ChoiceInfo(String title, String location,String cost ){
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

    public void loadModel(){

//        choiceList.add(new ChoiceInfo("Looking for roommate", "Maryville","$290"));
//
//        choiceList.add(new ChoiceInfo("Friendly roommate", "Kansas city","$290"));
//
//
        choiceList.add(new ChoiceInfo("Female roommate", "Maryville","$290"));
        choiceList.add(new ChoiceInfo("Male roommate", "Maryville","$290"));
        choiceList.add(new ChoiceInfo("Male roommate111", "Maryville","$290"));
       // choiceList.add(new ChoiceInfo(NewPostingActivity.gettitle(),NewPostingActivity.getplace(),NewPostingActivity.getprice()));

        for(int i=0;i<listdatatitle.size();i++) {
            choiceList.add(new ChoiceInfo(listdatatitle.get(i),listdataplace.get(i),listdataprice.get(i)));
        }

    }

    public void reset(){
        choiceList.clear();
        loadModel();
    }
}
