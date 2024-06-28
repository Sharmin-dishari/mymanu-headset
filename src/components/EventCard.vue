<template>
  <div>
    <div class="scroll-wrapper">
      <div class="horizontal-scroll-container">
        <q-card class="my-card option q-ma-md" v-for="item in 3" :key="item.id">
          <img src="/slider.jpeg" style="max-width: 320px; height: 250px" />

          <q-card-section>
            <div class="text-h5 text-bold">Mymanu CLIK Pro</div>
            <div class="text-subtitle2 text-grey">Mymanu CLIK S</div>
          </q-card-section>
        </q-card>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.my-card {
  width: 100%;
  max-width: 320px;
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
.option {
  display: inline-block;
}
</style>
