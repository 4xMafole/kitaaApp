package com.kitaa.startup;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.kitaa.R;
import com.kitaa.startup.adapters.ProductDetailsAdapter;
import com.kitaa.startup.adapters.ProductImageAdapter;
import com.kitaa.startup.auth.RegisterActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.kitaa.startup.MainActivity.SHOW_WISHLIST;
import static com.kitaa.startup.auth.RegisterActivity._setSignUpFragment;
import static com.kitaa.startup.database.DBqueries._currentUser;

public class ProductDetailsActivity extends AppCompatActivity
{

    public static boolean ALREADY_IN_WISHLIST = false;
    private Toolbar _toolbar;
    private ViewPager _productImagesViewPager;
    private TabLayout _viewPagerIndicator;
    private FloatingActionButton _addWishlistButton;

    private ViewPager _productDetailsViewPager;
    private TabLayout _productDetailsTabLayout;

    /////ProductImages
    private Dialog _signInDialog;
    private List<Integer> _productImages;
    private ImageView _productOwnerCall;
    private ImageView _productOwnerChat;


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
        _productDetailsTabLayout = findViewById(R.id.product_details_tablayout);
        _productDetailsViewPager = findViewById(R.id.product_details_viewpager);
        _productOwnerCall = findViewById(R.id.product_owner_call);
        _productOwnerChat = findViewById(R.id.product_owner_chat);

        prepareProductImageData();
        toggleWishlist();
        detailsTabController();
        authDialog();
        contactProductOwner();
    }

    private void contactProductOwner()
    {
        final String _dialNumberTemp = "0743809705";

        _productOwnerCall.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent _intentCall = new Intent(Intent.ACTION_DIAL);
                _intentCall.setData(Uri.parse("tel:" + _dialNumberTemp));
                startActivity(_intentCall);
            }
        });

        _productOwnerChat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent _intentChat = new Intent(Intent.ACTION_VIEW);
                _intentChat.setType("vnd.android-dir/mms-sms");
                _intentChat.putExtra("address", _dialNumberTemp);
                startActivity(_intentChat);
            }
        });
    }

    private void detailsTabController()
    {
        _productDetailsViewPager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(), _productDetailsTabLayout.getTabCount()));

        _productDetailsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(_productDetailsTabLayout));
        _productDetailsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                _productDetailsViewPager.setCurrentItem(tab.getPosition());
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

    private void authDialog()
    {
        _signInDialog = new Dialog(ProductDetailsActivity.this);
        _signInDialog.setContentView(R.layout.sign_in_dialog);
        _signInDialog.setCancelable(true);
        Objects.requireNonNull(_signInDialog.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button dialogSignInDialogBtn = _signInDialog.findViewById(R.id.sign_in_dialog_btn);
        Button dialogSignUpDialogBtn = _signInDialog.findViewById(R.id.sign_up_dialog_btn);
        final Intent registerIntent = new Intent(ProductDetailsActivity.this, RegisterActivity.class);

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
                if(_currentUser != null)
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
                else
                {
                    _signInDialog.show();
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
            if(_currentUser != null)
            {
                Intent wishlistIntent = new Intent(ProductDetailsActivity.this, MainActivity.class);
                SHOW_WISHLIST = true;
                startActivity(wishlistIntent);
                return true;
            }
            else
            {
                _signInDialog.show();
                return false;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
