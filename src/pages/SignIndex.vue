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
      <q-card-section class="q-py-none">
        <div>
          <q-input
            v-model="form.email"
            class="required q-mb-sm"
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
            class="required q-mb-lg"
            stack-label
            outlined
            placeholder="Enter your password"
            :type="isPwd ? 'password' : 'text'"
            :rules="[
              (val) => !!val,
              (val) =>
                val.length >= 8 ||
                'Password must contain at least one lowercase letter, one uppercase letter, one digit, and one special character.',
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
      <q-card-section class="q-py-none" align="right">
        <div class="row justify-end">
          <q-toggle
            size="md"
            @update:model-value="handleRemember"
            v-model="rememberMe"
            val="dark"
          />
          <div class="q-mt-sm">Remember Me</div>
        </div>
      </q-card-section>
      <q-card-section class="q-pt-none q-my-none cursor-pointer" align="right">
        Forgot password?
        <q-btn
          flat
          dense
          no-caps
          size=".9rem"
          class="q-py-none"
          color="primary"
          label="Reset password"
          @click="showModal = true"
        />
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
    <q-dialog v-model="showModal">
      <q-card>
        <q-form
          @submit="handleForgotPassword"
          style="max-width: 450px; margin: 0 auto"
        >
          <q-card-section class="q-pb-none q-mt-lg">
            <div>
              <q-input
                v-model="userEmail"
                class="required q-mb-sm"
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
import { ref, onMounted } from "vue";
import { LocalStorage } from "quasar";
import { useRouter } from "vue-router";
import { useQuasar } from "quasar";
const $q = useQuasar();

const isRemember = ref(true);
const router = useRouter();

const showModal = ref(false);
const commonStore = useCounterStore();
const isPwd = ref(true);
const form = ref({
  application_type: "2",
  application_version: "34.1.8",
  device_token:
    "fBAjEtcfRey9cNHydf1Egi:APA91bG79qVAeS4rkci7RaEj9agT3vonAQbDb_fiRA1U1v1zlGHWIeb8SC16gsCPlJ6yMJGmKrCzMixYAsjrXOFYqc6xAY_SNsSBEtZhSQxuUtr4kJDKt4JRCINPgnpG0XNl-64wp1E8",
  device_type: "1",
  email: "",
  password: "",
});

const rememberMe = ref(true);
const userEmail = ref(null);
const handleLogin = async () => {
  if (rememberMe.value) {
    LocalStorage.setItem("email", form.value.email);
    LocalStorage.setItem("password", form.value.password);
  } else {
    LocalStorage.removeItem("email");
    LocalStorage.removeItem("password");
  }
  const res = await commonStore.userLogin(form.value);
  if (commonStore.userProfile?.length) {
    $q.notify({
      message: "Login Succesfully",
      icon: "announcement",
      color: "green",
    });
    router.push({ name: "dashboard" });
  } else {
    $q.notify({
      message: res.data.message,
      icon: "announcement",
      color: "red-7",
    });
  }
};
const handleRemember = () => {
  if (!rememberMe.value) {
    LocalStorage.setItem("email", form.value.email);
    LocalStorage.setItem("password", form.value.password);
  } else {
    LocalStorage.removeItem("email");
    LocalStorage.removeItem("password");
  }
};
onMounted(() => {
  const rememberedUserEmail = LocalStorage.getItem("email");
  const rememberedPassword = LocalStorage.getItem("password");

  if (rememberedUserEmail && rememberedPassword) {
    form.value.email = rememberedUserEmail;
    form.value.password = rememberedPassword;
  }
});
const handleForgotPassword = async () => {
  const res = await commonStore.ForgotPassword({ email: userEmail.value });
  console.log(res);
  $q.notify({
    message: res.data.message,
    color: "green",
    icon: "announcement",
  });
  if (res.data.status === true) {
    router.push({ name: "reset-password", query: { email: userEmail.value } });
  }
  showModal.value = false;
  userEmail.value = null;
};

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
.footer {
  position: fixed;
  left: 0;
  bottom: 0;
  width: 100%;
  z-index: 1;
}
</style>
