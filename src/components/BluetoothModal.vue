<template>
  <q-dialog v-model="internalShow">
    <q-card>
      <q-card-section>
        <div class="text-h6">Bluetooth Devices</div>
      </q-card-section>

      <q-separator />

      <q-card-section>
        <div>
          <div class="text-subtitle1">Connected Devices</div>
          <q-list>
            <q-item v-for="device in connectedDevices" :key="device.deviceId">
              <q-item-section>{{
                device.name || device.deviceId
              }}</q-item-section>
              <q-item-section side>
                <q-btn
                  @click="disconnect(device.deviceId)"
                  color="negative"
                  label="Disconnect"
                />
              </q-item-section>
            </q-item>
          </q-list>

          <div class="text-subtitle1">Available Devices</div>
          <q-list>
            <q-item v-for="device in availableDevices" :key="device.deviceId">
              <q-item-section>{{
                device.name || device.deviceId
              }}</q-item-section>
              <q-item-section side>
                <q-btn
                  @click="connect(device.deviceId)"
                  color="primary"
                  label="Connect"
                />
              </q-item-section>
            </q-item>
          </q-list>
        </div>
      </q-card-section>

      <q-card-actions align="right">
        <q-btn flat label="Close" @click="internalShow = false" />
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script>
import { ref, watch, onMounted } from "vue";
import {
  requestBluetoothPermissions,
  scanForDevices,
  getConnectedDevices,
  connectToDevice,
  disconnectFromDevice,
} from "src/services/bluetoothService";
import { BleClient } from "@capacitor-community/bluetooth-le"; // Ensure this is imported

export default {
  props: {
    show: {
      type: Boolean,
      required: true,
    },
  },
  setup(props, { emit }) {
    const internalShow = ref(props.show);
    const connectedDevices = ref([]);
    const availableDevices = ref([]);

    watch(
      () => props.show,
      (newVal) => {
        internalShow.value = newVal;
        if (newVal) {
          loadDevices();
        }
      }
    );

    watch(internalShow, (newVal) => {
      emit("update:show", newVal);
    });

    const loadDevices = async () => {
      await requestBluetoothPermissions();
      connectedDevices.value = await getConnectedDevices();
      availableDevices.value = await scanForDevices();
    };

    const connect = async (deviceId) => {
      await connectToDevice(deviceId);
      loadDevices(); // Refresh device lists
    };

    const disconnect = async (deviceId) => {
      await disconnectFromDevice(deviceId);
      loadDevices(); // Refresh device lists
    };

    const checkBluetoothStatus = async () => {
      try {
        await requestBluetoothPermissions();
        const devices = await scanForDevices();
        if (devices.length > 0) {
          internalShow.value = true;
        } else {
          console.error(
            "No Bluetooth devices found. Requesting to turn on Bluetooth."
          );
          await BleClient.enable();
          const devicesAfterEnable = await scanForDevices();
          if (devicesAfterEnable.length > 0) {
            internalShow.value = true;
          } else {
            console.error(
              "No Bluetooth devices found after enabling Bluetooth."
            );
          }
        }
      } catch (error) {
        console.error("Error checking Bluetooth status:", error);
      }
    };

    onMounted(checkBluetoothStatus);

    return {
      internalShow,
      connectedDevices,
      availableDevices,
      connect,
      disconnect,
    };
  },
};
</script>
