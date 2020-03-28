package com.example.roommatefinder;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditProfileFragment extends Fragment {

private ProfileFragment profilefragment;
    private Callback myActivity;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    public interface Callback{
        public void swapfragment();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myActivity = (Callback) context;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_edit_profile, container, false);




        Button updateBTN = v.findViewById(R.id.updateBTN);
        updateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    myActivity.swapfragment();
            }
        });
        return v;
    }

}
