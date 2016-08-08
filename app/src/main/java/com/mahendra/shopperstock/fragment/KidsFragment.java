package com.mahendra.shopperstock.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.mahendra.shopperstock.R;
import com.mahendra.shopperstock.adapter.KidsAdapter;
import com.mahendra.shopperstock.adapter.WeeklytopAdapter;
import com.mahendra.shopperstock.custom.CustomGridView;

/**
 * Created by MK on 7/2/2016.
 */
public class KidsFragment extends Fragment {

    private GridView kids_gv;
    private KidsAdapter _weekly;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.kids_fragment, container,
                false);


        kids_gv = (GridView) rootView.findViewById(R.id.kids_gv);




        _weekly = new KidsAdapter(getActivity());



        kids_gv.setAdapter(_weekly);
        return rootView;
    }
}
