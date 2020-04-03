package com.example.roommatefinder;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {


    static ProfileViewModel.Profile profileobj = new ProfileViewModel.Profile("Jyotsna", "Female", "23-Dec-1995", "Maryville", "8019992123");
public static  class Profile{
    private String name;
    private String gender;
    private String dob;
    private String place;
    private String phno;

    public Profile(String name, String gender, String dob, String place, String phno) {
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.place = place;
        this.phno = phno;
    }


    private MutableLiveData<Profile> valueEarned = new MutableLiveData<Profile>();

    // We make the transformation function slightly more interesting
    private LiveData<String> valueString = Transformations.map(valueEarned,
            (value) -> {
                String result = name;
                return result;
            });
    private LiveData<String> dobString = Transformations.map(valueEarned,
            (value) -> {
                String result = dob;
                return result;
            });
    private LiveData<String> genderString = Transformations.map(valueEarned,
            (value) -> {
                String result = gender;
                return result;
            });
    private LiveData<String> placeString = Transformations.map(valueEarned,
            (value) -> {
                String result = place;
                return result;
            });
    private LiveData<String> phnoString = Transformations.map(valueEarned,
            (value) -> {
                String result = phno;
                return result;
            });

    public LiveData<String> getValueString (){
        return valueString;
    }
    public LiveData<String> getdobString (){
        return dobString;
    }
    public LiveData<String> getgenderString (){
        return genderString;
    }
    public LiveData<String> getplaceString (){
        return placeString;
    }
    public LiveData<String> getphnoString (){
        return phnoString;
    }

    public void getname(String name){
        profileobj.name=name;

        valueEarned.setValue(profileobj);
    }

    public void getDob(String dob){
        profileobj.dob=dob;

        valueEarned.setValue(profileobj);
    }
    public void getgender(String gender){
        profileobj.gender="Female";

        valueEarned.setValue(profileobj);
    }

    public void getcity(String city){
        profileobj.place = place;

        valueEarned.setValue(profileobj);
    }
    public void getphno(String phno){
        profileobj.phno=phno;

        valueEarned.setValue(profileobj);
    }


    public void reset(){

        valueEarned.setValue(profileobj);
    }
}



}
