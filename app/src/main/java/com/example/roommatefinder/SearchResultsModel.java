package com.example.roommatefinder;

import java.util.ArrayList;

public class SearchResultsModel {
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

//        choiceList.add(new ChoiceInfo("Looking for roommate", "Maryville","$290"));
//
//        choiceList.add(new ChoiceInfo("Friendly roommate", "Kansas city","$290"));
//
//
        choiceList.add(new ChoiceInfo("Female roommate", "Maryville","$290"));
        choiceList.add(new ChoiceInfo("Male roommate", "Maryville","$290"));
        choiceList.add(new ChoiceInfo("Male roommate111", "Maryville","$290"));




    }

    public void reset(){
        choiceList.clear();
        loadModel();
    }
}