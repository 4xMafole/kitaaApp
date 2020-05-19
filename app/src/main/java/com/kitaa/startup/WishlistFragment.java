package com.kitaa.startup;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        WishlistAdapter _adapter = new WishlistAdapter(_wishlistModelList, true);
        _wishlistRecyclerview.setAdapter(_adapter);
        _adapter.notifyDataSetChanged();
    }

    private void wishlistDataList()
    {
        _wishlistModelList = new ArrayList<>();
        _wishlistModelList.add(new WishlistModel(R.drawable.banner, "Hello Lotion CRs19", "Tshs.30,000", "46 minutes", "Kigoma"));
        _wishlistModelList.add(new WishlistModel(R.drawable.banner2, "Buggati Veron 213L", "Tshs.144,000", "24/05/20", "Dar es Salaam"));
        _wishlistModelList.add(new WishlistModel(R.drawable.banner3, "GSM Mall Collections Pie", "Tshs.32,000", "19/04/20", "Mwanza"));
        _wishlistModelList.add(new WishlistModel(R.drawable.banner4, "Ferrari Brand New", "Tshs.3,213,000", "12 hours", "Iringa"));
        _wishlistModelList.add(new WishlistModel(R.drawable.banner5, "WALMAT GABBLE HOME", "Tshs.10,000,000", "30 minutes", "Mbeya"));
        _wishlistModelList.add(new WishlistModel(R.drawable.banner6, "Iphone X 23GB", "Tshs.132,000", "15/05/20", "Ruvuma"));
        _wishlistModelList.add(new WishlistModel(R.drawable.banner7, "Lottery Tickets DL", "Tshs.54,000", "24 hours", "Dodoma"));
    }
}
