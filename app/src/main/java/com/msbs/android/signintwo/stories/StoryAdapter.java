package com.msbs.android.signintwo.stories;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import com.msbs.android.signintwo.R;
import com.msbs.android.signintwo.model.AppExecutors;
import com.msbs.android.signintwo.model.ThirdDatabase;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {
    // Constant for date format
    private static final String DATE_FORMAT = "MM/dd/yyy";

    // Member variable to handle item clicks
    // final private ItemClickListener mItemClickListener;
    // Class variables for the List that holds task data and the Context
    private List<Story> mStoryEntries;
    private Context mContext;
    // UserEditViewModel userModel;
    private int mTaskId = DEFAULT_TASK_ID;
    // Constant for default task id to be used when not in update mode
    private static final int DEFAULT_TASK_ID = -1;
    // Member variable for the Database
    private ThirdDatabase mDb;


    // Date formatter
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    /**
     * Constructor for the TaskAdapter that initializes the Context.
     *
     * @param context the current Context
     */
    public StoryAdapter(Context context) {
        mContext = context;
        //mItemClickListener = listener;
    }

    /**
     * Called when ViewHolders are created to fill a RecyclerView.
     *
     * @return A new TaskViewHolder that holds the view for each task
     */
    @Override
    public StoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the task_layout to a view
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.list_saved_audio, parent, false);

        return new StoryViewHolder(view);
    }

    /**
     * Called by the RecyclerView to display data at a specified position in the Cursor.
     *
     * @param holder   The ViewHolder to bind Cursor data to
     * @param position The position of the data in the Cursor
     */
    @Override
    public void onBindViewHolder(final StoryViewHolder holder, final int position) {
        // Determine the values of the wanted data
        final Story stories = mStoryEntries.get(position);
        final String title = stories.getAudiotitle();

        final String storycity = stories.getStorycity();
        final String storycounty = stories.getStorycounty();
        final String storystate = stories.getStorystate();

        final int userid = stories.getUserId();
        // final String updatedAt = dateFormat.format(stories.getUpdatedAt());
        mDb = ThirdDatabase.getInstance(mContext);

        //Handle Editing Database Entry
        holder.editStoryDetails.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                int elementId = mStoryEntries.get(position).getUserId();
                Intent intent = new Intent(mContext, EditAudioDetailsActivity.class);
                intent.putExtra(EditAudioDetailsActivity.EXTRA_TASK_ID, elementId);
                mContext.startActivity(intent);
            }
        });






        //Set values
        holder.titleView.setText(title);
        holder.storyStateView.setText(storystate);
        holder.storyCountyView.setText(storycounty);
        holder.storyCityView.setText(storycity);

        // holder.updatedAtView.setText(updatedAt);


    }


    /*


    /**
     * Returns the number of items to display.
     */
    @Override
    public int getItemCount() {
        if (mStoryEntries == null) {
            return 0;
        }
        return mStoryEntries.size();
    }

    public List<Story> getTasks() {
        return mStoryEntries;
    }

    /**
     * When data changes, this method updates the list of taskEntries
     * and notifies the adapter to use the new values on it
     */
    public void setTasks(List<Story> storyEntries) {
        mStoryEntries = storyEntries;
        notifyDataSetChanged();
    }

    // Inner class for creating ViewHolders
    class StoryViewHolder extends RecyclerView.ViewHolder {

        // Class variables for the task description and priority TextViews
        TextView titleView;
        TextView ancestorFNView;
        TextView ancestorLNView;
        TextView familynameView;
        TextView storyCityView;
        TextView storyCountyView;
        TextView storyStateView;
        // TextView updatedAtView;
        TextView fullMenu;
        TextView audioURLView;
        Button editStoryDetails;
        Button favoriteButton;

        /**
         * Constructor for the TaskViewHolders.
         *
         * @param itemView The view inflated in onCreateViewHolder
         */
        public StoryViewHolder(View itemView) {
            super(itemView);

            titleView = itemView.findViewById(R.id.audio_title);
            //updatedAtView = itemView.findViewById(R.id.taskUpdatedAt);

            storyCityView = itemView.findViewById(R.id.city_of_story);
            storyCountyView = itemView.findViewById(R.id.county_of_story);
            storyStateView = itemView.findViewById(R.id.state_of_story);
            editStoryDetails = itemView.findViewById(R.id.edit_saved_audio);
            fullMenu = itemView.findViewById(R.id.menuTextMenuView);


        }

    }

}