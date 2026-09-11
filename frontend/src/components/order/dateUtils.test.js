import { describe, expect, it } from "vitest";
import {
  currentLocalTime,
  daysBetweenIsoDates,
  formatIsoDateForBackend,
  isCollectionDateBeforeAdmissionDate,
  normalizeDateForState,
  todayLocalIso,
} from "./dateUtils";

describe("order date utilities", () => {
  it("serializes ISO dates using the configured deployment locale", () => {
    expect(formatIsoDateForBackend("2026-08-13", "en-US")).toBe("08/13/2026");
    expect(formatIsoDateForBackend("2026-08-13", "fr-FR")).toBe("13/08/2026");
  });

  it("normalizes configured API dates into canonical ISO state", () => {
    expect(normalizeDateForState("13/08/2026", "fr-FR")).toBe("2026-08-13");
    expect(normalizeDateForState("08/13/2026", "en-US")).toBe("2026-08-13");
    expect(normalizeDateForState("2026-08-13", "fr-FR")).toBe("2026-08-13");
  });

  it("detects a collection date before admission without rejecting empty dates", () => {
    expect(
      isCollectionDateBeforeAdmissionDate("2026-08-02", "2026-08-03"),
    ).toBe(true);
    expect(
      isCollectionDateBeforeAdmissionDate("2026-08-03", "2026-08-03"),
    ).toBe(false);
    expect(isCollectionDateBeforeAdmissionDate("", "2026-08-03")).toBe(false);
  });

  it("computes calendar days between admission and collection", () => {
    expect(daysBetweenIsoDates("2026-08-03", "2026-08-07")).toBe(4);
    expect(daysBetweenIsoDates("", "2026-08-07")).toBeNull();
  });

  // OGC-1201 O: vector orders were stamped from toISOString(), i.e. UTC, so a
  // late-evening collection west of Greenwich was stored as the next day.
  it("stamps the site's own calendar day, not the UTC one", () => {
    const lateEveningLocal = new Date(2026, 7, 13, 23, 30);

    expect(todayLocalIso(lateEveningLocal)).toBe("2026-08-13");
    expect(currentLocalTime(lateEveningLocal)).toBe("23:30");
  });

  it("stamps an early-morning time with a padded hour", () => {
    expect(currentLocalTime(new Date(2026, 7, 13, 6, 5))).toBe("06:05");
  });
});
