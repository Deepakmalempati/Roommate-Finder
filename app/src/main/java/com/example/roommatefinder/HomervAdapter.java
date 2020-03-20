//package com.example.roommatefinder;
//
//import android.content.Intent;
//import android.net.Uri;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.roommatefinder.R;
//import com.example.roommatefinder.listingsModel;
//
//public class HomervAdapter extends RecyclerView.Adapter<HomervAdapter.ChoiceViewHolder> {
//
//
//    public static class ChoiceViewHolder extends RecyclerView.ViewHolder {
//
//        private LinearLayout LLviewreference;
//
//        public ChoiceViewHolder(LinearLayout t){
//            super(t);
//            LLviewreference=t;
//        }
//    }
//    HomervModel choicemodel;
//    public HomervAdapter(){
//        super();
//        choicemodel = HomervModel.getSingleton();
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull HomervAdapter.ChoiceViewHolder holder, int position) {
//
//        TextView choicetv = holder.LLviewreference.findViewById(R.id.titleTV);
//        choicetv.setText(choicemodel.choiceList.get(position).title);
//
//        TextView durationTV =holder.LLviewreference.findViewById(R.id.locationTV);
//        durationTV.setText(choicemodel.choiceList.get(position).location);
////        TextView costTV =holder.LLviewreference.findViewById(R.id.costTV);
////        costTV.setText(choicemodel.choiceList.get(position).cost);
//
////       ImageView img = holder.LLviewreference.findViewById(R.id.imageView);
////       img.setImageIcon();
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), detailedinfo.class);
//                view.getContext().startActivity(intent);
//            }
//        });
//
//
//
//    }
//
//
//    @Override
//    public int getItemCount() {
//
//        return choicemodel.choiceList.size();
//    }
//
//
//    @NonNull
//    @Override
//    public HomervAdapter.ChoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.homepagerecview, parent, false);
//        HomervAdapter.ChoiceViewHolder vh = new HomervAdapter.ChoiceViewHolder(v);
//        return vh;
//    }
//
//}