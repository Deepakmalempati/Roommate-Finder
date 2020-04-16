package com.example.roommatefinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SearchResults extends AppCompatActivity {
    private DatabaseReference mPostReference;
    public static String value;

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

                    Intent intent = new Intent(SearchResults.this, detailedinfo.class);
                    intent.putExtra("data",model.choiceList.get(position).title);
                    intent.putExtra("data1",model.choiceList.get(position).cost);
                    intent.putExtra("housetypedata",model.choiceList.get(position).Housetype);
                    intent.putExtra("genderdata",model.choiceList.get(position).gender);
                    intent.putExtra("otherinfodata",model.choiceList.get(position).otherinfo);
                    intent.putExtra("amenitiesdata",model.choiceList.get(position).amenities);

                    startActivity(intent);




                    return true;
                }
            }

            return false;
        }
    }

    public static String title;

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
