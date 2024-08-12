import { BleClient, BleDevice } from "@capacitor-community/bluetooth-le";

export async function requestBluetoothPermissions() {
  try {
    await BleClient.initialize();
    console.log("Bluetooth initialized");
  } catch (error) {
    console.error("Bluetooth initialization failed", error);
    throw error;
  }
}

export async function scanForDevices() {
  const devices = [];
  try {
    await BleClient.requestLEScan({}, (result) => {
      if (
        result.device &&
        !devices.some((d) => d.deviceId === result.device.deviceId)
      ) {
        devices.push(result.device);
      }
    });
    // Scan for 5 seconds
    await new Promise((resolve) => setTimeout(resolve, 5000));
    await BleClient.stopLEScan();
  } catch (error) {
    console.error("Bluetooth scan failed", error);
    throw error;
  }
  return devices;
}

export async function getConnectedDevices() {
  const connectedDevices = [];
  try {
    const allDevices = await BleClient.getConnectedDevices();
    for (const device of allDevices) {
      connectedDevices.push(device);
    }
  } catch (error) {
    console.error("Failed to get connected devices", error);
    throw error;
  }
  return connectedDevices;
}

export async function connectToDevice(deviceId) {
  try {
    await BleClient.connect(deviceId, () => {
      console.log(`Disconnected from device ${deviceId}`);
    });
    console.log(`Connected to device ${deviceId}`);
  } catch (error) {
    console.error(`Failed to connect to device ${deviceId}`, error);
    throw error;
  }
}

export async function disconnectFromDevice(deviceId) {
  try {
    await BleClient.disconnect(deviceId);
    console.log(`Disconnected from device ${deviceId}`);
  } catch (error) {
    console.error(`Failed to disconnect from device ${deviceId}`, error);
    throw error;
  }
}
