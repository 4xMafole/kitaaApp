package com.kitaa.startup.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.kitaa.startup.ProductDescriptionFragment;
import com.kitaa.startup.ProductSafetyFragment;
import com.kitaa.startup.ProductSpecificationFragment;

public class ProductDetailsAdapter extends FragmentPagerAdapter
{
    private int totalTabs;

    public ProductDetailsAdapter(@NonNull FragmentManager fm, int totalTabs)
    {
        super(fm);
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        switch(position)
        {
            case 0:
                ProductDescriptionFragment _productDescriptionFragment = new ProductDescriptionFragment();
                return _productDescriptionFragment;
            case 1:
                ProductSpecificationFragment _productSpecificationFragment = new ProductSpecificationFragment();
                return _productSpecificationFragment;
            case 2:
                //todo: Change this safety tab to have safety measures!

                ProductSafetyFragment _productSafetyFragment = new ProductSafetyFragment();
                return _productSafetyFragment;
            default:
                return null;

        }
    }

    @Override
    public int getCount()
    {
        return totalTabs;
    }
}
