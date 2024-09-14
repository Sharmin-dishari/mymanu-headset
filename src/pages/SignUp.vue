<template>
  <q-page padding class="bg-grey-2 q-pt-xl">
    <div class="q-mt-xl">
      <div align="right">
        <q-btn
          label="SIGN IN"
          class="text-red-7 text-bold q-py-sm q-px-lg bg-white q-mr-md"
          style="font-size: 12px"
          outlined
          @click="$router.push({ name: 'sign-index' })"
          rounded
        />
      </div>
    </div>
    <div
      class="q-px-lg q-mt-xl q-pb-md text-h6"
      :class="$q.screen.gt.sm ? 'row flex-center' : ''"
    >
      Sign up
    </div>
    <div class="q-px-lg text-weight-medium text-grey-10">
      Sign up with your email address.
    </div>
    <q-form @submit="signUp" style="max-width: 450px; margin: 0 auto">
      <q-card-section>
        <div>
          <q-input
            v-model="form.name"
            class="required"
            stack-label
            outlined
            placeholder="Name"
            rounded
            type="text"
            bg-color="white"
            clearable
            clear-icon="close"
            :rules="[(val) => !!val || 'Name is required']"
          />
          <q-input
            v-model="form.email"
            class="required q-my-md"
            stack-label
            outlined
            rounded
            bg-color="white"
            placeholder="Email Address"
            type="email"
            clearable
            clear-icon="close"
            :rules="[
              (val) => !!val || 'Email is required',
              (val) => validateEmail(val) || 'Type a valid Email',
            ]"
          />
          <q-input
            v-model="form.password"
            class="required q-my-md q-mt-md"
            stack-label
            outlined
            rounded
            placeholder="Your password"
            bg-color="white"
            :type="isPwd ? 'password' : 'text'"
            :rules="[
              (val) => !!val,
              (val) =>
                val.length >= 8 ||
                'Password must contain at least one lowercase letter, one uppercase letter, one digit, and one special character.',
            ]"
          >
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
            rounded
            bg-color="white"
            placeholder="Confirm password"
            :type="confirm_password ? 'password' : 'text'"
            :rules="[
              (val) => !!val,
              (val) =>
                val.length >= 8 ||
                'Password must contain at least one lowercase letter, one uppercase letter, one digit, and one special character.',
            ]"
          >
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
      <q-item class="row justify-between q-pt-none">
        <q-item-section avatar>
          <div class="q-py-none">
            <div class="row">
              <q-checkbox
                size="40px"
                color="red-7"
                v-model="termsCondition"
                val="dark"
              />
            </div>
          </div>
        </q-item-section>
        <q-item-section
          ><div>
            By createing an account you are agreeing to the Terms of Service
          </div></q-item-section
        >
      </q-item>
      <q-card-actions align="center" class="q-mt-md">
        <q-btn
          label="Register"
          rounded
          type="submit"
          style="height: 50px"
          class="bg-red-8 full-width text-white q-pa-md text-weight-bold"
        />
      </q-card-actions>
    </q-form>
  </q-page>
</template>

<script setup>
import { useCounterStore } from "../stores/example-store";
import { ref } from "vue";
import { useQuasar } from "quasar";
import { useRouter } from "vue-router";
const confirm_password = ref(true);
const termsCondition = ref(false);
const isPwd = ref(true);
const form = ref({
  email: "",
  name: "",
  password: "",
  confirm_password: "",
  application_type: "2",
  device_token:
    "fBAjEtcfRey9cNHydf1Egi:APA91bG79qVAeS4rkci7RaEj9agT3vonAQbDb_fiRA1U1v1zlGHWIeb8SC16gsCPlJ6yMJGmKrCzMixYAsjrXOFYqc6xAY_SNsSBEtZhSQxuUtr4kJDKt4JRCINPgnpG0XNl-64wp1E8",
  device_type: "1",
});
const $q = useQuasar();
const router = useRouter();
const commonStore = useCounterStore();
const emailValidationRegex =
  /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
const validateEmail = (val) => {
  return emailValidationRegex.test(val);
};

const signUp = async () => {
  const res = await commonStore.UserRegister(form.value);
  $q.notify({
    message: res.data.message,
    icon: "announcement",
    color: "green",
  });
  form.value = {
    email: "",
    name: "",
    password: "",
    confirm_password: "",
  };
};
</script>

<style>
.book-btn {
  width: 295px;
  height: 50.14px;
  background: rgb(211, 15, 15);
}
</style>
