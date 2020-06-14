package com.kitaa.startup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kitaa.R;
import com.kitaa.startup.adapters.ProductSpecificationAdapter;
import com.kitaa.startup.models.ProductSpecificationModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductSpecificationFragment extends Fragment
{

    private RecyclerView _productSpecificationRecyclerview;
    public List<ProductSpecificationModel> _productSpecificationModelList;

    public ProductSpecificationFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View _view = inflater.inflate(R.layout.fragment_product_specification, container, false);

        _productSpecificationRecyclerview = _view.findViewById(R.id.product_specification_recyclerview);
        initProductSpecificationRecyclerview(_view);

        return _view;

    }

    private void initProductSpecificationRecyclerview(View _view)
    {
        LinearLayoutManager _linearLayoutManager = new LinearLayoutManager(_view.getContext());
        _linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        _productSpecificationRecyclerview.setLayoutManager(_linearLayoutManager);
        prepareProductSpecificationData();
    }

    private void prepareProductSpecificationData()
    {
        ProductSpecificationAdapter _productSpecificationAdapter = new ProductSpecificationAdapter(_productSpecificationModelList);
        _productSpecificationRecyclerview.setAdapter(_productSpecificationAdapter);
        _productSpecificationAdapter.notifyDataSetChanged();
    }

}
