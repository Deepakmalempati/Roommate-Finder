package com.example.roommatefinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class HomepageActivity extends AppCompatActivity {

    private HomervAdapter choiceadapter = null;
    private RecyclerView choiceRV= null;
    private GestureDetectorCompat detector = null;
    // We need a gesture listener

    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = choiceRV.findChildViewUnder(e.getX(), e.getY());
            if (view != null) {
                RecyclerView.ViewHolder holder = choiceRV.getChildViewHolder(view);
                if (holder instanceof HomervAdapter.ChoiceViewHolder) {
                    int position = holder.getAdapterPosition();
                    Log.d("click", "clicked on item "+ position);
                    HomervModel model = HomervModel.getSingleton();



                    return true;
                }
            }

            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        choiceadapter= new HomervAdapter();
        choiceRV = findViewById(R.id.homeRV);
        choiceRV.setAdapter(choiceadapter);
        RecyclerView.LayoutManager mymanager = new LinearLayoutManager(this);
        choiceRV.setLayoutManager(mymanager);
        // Make a Listener for taps
        detector = new GestureDetectorCompat(this,
                new HomepageActivity.RecyclerViewOnGestureListener());
// add the listener to the recycler
        choiceRV.addOnItemTouchListener(new
                                                RecyclerView.SimpleOnItemTouchListener(){
                                                    @Override
                                                    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                                                        return detector.onTouchEvent(e);
                                                    }
                                                });
    }

    public void newlistingclick(View view){
        Intent intent = new Intent(this,NewPostingActivity.class);
        startActivity(intent);
    }

    public void profileclick(View view){
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }
    public void historyclick(View view){
        Intent intent = new Intent(this,YourLisitngs.class);
        startActivity(intent);
    }
    public void logoutclick(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
