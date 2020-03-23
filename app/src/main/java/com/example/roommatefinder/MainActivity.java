package com.example.roommatefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Onclick(View view){

      //  Intent intent = new Intent(this,HomepageActivity.class);
       // startActivity(intent);

        Intent intent = new Intent(this,SignupActivity.class);
        startActivity(intent);

    }

    public void loginclick(View view){

        EditText emailET = findViewById(R.id.emailET);
        EditText passwordET = findViewById(R.id.passwordET);

        if(emailET.getText().toString().equals("") || passwordET.getText().toString().equals("")){
            emailET.setError("enter email");
            passwordET.setError("Enter password");
        }
        else if(emailET.getText().toString().equals("q") && passwordET.getText().toString().equals("q")){
            Intent intent = new Intent(this,HomepageActivity.class);
            startActivity(intent);
        }
        else {
            Toast toast=Toast.makeText(getApplicationContext(),"invalid email and password", Toast.LENGTH_LONG);
            toast.show();
        }

    }


}
