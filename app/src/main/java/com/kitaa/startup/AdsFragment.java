package com.kitaa.startup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kitaa.R;
import com.kitaa.startup.adapters.AdsAdapter;
import com.kitaa.startup.models.WishlistModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdsFragment extends Fragment
{
    private List<WishlistModel> _AdsModelList;
    private RecyclerView _adsRecyclerview;

    public AdsFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View _view = inflater.inflate(R.layout.fragment_ads, container, false);

        _adsRecyclerview = _view.findViewById(R.id.ads_recyclerview);
        initAdsRecyclerview();

        return _view;
    }

    private void initAdsRecyclerview()
    {
        LinearLayoutManager _linearLayoutManager = new LinearLayoutManager(getContext());
        _linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        _adsRecyclerview.setLayoutManager(_linearLayoutManager);
        prepareAdsDataList();
    }

    private void prepareAdsDataList()
    {
        adsDataList();
        AdsAdapter _adapter = new AdsAdapter(_AdsModelList);
        _adsRecyclerview.setAdapter(_adapter);
        _adapter.notifyDataSetChanged();
    }

    private void adsDataList()
    {
        ///todo: Adds fragment needs to retrive data from the firebase database for the user to have a specific ads uploaded by him.
        _AdsModelList = new ArrayList<>();
//        _AdsModelList.add(new WishlistModel(R.drawable.banner, "Hello Lotion CRs19", "Tshs.30,000", "46 minutes", "Kigoma"));
//        _AdsModelList.add(new WishlistModel(R.drawable.banner2, "Buggati Veron 213L", "Tshs.144,000", "24/05/20", "Dar es Salaam"));
//        _AdsModelList.add(new WishlistModel(R.drawable.banner3, "GSM Mall Collections Pie", "Tshs.32,000", "19/04/20", "Mwanza"));

    }
}
