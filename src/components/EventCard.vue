<template>
  <div>
    <div class="scroll-wrapper">
      <div class="horizontal-scroll-container">
        <q-card class="my-card option q-ma-md" v-for="item in 3" :key="item.id">
          <img src="/slider.jpeg" style="max-width: 300px; height: 150px" />

          <q-card-section>
            <div class="text-h6 text-bold">Mymanu CLIK Pro</div>
            <div class="text-subtitle2 text-grey">Mymanu CLIK S</div>
          </q-card-section>
        </q-card>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style scoped></style>
