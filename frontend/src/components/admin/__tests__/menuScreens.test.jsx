import React from "react";
import { render, screen, fireEvent } from "@testing-library/react";
import { waitFor } from "@testing-library/dom";
import userEvent from "@testing-library/user-event";
import { IntlProvider } from "react-intl";
import { MemoryRouter } from "react-router-dom";
import { QueryClientProvider } from "@tanstack/react-query";
import { beforeEach, describe, expect, it, vi } from "vitest";
import messages from "../../../languages/en.json";
import {
  getFromOpenElisServer,
  postToOpenElisServerJsonResponse,
} from "../../utils/Utils";
import { createQueryClient } from "../../utils/queryClient";
import { NotificationContext } from "../../layout/Layout";
import ExternalConnectionMenu from "../externalConnections/ExternalConnectionMenu";
import OrganizationManagement from "../OrganizationManagement/OrganizationManagement";

vi.mock("../../utils/Utils", async () => {
  const actual = await vi.importActual("../../utils/Utils");
  return {
    ...actual,
    getFromOpenElisServer: vi.fn(),
    postToOpenElisServerJsonResponse: vi.fn(),
  };
});

/**
 * Both screens list records with paging and a search box, and deactivating one
 * used to reload the document to show the list without it.
 */
const SCREENS = [
  {
    name: "ExternalConnectionMenu",
    Screen: ExternalConnectionMenu,
    browse: "/rest/ExternalConnectionMenu",
    search: "/rest/SearchExternalConnectionMenu",
    rows: (names) => ({
      menuList: names.map((name, index) => ({
        id: index + 1,
        nameLocalization: { localizedValue: name },
        programmedConnection: "",
        uri: "",
        activeAuthenticationType: "",
        active: true,
      })),
      fromRecordCount: 1,
      toRecordCount: names.length,
      totalRecordCount: names.length,
    }),
  },
  {
    name: "OrganizationManagement",
    Screen: OrganizationManagement,
    browse: "/rest/OrganizationMenu",
    search: "/rest/SearchOrganizationMenu",
    rows: (names) => ({
      menuList: names.map((organizationName, index) => ({
        id: String(index + 1),
        organizationName,
        organization: null,
        isActive: "Y",
        streetAddress: "",
      })),
      fromRecordCount: 1,
      toRecordCount: names.length,
      totalRecordCount: names.length,
    }),
  },
];

describe.each(SCREENS)("$name", ({ Screen, browse, search, rows }) => {
  let reload;
  let onServer;

  const renderScreen = () =>
    render(
      <MemoryRouter>
        <IntlProvider locale="en" messages={messages}>
          <QueryClientProvider client={createQueryClient()}>
            <NotificationContext.Provider
              value={{
                notificationVisible: false,
                setNotificationVisible: vi.fn(),
                addNotification: vi.fn(),
              }}
            >
              <Screen />
            </NotificationContext.Provider>
          </QueryClientProvider>
        </IntlProvider>
      </MemoryRouter>,
    );

  beforeEach(() => {
    onServer = rows(["Kepler Lab"]);
    getFromOpenElisServer.mockReset();
    getFromOpenElisServer.mockImplementation((url, callback) =>
      url.startsWith(browse) || url.startsWith(search)
        ? callback(onServer)
        : callback(undefined),
    );
    postToOpenElisServerJsonResponse.mockReset();
    reload = vi.fn();
    Object.defineProperty(window, "location", {
      configurable: true,
      value: { ...window.location, reload, assign: vi.fn() },
    });
  });

  it("reads the records once, from the endpoint for browsing", async () => {
    renderScreen();

    await waitFor(() =>
      expect(screen.getByText("Kepler Lab")).toBeInTheDocument(),
    );
    const reads = getFromOpenElisServer.mock.calls.map(([url]) => url);
    expect(reads.filter((u) => u.startsWith(browse))).toHaveLength(1);
    // Nothing has been searched for.
    expect(reads.filter((u) => u.startsWith(search))).toEqual([]);
  });

  it("reads the records again once one is deactivated, without reloading", async () => {
    renderScreen();
    await waitFor(() =>
      expect(screen.getByText("Kepler Lab")).toBeInTheDocument(),
    );

    postToOpenElisServerJsonResponse.mockImplementation(
      (url, payload, callback) => {
        onServer = rows(["Brahe Lab"]);
        callback(true);
      },
    );

    fireEvent.click(screen.getAllByLabelText("selectRows")[0]);
    await userEvent.click(screen.getByRole("button", { name: "Deactivate" }));

    await waitFor(() =>
      expect(screen.getByText("Brahe Lab")).toBeInTheDocument(),
    );
    // Deactivating used to reload the document, throwing away the whole app.
    expect(reload).not.toHaveBeenCalled();
  });
});
