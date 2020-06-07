package com.kitaa.startup.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kitaa.R;
import com.kitaa.startup.CategoryActivity;
import com.kitaa.startup.models.CategoryModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>
{
    private List<CategoryModel> _categoryModelList;
    private int _lastPosition = -1;

    public CategoryAdapter(List<CategoryModel> categoryModelList)
    {
        _categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new ViewHolder(_view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        String icon = _categoryModelList.get(position).getCategoryIconLink();
        String name = _categoryModelList.get(position).getCategoryName();

        holder.setCategory(name, position);
        holder.setCategoryIcon(icon);

        if(_lastPosition < position)
        {
            Animation _animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.fade_out);
            holder.itemView.setAnimation(_animation);
            _lastPosition = position;
        }
    }

    @Override
    public int getItemCount()
    {
        return _categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView categoryIcon;
        private TextView categoryName;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            categoryIcon = itemView.findViewById(R.id.category_icon);
            categoryName = itemView.findViewById(R.id.category_name);
        }

        private void setCategoryIcon(String iconUrl)
        {
            if(!iconUrl.equals("null"))
            {
                Glide.with(itemView.getContext()).load(iconUrl).apply(new RequestOptions().placeholder(R.drawable.slider_background)).into(categoryIcon);
            }
            else
            {
                categoryIcon.setImageResource(R.drawable.slider_background);
            }
        }

        private void setCategory(final String name, final int position)
        {
            categoryName.setText(name);

            if(!name.equals(""))
            {
                //Category item clicked listener.
                itemView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        if(position != 0)
                        {
                            Intent categoryIntent = new Intent(itemView.getContext(), CategoryActivity.class);
                            categoryIntent.putExtra("CategoryName", name);
                            itemView.getContext().startActivity(categoryIntent);
                        }
                    }
                });
            }
        }

    }
}
