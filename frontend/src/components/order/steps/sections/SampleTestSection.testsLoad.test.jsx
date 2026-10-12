import React from "react";
import { act, fireEvent, render, screen } from "@testing-library/react";
import "@testing-library/jest-dom";
import { vi } from "vitest";
import { IntlProvider } from "react-intl";
import messages from "../../../../languages/en.json";

const { getFromOpenElisServer } = vi.hoisted(() => ({
  getFromOpenElisServer: vi.fn(),
}));

vi.mock("../../../utils/Utils", () => ({ getFromOpenElisServer }));

import SampleTestSection from "./SampleTestSection";

/**
 * OGC-1449: the tests list on Enter Order follows the sample type chosen last,
 * whatever order the responses arrive in, and a failed request ends "Loading"
 * with an error and a Retry instead of loading forever.
 */

const pendingTests = {};

beforeEach(() => {
  getFromOpenElisServer.mockReset();
  Object.keys(pendingTests).forEach((key) => delete pendingTests[key]);
  getFromOpenElisServer.mockImplementation((url, callback) => {
    if (url.startsWith("/rest/user-sample-types")) {
      callback([
        { id: "ST1", value: "Serum" },
        { id: "ST2", value: "Urine" },
      ]);
    } else if (url.startsWith("/rest/sample-type-tests")) {
      const sampleType = new URL(url, "http://x").searchParams.get(
        "sampleType",
      );
      pendingTests[sampleType] = callback;
    }
  });
});

const renderSection = (sampleTypeId) => (
  <IntlProvider locale="en" messages={messages}>
    <SampleTestSection
      samples={[{ sampleTypeId, tests: [], panels: [] }]}
      setSamples={vi.fn()}
      orderData={{}}
      setOrderData={vi.fn()}
      isReadOnly={false}
      workflowType="clinical"
    />
  </IntlProvider>
);

const serumTests = { tests: [{ id: "T1", name: "Serum albumin" }], panels: [] };
const urineTests = { tests: [{ id: "T2", name: "Urine protein" }], panels: [] };

describe("SampleTestSection tests loading (OGC-1449)", () => {
  it("keeps the current sample type's tests when the previous type answers last", () => {
    const { rerender } = render(renderSection("ST1"));
    rerender(renderSection("ST2"));

    act(() => pendingTests.ST2(urineTests));
    act(() => pendingTests.ST1(serumTests));

    expect(screen.getByText("Urine protein")).toBeInTheDocument();
    expect(screen.queryByText("Serum albumin")).not.toBeInTheDocument();
  });

  it("does not show the previous type's tests while the new type loads", () => {
    const { rerender } = render(renderSection("ST1"));
    act(() => pendingTests.ST1(serumTests));
    expect(screen.getByText("Serum albumin")).toBeInTheDocument();

    rerender(renderSection("ST2"));

    expect(screen.queryByText("Serum albumin")).not.toBeInTheDocument();
  });

  it("ends loading with an error and a Retry when the request fails, and Retry loads the tests", () => {
    render(renderSection("ST1"));

    act(() => pendingTests.ST1(undefined));

    expect(screen.queryByText(/^Loading/)).not.toBeInTheDocument();
    expect(
      screen.getByText("The tests for this sample type could not be loaded."),
    ).toBeInTheDocument();

    fireEvent.click(screen.getByRole("button", { name: "Retry" }));
    act(() => pendingTests.ST1(serumTests));

    expect(screen.getByText("Serum albumin")).toBeInTheDocument();
    expect(
      screen.queryByText("The tests for this sample type could not be loaded."),
    ).not.toBeInTheDocument();
  });
});
