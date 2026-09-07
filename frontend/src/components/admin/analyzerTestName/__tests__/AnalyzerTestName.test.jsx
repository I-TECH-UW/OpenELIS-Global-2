import React from "react";
import { render, screen, fireEvent } from "@testing-library/react";
import { waitFor } from "@testing-library/dom";
import userEvent from "@testing-library/user-event";
import { IntlProvider } from "react-intl";
import { MemoryRouter } from "react-router-dom";
import { QueryClientProvider } from "@tanstack/react-query";
import { beforeEach, describe, expect, it, vi } from "vitest";
import messages from "../../../../languages/en.json";
import {
  getFromOpenElisServer,
  postToOpenElisServerFullResponse,
} from "../../../utils/Utils";
import { createQueryClient } from "../../../utils/queryClient";
import { NotificationContext } from "../../../layout/Layout";
import { ConfigurationContext } from "../../../layout/Layout";
import AnalyzerTestName from "../AnalyzerTestName";

vi.mock("../../../utils/Utils", async () => {
  const actual = await vi.importActual("../../../utils/Utils");
  return {
    ...actual,
    getFromOpenElisServer: vi.fn(),
    postToOpenElisServerFullResponse: vi.fn(),
  };
});

const mappings = (names) => ({
  menuList: names.map((analyzerTestName, index) => ({
    uniqueId: String(index + 1),
    analyzerName: "Cobas",
    analyzerTestName,
    actualTestName: "Glucose",
  })),
  fromRecordCount: "1",
  toRecordCount: String(names.length),
  totalRecordCount: String(names.length),
});

describe("AnalyzerTestName", () => {
  let reload;
  let onServer;

  const renderScreen = () =>
    render(
      <MemoryRouter>
        <IntlProvider locale="en" messages={messages}>
          <QueryClientProvider client={createQueryClient()}>
            <ConfigurationContext.Provider
              value={{
                reloadConfiguration: vi.fn(),
                configurationProperties: {},
              }}
            >
              <NotificationContext.Provider
                value={{
                  notificationVisible: false,
                  setNotificationVisible: vi.fn(),
                  addNotification: vi.fn(),
                }}
              >
                <AnalyzerTestName />
              </NotificationContext.Provider>
            </ConfigurationContext.Provider>
          </QueryClientProvider>
        </IntlProvider>
      </MemoryRouter>,
    );

  beforeEach(() => {
    onServer = mappings(["GLU"]);
    getFromOpenElisServer.mockReset();
    getFromOpenElisServer.mockImplementation((url, callback) => {
      if (url.startsWith("/rest/AnalyzerTestNameMenu"))
        return callback(onServer);
      if (url.startsWith("/rest/AnalyzerTestName?ID=0"))
        return callback({ analyzerList: [{ id: "1", value: "Cobas" }] });
      if (url.startsWith("/rest/test-list"))
        return callback([{ id: "9", value: "Glucose" }]);
      return callback(undefined);
    });
    postToOpenElisServerFullResponse.mockReset();
    reload = vi.fn();
    Object.defineProperty(window, "location", {
      configurable: true,
      value: { ...window.location, reload, assign: vi.fn() },
    });
  });

  it("reads the mappings once, not once per effect", async () => {
    renderScreen();

    await waitFor(() =>
      expect(screen.getByText("Cobas - GLU")).toBeInTheDocument(),
    );
    const menuReads = getFromOpenElisServer.mock.calls.filter(([url]) =>
      url.startsWith("/rest/AnalyzerTestNameMenu"),
    );
    expect(menuReads).toHaveLength(1);
  });

  it("reads the mappings again when a write answers, not on a timer", async () => {
    vi.useFakeTimers({ shouldAdvanceTime: true });
    renderScreen();
    await waitFor(() =>
      expect(screen.getByText("Cobas - GLU")).toBeInTheDocument(),
    );

    // Deactivating used to ignore the response entirely and reload a second
    // later, whatever the server said.
    let answer;
    postToOpenElisServerFullResponse.mockImplementation(
      (url, payload, callback) => {
        answer = () => {
          onServer = mappings(["UREA"]);
          callback({ status: 200 });
        };
      },
    );

    fireEvent.click(screen.getAllByLabelText("selectRows")[0]);
    await userEvent.click(screen.getByRole("button", { name: "Deactivate" }));
    expect(postToOpenElisServerFullResponse).toHaveBeenCalledTimes(1);

    // A second passes with the write unanswered: nothing refreshed, nothing
    // reloaded.
    vi.advanceTimersByTime(2000);
    expect(screen.getByText("Cobas - GLU")).toBeInTheDocument();
    expect(reload).not.toHaveBeenCalled();

    vi.useRealTimers();
    answer();
    await waitFor(() =>
      expect(screen.getByText("Cobas - UREA")).toBeInTheDocument(),
    );
    expect(reload).not.toHaveBeenCalled();
  });
});
