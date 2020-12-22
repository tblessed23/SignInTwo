package com.msbs.android.signintwo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.msbs.android.signintwo.model.ThirdDatabase;
import com.msbs.android.signintwo.view.LoggedInActivity;
import com.msbs.android.signintwo.view.UserDisplayDetailsActivity;
import com.msbs.android.signintwo.view.UserEditDetailsActivity;
import com.msbs.android.signintwo.viewmodel.LoginRegisterViewModel;

public class LoginRegisterActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button registerButton;
    Button registerButtonTwo;


    private int mTaskId = DEFAULT_TASK_ID;
    // Constant for default task id to be used when not in update mode
    private static final int DEFAULT_TASK_ID = -1;
    private ThirdDatabase mDb;

    private LoginRegisterViewModel loginRegisterViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        // Initialize member variable for the data base
        mDb = ThirdDatabase.getInstance(getApplicationContext());

        loginRegisterViewModel = ViewModelProviders.of(this).get(LoginRegisterViewModel.class);
        loginRegisterViewModel.getUserLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null) {
                    Intent intent = new Intent(LoginRegisterActivity.this, UserEditDetailsActivity.class);

                    startActivity(intent);
                }
            }
        });


        emailEditText = findViewById(R.id.fragment_loginregister_email);
        passwordEditText = findViewById(R.id.fragment_loginregister_password);
        registerButton = findViewById(R.id.fragment_loginregister_register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View view) {

                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (email.length() > 0 && password.length() > 0) {
                    loginRegisterViewModel.register(email, password);

                } else {
                    Toast.makeText(LoginRegisterActivity.this, "Email Address and Password Must Be Entered", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.sign_out_menu:
                //sign out
                Intent laurynIntent = new Intent(LoginRegisterActivity.this, UserDisplayDetailsActivity.class);
                startActivity(laurynIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

