<template>
  <q-page class="bg-grey-2" style="padding-bottom: 80px">
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
          <q-btn icon="add" outline color="text-grey-6" round>
            <q-menu>
              <div class="bg-grey-2">
                <q-item-section>
                  <div class="text-center q-py-xs q-mx-xl">
                    <q-btn class="add-btn" no-caps type="submit" unelevated>
                      <div class="row justify-between" style="width: 100%">
                        <div class="q-ma-xs q-mb-lg row">
                          <q-btn
                            square
                            class="btnBorder"
                            color="green-2"
                            unelevated
                          >
                            <q-icon
                              name="done"
                              color="green"
                              size="15px"
                              style="border-radius: 1px solid green"
                            />
                          </q-btn>
                          <div class="text-black q-mt-xs q-ml-sm">Active</div>
                        </div>

                        <div>
                          <q-btn
                            flat
                            icon="navigate_next"
                            color="black"
                            size="md"
                            round
                          />
                        </div>
                      </div>
                    </q-btn></div
                ></q-item-section>
              </div>
              <div class="q-py-none bg-grey-2">
                <q-item-section>
                  <div class="text-center q-py-xs q-mx-xl">
                    <q-btn class="add-btn" no-caps type="submit" unelevated>
                      <div class="row justify-between" style="width: 100%">
                        <div class="q-ma-xs q-mb-lg row">
                          <q-btn
                            square
                            class="btnBorder"
                            color="red-2"
                            unelevated
                          >
                            <q-icon name="delete" color="red" size="15px" />
                          </q-btn>
                          <div class="text-black q-mt-xs q-ml-sm">Delete</div>
                        </div>

                        <div>
                          <q-btn
                            flat
                            icon="navigate_next"
                            color="black"
                            size="md"
                            round
                          />
                        </div>
                      </div>
                    </q-btn></div
                ></q-item-section>
              </div>
            </q-menu>
          </q-btn>
        </div>
      </div>
    </q-header>
    <div class="q-mt-md">
      <q-card class="q-ma-md border-card" v-for="item in 3" :key="item">
        <q-card-section>
          <div class="row justify-between">
            <div class="q-ma-xs q-mb-lg row">
              <div>
                <q-btn square class="btnBorder" color="red" unelevated outline>
                  <img src="public/Frame.png" />
                </q-btn>
              </div>
              <div class="q-ml-md">
                <div>01714200080</div>
                <div>T-mobile</div>
              </div>
            </div>
            <div>
              <q-btn icon="more_vert" flat />
            </div>
          </div>
        </q-card-section>
        <q-card-section>
          <q-card bordered flat class="border-card">
            <div class="row justify-between q-px-md">
              <q-card-section class="q-px-none">
                <div>
                  <div>
                    <q-icon name="public" color="red" size="sm" />
                  </div>
                  <div class="q-mt-sm">Data Plan</div>
                  <div class="text-h6">10Gb</div>
                </div>
              </q-card-section>
              <q-card-section class="q-px-none">
                <div class="vl q-mt-sm"></div>
              </q-card-section>
              <q-card-section class="q-px-none">
                <div>
                  <div>
                    <q-icon name="alarm_on" color="red" size="sm" />
                  </div>
                  <div class="q-mt-sm">Duration</div>
                  <div class="text-h6">30 Days</div>
                </div>
              </q-card-section>
              <q-card-section class="q-px-none">
                <div class="vl q-mt-sm"></div>
              </q-card-section>
              <q-card-section class="q-px-none">
                <div>
                  <div>
                    <q-icon name="task_alt" color="red" size="sm" />
                  </div>
                  <div class="q-mt-sm">Enabled</div>
                  <div class="text-h6">2 may 2024</div>
                </div>
              </q-card-section>
            </div>
          </q-card>
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
.add-btn {
  width: 295px;
  height: 50.14px;
  border-radius: 15px;
  background: white;
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
.vl {
  border-left: 2px solid rgb(236, 241, 236);
  height: 70px;
}
.border-card {
  border-radius: 14px;
}
</style>
