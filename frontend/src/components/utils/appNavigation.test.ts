import { afterEach, describe, expect, it, vi } from "vitest";
import {
  isInAppRoute,
  navigateTo,
  registerAppNavigation,
  softReload,
} from "./appNavigation";

const stubWindowLocation = () => {
  const assign = vi.fn();
  const reload = vi.fn();
  // jsdom makes window.location non-writable, so replace the whole object.
  Object.defineProperty(window, "location", {
    configurable: true,
    value: { ...window.location, assign, reload },
  });
  return { assign, reload };
};

describe("appNavigation", () => {
  let unregister: (() => void) | null = null;

  afterEach(() => {
    if (unregister) {
      unregister();
      unregister = null;
    }
    vi.restoreAllMocks();
  });

  it("treats app paths as routed and server or absolute targets as not", () => {
    expect(isInAppRoute("/MasterListsPage/userManagement")).toBe(true);
    expect(isInAppRoute("/api/OpenELIS-Global/LoginPage")).toBe(false);
    expect(isInAppRoute("https://example.org/somewhere")).toBe(false);
    expect(isInAppRoute("//example.org/somewhere")).toBe(false);
    expect(isInAppRoute("")).toBe(false);
  });

  it("remounts through the router instead of reloading the document", () => {
    const { reload } = stubWindowLocation();
    const onSoftReload = vi.fn();
    unregister = registerAppNavigation({
      onSoftReload,
      onNavigate: vi.fn(),
    });

    softReload();

    expect(onSoftReload).toHaveBeenCalledTimes(1);
    expect(reload).not.toHaveBeenCalled();
  });

  it("routes an in-app target and leaves the app for anything else", () => {
    const { assign } = stubWindowLocation();
    const onNavigate = vi.fn();
    unregister = registerAppNavigation({
      onSoftReload: vi.fn(),
      onNavigate,
    });

    navigateTo("/MasterListsPage/userManagement");
    expect(onNavigate).toHaveBeenCalledWith("/MasterListsPage/userManagement");
    expect(assign).not.toHaveBeenCalled();

    navigateTo("/api/OpenELIS-Global/LoginPage");
    expect(assign).toHaveBeenCalledWith("/api/OpenELIS-Global/LoginPage");
  });

  it("falls back to a document load before the router registers", () => {
    const { assign, reload } = stubWindowLocation();

    softReload();
    navigateTo("/MasterListsPage/userManagement");

    expect(reload).toHaveBeenCalledTimes(1);
    expect(assign).toHaveBeenCalledWith("/MasterListsPage/userManagement");
  });
});
