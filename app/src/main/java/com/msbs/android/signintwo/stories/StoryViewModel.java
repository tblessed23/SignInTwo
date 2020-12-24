package com.msbs.android.signintwo.stories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Database;

import com.msbs.android.signintwo.model.ThirdDatabase;

public class StoryViewModel extends ViewModel {

    //  private UserEditRepository mRepository;
    private LiveData<Story> task;


    public StoryViewModel(){ };
    //Add a task member variable for the TaskEntry object wrapped in a LiveData


    // Create a constructor where you call loadTaskById of the taskDao to initialize the tasks variable
    // Note: The constructor should receive the database and the taskId
    public StoryViewModel(ThirdDatabase database, int taskId) {
        task = database.storyDao().loadStoryById(taskId);
    }



    // Create a getter for the task variable
    public LiveData<Story> getTask() {
        return task;
    }

    ;


}
