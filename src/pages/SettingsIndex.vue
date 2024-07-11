<template>
  <q-page class="bg-grey-2">
    <q-header class="bg-grey-2 text-black q-pa-md">
      <div
        class="cursor-pointer"
        @click="$router.back()"
        v-if="$q.screen.lt.sm"
      >
        <q-icon name="arrow_back" size="md" /></div
    ></q-header>
    <q-card flat>
      <q-tabs
        v-model="tab"
        dense
        flat
        class="text-grey q-px-sm bg-grey-2"
        active-color="primary"
        indicator-color="primary"
        align="justify"
        narrow-indicator
      >
        <q-tab name="name" label="Update Name" />
        <q-tab name="email" label="Update Email" />
        <q-tab name="password" label="Update Password" />
      </q-tabs>

      <q-separator />

      <q-tab-panels v-model="tab" animated class="q-pa-md bg-grey-2">
        <q-tab-panel name="name">
          <q-form @submit="onSubmitName">
            <div class="text-h6">Update Name</div>
            <div>
              <q-input
                v-model="form.name"
                class="itc-input required q-mb-sm"
                stack-label
                outlined
                placeholder="Enter your name"
                clearable
                clear-icon="close"
                :rules="[(val) => !!val || 'Name is required']"
              >
                <template #prepend> <q-icon name="person" /></template>
              </q-input>
            </div>
            <div align="right">
              <q-btn no-caps color="primary" type="submit" class="q-px-lg"
                >Save</q-btn
              >
            </div>
          </q-form>
        </q-tab-panel>

        <q-tab-panel name="email" class="q-pa-md">
          <div class="text-h6">Update Email</div>
          <q-form @submit="onSubmitEmail">
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
            </div>
            <div align="right">
              <q-btn no-caps color="primary" type="submit" class="q-px-lg"
                >Save</q-btn
              >
            </div>
          </q-form>
        </q-tab-panel>
        <q-tab-panel name="password" class="q-pa-md">
          <div class="text-h6">Update Password</div>
          <q-form @submit="onSubmitPassword">
            <q-input
              v-model="form.password"
              class="itc-input required q-mt-md"
              stack-label
              outlined
              placeholder="Your password"
              :type="isPwd ? 'password' : 'text'"
              :rules="[
                (val) => !!val || 'Password is required',
                (val) => val.length >= 8 || 'Minimum 8 characters required',
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
              class="itc-input required q-my-lg"
              stack-label
              outlined
              placeholder="Confirm password"
              :type="confirm_password ? 'password' : 'text'"
              :rules="[
                (val) => !!val || 'Password is required',
                (val) => val.length >= 8 || 'Minimum 8 characters required',
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
            <div align="right">
              <q-btn no-caps color="primary" type="submit" class="q-px-lg"
                >Save</q-btn
              >
            </div>
          </q-form>
        </q-tab-panel>
      </q-tab-panels>
    </q-card>
  </q-page>
</template>

<script setup>
import { useQuasar } from "quasar";
import { ref, watchEffect } from "vue";
import { useCounterStore } from "../stores/example-store";
const commonStore = useCounterStore();
const tab = ref("name");
const confirm_password = ref(true);
const isPwd = ref(true);
const form = ref({
  email: "",
  name: "",
  password: "",
  confirm_password: "",
});

const q = useQuasar();
const validateEmail = (email) => {
  const res =
    /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return res.test(String(email).toLowerCase());
};
const payload = {
  application_type: "2",
  application_version: "34.1.8",
  device_token:
    "fBAjEtcfRey9cNHydf1Egi:APA91bG79qVAeS4rkci7RaEj9agT3vonAQbDb_fiRA1U1v1zlGHWIeb8SC16gsCPlJ6yMJGmKrCzMixYAsjrXOFYqc6xAY_SNsSBEtZhSQxuUtr4kJDKt4JRCINPgnpG0XNl-64wp1E8",
  device_type: "1",
};
watchEffect(() => {
  form.value.name = commonStore.userProfile[0].user_name;
  form.value.email = commonStore.userProfile[0].user_email;
});
const onSubmitName = async () => {
  const res = await commonStore.UpdateName({
    name: form.value.name,
    ...payload,
  });
  q.notify({
    message: `${res.data.message}`,
    icon: "announcement",
    color: "green",
  });
  commonStore.userProfile[0].user_name = form.value.name;
};
const onSubmitEmail = async () => {
  const res = await commonStore.UpdateEmail({
    email: form.value.email,
    ...payload,
  });
  q.notify({
    message: `${res.data.message}`,
    icon: "announcement",
    color: "green",
  });
  commonStore.userProfile[0].user_email = form.value.email;
};
const onSubmitPassword = async () => {
  const res = await commonStore.UpdatePassword({
    password: form.value.confirm_password,
    old_password: form.value.password,
    ...payload,
  });
  q.notify({
    message: `${res.data.message}`,
    icon: "announcement",
    color: "green",
  });
  form.value.password = null;
  form.value.confirm_password = null;
};
</script>

<style></style>
