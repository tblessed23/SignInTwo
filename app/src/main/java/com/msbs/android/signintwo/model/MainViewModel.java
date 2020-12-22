package com.msbs.android.signintwo.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Database;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    //Constant for logging
    private static final String TAG = MainViewModel.class.getSimpleName();

    private LiveData<User> tasks;

    public MainViewModel(@NonNull Application application, String taskId) {
        super(application);
        ThirdDatabase database = ThirdDatabase.getInstance(this.getApplication());
        Log.d(TAG, "Actively retrieving  the tasks from the Database");
       // tasks = database.userDao().loadAllTasks();
        tasks = database.userDao().loadStoryById(taskId);
    }

    public LiveData<User> getTasks() {
        return tasks;
    }
}
