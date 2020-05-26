package com.kitaa.startup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kitaa.R;
import com.kitaa.startup.adapters.CategoryAdapter;
import com.kitaa.startup.adapters.HomePageAdapter;

import static com.kitaa.startup.database.DBqueries._categoryModelList;
import static com.kitaa.startup.database.DBqueries._homePageModelList;
import static com.kitaa.startup.database.DBqueries.loadCategories;
import static com.kitaa.startup.database.DBqueries.loadFragmentData;

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

        //Category view
        _categoryRecyclerview = _view.findViewById(R.id.category_rv);
        initCategoryRecyclerview();

        //Homepage view
        _homepageRecyclerview = _view.findViewById(R.id.home_page_recyclerview);
        initHomepageRecyclerview();

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
        _homePageAdapter = new HomePageAdapter(_homePageModelList);
        _homepageRecyclerview.setAdapter(_homePageAdapter);

        if(_homePageModelList.size() == 0)
        {
            loadFragmentData(_homePageAdapter, getContext());
        }
        else
        {
            _homePageAdapter.notifyDataSetChanged();
        }
    }

    /////Homepage Recyclerview

}
