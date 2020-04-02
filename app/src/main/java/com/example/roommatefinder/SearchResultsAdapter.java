package com.example.roommatefinder;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import afu.org.checkerframework.checker.nullness.qual.NonNull;

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.ChoiceViewHolder> {

    public static class ChoiceViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout LLviewreference;

        public ChoiceViewHolder(LinearLayout t){
            super(t);
            LLviewreference=t;
        }
    }
    SearchResultsModel choicemodel;
    public SearchResultsAdapter(){
        super();
        choicemodel = SearchResultsModel.getSingleton();
    }

    @Override
    public void onBindViewHolder(@NonNull ChoiceViewHolder holder, int position) {

        TextView choicetv = holder.LLviewreference.findViewById(R.id.titleTV);
        choicetv.setText(choicemodel.choiceList.get(position).title);

        TextView durationTV =holder.LLviewreference.findViewById(R.id.costTV);
        durationTV.setText(choicemodel.choiceList.get(position).cost);
        TextView costTV =holder.LLviewreference.findViewById(R.id.locationTV);
        costTV.setText(choicemodel.choiceList.get(position).location);



    }


    @Override
    public int getItemCount() {

        return choicemodel.choiceList.size();
    }


    @NonNull
    @Override
    public ChoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detailedsearchview, parent, false);
        ChoiceViewHolder vh = new ChoiceViewHolder(v);
        return vh;
    }
}
