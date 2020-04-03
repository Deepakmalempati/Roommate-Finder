package com.example.roommatefinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.icu.text.DisplayContext.LENGTH_SHORT;
import static com.example.roommatefinder.ProfileViewModel.profileobj;

public class ProfileActivity extends AppCompatActivity implements EditProfileFragment.EditCallback, ProfileFragment.ProfileCallbackInterface{



    private ProfileFragment profilefragment;
    private EditProfileFragment editprofilefragment;
    private ProfileViewModel model;

    @Override
    public void swaptoprofilefragment(){


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.containerFL, editprofilefragment, "editprofileFR");
        transaction.addToBackStack(null);
        transaction.commit();
        TextView nameenterTV = findViewById(R.id.nameenterTV);
        TextView genderenterTV = findViewById(R.id.genderenterTV);
        TextView dateenterTV = findViewById(R.id.dateenterTV);
        TextView cityenterTV = findViewById(R.id.cityenterTV);
        TextView phnoenterTV = findViewById(R.id.phoneenterTV);
        profileobj.getname(nameenterTV.getText().toString());
        profileobj.getDob(dateenterTV.getText().toString());
        profileobj.getcity(cityenterTV.getText().toString());
        profileobj.getphno(phnoenterTV.getText().toString());

    }

    @Override
    public void SwapToEditProfileFragment(){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.containerFL, profilefragment, "profileFR");
        transaction.addToBackStack(null);
        transaction.commit();

        Toast toast = Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT);
        toast.show();

        EditText nameETF = findViewById(R.id.nameETF);
        EditText dobET = findViewById(R.id.dobET);
        EditText placeET = findViewById(R.id.cityETF);
        EditText phnoET = findViewById(R.id.phnoETF);
        profileobj.getname(nameETF.getText().toString());
        profileobj.getDob(dobET.getText().toString());
        profileobj.getcity(placeET.getText().toString());
        profileobj.getphno(phnoET.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ViewModelProvider.Factory vmf = new ViewModelProvider.NewInstanceFactory();
        ViewModelProvider vmp = new ViewModelProvider(this, vmf);
        model = vmp.get(ProfileViewModel.class);
        Log.d("Model", "mainViewModel is " + model);

        if (savedInstanceState != null) {
            FragmentManager fm = getSupportFragmentManager();
            profilefragment = (ProfileFragment) fm.findFragmentByTag("profileFR");
            editprofilefragment = (EditProfileFragment) fm.findFragmentByTag("editprofileFR");
            return;
        }
        profileobj.reset();

        profilefragment = new ProfileFragment();
        editprofilefragment = new EditProfileFragment();

        profilefragment = new ProfileFragment();
        editprofilefragment = new EditProfileFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.containerFL, profilefragment, "profileFR");
        transaction.commit();



    }

    public void profileclick(View view){
       // (EditProfileFragment.Callback)getActivity().
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }
    public void homeBTNclick(View view){
        Intent intent = new Intent(this,HomepageActivity.class);
        startActivity(intent);
    }



}
