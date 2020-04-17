package com.example.roommatefinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class ProfileActivity extends AppCompatActivity {


    private DatabaseReference mdbReference;


//    private ProfileFragment profilefragment;
//    private EditProfileFragment editprofilefragment;
//  //  private ProfileViewModel model;
//    DatabaseReference userdbref;
//
//    @Override
//    public void swaptoprofilefragment(){
//
//
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.containerFL, editprofilefragment, "editprofileFR");
//        transaction.addToBackStack(null);
//        transaction.commit();



//        ValueEventListener postListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                UserInfo post = dataSnapshot.getValue(UserInfo.class);
//                TextView emailTV = findViewById(R.id.emailenterTV);
//                TextView nameenterTV = findViewById(R.id.emailenterTV);
//                TextView genderenterTV = findViewById(R.id.genderenterTV);
//                TextView dateenterTV = findViewById(R.id.dateenterTV);
//                TextView cityenterTV = findViewById(R.id.cityenterTV);
//                TextView phnoenterTV = findViewById(R.id.phoneenterTV);
//                emailTV.setText(post.getEmail());
//                nameenterTV.setText(post.getName());
//                dateenterTV.setText(post.getDob());
//                cityenterTV.setText(post.getPlace());
//                phnoenterTV.setText(post.getPhno());
//                // [END_EXCLUDE]
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post failed, log a message
//                Log.w("log message error", "loadPost:onCancelled", databaseError.toException());
//                // [START_EXCLUDE]
////                Toast.makeText(PostDetailActivity.this, "Failed to load post.",
////                        Toast.LENGTH_SHORT).show();
//                // [END_EXCLUDE]
//            }
//        };
//        userdbref.addValueEventListener(postListener);

 //   }

  //  @Override
//    public void SwapToEditProfileFragment(){
//
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.containerFL, profilefragment, "profileFR");
//        transaction.addToBackStack(null);
//        transaction.commit();
//
//        Toast toast = Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT);
//        toast.show();

//        EditText emailETF = findViewById(R.id.emailETF);
//                EditText nameETF = findViewById(R.id.nameET);
//                EditText dobETF = findViewById(R.id.dobET);
//                EditText placeETF = findViewById(R.id.cityETF);
//                EditText phnoETF = findViewById(R.id.phnoETF);
//                String email = emailETF.getText().toString();
//                String name=nameETF.getText().toString();
//                String dob = dobETF.getText().toString();
//                String place = placeETF.getText().toString();
//                String phno = phnoETF.getText().toString();
//        UserInfo userobj = new UserInfo(email,name,dob,place,phno);
//        userdbref.setValue(userobj);


//        DatabaseReference Users = FirebaseDatabase.getInstance().getReference()
//                .child("Users");
//
//
//        ValueEventListener postListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // Get Post object and use the values to update the UI
//                UserInfo userinfoobj = dataSnapshot.getValue(UserInfo.class);
//                TextView nameTV = findViewById(R.id.emailenterTV);
//                TextView genderTV = findViewById(R.id.genderenterTV);
//                TextView dobTV = findViewById(R.id.dateenterTV);
//                TextView cityTV = findViewById(R.id.cityenterTV);
//                TextView phoneTV = findViewById(R.id.phoneenterTV);
//
//
//                nameTV.setText(userinfoobj.getName());
//                phoneTV.setText(userinfoobj.getPhno());
//
//                // [END_EXCLUDE]
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post failed, log a message
//                Log.w("log message error", "loadPost:onCancelled", databaseError.toException());
//                // [START_EXCLUDE]
////                Toast.makeText(PostDetailActivity.this, "Failed to load post.",
////                        Toast.LENGTH_SHORT).show();
//                // [END_EXCLUDE]
//            }
//        };
//        Users.addValueEventListener(postListener);
  //  }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mdbReference = FirebaseDatabase.getInstance().getReference()
                .child("Users-Data").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        DatabaseReference Users = FirebaseDatabase.getInstance().getReference()
                .child("Users");


        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                UserInfo userobj = dataSnapshot.getValue(UserInfo.class);
                EditText nameTV = findViewById(R.id.nameenterTV);
                EditText emailTV = findViewById(R.id.emailenterTV);
                EditText genderTV = findViewById(R.id.genderenterTV);
                EditText dobTV = findViewById(R.id.dateenterTV);
                EditText cityTV = findViewById(R.id.cityenterTV);
                EditText phoneTV = findViewById(R.id.phoneenterTV);


                nameTV.setText(userobj.getName());
                emailTV.setText(userobj.getEmail());
                genderTV.setText("Male");
                dobTV.setText(userobj.getDob());
                cityTV.setText(userobj.getPlace());


                // [END_EXCLUDE]
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("log message error", "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
//                Toast.makeText(PostDetailActivity.this, "Failed to load post.",
//                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        };

        mdbReference.addValueEventListener(postListener);

//        userdbref = FirebaseDatabase.getInstance().getReference().child("Users").push();
//
//        ViewModelProvider.Factory vmf = new ViewModelProvider.NewInstanceFactory();
//        ViewModelProvider vmp = new ViewModelProvider(this, vmf);
//     //   model = vmp.get(ProfileViewModel.class);
//        Log.d("Model", "mainViewModel is ");
//
//        if (savedInstanceState != null) {
//            FragmentManager fm = getSupportFragmentManager();
//            profilefragment = (ProfileFragment) fm.findFragmentByTag("profileFR");
//            editprofilefragment = (EditProfileFragment) fm.findFragmentByTag("editprofileFR");
//            return;
//        }
//      //  profileobj.reset();
//
//        profilefragment = new ProfileFragment();
//        editprofilefragment = new EditProfileFragment();
//
//        profilefragment = new ProfileFragment();
//        editprofilefragment = new EditProfileFragment();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.add(R.id.containerFL, profilefragment, "profileFR");
//        transaction.commit();
//
//
//
    }


    public void profileclick(View view){
       // (EditProfileFragment.Callback)getActivity().
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }
    public void homeBTNclick(View view){
        Intent intent = new Intent(this,HomepageActivity.class);
        startActivity(intent);
    }
}
