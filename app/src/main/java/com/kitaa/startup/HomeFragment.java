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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.kitaa.R;
import com.kitaa.startup.adapters.CategoryAdapter;
import com.kitaa.startup.adapters.HomePageAdapter;
import com.kitaa.startup.models.CategoryModel;
import com.kitaa.startup.models.HomePageModel;
import com.kitaa.startup.models.HorizontalScrollProductModel;
import com.kitaa.startup.models.SliderModel;
import com.kitaa.startup.models.WishlistModel;

import java.util.ArrayList;
import java.util.List;
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

    /////Refresh fragment
    public static SwipeRefreshLayout _refreshLayout;
    private ConnectivityManager _connectivityManager;
    private NetworkInfo _networkInfo;

    private List<CategoryModel> _categoryFakeModelList = new ArrayList<>();
    private List<HomePageModel> _homePageFakeModelList = new ArrayList<>();

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
        _refreshLayout = _view.findViewById(R.id.refresh_layout);


        checkConnection();
        refreshHomeLayout();

        return _view;
    }

    private void checkConnection()
    {
        _connectivityManager = (ConnectivityManager) requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        _networkInfo = Objects.requireNonNull(_connectivityManager).getActiveNetworkInfo();
        if(_networkInfo != null && _networkInfo.isConnected())
        {
            _noInternetConnection.setVisibility(View.GONE);
            _networkError.setVisibility(View.GONE);

            _categoryRecyclerview.setVisibility(View.VISIBLE);
            _homepageRecyclerview.setVisibility(View.VISIBLE);

            initCategoryRecyclerview();
            initHomepageRecyclerview();
        }
        else
        {
            _categoryRecyclerview.setVisibility(View.GONE);
            _homepageRecyclerview.setVisibility(View.GONE);

            Glide.with(this).load(R.drawable.no_internet_connection).into(_noInternetConnection);

            _noInternetConnection.setVisibility(View.VISIBLE);
            _networkError.setVisibility(View.VISIBLE);
        }
    }

    private void refreshHomeLayout()
    {
        _refreshLayout.setColorSchemeColors(requireContext().getResources().getColor(R.color.colorAccent), requireContext().getResources().getColor(R.color.colorAccent), requireContext().getResources().getColor(R.color.colorAccent));

        _refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                _refreshLayout.setRefreshing(true);
                _networkInfo = Objects.requireNonNull(_connectivityManager).getActiveNetworkInfo();
                _categoryModelList.clear();
                lists.clear();
                loadedCategoriesNames.clear();

                if(_networkInfo != null && _networkInfo.isConnected())
                {
                    _noInternetConnection.setVisibility(View.GONE);
                    _networkError.setVisibility(View.GONE);

                    _categoryRecyclerview.setVisibility(View.VISIBLE);
                    _homepageRecyclerview.setVisibility(View.VISIBLE);

                    _categoryAdapter = new CategoryAdapter(_categoryFakeModelList);
                    _homePageAdapter = new HomePageAdapter(_homePageFakeModelList);
                    _categoryRecyclerview.setAdapter(_categoryAdapter);
                    _homepageRecyclerview.setAdapter(_homePageAdapter);


                    loadCategories(_categoryRecyclerview, getContext());

                    loadedCategoriesNames.add("HOME");
                    lists.add(new ArrayList<HomePageModel>());
                    loadFragmentData(_homepageRecyclerview, getContext(), 0, "Home");

                }
                else
                {
                    Glide.with(requireContext()).load(R.drawable.no_internet_connection).into(_noInternetConnection);
                    _categoryRecyclerview.setVisibility(View.GONE);
                    _homepageRecyclerview.setVisibility(View.GONE);

                    _noInternetConnection.setVisibility(View.VISIBLE);
                    _networkError.setVisibility(View.VISIBLE);

                    _refreshLayout.setRefreshing(false);
                }
            }
        });
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
        categoryFakeDataList();
        _categoryAdapter = new CategoryAdapter(_categoryFakeModelList);

        if(_categoryModelList.size() == 0)
        {
            loadCategories(_categoryRecyclerview, getContext());
        }
        else
        {
            _categoryAdapter = new CategoryAdapter(_categoryModelList);
            _categoryAdapter.notifyDataSetChanged();
        }
        _categoryRecyclerview.setAdapter(_categoryAdapter);
    }

    private void categoryFakeDataList()
    {
        _categoryFakeModelList.add(new CategoryModel("", ""));
        _categoryFakeModelList.add(new CategoryModel("", ""));
        _categoryFakeModelList.add(new CategoryModel("", ""));
        _categoryFakeModelList.add(new CategoryModel("", ""));
        _categoryFakeModelList.add(new CategoryModel("", ""));
        _categoryFakeModelList.add(new CategoryModel("", ""));
        _categoryFakeModelList.add(new CategoryModel("", ""));
        _categoryFakeModelList.add(new CategoryModel("", ""));
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
        homeFakePageDataList();
        _homePageAdapter = new HomePageAdapter(_homePageFakeModelList);

        if(lists.size() == 0)
        {
            loadedCategoriesNames.add("HOME");
            lists.add(new ArrayList<HomePageModel>());
            loadFragmentData(_homepageRecyclerview, getContext(), 0, "Home");
        }
        else
        {
            _homePageAdapter = new HomePageAdapter(lists.get(0));
            _homePageAdapter.notifyDataSetChanged();
        }
        _homepageRecyclerview.setAdapter(_homePageAdapter);
    }

    private void homeFakePageDataList()
    {
        List<SliderModel> _sliderModelList = new ArrayList<>();
        _sliderModelList.add(new SliderModel(null));
        _sliderModelList.add(new SliderModel(null));
        _sliderModelList.add(new SliderModel(null));
        _sliderModelList.add(new SliderModel(null));
        _sliderModelList.add(new SliderModel(null));

        List<HorizontalScrollProductModel> _horizontalScrollProductModelList = new ArrayList<>();
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel("", "", "", "", ""));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel("", "", "", "", ""));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel("", "", "", "", ""));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel("", "", "", "", ""));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel("", "", "", "", ""));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel("", "", "", "", ""));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel("", "", "", "", ""));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel("", "", "", "", ""));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel("", "", "", "", ""));

        _homePageFakeModelList.add(new HomePageModel(0, _sliderModelList));
        _homePageFakeModelList.add(new HomePageModel(1, ""));
        _homePageFakeModelList.add(new HomePageModel(3, "Trending Deals", _horizontalScrollProductModelList, new ArrayList<WishlistModel>()));
        _homePageFakeModelList.add(new HomePageModel(2, "#Latest", _horizontalScrollProductModelList, new ArrayList<WishlistModel>()));
    }

    /////Homepage Recyclerview

}
