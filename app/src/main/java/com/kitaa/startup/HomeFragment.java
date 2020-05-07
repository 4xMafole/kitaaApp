package com.kitaa.startup;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kitaa.R;
import com.kitaa.startup.adapters.CategoryAdapter;
import com.kitaa.startup.adapters.HorizontalScrollProductAdapter;
import com.kitaa.startup.adapters.SliderAdapter;
import com.kitaa.startup.models.CategoryModel;
import com.kitaa.startup.models.HorizontalScrollProductModel;
import com.kitaa.startup.models.SliderModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment
{
    /////Banner fields
    private ViewPager _bannerSlider;
    private List<SliderModel> _sliderModelList;
    private int _currentPage = 1;
    private Timer _timer;
    final private long DELAY_TIME = 3000;
    final private long PERIOD_TIME = 3000;
    /////Banner fields

    /////Strip add fields
    private ImageView _stripAdImage;
    private ConstraintLayout _stripAdContainer;
    /////Strip add fields

    ///// Category fields
    private RecyclerView _categoryRecyclerview;
    private CategoryAdapter _categoryAdapter;
    List<CategoryModel> _categoryModelList;
    ///// Category fields

    ///// Horizontal scroll product fields
    private TextView _horizontalProductLayoutTitle;
    private Button _horizontalProductLayoutButton;
    private RecyclerView _horizontalProductRecyclerview;
    private List<HorizontalScrollProductModel> _horizontalScrollProductModelList;
    ///// Horizontal scroll product fields


    public HomeFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View _view =  inflater.inflate(R.layout.fragment_home, container, false);

        _categoryRecyclerview = _view.findViewById(R.id.category_rv);

        LinearLayoutManager _layoutManager = new LinearLayoutManager(getActivity());
        _layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        _categoryRecyclerview.setLayoutManager(_layoutManager);
        prepareCategoryData();

        _bannerSlider = _view.findViewById(R.id.banner_slider_view_pager);
        prepareBannerData();

        _stripAdImage = _view.findViewById(R.id.strip_ad_banner);
        _stripAdContainer = _view.findViewById(R.id.strip_ad_container);
        _stripAdImage.setImageResource(R.drawable.strip_add);

        /////Horizontal Scroll Product
        _horizontalProductLayoutTitle = _view.findViewById(R.id.horizontal_scroll_layout_title);
        _horizontalProductLayoutButton = _view.findViewById(R.id.horizontal_scroll_layout_button);
        _horizontalProductRecyclerview = _view.findViewById(R.id.horizontal_scroll_layout_recyclerview);
        prepareProductData();
        /////Horizontal Scroll Product


        return _view;
    }

    private void prepareProductData()
    {
        productData();
        HorizontalScrollProductAdapter _horizontalScrollProductAdapter = new HorizontalScrollProductAdapter(_horizontalScrollProductModelList);
        LinearLayoutManager _layoutManager = new LinearLayoutManager(getContext());
        _layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        _horizontalProductRecyclerview.setLayoutManager(_layoutManager);

        _horizontalProductRecyclerview.setAdapter(_horizontalScrollProductAdapter);
        _horizontalScrollProductAdapter.notifyDataSetChanged();
    }

    private void productData()
    {
        _horizontalScrollProductModelList = new ArrayList<HorizontalScrollProductModel>();
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone4, "Iphone X", "SD 234 Processors", "Tshs.10,000,000/="));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone, "Techno S8", "2 Core Processors", "Tshs.700,000/="));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone4, "Iphone X", "SD 234 Processors", "Tshs.10,000,000/="));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone4, "Iphone X", "SD 234 Processors", "Tshs.10,000,000/="));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone2, "Huawei P20", "CBC 10-2 Processors", "Tshs.800,000/="));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone3, "Vivo 20T", "CBC 10-2 Processors", "Tshs.400,000/="));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone4, "Iphone X", "SD 234 Processors", "Tshs.10,000,000/="));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone2, "Huawei P20", "CBC 10-2 Processors", "Tshs.500,000/="));
        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone, "Techno S8", "2 Core Processors", "Tshs.700,000/="));
    }

    @SuppressLint("ClickableViewAccessibility")
    private void prepareBannerData()
    {
        bannerData();
        SliderAdapter _sliderAdapter = new SliderAdapter(_sliderModelList);
        _bannerSlider.setAdapter(_sliderAdapter);
        _bannerSlider.setClipToPadding(false);
        _bannerSlider.setPageMargin(20);
        _bannerSlider.setCurrentItem(_currentPage);

        ViewPager.OnPageChangeListener _onPageChangeListener = new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                _currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {
                if(state == ViewPager.SCROLL_STATE_IDLE)
                {
                    pageLooper();
                }
            }
        };

        _bannerSlider.addOnPageChangeListener(_onPageChangeListener);
        startBannerSlideShow();

        _bannerSlider.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                pageLooper();
                stopBannerSlideShow();
                if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    startBannerSlideShow();
                }
                return false;
            }
        });

    }

    private void bannerData()
    {
        ///todo: Admin adds user's own banner to promote a work public
        _sliderModelList = new ArrayList<SliderModel>();

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
    }

    private void pageLooper()
    {
        if(_currentPage == _sliderModelList.size() - 1)
        {
            _currentPage = 1;
            _bannerSlider.setCurrentItem(_currentPage,false);
        }
        if(_currentPage == 0)
        {
            _currentPage = _sliderModelList.size() - 2;
            _bannerSlider.setCurrentItem(_currentPage, false);

        }
    }

    private void startBannerSlideShow()
    {
        final Handler _handler = new Handler();
        final Runnable update = new Runnable()
        {
            @Override
            public void run()
            {
                if(_currentPage >= _sliderModelList.size())
                {
                    _currentPage = 0;
                }
                _bannerSlider.setCurrentItem(_currentPage++, true);
            }
        };

        _timer = new Timer();
        _timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                _handler.post(update);
            }
        }, DELAY_TIME, PERIOD_TIME);
    }

    private void stopBannerSlideShow()
    {
        _timer.cancel();
    }


    private void prepareCategoryData()
    {
        categoryData();
        _categoryAdapter = new CategoryAdapter(_categoryModelList);
        _categoryRecyclerview.setAdapter(_categoryAdapter);
        _categoryAdapter.notifyDataSetChanged();
    }

    private void categoryData()
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
}
