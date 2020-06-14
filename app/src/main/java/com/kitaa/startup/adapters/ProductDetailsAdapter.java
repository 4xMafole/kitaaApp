package com.kitaa.startup.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.kitaa.startup.ProductDescriptionFragment;
import com.kitaa.startup.ProductSafetyFragment;
import com.kitaa.startup.ProductSpecificationFragment;
import com.kitaa.startup.models.ProductSpecificationModel;

import java.util.List;

public class ProductDetailsAdapter extends FragmentPagerAdapter
{
    private int totalTabs;
    private String _productDescription;
    private List<ProductSpecificationModel> _productSpecificationModelList;

    public ProductDetailsAdapter(@NonNull FragmentManager fm, int totalTabs, String productDescription, List<ProductSpecificationModel> productSpecificationModelList)
    {
        super(fm);
        this.totalTabs = totalTabs;
        this._productDescription = productDescription;
        this._productSpecificationModelList = productSpecificationModelList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        switch(position)
        {
            case 0:
                ProductDescriptionFragment _productDescriptionFragment = new ProductDescriptionFragment();
                _productDescriptionFragment._body = _productDescription;
                return _productDescriptionFragment;
            case 1:
                ProductSpecificationFragment _productSpecificationFragment = new ProductSpecificationFragment();
                _productSpecificationFragment._productSpecificationModelList = _productSpecificationModelList;
                return _productSpecificationFragment;
            case 2:
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
