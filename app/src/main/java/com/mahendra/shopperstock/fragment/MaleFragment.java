package com.mahendra.shopperstock.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.mahendra.shopperstock.R;
import com.mahendra.shopperstock.adapter.MaleAdapter;
import com.mahendra.shopperstock.adapter.WeeklytopAdapter;
import com.mahendra.shopperstock.custom.CustomGridView;

/**
 * Created by MK on 7/2/2016.
 */
public class MaleFragment extends Fragment{

    private GridView male_gv;
    private MaleAdapter _weekly;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.male_fragment, container,
                false);
        male_gv = (GridView) rootView.findViewById(R.id.male_gv);




        _weekly = new MaleAdapter(getActivity());



        male_gv.setAdapter(_weekly);


        return rootView;
    }
}
