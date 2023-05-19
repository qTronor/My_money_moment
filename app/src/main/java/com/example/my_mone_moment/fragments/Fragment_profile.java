package com.example.my_mone_moment.fragments;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.my_mone_moment.MainActivity;
import com.example.my_mone_moment.R;

import com.example.my_mone_moment.ui.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class Fragment_profile extends Fragment {

    private FirebaseAuth firebaseAuth;

    private static final int PICK_IMAGE = 100;
    private Uri imageUri;

    private ImageView profilePicture;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        Button logOutBtn = view.findViewById(R.id.logout_btn);
        profilePicture = (ImageView) view.findViewById(R.id.profile_picture);
        TextView emailTv = (TextView) view.findViewById(R.id.email_tv);

        emailTv.setText(firebaseAuth.getCurrentUser().getEmail());

        profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
        // Inflate the layout for this fragment
        return view;
    }


    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            profilePicture.setImageURI(imageUri);
        }
    }

    private void uploadImage(){

    }
}