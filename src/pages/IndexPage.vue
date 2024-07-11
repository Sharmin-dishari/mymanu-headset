<template>
  <q-page class="bg-grey-2">
    <q-header class="bg-grey-2 text-black q-pa-md"></q-header>
    <div class="text-h4 q-px-md text-center text-bold text-black">
      Explore Premium
    </div>
    <div class="text-h4 q-px-md text-center text-bold text-red">Sounds</div>
    <div class="flex flex-center q-mt-md">
      <q-img width="300px" src="/sounds.png" />
    </div>
    <EventCard />
    <div class="q-px-md text-black text-h6">Recommended Tutorial</div>
    <div class="q-pa-md"><VideoIndex :video-list="videoList" /></div>
    <q-page-sticky position="bottom" :offset="[18, 4]">
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
                  class="text-white"
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
          </div></q-card-section
        >
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
  </q-page>
</template>

<script setup>
import EventCard from "../components/EventCard.vue";
import { ref } from "vue";
import VideoIndex from "../components/VideoIndex.vue";
const videoList = ref(["https://www.youtube.com/embed/k3_tw44QsZQ?rel=0"]);
const addDevice = ref(false);
</script>

<style scoped>
.book-btn {
  width: 295px;
  height: 50.14px;
  border-radius: 15px;
  background: #fc002b;
}
.horizontal-scroll-container {
  overflow-x: auto;
  position: relative;
  white-space: nowrap;
}
.scroll-wrapper {
  overflow: hidden; /* Hide the scrollbar */
}
.option {
  display: inline-block;
}
</style>
