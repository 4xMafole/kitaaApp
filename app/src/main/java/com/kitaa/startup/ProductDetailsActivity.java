package com.kitaa.startup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.kitaa.R;
import com.kitaa.startup.adapters.ProductDetailsAdapter;
import com.kitaa.startup.adapters.ProductImageAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductDetailsActivity extends AppCompatActivity
{

    public static boolean ALREADY_IN_WISHLIST = false;
    private Toolbar _toolbar;
    private ViewPager _productImagesViewPager;
    private TabLayout _viewPagerIndicator;
    private FloatingActionButton _addWishlistButton;

    private ViewPager productDetailsViewPager;
    private TabLayout productDetailsTabLayout;

    /////ProductImages
    private List<Integer> _productImages;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        _toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(_toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        _productImagesViewPager = findViewById(R.id.products_images_viewpager);
        _viewPagerIndicator = findViewById(R.id.viewpager_indicator);
        _addWishlistButton = findViewById(R.id.add_to_wishlist_btn);
        productDetailsTabLayout = findViewById(R.id.product_details_tablayout);
        productDetailsViewPager = findViewById(R.id.product_details_viewpager);

        prepareProductImageData();
        toggleWishlist();

        productDetailsViewPager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(), productDetailsTabLayout.getTabCount()));

        productDetailsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDetailsTabLayout));
        productDetailsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                productDetailsViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });
    }

    private void prepareProductImageData()
    {
        productImageDataList();
        ProductImageAdapter _productImageAdapter = new ProductImageAdapter(_productImages);
        _productImagesViewPager.setAdapter(_productImageAdapter);
        _viewPagerIndicator.setupWithViewPager(_productImagesViewPager, true);
    }

    private void productImageDataList()
    {
        _productImages = new ArrayList<>();
        _productImages.add(R.drawable.phone);
        _productImages.add(R.drawable.phone2);
        _productImages.add(R.drawable.banner6);
        _productImages.add(R.drawable.banner5);
    }

    private void toggleWishlist()
    {
        _addWishlistButton.setOnClickListener(new View.OnClickListener()
        {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v)
            {
                if(ALREADY_IN_WISHLIST)
                {
                    ALREADY_IN_WISHLIST = false;
                    _addWishlistButton.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#E6CE97")));
                }
                else
                {
                    ALREADY_IN_WISHLIST = true;
                    _addWishlistButton.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#FFB000")));
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_and_wishlist_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();

        if(id == android.R.id.home)
        {
            finish();
            return true;
        }
        else if(id == R.id.main_search_icon)
        {
            //todo : search
            return true;
        }
        else if(id == R.id.main_wishlist_icon)
        {
            //todo : shopping cart
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
