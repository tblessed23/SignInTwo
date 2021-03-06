package com.msbs.android.signintwo.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    //Constant for logging
    private static final String TAG = UserViewModel.class.getSimpleName();
   // private AuthAppRepository mRepository;
    private final LiveData<List<User>> tasks;

    public UserViewModel(@NonNull Application application) {
        super(application);
       ThirdDatabase database = ThirdDatabase.getInstance(this.getApplication());
       // mRepository = new AuthAppRepository(application);
        Log.d(TAG, "Actively retrieving  the tasks from the Database");
        tasks = database.userDao().loadAllTasks();
    }

    public LiveData<List<User>> getTasks() {
        return tasks;
    }

//    public void insertTask(User user) { mRepository.insertTask(user); }

}

