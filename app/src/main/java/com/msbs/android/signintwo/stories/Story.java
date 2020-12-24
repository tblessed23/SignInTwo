package com.msbs.android.signintwo.stories;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity (tableName = "story")
public class Story {

    @PrimaryKey(autoGenerate = true)
    private int userId;
    private String audiotitle;
    private String storycity;
    private String storycounty;
    private String storystate;


    /**
     * No args constructor for use in serialization
     *
     * @param story
     */
    public Story(String story) {
    }

    //Regular Constructor
    @Ignore
    public Story(String audiotitle, String storycity, String storycounty, String storystate) {
        this.audiotitle = audiotitle;
        this.storycity = storycity;
        this.storycounty = storycounty;
        this.storystate= storystate;

    }

    //Id Constructor

    public Story(int userId, String audiotitle, String storycity, String storycounty, String storystate) {
        this.userId = userId;
        this.audiotitle = audiotitle;
        this.storycity = storycity;
        this.storycounty = storycounty;
        this.storystate = storystate;

    }




    public Story() {

    }

    /***UserId**/
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**Audio Title**/

    public String getAudiotitle() {
        return audiotitle;
    }

    public void setAudiotitle(String audiotitle) {
        this.audiotitle= audiotitle;
    }

    /**Story City***/
    public String getStorycity() {
        return storycity;
    }

    public void setStorycity(String storycity) {
        this.storycity = storycity;
    }


    /***Story County***/
    public String getStorycounty() {
        return storycounty;
    }

    public void setStorycounty(String storycounty) {
        this.storycounty = storycounty;
    }

    /***Story State***/
    public String getStorystate() {
        return storystate;
    }

    public void setStorystate(String storystate) {
        this.storystate = storystate;
    }


//    public static DiffUtil.ItemCallback<com.msbs.android.asik.model.Story> DIFF_CALLBACK =
//            new DiffUtil.ItemCallback<com.msbs.android.asik.model.Story>() {
//
//                // FoodObject details may have changed if reloaded
//                // from the database, but ID is fixed.
//                @Override
//                public boolean areItemsTheSame(@NonNull com.msbs.android.asik.model.Story oldItem, @NonNull com.msbs.android.asik.model.Story newItem) {
//                    return oldItem.getUserId() == newItem.getUserId();
//                }
//
//                @SuppressLint("DiffUtilEquals")
//                @Override
//                public boolean areContentsTheSame(@NonNull com.msbs.android.asik.model.Story oldItem, @NonNull com.msbs.android.asik.model.Story newItem) {
//                    return oldItem.equals(newItem);
//                }
//            };

}

