package com.example.roommatefinder;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import static android.app.Activity.RESULT_OK;
import static com.example.roommatefinder.ProfileViewModel.profileobj;

public class ProfileFragment extends Fragment {

    ImageView imageView;
    private ProfileViewModel profileViewModel;
    Button button;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    private ProfileViewModel mViewModel;
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
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
             name = user.getDisplayName();
            String email = user.getEmail();
            TextView nameTV = v.findViewById(R.id.nameenterTV);
            nameTV.setText(email);

        }

        profileobj.getValueString().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                TextView nameTV = v.findViewById(R.id.nameenterTV);

                nameTV.setText(s);
            }
        });
        profileobj.getdobString().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                TextView dobTV1 = v.findViewById(R.id.dateenterTV);

                dobTV1.setText(s);
            }
        });
        profileobj.getplaceString().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                TextView cityTV = v.findViewById(R.id.cityenterTV);

                cityTV.setText(s);
            }
        });
        profileobj.getphnoString().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                TextView phnoTV = v.findViewById(R.id.phoneenterTV);

                phnoTV.setText(s);
            }
        });

        TextView updateTV = v.findViewById(R.id.updateTV);

        updateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
               startActivityForResult(gallery, PICK_IMAGE);
            }
        });

       // Button editBTN = v.findViewById(R.id.editBTN);


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
