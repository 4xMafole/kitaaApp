package com.kitaa.startup.adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kitaa.R;
import com.kitaa.startup.ProductDetailsActivity;
import com.kitaa.startup.models.WishlistModel;

import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder>
{
    private List<WishlistModel> _wishlistModelList;
    private boolean wishlist;

    public WishlistAdapter(List<WishlistModel> wishlistModelList, boolean wishlist)
    {
        _wishlistModelList = wishlistModelList;
        this.wishlist = wishlist;
    }


    @NonNull
    @Override
    public WishlistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item_layout, parent, false);

        return new ViewHolder(_view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistAdapter.ViewHolder holder, int position)
    {
        int resource = _wishlistModelList.get(position).getProductItemImage();
        String title = _wishlistModelList.get(position).getProductTitleDisplay();
        String price = _wishlistModelList.get(position).getProductPriceDisplay();
        String uploadTime = _wishlistModelList.get(position).getProductUploadTimeDisplay();
        String region = _wishlistModelList.get(position).getProductRegionDisplay();

        holder.setData(resource, title, price, uploadTime, region);
    }

    @Override
    public int getItemCount()
    {
        return _wishlistModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView productItemImage;
        private TextView productTitleDisplay;
        private TextView productPriceDisplay;
        private TextView productUploadTimeDisplay;
        private TextView productRegionDisplay;
        private ImageView productOwnerCall;
        private ImageView productOwnerChat;
        private FloatingActionButton wishlistItemButton;

        private boolean ALREADY_IN_WISHLIST;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            productItemImage = itemView.findViewById(R.id.product_item_image);
            productTitleDisplay = itemView.findViewById(R.id.product_title_display);
            productPriceDisplay = itemView.findViewById(R.id.product_price_display);
            productUploadTimeDisplay = itemView.findViewById(R.id.product_upload_time_display);
            productRegionDisplay = itemView.findViewById(R.id.product_region_display);
            productOwnerCall = itemView.findViewById(R.id.product_owner_call);
            productOwnerChat = itemView.findViewById(R.id.product_owner_chat);
            wishlistItemButton = itemView.findViewById(R.id.wishlist_item_button);
        }

        private void setData(int resource, String title, String price, String uploadTime, String region)
        {
            productItemImage.setImageResource(resource);
            productTitleDisplay.setText(title);
            productPriceDisplay.setText(price);
            productUploadTimeDisplay.setText(uploadTime);
            productRegionDisplay.setText(region);

            if(wishlist)
            {
                wishlistItemButton.setVisibility(View.VISIBLE);
                toggleWishlistButton();
            }
            else
            {
                wishlistItemButton.setVisibility(View.GONE);
            }

            lookProduct();
            contactOwner();

        }

        private void lookProduct()
        {
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent showProductIntent = new Intent(itemView.getContext(), ProductDetailsActivity.class);
                    itemView.getContext().startActivity(showProductIntent);
                }
            });
        }

        private void contactOwner()
        {
            callProductOwnerButton();
            chatProductOwnerButton();
        }

        private void chatProductOwnerButton()
        {
            productOwnerChat.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(itemView.getContext(), "Chat with the Seller", Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void callProductOwnerButton()
        {
            productOwnerCall.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(itemView.getContext(), "Call the Seller!!!", Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void toggleWishlistButton()
        {
            wishlistItemButton.setOnClickListener(new View.OnClickListener()
            {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v)
                {
                    if(ALREADY_IN_WISHLIST)
                    {
                        ALREADY_IN_WISHLIST = false;
                        wishlistItemButton.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#E6CE97")));
                    }
                    else
                    {
                        ALREADY_IN_WISHLIST = true;
                        wishlistItemButton.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#FFB000")));
                    }
                }
            });
        }

    }
}
