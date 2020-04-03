package com.example.roommatefinder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HomepageActivity extends FragmentActivity {


    private static final int NUM_PAGES = 5;
    private ViewPager2 viewPager;
    private FragmentStateAdapter pagerAdapter;
    public static String placename;

    //for google place autocomplete
    String TAG = "placeautocomplete";
    TextView txtView;


    String names[] = {"Looking for Roommate","Room for sharing","College Dorms","Friendly Roommate"};
    String emails[] = {"Maryville","Kansas city","Omaha","Kansas City"};
    String cost[] = {"$250","$500","$300","$150"};
    int images[] = {R.drawable.dormroom,R.drawable.two_bed,R.drawable.room_sharing,R.drawable.dormroom,R.drawable.dormroom};
    List<HomervModel> itemsModelList = new ArrayList<>();
    ListView listView;

    CustomAdapter customAdapter;
    private GestureDetectorCompat detector = null;
    // We need a gesture listenr




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);


        // for gogole ac

        // Initialize Places.
        String secretValue = getString(R.string.google_places_api_key);
        Places.initialize(getApplicationContext(), secretValue);
        // Create a new Places client instance.
        PlacesClient placesClient = Places.createClient(this);

        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));



        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                //txtView.setText(place.getName());
                placename = place.getName();

                Intent intent = new Intent(HomepageActivity.this,SearchResults.class);
                startActivity(intent);

                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

        //listview search code
        listView = findViewById(R.id.listview);


        for (int i = 0; i < names.length; i++) {

            HomervModel itemsModel = new HomervModel(names[i], emails[i], cost[i], images[i]);

            itemsModelList.add(itemsModel);

        }

       customAdapter = new CustomAdapter(itemsModelList, this);

      listView.setAdapter(customAdapter);

        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);


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




    public class CustomAdapter extends BaseAdapter {

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
            View view = getLayoutInflater().inflate(R.layout.homepagerecview, null);


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
                    Log.e("main activity", "item clicked");
                    startActivity(new Intent(HomepageActivity.this, detailedinfo.class).putExtra("items", itemsModelListFiltered.get(position)));

                }
            });

            return view;
        }


    }
}
