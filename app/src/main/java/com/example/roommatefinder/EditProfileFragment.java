package com.example.roommatefinder;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.roommatefinder.ProfileViewModel.profileobj;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditProfileFragment extends Fragment {



    private EditCallback myActivity;
    private ProfileViewModel mainViewModel ;


private ProfileFragment profilefragment;
   // private EditCallback myActivity;


    public EditProfileFragment() {
        // Required empty public constructor
    }

    public interface EditCallback{
        public void SwapToEditProfileFragment();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        myActivity = (EditCallback) context;
        ProfileActivity mainActivity = (ProfileActivity) context;
        ViewModelProvider.Factory vmf = new ViewModelProvider.NewInstanceFactory();
        ViewModelProvider vmp = new ViewModelProvider(mainActivity, vmf);
        mainViewModel = vmp.get(ProfileViewModel.class);

        myActivity = (EditCallback) context;



    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_edit_profile, container, false);

        profileobj.getValueString().observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        EditText valueTV = v.findViewById(R.id.nameETF);

                        valueTV.setText(s);

                    }
                });

        profileobj.getdobString().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                EditText dobTV = v.findViewById(R.id.dobET);

                dobTV.setText(s);
            }
        });

        profileobj.getplaceString().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                EditText placeETF = v.findViewById(R.id.cityETF);

                placeETF.setText(s);
            }
        });
        profileobj.getphnoString().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                EditText phnoET = v.findViewById(R.id.phnoETF);

                phnoET.setText(s);
            }
        });

        Button updateBTN = v.findViewById(R.id.updateBTN);
        updateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    myActivity.SwapToEditProfileFragment();
            }
        });




        return v;
    }

}
