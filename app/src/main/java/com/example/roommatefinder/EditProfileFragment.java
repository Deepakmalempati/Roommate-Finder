package com.example.roommatefinder;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.roommatefinder.ProfileViewModel.profileobj;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditProfileFragment extends Fragment {


        DatabaseReference userdbref;
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
        EditText valueTV = v.findViewById(R.id.nameET);
        EditText dobTV = v.findViewById(R.id.dobET);
        EditText placeETF = v.findViewById(R.id.cityETF);
        EditText phnoET = v.findViewById(R.id.phnoETF);

        userdbref = FirebaseDatabase.getInstance().getReference().child("Users");





//        profileobj.getValueString().observe(getViewLifecycleOwner(), new Observer<String>() {
//                    @Override
//                    public void onChanged(String s) {
//
//
//                        valueTV.setText(s);
//
//                    }
//                });
//
//        profileobj.getdobString().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//
//
//
//                dobTV.setText(s);
//            }
//        });
//
//        profileobj.getplaceString().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//
//
//
//                placeETF.setText(s);
//            }
//        });
//        profileobj.getphnoString().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//
//
//
//                phnoET.setText(s);
//            }
//        });

        Button updateBTN = v.findViewById(R.id.updateBTN);
        updateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EditText emailETF = v.findViewById(R.id.emailETF);
//                EditText nameETF = v.findViewById(R.id.nameET);
//                EditText dobETF = v.findViewById(R.id.dobET);
//                EditText placeETF = v.findViewById(R.id.cityETF);
//                EditText phnoETF = v.findViewById(R.id.phnoETF);
//                String email = emailETF.getText().toString();
//                String name=nameETF.getText().toString();
//                String dob = dobETF.getText().toString();
//                String place = placeETF.getText().toString();
//                String phno = phnoETF.getText().toString();


                 myActivity.SwapToEditProfileFragment();



            }
        });




        return v;
    }

}
