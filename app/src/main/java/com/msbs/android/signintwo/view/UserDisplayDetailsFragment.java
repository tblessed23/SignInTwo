package com.msbs.android.signintwo.view;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseUser;
import com.msbs.android.signintwo.MainActivity;
import com.msbs.android.signintwo.R;
import com.msbs.android.signintwo.model.MainViewModel;
import com.msbs.android.signintwo.model.ThirdDatabase;
import com.msbs.android.signintwo.model.User;
import com.msbs.android.signintwo.model.UserViewModel;
import com.msbs.android.signintwo.viewmodel.LoggedInViewModel;

import java.util.ArrayList;
import java.util.List;

public class UserDisplayDetailsFragment extends Fragment {



        //Constant for logging
        private static final String TAG = MainActivity.class.getSimpleName();
    private LoggedInViewModel loggedInViewModel;
    Button mButton;
    private TextView loggedInUserTextView;
        // Member variables for the adapter and RecyclerView
        private RecyclerView mRecyclerView;
        private UserAdapter mAdapter;
        private List<User> mStoryEntries = new ArrayList<>();
    private Button logOutButton;

        /**
         * TextView that is displayed when the list is empty
         */
        private TextView mEmptyStateTextView;

        //Implement Database
        private ThirdDatabase mDb;

    public UserDisplayDetailsFragment() {
        // Required empty public constructor
    }


        @Override
        public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//
//        }

            loggedInViewModel = ViewModelProviders.of(this).get(LoggedInViewModel.class);
            loggedInViewModel.getUserLiveData().observe(this, new Observer<FirebaseUser>() {
                @Override
                public void onChanged(FirebaseUser firebaseUser) {
                    if (firebaseUser != null) {
                        loggedInUserTextView.setText("Logged In User: " + firebaseUser.getEmail());
                        logOutButton.setEnabled(true);
                    } else {
                        logOutButton.setEnabled(false);
                    }
                }
            });

            loggedInViewModel.getLoggedOutLiveData().observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean loggedOut) {
                    if (loggedOut) {
                        Toast.makeText(getContext(), "User Logged Out", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }
                }
            });

    }

        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                                  Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_user_display_details, container, false);
            mRecyclerView = rootView.findViewById(R.id.recyclerViewTasks);

            // Set the RecyclerView to its corresponding view
        // mRecyclerView = rootView.findViewById(R.id.recyclerViewTasks);

        // Set the layout for the RecyclerView to be a linear layout, which measures and
        // positions items within a RecyclerView into a linear list
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Initialize the adapter and attach it to the RecyclerView
        mAdapter = new UserAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(decoration);


        //Initialize Database
        mDb = ThirdDatabase.getInstance(getContext());

        setupViewModel();


//        if (mStoryEntries.isEmpty()) {
//            mRecyclerView.setVisibility(View.GONE);
//            mEmptyStateTextView.setVisibility(View.VISIBLE);
//        }
//        else {
//            mRecyclerView.setVisibility(View.VISIBLE);
//            mEmptyStateTextView.setVisibility(View.GONE);
//        }


            loggedInUserTextView = rootView.findViewById(R.id.fragment_loggedin_loggedInUser);
            logOutButton = rootView.findViewById(R.id.fragment_loggedin_logOut);

            logOutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loggedInViewModel.logOut();
                }
            });


        return rootView;
    }


        private void setupViewModel () {


       UserViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(UserViewModel.class);
        viewModel.getTasks().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> taskEntries) {
                Log.d(TAG, "Updating list of tasks from LiveData in ViewModel");
                mAdapter.setTasks(taskEntries);
            }
        });
    }





    }