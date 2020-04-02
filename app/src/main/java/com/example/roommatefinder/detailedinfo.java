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
        TextView priceTV = findViewById(R.id.priceTV);
        priceTV.setText("Cost per month: "+price);
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
