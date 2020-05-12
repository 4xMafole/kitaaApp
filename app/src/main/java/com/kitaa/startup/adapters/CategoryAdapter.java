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
import com.kitaa.startup.CategoryActivity;
import com.kitaa.startup.models.CategoryModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>
{
    private List<CategoryModel> _categoryModelList;

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
        ///todo: Bind icon link into view
        String icon = _categoryModelList.get(position).getCategoryIconLink();
        String name = _categoryModelList.get(position).getCategoryName();

        holder.setCategory(name, position);
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

        private void setCategoryIcon()
        {
            ///todo: Set category icon here
        }

        private void setCategory(final String name, final int position)
        {
            categoryName.setText(name);

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
