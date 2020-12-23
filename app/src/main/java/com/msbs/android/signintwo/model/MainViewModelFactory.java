package com.msbs.android.signintwo.model;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import org.jetbrains.annotations.NotNull;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final ThirdDatabase mDb;
    private final String mTaskId;


    // Initialize the member variables in the constructor with the parameters received
    public  MainViewModelFactory(ThirdDatabase database, String taskId) {
        mDb = database;
        mTaskId = taskId;


    }

    // Uncomment the following method
    @NotNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainViewModel(mDb, mTaskId);
    }


}