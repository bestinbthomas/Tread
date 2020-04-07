package com.treadhill.app.views


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.treadhill.app.R
import com.treadhill.app.adapters.TextRecAdapter
import com.treadhill.app.adapters.TrainerRecAdapter
import com.treadhill.app.dataTypes.FilterOptions
import com.treadhill.app.dataTypes.Trainer
import com.treadhill.app.factory_and_ingection.InjectionUtils
import com.treadhill.app.viewModel.Actions
import com.treadhill.app.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_search_filter.*
import kotlinx.android.synthetic.main.fragment_search_filter.view.*

/**
 * Filter Page with options to filter the Videos
 *
 */
class SearchFilterFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    lateinit var mView: View
    private lateinit var typeAdapter: TextRecAdapter
    private lateinit var difficultyAdapter: TextRecAdapter
    private var options = FilterOptions()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mView = view

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.e("FILTER", "filter fragment called")
        return inflater.inflate(R.layout.fragment_search_filter, container, false)
    }

    override fun onStart() {
        super.onStart()

        viewModel = ViewModelProviders.of(
            requireActivity(),
            InjectionUtils.provideMainViewModelFactory(requireActivity().applicationContext)
        ).get(MainViewModel::class.java)

        typeAdapter = TextRecAdapter(listOf("HITT", "PILATES", "ZUMBA", "YOGA", "Combo"), requireContext())
        difficultyAdapter = TextRecAdapter(listOf("Beginner", "Intermediate", "Difficult"), requireContext())
        setUpSlider()
        setupRecycles()
        setClickliseners()
        seteditText()
        setObservations()
        Log.e("FILTER", "filter fragment start over")
    }

    /**
     * Sets up the Duration Slider to select the duration
     *
     */
    private fun setUpSlider() {
        mView.duration_slider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mView.duration_text.text = requireActivity().resources.getString(R.string.duration, progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                options.filterDuration = seekBar?.progress ?: 0
            }
        })
    }

    /**
     * observe filterOptions live data from ViewModels
     *
     */
    private fun setObservations() {
        viewModel.filterOptions.observe(this,
            Observer {
                Log.i("duration", it.filterDuration.toString())
                options = it
                typeAdapter.setSelectedList(it.filterTypes)
                difficultyAdapter.setSelectedList(it.filterDifficulties)
                duration_slider.progress = it.filterDuration
                search_edit_text.text = Editable.Factory.getInstance().newEditable(it.query)
            })
    }

    /**
     * Sets the search Field to enter the search data
     *
     */
    private fun seteditText() {
        mView.search_edit_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                options.query = s.toString()
            }

        })
    }

    /** Handle Clicks on back button, Filter button, and reset button
     *
     */
    private fun setClickliseners() {
        mView.back_botton.setOnClickListener {
            findNavController().popBackStack()
        }
        /**
         * get the selected Items from the respective recycler view and update FilterOptions
         */
        mView.filter_button.setOnClickListener {
            options.filterDifficulties = difficultyAdapter.selectedItems
            options.filterTypes = typeAdapter.selectedItems
            viewModel.listen(Actions.FilterVideos(options))
            findNavController().navigate(SearchFilterFragmentDirections.actionSearchFilterFragmentToFilterResults())
        }
        mView.reset_button.setOnClickListener {
            viewModel.listen(Actions.FilterVideos(FilterOptions()))
        }
    }

    /**
     * attach adapters to Type, Difficulty, Trainers Recycler view
     *
     */
    private fun setupRecycles() {

        mView.types_recyclerView.adapter = typeAdapter
        mView.types_recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        mView.difficulty_recyclerView.adapter = difficultyAdapter
        mView.difficulty_recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        val trainerList = ArrayList<Trainer>()
        trainerList.add(Trainer("Trainer 1", R.mipmap.avatar1))
        trainerList.add(Trainer("Trainer 2", R.mipmap.avatar1))
        trainerList.add(Trainer("Trainer 3", R.mipmap.avatar1))
        trainerList.add(Trainer("Trainer 4", R.mipmap.avatar1))
        trainerList.add(Trainer("Trainer 5", R.mipmap.avatar1))
        trainerList.add(Trainer("Trainer 6", R.mipmap.avatar1))
        trainerList.add(Trainer("Trainer 7", R.mipmap.avatar1))
        val trainerAdapter = TrainerRecAdapter(trainerList)
        mView.trainers_recyclerView.adapter = trainerAdapter
        mView.trainers_recyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        trainerAdapter.clickListener.observe(this,
            Observer {
                //TODO handle clicks
            })

    }

}
