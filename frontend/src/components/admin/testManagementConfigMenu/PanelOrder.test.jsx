import React from "react";
import { render, screen } from "@testing-library/react";
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
import PanelOrder from "./PanelOrder";

vi.mock("../../utils/Utils", async () => {
  const actual = await vi.importActual("../../utils/Utils");
  return {
    ...actual,
    getFromOpenElisServer: vi.fn(),
    postToOpenElisServerJsonResponse: vi.fn(),
  };
});

const order = (names) => ({
  panelList: names.map((name, index) => ({
    id: String(index + 1),
    value: name,
    sortOrder: index,
  })),
});

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
            <PanelOrder />
          </NotificationContext.Provider>
        </QueryClientProvider>
      </IntlProvider>
    </MemoryRouter>,
  );

describe("PanelOrder", () => {
  let reload;

  beforeEach(() => {
    getFromOpenElisServer.mockReset();
    postToOpenElisServerJsonResponse.mockReset();
    reload = vi.fn();
    Object.defineProperty(window, "location", {
      configurable: true,
      value: { ...window.location, reload, assign: vi.fn() },
    });
  });

  it("shows the stored order", async () => {
    getFromOpenElisServer.mockImplementation((url, cb) =>
      cb(order(["Chemistry", "Haematology"])),
    );

    renderScreen();

    expect(await screen.findByText("Chemistry")).toBeInTheDocument();
    expect(screen.getByText("Haematology")).toBeInTheDocument();
  });

  it("reads the order again when the pending change is discarded, without reloading", async () => {
    let onServer = order(["Chemistry", "Haematology"]);
    getFromOpenElisServer.mockImplementation((url, cb) => cb(onServer));

    renderScreen();
    expect(await screen.findByText("Chemistry")).toBeInTheDocument();

    // Someone else's change lands while this screen is open.
    onServer = order(["Serology"]);
    await userEvent.click(
      screen.getByRole("button", { name: /previous|reject/i }),
    );

    await waitFor(() =>
      expect(screen.getByText("Serology")).toBeInTheDocument(),
    );
    // Discarding used to reload the document, which threw away the whole app.
    expect(reload).not.toHaveBeenCalled();
  });
});
