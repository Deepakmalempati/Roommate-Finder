package com.example.roommatefinder;

import android.content.Intent;

import java.util.ArrayList;

import static com.example.roommatefinder.NewPostingActivity.listdatatitle;
import static com.example.roommatefinder.NewPostingActivity.listdataplace;
import static com.example.roommatefinder.NewPostingActivity.listdataprice;
import static com.example.roommatefinder.NewPostingActivity.listdatatitle;

public class SearchResultsModel {



 public static class ChoiceInfo{
        public String title;
        public String location;
        public String cost;
        public String Housetype;
        public String gender;
        public String otherinfo;
        public String amenities;



        public ChoiceInfo(String title, String location,String cost, String Housetype, String gender, String otherinfo, String amenities ){
            this.title=title;
            this.location = location;
            this.cost = cost;
            this.Housetype= Housetype;
            this.gender = gender;
            this.otherinfo = otherinfo;
            this.amenities = amenities;
        }
    }

    public ArrayList<ChoiceInfo> choiceList;
    private static SearchResultsModel theModel=null;

    private SearchResultsModel(){
        choiceList=new ArrayList<ChoiceInfo>();

        loadModel();
    }


    public static SearchResultsModel getSingleton(){
        if(theModel==null)
            theModel=new SearchResultsModel();
        return theModel;
    }

    public void loadModel(){

        for(int i=0;i<listdatatitle.size();i++) {
            choiceList.add(new ChoiceInfo(listdatatitle.get(i),listdataplace.get(i),listdataprice.get(i)));
        }

        if(HomepageActivity.placename.equalsIgnoreCase("Maryville")) {
            choiceList.add(new ChoiceInfo("Looking for Roommate", "Maryville", "$290"));
            choiceList.add(new ChoiceInfo("Room available", "Maryville", "$500"));
            choiceList.add(new ChoiceInfo("Looking for friendly student", "Maryville", "$450"));


        }
        if(HomepageActivity.placename.equalsIgnoreCase("Kansas city")){
            choiceList.add(new ChoiceInfo("Looking for Roommate", "Kansas city","$290"));
            choiceList.add(new ChoiceInfo("Room available", "Kansas city","$500"));
            choiceList.add(new ChoiceInfo("Looking for friendly student", "Kansas city","$450"));
        }
    }


    public void reset(){
        choiceList.clear();
        loadModel();
    }
}