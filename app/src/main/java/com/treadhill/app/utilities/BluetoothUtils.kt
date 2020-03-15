package com.treadhill.app.utilities

import android.bluetooth.*
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*
import kotlin.collections.ArrayList

class BluetoothUtils(private val context: Context) {
    private val adapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    private val TAG = "BLUETOOTH"
    private val mutableDevices = MutableLiveData<ArrayList<BluetoothDevice>>()
    private val STATE_DISCONNECTED = 0
    private val STATE_CONNECTING = 1
    private val STATE_CONNECTED = 2
    val ACTION_GATT_CONNECTED = "com.example.bluetooth.le.ACTION_GATT_CONNECTED"
    val ACTION_GATT_DISCONNECTED = "com.example.bluetooth.le.ACTION_GATT_DISCONNECTED"
    val ACTION_GATT_SERVICES_DISCOVERED =
        "com.example.bluetooth.le.ACTION_GATT_SERVICES_DISCOVERED"
    val ACTION_DATA_AVAILABLE = "com.example.bluetooth.le.ACTION_DATA_AVAILABLE"
    val EXTRA_DATA = "com.example.bluetooth.le.EXTRA_DATA"
    val UUID_HEART_RATE_MEASUREMENT = UUID.fromString("00002a37-0000-1000-8000-00805f9b34fb")
    val devices: LiveData<ArrayList<BluetoothDevice>>
        get() = mutableDevices

    init {
        mutableDevices.value = ArrayList()
    }

    fun checkBluetooth(): BluetoothStatus {
        if (adapter == null)
            return BluetoothStatus.NOT_SUPPORTED
        if (!adapter.isEnabled) return BluetoothStatus.DISABLED
        if (adapter.isDiscovering) return BluetoothStatus.SEARCHING


        return BluetoothStatus.ENABLED
    }

    fun scanBle() {
        val gattCallback = object : BluetoothGattCallback() {
            override fun onConnectionStateChange(
                gatt: BluetoothGatt,
                status: Int,
                newState: Int
            ) {
                val intentAction: String
                when (newState) {
                    BluetoothProfile.STATE_CONNECTED -> {
//                        intentAction = ACTION_GATT_CONNECTED
                        //connectionState = STATE_CONNECTED
                        //broadcastUpdate(intentAction)
                        Log.i(TAG, "Connected to GATT server.")
                        Log.i(
                            TAG,
                            "Attempting to start service discovery: " + gatt.discoverServices()
                        )
                    }
                    BluetoothProfile.STATE_DISCONNECTED -> {
                        intentAction = ACTION_GATT_DISCONNECTED
                        //connectionState = STATE_DISCONNECTED
                        Log.i(TAG, "Disconnected from GATT server.")
                        //broadcastUpdate(intentAction)
                    }
                }
            }

            // New services discovered
            override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
                val uuid: UUID
                when (status) {
                    BluetoothGatt.GATT_SUCCESS -> {
                        gatt.device.uuids?.forEach {
                            Log.e("UUID", it.uuid.toString())
                        }
                        Log.e("SERVICE FOUND", gatt.device.name + gatt.device.uuids)
                    }//broadcastUpdate(ACTION_GATT_SERVICES_DISCOVERED)
                    else -> Log.w(TAG, "onServicesDiscovered received: $status")
                }
            }

            // Result of a characteristic read operation
            override fun onCharacteristicRead(
                gatt: BluetoothGatt,
                characteristic: BluetoothGattCharacteristic,
                status: Int
            ) {

                Log.e("CHAR READ", "read char")
                Log.e("CHAR READ", gatt.readCharacteristic(characteristic).toString())
                Log.e("CHAR READ", characteristic.value.toString())
                when (status) {
                    BluetoothGatt.GATT_SUCCESS -> {
                        //broadcastUpdate(ACTION_DATA_AVAILABLE, characteristic)
                        Log.e("GOT CHAR UUID", characteristic.uuid.toString())
                        Log.e("GOT CHAR value", characteristic.value.toString())
                    }
                }
            }
        }
        if (checkBluetooth() == BluetoothStatus.ENABLED) {
            Log.i(TAG, "Scan Started")
//            adapter!!.startLeScan { device, rssi, scanRecord ->
//                Log.i("SCAN","device found")
//                val devices = devices.value
//                devices!!.add(device)
//                Log.e(TAG,"${device.address }\n"+
//                        "${device.name }\n"+
//                        "${device.type }\n" +
//                        "${device.uuids}")
//
//                device.uuids?.forEach {
//                    Log.e("UUID",it.uuid.toString())
//                }
//                mutableDevices.value = devices
//            }

            //BluetoothLeScanner
            val scanCallback = object : ScanCallback() {
                override fun onScanFailed(errorCode: Int) {
                    super.onScanFailed(errorCode)
                    Log.e(TAG, "scan failed")
                }

                override fun onScanResult(callbackType: Int, result: ScanResult?) {
                    super.onScanResult(callbackType, result)
                    result?.device?.let {
                        if (it.address == "DB:40:C2:FF:CC:8B") {
                            Log.e(
                                TAG, "${it.address}\n" +
                                        "${it.name}\n" +
                                        "${it.type}\n" +
                                        "${it.uuids}"
                            )

                            it.connectGatt(context, true, gattCallback)

                            it.uuids?.forEach { uuid ->
                                Log.e("UUID", uuid.uuid.toString())
                            }
                        }
                    }
                }

            }
            adapter!!.bluetoothLeScanner.startScan(scanCallback)
        }
    }

    enum class BluetoothStatus(device: String? = null) {
        NOT_SUPPORTED,
        DISABLED,
        ENABLED,
        SEARCHING,
        CONNECTING,
        CONNECTED()
    }
}