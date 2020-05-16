package com.kitaa.startup.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kitaa.R;
import com.kitaa.startup.models.ProductSpecificationModel;

import java.util.List;

public class ProductSpecificationAdapter extends RecyclerView.Adapter<ProductSpecificationAdapter.ViewHolder>
{
    private List<ProductSpecificationModel> _productSpecificationModelList;

    public ProductSpecificationAdapter(List<ProductSpecificationModel> productSpecificationModelList)
    {
        _productSpecificationModelList = productSpecificationModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_specification_item_layout, parent, false);

        return new ViewHolder(_view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        String featureTitle = _productSpecificationModelList.get(position).getFeatureName();
        String featureDetail = _productSpecificationModelList.get(position).getFeatureValue();
        holder.setFeatures(featureTitle, featureDetail);

    }

    @Override
    public int getItemCount()
    {
        return _productSpecificationModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView featureName;
        private TextView featureValue;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            featureName = itemView.findViewById(R.id.feature_name);
            featureValue = itemView.findViewById(R.id.feature_value);

        }

        private void setFeatures(String featureTitle, String featureDetail)
        {
            featureName.setText(featureTitle);
            featureValue.setText(featureDetail);

        }
    }
}
