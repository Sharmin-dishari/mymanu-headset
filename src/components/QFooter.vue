<template>
  <div class="foot-btn row flex-center justify-around">
    <q-btn rounded no-caps @click="$router.push({ name: 'dashboard-index' })">
      <div class="person-icon row flex-center bg-red">
        <q-avatar class="q-pa-none q-ma-none">
          <q-icon
            name="home"
            size="30px"
            color="white"
            @click="$router.push({ name: 'dashboard' })"
          />
        </q-avatar>
        <span></span>
      </div>
      <div class="q-mx-lg">Home</div>
    </q-btn>
    <q-btn rounded no-caps>
      <div class="person-icon row flex-center bg-red">
        <q-avatar class="q-pa-none q-ma-none">
          <q-icon
            name="person_outline"
            size="30px"
            color="white"
            @click="$router.push({ name: 'dashboard' })"
          />
        </q-avatar>
        <span></span>
      </div>
      <div class="q-mx-lg">Profile</div>
    </q-btn>
  </div>
</template>

<script setup></script>

<style>
.foot-btn {
  height: 71px;
  background: #ffff;
  border-radius: 90px;
  opacity: 0px;
}
.person-icon {
  width: 50px;
  margin-left: -10px;
  height: 50px;
  border-radius: 90px;
  /* opacity: 0px; */
  /* background: rgb(228, 226, 226); */
}
</style>
