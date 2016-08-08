package com.mahendra.shopperstock.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.mahendra.shopperstock.R;
import com.mahendra.shopperstock.custom.TouchImage;

import java.util.ArrayList;

/**
 * Created by MK on 6/30/2016.
 */
public class ViewPagerAdapter  extends PagerAdapter

{
    int[] images;
    Context context;
    LayoutInflater inflater;

    public ViewPagerAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
        // pd = new ProgressDialog(_overview);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        /*TouchImage imgflag;
        final ProgressBar progressBar_viewpager;*/

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item,
                container, false);
        TouchImage image = (TouchImage) itemView.findViewById(R.id.touchimage);

        image.setBackgroundResource(images[position]);

      /*  imgflag = (TouchImage) itemView
                .findViewById(R.id.popup_ImageInfo_Image_new);
        progressBar_viewpager = (ProgressBar) itemView
                .findViewById(R.id.progressBar_viewpager);
        // Capture position and set to the ImageView

        try {
            progressBar_viewpager.setVisibility(View.VISIBLE);

            Picasso.with(_overview).load(images.get(position))
                    .into(imgflag, new Callback() {

                        @Override
                        public void onSuccess() {
                            progressBar_viewpager.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            progressBar_viewpager.setVisibility(View.GONE);
                        }
                    });
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

        // imgflag.setImageResource(images[position]);

        // Add viewpager_item.xml to ViewPager
        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((RelativeLayout) object);

    }

}