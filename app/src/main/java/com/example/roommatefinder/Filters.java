package com.example.roommatefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class Filters extends AppCompatActivity {
 ArrayList<String> selection = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);
    }
    public void select(View view)
    {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId())
        {
            case R.id.checkBox:
                if(checked)
                selection.add("lowtohigh");
                break;
            case R.id.checkBox2:
                if (checked)
                    selection.add("nearset");
                break;
            case R.id.checkBox3:
                if (checked)
                    selection.add("Male");
                break;
            case R.id.checkBox4:
                if (checked)
                    selection.add("female");
                break;
            case R.id.checkBox5:
                if (checked)
                    selection.add("entire space");
                break;


        }


    }

    public void applyfiltersclick(View view){

        Intent intent = new Intent(this, SearchResults.class);
        startActivity(intent);
        Toast bread=Toast.makeText(getApplicationContext(),"Filters Applied Successfully!", Toast.LENGTH_LONG);
        bread.show();

    }
}
