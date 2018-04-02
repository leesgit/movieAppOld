package com.lbc.practice.movieapp.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by jwk on 2017. 9. 9..
 */

public class ImagePagerAdapter extends PagerAdapter {
    private String[] imageUrls;

    public void setImageUrls(String[] imageUrls){
        this.imageUrls = imageUrls;
    }

    @Override
    public int getCount() {
        return (imageUrls == null) ? 0 : imageUrls.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(container.getContext());
        Glide.with(container.getContext()).load(imageUrls[position]).fitCenter().into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }


}
