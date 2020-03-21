package com.example.roommatefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    EditText nameenterTV;
    EditText genderenterTV;
    EditText dateenterTV;
    EditText cityenterTV;
    EditText phoneenterTV;

    ImageView imageView;
    Button button;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        imageView = (ImageView)findViewById(R.id.imageView);
        nameenterTV = (EditText) findViewById(R.id.nameenterTV);
        genderenterTV = (EditText) findViewById(R.id.genderenterTV);
        dateenterTV = (EditText) findViewById(R.id.dateenterTV);
        cityenterTV = (EditText) findViewById(R.id.cityenterTV);
        phoneenterTV = (EditText) findViewById(R.id.phoneenterTV);

    }

    public void profileclick(View view){
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }
    public void homeBTNclick(View view){
        Intent intent = new Intent(this,HomepageActivity.class);
        startActivity(intent);
    }

    public void profilepic(View view){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    public void edit(View view){

        genderenterTV.setFocusableInTouchMode(true);
        genderenterTV.setCursorVisible(true);
        genderenterTV.requestFocus();
        dateenterTV.setFocusableInTouchMode(true);
        dateenterTV.setCursorVisible(true);
        dateenterTV.requestFocus();
        cityenterTV.setFocusableInTouchMode(true);
        cityenterTV.setCursorVisible(true);
        cityenterTV.requestFocus();
        phoneenterTV.setFocusableInTouchMode(true);
        phoneenterTV.setCursorVisible(true);
        phoneenterTV.requestFocus();
        nameenterTV.setFocusableInTouchMode(true);
        nameenterTV.setCursorVisible(true);
        nameenterTV.requestFocus();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
            imageView.setBackground(getDrawable(R.color.white));
        }
    }
}
