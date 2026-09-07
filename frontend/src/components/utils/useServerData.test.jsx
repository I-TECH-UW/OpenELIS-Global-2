import React from "react";
import { render, screen } from "@testing-library/react";
import { waitFor } from "@testing-library/dom";
import userEvent from "@testing-library/user-event";
import { QueryClientProvider } from "@tanstack/react-query";
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

const renderWithCache = (props) =>
  render(
    <QueryClientProvider client={createQueryClient()}>
      <Screen {...props} />
    </QueryClientProvider>,
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
});
