const routes = [
  {
    path: "/",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      {
        path: "dashboard-index",
        name: "dashboard-index",
        component: () => import("pages/IndexPage.vue"),
      },
      {
        path: "dashboard-index2",
        name: "dashboard-index2",
        component: () => import("pages/DashboardIndex.vue"),
      },
      {
        path: "device-index",
        name: "device-index",
        component: () => import("pages/DeviceIndex.vue"),
      },
      {
        path: "manage-esim",
        name: "manage-esim",
        component: () => import("pages/ManageEsim.vue"),
      },
      {
        path: "esim-details",
        name: "esim-details",
        component: () => import("pages/EsimDetails.vue"),
      },
      {
        path: "",
        name: "sign-index",
        component: () => import("pages/SignIndex.vue"),
      },
      {
        path: "sign-up",
        name: "sign-up",
        component: () => import("pages/SignUp.vue"),
      },
      {
        path: "settings-index",
        name: "settings-index",
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
