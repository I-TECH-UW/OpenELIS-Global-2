import React from "react";
import { vi } from "vitest";
import { render, screen } from "@testing-library/react";
import "@testing-library/jest-dom";
import { IntlProvider } from "react-intl";
import messages from "../../../languages/en.json";
import { NotificationContext } from "../../layout/contexts";
import UserSessionDetailsContext from "../../../UserSessionDetailsContext";
import DeviceManagement from "./DeviceManagement";

vi.mock("../api", () => ({
  fetchDevices: vi.fn(() =>
    Promise.resolve([
      { id: 1, name: "Freezer A", active: true, protocol: "TCP" },
    ]),
  ),
  fetchLocations: vi.fn(() => Promise.resolve([])),
  createDevice: vi.fn(),
  updateDevice: vi.fn(),
  toggleDeviceStatus: vi.fn(),
  deleteDevice: vi.fn(),
  createRoom: vi.fn(),
}));

const renderFor = (roles) =>
  render(
    <IntlProvider locale="en" messages={messages}>
      <UserSessionDetailsContext.Provider
        value={{ userSessionDetails: { roles } }}
      >
        <NotificationContext.Provider
          value={{
            notificationVisible: false,
            setNotificationVisible: vi.fn(),
            addNotification: vi.fn(),
          }}
        >
          <DeviceManagement />
        </NotificationContext.Provider>
      </UserSessionDetailsContext.Provider>
    </IntlProvider>,
  );

/**
 * The device writes on FreezerDeviceController require ADMIN, while the route
 * gate on /FreezerMonitoring also admits Reception, so Reception must reach a
 * read-only Device Management rather than controls that fail on click.
 */
describe("DeviceManagement write controls by role", () => {
  const writeControlNames = [
    "Add New Device",
    "Edit device",
    "Deactivate device",
    "Delete Device",
  ];

  it("hides add, edit, toggle and delete from a non-admin", async () => {
    renderFor(["Reception"]);

    expect(await screen.findByText("Freezer A")).toBeInTheDocument();
    writeControlNames.forEach((name) => {
      expect(screen.queryByRole("button", { name })).not.toBeInTheDocument();
    });
    expect(
      screen.queryByRole("columnheader", { name: "Actions" }),
    ).not.toBeInTheDocument();
  });

  it("shows them to a global administrator", async () => {
    renderFor(["Global Administrator"]);

    expect(await screen.findByText("Freezer A")).toBeInTheDocument();
    writeControlNames.forEach((name) => {
      expect(screen.getByRole("button", { name })).toBeInTheDocument();
    });
    expect(
      screen.getByRole("columnheader", { name: "Actions" }),
    ).toBeInTheDocument();
  });
});
