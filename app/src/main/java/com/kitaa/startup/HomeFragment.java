package com.kitaa.startup;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kitaa.R;
import com.kitaa.startup.adapters.CategoryAdapter;
import com.kitaa.startup.adapters.HomePageAdapter;
import com.kitaa.startup.models.HomePageModel;

import java.util.ArrayList;
import java.util.Objects;

import static com.kitaa.startup.database.DBqueries._categoryModelList;
import static com.kitaa.startup.database.DBqueries.lists;
import static com.kitaa.startup.database.DBqueries.loadCategories;
import static com.kitaa.startup.database.DBqueries.loadFragmentData;
import static com.kitaa.startup.database.DBqueries.loadedCategoriesNames;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment
{
    ///// Category fields
    private RecyclerView _categoryRecyclerview;
    ///// Category fields

    /////Home Page fields
    private RecyclerView _homepageRecyclerview;
    private CategoryAdapter _categoryAdapter;
    private HomePageAdapter _homePageAdapter;
    private ImageView _noInternetConnection;
    private TextView _networkError;

    public HomeFragment()
    {
        // Required empty public constructor
    }

    /////Home Page fields

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View _view =  inflater.inflate(R.layout.fragment_home, container, false);

        _categoryRecyclerview = _view.findViewById(R.id.category_rv);
        _homepageRecyclerview = _view.findViewById(R.id.home_page_recyclerview);
        _noInternetConnection = _view.findViewById(R.id.no_internet_connection);
        _networkError = _view.findViewById(R.id.network_error);

        ConnectivityManager _connectivityManager = (ConnectivityManager) requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo _networkInfo = Objects.requireNonNull(_connectivityManager).getActiveNetworkInfo();
        if(_networkInfo != null && _networkInfo.isConnected())
        {
            _noInternetConnection.setVisibility(View.GONE);
            _networkError.setVisibility(View.GONE);

            initCategoryRecyclerview();
            initHomepageRecyclerview();
        }
        else
        {
            //todo: this image should be changed to reflect no internet connection.
            Glide.with(this).load(R.drawable.no_internet_connection).into(_noInternetConnection);
            _noInternetConnection.setVisibility(View.VISIBLE);
            _networkError.setVisibility(View.VISIBLE);
        }

        //Category view

        //Homepage view

        return _view;
    }


    /////Category Recyclerview
    private void initCategoryRecyclerview()
    {
        LinearLayoutManager _layoutManager = new LinearLayoutManager(getActivity());
        _layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        _categoryRecyclerview.setLayoutManager(_layoutManager);
        prepareCategoryData();
    }

    private void prepareCategoryData()
    {
        _categoryAdapter = new CategoryAdapter(_categoryModelList);
        _categoryRecyclerview.setAdapter(_categoryAdapter);

        if(_categoryModelList.size() == 0)
        {
            loadCategories(_categoryAdapter, getContext());
        }
        else
        {
            _categoryAdapter.notifyDataSetChanged();
        }
    }

    /////Category Recyclerview

    /////Homepage Recyclerview
    private void initHomepageRecyclerview()
    {
        LinearLayoutManager _linearLayoutManager = new LinearLayoutManager(getContext());
        _linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        _homepageRecyclerview.setLayoutManager(_linearLayoutManager);
        prepareHomePageData();

    }

    private void prepareHomePageData()
    {

        if(lists.size() == 0)
        {
            loadedCategoriesNames.add("HOME");
            lists.add(new ArrayList<HomePageModel>());
            _homePageAdapter = new HomePageAdapter(lists.get(0));
            loadFragmentData(_homePageAdapter, getContext(), 0, "Home");
        }
        else
        {
            _homePageAdapter = new HomePageAdapter(lists.get(0));
            _homePageAdapter.notifyDataSetChanged();
        }
        _homepageRecyclerview.setAdapter(_homePageAdapter);
    }

    /////Homepage Recyclerview

}
