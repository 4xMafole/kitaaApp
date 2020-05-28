package com.kitaa.startup;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kitaa.R;
import com.kitaa.startup.adapters.GridProductLayoutAdapter;
import com.kitaa.startup.adapters.WishlistAdapter;
import com.kitaa.startup.models.HorizontalScrollProductModel;
import com.kitaa.startup.models.WishlistModel;

import java.util.List;
import java.util.Objects;

public class ViewAllActivity extends AppCompatActivity
{

    public static List<WishlistModel> _wishlistModelList;
    public static List<HorizontalScrollProductModel> _horizontalScrollProductModelList;
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
        String layout_title = getIntent().getStringExtra("title");

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(layout_title);
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
        GridProductLayoutAdapter _adapter = new GridProductLayoutAdapter(_horizontalScrollProductModelList);
        _viewAllGridView.setAdapter(_adapter);
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
        WishlistAdapter _adapter = new WishlistAdapter(_wishlistModelList);
        _viewAllRecyclerview.setAdapter(_adapter);
        _adapter.notifyDataSetChanged();
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
