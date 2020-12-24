package com.msbs.android.signintwo.stories;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Database;

import com.msbs.android.signintwo.model.MainViewModel;
import com.msbs.android.signintwo.model.ThirdDatabase;

import java.util.List;

public class StoryMainViewModel extends AndroidViewModel {

    //Constant for logging
    private static final String TAG = StoryMainViewModel.class.getSimpleName();

    private LiveData<List<Story>> tasks;

    public StoryMainViewModel(@NonNull Application application) {
        super(application);
        ThirdDatabase database = ThirdDatabase.getInstance(this.getApplication());
        Log.d(TAG, "Actively retrieving  the tasks from the Database");
        tasks = database.storyDao().loadAllStories();
    }

    public LiveData<List<Story>> getTasks() {
        return tasks;
    }
}
