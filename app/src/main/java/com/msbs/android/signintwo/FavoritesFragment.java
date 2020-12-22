package com.msbs.android.signintwo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.auth.FirebaseUser;
import com.msbs.android.signintwo.model.ThirdDatabase;
import com.msbs.android.signintwo.model.User;
import com.msbs.android.signintwo.model.UserViewModel;
import com.msbs.android.signintwo.view.LoggedInActivity;
import com.msbs.android.signintwo.view.UserDisplayDetailsActivity;
import com.msbs.android.signintwo.viewmodel.LoggedInViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {
    private TextView loggedInUserTextView;
    private Button logOutButton;

    private LoggedInViewModel loggedInViewModel;


    // Constant for logging
    private static final String TAG = com.msbs.android.signintwo.FavoritesFragment.class.getSimpleName();
    private ThirdDatabase mDb;
    private FavoritesAdapter mAdapter;

    private RecyclerView.LayoutManager layoutManager;
private RecyclerView mRecyclerView;

    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDb = ThirdDatabase.getInstance(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_favorites, container, false);

        loggedInViewModel = ViewModelProviders.of(this).get(LoggedInViewModel.class);
        loggedInViewModel.getUserLiveData().observe(getActivity(), new Observer<FirebaseUser>() {
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


        loggedInViewModel.getLoggedOutLiveData().observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean loggedOut) {
                if (loggedOut) {
                    Toast.makeText(getActivity(), "User Logged Out", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);

                }
            }
        });

        loggedInUserTextView = rootView.findViewById(R.id.fragment_loggedin_loggedInUser);
        logOutButton = rootView.findViewById(R.id.fragment_loggedin_logOut);

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loggedInViewModel.logOut();
            }
        });



        setHasOptionsMenu(true);
        mRecyclerView = rootView.findViewById(R.id.recyclerViewFavorites);

        // use a grid layout manager

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        //mRecyclerView.setHasFixedSize(true);

        // Create a new adapter that takes an empty list of moviess as input
        mAdapter = new FavoritesAdapter(getActivity(), new ArrayList<User>());
        mRecyclerView.setAdapter(mAdapter);


        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        mRecyclerView.setAdapter(mAdapter);





        setUpViewModel();
        return rootView;
    }


    private void setUpViewModel() {
        //Insert Update andDelete do not have to observe changes in the database. This is for retrieving tass.
        //LiveData is for retrieving data, AppExcutors for the other three
        // Log.d(TAG, "Actively retrieving the tasks from the DataBase");
       UserViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) getActivity()).get(UserViewModel.class);
        viewModel.getTasks().observe((LifecycleOwner) getActivity(), (Observer<? super List<User>>) new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> favoritesEntries) {

                mAdapter.setTasks(favoritesEntries);
            }
        });


    }




}