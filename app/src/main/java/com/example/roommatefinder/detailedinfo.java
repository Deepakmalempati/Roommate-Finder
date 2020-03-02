package com.example.roommatefinder;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class detailedinfo extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailedinfo);
    }
    TextView on = (TextView)findViewById(R.id.one);
    TextView tw = (TextView)findViewById(R.id.two);
    TextView thre = (TextView)findViewById(R.id.three);
    TextView fou = (TextView)findViewById(R.id.four);
    TextView fiv = (TextView)findViewById(R.id.five);
    TextView si = (TextView)findViewById(R.id.six);
    String A = on.getText().toString();





}
