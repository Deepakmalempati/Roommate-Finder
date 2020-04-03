package com.example.roommatefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class detailedinfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailedinfo);

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
        startActivity(intent);
    }
}
