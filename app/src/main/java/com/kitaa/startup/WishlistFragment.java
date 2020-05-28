package com.kitaa.startup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kitaa.R;
import com.kitaa.startup.adapters.WishlistAdapter;
import com.kitaa.startup.models.WishlistModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WishlistFragment extends Fragment
{

    private List<WishlistModel> _wishlistModelList;
    private RecyclerView _wishlistRecyclerview;

    public WishlistFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View _view = inflater.inflate(R.layout.fragment_wishlist, container, false);

        _wishlistRecyclerview = _view.findViewById(R.id.wishlist_recyclerview);
        initWishlistRecyclerview();

        return _view;
    }

    private void initWishlistRecyclerview()
    {
        LinearLayoutManager _linearLayoutManager = new LinearLayoutManager(getContext());
        _linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        _wishlistRecyclerview.setLayoutManager(_linearLayoutManager);
        prepareWishlistDataList();
    }

    private void prepareWishlistDataList()
    {
        wishlistDataList();
        WishlistAdapter _adapter = new WishlistAdapter(_wishlistModelList);
        _wishlistRecyclerview.setAdapter(_adapter);
        _adapter.notifyDataSetChanged();
    }

    private void wishlistDataList()
    {
        _wishlistModelList = new ArrayList<>();
    }
}
