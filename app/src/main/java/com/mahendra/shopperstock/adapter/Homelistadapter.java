package com.mahendra.shopperstock.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mahendra.shopperstock.R;

/**
 * Created by MK on 21-06-2016.
 */
public class Homelistadapter extends BaseAdapter {
    private LayoutInflater inflater;
    Activity _activity;
    //private ArrayList<ProductDetailByName> al;
    private viewHolderFull holder;

    public Homelistadapter(Activity _activity)
    {

        this._activity = _activity;
    }

    @Override
    public int getCount() {
        return 15;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        inflater = (LayoutInflater) _activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            holder = new viewHolderFull();
            convertView = inflater.inflate(
                    R.layout.custom_full_list_header, null);

         /*   holder.Fram_icon_imageView_full = (ImageView) convertViewlf
                    .findViewById(R.id.imageView_fulllist_icon);*/

            // holder.pb_full = (ProgressBar) convertViewlf
            // .findViewById(R.id.progressbar_full_list_img);
/*

            holder.Main_f_Tag_TextView_full = (TextView) convertViewlf
                    .findViewById(R.id.textView_main_full_tag_text);

            holder.Category_textView_full = (TextView) convertViewlf
                    .findViewById(R.id.textView_full_category_tag);

            holder.Price_category_full = (TextView) convertViewlf
                    .findViewById(R.id.textView_full_price_tag);
*/

            convertView.setTag(holder);

        } else {
            holder = (viewHolderFull) convertView.getTag();
        }
      /*  try {
            Picasso.with(_activity).load(al.get(position).getImage())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(holder.Fram_icon_imageView_full);
        } catch (Exception e) {

        }*/
/*

        holder.Main_f_Tag_TextView_full
                .setText(al.get(position).getTitle());
        holder.Category_textView_full.setText(al.get(position)
                .getCategory());
        holder.Price_category_full.setText(al.get(position).getPrice()
                + " ₹");

        convertViewlf.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("Click", "On view");

                ArrayList<String> temp_image_Array = al.get(position)
                        .getImages();
                Log.d("Click", "On view imag"+temp_image_Array);
                String asin = al.get(position).getAsin();
                OverViewClass.searchByName = true;
                Intent intentf = new Intent(MainViewFragment.this._activity.getApplicationContext(),
                        peelyourdeal.best.price.compare.app.OverViewClass.class);
                Log.d("Click", "On view intent"+intentf);
                intentf.putExtra("code", asin);
                intentf.putExtra("whichservice", "asin");
                intentf.putStringArrayListExtra("image_array",
                        temp_image_Array);
//					intentf.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//					intentf.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                MainViewFragment.this._activity.startActivity(intentf);
                Log.d("Click", "final intent call");
                getActivity().overridePendingTransition(
                        R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });
*/

        return convertView;
    }

    class viewHolderFull {
        // ProgressBar pb_full;
       /* ImageView Fram_icon_imageView_full;
        TextView Main_f_Tag_TextView_full, Category_textView_full,
                Price_category_full;*/

    }

 /*   void setData(ArrayList<ProductDetailByName> products) {
        this.al = products;
        notifyDataSetChanged();

    }*/
}
