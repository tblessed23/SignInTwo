package com.msbs.android.signintwo.stories;

import androidx.lifecycle.LiveData;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StoryDao {

    @Query("SELECT * FROM story ")
    LiveData<List<Story>> loadAllStories();

    @Query("SELECT * FROM story")
    Story loadStoryObject();
//
//    @Query("SELECT * FROM story where storystate LIKE  :query " + "OR storycity LIKE :query order by storystate")
//    DataSource.Factory<Integer, Story> loadAllStoriesFromSearch(String query);
//
//    @Query("SELECT * FROM story order by storystate")
//    DataSource.Factory<Integer, Story> loadAllStoryView();

    @Insert
    void insertTask(Story storyEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(Story storyEntry);

    @Delete
    void deleteTask(Story story);

    // Create a Query method named loadTaskById that receives an int id and returns a TaskEntry Object
    // The query for this method should get all the data for that id in the task table
    @Query("SELECT * FROM story WHERE userId = :id")
    LiveData<Story> loadStoryById(int id);


//    @Query("SELECT * FROM Story WHERE audiotitle LIKE :storyText")
//    LiveData<List<Story>> getDealsList(String storyText);

    @Query("SELECT * from story WHERE storystate like  :desc")
    LiveData<List<Story>> getSearchResults(String desc);


}
