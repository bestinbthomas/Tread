package com.treadhill.app.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.treadhill.app.R
import com.treadhill.app.adapters.CategoryRecyclerAdapter
import com.treadhill.app.dataTypes.VideoItem
import com.treadhill.app.factory_and_ingection.InjectionUtils
import com.treadhill.app.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_filter_results.view.*

/**
 * A simple [Fragment] subclass.
 */
class FilterResults : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var mView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filter_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mView = view
    }

    override fun onStart() {
        super.onStart()

        viewModel = ViewModelProviders.of(
            requireActivity(),
            InjectionUtils.provideMainViewModelFactory(requireActivity().applicationContext)
        ).get(MainViewModel::class.java)

        val videos: ArrayList<VideoItem> = (viewModel.treadVideoList.value as ArrayList)
            ?: arrayListOf()
        val adapter = CategoryRecyclerAdapter(requireContext(), videos)
        mView.filteredList.adapter = adapter
        mView.filteredList.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
        adapter.clickListner.observe(this,
            Observer {
                findNavController().navigate(FilterResultsDirections.actionFilterResultsToPlayerFragment(null, videos[it]))
            })

        viewModel.treadVideoList.observe(this,
            Observer {
                adapter.submitList(it as ArrayList)
            })

    }

}
