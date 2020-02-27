package com.example.roommatefinder;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class listingsAdapter extends RecyclerView.Adapter<listingsAdapter.ChoiceViewHolder> {

    public static class ChoiceViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout LLviewreference;

        public ChoiceViewHolder(LinearLayout t){
            super(t);
            LLviewreference=t;
        }
    }
    listingsModel choicemodel;
    public listingsAdapter(){
        super();
        choicemodel = listingsModel.getSingleton();
    }

    @Override
    public void onBindViewHolder(@NonNull ChoiceViewHolder holder, int position) {

        TextView choicetv = holder.LLviewreference.findViewById(R.id.lisitingtitleTV);
        choicetv.setText(choicemodel.choiceList.get(position).title);

        TextView durationTV =holder.LLviewreference.findViewById(R.id.locationTV);
        durationTV.setText(choicemodel.choiceList.get(position).location);
        TextView costTV =holder.LLviewreference.findViewById(R.id.costTV);
        costTV.setText(choicemodel.choiceList.get(position).cost);



    }


    @Override
    public int getItemCount() {

        return choicemodel.choiceList.size();
    }


    @NonNull
    @Override
    public ChoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listingview, parent, false);
        ChoiceViewHolder vh = new ChoiceViewHolder(v);
        return vh;
    }

}