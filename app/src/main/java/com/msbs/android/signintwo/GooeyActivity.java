package com.msbs.android.signintwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.msbs.android.signintwo.view.UserDisplayDetailsActivity;

public class GooeyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gooey);


        // Find the View that shows the Lauryn Hill songs category
        Button lauryn = (Button) findViewById(R.id.profile);

        // Set a click listener on that View
        lauryn.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Lauryn Hill songs View is clicked on.
            @Override
            public void onClick(View view) {
                Intent laurynIntent = new Intent(GooeyActivity.this, UserDisplayDetailsActivity.class);
                startActivity(laurynIntent);


            }
        });

        // Find the View that shows the Lauryn Hill songs category
        Button logout = (Button) findViewById(R.id.favorites);

        // Set a click listener on that View
        logout.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Lauryn Hill songs View is clicked on.
            @Override
            public void onClick(View view) {
                Intent laurynIntent = new Intent(GooeyActivity.this, FavoritesActivity.class);
                startActivity(laurynIntent);
            }
        });

    }
}