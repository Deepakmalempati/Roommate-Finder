package com.example.roommatefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NewPostingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_posting);
    }


    public void postBTNclick(View v){
        Button postBTN = findViewById(R.id.postBTN);
        Intent ini= new Intent(this,HomepageActivity.class);
        startActivity(ini);
        Toast toast=Toast.makeText(getApplicationContext(),"Posted Successfully", Toast.LENGTH_LONG);
        toast.show();
    }
}
