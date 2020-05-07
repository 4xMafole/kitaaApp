package com.kitaa.startup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kitaa.R;
import com.kitaa.startup.auth.RegisterActivity;

public class SplashActivity extends AppCompatActivity
{

    private FirebaseAuth _firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        _firebaseAuth = FirebaseAuth.getInstance();



    }

    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseUser currentUser = _firebaseAuth.getCurrentUser();

        if(currentUser == null)
        {
            Intent registerIntent = new Intent(SplashActivity.this, RegisterActivity.class);
            startActivity(registerIntent);
            finish();
        }
        else
        {
            Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }
    }
}
