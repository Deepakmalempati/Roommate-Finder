package com.example.roommatefinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity implements EditProfileFragment.Callback{



    private ProfileFragment profilefragment;
    private EditProfileFragment editprofilefragment;

    @Override
    public void swapfragment(){

        profilefragment = new ProfileFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.containerFL, profilefragment, "profileFR");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (savedInstanceState != null) {
            FragmentManager fm = getSupportFragmentManager();
            profilefragment = (ProfileFragment) fm.findFragmentByTag("profileFR");
            editprofilefragment = (EditProfileFragment) fm.findFragmentByTag("editprofileFR");
            return;
        }

        profilefragment = new ProfileFragment();
        editprofilefragment = new EditProfileFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.containerFL, profilefragment, "profileFR");
        transaction.commit();

        Button editBTN = findViewById(R.id.editBTN);
        editBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profilefragment = new ProfileFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.containerFL, editprofilefragment, "editprofileFR");
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }

    public void profileclick(View view){
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }
    public void homeBTNclick(View view){
        Intent intent = new Intent(this,HomepageActivity.class);
        startActivity(intent);
    }



}
