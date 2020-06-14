package com.kitaa.startup;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kitaa.R;
import com.kitaa.startup.auth.RegisterActivity;

import java.util.Objects;

import static com.kitaa.startup.auth.RegisterActivity._setSignUpFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    public static final int HOME_FRAGMENT = 0;
    public static final int WISHLIST_FRAGMENT = 2;
    public static final int ADS_FRAGMENT = 1;
    public static final int ACCOUNT_FRAGMENT = 4;
    public static Boolean SHOW_WISHLIST = false;

    private NavigationView _navigationView;
    private Toolbar _toolbar;
    private FrameLayout _frameLayout;
    private int _currentFragment = -1;
    private ImageView _actionBarLogo;
    public static DrawerLayout _drawer;
    private Dialog _signInDialog;

    private FirebaseUser _currentUser;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(_toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        _actionBarLogo = findViewById(R.id.actionBar_logo);
        _frameLayout = findViewById(R.id.main_framelayout);

        _navigationView = findViewById(R.id.nav_view);
        _navigationView.setNavigationItemSelectedListener(this);
        _navigationView.getMenu().getItem(0).setChecked(true);

        _drawer = findViewById(R.id.drawer_layout);
        displayWishlist();

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        checkCurrentUser();
    }

    private void checkCurrentUser()
    {
        int navigationItem = 4;
        _currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(_currentUser == null)
        {
            for(int x = 1; x < navigationItem; x++)
            {
                _navigationView.getMenu().getItem(_navigationView.getMenu().size() - x).setEnabled(false);
                _navigationView.getMenu().getItem(_navigationView.getMenu().size() - x).setVisible(false);
            }
        }
        else
        {
            for(int x = 1; x < navigationItem; x++)
            {
                _navigationView.getMenu().getItem(_navigationView.getMenu().size() - x).setEnabled(true);
                _navigationView.getMenu().getItem(_navigationView.getMenu().size() - x).setVisible(true);
            }
        }
        authDialog();

    }

    private void authDialog()
    {
        _signInDialog = new Dialog(MainActivity.this);
        _signInDialog.setContentView(R.layout.sign_in_dialog);
        _signInDialog.setCancelable(true);
        Objects.requireNonNull(_signInDialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button dialogSignInDialogBtn = _signInDialog.findViewById(R.id.sign_in_dialog_btn);
        Button dialogSignUpDialogBtn = _signInDialog.findViewById(R.id.sign_up_dialog_btn);
        final Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);

        dialogSignInDialogBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                _signInDialog.dismiss();
                _setSignUpFragment = false;
                startActivity(registerIntent);
            }
        });

        dialogSignUpDialogBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                _signInDialog.dismiss();
                _setSignUpFragment = true;
                startActivity(registerIntent);
            }
        });
    }

    @SuppressLint("WrongConstant")
    private void displayWishlist()
    {
        if(SHOW_WISHLIST)
        {
            _drawer.setDrawerLockMode(1);
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
            gotoFragment("My Wishlist", new WishlistFragment(), -2);
        }
        else
        {
            ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(this, _drawer, _toolbar, 0, 0);
            _drawer.addDrawerListener(_toggle);
            _toggle.syncState();
            setFragment(new HomeFragment(), HOME_FRAGMENT);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        if(_currentFragment == HOME_FRAGMENT)
        {
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();

        if(id == R.id.main_search_icon)
        {
            //todo : search
            return true;
        }
        else if(id == R.id.main_notification_icon)
        {
            //todo : notification
            return true;
        }
        else if(id == R.id.main_wishlist_icon)
        {
            if(_currentUser == null)
            {
                _signInDialog.show();
            }
            else
            {
                gotoFragment("My Wishlist", new WishlistFragment(), WISHLIST_FRAGMENT);
            }

            return true;
        }
        else if(id == android.R.id.home)
        {
            SHOW_WISHLIST = false;
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void gotoFragment(String title, Fragment fragment, int fragmentNo)
    {
        _actionBarLogo.setVisibility(View.GONE);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        invalidateOptionsMenu();
        setFragment(fragment, fragmentNo);

        if(fragmentNo == WISHLIST_FRAGMENT)
        {
            _navigationView.getMenu().getItem(2).setChecked(true);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        DrawerLayout _drawerLayout = findViewById(R.id.drawer_layout);

        if(_currentUser != null)
        {
            int id = item.getItemId();

            if(id == R.id.main_home)
            {
                _actionBarLogo.setVisibility(View.VISIBLE);
                invalidateOptionsMenu();
                setFragment(new HomeFragment(), HOME_FRAGMENT);
            }
            else if(id == R.id.main_ads)
            {
                gotoFragment("My Ads", new AdsFragment(), ADS_FRAGMENT);
            }
            else if(id == R.id.main_wishlist)
            {
                gotoFragment("My Wishlist", new WishlistFragment(), WISHLIST_FRAGMENT);
            }
            else if(id == R.id.main_profile)
            {
                gotoFragment("My Profile", new AccountFragment(), ACCOUNT_FRAGMENT);
            }
            else if(id == R.id.main_logout)
            {
                FirebaseAuth.getInstance().signOut();
                Intent signInIntent = new Intent(this, RegisterActivity.class);
                startActivity(signInIntent);
                finish();
            }
            _drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
        else
        {
            _drawerLayout.closeDrawer(GravityCompat.START);
            _signInDialog.show();
            return false;
        }
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout _drawerLayout = findViewById(R.id.drawer_layout);
        if(_drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            _drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            if(_currentFragment == HOME_FRAGMENT)
            {
                _currentFragment = -1;
                super.onBackPressed();
            }
            else
            {
                if(SHOW_WISHLIST)
                {
                    SHOW_WISHLIST = false;
                    finish();
                }
                else
                {
                    _actionBarLogo.setVisibility(View.VISIBLE);
                    invalidateOptionsMenu();
                    setFragment(new HomeFragment(), HOME_FRAGMENT);
                    _navigationView.getMenu().getItem(0).setChecked(true);
                }
            }
        }
    }

    private void setFragment(Fragment fragment, int fragmentNo)
    {
        if(fragmentNo != _currentFragment)
        {
            _currentFragment = fragmentNo;
            FragmentTransaction _fragmentTransaction = getSupportFragmentManager().beginTransaction();
            _fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            _fragmentTransaction.replace(_frameLayout.getId(), fragment);
            _fragmentTransaction.commit();
        }

    }


}
