import config from "../../config.json";

type SoftReloadListener = () => void;
type RouteNavigator = (target: string) => void;

let softReloadListener: SoftReloadListener | null = null;
let routeNavigator: RouteNavigator | null = null;

/**
 * The router side of the app registers itself here once. Until it does, both
 * helpers fall back to a document load, so calling them before mount — or from
 * a test that renders a component on its own — still behaves sensibly.
 */
export const registerAppNavigation = (handlers: {
  onSoftReload: SoftReloadListener;
  onNavigate: RouteNavigator;
}): (() => void) => {
  softReloadListener = handlers.onSoftReload;
  routeNavigator = handlers.onNavigate;
  return () => {
    softReloadListener = null;
    routeNavigator = null;
  };
};

/**
 * A target the router owns: an app-relative path that is not the server's own
 * servlet space and not protocol-relative. Anything else — an absolute URL, a
 * legacy server page — has to leave the single-page app.
 */
export const isInAppRoute = (target: string): boolean => {
  if (!target || !target.startsWith("/") || target.startsWith("//")) {
    return false;
  }
  return !target.startsWith(config.serverBaseUrl);
};

/**
 * Show the current screen's data again after a write, by remounting the routed
 * subtree rather than re-downloading the application. A document load discards
 * every bit of client state and, because it lands whenever the browser gets to
 * it, races anything already in flight.
 */
export const softReload = (): void => {
  if (softReloadListener) {
    softReloadListener();
    return;
  }
  window.location.reload();
};

/**
 * Go somewhere else in the app through the router, so no document load is
 * queued behind the caller. Targets outside the router still get a real load.
 */
export const navigateTo = (target: string): void => {
  if (routeNavigator && isInAppRoute(target)) {
    routeNavigator(target);
    return;
  }
  window.location.assign(target);
};
