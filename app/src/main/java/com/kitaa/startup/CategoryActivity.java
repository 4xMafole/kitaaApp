package com.kitaa.startup;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kitaa.R;
import com.kitaa.startup.adapters.HomePageAdapter;
import com.kitaa.startup.models.HomePageModel;
import com.kitaa.startup.models.HorizontalScrollProductModel;
import com.kitaa.startup.models.SliderModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryActivity extends AppCompatActivity
{

    private Toolbar _toolbar;
    private RecyclerView _categoryRecyclerview;
    private ArrayList<HomePageModel> _homePageModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        _toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(_toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        String _title = getIntent().getStringExtra("CategoryName");
        Objects.requireNonNull(getSupportActionBar()).setTitle(_title);

        /////RecyclerView
        _categoryRecyclerview = findViewById(R.id.category_recyclerview);
        initHomepageRecyclerview();
        /////RecyclerView

    }

    /////Homepage Recyclerview
    private void initHomepageRecyclerview()
    {
        LinearLayoutManager _linearLayoutManager = new LinearLayoutManager(this);
        _linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        _categoryRecyclerview.setLayoutManager(_linearLayoutManager);
        prepareHomePageData();

    }

    private void prepareHomePageData()
    {
        homePageDataList();
        HomePageAdapter _homePageAdapter = new HomePageAdapter(_homePageModelList);
        _categoryRecyclerview.setAdapter(_homePageAdapter);
        _homePageAdapter.notifyDataSetChanged();
    }

    private void homePageDataList()
    {
        _homePageModelList = new ArrayList<HomePageModel>();
//        _homePageModelList.add(new HomePageModel(0, bannerDataList()));
//        _homePageModelList.add(new HomePageModel(1, R.drawable.strip_add));
//        _homePageModelList.add(new HomePageModel(2, "# Latest Electronics", horizontalProductDataList()));
//        _homePageModelList.add(new HomePageModel(3, "New In Town", horizontalProductDataList()));
//        _homePageModelList.add(new HomePageModel(1, R.drawable.strip_add));
//        _homePageModelList.add(new HomePageModel(3, "# Trending", horizontalProductDataList()));
//        _homePageModelList.add(new HomePageModel(2, "Deals of the Day", horizontalProductDataList()));
    }
    /////Homepage Recyclerview

    /////Banner and horizontal data list.
    private List<SliderModel> bannerDataList()
    {
        ///todo: Admin adds user's own banner to promote a work public
        List<SliderModel> _sliderModelList = new ArrayList<SliderModel>();
//
//        _sliderModelList.add(new SliderModel(R.drawable.banner7));
//        _sliderModelList.add(new SliderModel(R.drawable.banner1));
//
//        _sliderModelList.add(new SliderModel(R.drawable.banner2));
//        _sliderModelList.add(new SliderModel(R.drawable.banner));
//        _sliderModelList.add(new SliderModel(R.drawable.banner4));
//        _sliderModelList.add(new SliderModel(R.drawable.banner5));
//        _sliderModelList.add(new SliderModel(R.drawable.banner6));
//        _sliderModelList.add(new SliderModel(R.drawable.banner10));
//        _sliderModelList.add(new SliderModel(R.drawable.banner9));
//        _sliderModelList.add(new SliderModel(R.drawable.banner11));
//
//        _sliderModelList.add(new SliderModel(R.drawable.banner7));
//        _sliderModelList.add(new SliderModel(R.drawable.banner1));

        return _sliderModelList;
    }

    private List<HorizontalScrollProductModel> horizontalProductDataList()
    {
        List<HorizontalScrollProductModel> _horizontalScrollProductModelList = new ArrayList<>();

        return _horizontalScrollProductModelList;
    }
    /////Banner and horizontal data list.

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_icon, menu);
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
        else if(id == android.R.id.home)
        {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
