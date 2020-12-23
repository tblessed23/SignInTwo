package com.msbs.android.signintwo.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Database;

import java.util.List;

public class MainViewModel extends ViewModel {

    //Constant for logging
    private static final String TAG = MainViewModel.class.getSimpleName();

    private final LiveData<List<User>> tasks;

    // Create a constructor where you call loadTaskById of the taskDao to initialize the tasks variable
    // Note: The constructor should receive the database and the taskId
    public MainViewModel(ThirdDatabase database, String taskId) {
        tasks = database.userDao().loadTaskById(taskId);
    }


    public LiveData<List<User>> getTasks() {
        return tasks;
    }
}
