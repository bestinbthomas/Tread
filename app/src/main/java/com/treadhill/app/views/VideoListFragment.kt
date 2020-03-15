package com.treadhill.app.views


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.treadhill.app.R
import com.treadhill.app.factory_and_ingection.InjectionUtils
import com.treadhill.app.viewModel.MainViewModel

/**
 * A simple [Fragment] subclass.
 */
class VideoListFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_list, container, false)
    }


    override fun onStart() {
        super.onStart()

        viewModel = ViewModelProviders.of(
            requireActivity(),
            InjectionUtils.provideMainViewModelFactory(requireActivity().applicationContext)
        ).get(MainViewModel::class.java)
    }

}
