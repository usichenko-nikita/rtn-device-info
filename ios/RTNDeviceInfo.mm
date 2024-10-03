#import "RTNDeviceInfo.h"
#import <React/RCTUtils.h>
#import <UIKit/UIKit.h>

@implementation RTNDeviceInfo

RCT_EXPORT_MODULE();

- (void)getBatteryLevel:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject {
    UIDevice *device = [UIDevice currentDevice];
    device.batteryMonitoringEnabled = YES;
    float batteryLevel = device.batteryLevel;

    if (batteryLevel < 0) {
        NSError *error = [NSError errorWithDomain:@"BatteryError" code:404 userInfo:@{NSLocalizedDescriptionKey:@"Unable to retrieve battery level"}];
        reject(@"battery_level_error", @"Unable to retrieve battery level", error);
    } else {
        NSNumber *level = [[NSNumber alloc] initWithFloat:batteryLevel * 100]; // Convert to percentage
        resolve(level);
    }
}

- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params {
  return std::make_shared<facebook::react::NativeRTNDeviceInfoSpecJSI>(params);
}

@end
