package com.kitaa.startup.adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
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
        String resource = _horizontalScrollProductModelList.get(position).getProductPhoto();
        String title = _horizontalScrollProductModelList.get(position).getProductTitle();
        String description = _horizontalScrollProductModelList.get(position).getProductDescription();
        String price = _horizontalScrollProductModelList.get(position).getProductPrice();

        holder.setData(resource, title, description, price);

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

        }

        @SuppressLint("SetTextI18n")
        private void setData(String resource, String title, String description, String price)
        {
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.slider_background)).into(productPhoto);
            productTitle.setText(title);
            productDescription.setText(description);
            productPrice.setText("Tshs." + price + "/=");

            if(!title.equals(""))
            {
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
        }

    }
}
