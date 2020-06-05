package com.kitaa.startup.database;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kitaa.startup.HomeFragment;
import com.kitaa.startup.adapters.CategoryAdapter;
import com.kitaa.startup.adapters.HomePageAdapter;
import com.kitaa.startup.models.CategoryModel;
import com.kitaa.startup.models.HomePageModel;
import com.kitaa.startup.models.HorizontalScrollProductModel;
import com.kitaa.startup.models.SliderModel;
import com.kitaa.startup.models.WishlistModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DBqueries
{
    public static FirebaseFirestore _firebaseFirestore = FirebaseFirestore.getInstance();
    public static List<CategoryModel> _categoryModelList = new ArrayList<>();

    public static List<List<HomePageModel>> lists = new ArrayList<>();
    public static List<String> loadedCategoriesNames = new ArrayList<>();

    public static void loadCategories(final RecyclerView _categoryRecyclerView, final Context _context)
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
                    CategoryAdapter _categoryAdapter = new CategoryAdapter(_categoryModelList);
                    _categoryRecyclerView.setAdapter(_categoryAdapter);
                    _categoryAdapter.notifyDataSetChanged();
                }
                else
                {
                    String error = Objects.requireNonNull(task.getException()).getMessage();
                    Toast.makeText(_context, error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void loadFragmentData(final RecyclerView homePageRecyclerview, final Context _context, final int index, String categoryName)
    {
        _firebaseFirestore.collection("CATEGORIES").document(categoryName.toUpperCase()).collection("TOP_DEALS").orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
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
                            lists.get(index).add(new HomePageModel(0, _sliderModelList));
                        }
                        else if((long) _documentSnapshot.get("view_type") == 1)
                        {
                            lists.get(index).add(new HomePageModel(1, Objects.requireNonNull(_documentSnapshot.get("strip_ad_banner")).toString()));
                        }
                        else if((long) _documentSnapshot.get("view_type") == 2)
                        {
                            List<WishlistModel> _viewAllProductList = new ArrayList<>();
                            List<HorizontalScrollProductModel> _horizontalScrollProductModelList = new ArrayList<>();
                            long no_of_products = (long) _documentSnapshot.get("no_of_products");
                            for(long x = 1; x < no_of_products + 1; x++)
                            {
                                _horizontalScrollProductModelList.add(new HorizontalScrollProductModel(Objects.requireNonNull(_documentSnapshot.get("product_ID_" + x)).toString(), Objects.requireNonNull(_documentSnapshot.get("product_image_" + x)).toString(), Objects.requireNonNull(_documentSnapshot.get("product_title_" + x)).toString(), Objects.requireNonNull(_documentSnapshot.get("product_subtitle_" + x)).toString(), Objects.requireNonNull(_documentSnapshot.get("product_price_" + x)).toString()));

                                _viewAllProductList.add(new WishlistModel(Objects.requireNonNull(_documentSnapshot.get("product_image_" + x)).toString(), Objects.requireNonNull(_documentSnapshot.get("product_title_" + x)).toString(), Objects.requireNonNull(_documentSnapshot.get("product_price_" + x)).toString(), Objects.requireNonNull(_documentSnapshot.get("product_uploaded_time_" + x)).toString(), Objects.requireNonNull(_documentSnapshot.get("product_location_" + x)).toString(), Objects.requireNonNull(_documentSnapshot.get("product_contact_" + x)).toString()));
                            }
                            lists.get(index).add(new HomePageModel(2, Objects.requireNonNull(_documentSnapshot.get("layout_title")).toString(), _horizontalScrollProductModelList, _viewAllProductList));
                        }
                        else if((long) _documentSnapshot.get("view_type") == 3)
                        {
                            List<HorizontalScrollProductModel> _gridProductModelList = new ArrayList<>();
                            long no_of_products = (long) _documentSnapshot.get("no_of_products");
                            for(long x = 1; x < no_of_products + 1; x++)
                            {
                                _gridProductModelList.add(new HorizontalScrollProductModel(Objects.requireNonNull(_documentSnapshot.get("product_ID_" + x)).toString(), Objects.requireNonNull(_documentSnapshot.get("product_image_" + x)).toString(), Objects.requireNonNull(_documentSnapshot.get("product_title_" + x)).toString(), Objects.requireNonNull(_documentSnapshot.get("product_subtitle_" + x)).toString(), Objects.requireNonNull(_documentSnapshot.get("product_price_" + x)).toString()));
                            }
                            lists.get(index).add(new HomePageModel(3, Objects.requireNonNull(_documentSnapshot.get("layout_title")).toString(), _gridProductModelList, null));

                        }

                    }
                    HomePageAdapter _homePageAdapter = new HomePageAdapter(lists.get(index));
                    homePageRecyclerview.setAdapter(_homePageAdapter);
                    _homePageAdapter.notifyDataSetChanged();
                    HomeFragment._refreshLayout.setRefreshing(false);
                }
                else
                {
                    String error = Objects.requireNonNull(task.getException()).getMessage();
                    Toast.makeText(_context, error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
