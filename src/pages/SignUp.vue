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
      <div class="q-px-md row flex-center text-h6">Sign up</div>
    </div>
    <q-form @submit="signUp" style="max-width: 450px; margin: 0 auto">
      <q-card-section>
        <div>
          <q-input
            v-model="form.displayName"
            class="itc-input required"
            stack-label
            outlined
            placeholder="Enter your name"
            type="text"
            clearable
            clear-icon="close"
            :rules="[(val) => !!val || 'Name is required']"
          >
            <template #prepend> <q-icon name="person_outline" /></template>
          </q-input>
          <q-input
            v-model="form.email"
            class="itc-input required"
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
            placeholder="Your password"
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
          <q-input
            v-model="form.confirm_password"
            class="itc-input required"
            stack-label
            outlined
            placeholder="Confirm password"
            :type="confirm_password ? 'password' : 'text'"
            :rules="[
              (val) => !!val || 'Password is required',
              (val) => val.length >= 6 || 'Minimum 6 characters required',
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
              <div class="q-mt-xs text-bold">Sign Up</div>
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
import { useRouter } from "vue-router";
const confirm_password = ref(true);
const isPwd = ref(true);
const form = ref({
  email: "",
  displayName: "",
  password: "",
  confirm_password: "",
});
const router = useRouter();
const commonStore = useCounterStore();
const emailValidationRegex =
  /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
const validateEmail = (val) => {
  return emailValidationRegex.test(val);
};

const signUp = () => {
  const form = {
    email: form.value.email,
    password: form.value.password,
  };
  commonStore.UserRegister(form.value);
};
</script>

<style>
.book-btn {
  width: 295px;
  height: 50.14px;
  background: rgb(211, 15, 15);
}
</style>
