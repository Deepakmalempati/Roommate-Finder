package com.example.roommatefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void signupclick(View view){

            Intent intent = new Intent(this, RoommateInfoActivity.class);
            startActivity(intent);
        Toast toast=Toast.makeText(getApplicationContext(),"Signup Successful. please login!", Toast.LENGTH_LONG);
        toast.show();

    }

}
