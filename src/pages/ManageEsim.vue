<template>
  <q-page class="bg-grey-2">
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
        <div></div>
      </div>
    </q-header>
    <div>
      <q-card class="q-ma-md" @click="$router.push({ name: 'esim-details' })">
        <q-card-section align="center">
          <q-avatar
            size="150px"
            color="grey-2"
            style="border-radius: 50%"
            text-color="white"
          >
            <div class="q-pa-sm"><img src="public/cart.png" /></div>
          </q-avatar>
          <div class="q-pt-sm text-h6 text-bold text-grey-9">Unlock</div>
          <div class="text-subtitle2">new opportunity</div>
          <div class="text-center q-py-md q-mx-xl">
            <q-btn class="book-btn" no-caps type="submit">
              <div class="row justify-between" style="width: 100%">
                <div class="q-ma-xs q-mb-lg row">
                  <q-btn square class="btnBorder" color="grey-2" unelevated>
                    <q-icon name="sim_card" color="red" size="15px" />
                  </q-btn>
                  <div class="text-white q-mt-xs q-ml-sm">Buy Esim</div>
                </div>

                <div>
                  <q-btn
                    flat
                    icon="navigate_next"
                    color="white"
                    size="md"
                    round
                  />
                </div>
              </div>
            </q-btn>
          </div>
        </q-card-section>
      </q-card>
      <q-card class="q-ma-md">
        <q-card-section align="center">
          <q-avatar
            size="150px"
            color="grey-2"
            style="border-radius: 50%"
            text-color="white"
          >
            <div class="q-pa-sm"><img src="public/qr-scan.png" /></div>
          </q-avatar>
          <div class="q-pt-sm text-h6 text-bold text-grey-9">
            Scan to add <span class="text-bold">Esim </span>
          </div>
          <div class="text-subtitle2">Profile</div>
          <div class="text-center q-py-md q-mx-xl">
            <q-btn class="book-btn" no-caps type="submit">
              <div class="row justify-between" style="width: 100%">
                <div class="q-ma-xs q-mb-lg row">
                  <q-btn square class="btnBorder" color="grey-2" unelevated>
                    <q-icon name="add" color="red" size="15px" />
                  </q-btn>
                  <div class="text-white q-mt-xs q-ml-sm">Add Esim</div>
                </div>

                <div>
                  <q-btn
                    flat
                    icon="navigate_next"
                    color="white"
                    size="md"
                    round
                  />
                </div>
              </div>
            </q-btn>
          </div>
        </q-card-section>
      </q-card>
    </div>
    <q-footer class="footer">
      <QFooter />
    </q-footer>
  </q-page>
</template>

<script setup>
import QFooter from "../components/QFooter.vue";
</script>

<style scoped>
.book-btn {
  width: 295px;
  height: 50.14px;
  border-radius: 15px;
  background: rgb(211, 15, 15);
}
.btnBorder {
  width: 38px;
  height: 30px;
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
