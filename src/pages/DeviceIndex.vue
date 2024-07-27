<template>
  <q-page>
    <q-header class="bg-white text-black q-pa-md">
      <div class="row justify-between">
        <div>
          <q-btn
            icon="arrow_back"
            outline
            color="text-grey-6"
            round
            @click="$router.go(-1)"
          />
        </div>
        <div class="text-center text-h6 q-mt-xs">My Device</div>
        <div>
          <q-btn
            icon="logout"
            outline
            color="text-grey-6"
            round
            @click="handleLogout"
          />
        </div>
        <!-- <div>
          <q-btn
            icon="menu"
            outline=""
            color="text-grey-6"
            round
          />
        </div> -->
      </div>
    </q-header>
    <div class="row justify-center" align="center">
      <q-btn
        label="Connected device via"
        color="primary"
        glossy
        rounded
        outline=""
        no-caps
        icon-right="bluetooth"
      />
    </div>
    <div class="flex flex-center q-mt-md">
      <q-img width="300px" src="/sounds.png" />
    </div>
    <div class="flex flex-center q-mt-md">
      <img
        class="q-mt-md"
        src="/earbuds.png"
        style="transform: rotate(0deg); max-width: 550px"
      />
    </div>
    <div style="border-radius: 10px; background-color: #f5f5f5" class="q-ma-sm">
      <div class="row q-pa-md q-gutter-x-md q-pt-lg justify-between">
        <q-card class="mycard q-pa-sm">
          <div class="row justify-between">
            <div class="q-ma-xs q-mb-lg">
              <q-btn square class="btnBorder" color="grey-2" unelevated>
                <q-icon
                  name="done"
                  color="black"
                  style="border: 1px solid black; border-radius: 5px"
                  size="15px"
                />
              </q-btn>
            </div>
            <div>
              <q-btn flat icon="navigate_next" color="red" size="md" round />
            </div>
          </div>
          <div>Update Firmware</div>
        </q-card>
        <q-card
          class="mycard q-pa-sm"
          @click="$router.push({ name: 'manage-esim' })"
        >
          <div class="row justify-between">
            <div class="q-ma-xs q-mb-lg">
              <q-btn square class="btnBorder" color="grey-2" unelevated>
                <q-icon
                  name="sim_card"
                  color="black"
                  style="border: 1px solid black; border-radius: 5px"
                  size="15px"
                />
              </q-btn>
            </div>
            <div>
              <q-btn flat icon="navigate_next" color="red" size="md" round />
            </div>
          </div>
          <div>Add esim</div>
        </q-card>
      </div>
      <!-- <div class="row q-px-md q-gutter-md justify-between q-pb-lg">
        <q-card class="mycard q-pa-sm">
          <div class="row justify-between">
            <div class="q-ma-xs q-mb-lg">
              <q-btn square class="btnBorder" color="grey-2" unelevated>
                <q-icon
                  name="image"
                  color="black"
                  style="border: 1px solid black; border-radius: 5px"
                  size="15px"
                />
              </q-btn>
            </div>
            <div>
              <q-btn flat icon="navigate_next" color="red" size="md" round />
            </div>
          </div>
          <div>Update Wallpaper</div>
        </q-card>
        <q-card
          class="mycard q-pa-sm"
          @click="$router.push({ name: 'settings-index' })"
        >
          <div class="row justify-between">
            <div class="q-ma-xs q-mb-lg">
              <q-btn square class="btnBorder" color="grey-2" unelevated>
                <q-icon
                  name="linear_scale"
                  color="black"
                  style="border: 1px solid black; border-radius: 5px"
                  size="15px"
                />
              </q-btn>
            </div>
            <div>
              <q-btn flat icon="navigate_next" color="red" size="md" round />
            </div>
          </div>
          <div>More Option</div>
        </q-card>
      </div> -->
    </div>
    <q-footer class="footer">
      <QFooter />
    </q-footer>
  </q-page>
</template>

<script setup>
import QFooter from "../components/QFooter.vue";
import { useCounterStore } from "../stores/example-store";
const commonStore = useCounterStore();
import { useRouter } from "vue-router";
const router = useRouter();
const handleLogout = () => {
  commonStore.logout();
  router.push({ name: "sign-index" });
};
</script>

<style>
.btnBorder {
  width: 38px;
  height: 38px;
  gap: 0px;
  border-radius: 10px;
  opacity: 0px;
}
.mycard {
  border-radius: 10px;
  height: 100px;
  width: 45%;
}
.footer {
  position: fixed;
  left: 0;
  bottom: 0;
  width: 100%;
  z-index: 1;
}
</style>
