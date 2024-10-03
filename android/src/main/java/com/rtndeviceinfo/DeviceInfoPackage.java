package com.rtndeviceinfo;

import androidx.annotation.Nullable;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.TurboReactPackage;

import java.util.HashMap;
import java.util.Map;

public class DeviceInfoPackage extends TurboReactPackage {

  @Nullable
  @Override
  public NativeModule getModule(String name, ReactApplicationContext reactContext) {
    if (name.equals(DeviceInfoModule.NAME)) {
      return new DeviceInfoModule(reactContext);
    }
    return null;
  }

  @Override
  public ReactModuleInfoProvider getReactModuleInfoProvider() {
    return () -> {
      final Map<String, com.facebook.react.module.model.ReactModuleInfo> moduleInfos = new HashMap<>();
      moduleInfos.put(
        DeviceInfoModule.NAME,
        new com.facebook.react.module.model.ReactModuleInfo(
          DeviceInfoModule.NAME,
          DeviceInfoModule.NAME,
          false, // canOverrideExistingModule
          false, // needsEagerInit
          false, // hasConstants
          false, // isCxxModule
          true   // isTurboModule
        )
      );
      return moduleInfos;
    };
  }
}
