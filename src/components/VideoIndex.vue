<template>
  <div>
    <div class="scroll-wrapper">
      <div class="horizontal-scroll-container" v-if="videoList.length">
        <div v-for="item in videoList" :key="item.id" class="option">
          <q-card
            class="vedio-event bg-grey-2"
            :class="
              $q.dark.isActive ? 'dark-card text-white' : 'bg-white text-black'
            "
          >
            <div class="row justify-between q-mt-sm" @click="openModal(item)">
              <div>{{ title }}</div>
              <q-btn
                round
                unelevated
                dense
                size="10px"
                icon="arrow_forward_ios"
                class="text-black"
                color="white"
              />
            </div>

            <div class="container q-mt-md" @click="openModal(item)">
              <div class="container q-mt-md">
                <q-video
                  :ratio="16 / 13"
                  style="border-radius: 12px"
                  :src="item"
                />
              </div>
            </div>
          </q-card>
        </div>
      </div>
    </div>
    <q-dialog v-model="modalOpen" persistent full-width>
      <q-card>
        <q-card-section>
          <q-video
            :ratio="16 / 13"
            style="border-radius: 12px"
            :src="selectedVideo"
          />
        </q-card-section>
        <q-card-actions align="right" class="q-mr-sm">
          <q-btn label="Close" no-caps color="red-10" @click="closeModal" />
        </q-card-actions>
      </q-card>
    </q-dialog>
  </div>
</template>
<script setup>
import { ref } from "vue";
defineProps({
  videoList: {
    type: Array,
    default: () => [],
  },
  title: {
    type: String,
    default: () => "CLIK Pro",
  },
});
const modalOpen = ref(false);

const selectedVideo = ref(null);
const openModal = (item) => {
  modalOpen.value = true;
  selectedVideo.value = item;
};
const closeModal = () => {
  modalOpen.value = false;
  selectedVideo.value = null;
};
</script>

<style>
.vedio-event {
  max-width: 220px;
  height: 229px;
  border: 1px solid #ddd;
  padding: 10px;
  position: relative;
  border-radius: 25px;
}
</style>
