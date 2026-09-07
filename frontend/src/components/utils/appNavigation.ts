import config from "../../config.json";

type RouteNavigator = (target: string) => void;

let routeNavigator: RouteNavigator | null = null;

/**
 * The router side of the app registers itself here once. Until it does,
 * navigation falls back to a document load, so calling it before mount — or
 * from a test that renders a component on its own — still behaves sensibly.
 */
export const registerAppNavigation = (handlers: {
  onNavigate: RouteNavigator;
}): (() => void) => {
  routeNavigator = handlers.onNavigate;
  return () => {
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
 * Show the current screen's data again after a write.
 *
 * This reloads the document. Remounting the routed subtree instead is not
 * equivalent: screens whose state or fetches live above that subtree keep
 * showing what they had, which is how analyzer result acceptance came to leave
 * its staged rows on screen. Converting a screen to a real refetch has to be
 * done per screen, with its own coverage; until then the callers keep the
 * behaviour they were written against, through one helper rather than 83
 * scattered calls.
 */
export const softReload = (): void => {
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
