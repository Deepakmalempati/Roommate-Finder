package com.example.roommatefinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class ProfileActivity extends AppCompatActivity {


    private DatabaseReference mdbReference;
    ImageView imageView;
    Uri imageUri;
    FirebaseUser user;
    private static final int PICK_IMAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        imageView = findViewById(R.id.imageView);
         user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Log.d("image","User image"+user.getDisplayName());

            Uri photoUrl = user.getPhotoUrl();
            Glide.with(this).load(photoUrl).into(imageView);
            imageView.setBackground(getDrawable(R.color.white));
            //imageView.setImageURI(photoUrl);
            Log.d("image","User image"+photoUrl);
        } else {
            Log.d("user log","No user signed in");
        }


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
                phoneTV.setText(userobj.getPhno());


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

    public void updateBTNclick(View view){
        EditText nameTV = findViewById(R.id.nameenterTV);
        EditText emailTV = findViewById(R.id.emailenterTV);
        EditText genderTV = findViewById(R.id.genderenterTV);
        EditText dobTV = findViewById(R.id.dateenterTV);
        EditText cityTV = findViewById(R.id.cityenterTV);
        EditText phoneTV = findViewById(R.id.phoneenterTV);
        String name = nameTV.getText().toString();
        String email = emailTV.getText().toString();
        String gender = genderTV.getText().toString();
        String dob = dobTV.getText().toString();
        String city = cityTV.getText().toString();
        String phno = phoneTV.getText().toString();
        UserInfo userobj = new UserInfo(name,email,name,dob,city,phno);
        mdbReference.setValue(userobj);
        Toast toast = Toast.makeText(getApplicationContext(),"Profile Updated!", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent(ProfileActivity.this,HomepageActivity.class);
        startActivity(intent);
    }


            public void UpdateImageClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
               startActivityForResult(gallery, PICK_IMAGE);
            }

        @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){



            imageUri = data.getData();
            imageView.setImageURI(imageUri);
            updateProfile(imageUri);
            imageView.setBackground(getDrawable(R.color.white));

        }
    }

    public void updateProfile( Uri image) {
        // [START update_profile]



        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName("Deepak")
                .setPhotoUri(image)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("user pic", "User profile updated."+image);

                        }
                    }
                });
        // [END update_profile]
    }

}
