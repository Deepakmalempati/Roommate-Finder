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
import android.widget.Button;

public class SearchResults extends AppCompatActivity {



    private SearchResultsAdapter choiceadapter = null;
    private RecyclerView choiceRV= null;
    private GestureDetectorCompat detector = null;
    // We need a gesture listener

    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            View view = choiceRV.findChildViewUnder(e.getX(), e.getY());
            if (view != null) {
                RecyclerView.ViewHolder holder = choiceRV.getChildViewHolder(view);
                if (holder instanceof SearchResultsAdapter.ChoiceViewHolder) {
                    int position = holder.getAdapterPosition();
                    Log.d("click", "clicked on item "+ position);
                    SearchResultsModel model = SearchResultsModel.getSingleton();

                           
                    return true;
                }
            }

            return false;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);




        choiceadapter= new SearchResultsAdapter();
        choiceRV = findViewById(R.id.searchRV);
        choiceRV.setAdapter(choiceadapter);
        RecyclerView.LayoutManager mymanager = new LinearLayoutManager(this);
        choiceRV.setLayoutManager(mymanager);
        // Make a Listener for taps
        detector = new GestureDetectorCompat(this,
                new SearchResults.RecyclerViewOnGestureListener());
// add the listener to the recycler
        choiceRV.addOnItemTouchListener(new
                                                RecyclerView.SimpleOnItemTouchListener(){
                                                    @Override
                                                    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                                                        return detector.onTouchEvent(e);
                                                    }
                                                });
    }

    public void flitersclick(View view){
        Button filtersBTN = findViewById(R.id.filters);
        Intent intent = new Intent(this, Filters.class);
        startActivity(intent);
    }
}
