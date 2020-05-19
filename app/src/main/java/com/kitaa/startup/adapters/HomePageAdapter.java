package com.kitaa.startup.adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.kitaa.R;
import com.kitaa.startup.ViewAllActivity;
import com.kitaa.startup.models.HomePageModel;
import com.kitaa.startup.models.HorizontalScrollProductModel;
import com.kitaa.startup.models.SliderModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageAdapter extends RecyclerView.Adapter
{
    private List<HomePageModel> _homePageModelList;

    public HomePageAdapter(List<HomePageModel> homePageModelList)
    {
        _homePageModelList = homePageModelList;
    }

    @Override
    public int getItemViewType(int position)
    {
        switch(_homePageModelList.get(position).getType())
        {
            case 0:
                return HomePageModel.BANNER_SLIDER;
            case 1:
                return HomePageModel.STRIP_AD_BANNER;
            case 2:
                return HomePageModel.HORIZONTAL_PRODUCT_VIEW;
            case 3:
                return HomePageModel.GRID_PRODUCT_VIEW;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        switch(viewType)
        {
            case HomePageModel.BANNER_SLIDER:
                View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sliding_ad_banner, parent, false);
                return new BannerSliderViewHolder(_view);
            case HomePageModel.STRIP_AD_BANNER:
                View _viewStripped = LayoutInflater.from(parent.getContext()).inflate(R.layout.strip_ad_layout, parent, false);
                return new StripAdViewHolder(_viewStripped);
            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                View _viewHorizontalProduct = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_layout, parent, false);
                return new HorizontalScrollViewHolder(_viewHorizontalProduct);
            case HomePageModel.GRID_PRODUCT_VIEW:
                View _viewGridProduct = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_product_layout, parent, false);
                return new GridProductViewHolder(_viewGridProduct);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        switch(_homePageModelList.get(position).getType())
        {
            case HomePageModel.BANNER_SLIDER:
                List<SliderModel> _sliderModelList = _homePageModelList.get(position).getSliderModelList();
                ((BannerSliderViewHolder) holder).setBannerSliderViewPager(_sliderModelList);
                break;
            case HomePageModel.STRIP_AD_BANNER:
                int resource = _homePageModelList.get(position).getResource();
                ((StripAdViewHolder) holder).setStripAd(resource);
                break;
            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                String horizotalTitle = _homePageModelList.get(position).getTitle();
                List<HorizontalScrollProductModel> _horizontalScrollProductModelList = _homePageModelList.get(position).getHorizontalScrollProductModelList();
                ((HorizontalScrollViewHolder) holder).setHorizontalProductLayout(horizotalTitle, _horizontalScrollProductModelList);
                break;
            case HomePageModel.GRID_PRODUCT_VIEW:
                String gridTitle = _homePageModelList.get(position).getTitle();
                List<HorizontalScrollProductModel> gridProductModelList = _homePageModelList.get(position).getHorizontalScrollProductModelList();
                ((GridProductViewHolder) holder).setGridProductLayout(gridTitle, gridProductModelList);
                break;
            default:
                return;
        }
    }

    @Override
    public int getItemCount()
    {
        return _homePageModelList.size();
    }


    /////Recycler ViewHolder
    public class BannerSliderViewHolder extends RecyclerView.ViewHolder
    {
        final private long DELAY_TIME = 3000;
        final private long PERIOD_TIME = 3000;
        /////Banner fields
        private ViewPager _bannerSliderViewPager;
        private int _currentPage = 1;
        private Timer _timer;
        /////Banner fields

        public BannerSliderViewHolder(@NonNull View itemView)
        {
            super(itemView);
            _bannerSliderViewPager = itemView.findViewById(R.id.banner_slider_view_pager);
        }

        /////Banner slider methods
        @SuppressLint("ClickableViewAccessibility")
        private void setBannerSliderViewPager(final List<SliderModel> _sliderModelList)
        {
            bannerData(_sliderModelList);
            SliderAdapter _sliderAdapter = new SliderAdapter(_sliderModelList);
            _bannerSliderViewPager.setAdapter(_sliderAdapter);
            _bannerSliderViewPager.setClipToPadding(false);
            _bannerSliderViewPager.setPageMargin(20);
            _bannerSliderViewPager.setCurrentItem(_currentPage);

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
                        pageLooper(_sliderModelList);
                    }
                }
            };

            _bannerSliderViewPager.addOnPageChangeListener(_onPageChangeListener);
            startBannerSlideShow(_sliderModelList);

            _bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener()
            {
                @Override
                public boolean onTouch(View v, MotionEvent event)
                {
                    pageLooper(_sliderModelList);
                    stopBannerSlideShow();
                    if(event.getAction() == MotionEvent.ACTION_UP)
                    {
                        startBannerSlideShow(_sliderModelList);
                    }
                    return false;
                }
            });

        }

        private void bannerData(List<SliderModel> _sliderModelList)
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

        private void pageLooper(List<SliderModel> _sliderModelList)
        {
            if(_currentPage == _sliderModelList.size() - 1)
            {
                _currentPage = 1;
                _bannerSliderViewPager.setCurrentItem(_currentPage, false);
            }
            if(_currentPage == 0)
            {
                _currentPage = _sliderModelList.size() - 2;
                _bannerSliderViewPager.setCurrentItem(_currentPage, false);

            }
        }

        private void startBannerSlideShow(final List<SliderModel> _sliderModelList)
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
                    _bannerSliderViewPager.setCurrentItem(_currentPage++, true);
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
        /////Banner slider methods
    }

    public class StripAdViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView _stripAdImage;
        private ConstraintLayout _stripAdContainer;

        public StripAdViewHolder(@NonNull View itemView)
        {
            super(itemView);
            _stripAdImage = itemView.findViewById(R.id.strip_ad_banner);
            _stripAdContainer = itemView.findViewById(R.id.strip_ad_container);
        }

        private void setStripAd(int resource)
        {
            _stripAdImage.setImageResource(resource);
        }
    }

    private class HorizontalScrollViewHolder extends RecyclerView.ViewHolder
    {
        private TextView _horizontalProductLayoutTitle;
        private Button _horizontalProductLayoutButton;
        private RecyclerView _horizontalProductRecyclerview;
        private List<HorizontalScrollProductModel> _horizontalScrollProductModelList;

        public HorizontalScrollViewHolder(@NonNull View itemView)
        {
            super(itemView);
            _horizontalProductLayoutTitle = itemView.findViewById(R.id.horizontal_scroll_layout_title);
            _horizontalProductLayoutButton = itemView.findViewById(R.id.horizontal_scroll_layout_button);
            _horizontalProductRecyclerview = itemView.findViewById(R.id.horizontal_scroll_layout_recyclerview);
        }

        private void setHorizontalProductLayout(String title, List<HorizontalScrollProductModel> horizontalScrollProductModelList)
        {
            _horizontalProductLayoutTitle.setText(title);

            if(horizontalScrollProductModelList.size() > 8)
            {
                _horizontalProductLayoutButton.setVisibility(View.VISIBLE);
                _horizontalProductLayoutButton.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent viewAllIntent = new Intent(itemView.getContext(), ViewAllActivity.class);
                        viewAllIntent.putExtra("layout_code", 0);
                        itemView.getContext().startActivity(viewAllIntent);
                    }
                });
            }
            else
            {
                _horizontalProductLayoutButton.setVisibility(View.INVISIBLE);
            }

            HorizontalScrollProductAdapter _adapter = new HorizontalScrollProductAdapter(horizontalScrollProductModelList);
            LinearLayoutManager _linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            _linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            _horizontalProductRecyclerview.setLayoutManager(_linearLayoutManager);
            _horizontalProductRecyclerview.setAdapter(_adapter);
            _adapter.notifyDataSetChanged();

        }
    }

    private class GridProductViewHolder extends RecyclerView.ViewHolder
    {
        private TextView _gridLayoutTitle;
        private Button _gridLayoutButton;
        private GridView _gridLayoutGridview;

        public GridProductViewHolder(View itemView)
        {
            super(itemView);
            _gridLayoutTitle = itemView.findViewById(R.id.grid_product_layout_title);
            _gridLayoutButton = itemView.findViewById(R.id.grid_product_layout_button);
            _gridLayoutGridview = itemView.findViewById(R.id.grid_product_layout_gridview);
        }

        private void setGridProductLayout(String title, List<HorizontalScrollProductModel> horizontalScrollProductModelList)
        {
            _gridLayoutTitle.setText(title);
            _gridLayoutGridview.setAdapter(new GridProductLayoutAdapter(horizontalScrollProductModelList));
            _gridLayoutButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent viewAllIntent = new Intent(itemView.getContext(), ViewAllActivity.class);
                    viewAllIntent.putExtra("layout_code", 1);
                    itemView.getContext().startActivity(viewAllIntent);
                }
            });
        }

    }
    /////Recycler ViewHolder
}
