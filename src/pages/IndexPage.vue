<template>
  <q-page class="bg-grey-2" style="padding-bottom: 70px">
    <q-header class="bg-grey-2 text-black q-pa-md"></q-header>

    <div class="text-h4 q-px-md text-center text-bold text-black">
      Explore Premium
    </div>
    <div class="text-h4 q-px-md text-center text-bold text-red">Sounds</div>
    <div
      v-if="loading"
      style="
        height: 70vh;
        display: flex;
        justify-content: center;
        align-items: center;
      "
    >
      <q-spinner-grid color="purple-4" size="3em" />
    </div>
    <div v-else>
      <div class="flex flex-center q-mt-md" @click="checkBluetoothStatus">
        <q-img width="300px" src="/sounds.png" />
      </div>
      <BluetoothModal :show="showModal" @update:show="showModal = $event" />

      <div class="">
        <div class="scroll-wrapper" v-if="eventList.length > 1">
          <div class="horizontal-scroll-container">
            <div
              v-for="item in eventList"
              :key="item.id"
              @click="(showImage = true), (selectedImage = item.eventImg)"
            >
              <q-card class="option q-ma-md art-event-gallery">
                <q-img
                  :src="item.eventImg"
                  alt="Snow"
                  style="border-radius: 20px; max-width: 450px"
                />
                <q-card-section>
                  <div class="text-h6 text-bold">{{ item.eventTitle }}</div>
                  <div class="text-subtitle2 text-black ellipsis">
                    {{ item.eventSummary.substring(0, 30) }}...
                  </div>
                  <q-tooltip class="bg-purple-5">
                    <div>{{ item.eventSummary }}</div>
                  </q-tooltip>
                </q-card-section>
              </q-card>
            </div>
          </div>
        </div>
        <div v-else class="scroll-wrapper">
          <div class="horizontal-scroll-container container">
            <div v-for="item in eventList" :key="item.id">
              <q-card class="option q-ma-md art-event-gallery-single">
                <q-img
                  :src="item.eventImg"
                  alt="Snow"
                  @click="(showImage = true), (selectedImage = item.eventImg)"
                  style="border-radius: 20px; width: 300px"
                />

                <q-card-section>
                  <div class="text-h6 text-bold text-black">
                    {{ item.eventTitle }}
                  </div>
                  <div class="text-subtitle2 text-black ellipsis">
                    {{ item.eventSummary.substring(0, 30) }}...
                  </div>
                  <q-tooltip class="bg-purple-5">
                    <div>{{ item.eventSummary }}</div>
                  </q-tooltip>
                </q-card-section>
              </q-card>
            </div>
          </div>
        </div>
      </div>
      <div class="q-px-md text-black text-h6">Recommended Tutorial</div>
      <div class="q-pa-md">
        <VideoIndex
          v-if="videoList.length"
          :video-list="videoList[0].videolist"
        />
      </div>
    </div>
    <q-page-sticky position="bottom" :offset="[18, 0]">
      <q-card-actions class="q-pt-none" align="center">
        <div class="text-center q-py-md">
          <q-btn class="book-btn q-mt-lg" no-caps @click="addDevice = true">
            <div class="row text-white">
              <div class="q-mt-xs text-subtitle1 text-weight-medium">
                Add a device
              </div>
              <div class="q-ml-md">
                <q-btn
                  round
                  icon="add"
                  size="sm"
                  text-black
                  unelevated
                  color="red-5"
                  class="text-white q-mt-xs"
                />
              </div>
            </div>
          </q-btn>
        </div>
      </q-card-actions>
    </q-page-sticky>
    <q-dialog v-model="addDevice" persistent>
      <q-card class="bg-grey-2">
        <q-card-section class="text-center text-h6">
          List of Devices
        </q-card-section>
        <q-card-section>
          <div class="scroll-wrapper">
            <div class="horizontal-scroll-container">
              <q-card
                class="my-card option q-ma-md"
                v-for="item in 3"
                :key="item.id"
                style="border-radius: 10px"
              >
                <img
                  src="/headset.png"
                  style="
                    transform: rotate(180deg);
                    max-width: 300px;
                    height: 150px;
                  "
                />
                <q-separator class="q-mx-xl"></q-separator>
                <q-card-section>
                  <div class="text-h6 text-bold text-center">Mymanu CLIK S</div>
                </q-card-section>
              </q-card>
            </div>
          </div>
        </q-card-section>
        <q-card-actions align="center">
          <q-btn
            label="Back"
            color="black"
            class="q-px-xl"
            outline
            no-caps
            rounded
            v-close-popup
          />
          <q-btn
            label="Add"
            @click="$router.push({ name: 'device-index' })"
            class="q-px-xl"
            color="red"
            rounded
            no-caps
          />
        </q-card-actions>
      </q-card>
    </q-dialog>
    <q-dialog v-model="showImage">
      <q-card
        :class="{
          'default-dimension': !maximized,
        }"
      >
        <q-bar>
          <div></div>
          <q-space />
          <q-btn dense flat icon="close" v-close-popup>
            <q-tooltip>Close</q-tooltip>
          </q-btn>
        </q-bar>
        <q-card-section>
          <q-img :src="selectedImage" alt="Snow" />
        </q-card-section>
      </q-card>
    </q-dialog>
  </q-page>
</template>

<script setup>
import EventCard from "../components/EventCard.vue";
import { ref, onMounted } from "vue";
import { db, collection } from "src/stores/firebase.js";
import { onSnapshot } from "firebase/firestore";
import VideoIndex from "../components/VideoIndex.vue";
import BluetoothModal from "src/components/BluetoothModal.vue";
import {
  requestBluetoothPermissions,
  scanForDevices,
} from "src/services/bluetoothService"; // Import your Bluetooth service

const selectedImage = ref(null);
const addDevice = ref(false);
const maximized = ref(false);
const showImage = ref(false);
const showModal = ref(false); // Add this line
const loading = ref(false);
const eventList = ref([]);
const videoList = ref([]);

onMounted(async () => {
  loading.value = true;
  const catsRef = collection(db, "RSVPEvents");
  await onSnapshot(catsRef, (snapshot) => {
    eventList.value = [];
    snapshot.docs.forEach((doc) => {
      eventList.value.push({ ...doc.data(), id: doc.id });
    });
  });
  const videos = collection(db, "NewsnFeedvideo");
  videoList.value = [];
  onSnapshot(videos, (snapshot) => {
    snapshot.docs.forEach((doc) => {
      videoList.value.push({ ...doc.data(), id: doc.id });
    });
  });
  setTimeout(() => {
    loading.value = false;
  }, 1000);
});

const checkBluetoothStatus = async () => {
  try {
    await requestBluetoothPermissions();
    const devices = await scanForDevices();
    if (devices.length > 0) {
      showModal.value = true;
    } else {
      console.error(
        "No Bluetooth devices found. Requesting to turn on Bluetooth."
      );
      await BleClient.enable();
      const devicesAfterEnable = await scanForDevices();
      if (devicesAfterEnable.length > 0) {
        showModal.value = true;
      } else {
        console.error("No Bluetooth devices found after enabling Bluetooth.");
      }
    }
  } catch (error) {
    console.error("Error checking Bluetooth status:", error);
  }
};
</script>

<style scoped>
.book-btn {
  width: 195px;
  height: 50.14px;
  border-radius: 15px;
  background: #fc002b;
}
.my-card {
  width: 100%;
  max-width: 220px;
  border-radius: 20px;
}

.horizontal-scroll-container {
  overflow-x: auto;
  position: relative;
  white-space: nowrap;
}
.scroll-wrapper {
  overflow: hidden; /* Hide the scrollbar */
}
.default-dimension {
  width: 700px;
  min-height: 400px;
  border-radius: 20px;
}

.option {
  display: inline-block;
}
.container {
  position: relative;
  text-align: center;
  color: white;
}
.art-event-gallery-single {
  border: 1px solid #ddd; /* Add a 1px solid border in light gray */
  position: relative; /* This allows us to position the button relatively inside the container */
  border-radius: 25px;
  max-width: 450px;
  padding: 15px;
  opacity: 0px;
}
.art-event-gallery {
  border: 1px solid #ddd; /* Add a 1px solid border in light gray */
  position: relative; /* This allows us to position the button relatively inside the container */
  border-radius: 25px;
  width: 300px;
  height: 329px;
  padding: 15px;
  opacity: 0px;
}
</style>
