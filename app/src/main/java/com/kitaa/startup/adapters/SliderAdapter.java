package com.kitaa.startup.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kitaa.R;
import com.kitaa.startup.models.SliderModel;

import java.util.List;

public class SliderAdapter extends PagerAdapter
{
    private List<SliderModel> _sliderModelList;

    public SliderAdapter(List<SliderModel> sliderModelList)
    {
        _sliderModelList = sliderModelList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position)
    {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.slider_layout, container, false);
        ImageView banner = view.findViewById(R.id.banner_slider);
        banner.setClipToOutline(true);
        Glide.with(container.getContext()).load(_sliderModelList.get(position).getBanner()).apply(new RequestOptions().placeholder(R.drawable.slider_background)).into(banner);
        container.addView(view, 0);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object)
    {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object)
    {
        container.removeView((View) object);
    }

    @Override
    public int getCount()
    {
        return _sliderModelList.size();
    }
}
