package com.kitaa.startup.adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kitaa.R;
import com.kitaa.startup.ProductDetailsActivity;
import com.kitaa.startup.models.HorizontalScrollProductModel;

import java.util.List;

public class GridProductLayoutAdapter extends BaseAdapter
{
    private List<HorizontalScrollProductModel> _horizontalScrollProductModelList;

    public GridProductLayoutAdapter(List<HorizontalScrollProductModel> horizontalScrollProductModelList)
    {
        _horizontalScrollProductModelList = horizontalScrollProductModelList;
    }

    @Override
    public int getCount()
    {
        return _horizontalScrollProductModelList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, final ViewGroup parent)
    {
        View _view;
        ImageView productPhoto;
        TextView productTitle;
        TextView productDescription;
        TextView productPrice;

        if(convertView == null)
        {
            _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item, null);
            _view.setElevation(0);

            _view.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent productDetailsIntent = new Intent(parent.getContext(), ProductDetailsActivity.class);
                    parent.getContext().startActivity(productDetailsIntent);

                }
            });

            productPhoto = _view.findViewById(R.id.h_s_product_photo);
            productTitle = _view.findViewById(R.id.h_s_product_title);
            productDescription = _view.findViewById(R.id.h_s_product_description);
            productPrice = _view.findViewById(R.id.h_s_product_price);

            Glide.with(parent.getContext()).load(_horizontalScrollProductModelList.get(position).getProductPhoto()).apply(new RequestOptions().placeholder(R.drawable.slider_background)).into(productPhoto);
            productTitle.setText(_horizontalScrollProductModelList.get(position).getProductTitle());
            productDescription.setText(_horizontalScrollProductModelList.get(position).getProductDescription());
            productPrice.setText("Tshs." + _horizontalScrollProductModelList.get(position).getProductPrice() + "/=");

        }
        else
        {
            _view = convertView;
        }

        return _view;
    }
}
