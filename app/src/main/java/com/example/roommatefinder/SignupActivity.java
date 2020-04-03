package com.example.roommatefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void signupclick(View view){

        EditText nameET = findViewById(R.id.nameETF);
        EditText emailET = findViewById(R.id.emailET);
        EditText phnumET = findViewById(R.id.phnoET);
        EditText passwordET = findViewById(R.id.passwordET);
        EditText repasswordET = findViewById(R.id.repasswordET);

        if(nameET.getText().toString().isEmpty()){
            nameET.setError("Name is requried");
        }

        else if(emailET.getText().toString().isEmpty()){
            emailET.setError("Email is required");
        }
        else if(phnumET.getText().toString().isEmpty()){
            phnumET.setError("Phone number is required");
        }
        else if(passwordET.getText().toString().isEmpty()){
            passwordET.setError("enter a password");
        }
        else if(repasswordET.getText().toString().isEmpty()){
            repasswordET.setError("confirm password");
        }

        else if(!(passwordET.getText().toString().equals(repasswordET.getText().toString()))){
            passwordET.setError("Password does not match");
            repasswordET.setError("Password does not match");
        }
        else if(!(emailET.getText().toString().contains("@"))){
            emailET.setError("Enter a valid email");
        }
        else if(phnumET.getText().toString().length()<10){
            phnumET.setError("Enter a valid phone number");
        }
        else if(passwordET.getText().toString().length()<8){
            passwordET.setError("Password must be 8 characters");
        }



        else {
            Intent intent = new Intent(this, MainActivity.class);

            intent.putExtra("profiledata",nameET.getText().toString());
            startActivity(intent);

            Toast toast = Toast.makeText(getApplicationContext(), "Signup Successful. please login!", Toast.LENGTH_LONG);
            toast.show();
        }

    }

}
