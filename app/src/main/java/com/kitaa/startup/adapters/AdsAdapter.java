package com.kitaa.startup.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kitaa.R;
import com.kitaa.startup.models.WishlistModel;

import java.util.List;

public class AdsAdapter extends RecyclerView.Adapter<AdsAdapter.ViewHolder>
{
    private List<WishlistModel> _adsModelList;

    public AdsAdapter(List<WishlistModel> adsModelList)
    {
        _adsModelList = adsModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_item_layout, parent, false);

        return new ViewHolder(_view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        int resource = _adsModelList.get(position).getProductItemImage();
        String title = _adsModelList.get(position).getProductTitleDisplay();
        String price = _adsModelList.get(position).getProductPriceDisplay();
        String uploadTime = _adsModelList.get(position).getProductUploadTimeDisplay();
        String region = _adsModelList.get(position).getProductRegionDisplay();

        holder.setAdData(resource, title, price, uploadTime, region);
    }

    @Override
    public int getItemCount()
    {
        return _adsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView productItemImageAd;
        private TextView productTitleDisplayAd;
        private TextView productPriceDisplayAd;
        private TextView productUploadTimeDisplayAd;
        private TextView productRegionDisplayAd;
        private ImageView deleteAdButton;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            productItemImageAd = itemView.findViewById(R.id.product_item_image_ad);
            productTitleDisplayAd = itemView.findViewById(R.id.product_title_display_ad);
            productPriceDisplayAd = itemView.findViewById(R.id.product_price_display_ad);
            productUploadTimeDisplayAd = itemView.findViewById(R.id.product_upload_time_display_ad);
            productRegionDisplayAd = itemView.findViewById(R.id.product_region_display_ad);
            deleteAdButton = itemView.findViewById(R.id.delete_ad_button);
        }

        private void setAdData(int resource, String title, String price, String uploadTime, String region)
        {
            productItemImageAd.setImageResource(resource);
            productTitleDisplayAd.setText(title);
            productPriceDisplayAd.setText(price);
            productUploadTimeDisplayAd.setText(uploadTime);
            productRegionDisplayAd.setText(region);

            deleteAd();

        }

        private void deleteAd()
        {
            deleteAdButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(itemView.getContext(), "Ad deleted", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
