package com.msbs.android.signintwo.view;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.msbs.android.signintwo.R;

public class UserDisplayDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_display_details);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new UserDisplayDetailsFragment())
                .commit();

    }
}