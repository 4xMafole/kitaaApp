package com.kitaa.startup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kitaa.R;
import com.kitaa.startup.adapters.CategoryAdapter;
import com.kitaa.startup.adapters.HomePageAdapter;
import com.kitaa.startup.models.CategoryModel;
import com.kitaa.startup.models.HomePageModel;
import com.kitaa.startup.models.HorizontalScrollProductModel;
import com.kitaa.startup.models.SliderModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment
{
    private List<CategoryModel> _categoryModelList;

    ///// Category fields
    private RecyclerView _categoryRecyclerview;
    private FirebaseFirestore _firebaseFirestore;
    ///// Category fields

    /////Home Page fields
    private RecyclerView _homepageRecyclerview;
    private CategoryAdapter _categoryAdapter;

    public HomeFragment()
    {
        // Required empty public constructor
    }
    private List<HomePageModel> _homePageModelList;

    /////Home Page fields

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View _view =  inflater.inflate(R.layout.fragment_home, container, false);

        //Category view
        _categoryRecyclerview = _view.findViewById(R.id.category_rv);
        initCategoryRecyclerview();

        _firebaseFirestore = FirebaseFirestore.getInstance();
        initFireStoreCategory();

        //Homepage view
        _homepageRecyclerview = _view.findViewById(R.id.home_page_recyclerview);
        initHomepageRecyclerview();

        return _view;
    }

    private void initFireStoreCategory()
    {
        _firebaseFirestore.collection("CATEGORIES").orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                if(task.isSuccessful())
                {
                    for(QueryDocumentSnapshot _documentSnapshot : Objects.requireNonNull(task.getResult()))
                    {
                        _categoryModelList.add(new CategoryModel(Objects.requireNonNull(_documentSnapshot.get("icon")).toString(), Objects.requireNonNull(_documentSnapshot.get("categoryName")).toString()));
                    }
                    _categoryAdapter.notifyDataSetChanged();
                }
                else
                {
                    String error = Objects.requireNonNull(task.getException()).getMessage();
                    Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /////Category Recyclerview
    private void initCategoryRecyclerview()
    {
        LinearLayoutManager _layoutManager = new LinearLayoutManager(getActivity());
        _layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        _categoryRecyclerview.setLayoutManager(_layoutManager);
        prepareCategoryData();
    }

    private void prepareCategoryData()
    {
        categoryDataList();
        _categoryAdapter = new CategoryAdapter(_categoryModelList);
        _categoryRecyclerview.setAdapter(_categoryAdapter);
    }

    private void categoryDataList()
    {
        _categoryModelList = new ArrayList<CategoryModel>();
    }
    /////Category Recyclerview

    /////Homepage Recyclerview
    private void initHomepageRecyclerview()
    {
        LinearLayoutManager _linearLayoutManager = new LinearLayoutManager(getContext());
        _linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        _homepageRecyclerview.setLayoutManager(_linearLayoutManager);
        prepareHomePageData();

    }
    private void prepareHomePageData()
    {
        homePageDataList();
        final HomePageAdapter _homePageAdapter = new HomePageAdapter(_homePageModelList);
        _homepageRecyclerview.setAdapter(_homePageAdapter);

        _firebaseFirestore.collection("CATEGORIES").document("HOME").collection("TOP_DEALS").orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                if(task.isSuccessful())
                {
                    for(QueryDocumentSnapshot _documentSnapshot : Objects.requireNonNull(task.getResult()))
                    {

                        if((long) _documentSnapshot.get("view_type") == 0)
                        {
                            List<SliderModel> _sliderModelList = new ArrayList<>();
                            long no_of_banners = (long) _documentSnapshot.get("no_of_banners");
                            for(long x = 1; x < no_of_banners + 1; x++)
                            {
                                _sliderModelList.add(new SliderModel(Objects.requireNonNull(_documentSnapshot.get("banner_" + x)).toString()));
                            }
                            _homePageModelList.add(new HomePageModel(0, _sliderModelList));
                        }
                        else if((long) _documentSnapshot.get("view_type") == 1)
                        {
                            _homePageModelList.add(new HomePageModel(1, Objects.requireNonNull(_documentSnapshot.get("strip_ad_banner")).toString()));
                        }
                        else if((long) _documentSnapshot.get("view_type") == 2)
                        {
                            List<HorizontalScrollProductModel> _horizontalScrollProductModelList = new ArrayList<>();
                            long no_of_products = (long) _documentSnapshot.get("no_of_products");
                            for(long x = 1; x < no_of_products + 1; x++)
                            {
                                _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(Objects.requireNonNull(_documentSnapshot.get("product_ID_" + x)).toString(), Objects.requireNonNull(_documentSnapshot.get("product_image_" + x)).toString(), Objects.requireNonNull(_documentSnapshot.get("product_title_" + x)).toString(), Objects.requireNonNull(_documentSnapshot.get("product_subtitle_" + x)).toString(), Objects.requireNonNull(_documentSnapshot.get("product_price_" + x)).toString()));
                            }
                            _homePageModelList.add(new HomePageModel(2, Objects.requireNonNull(_documentSnapshot.get("layout_title")).toString(), _horizontalScrollProductModelList));
                        }
                        else if((long) _documentSnapshot.get("view_type") == 3)
                        {

                        }

                    }
                    _homePageAdapter.notifyDataSetChanged();
                }
                else
                {

                }
            }
        });
    }

    private void homePageDataList()
    {
        _homePageModelList = new ArrayList<HomePageModel>();
//        _homePageModelList.add(new HomePageModel(0, bannerDataList()));
//        _homePageModelList.add(new HomePageModel(1, R.drawable.strip_add));
//        _homePageModelList.add(new HomePageModel(2, "#Latest", horizontalProductDataList()));
//        _homePageModelList.add(new HomePageModel(3, "Hot Trends", horizontalProductDataList()));

    }
    /////Homepage Recyclerview

    private List<HorizontalScrollProductModel> horizontalProductDataList()
    {
        List<HorizontalScrollProductModel> _horizontalScrollProductModelList = new ArrayList<>();
//        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone4, "Iphone X", "SD 234 Processors", "Tshs.10,000,000/="));
//        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone4, "Iphone X", "SD 234 Processors", "Tshs.10,000,000/="));
//        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone, "Techno S8", "2 Core Processors", "Tshs.700,000/="));
//        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone4, "Iphone X", "SD 234 Processors", "Tshs.10,000,000/="));
//        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone4, "Iphone X", "SD 234 Processors", "Tshs.10,000,000/="));
//        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone2, "Huawei P20", "CBC 10-2 Processors", "Tshs.800,000/="));
//        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone3, "Vivo 20T", "CBC 10-2 Processors", "Tshs.400,000/="));
//        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone4, "Iphone X", "SD 234 Processors", "Tshs.10,000,000/="));
//        _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(R.drawable.phone, "Techno S8", "2 Core Processors", "Tshs.700,000/="));

        return _horizontalScrollProductModelList;
    }

    private List<SliderModel> bannerDataList()
    {
        ///todo: Admin adds user's own banner to promote a work public
        List<SliderModel> _sliderModelList = new ArrayList<SliderModel>();
//
//        _sliderModelList.add(new SliderModel(R.drawable.banner7));
//        _sliderModelList.add(new SliderModel(R.drawable.banner1));
//
//        _sliderModelList.add(new SliderModel(R.drawable.banner2));
//        _sliderModelList.add(new SliderModel(R.drawable.banner));
//        _sliderModelList.add(new SliderModel(R.drawable.banner4));
//        _sliderModelList.add(new SliderModel(R.drawable.banner5));
//        _sliderModelList.add(new SliderModel(R.drawable.banner6));
//        _sliderModelList.add(new SliderModel(R.drawable.banner10));
//        _sliderModelList.add(new SliderModel(R.drawable.banner9));
//        _sliderModelList.add(new SliderModel(R.drawable.banner11));
//
//        _sliderModelList.add(new SliderModel(R.drawable.banner7));
//        _sliderModelList.add(new SliderModel(R.drawable.banner1));

        return _sliderModelList;
    }

}
