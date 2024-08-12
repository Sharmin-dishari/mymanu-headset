const routes = [
  {
    path: "/",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      {
        path: "dashboard",
        name: "dashboard",
        meta: { requiresAuth: true },
        component: () => import("pages/IndexPage.vue"),
      },
      {
        path: "dashboard-index2",
        name: "dashboard-index2",
        meta: { requiresAuth: true },
        component: () => import("pages/DashboardIndex.vue"),
      },
      {
        path: "device-index",
        name: "device-index",
        meta: { requiresAuth: true },
        component: () => import("pages/DeviceIndex.vue"),
      },
      {
        path: "manage-esim",
        name: "manage-esim",
        meta: { requiresAuth: true },
        component: () => import("pages/ManageEsim.vue"),
      },
      {
        path: "esim-details",
        name: "esim-details",
        meta: { requiresAuth: true },
        component: () => import("pages/EsimDetails.vue"),
      },
      {
        path: "",
        name: "sign-index",
        meta: { requiresAuth: false },
        component: () => import("pages/SignIndex.vue"),
      },
      {
        path: "sign-up",
        name: "sign-up",
        meta: { requiresAuth: false },
        component: () => import("pages/SignUp.vue"),
      },
      {
        path: "reset-password",
        name: "reset-password",
        meta: { requiresAuth: false },
        component: () => import("pages/ResetPassword.vue"),
      },
      {
        path: "settings-index",
        name: "settings-index",
        meta: { requiresAuth: true },
        component: () => import("pages/SettingsIndex.vue"),
      },
    ],
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: "/:catchAll(.*)*",
    component: () => import("pages/ErrorNotFound.vue"),
  },
];

export default routes;
