package com.msbs.android.signintwo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.msbs.android.signintwo.stories.EditAudioDetailsActivity;
import com.msbs.android.signintwo.stories.SavedAudioActivity;
import com.msbs.android.signintwo.stories.SavedAudioFragment;
import com.msbs.android.signintwo.view.LoggedInActivity;
import com.msbs.android.signintwo.view.UserDisplayDetailsActivity;
import com.msbs.android.signintwo.view.UserEditDetailsActivity;
import com.msbs.android.signintwo.viewmodel.LoggedInViewModel;

public class GooeyActivity extends AppCompatActivity {
    private TextView loggedInUserTextView;
    private Button logOutButton;

    private LoggedInViewModel loggedInViewModel;
    private TextView messageTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gooey);




        loggedInViewModel = ViewModelProviders.of(this).get(LoggedInViewModel.class);
        loggedInViewModel.getUserLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null) {
                    loggedInUserTextView.setText("Logged In User: " + firebaseUser.getEmail());
                    logOutButton.setEnabled(true);
                } else {
                    logOutButton.setEnabled(false);
                }
            }
        });


        loggedInViewModel.getLoggedOutLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean loggedOut) {
                if (loggedOut) {
                    Toast.makeText(GooeyActivity.this, "User Logged Out", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(GooeyActivity.this, MainActivity.class);
                    startActivity(intent);

                }
            }
        });

        loggedInUserTextView = findViewById(R.id.fragment_loggedin_loggedInUser);
        logOutButton = findViewById(R.id.fragment_loggedin_logOut);

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loggedInViewModel.logOut();
            }
        });

        // Find the View that shows the Lauryn Hill songs category
        Button logging = (Button) findViewById(R.id.search_button);

        // Set a click listener on that View
        logging.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Lauryn Hill songs View is clicked on.
            @Override
            public void onClick(View view) {
                Intent laurynIntent = new Intent(GooeyActivity.this, FavoritesActivity.class);
                startActivity(laurynIntent);

            }
        });


        // Find the View that shows the Lauryn Hill songs category
        Button lauryn = (Button) findViewById(R.id.view_profile);

        // Set a click listener on that View
        lauryn.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Lauryn Hill songs View is clicked on.
            @Override
            public void onClick(View view) {
                Intent vpIntent = new Intent(GooeyActivity.this, UserDisplayDetailsActivity.class);
                startActivity(vpIntent);


            }
        });

        // Find the View that shows the Lauryn Hill songs category
        Button hill= (Button) findViewById(R.id.edit_profile);

        // Set a click listener on that View
        hill.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Lauryn Hill songs View is clicked on.
            @Override
            public void onClick(View view) {
                Intent epIntent = new Intent(GooeyActivity.this, UserEditDetailsActivity.class);
                startActivity(epIntent);


            }
        });

        // Find the View that shows the Lauryn Hill songs category
        Button logout = (Button) findViewById(R.id.favorites);

        // Set a click listener on that View
        logout.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Lauryn Hill songs View is clicked on.
            @Override
            public void onClick(View view) {
                Intent favIntent = new Intent(GooeyActivity.this, FavoritesActivity.class);
                startActivity(favIntent);
            }
        });

        // Find the View that shows the Lauryn Hill songs category
        Button story = (Button) findViewById(R.id.add_story);

        // Set a click listener on that View
        story.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Lauryn Hill songs View is clicked on.
            @Override
            public void onClick(View view) {
                Intent asIntent = new Intent(GooeyActivity.this,  SavedAudioActivity.class);
                startActivity(asIntent);
            }
        });

        // Find the View that shows the Lauryn Hill songs category
        Button viewstory = (Button) findViewById(R.id.view_story);

        // Set a click listener on that View
        viewstory.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Lauryn Hill songs View is clicked on.
            @Override
            public void onClick(View view) {
                Intent vsIntent = new Intent(GooeyActivity.this,  SavedAudioActivity.class);
                startActivity(vsIntent);
            }
        });

    }
}