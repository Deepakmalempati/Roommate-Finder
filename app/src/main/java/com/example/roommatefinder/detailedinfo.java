package com.example.roommatefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class detailedinfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailedinfo);
    }

    public void profilenameclick(View v){
        Intent intent = new Intent(this,RoommateInfoActivity.class);
        startActivity(intent);
    }

    public void Homeclick(View v){
        Intent intent = new Intent(this,HomepageActivity.class);
        startActivity(intent);
    }
}
