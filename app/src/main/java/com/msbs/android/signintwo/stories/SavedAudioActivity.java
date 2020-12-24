package com.msbs.android.signintwo.stories;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.msbs.android.signintwo.R;

public class SavedAudioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_audio);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new SavedAudioFragment())
                .commit();
    }
}