package com.lbc.practice.movieapp.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by jwk on 2017. 9. 9..
 */


class ImagePagerAdapter : PagerAdapter() {
    public var imageUrls = arrayOfNulls<String>(2)

//    fun setImageUrls(imageUrls : Array<String?>) {
//        this.imageUrls = imageUrls
//    }

    override fun getCount(): Int {
        return imageUrls.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(container.context)
        Glide.with(container.context).load(imageUrls[position]).fitCenter().into(imageView)
        container.addView(imageView)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }


}

