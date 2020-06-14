package com.kitaa.startup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.kitaa.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDescriptionFragment extends Fragment
{

    public ProductDescriptionFragment()
    {
        // Required empty public constructor
    }

    public String _body;
    private TextView _descriptionBody;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View _view = inflater.inflate(R.layout.fragment_product_description, container, false);
        _descriptionBody = _view.findViewById(R.id.product_description_text);
        _descriptionBody.setText(_body);
        return _view;
    }
}
