package com.example.roommatefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class detailedinfo extends AppCompatActivity {

    private DatabaseReference mPostReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailedinfo);
        mPostReference = FirebaseDatabase.getInstance().getReference("NewPost");

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                SearchResultsModel.ChoiceInfo post = dataSnapshot.getValue(SearchResultsModel.ChoiceInfo.class);
                Log.d("cancelled log", "value fetched" + post.title);
                TextView priceTV = findViewById(R.id.priceTV);
                TextView housetypeTV = findViewById(R.id.housetypeTV);
                TextView genderTV = findViewById(R.id.genderTV);
                TextView otherinfoTV = findViewById(R.id.otherinfoTV);
                TextView amenitiesTV = findViewById(R.id.amenitiesTV);
                TextView titleTV = findViewById(R.id.titleTV);

                titleTV.setText(post.title);
                priceTV.setText("Cost per month: $"+post.cost);
                housetypeTV.setText("House Type: "+post.Housetype);
                genderTV.setText("Preffered: "+post.gender);
                otherinfoTV.setText("Other Info: "+post.otherinfo);
                amenitiesTV.setText("Amenities included: "+post.amenities);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("cancelled log", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        mPostReference.addValueEventListener(postListener);

        Intent intent = getIntent();
        String title = intent.getStringExtra("data");
        TextView titleTV = findViewById(R.id.titleTV);
        titleTV.setText(title);
        String price = intent.getStringExtra("data1");
        String housetype = intent.getStringExtra("housetypedata");
        String gender = intent.getStringExtra("genderdata");
        String otherinfo = intent.getStringExtra("otherinfodata");
        String amenities = intent.getStringExtra("amenitiesdata");

        TextView priceTV = findViewById(R.id.priceTV);
        TextView housetypeTV = findViewById(R.id.housetypeTV);
        TextView genderTV = findViewById(R.id.genderTV);
        TextView otherinfoTV = findViewById(R.id.otherinfoTV);
        TextView amenitiesTV = findViewById(R.id.amenitiesTV);

        priceTV.setText("Cost per month: "+price);
        housetypeTV.setText("House Type: "+housetype);
        genderTV.setText("Preffered: "+gender);
        otherinfoTV.setText("Other Info: "+otherinfo);
        amenitiesTV.setText("Amenities included: "+amenities);

      //  TextView amenitiesTV = findViewById(R.id.amenitiesTV);
      //  amenitiesTV.setText(intent.getStringExtra("amenitiesdata"));



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
