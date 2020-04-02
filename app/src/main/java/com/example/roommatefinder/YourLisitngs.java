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

public class YourLisitngs extends AppCompatActivity {

    private listingsAdapter choiceadapter = null;
    private RecyclerView choiceRV= null;
    private GestureDetectorCompat detector = null;
    // We need a gesture listener

    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = choiceRV.findChildViewUnder(e.getX(), e.getY());
            if (view != null) {
                RecyclerView.ViewHolder holder = choiceRV.getChildViewHolder(view);
                if (holder instanceof listingsAdapter.ChoiceViewHolder) {
                    int position = holder.getAdapterPosition();
                    Log.d("click", "clicked on item "+ position);
                    listingsModel model = listingsModel.getSingleton();


                    model.choiceList.remove(position);

                    choiceadapter.notifyItemRemoved(position);
                    return true;
                }
            }

            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_lisitngs);

            choiceadapter= new listingsAdapter();
            choiceRV = findViewById(R.id.listingsRV);
            choiceRV.setAdapter(choiceadapter);
            RecyclerView.LayoutManager mymanager = new LinearLayoutManager(this);
            choiceRV.setLayoutManager(mymanager);
            // Make a Listener for taps
            detector = new GestureDetectorCompat(this,
                    new RecyclerViewOnGestureListener());
// add the listener to the recycler
            choiceRV.addOnItemTouchListener(new
                                                    RecyclerView.SimpleOnItemTouchListener(){
                                                        @Override
                                                        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                                                            return detector.onTouchEvent(e);
                                                        }
                                                    });
    }

    public void HomeBTNclick(View view){
        Intent intent = new Intent(this,HomepageActivity.class);
        startActivity(intent);
    }

    public void profileclick(View view){
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }

}
