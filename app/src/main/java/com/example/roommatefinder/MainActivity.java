package com.example.roommatefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Onclick(View view){
<<<<<<< HEAD
      //  Intent intent = new Intent(this,HomepageActivity.class);
       // startActivity(intent);
=======
        Intent intent = new Intent(this,HomepageActivity.class);
        startActivity(intent);
>>>>>>> a0b35ad78b716494791cb06bd5a0a0caa18e4f36
    }
}
