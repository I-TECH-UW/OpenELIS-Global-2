import React from "react";
import { render, screen } from "@testing-library/react";
import { waitFor } from "@testing-library/dom";
import userEvent from "@testing-library/user-event";
import { IntlProvider } from "react-intl";
import { MemoryRouter } from "react-router-dom";
import { describe, expect, it, vi, beforeEach } from "vitest";
import messages from "../../../languages/en.json";
import { getFromOpenElisServer } from "../../utils/Utils";
import { NotificationContext } from "../../layout/Layout";
import UserManagement from "./UserManagement";

vi.mock("../../utils/Utils", async () => {
  const actual = await vi.importActual("../../utils/Utils");
  return {
    ...actual,
    getFromOpenElisServer: vi.fn(),
    postToOpenElisServerJsonResponse: vi.fn(),
  };
});

const payload = {
  totalRecordCount: 1,
  fromRecordCount: 1,
  toRecordCount: 1,
  menuList: [
    {
      systemUserId: "1",
      combinedUserID: "1",
      firstName: "Open",
      lastName: "ELIS",
      loginName: "admin",
      expDate: "07/09/2031",
      locked: "N",
      disabled: "N",
      active: "Y",
      timeout: "220",
    },
  ],
  testSections: [{ id: "10", value: "Serology" }],
};

describe("UserManagement filters", () => {
  beforeEach(() => {
    getFromOpenElisServer.mockReset();
    getFromOpenElisServer.mockImplementation((url, cb) => cb(payload));
  });

  it("asks the server for active users when Only Active is clicked", async () => {
    render(
      <MemoryRouter>
        <IntlProvider locale="en" messages={messages}>
          <NotificationContext.Provider
            value={{
              notificationVisible: false,
              setNotificationVisible: vi.fn(),
              addNotification: vi.fn(),
            }}
          >
            <UserManagement />
          </NotificationContext.Provider>
        </IntlProvider>
      </MemoryRouter>,
    );

    const box = await waitFor(() => {
      const el = document.querySelector("#only-active");
      if (!el) throw new Error("checkbox not rendered");
      return el;
    });
    expect(box).not.toBeChecked();

    await userEvent.click(screen.getByText("Only Active"));

    expect(document.querySelector("#only-active")).toBeChecked();
    await waitFor(() =>
      expect(
        getFromOpenElisServer.mock.calls.some(([u]) =>
          String(u).includes("filter=isActive"),
        ),
      ).toBe(true),
    );
  });

  it("does not tear the filter row down when a filter changes", async () => {
    render(
      <MemoryRouter>
        <IntlProvider locale="en" messages={messages}>
          <NotificationContext.Provider
            value={{
              notificationVisible: false,
              setNotificationVisible: vi.fn(),
              addNotification: vi.fn(),
            }}
          >
            <UserManagement />
          </NotificationContext.Provider>
        </IntlProvider>
      </MemoryRouter>,
    );

    const before = await waitFor(() => {
      const el = document.querySelector("#only-active");
      if (!el) throw new Error("checkbox not rendered");
      return el;
    });

    await userEvent.click(screen.getByText("Only Active"));

    // The row has to survive the reload its own filter change triggers.
    expect(document.querySelector("#only-active")).toBe(before);
  });
});
