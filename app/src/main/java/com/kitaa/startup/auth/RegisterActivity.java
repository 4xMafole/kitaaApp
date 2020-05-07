package com.kitaa.startup.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.kitaa.R;

public class RegisterActivity extends AppCompatActivity
{
    private FrameLayout _frameLayout;
    public static boolean _onResetPasswordFragment = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        _frameLayout = findViewById(R.id.register_framelayout);
        setDefaultFragment(new SignInFragment());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            if(_onResetPasswordFragment)
            {
                _onResetPasswordFragment = false;
                setFragment(new SignInFragment());
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setDefaultFragment(Fragment fragment)
    {
        FragmentTransaction _fragmentTransaction = getSupportFragmentManager().beginTransaction();
        _fragmentTransaction.replace(_frameLayout.getId(), fragment);
        _fragmentTransaction.commit();
    }

    private void setFragment(Fragment fragment)
    {
        FragmentTransaction _fragmentTransaction = getSupportFragmentManager().beginTransaction();
        _fragmentTransaction.setCustomAnimations(R.anim.slide_from_left, R.anim.slideout_from_right);
        _fragmentTransaction.replace(_frameLayout.getId(), fragment);
        _fragmentTransaction.commit();
    }
}
