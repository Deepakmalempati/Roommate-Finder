package com.example.roommatefinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class YourLisitngs extends AppCompatActivity {

    private ChoiceAdapter choiceadapter = null;
    private RecyclerView choiceRV= null;
    private GestureDetectorCompat detector = null;
    // We need a gesture listener

    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = choiceRV.findChildViewUnder(e.getX(), e.getY());
            if (view != null) {
                RecyclerView.ViewHolder holder = choiceRV.getChildViewHolder(view);
                if (holder instanceof ChoiceAdapter.ChoiceViewHolder) {
                    int position = holder.getAdapterPosition();
                    Log.d("click", "clicked on item "+ position);
                    ChoiceModel model = ChoiceModel.getSingleton();


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
        setContentView(R.layout.activity_main);


        choiceadapter= new ChoiceAdapter();
        choiceRV = findViewById(R.id.choiceRV);
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_lisitngs);

            choiceadapter= new ChoiceAdapter();
            choiceRV = findViewById(R.id.choiceRV);
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
}
