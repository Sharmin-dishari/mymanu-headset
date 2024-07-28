<template>
  <q-page>
    <div class="q-pa-md">
      <div
        class="cursor-pointer"
        @click="$router.back()"
        v-if="$q.screen.lt.sm"
      >
        <q-icon name="arrow_back" size="md" />
      </div>
      <div class="q-px-md row flex-center text-h6">Password Reset</div>
    </div>
    <q-form @submit="signUp" style="max-width: 450px; margin: 0 auto">
      <q-card-section>
        <div>
          <div class="q-mb-md">Please enter OTP and password</div>
          <q-input
            v-model="form.otp"
            class="required"
            stack-label
            outlined
            placeholder="Enter your OTP"
            type="text"
            clearable
            clear-icon="close"
            :rules="[(val) => !!val]"
          >
            <template #prepend> <q-icon name="person_outline" /></template>
          </q-input>
          <q-input
            v-model="form.password"
            class="required q-my-md q-mt-md"
            stack-label
            outlined
            placeholder="Your password"
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
          <q-input
            v-model="form.confirm_password"
            class="required"
            stack-label
            outlined
            placeholder="Confirm password"
            :type="confirm_password ? 'password' : 'text'"
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
                :name="confirm_password ? 'visibility' : 'visibility_off'"
                class="cursor-pointer"
                @click="confirm_password = !confirm_password"
              />
            </template>
          </q-input>
        </div>
      </q-card-section>
      <q-card-actions class="q-pt-none" align="center">
        <div class="text-center q-py-md">
          <q-btn class="book-btn" rounded type="submit">
            <div class="row text-white">
              <div class="q-mt-xs text-bold">Reset Password</div>
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
    <div
      class="text-caption text-center q-py-md cursor-pointer"
      @click="$router.push({ name: 'sign-index' })"
    >
      Already have an account?
      <span class="text-primary q-ml-sm">Signin</span>
    </div>
  </q-page>
</template>

<script setup>
import { useCounterStore } from "../stores/example-store";
import { ref } from "vue";
import { useQuasar } from "quasar";
import { useRouter, useRoute } from "vue-router";
const confirm_password = ref(true);
const isPwd = ref(true);
const router = useRouter();
const route = useRoute();
const form = ref({
  email: route.query.email,
  otp: "",
  password: "",
  confirm_password: "",
  application_type: "2",
  device_token:
    "fBAjEtcfRey9cNHydf1Egi:APA91bG79qVAeS4rkci7RaEj9agT3vonAQbDb_fiRA1U1v1zlGHWIeb8SC16gsCPlJ6yMJGmKrCzMixYAsjrXOFYqc6xAY_SNsSBEtZhSQxuUtr4kJDKt4JRCINPgnpG0XNl-64wp1E8",
  device_type: "1",
});
const $q = useQuasar();

const commonStore = useCounterStore();
const emailValidationRegex =
  /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
const validateEmail = (val) => {
  return emailValidationRegex.test(val);
};

const signUp = async () => {
  const res = await commonStore.ResetPassword(form.value);
  $q.notify({
    message: res.data.message,
    icon: "announcement",
    color: "green",
  });
  form.value = {
    email: "",
    otp: "",
    password: "",
    confirm_password: "",
  };
  router.push({ name: "sign-index" });
};
</script>

<style>
.book-btn {
  width: 295px;
  height: 50.14px;
  background: rgb(211, 15, 15);
}
</style>
