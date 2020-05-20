package com.kitaa.startup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.kitaa.R;
import com.kitaa.startup.adapters.GridProductLayoutAdapter;
import com.kitaa.startup.adapters.WishlistAdapter;
import com.kitaa.startup.models.HorizontalScrollProductModel;
import com.kitaa.startup.models.WishlistModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ViewAllActivity extends AppCompatActivity
{

    private List<WishlistModel> _wishlistModelList;
    private List<HorizontalScrollProductModel> _horizontalScrollProductModelList;
    private RecyclerView _viewAllRecyclerview;
    private GridView _viewAllGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        Toolbar _toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(_toolbar);

        int layout_code = getIntent().getIntExtra("layout_code", -1);

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Deals In Town");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(layout_code == 0)
        {
            _viewAllRecyclerview = findViewById(R.id.recycler_view);
            initViewAllRecyclerview();
        }
        else if(layout_code == 1)
        {
            _viewAllGridView = findViewById(R.id.grid_view);
            initViewAllGridview();
        }


    }

    private void initViewAllGridview()
    {
        _viewAllGridView.setVisibility(View.VISIBLE);
        viewAllGridDataList();
        GridProductLayoutAdapter _adapter = new GridProductLayoutAdapter(_horizontalScrollProductModelList);
        _viewAllGridView.setAdapter(_adapter);
    }

    private void viewAllGridDataList()
    {
        _horizontalScrollProductModelList = new ArrayList<>();
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone4, "Iphone X", "SD 234 Processors", "Tshs.10,000,000/="));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone, "Techno S8", "2 Core Processors", "Tshs.700,000/="));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone4, "Iphone X", "SD 234 Processors", "Tshs.10,000,000/="));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone4, "Iphone X", "SD 234 Processors", "Tshs.10,000,000/="));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone2, "Huawei P20", "CBC 10-2 Processors", "Tshs.800,000/="));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone3, "Vivo 20T", "CBC 10-2 Processors", "Tshs.400,000/="));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone4, "Iphone X", "SD 234 Processors", "Tshs.10,000,000/="));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone, "Techno S8", "2 Core Processors", "Tshs.700,000/="));

    }

    private void initViewAllRecyclerview()
    {
        _viewAllRecyclerview.setVisibility(View.VISIBLE);
        LinearLayoutManager _linearLayoutManager = new LinearLayoutManager(this);
        _linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        _viewAllRecyclerview.setLayoutManager(_linearLayoutManager);
        prepareViewAllDataList();
    }

    private void prepareViewAllDataList()
    {
        viewAllDataList();
        WishlistAdapter _adapter = new WishlistAdapter(_wishlistModelList);
        _viewAllRecyclerview.setAdapter(_adapter);
        _adapter.notifyDataSetChanged();
    }

    private void viewAllDataList()
    {
        _wishlistModelList = new ArrayList<>();
        _wishlistModelList.add(new WishlistModel(R.drawable.banner, "Hello Lotion CRs19", "Tshs.30,000", "46 minutes", "Kigoma"));
        _wishlistModelList.add(new WishlistModel(R.drawable.banner2, "Buggati Veron 213L", "Tshs.144,000", "24/05/20", "Dar es Salaam"));
        _wishlistModelList.add(new WishlistModel(R.drawable.banner2, "Buggati Veron 213L", "Tshs.144,000", "24/05/20", "Dar es Salaam"));
        _wishlistModelList.add(new WishlistModel(R.drawable.banner3, "GSM Mall Collections Pie", "Tshs.32,000", "19/04/20", "Mwanza"));
        _wishlistModelList.add(new WishlistModel(R.drawable.banner4, "Ferrari Brand New", "Tshs.3,213,000", "12 hours", "Iringa"));
        _wishlistModelList.add(new WishlistModel(R.drawable.banner5, "WALMAT GABBLE HOME", "Tshs.10,000,000", "30 minutes", "Mbeya"));
        _wishlistModelList.add(new WishlistModel(R.drawable.banner6, "Iphone X 23GB", "Tshs.132,000", "15/05/20", "Ruvuma"));
        _wishlistModelList.add(new WishlistModel(R.drawable.banner6, "Iphone X 23GB", "Tshs.132,000", "15/05/20", "Ruvuma"));
        _wishlistModelList.add(new WishlistModel(R.drawable.banner7, "Lottery Tickets DL", "Tshs.54,000", "24 hours", "Dodoma"));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
