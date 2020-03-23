package com.example.roommatefinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SearchResults extends AppCompatActivity {

    String names[] = {"Looking for Roommate","Room for sharing","College Dorms","Friendly Roommate"};
    String emails[] = {"Maryville","Kansas city","Omaha","Kansas City"};
    String cost[] = {"$250","$500","$300","$150"};
    int images[] = {R.drawable.bedroom2_2,R.drawable.bedroom2_2,R.drawable.bedroom2_2,R.drawable.bedroom2_2,};
    List<DetailedSearchModel> itemsModelList = new ArrayList<>();
    ListView listView;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);


        listView = findViewById(R.id.listview);


        for(int i = 0;i<names.length;i++){
//            names[names.length-1]=NewPostingActivity.gettitle();
//            emails[emails.length-1]=NewPostingActivity.getplace();
//            cost[cost.length-1]=NewPostingActivity.getprice();
            DetailedSearchModel itemsModel = new DetailedSearchModel(names[i],emails[i],cost[i],images[i]);

            itemsModelList.add(itemsModel);

        }

        customAdapter = new CustomAdapter(itemsModelList,this);

        listView.setAdapter(customAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu,menu);

        MenuItem menuItem = menu.findItem(R.id.searchView);

        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                Log.e("Main"," data search"+newText);

                customAdapter.getFilter().filter(newText);



                return true;
            }
        });


        return true;

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();


        if(id == R.id.searchView){

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public class CustomAdapter extends BaseAdapter implements Filterable {

        private List<DetailedSearchModel> itemsModelsl;
        private List<DetailedSearchModel> itemsModelListFiltered;
        private Context context;

        public CustomAdapter(List<DetailedSearchModel> itemsModelsl, Context context) {
            this.itemsModelsl = itemsModelsl;
            this.itemsModelListFiltered = itemsModelsl;
            this.context = context;
        }

        @Override
        public int getCount() {
            return itemsModelListFiltered.size();
        }

        @Override
        public Object getItem(int position) {
            return itemsModelListFiltered.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.detailedsearchview,null);


            TextView titleTV = view.findViewById(R.id.titleTV);
            TextView locationTV = view.findViewById(R.id.locationTV);
            TextView costTV = view.findViewById(R.id.costTV);
            ImageView imageView = view.findViewById(R.id.imageView);

            titleTV.setText(itemsModelListFiltered.get(position).getName());
            locationTV.setText(itemsModelListFiltered.get(position).getEmail());
            costTV.setText(itemsModelListFiltered.get(position).getcost());
            imageView.setImageResource(images[position]);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("main activity","item clicked");
                    startActivity(new Intent(SearchResults.this,detailedinfo.class).putExtra("items",itemsModelListFiltered.get(position)));

                }
            });

            return view;
        }



        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {

                    FilterResults filterResults = new FilterResults();
                    if(constraint == null || constraint.length() == 0){
                        filterResults.count = itemsModelsl.size();
                        filterResults.values = itemsModelsl;

                    }else{
                        List<DetailedSearchModel> resultsModel = new ArrayList<>();
                        String searchStr = constraint.toString().toLowerCase();

                        for(DetailedSearchModel itemsModel:itemsModelsl){
                            if(itemsModel.getName().contains(searchStr)){
                                resultsModel.add(itemsModel);
                                filterResults.count = resultsModel.size();
                                filterResults.values = resultsModel;
                            }
                        }


                    }

                    return filterResults;
                }
                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {

                    itemsModelListFiltered = (List<DetailedSearchModel>) results.values;
                    notifyDataSetChanged();

                }
            };
            return filter;
        }
    }



    public void flitersclick(View view){
        Button filtersBTN = findViewById(R.id.filters);
        Intent intent = new Intent(this, Filters.class);
        startActivity(intent);
    }
}
