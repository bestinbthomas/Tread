package com.treadhill.app.adapters

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.treadhill.app.dataTypes.Category

class CategoryViewPagerAdapter(val context: Context, private val owner: LifecycleOwner, var categories: ArrayList<Category>, val clickListener: MutableLiveData<Pair<Int, Int>> = MutableLiveData()) : PagerAdapter() {
    override fun getCount(): Int =
        categories.size

    override fun instantiateItem(container: ViewGroup, position: Int): View {
        Log.e("viewPager", "instantiate")
        val subCatRecView = RecyclerView(context)
        val reclayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        subCatRecView.layoutParams = reclayoutParams
        val adapter = CategoryRecyclerAdapter(container.context, categories[position].items)
        subCatRecView.adapter = adapter
        subCatRecView.layoutManager = LinearLayoutManager(container.context,
            LinearLayoutManager.VERTICAL, false)
        adapter.clickListner.observe(owner,
            Observer {
                clickListener.value = Pair(position, it)
            })

        container.addView(subCatRecView)

//        val cardView = CardView(context)
//        val layoutParams = LinearLayout.LayoutParams(
//            LinearLayout.LayoutParams.MATCH_PARENT,400)
//        cardView.layoutParams = layoutParams
//        cardView.setCardBackgroundColor(Color.DKGRAY)
//        val textView = TextView(context)
//        textView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT)
//        textView.textSize = 112f
//        textView.text = (position+1).toString()
//        textView.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
//        cardView.addView(textView)
//        container.addView(cardView)
//        return cardView
        return subCatRecView

    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean = `object` == view

    override fun getPageTitle(position: Int): CharSequence? = categories[position].name

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    fun updateList(categories: ArrayList<Category>) {
        this.categories = categories
        notifyDataSetChanged()
    }
}