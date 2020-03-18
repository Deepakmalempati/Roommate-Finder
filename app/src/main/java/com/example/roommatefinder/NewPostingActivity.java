package com.example.roommatefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewPostingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_posting);
    }


    public void postBTNclick(View v){

        EditText housetypeET = findViewById(R.id.housetypeET);
        EditText amenitiesET = findViewById(R.id.amenitiesET);
        EditText priceET = findViewById(R.id.priceET);
        EditText phnoET = findViewById(R.id.phnoET);

        if(housetypeET.getText().toString().isEmpty()){
            housetypeET.setError("Enter a title");
        }
        else if(amenitiesET.getText().toString().isEmpty()){
            amenitiesET.setError("enter amenities");
        }
        else if(priceET.getText().toString().isEmpty()){
            priceET.setError("enter rent");
        }
        else if(phnoET.getText().toString().isEmpty() || phnoET.getText().toString().length()<10){
            phnoET.setError("Phone number incorrect");
        }
        else {
            Intent ini = new Intent(this, HomepageActivity.class);
            startActivity(ini);
            Toast toast = Toast.makeText(getApplicationContext(), "Posted Successfully", Toast.LENGTH_LONG);
            toast.show();
        }
    }
    public void homeBTNclick(View view){
        Intent intent = new Intent(this,HomepageActivity.class);
        startActivity(intent);
    }
    public void profileclick(View view){
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }
}
