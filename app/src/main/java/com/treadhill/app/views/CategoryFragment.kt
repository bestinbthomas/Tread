package com.treadhill.app.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.treadhill.app.R
import com.treadhill.app.adapters.CategoryViewPagerAdapter
import com.treadhill.app.dataTypes.Category
import com.treadhill.app.dataTypes.VideoItem
import com.treadhill.app.factory_and_ingection.InjectionUtils
import com.treadhill.app.viewModel.MainViewModel
import kotlinx.android.synthetic.main.category_fragment.view.*


class CategoryFragment : Fragment() {

    lateinit var mView: View
    private var categories = ArrayList<Category>()
    private lateinit var adapter: CategoryViewPagerAdapter

    companion object {
        fun newInstance() = CategoryFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.category_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(
            requireActivity(),
            InjectionUtils.provideMainViewModelFactory(requireActivity().applicationContext)
        ).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mView = view
    }

    override fun onStart() {
        super.onStart()

        setupObservations()
        setupViewPager()
        mView.Category_tabs.setupWithViewPager(mView.view_pager)
    }

    /**
     * The Categories are implemented as Viewpager and it is initialised here
     *
     */
    private fun setupViewPager() {
        categories.add(Category("HIIT", arrayListOf()))
        categories.add(Category("Pilates", arrayListOf()))
        categories.add(Category("Zumba", arrayListOf()))
        categories.add(Category("Yoga", arrayListOf()))
        categories.add(Category("Combo", arrayListOf()))
        Log.e("category", "category 1 has ${categories[1].items.size}")

        adapter = CategoryViewPagerAdapter(requireContext(), this, categories)
        mView.view_pager.adapter = adapter
        adapter.clickListener.observe(this,
            Observer {
                Log.i("Catgory", "category ${it.first} item ${it.second}")
                findNavController().navigate(CategoryFragmentDirections.actionCategoryFragmentToPlayerFragment(null, categories[it.first].items[it.second]))
            })

    }

    /**
     * observe Live data from view model - filertSearch and Video Lst
     *
     */
    private fun setupObservations() {
        viewModel.filterSearch.observe(this,
            Observer {
                if (it) {
                    viewModel.filterSearch.value = false
                    findNavController().navigate(CategoryFragmentDirections.actionCategoryFragmentToSearchFilterFragment())
                }
            })
        viewModel.treadVideoList.observe(this,
            Observer {
                it.forEach {
                    Log.i("VIDEOS", it.name)
                    Log.i("VIDEOS", it.VideoCategory ?: "null")
                }
                populateCategories(it)
                adapter.updateList(categories)
            })
    }

    /**
     * segregate the videos into categories
     *
     * @param videos
     */
    private fun populateCategories(videos: List<VideoItem>) {
        categories = arrayListOf()
        categories.add(Category("HIIT", arrayListOf()))
        categories.add(Category("Pilates", arrayListOf()))
        categories.add(Category("Zumba", arrayListOf()))
        categories.add(Category("Yoga", arrayListOf()))
        categories.add(Category("Combo", arrayListOf()))
        videos.forEach {
            Log.i("populate", it.name)
            Log.i("populate", it.VideoCategory ?: "null")
            when (it.VideoCategory?.toLowerCase()?.trim()) {
                "hitt" -> categories[0].items.add(it)
                "pilates" -> categories[1].items.add(it)
                "Zumba" -> categories[2].items.add(it)
                "yoga" -> categories[3].items.add(it)
                "combo" -> categories[4].items.add(it)
            }
        }
    }
}
