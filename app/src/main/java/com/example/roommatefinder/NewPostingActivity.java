package com.example.roommatefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;


public class NewPostingActivity extends AppCompatActivity {

    private static final int Image_Capture_Code = 1;
    private ImageView imgCapture;

    private  String title;
    private  String place;
    private  String price;
//    public static String gettitle() {
//        return title;
//    }
//    public static String getplace() {
//        return place;
//    }
//    public static String getprice() {
//        return price;
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_posting);
    }

    public static ArrayList<String> listdatatitle =new  ArrayList<>();
    public static ArrayList<String> listdataplace =new  ArrayList<>();
    public static ArrayList<String> listdataprice =new  ArrayList<>();
    public static ArrayList<String> listdataamenities =new  ArrayList<>();
    public static ArrayList<String> listdatahousetype =new  ArrayList<>();
    public static ArrayList<String> listdatagender =new  ArrayList<>();
    public static ArrayList<String> listdataotherinfo =new  ArrayList<>();

    public void postBTNclick(View v){

        EditText housetypeET = findViewById(R.id.housetypeET);
        EditText amenitiesET = findViewById(R.id.amenitiesET);
        EditText priceET = findViewById(R.id.priceET);
        EditText phnoET = findViewById(R.id.phnoET);
        EditText otherinfoET = findViewById(R.id.otherinfoET);
        CheckBox genderCB = findViewById(R.id.genderCB);

        if(housetypeET.getText().toString().isEmpty()){
            housetypeET.setError("Enter a title");
        }
        else if(amenitiesET.getText().toString().isEmpty()){
            amenitiesET.setError("Enter amenities");
        }
        else if(priceET.getText().toString().isEmpty()){
            priceET.setError("Enter rent");
        }
        else if(phnoET.getText().toString().isEmpty() || phnoET.getText().toString().length()<10){
            phnoET.setError("Phone number incorrect");
        }
        else {
            EditText titleET = findViewById(R.id.housetypeET);
            title = titleET.getText().toString();
            EditText placeET = findViewById(R.id.cityET);
            place = placeET.getText().toString();

            price = priceET.getText().toString();
            Intent ini = new Intent(this, HomepageActivity.class);


         /*   listdatatitle.add(title);
            listdataplace.add(place);
            listdataprice.add(price);
            listdataamenities.add(amenitiesET.getText().toString());
            listdatahousetype.add(housetypeET.getText().toString());
            listdatagender.add(genderCB.getText().toString());
            listdataotherinfo.add(otherinfoET.getText().toString()); */




            startActivity(ini);
            Toast toast = Toast.makeText(getApplicationContext(), "Posted Successfully", Toast.LENGTH_LONG);
            toast.show();


        }
    }
    public void homeBTNclick(View view){
        Intent intent = new Intent(this,HomepageActivity.class);
        startActivity(intent);
    }
    public void profileclick(View view){
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }

   public void uploadimage(View view){
       Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
       startActivityForResult(cInt,Image_Capture_Code);
   }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Image_Capture_Code) {
            if (resultCode == RESULT_OK) {
                Bitmap bp = (Bitmap) data.getExtras().get("data");
                imgCapture.setImageBitmap(bp);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            }
        }
    }
}

