import { createGlobalStore } from "../utils/store";

const breadcrumbsStore = createGlobalStore('breadcrumbs', {})


export function registerBreadcrumbs(breadcrumbs) {
  const prevBreadcrumbs = getBreadcrumbs();
  const nextBreadcrumbs = [...prevBreadcrumbs, ...breadcrumbs];
  breadcrumbsStore.setState(nextBreadcrumbs, true);
}
export function getBreadcrumbs() {
  return breadcrumbsStore.getState()
}
