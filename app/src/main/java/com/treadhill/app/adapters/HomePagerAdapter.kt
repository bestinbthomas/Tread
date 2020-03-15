package com.treadhill.app.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter

class HomePagerAdapter(val context: Context, val homeImages: IntArray) : PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean =
        view == `object`

    override fun getCount(): Int =
        homeImages.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(context)
        imageView.setImageResource(homeImages[position])
        val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        imageView.layoutParams = params
        container.addView(imageView)

        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}