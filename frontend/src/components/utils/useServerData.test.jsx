import React from "react";
import { render, screen } from "@testing-library/react";
import { waitFor } from "@testing-library/dom";
import userEvent from "@testing-library/user-event";
import { QueryClientProvider } from "@tanstack/react-query";
import { IntlProvider } from "react-intl";
import messages from "../../languages/en.json";
import { NotificationContext } from "../layout/contexts";
import { beforeEach, describe, expect, it, vi } from "vitest";
import { getFromOpenElisServer } from "./Utils";
import { createQueryClient } from "./queryClient";
import { useServerData, useInvalidateServerData } from "./useServerData";

vi.mock("./Utils", async () => {
  const actual = await vi.importActual("./Utils");
  return { ...actual, getFromOpenElisServer: vi.fn() };
});

const Screen = ({ endPoint = "/rest/things", second = null }) => {
  const { data } = useServerData(endPoint);
  const other = useServerData(second);
  const invalidate = useInvalidateServerData();
  return (
    <>
      <output data-testid="value">{data?.name ?? "—"}</output>
      <output data-testid="other">{other.data?.name ?? "—"}</output>
      <button type="button" onClick={() => invalidate(endPoint)}>
        refresh one
      </button>
      <button type="button" onClick={() => invalidate()}>
        refresh all
      </button>
    </>
  );
};

const renderWithCache = (props, notificationContext) =>
  render(
    <IntlProvider locale="en" messages={messages}>
      <QueryClientProvider client={createQueryClient()}>
        <NotificationContext.Provider value={notificationContext ?? {}}>
          <Screen {...props} />
        </NotificationContext.Provider>
      </QueryClientProvider>
    </IntlProvider>,
  );

describe("useServerData", () => {
  beforeEach(() => {
    getFromOpenElisServer.mockReset();
  });

  it("reads the endpoint and shows what the server returned", async () => {
    getFromOpenElisServer.mockImplementation((url, cb) =>
      cb({ name: "first" }),
    );

    renderWithCache({});

    expect(await screen.findByText("first")).toBeInTheDocument();
    expect(getFromOpenElisServer).toHaveBeenCalledWith(
      "/rest/things",
      expect.any(Function),
      expect.anything(),
    );
  });

  it("reads again after the endpoint is invalidated", async () => {
    let onServer = { name: "before" };
    getFromOpenElisServer.mockImplementation((url, cb) => cb(onServer));

    renderWithCache({});
    expect(await screen.findByText("before")).toBeInTheDocument();

    onServer = { name: "after" };
    await userEvent.click(screen.getByRole("button", { name: "refresh one" }));

    expect(await screen.findByText("after")).toBeInTheDocument();
  });

  it("retires every read when no endpoint is named", async () => {
    let onServer = { name: "before" };
    getFromOpenElisServer.mockImplementation((url, cb) =>
      cb(
        url === "/rest/others"
          ? { ...onServer, name: onServer.name + "-two" }
          : onServer,
      ),
    );

    renderWithCache({ second: "/rest/others" });
    expect(await screen.findByText("before")).toBeInTheDocument();
    expect(await screen.findByText("before-two")).toBeInTheDocument();

    onServer = { name: "after" };
    await userEvent.click(screen.getByRole("button", { name: "refresh all" }));

    // A screen built from several reads has to refresh all of them, the way
    // reloading the document did; refreshing one would leave the rest stale.
    expect(await screen.findByText("after")).toBeInTheDocument();
    expect(await screen.findByText("after-two")).toBeInTheDocument();
  });

  it("holds off until it has an endpoint to read", async () => {
    getFromOpenElisServer.mockImplementation((url, cb) => cb({ name: "x" }));

    renderWithCache({ endPoint: null });

    await waitFor(() =>
      expect(screen.getByTestId("value")).toHaveTextContent("—"),
    );
    expect(getFromOpenElisServer).not.toHaveBeenCalled();
  });

  it("tells the user once when a read fails, instead of spinning forever", async () => {
    // getFromOpenElisServer reports a failed read by calling back undefined —
    // fetchFromServer turns that into a rejected query.
    getFromOpenElisServer.mockImplementation((url, cb) => cb(undefined));
    const addNotification = vi.fn();
    const setNotificationVisible = vi.fn();

    renderWithCache({}, { addNotification, setNotificationVisible });

    await waitFor(() => expect(addNotification).toHaveBeenCalledTimes(1));
    expect(addNotification.mock.calls[0][0]).toMatchObject({
      kind: "error",
      message: messages["server.error.msg"],
    });
    expect(setNotificationVisible).toHaveBeenCalledWith(true);

    // Re-rendering while the same endpoint keeps failing must not repeat it.
    await userEvent.click(screen.getByRole("button", { name: "refresh one" }));
    await waitFor(() => expect(getFromOpenElisServer).toHaveBeenCalledTimes(2));
    expect(addNotification).toHaveBeenCalledTimes(1);
  });
});
