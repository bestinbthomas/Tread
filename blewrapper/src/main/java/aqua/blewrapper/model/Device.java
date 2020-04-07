package aqua.blewrapper.model;

import android.content.Context;

/**
 * Created by Saurabh on 09-01-2018.
 */

public class Device {

    private static final String deviceName = "deviceName";
    private static final String deviceAddress = "deviceAddress";
    private Context context;

    public Device(Context context) {
        this.context = context;
    }

    public String getDeviceName() {
        return PreferenceClass.getInstance(context).getString(deviceName, "");
    }

    public void setDeviceName(String deviceName) {
        PreferenceClass.getEditor(context).putString(deviceName, deviceName).apply();
    }

    public String getDeviceAddress() {
        return PreferenceClass.getInstance(context).getString(deviceAddress, "");
    }

    public void setDeviceAddress(String deviceAddress) {
        PreferenceClass.getEditor(context).putString(Device.deviceAddress, deviceAddress).apply();
    }

    @Override
    public String toString() {
        return "device name: "+ deviceName + ", address: " + deviceAddress;
    }
}
