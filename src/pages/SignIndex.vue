<template>
  <q-page>
    <div class="row flex-center q-pt-lg"></div>
    <div
      class="q-pa-md text-h6"
      :class="$q.screen.gt.sm ? 'row flex-center' : ''"
    >
      Sign In
    </div>
    <div class="q-px-md">Sign in with your email or user name</div>
    <div class="q-px-md q-pb-md">Don't remember your username or password</div>
    <q-form @submit="handleLogin" style="max-width: 450px; margin: 0 auto">
      <div class="q-pa-md text-red-6">Click Here</div>
      <q-card-section class="q-py-none">
        <div>
          <q-input
            v-model="form.email"
            class="itc-input required q-mb-sm"
            stack-label
            outlined
            placeholder="Enter your email"
            type="email"
            clearable
            clear-icon="close"
            :rules="[
              (val) => !!val || 'Email is required',
              (val) => validateEmail(val) || 'Type a valid Email',
            ]"
          >
            <template #prepend> <q-icon name="email" /></template>
          </q-input>
          <q-input
            v-model="form.password"
            class="itc-input required"
            stack-label
            outlined
            placeholder="Enter your password"
            :type="isPwd ? 'password' : 'text'"
            :rules="[
              (val) => !!val || 'Password is required',
              (val) => val.length >= 6 || 'Minimum 6 characters required',
            ]"
          >
            <template #prepend> <q-icon name="lock" /></template>
            <template #append>
              <q-icon
                :name="isPwd ? 'visibility' : 'visibility_off'"
                class="cursor-pointer"
                @click="isPwd = !isPwd"
              />
            </template>
          </q-input>
        </div>
      </q-card-section>
      <q-card-section class="q-py-none">
        <div class="row justify-between">
          <div class="row">
            <q-toggle
              size="md"
              @update:model-value="darkMode = !darkMode"
              v-model="isRemember"
              val="dark"
            />
            <div class="q-mt-sm">Remember Me</div>
          </div>
          <div class="q-mt-sm cursor-pointer" @click="showModal = true">
            Forgot Password
          </div>
        </div>
      </q-card-section>
      <q-card-actions class="q-pt-none" align="center">
        <div class="text-center q-py-md">
          <q-btn class="book-btn" rounded type="submit">
            <div class="row text-white">
              <div class="q-mt-xs text-bold">Sign In</div>
              <div class="q-ml-md">
                <q-btn
                  round
                  icon="east"
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
    </q-form>
    <div class="bg-color" style="max-width: 450px; margin: 0 auto">
      <div
        class="text-caption text-center q-py-md cursor-pointer"
        @click="$router.push({ name: 'sign-up' })"
      >
        Don't have an account?
        <span class="text-primary q-ml-sm">Sign up</span>
      </div>
    </div>

    <div class="q-py-none" align="center">
      <div class="text-caption text-center q-py-md cursor-pointer">
        By signing in you are agreeing to the Terms of Service
      </div>
    </div>
    <div class="text-center">
      <div class="text-red">Terms of use | Privacy Policy</div>
    </div>
    <q-dialog v-model="showModal" persistent>
      <q-card>
        <q-form
          @submit="handleForgotPassword"
          style="max-width: 450px; margin: 0 auto"
        >
          <q-card-section class="q-pb-none q-mt-lg">
            <div>
              <q-input
                v-model="userEmail"
                class="itc-input required q-mb-sm"
                stack-label
                outlined
                placeholder="Enter your email"
                type="email"
                clearable
                clear-icon="close"
                :rules="[
                  (val) => !!val || 'Email is required',
                  (val) => validateEmail(val) || 'Type a valid Email',
                ]"
              >
                <template #prepend> <q-icon name="email" /></template>
              </q-input>
            </div>
          </q-card-section>
          <q-card-actions class="q-p-none" align="center">
            <div class="text-center q-py-md q-mx-xl">
              <q-btn class="book-btn" rounded type="submit">
                <div class="row text-white">
                  <div class="q-mt-xs text-bold">Submit</div>
                  <div class="q-ml-md">
                    <q-btn
                      round
                      icon="east"
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
        </q-form>
      </q-card>
    </q-dialog>
    <!-- <div class="text-center text-bold">Logged in as {{ myinfo }}</div> -->
  </q-page>
</template>

<script setup>
import { useCounterStore } from "../stores/example-store";
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useQuasar } from "quasar";
const $q = useQuasar();
const showModal = ref(false);
const commonStore = useCounterStore();
const isPwd = ref(true);
const form = ref({
  application_type: "2",
  application_version: "34.1.8",
  device_token:
    "fBAjEtcfRey9cNHydf1Egi:APA91bG79qVAeS4rkci7RaEj9agT3vonAQbDb_fiRA1U1v1zlGHWIeb8SC16gsCPlJ6yMJGmKrCzMixYAsjrXOFYqc6xAY_SNsSBEtZhSQxuUtr4kJDKt4JRCINPgnpG0XNl-64wp1E8",
  device_type: "1",
  email: "dinesh.singh.kunwar@gmail.com",
  password: "Hello@123",
});

const isRemember = ref(true);
const userEmail = ref(null);
const handleLogin = async () => {
  await commonStore.userLogin(form.value);
  $q.notify({
    message: "Login Succesfully",
    icon: "announcement",
    color: "green",
  });
  if (commonStore.userProfile) {
    router.push({ name: "dashboard" });
  }
};
const handleForgotPassword = () => {
  commonStore.ForgotPassword({ email: userEmail.value });
  $q.notify({
    message: "OTP for forgot password has been sent to email",
    color: "green",
    icon: "announcement",
  });
  showModal.value = false;
};
const router = useRouter();

const emailValidationRegex =
  /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
const validateEmail = (val) => {
  return emailValidationRegex.test(val);
};
</script>

<style>
.book-btn {
  width: 295px;
  height: 50.14px;
  background: #fc002b;
}
</style>
