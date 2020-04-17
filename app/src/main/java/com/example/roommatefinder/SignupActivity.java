package com.example.roommatefinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private String phno;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mydbRef = database.getReference("Users").push();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        // Initialize Firebase Auth
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    }


    public void signupclick(View view){

        EditText nameET = findViewById(R.id.nameETF);
        EditText emailET = findViewById(R.id.emailET);
        EditText phnoET = findViewById(R.id.phnoET);
        EditText passwordET = findViewById(R.id.passwordET);
        EditText repasswordET = findViewById(R.id.repasswordET);
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();
        String name = nameET.getText().toString();
         phno = phnoET.getText().toString();

        if(nameET.getText().toString().isEmpty()){
            nameET.setError("Name is requried");
        }

        else if(emailET.getText().toString().isEmpty()){
            emailET.setError("Email is required");
        }
        else if(phnoET.getText().toString().isEmpty()){
            phnoET.setError("Phone number is required");
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
        else if(phnoET.getText().toString().length()<10){
            phnoET.setError("Enter a valid phone number");
        }
        else if(passwordET.getText().toString().length()<8){
            passwordET.setError("Password must be 8 characters");
        }

        else{

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                onAuthSuccess(task.getResult().getUser());
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("signup log", "createUserWithEmail:success");




                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("signup log", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                    });

//            UserInfo user = new UserInfo(name,email,phno);
//            mydbRef.setValue(user);

        }



//        else {
//            Intent intent = new Intent(this, MainActivity.class);
//
//            intent.putExtra("profiledata",nameET.getText().toString());
//            startActivity(intent);
//
//            Toast toast = Toast.makeText(getApplicationContext(), "Signup Successful. please login!", Toast.LENGTH_LONG);
//            toast.show();
//        }

    }
    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }
    private void onAuthSuccess(FirebaseUser user) {

        String username = usernameFromEmail(user.getEmail());
        // Write new user
        writeNewUser(user.getUid(), username, user.getEmail());

        // Go to MainActivity
        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Signup Successful! Login back",
                Toast.LENGTH_SHORT).show();
        finish();
    }

    private void writeNewUser(String userId, String name, String email) {
        UserInfo user = new UserInfo(email,name,"DD-MM-YY","Place",phno);

        mDatabase.child("Users").child(userId).setValue(user);
    }

}
