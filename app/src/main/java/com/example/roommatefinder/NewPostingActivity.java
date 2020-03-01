package com.example.roommatefinder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class NewPostingActivity extends AppCompatActivity {
    View myView;
//
//    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
//        myView = inflater.inflate(R.layout.activity_new_posting,container,false);
//        return myView;
//    }


//    @Override
//    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
//        return super.onCreateView(parent, name, context, attrs);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_posting);
    }
}
