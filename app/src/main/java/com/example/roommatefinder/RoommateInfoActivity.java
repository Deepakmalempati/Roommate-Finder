package com.example.roommatefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RoommateInfoActivity extends AppCompatActivity {

    private DatabaseReference mdbReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roommate_info);

        mdbReference = FirebaseDatabase.getInstance().getReference()
                .child("Users-Data").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ValueEventListener postListener1 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI

                UserInfo userobj = dataSnapshot.getValue(UserInfo.class);

                TextView nameTVR = findViewById(R.id.nameTVR);
                TextView emailTVR = findViewById(R.id.emailTVR);
                TextView placeTVR = findViewById(R.id.placeTVR);


                nameTVR.setText(userobj.getName());
                emailTVR.setText(userobj.getEmail());
                placeTVR.setText(userobj.getPlace());
                // [END_EXCLUDE]

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("log message error", "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
//                Toast.makeText(PostDetailActivity.this, "Failed to load post.",
//                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        };
        mdbReference.addValueEventListener(postListener1);
    }

    public void Homeclick(View v){
        Intent intent = new Intent(this,HomepageActivity.class);
        startActivity(intent);
    }
    public void Profileclick(View v){
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }
}
