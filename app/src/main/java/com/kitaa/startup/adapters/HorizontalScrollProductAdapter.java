package com.kitaa.startup.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kitaa.R;
import com.kitaa.startup.ProductDetailsActivity;
import com.kitaa.startup.models.HorizontalScrollProductModel;

import java.util.List;

public class HorizontalScrollProductAdapter extends RecyclerView.Adapter<HorizontalScrollProductAdapter.ViewHolder>
{
    private List<HorizontalScrollProductModel> _horizontalScrollProductModelList;

    public HorizontalScrollProductAdapter(List<HorizontalScrollProductModel> horizontalScrollProductModelList)
    {
        _horizontalScrollProductModelList = horizontalScrollProductModelList;
    }

    @NonNull
    @Override
    public HorizontalScrollProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item, parent, false);


        return new ViewHolder(_view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalScrollProductAdapter.ViewHolder holder, int position)
    {
        int resource = _horizontalScrollProductModelList.get(position).getProductPhoto();
        String title = _horizontalScrollProductModelList.get(position).getProductTitle();
        String description = _horizontalScrollProductModelList.get(position).getProductDescription();
        String price = _horizontalScrollProductModelList.get(position).getProductPrice();

        holder.setProductPhoto(resource);
        holder.setProductTitle(title);
        holder.setProductDescription(description);
        holder.setProductPrice(price);

    }

    @Override
    public int getItemCount()
    {
        if(_horizontalScrollProductModelList.size() > 8)
        {
            return 8;
        }
        else
        {
            return _horizontalScrollProductModelList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView productPhoto;
        private TextView productTitle;
        private TextView productDescription;
        private TextView productPrice;

        public ViewHolder(@NonNull final View itemView)
        {
            super(itemView);
            productPhoto = itemView.findViewById(R.id.h_s_product_photo);
            productTitle = itemView.findViewById(R.id.h_s_product_title);
            productDescription = itemView.findViewById(R.id.h_s_product_description);
            productPrice = itemView.findViewById(R.id.h_s_product_price);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent _productIntentDetails = new Intent(itemView.getContext(), ProductDetailsActivity.class);
                    itemView.getContext().startActivity(_productIntentDetails);
                }
            });
        }

        private void setProductPhoto(int resource)
        {
            productPhoto.setImageResource(resource);
        }

        private void setProductTitle(String title)
        {
            productTitle.setText(title);
        }

        private void setProductDescription(String description)
        {
            productDescription.setText(description);
        }

        private void setProductPrice(String price)
        {
            productPrice.setText(price);
        }
    }
}
