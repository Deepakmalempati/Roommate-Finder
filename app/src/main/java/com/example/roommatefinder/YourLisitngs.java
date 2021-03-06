package com.example.roommatefinder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
//import com.google.android.material.button.MaterialButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.quickstart.database.R;
//import com.google.firebase.quickstart.database.java.models.Comment;
//import com.google.firebase.quickstart.database.java.models.Post;
//import com.google.firebase.quickstart.database.java.models.User;

import java.util.ArrayList;
import java.util.List;

public class YourLisitngs extends AppCompatActivity {

    private static final String TAG = "YourListingsActivity";




    private DatabaseReference mPostReference;
    private DatabaseReference mCommentsReference;
    private ValueEventListener mPostListener;
    private String mPostKey;
    private CommentAdapter mAdapter;
    private CommentAdapter mAdapter1;


    private RecyclerView mCommentsRecycler;

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_lisitngs);



        // Get post key from intent
//        mPostKey = getIntent().getStringExtra(EXTRA_POST_KEY);
//        if (mPostKey == null) {
//            throw new IllegalArgumentException("Must pass EXTRA_POST_KEY");
//        }

        // Initialize Database
        mPostReference = FirebaseDatabase.getInstance().getReference()
                .child("New-Posts");
        mCommentsReference = FirebaseDatabase.getInstance().getReference()
                .child("User-Post").child(getUid());

        // Initialize Views

        mCommentsRecycler = findViewById(R.id.listingsRV);


        mCommentsRecycler.setLayoutManager(new LinearLayoutManager(this));

    }

    public void HomeBTNclick(View view){
        Intent intent = new Intent(YourLisitngs.this,HomepageActivity.class);
        startActivity(intent);
    }

    public void profileclick(View view){
        Intent intent = new Intent(YourLisitngs.this,ProfileActivity.class);
        startActivity(intent);
    }



    @Override
    public void onStart() {
        super.onStart();



        // Listen for comments

        mAdapter1 = new CommentAdapter(this, mCommentsReference);
        mCommentsRecycler.setAdapter(mAdapter1);

    }



    private static class CommentViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTV;
        public TextView costTV;
        public TextView locationTV;

        public CommentViewHolder(View itemView) {
            super(itemView);

            titleTV = itemView.findViewById(R.id.lisitingtitleTV);
            costTV = itemView.findViewById(R.id.costTV);
            locationTV = itemView.findViewById(R.id.titleTV);
        }
    }

    private class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {

        private Context mContext;
        private DatabaseReference mDatabaseReference;
        private ChildEventListener mChildEventListener;

        private List<String> mCommentIds = new ArrayList<>();
        private List<SearchResultsModel.ChoiceInfo> mComments = new ArrayList<>();

        public CommentAdapter(final Context context, DatabaseReference ref) {
            mContext = context;
            mDatabaseReference = ref;

            // Create child event listener
            // [START child_event_listener_recycler]
            ChildEventListener childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                    Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());

                    // A new comment has been added, add it to the displayed list
                    SearchResultsModel.ChoiceInfo comment = dataSnapshot.getValue(SearchResultsModel.ChoiceInfo.class);

                    // [START_EXCLUDE]
                    // Update RecyclerView
                    mCommentIds.add(dataSnapshot.getKey());

                    mComments.add(comment);
                    notifyItemInserted(mComments.size() - 1);
                    // [END_EXCLUDE]
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                    Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());

                    // A comment has changed, use the key to determine if we are displaying this
                    // comment and if so displayed the changed comment.
                    SearchResultsModel.ChoiceInfo newComment = dataSnapshot.getValue(SearchResultsModel.ChoiceInfo.class);
                    String commentKey = dataSnapshot.getKey();

                    // [START_EXCLUDE]
                    int commentIndex = mCommentIds.indexOf(commentKey);
                    if (commentIndex > -1) {
                        // Replace with the new data
                        mComments.set(commentIndex, newComment);

                        // Update the RecyclerView
                        notifyItemChanged(commentIndex);
                    } else {
                        Log.w(TAG, "onChildChanged:unknown_child:" + commentKey);
                    }
                    // [END_EXCLUDE]
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());

                    // A comment has changed, use the key to determine if we are displaying this
                    // comment and if so remove it.
                    String commentKey = dataSnapshot.getKey();

                    // [START_EXCLUDE]
                    int commentIndex = mCommentIds.indexOf(commentKey);
                    if (commentIndex > -1) {
                        // Remove data from the list
                        mCommentIds.remove(commentIndex);
                        mComments.remove(commentIndex);

                        // Update the RecyclerView
                        notifyItemRemoved(commentIndex);
                    } else {
                        Log.w(TAG, "onChildRemoved:unknown_child:" + commentKey);
                    }
                    // [END_EXCLUDE]
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                    Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());

                    // A comment has changed position, use the key to determine if we are
                    // displaying this comment and if so move it.
                    SearchResultsModel.ChoiceInfo movedComment = dataSnapshot.getValue(SearchResultsModel.ChoiceInfo.class);
                    String commentKey = dataSnapshot.getKey();

                    // ...
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.w(TAG, "postComments:onCancelled", databaseError.toException());
                    Toast.makeText(mContext, "Failed to load comments.",
                            Toast.LENGTH_SHORT).show();
                }
            };
            ref.addChildEventListener(childEventListener);
            // [END child_event_listener_recycler]

            // Store reference to listener so it can be removed on app stop
            mChildEventListener = childEventListener;
        }

        @Override
        public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            View view = inflater.inflate(R.layout.listingview, parent, false);
            return new CommentViewHolder(view);
        }


        @Override
        public void onBindViewHolder(CommentViewHolder holder, int position) {
            // final DatabaseReference postRef = ;
            SearchResultsModel.ChoiceInfo comment = mComments.get(position);
            holder.titleTV.setText(comment.title);
            holder.costTV.setText("$"+comment.cost);
            holder.locationTV.setText(comment.location);


            // final String postKey = postRef.getKey();
            final String postKey = mCommentIds.get(position);
            //final String postKey = mCommentsReference.getRef().getKey();
            Log.d("Key log value","Key log value: "+postKey);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCommentsReference.child(postKey).removeValue();
                    mPostReference.child(postKey).removeValue();
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mComments.size();
        }

        public void cleanupListener() {
            if (mChildEventListener != null) {
                mDatabaseReference.removeEventListener(mChildEventListener);
            }
        }

    }
}
