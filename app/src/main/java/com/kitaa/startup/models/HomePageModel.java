package com.kitaa.startup.models;

import java.util.List;

public class HomePageModel
{
    public static final int BANNER_SLIDER = 0;
    public static final int STRIP_AD_BANNER = 1;
    public static final int HORIZONTAL_PRODUCT_VIEW = 2;
    public static final int GRID_PRODUCT_VIEW = 3;

    private int type;

    /////Banner Slider

    private List<SliderModel> _sliderModelList;
    private String resource;
    /////Horizontal Product layout and Grid Product layout
    private String title;
    private List<HorizontalScrollProductModel> _horizontalScrollProductModelList;

    public HomePageModel(int type, List<SliderModel> sliderModelList)
    {
        this.type = type;
        _sliderModelList = sliderModelList;
    }

    public HomePageModel(int type, String resource)
    {
        this.type = type;
        this.resource = resource;
    }

    /////Banner Slider

    /////Strip Ad Banner

    public HomePageModel(int type, String title, List<HorizontalScrollProductModel> horizontalScrollProductModelList)
    {
        this.type = type;
        this.title = title;
        _horizontalScrollProductModelList = horizontalScrollProductModelList;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public List<SliderModel> getSliderModelList()
    {
        return _sliderModelList;
    }

    /////Strip Ad Banner

    public void setSliderModelList(List<SliderModel> sliderModelList)
    {
        _sliderModelList = sliderModelList;
    }

    public String getResource()
    {
        return resource;
    }

    public void setResource(String resource)
    {
        this.resource = resource;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public List<HorizontalScrollProductModel> getHorizontalScrollProductModelList()
    {
        return _horizontalScrollProductModelList;
    }

    public void setHorizontalScrollProductModelList(List<HorizontalScrollProductModel> horizontalScrollProductModelList)
    {
        _horizontalScrollProductModelList = horizontalScrollProductModelList;
    }
    /////Horizontal Product layout and Grid Product Layout
}
