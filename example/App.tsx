import React, {useState} from 'react';
import {SafeAreaView, StatusBar, Text, Button, StyleSheet} from 'react-native';
import RTNDeviceInfo from 'rtn-device-info/js/NativeRTNDeviceInfo';

const App = () => {
  const [batteryLevel, setBatteryLevel] = useState<number | null>(null);

  const handleGetBatteryLevel = async () => {
    try {
      const level = await RTNDeviceInfo?.getBatteryLevel();
      if (level) {
        setBatteryLevel(level);
      }
    } catch (error) {
      console.error('Error getting battery level:', error);
    }
  };

  return (
    <SafeAreaView style={styles.container}>
      <StatusBar barStyle="dark-content" />
      <Text style={{margin: 20}}>
        Battery Level: {batteryLevel !== null ? `${batteryLevel}%` : 'Unknown'}
      </Text>
      <Button title="Get Battery Level" onPress={handleGetBatteryLevel} />
    </SafeAreaView>
  );
};

export default App;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#FFFFFF',
  },
});
