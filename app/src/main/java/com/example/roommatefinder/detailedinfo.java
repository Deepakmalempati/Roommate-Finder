package com.example.roommatefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.roommatefinder.SearchResults.EXTRA_POST_KEY;

public class detailedinfo extends AppCompatActivity {

    private DatabaseReference mPostReference;
    private DatabaseReference mCommentsReference;
    private DatabaseReference mdbReference;

    private String mPostKey;
    public static final String EXTRA_POST_KEY = "post_key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailedinfo);

        mPostKey = getIntent().getStringExtra(EXTRA_POST_KEY);
        if (mPostKey == null) {
            throw new IllegalArgumentException("Must pass EXTRA_POST_KEY");
        }
        mPostReference = FirebaseDatabase.getInstance().getReference()
                .child("New-Posts").child(mPostKey);
        mCommentsReference = FirebaseDatabase.getInstance().getReference()
                .child("New-Posts").child(mPostKey);




//        mPostReference = FirebaseDatabase.getInstance().getReference("NewPost");
//
//        ValueEventListener postListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // Get Post object and use the values to update the UI
//                SearchResultsModel.ChoiceInfo post = dataSnapshot.getValue(SearchResultsModel.ChoiceInfo.class);
//                Log.d("cancelled log", "value fetched" + post.title);
//                TextView priceTV = findViewById(R.id.priceTV);
//                TextView housetypeTV = findViewById(R.id.housetypeTV);
//                TextView genderTV = findViewById(R.id.genderTV);
//                TextView otherinfoTV = findViewById(R.id.otherinfoTV);
//                TextView amenitiesTV = findViewById(R.id.amenitiesTV);
//                TextView titleTV = findViewById(R.id.titleTV);
//
//                titleTV.setText(post.title);
//                priceTV.setText("Cost per month: $"+post.cost);
//                housetypeTV.setText("House Type: "+post.Housetype);
//                genderTV.setText("Preffered: "+post.gender);
//                otherinfoTV.setText("Other Info: "+post.otherinfo);
//                amenitiesTV.setText("Amenities included: "+post.amenities);
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post failed, log a message
//                Log.w("cancelled log", "loadPost:onCancelled", databaseError.toException());
//                // ...
//            }
//        };
//        mPostReference.addValueEventListener(postListener);
//
//        Intent intent = getIntent();
//        String title = intent.getStringExtra("data");
//        TextView titleTV = findViewById(R.id.titleTV);
//        titleTV.setText(title);
//        String price = intent.getStringExtra("data1");
//        String housetype = intent.getStringExtra("housetypedata");
//        String gender = intent.getStringExtra("genderdata");
//        String otherinfo = intent.getStringExtra("otherinfodata");
//        String amenities = intent.getStringExtra("amenitiesdata");
//
//        TextView priceTV = findViewById(R.id.priceTV);
//        TextView housetypeTV = findViewById(R.id.housetypeTV);
//        TextView genderTV = findViewById(R.id.genderTV);
//        TextView otherinfoTV = findViewById(R.id.otherinfoTV);
//        TextView amenitiesTV = findViewById(R.id.amenitiesTV);
//
//        priceTV.setText("Cost per month: "+price);
//        housetypeTV.setText("House Type: "+housetype);
//        genderTV.setText("Preffered: "+gender);
//        otherinfoTV.setText("Other Info: "+otherinfo);
//        amenitiesTV.setText("Amenities included: "+amenities);

      //  TextView amenitiesTV = findViewById(R.id.amenitiesTV);
      //  amenitiesTV.setText(intent.getStringExtra("amenitiesdata"));



    }

    @Override
    public void onStart() {
        super.onStart();

        // Add value event listener to the post
        // [START post_value_event_listener]

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                SearchResultsModel.ChoiceInfo post = dataSnapshot.getValue(SearchResultsModel.ChoiceInfo.class);
                UserInfo userobj = dataSnapshot.getValue(UserInfo.class);
                TextView priceTV = findViewById(R.id.priceTV);
                TextView housetypeTV = findViewById(R.id.housetypeTV);
                TextView genderTV = findViewById(R.id.genderTV);
                TextView otherinfoTV = findViewById(R.id.otherinfoTV);
                TextView amenitiesTV = findViewById(R.id.amenitiesTV);
                TextView titleTV = findViewById(R.id.titleTV);
                TextView postbyTV = findViewById(R.id.postedbyTV);

                titleTV.setText(post.title);
                priceTV.setText("Cost per month: $"+post.cost);
                housetypeTV.setText("House Type: "+post.Housetype);
                genderTV.setText("Preffered: "+post.gender);
                otherinfoTV.setText("Other Info: "+post.otherinfo);
                amenitiesTV.setText("Amenities included: "+post.amenities);
                postbyTV.setText("Posted by: "+userobj.getName());
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
        mPostReference.addValueEventListener(postListener);
        // [END post_value_event_listener]

        mdbReference = FirebaseDatabase.getInstance().getReference()
                .child("Users-Data").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ValueEventListener postListener1 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI

                UserInfo userobj = dataSnapshot.getValue(UserInfo.class);

                TextView postbyTV = findViewById(R.id.postedbyTV);


                postbyTV.setText("Posted by: "+userobj.getName());
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
  // intents for the activity
    public void profilenameclick(View v){
        Intent intent = new Intent(this,RoommateInfoActivity.class);
        startActivity(intent);
    }

    public void Homeclick(View v){
        Intent intent = new Intent(this,HomepageActivity.class);
        startActivity(intent);
    }

    public void contactHost(View v){
        String e="90909090";
        Uri uri = Uri.parse("tel:"+e);
        Intent intent = new Intent(Intent.ACTION_DIAL,uri);
        startActivity(intent);  }
}
