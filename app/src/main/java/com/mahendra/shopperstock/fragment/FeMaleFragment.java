package com.mahendra.shopperstock.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.mahendra.shopperstock.R;
import com.mahendra.shopperstock.adapter.FemaleAdapter;
import com.mahendra.shopperstock.adapter.WeeklytopAdapter;
import com.mahendra.shopperstock.custom.CustomGridView;

/**
 * Created by MK on 7/2/2016.
 */
public class FeMaleFragment extends Fragment {

    private GridView female_gv;
    private FemaleAdapter _femaleAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.female_fragment, container,
                false);

        female_gv = (GridView) rootView.findViewById(R.id.female_gv);




        _femaleAdapter = new FemaleAdapter(getActivity());



           female_gv.setAdapter(_femaleAdapter);

        return rootView;
    }
}
