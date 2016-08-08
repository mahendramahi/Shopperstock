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
public class KidsofferAdapter extends BaseAdapter {

    private Activity _PAda;
    private LayoutInflater inflater;


    public KidsofferAdapter(Activity act) {


        this._PAda = act;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 10;
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

            convertViewlg = inflater.inflate(R.layout.customlist, null);

            ImageView imageView = (ImageView) convertViewlg.findViewById(R.id.imageView_tv_titlle_story);
            imageView.setBackgroundResource(R.drawable.kids_img);




        }

        return convertViewlg;
    }



}