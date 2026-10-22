import { test, expect } from "../../../helpers/test-base";

/**
 * Lab Management Dashboard smoke (OGC-485 Slice 1).
 *
 * Confirms the page shell renders when the signed-in user has the required
 * role: title, three header filter controls (Section, Date Range, refresh
 * interval), Last Updated line, Refresh button, and the six KPI tiles. The
 * default session user is a Global Administrator and therefore satisfies the
 * [REPORTS, GLOBAL_ADMIN] SecureRoute. Data correctness is covered by
 * backend tests; this is a render/routing smoke.
 */
test.describe("Lab Management Dashboard smoke", () => {
  test("page renders with header, filters, and six KPI tiles", async ({
    page,
  }) => {
    await page.goto("/LabManagementDashboard", {
      waitUntil: "domcontentloaded",
    });

    await expect(page.getByTestId("dashboard-title")).toBeVisible();

    await expect(page.locator("#dashboard-section-filter")).toBeVisible();
    await expect(page.locator("#dashboard-date-range-filter")).toBeVisible();
    await expect(page.locator("#dashboard-refresh-interval")).toBeVisible();

    await expect(page.getByTestId("dashboard-last-updated")).toBeVisible();
    await expect(page.getByTestId("dashboard-refresh-button")).toBeVisible();

    await expect(
      page.getByTestId("dashboard-kpi-label.dashboard.testsToday"),
    ).toBeVisible();
    await expect(
      page.getByTestId("dashboard-kpi-label.dashboard.pending"),
    ).toBeVisible();
    await expect(
      page.getByTestId("dashboard-kpi-label.dashboard.tatCompliance"),
    ).toBeVisible();
    await expect(
      page.getByTestId("dashboard-kpi-label.dashboard.qcPassRate"),
    ).toBeVisible();
    await expect(
      page.getByTestId("dashboard-kpi-label.dashboard.analyzersOnline"),
    ).toBeVisible();
    await expect(
      page.getByTestId("dashboard-kpi-label.dashboard.stockAlerts"),
    ).toBeVisible();
  });

  test("refresh button triggers reload and updates timestamp", async ({
    page,
  }) => {
    await page.goto("/LabManagementDashboard", {
      waitUntil: "domcontentloaded",
    });
    await expect(page.getByTestId("dashboard-title")).toBeVisible();

    const timestamp = page.getByTestId("dashboard-last-updated");
    const before = await timestamp.textContent();

    await page.getByTestId("dashboard-refresh-button").click();

    await expect
      .poll(async () => (await timestamp.textContent()) !== before, {
        timeout: 5_000,
      })
      .toBeTruthy();
  });
});
