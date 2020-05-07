package com.kitaa.startup;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.kitaa.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    private NavigationView _navigationView;
    private Toolbar _toolbar;
    private FrameLayout _frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(_toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        _navigationView = findViewById(R.id.nav_view);
        _navigationView.setNavigationItemSelectedListener(this);
        _navigationView.getMenu().getItem(0).setChecked(true);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle _toggle =  new ActionBarDrawerToggle(this, drawer, _toolbar, 0, 0);
        drawer.addDrawerListener(_toggle);
        _toggle.syncState();

        _frameLayout = findViewById(R.id.main_framelayout);
        setFragment(new HomeFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
        else if(id == R.id.main_cart_icon)
        {
            //todo : shopping cart
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();

        if(id == R.id.main_mall)
        {
            Toast.makeText(this, "Mall fragment", Toast.LENGTH_SHORT).show();
        }
         else if(id == R.id.main_orders)
         {
             Toast.makeText(this, "Order fragment", Toast.LENGTH_SHORT).show();
         }
         else if(id == R.id.main_shopping_cart)
         {
             Toast.makeText(this, "Cart fragment", Toast.LENGTH_SHORT).show();
         }
         else if(id == R.id.main_wishlist)
         {
             Toast.makeText(this, "Wishlist fragment", Toast.LENGTH_SHORT).show();
         }
         else if(id == R.id.main_rewards)
         {
             Toast.makeText(this, "Rewards fragment", Toast.LENGTH_SHORT).show();
         }
         else if(id == R.id.main_profile)
         {
             Toast.makeText(this, "Profile fragment", Toast.LENGTH_SHORT).show();
         }
         else if(id == R.id.main_logout)
        {
            Toast.makeText(this, "Logout fragment", Toast.LENGTH_SHORT).show();
        }
        DrawerLayout _drawerLayout = findViewById(R.id.drawer_layout);
        _drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragment(Fragment fragment)
    {
        FragmentTransaction _fragmentTransaction = getSupportFragmentManager().beginTransaction();
        _fragmentTransaction.replace(_frameLayout.getId(), fragment);
        _fragmentTransaction.commit();

    }


}
