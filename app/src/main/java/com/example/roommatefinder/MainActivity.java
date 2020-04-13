package com.example.roommatefinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
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
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();

        if(emailET.getText().toString().equals("") || passwordET.getText().toString().equals("")){
            emailET.setError("enter email");
            passwordET.setError("Enter password");
        }
        else if(!(email.equals("")&&password.equals(""))){


            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("log message", "signInWithEmail:success");
                                Intent intent = new Intent(MainActivity.this, HomepageActivity.class);
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("log message", "signInWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                    });
        }
        else {
            Toast toast=Toast.makeText(getApplicationContext(),"invalid email and password", Toast.LENGTH_LONG);
            toast.show();
        }


    }


}
