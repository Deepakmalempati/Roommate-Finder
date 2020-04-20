package com.example.roommatefinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.app.Service.START_REDELIVER_INTENT;


public class NewPostingActivity extends AppCompatActivity {

    public static final String ACTION_UPLOAD = "action_upload";
    public static final String UPLOAD_COMPLETED = "upload_completed";
    public static final String UPLOAD_ERROR = "upload_error";
    private static final String TAG = "MyUploadService";

    /** Intent Extras **/
    public static final String EXTRA_FILE_URI = "extra_file_uri";
    public static final String EXTRA_DOWNLOAD_URL = "extra_download_url";
   FirebaseDatabase database = FirebaseDatabase.getInstance();
   DatabaseReference mydbRef = database.getReference();
    private StorageReference mStorageRef;
//    DatabaseReference myRefplace = database.getReference("/New Post/Location of post");
//    DatabaseReference myRefprice = database.getReference("/New Post/Cost of post");


    private static final int Image_Capture_Code = 1;
    private ImageView imgCapture;

    private  String title;
    private  String place;
    private  String price;
    private String amenities;
    private String housetype;
    private String otherinfo;
    private String gender;

//    public static String gettitle() {
//        return title;
//    }
//    public static String getplace() {
//        return place;
//    }
//    public static String getprice() {
//        return price;
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_posting);

        mStorageRef = FirebaseStorage.getInstance().getReference();
    }



    public void postBTNclick(View v){

        EditText housetypeET = findViewById(R.id.housetypeET);
        EditText amenitiesET = findViewById(R.id.amenitiesET);
        EditText priceET = findViewById(R.id.priceET);
        EditText phnoET = findViewById(R.id.phnoET);
        EditText otherinfoET = findViewById(R.id.otherinfoET);
        CheckBox genderCB = findViewById(R.id.genderCB);

        if(housetypeET.getText().toString().isEmpty()){
            housetypeET.setError("Enter a title");
        }
        else if(amenitiesET.getText().toString().isEmpty()){
            amenitiesET.setError("Enter amenities");
        }
        else if(priceET.getText().toString().isEmpty()){
            priceET.setError("Enter rent");
        }
        else if(phnoET.getText().toString().isEmpty() || phnoET.getText().toString().length()<10){
            phnoET.setError("Phone number incorrect");
        }
        else {
            EditText titleET = findViewById(R.id.housetypeET);
            title = titleET.getText().toString();
          //  myReftitle.setValue(title);
            EditText placeET = findViewById(R.id.cityET);
            place = placeET.getText().toString();
          //  myRefplace.setValue(place);
            price = priceET.getText().toString();
            amenities = amenitiesET.getText().toString();
            housetype = housetypeET.getText().toString();
            otherinfo = otherinfoET.getText().toString();
            gender = genderCB.getText().toString();

         //   myRefprice.setValue(price);
            Intent ini = new Intent(this, HomepageActivity.class);



            final String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            mydbRef.child("Users").child(userId).addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // Get user value
                            UserInfo user = dataSnapshot.getValue(UserInfo.class);

                            // [START_EXCLUDE]
                            if (user == null) {
                                // User is null, error out
                                Log.d("Error log", "User " + userId + " is unexpectedly null");
                                Toast.makeText(NewPostingActivity.this,
                                        "Error: could not fetch user.",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                // Write new post
                                Log.d("User ID log", "User ID is = " + userId);
                               // writeNewPost(userId, user.username, title, body);
                                String key = mydbRef.child("User-Post").push().getKey();
                                Log.d("Key","Key value: "+key);
                                SearchResultsModel.ChoiceInfo ci = new SearchResultsModel.ChoiceInfo(title,place,price,housetype,gender,otherinfo,amenities);
                                mydbRef.child("New-Posts").push().setValue(ci);
                                Map<String, Object> postValues = ci.toMap();

                                Map<String, Object> childUpdates = new HashMap<>();
                                //childUpdates.put("/NewPost/" + key, postValues);
                               childUpdates.put("/User-Post/" + userId + "/" + key, postValues);

                                mydbRef.updateChildren(childUpdates);

                            }

                            // Finish this Activity, back to the stream
                           // setEditingEnabled(true);
                            finish();
                            // [END_EXCLUDE]
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.w("Cancelled log", "getUser:onCancelled", databaseError.toException());
                            // [START_EXCLUDE]
                           // setEditingEnabled(true);
                            // [END_EXCLUDE]
                        }
                    });
            // [END single_value_read]

           // String key = mydbRef.child("posts").push().getKey();
            startActivity(ini);
            Toast toast = Toast.makeText(getApplicationContext(), "Posted Successfully", Toast.LENGTH_LONG);
            toast.show();


        }
    }
    public void homeBTNclick(View view){
        Intent intent = new Intent(this,HomepageActivity.class);
        startActivity(intent);
    }
    public void profileclick(View view){
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }

   public void uploadimage(View view){

   }


}

