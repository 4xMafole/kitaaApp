package com.kitaa.startup;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kitaa.R;
import com.kitaa.startup.adapters.CategoryAdapter;
import com.kitaa.startup.adapters.HomePageAdapter;
import com.kitaa.startup.models.CategoryModel;
import com.kitaa.startup.models.HomePageModel;
import com.kitaa.startup.models.HorizontalScrollProductModel;
import com.kitaa.startup.models.SliderModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment
{
    private List<CategoryModel> _categoryModelList;

    ///// Category fields
    private RecyclerView _categoryRecyclerview;
    ///// Category fields
    /////Home Page fields
    private RecyclerView _homepageRecyclerview;

    public HomeFragment()
    {
        // Required empty public constructor
    }
    private List<HomePageModel> _homePageModelList;

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
        categoryDataList();
        CategoryAdapter _categoryAdapter = new CategoryAdapter(_categoryModelList);
        _categoryRecyclerview.setAdapter(_categoryAdapter);
        _categoryAdapter.notifyDataSetChanged();
    }

    private void categoryDataList()
    {
        _categoryModelList = new ArrayList<CategoryModel>();
        _categoryModelList.add(new CategoryModel("Link", "Home"));
        _categoryModelList.add(new CategoryModel("Link", "Electronics"));
        _categoryModelList.add(new CategoryModel("Link", "Appliances"));
        _categoryModelList.add(new CategoryModel("Link", "Furniture"));
        _categoryModelList.add(new CategoryModel("Link", "Books"));
        _categoryModelList.add(new CategoryModel("Link", "Lifestyle"));
        _categoryModelList.add(new CategoryModel("Link", "Fashion"));
        _categoryModelList.add(new CategoryModel("Link", "More"));
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
        homePageDataList();
        HomePageAdapter _homePageAdapter = new HomePageAdapter(_homePageModelList);
        _homepageRecyclerview.setAdapter(_homePageAdapter);
        _homePageAdapter.notifyDataSetChanged();
    }

    private void homePageDataList()
    {
        _homePageModelList = new ArrayList<HomePageModel>();
        _homePageModelList.add(new HomePageModel(0, bannerDataList()));
        _homePageModelList.add(new HomePageModel(1, R.drawable.strip_add));
        _homePageModelList.add(new HomePageModel(2, "# Latest Electronics", horizontalProductDataList()));
        _homePageModelList.add(new HomePageModel(3, "New In Town", horizontalProductDataList()));
        _homePageModelList.add(new HomePageModel(1, R.drawable.strip_add));
        _homePageModelList.add(new HomePageModel(3, "# Trending", horizontalProductDataList()));
        _homePageModelList.add(new HomePageModel(2, "Deals of the Day", horizontalProductDataList()));
    }
    /////Homepage Recyclerview

    private List<HorizontalScrollProductModel> horizontalProductDataList()
    {
        List<HorizontalScrollProductModel> _horizontalScrollProductModelList = new ArrayList<>();
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone4, "Iphone X", "SD 234 Processors", "Tshs.10,000,000/="));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone, "Techno S8", "2 Core Processors", "Tshs.700,000/="));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone4, "Iphone X", "SD 234 Processors", "Tshs.10,000,000/="));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone4, "Iphone X", "SD 234 Processors", "Tshs.10,000,000/="));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone2, "Huawei P20", "CBC 10-2 Processors", "Tshs.800,000/="));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone3, "Vivo 20T", "CBC 10-2 Processors", "Tshs.400,000/="));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone4, "Iphone X", "SD 234 Processors", "Tshs.10,000,000/="));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone, "Techno S8", "2 Core Processors", "Tshs.700,000/="));

        return _horizontalScrollProductModelList;
    }

    private List<SliderModel> bannerDataList()
    {
        ///todo: Admin adds user's own banner to promote a work public
        List<SliderModel> _sliderModelList = new ArrayList<SliderModel>();

        _sliderModelList.add(new SliderModel(R.drawable.banner7));
        _sliderModelList.add(new SliderModel(R.drawable.banner1));

        _sliderModelList.add(new SliderModel(R.drawable.banner2));
        _sliderModelList.add(new SliderModel(R.drawable.banner));
        _sliderModelList.add(new SliderModel(R.drawable.banner4));
        _sliderModelList.add(new SliderModel(R.drawable.banner5));
        _sliderModelList.add(new SliderModel(R.drawable.banner6));
        _sliderModelList.add(new SliderModel(R.drawable.banner10));
        _sliderModelList.add(new SliderModel(R.drawable.banner9));
        _sliderModelList.add(new SliderModel(R.drawable.banner11));

        _sliderModelList.add(new SliderModel(R.drawable.banner7));
        _sliderModelList.add(new SliderModel(R.drawable.banner1));

        return _sliderModelList;
    }

}
