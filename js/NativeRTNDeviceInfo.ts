import { TurboModule, TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
    getBatteryLevel(): Promise<number>;
}

export default TurboModuleRegistry.get<Spec>("RTNDeviceInfo") as Spec | null;
