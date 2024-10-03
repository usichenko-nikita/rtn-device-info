// DeviceInfoModule.java

package com.rtndeviceinfo;

import androidx.annotation.NonNull;
import android.content.Context;
import android.os.BatteryManager;
import android.util.Log;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.rtndeviceinfo.NativeRTNDeviceInfoSpec;

public class DeviceInfoModule extends NativeRTNDeviceInfoSpec {

    public static final String NAME = "RTNDeviceInfo";
    private static final String TAG = "DeviceInfoModule";

    public DeviceInfoModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    @NonNull
    public String getName() {
        return NAME;
    }


    @Override
    public void getBatteryLevel(Promise promise) {
        try {
            BatteryManager batteryManager = (BatteryManager) getReactApplicationContext().getSystemService(Context.BATTERY_SERVICE);
            if (batteryManager != null) {
                int batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
                Log.d(TAG, "Battery level is " + batteryLevel);
                promise.resolve((double) batteryLevel);
            } else {
                Log.e(TAG, "BatteryManager is null");
                promise.reject("battery_unavailable", "BatteryManager is null");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error getting battery level", e);
            promise.reject("battery_error", "Error getting battery level", e);
        }
    }
}
