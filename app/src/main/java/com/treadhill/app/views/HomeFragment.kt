package com.treadhill.app.views


import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import aqua.blewrapper.connectionstates.StateCodes.*
import com.google.android.material.snackbar.Snackbar
import com.treadhill.app.R
import com.treadhill.app.adapters.HomePagerAdapter
import com.treadhill.app.adapters.HomeRecyclerAdapter
import com.treadhill.app.adapters.TrainerRecAdapter
import com.treadhill.app.dataTypes.Trainer
import com.treadhill.app.dataTypes.VideoItem
import com.treadhill.app.dataTypes.VideoThumb
import com.treadhill.app.factory_and_ingection.InjectionUtils
import com.treadhill.app.highOrder.showSnackbar
import com.treadhill.app.viewModel.Actions.ScanBluetooth
import com.treadhill.app.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {

    private var videolist: List<VideoItem>? = null
    private val ItemList1 = ArrayList<VideoThumb>()
    private val adapter1 = HomeRecyclerAdapter(ItemList1)
    private lateinit var mView: View

    /**
     * Moves the Moving Carousel every 2 seconds
     */
    private val counter = object : CountDownTimer(1000000, 2000) {
        override fun onFinish() {
            startCounter()
        }

        override fun onTick(millisUntilFinished: Long) {
            val item = mView.home_view_pager.currentItem
            if (item == 2)
                mView.home_view_pager.currentItem = 0
            else mView.home_view_pager.currentItem = item + 1
        }

    }

    private fun startCounter() {
        counter.start()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mView = view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(
            requireActivity(),
            InjectionUtils.provideMainViewModelFactory(requireActivity().applicationContext)
        ).get(MainViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        mView.shimmer_layout.showShimmer(true)
        mView.bluetooth_btn.setOnClickListener {
            viewModel.listen(ScanBluetooth)
        }
        observeViewModel()
        setupRecyclers()
        setupCal()
        counter.start()

    }

    override fun onStop() {
        super.onStop()
        counter.cancel()
    }

    /**
     * Set Up The Calender
     *
     * Calender is implemented as 7 TextViews the date is obtained From Java Calender Class
     *
     */
    private fun setupCal() {
        val calendar = Calendar.getInstance()
        val dow = calendar[Calendar.DAY_OF_WEEK]
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        var sunday = calendar[Calendar.DAY_OF_MONTH]
        mView.sun.text = sunday.toString()
        mView.mon.text = (++sunday).toString()
        mView.tue.text = (++sunday).toString()
        mView.wed.text = (++sunday).toString()
        mView.thu.text = (++sunday).toString()
        mView.fri.text = (++sunday).toString()
        mView.sat.text = (++sunday).toString()
        when (dow) {
            Calendar.SUNDAY -> {
                mView.sun.setTextColor(resources.getColor(R.color.whiteTextColor))
                mView.sun.background = resources.getDrawable(R.drawable.cal_accent_grey)
            }
            Calendar.MONDAY -> {
                mView.mon.setTextColor(resources.getColor(R.color.whiteTextColor))
                mView.mon.background = resources.getDrawable(R.drawable.cal_accent_grey)
            }
            Calendar.THURSDAY -> {
                mView.tue.setTextColor(resources.getColor(R.color.whiteTextColor))
                mView.tue.background = resources.getDrawable(R.drawable.cal_accent_grey)
            }
            Calendar.WEDNESDAY -> {
                mView.wed.setTextColor(resources.getColor(R.color.whiteTextColor))
                mView.wed.background = resources.getDrawable(R.drawable.cal_accent_grey)
            }
            Calendar.THURSDAY -> {
                mView.thu.setTextColor(resources.getColor(R.color.whiteTextColor))
                mView.thu.background = resources.getDrawable(R.drawable.cal_accent_grey)
            }
            Calendar.FRIDAY -> {
                mView.fri.setTextColor(resources.getColor(R.color.whiteTextColor))
                mView.fri.background = resources.getDrawable(R.drawable.cal_accent_grey)
            }
            Calendar.SATURDAY -> {
                mView.sat.setTextColor(resources.getColor(R.color.whiteTextColor))
                mView.sat.background = resources.getDrawable(R.drawable.cal_accent_grey)
            }
        }
    }

    /**
     * Sets adapters for Moving Carousel, Horizontal Videos List and Horizontal Trainers List
     *
     * Moving Carousel is implemented using a ViewPager,
     * Horizontal Videos List and Horizontal Trainers List are implemented using Recycler Views
     *
     * fills Mock data for trainer recycler view
     *
     */
    private fun setupRecyclers() {
        val imgs = IntArray(3)
        imgs[0] = R.drawable.slider_1
        imgs[1] = R.drawable.slider_2
        imgs[2] = R.drawable.slider_3
        mView.home_view_pager.adapter = HomePagerAdapter(requireContext(), imgs)
        val trainerList = ArrayList<Trainer>()
        trainerList.add(Trainer("Trainer 1", R.mipmap.avatar1))
        trainerList.add(Trainer("Trainer 2", R.mipmap.avatar1))
        trainerList.add(Trainer("Trainer 3", R.mipmap.avatar1))
        trainerList.add(Trainer("Trainer 4", R.mipmap.avatar1))
        trainerList.add(Trainer("Trainer 5", R.mipmap.avatar1))
        trainerList.add(Trainer("Trainer 6", R.mipmap.avatar1))
        trainerList.add(Trainer("Trainer 7", R.mipmap.avatar1))
//        val ItemList3 = ArrayList<CategoryItem>()
//        ItemList3.add(CategoryItem("Item1", "This is a sample homepage item", R.mipmap.img1))
//        ItemList3.add(CategoryItem("Item2", "This is a sample homepage item", R.mipmap.img1))
//        ItemList3.add(CategoryItem("Item3", "This is a sample homepage item", R.mipmap.img1))
//        ItemList3.add(CategoryItem("Item4", "This is a sample homepage item", R.mipmap.img1))
//        ItemList3.add(CategoryItem("Item5", "This is a sample homepage item", R.mipmap.img1))
//        ItemList3.add(CategoryItem("Item6", "This is a sample homepage item", R.mipmap.img1))
//        ItemList3.add(CategoryItem("Item7", "This is a sample homepage item", R.mipmap.img1))

        rec_1.adapter = adapter1
        rec_1.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        adapter1.clickListner.observe(this,
            Observer { position ->
                Log.i("HOME", videolist.toString())
                if (position >= 0)
                    videolist?.let {
                        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToPlayerFragment(null, it[position]))
                        adapter1.clickListner.value = -1
                    }
            })
        val adapter2 = TrainerRecAdapter(trainerList)
        rec_2.adapter = adapter2
        rec_2.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        adapter2.clickListener.observe(this,
            Observer {
                showSnackbar("item $it chicked from trainer list", Snackbar.LENGTH_SHORT)
            })
//        val adapter3 = CategoryRecyclerAdapter(ItemList3)
//        rec_3.adapter = adapter2
//        rec_3.layoutManager =
//            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
//        adapter3.clickListner.observe(this,
//            Observer {
//                showSnackbar("item $it chicked from recycler 3", Snackbar.LENGTH_SHORT)
//            })
    }

    /**
     * observing the ViewModel Live datas - HeartRate, bluetooth state, Video List
     *
     */
    private fun observeViewModel() {
        viewModel.treadVideoList.observe(this,
            Observer {
                if (it.isNotEmpty()) {
                    mView.shimmer_layout.stopShimmer()
                    mView.shimmer_layout.visibility = View.INVISIBLE
                    mView.rec_1.visibility = View.VISIBLE
                }
                videolist = it
                val list = it.map { video ->
                    val link = video.ThumbnailUrl
                    val name = video.name
                    val desc = video.VideoDescription

                    VideoThumb(name, desc, link)

                }

                adapter1.videoThumbnails = list as ArrayList<VideoThumb>
                adapter1.notifyDataSetChanged()
            })
        viewModel.heartRate.observe(this,
            Observer {
                mView.heart_rate.text = it
            })
        viewModel.bTState.observe(this,
            Observer {
                Log.e("HOME", it.toString())
                when (it) {
                    STATE_ENABLED -> {
                        mView.bluetooth_btn.text = "Bluetooth connected"
                    }
                    STATE_DISABLED -> {
                        mView.bluetooth_btn.text = "Bluetooth disconnected"
                    }
                    STATE_ENABLED_ALREADY -> {
                        mView.bluetooth_btn.text = "Bluetooth connected"
                    }
                    STATE_DISBALED_ALREADY -> {
                        mView.bluetooth_btn.text = "Bluetooth disconnected"
                    }
                    BluetoothTurnedOn -> {
                        mView.bluetooth_btn.text = "Bluetooth Turned on"
                    }
                    BluetoothTurnedOff -> {
                        mView.bluetooth_btn.text = "Bluetooth is off"
                    }
                    DeviceConnected -> {
                        mView.bluetooth_btn.text = "Bluetooth connected"
                    }
                    DeviceDisconnected -> {
                        mView.bluetooth_btn.text = "Bluetooth disconnected"
                    }
                    RetryConnection -> {
                        mView.bluetooth_btn.text = "Bluetooth connecting ..."
                    }
                }
            })
    }

}
