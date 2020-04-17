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

public class SearchResults extends AppCompatActivity {

    private static final String TAG = "PostDetailActivity";

    public static final String EXTRA_POST_KEY = "post_key";


    private DatabaseReference mPostReference;
    private DatabaseReference mCommentsReference;
    private ValueEventListener mPostListener;
    private String mPostKey;
    private CommentAdapter mAdapter;


    private RecyclerView mCommentsRecycler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);



        // Get post key from intent
//        mPostKey = getIntent().getStringExtra(EXTRA_POST_KEY);
//        if (mPostKey == null) {
//            throw new IllegalArgumentException("Must pass EXTRA_POST_KEY");
//        }

        // Initialize Database
        mPostReference = FirebaseDatabase.getInstance().getReference()
                .child("NewPost");
        mCommentsReference = FirebaseDatabase.getInstance().getReference()
                .child("NewPost");

        // Initialize Views

        mCommentsRecycler = findViewById(R.id.searchRV);


        mCommentsRecycler.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onStart() {
        super.onStart();

        // Add value event listener to the post
        // [START post_value_event_listener]
//        ValueEventListener postListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // Get Post object and use the values to update the UI
//                Post post = dataSnapshot.getValue(Post.class);
//                // [START_EXCLUDE]
//                mAuthorView.setText(post.author);
//                mTitleView.setText(post.title);
//                mBodyView.setText(post.body);
//                // [END_EXCLUDE]
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post failed, log a message
//                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
//                // [START_EXCLUDE]
//                Toast.makeText(PostDetailActivity.this, "Failed to load post.",
//                        Toast.LENGTH_SHORT).show();
//                // [END_EXCLUDE]
//            }
//        };
//        mPostReference.addValueEventListener(postListener);
        // [END post_value_event_listener]

        // Keep copy of post listener so we can remove it when app stops
        //   mPostListener = postListener;

        // Listen for comments
        mAdapter = new CommentAdapter(this, mCommentsReference);
        mCommentsRecycler.setAdapter(mAdapter);
    }

//    @Override
//    public void onStop() {
//        super.onStop();
//
//        // Remove post value event listener
//        if (mPostListener != null) {
//            mPostReference.removeEventListener(mPostListener);
//        }
//
//        // Clean up comments listener
//        mAdapter.cleanupListener();
//    }

//    @Override
//    public void onClick(View v) {
//        int i = v.getId();
//        if (i == R.id.buttonPostComment) {
//            postComment();
//        }
//    }

//    private void postComment() {
//        final String uid = getUid();
//        FirebaseDatabase.getInstance().getReference().child("users").child(uid)
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        // Get user information
//                        User user = dataSnapshot.getValue(User.class);
//                        String authorName = user.username;
//
//                        // Create new comment object
//                        String commentText = mCommentField.getText().toString();
//                        Comment comment = new Comment(uid, authorName, commentText);
//
//                        // Push the comment, it will appear in the list
//                        mCommentsReference.push().setValue(comment);
//
//                        // Clear the field
//                        mCommentField.setText(null);
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
//    }

    private static class CommentViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTV;
        public TextView costTV;
        public TextView locationTV;

        public CommentViewHolder(View itemView) {
            super(itemView);

            titleTV = itemView.findViewById(R.id.titleTV);
            costTV = itemView.findViewById(R.id.costTV);
            locationTV = itemView.findViewById(R.id.locationTV);
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
            View view = inflater.inflate(R.layout.detailedsearchview, parent, false);
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
                    Intent intent = new Intent(SearchResults.this, detailedinfo.class);
                    intent.putExtra(SearchResults.EXTRA_POST_KEY, postKey);
                    startActivity(intent);
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
