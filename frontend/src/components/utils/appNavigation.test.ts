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

  it("reloads the document when a screen asks to show its data again", () => {
    const { reload } = stubWindowLocation();
    unregister = registerAppNavigation({ onNavigate: vi.fn() });

    softReload();

    // Remounting the routed subtree is not equivalent for screens whose state
    // lives above it, so this stays a real reload until each is converted.
    expect(reload).toHaveBeenCalledTimes(1);
  });

  it("routes an in-app target and leaves the app for anything else", () => {
    const { assign } = stubWindowLocation();
    const onNavigate = vi.fn();
    unregister = registerAppNavigation({ onNavigate });

    navigateTo("/MasterListsPage/userManagement");
    expect(onNavigate).toHaveBeenCalledWith("/MasterListsPage/userManagement");
    expect(assign).not.toHaveBeenCalled();

    navigateTo("/api/OpenELIS-Global/LoginPage");
    expect(assign).toHaveBeenCalledWith("/api/OpenELIS-Global/LoginPage");
  });

  it("falls back to a document load before the router registers", () => {
    const { assign } = stubWindowLocation();

    navigateTo("/MasterListsPage/userManagement");

    expect(assign).toHaveBeenCalledWith("/MasterListsPage/userManagement");
  });
});
