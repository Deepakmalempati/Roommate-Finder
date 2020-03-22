package com.example.roommatefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class Filters extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);
    }
    public void select(View view)
    {
        boolean checked = ((CheckBox) view).isChecked();

    }

    public void applyfiltersclick(View view){

        Intent intent = new Intent(this, HomepageActivity.class);
        startActivity(intent);
        Toast bread=Toast.makeText(getApplicationContext(),"Filters Applied Successfully!", Toast.LENGTH_LONG);
        bread.show();

    }
}
