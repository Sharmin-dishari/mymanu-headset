import { boot } from "quasar/wrappers";
import { useCounterStore } from "stores/example-store";

export default boot(({ router }) => {
  router.beforeEach((to, from, next) => {
    // Access the store
    const commonStore = useCounterStore();
    const isAuthenticated = !!commonStore.apiToken;

    // Paths that do not require authentication
    const nonAuthRequiredPaths = ["", "/sign-up"];

    // Check if the route requires authentication
    if (to.matched.some((record) => record.meta.requiresAuth)) {
      // Route requires authentication and user is not authenticated
      if (!isAuthenticated) {
        next({ name: "sign-index" });
      } else {
        next();
      }
    } else {
      // Route does not require authentication
      if (
        nonAuthRequiredPaths.some((path) => to.path.startsWith(path)) &&
        isAuthenticated
      ) {
        // Redirect authenticated user away from non-auth pages
        next({ name: "dashboard" });
      } else {
        next();
      }
    }
  });
});
