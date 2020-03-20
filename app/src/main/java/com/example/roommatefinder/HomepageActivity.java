package com.example.roommatefinder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;


public class HomepageActivity extends FragmentActivity {


    private static final int NUM_PAGES = 5;
    private ViewPager2 viewPager;
    private FragmentStateAdapter pagerAdapter;

//    private HomervAdapter choiceadapter = null;
//    private RecyclerView choiceRV= null;

    String names[] = {"Looking for Roommate","Room for sharing","College Dorms","Friendly Roommate"};
    String emails[] = {"Maryville","Kansas city","Omaha","Kansas City"};
    int images[] = {R.drawable.bedroom2_2,R.drawable.bedroom2_2,R.drawable.bedroom2_2,R.drawable.bedroom2_2,R.drawable.bedroom2_2};
    List<HomervModel> itemsModelList = new ArrayList<>();
    ListView listView;

    CustomAdapter customAdapter;
    private GestureDetectorCompat detector = null;
    // We need a gesture listenr

//    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {
//
//        @Override
//        public boolean onSingleTapConfirmed(MotionEvent e) {
//
//            View view = choiceRV.findChildViewUnder(e.getX(), e.getY());
//            if (view != null) {
//                RecyclerView.ViewHolder holder = choiceRV.getChildViewHolder(view);
//                if (holder instanceof HomervAdapter.ChoiceViewHolder) {
//                    int position = holder.getAdapterPosition();
//                    Log.d("click", "clicked on item "+ position);
//                    HomervModel model = HomervModel.getSingleton();
//
//
//
//
//                    return true;
//                }
//            }
//
//            return false;
//        }
//    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
       // setSupportActionBar(myToolbar);

        //listview search code
        listView = findViewById(R.id.listview);


        for (int i = 0; i < names.length; i++) {

            HomervModel itemsModel = new HomervModel(names[i], emails[i], images[i]);

            itemsModelList.add(itemsModel);

        }

        customAdapter = new CustomAdapter(itemsModelList, this);

        listView.setAdapter(customAdapter);

        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

//        choiceadapter= new HomervAdapter();
//        choiceRV = findViewById(R.id.homeRV);
//        choiceRV.setAdapter(choiceadapter);
//        RecyclerView.LayoutManager mymanager = new LinearLayoutManager(this);
//        choiceRV.setLayoutManager(mymanager);
//        // Make a Listener for taps
//        detector = new GestureDetectorCompat(this,
//                new HomepageActivity.RecyclerViewOnGestureListener());
//// add the listener to the recycler
//        choiceRV.addOnItemTouchListener(new
//                                                RecyclerView.SimpleOnItemTouchListener(){
//                                                    @Override
//                                                    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//                                                        return detector.onTouchEvent(e);
//                                                    }
//                                                });
//
//
//    }
    }





    public void newlistingclick(View view){
        Intent intent = new Intent(this,NewPostingActivity.class);
        startActivity(intent);
    }

    public void profileclick(View view){
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }
    public void historyclick(View view){
        Intent intent = new Intent(this,YourLisitngs.class);
        startActivity(intent);
    }
    public void logoutclick(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void filtersclick(View view){
        Intent intent = new Intent(this,Filters.class);
        startActivity(intent);
    }

    //// code for image slider in homepage

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }
    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            return new ScreenSlidePageFragment();
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }




    // code for list view and search

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

        private List<HomervModel> itemsModelsl;
        private List<HomervModel> itemsModelListFiltered;
        private Context context;

        public CustomAdapter(List<HomervModel> itemsModelsl, Context context) {
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
            View view = getLayoutInflater().inflate(R.layout.homepagerecview,null);


            TextView titleTV = view.findViewById(R.id.titleTV);
            TextView locationTV = view.findViewById(R.id.locationTV);
            TextView costTV = view.findViewById(R.id.costTV);
            ImageView imageView = view.findViewById(R.id.imageView);

            titleTV.setText(itemsModelListFiltered.get(position).getName());
            locationTV.setText(itemsModelListFiltered.get(position).getEmail());
            imageView.setImageResource(images[position]);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("main activity","item clicked");
                    startActivity(new Intent(HomepageActivity.this,detailedinfo.class).putExtra("items",itemsModelListFiltered.get(position)));

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
                        List<HomervModel> resultsModel = new ArrayList<>();
                        String searchStr = constraint.toString().toLowerCase();

                        for(HomervModel itemsModel:itemsModelsl){
                            if(itemsModel.getName().contains(searchStr) || itemsModel.getEmail().contains(searchStr)){
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

                    itemsModelListFiltered = (List<HomervModel>) results.values;
                    notifyDataSetChanged();

                }
            };
            return filter;
        }
    }

}
