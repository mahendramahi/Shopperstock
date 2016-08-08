package com.mahendra.shopperstock.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.mahendra.shopperstock.R;

/**
 * Created by MK on 7/2/2016.
 */
public class SpecialoffAdapter extends BaseAdapter {

    private Activity _PAda;
    private LayoutInflater inflater;


    public SpecialoffAdapter(Activity act) {


        this._PAda = act;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 16;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertViewlg,
                        ViewGroup parent) {


        if (convertViewlg == null) {
            inflater = (LayoutInflater) _PAda.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);

            convertViewlg = inflater.inflate(R.layout.custom_grid_view, null);

            ImageView imageView1 = (ImageView) convertViewlg.findViewById(R.id.imageView1);

            imageView1.setImageResource(R.drawable.male_img);


        }

        return convertViewlg;
    }



}