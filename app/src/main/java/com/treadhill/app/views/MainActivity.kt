package com.treadhill.app.views

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import aqua.blewrapper.connectionstates.StateCodes
import aqua.blewrapper.connectionstates.StateCodes.RC_LOCATION
import aqua.blewrapper.connectionstates.StateCodes.STATE_DISABLED
import aqua.blewrapper.contracts.BluetoothManager
import aqua.blewrapper.contracts.BluetoothViewContract.*
import aqua.blewrapper.helper.BluetoothController
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.treadhill.app.MainNavigationDirections
import com.treadhill.app.R
import com.treadhill.app.factory_and_ingection.InjectionUtils
import com.treadhill.app.highOrder.PREF_NAME
import com.treadhill.app.highOrder.SHARED_PREF_NAME
import com.treadhill.app.highOrder.showSnackbar
import com.treadhill.app.viewModel.Actions
import com.treadhill.app.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks


class MainActivity : AppCompatActivity(), PermissionCallbacks,
    ConnectionStateCallbacks, CommunicationCallbacks,
    ConnectedDeviceStateCallbacks {


    lateinit var viewModel: MainViewModel

    private lateinit var bluetoothManager: BluetoothManager<Any>
    private lateinit var mGoogleApiClient: GoogleApiClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(
            this,
            InjectionUtils.provideMainViewModelFactory(applicationContext)
        ).get(MainViewModel::class.java)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val navController = findNavController(R.id.main_fragment)
        bottom_nav_view.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController)

        buildGoogleApiClient()

        bluetoothManager = BluetoothController(this)

        bluetoothManager.setGoogleApiClient(mGoogleApiClient)
        bluetoothManager.setConnectionCallbacks(this)
        bluetoothManager.setDataCallbacks(this)
        bluetoothManager.setConnectedDeviceStateCallbacks(this)
        bluetoothManager.checkBluetoothRequirements()
    }

    @SuppressLint("LongLogTag")
    @Synchronized
    protected fun buildGoogleApiClient() {
        BluetoothController.log("Building GoogleApiClient")
        mGoogleApiClient = GoogleApiClient.Builder(this)
            .addApi(LocationServices.API)
            .build()
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.main_fragment).navigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        val logoutBtn = menu!!.findItem(R.id.logout)
        val searchItem = menu.findItem(R.id.action_search)
        val filterItem = menu.findItem(R.id.filterSearch)
        filterItem.setOnMenuItemClickListener {
            viewModel.listen(Actions.Filter)
            false
        }
        val searchView = searchItem.actionView as SearchView
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                filterItem.isVisible = true
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                filterItem.isVisible = false
                return true
            }

        })
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.listen(Actions.SearchChange(query, true))
                findNavController(R.id.main_fragment).navigate(MainNavigationDirections.actionGlobalFilterResults())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Toast.makeText(this@MainActivity, "filter text : $newText", Toast.LENGTH_SHORT)
                    .show()
                viewModel.listen(Actions.SearchChange(newText))
                return true
            }

        })

        logoutBtn.setOnMenuItemClickListener {

            viewModel.listen(Actions.Logout)
            getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE).edit().clear().apply()
            val intent = Intent(this, SplashActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            false
        }

        findNavController(R.id.main_fragment).addOnDestinationChangedListener { _, destination, _ ->
            logoutBtn.isVisible = destination.label == "Dashboard"
            searchItem.isVisible = destination.label == "Categories"
            bottom_nav_view.visibility = if (destination.label == "Player" || destination.label == "Workout finder") View.GONE else View.VISIBLE
            toolbar.visibility = if (destination.label == "Player" || destination.label == "Workout finder") View.GONE else View.VISIBLE
            if (destination.id == R.id.homeFragment) {
                val name = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE).getString(PREF_NAME, "User")
                destination.label = "Welcome, $name"
            }
        }
        return true
    }

    private fun scan() {
        val scanDeviceFragment = ScanDeviceFragment()
        scanDeviceFragment.setScanDeviceListener { device ->
            /* once a device is connected, we can connect to it and stop scanning */
            showSnackbar("Connecting to " + device.name + "...", Snackbar.LENGTH_SHORT)
            Log.d("MAIN ACTIVITY", "Connecting to " + device.name + "...")
            bluetoothManager.connectToDevice(device)
        }
        scanDeviceFragment.show(fragmentManager, "ScanDeviceFragment")
    }

    private fun connect() {
        if (bluetoothManager.savedDevice.deviceAddress.isNotEmpty()) {
            showSnackbar("Connecting to " + bluetoothManager.savedDevice.deviceName + "...", Snackbar.LENGTH_SHORT)
            Log.d("MAIN ACTIVITY", "Connecting to " + bluetoothManager.savedDevice.deviceName + "...")
            bluetoothManager.connectToDevice(bluetoothManager.savedDevice.deviceAddress)
        } else {
            showSnackbar("Scan required", Snackbar.LENGTH_SHORT)
            Log.d("MAIN ACTIVITY", "Scan required")
            scan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        bluetoothManager.onActivityResult(requestCode, resultCode, data)
    }

    override fun onStart() {
        super.onStart()
        mGoogleApiClient.connect()
        viewModel.scanBT.observe(this,
            Observer {
                if (it) {
                    connect()
                    viewModel.scanBT.value = false
                }
            })
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val name = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE).getString(PREF_NAME, "User")
        toolbar.title = "Welcome, $name"
    }

    override fun onResume() {
        super.onResume()
        /* if we want to know bluetooth on device is turned on or off */
        bluetoothManager.registerStateDetection()
    }


    override fun onPause() {
        super.onPause()
        bluetoothManager.unregisterStateDetection()
    }

    override fun onStop() {
        super.onStop()
        mGoogleApiClient.disconnect()
        bluetoothManager.stopScanning()
        showSnackbar("onStop called", Snackbar.LENGTH_SHORT)
        Log.d("MAIN ACTIVITY", "onStop called")
        bluetoothManager.disconnect()
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>?) {
        AppSettingsDialog.Builder(this)
            .setTitle(getString(R.string.rationale_ask_again))
            .setPositiveButton(getString(R.string.setting))
            .setNegativeButton(getString(R.string.cancel))
            .setRequestCode(RC_LOCATION)
            .build()
            .show()
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>?) {}

    override fun gpsState(connectionState: Int) {
        when (connectionState) {
            StateCodes.GPSEnabled -> {
                showSnackbar("All conditions for Bluetooth satisfied.", Snackbar.LENGTH_SHORT)
                Log.d("MAIN ACTIVITY", "All conditions for Bluetooth satisfied.")
                bluetoothManager.retryConnection()
            }
            StateCodes.GPSDisabled -> bluetoothManager.checkLocationRequirements()
        }
    }

    override fun askLocationPermission() {
        afterPermissionGranted()
    }

    override fun permissionState(permissionState: Int) {
        when (permissionState) {
            StateCodes.PermissionGranted -> {
            }
            StateCodes.PermissionDenied -> bluetoothManager.checkLocationRequirements()
            StateCodes.PermissionError -> BluetoothController.log("location permission error")
        }
    }

    override fun bleDeviceConnectionState(deviceConnectionState: Int) {
        viewModel.listen(Actions.BluetoothState(deviceConnectionState))
        when (deviceConnectionState) {
            StateCodes.BluetoothTurnedOn -> {
                Toast.makeText(
                    this,
                    "BLE connected on device",
                    Toast.LENGTH_SHORT
                ).show()
                showSnackbar("Bluetooth turned on", Snackbar.LENGTH_SHORT)
                Log.d("MAIN ACTIVITY", "Bluetooth turned on")
                bluetoothManager.retryConnection()
            }
            StateCodes.BluetoothTurnedOff -> {
                Log.d("MAIN ACTIVITY", "Bluetooth turned off")
            }
        }
    }

    override fun bleConnectionState(connectionState: Int) {
        viewModel.listen(Actions.BluetoothState(connectionState))
        if (connectionState == STATE_DISABLED) {
            bluetoothManager.checkBluetoothRequirements()
        }
    }

    override fun onDataReceived(data: String?) {
        //showSnackbar("Data received: $data",Snackbar.LENGTH_SHORT)
        viewModel.listen(Actions.HeartRate(data ?: "0"))
        Log.d("MAIN ACTIVITY", "Data received: $data")
    }

    override fun connectedDeviceState(connectedDeviceState: Int) {
        viewModel.listen(Actions.BluetoothState(connectedDeviceState))
        when (connectedDeviceState) {
            StateCodes.DeviceConnected -> {
                showSnackbar("Connected " + bluetoothManager.savedDevice.deviceName, Snackbar.LENGTH_SHORT)
                Log.d("MAIN ACTIVITY", "Connected " + bluetoothManager.savedDevice.deviceName)
            }
            StateCodes.DeviceDisconnected -> {
                showSnackbar("Disconnected " + bluetoothManager.savedDevice.deviceName, Snackbar.LENGTH_SHORT)
                Log.d("MAIN ACTIVITY", "Disconnected " + bluetoothManager.savedDevice.deviceName)
                bluetoothManager.retryConnection()
            }
            StateCodes.RetryConnection -> {
                showSnackbar("Retrying connecting " + bluetoothManager.savedDevice.deviceName + "...", Snackbar.LENGTH_SHORT)
                Log.d("MAIN ACTIVITY", "Retrying connecting " + bluetoothManager.savedDevice.deviceName + "...")
                checkPreviousState()
            }
        }
    }

    private fun checkPreviousState() {
        if (!bluetoothManager.savedDevice.deviceAddress.isEmpty()) {
            showSnackbar("Last saved device " + bluetoothManager.savedDevice.deviceName, Snackbar.LENGTH_SHORT)
            Log.d("MAIN ACTIVITY", "Last saved device " + bluetoothManager.savedDevice.deviceName)
            bluetoothManager.setDiscoveryCallbacks(object : DiscoveryCallbacks {
                override fun onDeviceDiscovered(device: BluetoothDevice) {
                    if (device.address == bluetoothManager.savedDevice.deviceAddress) {
                        bluetoothManager.stopScanning()
                        bluetoothManager.setDiscoveryCallbacks(null)
                        showSnackbar("Connecting to " + bluetoothManager.savedDevice.deviceName + "...", Snackbar.LENGTH_SHORT)
                        Log.d("MAIN ACTIVITY", "Connecting to " + bluetoothManager.savedDevice.deviceName + "...")
                        bluetoothManager.connectToDevice(device)
                    }
                }

                override fun onDeviceDiscoveryStopped() {
                    if (!bluetoothManager.isDeviceConnected) {
                        showSnackbar(bluetoothManager.savedDevice.deviceName + " is offline. Restart the device", Snackbar.LENGTH_SHORT)
                        Log.d("MAIN ACTIVITY", bluetoothManager.savedDevice.deviceName + " is offline. Restart the device")
                    }
                }
            })
            bluetoothManager.scanDevices()
        } else {
            showSnackbar("Connect a device.", Snackbar.LENGTH_SHORT)
            Log.d("MAIN ACTIVITY", "Connect a device.")
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onBackPressed() {
        if (requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
            super.onBackPressed()
        else requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    @AfterPermissionGranted(RC_LOCATION)
    fun afterPermissionGranted() {
        try {
            if (!EasyPermissions.hasPermissions(this, StateCodes.PermissionLocation)) {
                EasyPermissions.requestPermissions(
                    this,
                    getString(R.string.rationale_phone_state),
                    RC_LOCATION,
                    StateCodes.PermissionLocation
                )
            } else {
                bluetoothManager.checkLocationRequirements()
            }
        } catch (e: Exception) {
            Log.e("scan permission error", "error: " + e.message)
        }
    }

}

