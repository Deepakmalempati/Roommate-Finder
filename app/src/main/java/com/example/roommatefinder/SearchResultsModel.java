package com.example.roommatefinder;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


//import static com.example.roommatefinder.SearchResults.value;

public class SearchResultsModel {


//    private DatabaseReference mPostReference;
//    static String x;

 public static class ChoiceInfo{
        public String title;
        public String location;
        public String cost;
        public String Housetype;
        public String gender;
        public String otherinfo;
        public String amenities;



        public ChoiceInfo(){

        }

        public ChoiceInfo(String title, String location,String cost, String Housetype, String gender, String otherinfo, String amenities ){
            this.title=title;
            this.location = location;
            this.cost = cost;
            this.Housetype= Housetype;
            this.gender = gender;
            this.otherinfo = otherinfo;
            this.amenities = amenities;
        }


     @Exclude
     public Map<String, Object> toMap() {
         HashMap<String, Object> result = new HashMap<>();
         result.put("title", title);
         result.put("location", location);
         result.put("cost", cost);
         result.put("Housetype", Housetype);
         result.put("gender", gender);
         result.put("otherinfo", otherinfo);
         result.put("amenities", amenities);

         return result;
     }
    }

//    public ArrayList<ChoiceInfo> choiceList;
//    private static SearchResultsModel theModel=null;
//
//    private SearchResultsModel(){
//        choiceList=new ArrayList<ChoiceInfo>();
//
//        loadModel();
//    }
//
//
//    public static SearchResultsModel getSingleton(){
//        if(theModel==null)
//            theModel=new SearchResultsModel();
//        return theModel;
//    }
//
//    public void loadModel(){
//
//        for(int i=0;i<listdatatitle.size();i++) {
//            ChoiceInfo ci = new ChoiceInfo(listdatatitle.get(i),listdataplace.get(i),listdataprice.get(i),listdatahousetype.get(i),listdatagender.get(i),listdataotherinfo.get(i),listdataamenities.get(i));
//            choiceList.add(ci);
//
//
//
//
//        }
//
////        if(HomepageActivity.placename.equalsIgnoreCase("Maryville")) {
////
////
////        }
//
//
//
//        choiceList.add(new ChoiceInfo("Looking for Roommate", "Maryville", "$290","2 Bedroom","Male","No partis after 8 pm","Electricity, laundry"));
////            choiceList.add(new ChoiceInfo("Room available", "Maryville", "$500","2 Bedroom","Male","No partis after 8 pm","Electricity, laundry"));
////            choiceList.add(new ChoiceInfo("Looking for friendly student", "Maryville", "$450","2 Bedroom","Male","No partis after 8 pm","Electricity, laundry"));
////           // choiceList.add(new ChoiceInfo(SearchResults.value,"Maryville", "$290","2 Bedroom","Male","No partis after 8 pm","Electricity, laundry"));
////
////
////       }
////       if(HomepageActivity.placename.equalsIgnoreCase("Kansas city")){
////            choiceList.add(new ChoiceInfo("Looking for Roommate", "Kansas city","$290","2 Bedroom","Male","No partis after 8 pm","Electricity, laundry"));
////            choiceList.add(new ChoiceInfo("Room available", "Kansas city","$500","2 Bedroom","Male","No partis after 8 pm","Electricity, laundry"));
////            choiceList.add(new ChoiceInfo("Looking for friendly student", "Kansas city","$450","2 Bedroom","Male","No partis after 8 pm","Electricity, laundry"));
////       }
//
//
//
//    }
//
//
//    public void reset(){
//        choiceList.clear();
//        loadModel();
//    }
}