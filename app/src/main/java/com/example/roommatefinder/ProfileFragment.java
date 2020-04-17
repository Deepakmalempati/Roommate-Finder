package com.example.roommatefinder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {

    ImageView imageView;
    //private ProfileViewModel profileViewModel;
    Button button;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
  //  private ProfileViewModel mViewModel;
    ProfileCallbackInterface myinterface;

public interface ProfileCallbackInterface{
    public void swaptoprofilefragment();
}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        myinterface = (ProfileFragment.ProfileCallbackInterface) context;

    }


    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }
    private FirebaseAuth mAuth;
     String name;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile_fragment, container, false);
        mAuth = FirebaseAuth.getInstance();
        TextView emailTV = v.findViewById(R.id.emailenterTV);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url

            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            emailTV.setText(email);

        }




        DatabaseReference Users = FirebaseDatabase.getInstance().getReference()
                .child("Users");


//        ValueEventListener postListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // Get Post object and use the values to update the UI
//                UserInfo userinfoobj = dataSnapshot.getValue(UserInfo.class);
//                TextView nameTV = v.findViewById(R.id.emailenterTV);
//                TextView genderTV = v.findViewById(R.id.genderenterTV);
//                TextView dobTV = v.findViewById(R.id.dateenterTV);
//                TextView cityTV = v.findViewById(R.id.cityenterTV);
//                TextView phoneTV = v.findViewById(R.id.phoneenterTV);
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

//        if (user != null) {
//            // Name, email address, and profile photo Url
//             name = user.;
//            String email = user.getEmail();
//            TextView nameTV = v.findViewById(R.id.nameenterTV);
//            nameTV.setText(email);
//
//        }

//        profileobj.getValueString().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//
//                TextView nameTV = v.findViewById(R.id.nameenterTV);
//
//                nameTV.setText(s);
//            }
//        });
//        profileobj.getdobString().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//
//                TextView dobTV1 = v.findViewById(R.id.dateenterTV);
//
//                dobTV1.setText(s);
//            }
//        });
//        profileobj.getplaceString().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//
//                TextView cityTV = v.findViewById(R.id.cityenterTV);
//
//                cityTV.setText(s);
//            }
//        });
//        profileobj.getphnoString().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//
//                TextView phnoTV = v.findViewById(R.id.phoneenterTV);
//
//                phnoTV.setText(s);
//            }
//        });
//
//        TextView updateTV = v.findViewById(R.id.updateTV);
//
//        updateTV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//               startActivityForResult(gallery, PICK_IMAGE);
//            }
//        });
//
//       // Button editBTN = v.findViewById(R.id.editBTN);
//
//
        Button editBTN = v.findViewById(R.id.editBTN);
        editBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myinterface.swaptoprofilefragment();
            }
        });
       return v;

    }





    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
           // imageView.setBackground(getDrawable(R.color.white));

        }
    }

}
