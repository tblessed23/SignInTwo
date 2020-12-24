package com.msbs.android.signintwo.view;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.msbs.android.signintwo.R;

public class UserDisplayDetailsActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_display_details);
// ...
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new UserDisplayDetailsFragment())
                .commit();

    }}

//    @Override
//    public void onStart() {
//        super.onStart();
//
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
//    }
//}