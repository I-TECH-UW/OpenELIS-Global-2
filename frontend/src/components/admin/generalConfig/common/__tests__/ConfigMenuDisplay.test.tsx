import React from "react";
import { render, screen, fireEvent } from "@testing-library/react";
import { waitFor } from "@testing-library/dom";
import userEvent from "@testing-library/user-event";
import { IntlProvider } from "react-intl";
import { MemoryRouter } from "react-router-dom";
import { QueryClientProvider } from "@tanstack/react-query";
import { beforeEach, describe, expect, it, vi } from "vitest";
import messages from "../../../../../languages/en.json";
import {
  getFromOpenElisServer,
  postToOpenElisServer,
} from "../../../../utils/Utils";
import { createQueryClient } from "../../../../utils/queryClient";
import { NotificationContext } from "../../../../layout/Layout";
import ConfigMenuDisplay from "../ConfigMenuDisplay";

vi.mock("../../../../utils/Utils", async () => {
  const actual = await vi.importActual("../../../../utils/Utils");
  return {
    ...actual,
    getFromOpenElisServer: vi.fn(),
    postToOpenElisServer: vi.fn(),
    postToOpenElisServerFormData: vi.fn(),
  };
});

const MENU = "NonConformityConfigurationMenu";
const CONFIG = "NonConformityConfiguration";

const config = (value: string) => ({
  id: "7",
  name: "resultsOnly",
  description: "Results only",
  value,
  valueType: "boolean",
  paramName: "resultsOnly",
});

describe("ConfigMenuDisplay", () => {
  let onServer: { value: string };

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
              <ConfigMenuDisplay
                id="admin.formEntryConfig"
                label="Non Conformity"
                menuType={MENU}
              />
            </NotificationContext.Provider>
          </QueryClientProvider>
        </IntlProvider>
      </MemoryRouter>,
    );

  const openTheEditor = async () => {
    renderScreen();
    const row = await screen.findByLabelText("selectRow");
    fireEvent.click(row);
    await userEvent.click(screen.getByRole("button", { name: "Modify" }));
    return screen.findByRole("heading", { name: "Edit Record" });
  };

  beforeEach(() => {
    onServer = { value: "false" };
    (getFromOpenElisServer as ReturnType<typeof vi.fn>).mockReset();
    (getFromOpenElisServer as ReturnType<typeof vi.fn>).mockImplementation(
      (url: string, callback: (r: unknown) => void) => {
        if (url === `/rest/${MENU}`)
          return callback({ menuList: [config(onServer.value)] });
        if (url.startsWith(`/rest/${CONFIG}?ID=`))
          return callback(config(onServer.value));
        return callback(undefined);
      },
    );
    (postToOpenElisServer as ReturnType<typeof vi.fn>).mockReset();
  });

  it("lists the configuration values", async () => {
    renderScreen();

    expect(await screen.findByText("Results only")).toBeInTheDocument();
  });

  it("returns to the list showing the saved value", async () => {
    await openTheEditor();

    await userEvent.click(screen.getByLabelText("True"));
    (postToOpenElisServer as ReturnType<typeof vi.fn>).mockImplementation(
      (url: string, body: string, callback: (status: number) => void) => {
        onServer = { value: "true" };
        callback(200);
      },
    );
    await userEvent.click(screen.getByRole("button", { name: "Save" }));

    // Reloading the document is what used to close the editor and refresh the
    // list; both now happen without leaving the app.
    await waitFor(() =>
      expect(
        screen.queryByRole("heading", { name: "Edit Record" }),
      ).not.toBeInTheDocument(),
    );
    expect(await screen.findByText("true")).toBeInTheDocument();
  });

  it("returns to the list when the edit is abandoned", async () => {
    await openTheEditor();

    await userEvent.click(screen.getByRole("button", { name: "Exit" }));

    await waitFor(() =>
      expect(
        screen.queryByRole("heading", { name: "Edit Record" }),
      ).not.toBeInTheDocument(),
    );
    expect(postToOpenElisServer).not.toHaveBeenCalled();
  });
});
