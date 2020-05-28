package com.kitaa.startup.adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kitaa.R;
import com.kitaa.startup.ProductDetailsActivity;
import com.kitaa.startup.ViewAllActivity;
import com.kitaa.startup.models.HomePageModel;
import com.kitaa.startup.models.HorizontalScrollProductModel;
import com.kitaa.startup.models.SliderModel;
import com.kitaa.startup.models.WishlistModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageAdapter extends RecyclerView.Adapter
{

    private List<HomePageModel> _homePageModelList;
    private RecyclerView.RecycledViewPool _recycledViewPool;

    public HomePageAdapter(List<HomePageModel> homePageModelList)
    {
        _homePageModelList = homePageModelList;
        _recycledViewPool = new RecyclerView.RecycledViewPool();
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
                String resource = _homePageModelList.get(position).getResource();
                ((StripAdViewHolder) holder).setStripAd(resource);
                break;
            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                String horizotalTitle = _homePageModelList.get(position).getTitle();
                List<WishlistModel> _viewAllProductList = _homePageModelList.get(position).getViewAllProductList();
                List<HorizontalScrollProductModel> _horizontalScrollProductModelList = _homePageModelList.get(position).getHorizontalScrollProductModelList();
                ((HorizontalScrollViewHolder) holder).setHorizontalProductLayout(horizotalTitle, _horizontalScrollProductModelList, _viewAllProductList);
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
        private int _currentPage;
        private Timer _timer;
        private List<SliderModel> _sliderModelListArrange;
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
            _currentPage = 1;

            if(_timer != null)
            {
                _timer.cancel();
            }

            _sliderModelListArrange = new ArrayList<>();
            for(int x = 0; x < _sliderModelList.size(); x++)
            {
                _sliderModelListArrange.add(x, _sliderModelList.get(x));
            }

            SliderAdapter _sliderAdapter = new SliderAdapter(_sliderModelListArrange);
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
                        pageLooper(_sliderModelListArrange);
                    }
                }
            };

            _bannerSliderViewPager.addOnPageChangeListener(_onPageChangeListener);
            startBannerSlideShow(_sliderModelListArrange);

            _bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener()
            {
                @Override
                public boolean onTouch(View v, MotionEvent event)
                {
                    pageLooper(_sliderModelListArrange);
                    stopBannerSlideShow();
                    if(event.getAction() == MotionEvent.ACTION_UP)
                    {
                        startBannerSlideShow(_sliderModelListArrange);
                    }
                    return false;
                }
            });

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

        private void setStripAd(String resource)
        {
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.ic_shopping_cart_24dp)).into(_stripAdImage);
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
            _horizontalProductRecyclerview.setRecycledViewPool(_recycledViewPool);
        }

        private void setHorizontalProductLayout(final String title, List<HorizontalScrollProductModel> horizontalScrollProductModelList, final List<WishlistModel> viewAllProductList)
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
                        ViewAllActivity._wishlistModelList = viewAllProductList;
                        Intent viewAllIntent = new Intent(itemView.getContext(), ViewAllActivity.class);
                        viewAllIntent.putExtra("layout_code", 0);
                        viewAllIntent.putExtra("title", title);
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
        private GridLayout _gridLayout;

        public GridProductViewHolder(View itemView)
        {
            super(itemView);
            _gridLayoutTitle = itemView.findViewById(R.id.grid_product_layout_title);
            _gridLayoutButton = itemView.findViewById(R.id.grid_product_layout_button);
            _gridLayout = itemView.findViewById(R.id.grid_product_layout_gridview);
        }

        @SuppressLint("SetTextI18n")
        private void setGridProductLayout(final String title, final List<HorizontalScrollProductModel> horizontalScrollProductModelList)
        {
            _gridLayoutTitle.setText(title);

            for(int x = 0; x < 4; x++)
            {
                ImageView productImage = _gridLayout.getChildAt(x).findViewById(R.id.h_s_product_photo);
                TextView productTitle = _gridLayout.getChildAt(x).findViewById(R.id.h_s_product_title);
                TextView productPrice = _gridLayout.getChildAt(x).findViewById(R.id.h_s_product_price);
                TextView productDescription = _gridLayout.getChildAt(x).findViewById(R.id.h_s_product_description);

                Glide.with(itemView.getContext()).load(horizontalScrollProductModelList.get(x).getProductPhoto()).apply(new RequestOptions().placeholder(R.drawable.ic_shopping_cart_24dp)).into(productImage);
                productTitle.setText(horizontalScrollProductModelList.get(x).getProductTitle());
                productDescription.setText(horizontalScrollProductModelList.get(x).getProductDescription());
                productPrice.setText("Tshs." + horizontalScrollProductModelList.get(x).getProductPrice() + "/=");

                _gridLayout.getChildAt(x).setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent productDetailsIntent = new Intent(itemView.getContext(), ProductDetailsActivity.class);
                        itemView.getContext().startActivity(productDetailsIntent);
                    }
                });
            }

            _gridLayoutButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    ViewAllActivity._horizontalScrollProductModelList = horizontalScrollProductModelList;
                    Intent viewAllIntent = new Intent(itemView.getContext(), ViewAllActivity.class);
                    viewAllIntent.putExtra("layout_code", 1);
                    viewAllIntent.putExtra("title", title);
                    itemView.getContext().startActivity(viewAllIntent);
                }
            });
        }

    }
    /////Recycler ViewHolder
}
