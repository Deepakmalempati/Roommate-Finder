//package com.example.roommatefinder;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.annotation.Nullable;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.firebase.database.ChildEventListener;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import afu.org.checkerframework.checker.nullness.qual.NonNull;
//
//public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.ChoiceViewHolder> {
//    private DatabaseReference mPostReference;
//    private ChildEventListener mChildEventListener;
//
//
//
//    public static class ChoiceViewHolder extends RecyclerView.ViewHolder {
//        private LinearLayout LLviewreference;
//        public ChoiceViewHolder(LinearLayout t){
//            super(t);
//            LLviewreference=t;
//        }
//    }
//    SearchResultsModel choicemodel;
//    public SearchResultsAdapter(){
//        super();
//        choicemodel = SearchResultsModel.getSingleton();
//        choicemodel.reset();
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ChoiceViewHolder holder, int position) {
//
//        mPostReference = FirebaseDatabase.getInstance().getReference("NewPost");
//
//        ValueEventListener postListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // Get Post object and use the values to update the UI
//                SearchResultsModel.ChoiceInfo post = dataSnapshot.getValue(SearchResultsModel.ChoiceInfo.class);
//                Log.d("cancelled log", "value fetched" + post.title);
//
//              //  Log.d("value in variable","Value: "+value);
//                // choicemodel.loadModel();
//                TextView choicetv = holder.LLviewreference.findViewById(R.id.titleTV);
//                choicetv.setText(post.title);
//                TextView durationTV =holder.LLviewreference.findViewById(R.id.costTV);
//                durationTV.setText(post.cost);
//                TextView costTV =holder.LLviewreference.findViewById(R.id.locationTV);
//                costTV.setText(post.location);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post failed, log a message
//                Log.w("cancelled log", "loadPost:onCancelled", databaseError.toException());
//                // ...
//            }
//        };
//        mPostReference.addValueEventListener(postListener);
//
//
//
//            TextView choicetv = holder.LLviewreference.findViewById(R.id.titleTV);
//                choicetv.setText(choicemodel.choiceList.get(position).title);
//                TextView durationTV =holder.LLviewreference.findViewById(R.id.costTV);
//                durationTV.setText(choicemodel.choiceList.get(position).cost);
//                TextView costTV =holder.LLviewreference.findViewById(R.id.locationTV);
//                costTV.setText(choicemodel.choiceList.get(position).location);
//               // choicetv.setText(SearchResults.title);
//
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return choicemodel.choiceList.size();
//    }
//
//
//    @NonNull
//    @Override
//    public ChoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.detailedsearchview, parent, false);
//        ChoiceViewHolder vh = new ChoiceViewHolder(v);
//
//        return vh;
//    }
//}
