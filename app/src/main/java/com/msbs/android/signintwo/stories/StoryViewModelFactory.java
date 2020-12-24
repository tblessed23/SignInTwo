package com.msbs.android.signintwo.stories;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.msbs.android.signintwo.model.ThirdDatabase;
import com.msbs.android.signintwo.model.UserEditViewModel;

import org.jetbrains.annotations.NotNull;

public class StoryViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final ThirdDatabase mDb;
    private final int mTaskId;


    // Initialize the member variables in the constructor with the parameters received
    public  StoryViewModelFactory(ThirdDatabase database, int taskId) {
        mDb = database;
        mTaskId = taskId;


    }

    // Uncomment the following method
    @NotNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new StoryViewModel(mDb, mTaskId);
    }


}


