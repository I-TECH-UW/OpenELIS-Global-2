import { createGlobalStore } from "../utils/store";

const breadcrumbsStore = createGlobalStore('breadcrumbs', {})


export function registerBreadcrumbs(breadcrumbs) {
  const prevBreadcrumbs = getBreadcrumbs();
  // const newBreadcrumbs = breadcrumbs.map((settings) => ({
  //   matcher: getMatcher(settings),
  //   settings,
  // }));
  const nextBreadcrumbs = [...prevBreadcrumbs, ...breadcrumbs];
  breadcrumbsStore.setState(nextBreadcrumbs, true);
}


// export function pathToRegExp(path) {
//   const escapedPath = path.replace(/[-/\\^$*+?.()|[\]{}]/g, '\\$&');
//   const paramPattern = /:(\w+)/g;
//   let regexpString = escapedPath.replace(paramPattern, (_, paramName) => `([^/]+)`);
//   regexpString = `^${regexpString}$`;
//   return new RegExp(regexpString);
// }


// export function getMatcher(settings) {
//   if (settings.matcher instanceof RegExp) {
//     return settings.matcher;
//   } else if (typeof settings.matcher === 'string') {
//     return pathToRegexp(settings.matcher);
//   } else {
//     return pathToRegexp(settings.path);
//   }
// }

export function getBreadcrumbs() {
  return breadcrumbsStore.getState()
}
