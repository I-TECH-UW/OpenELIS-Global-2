import React from "react";
import { render, screen } from "@testing-library/react";
import "@testing-library/jest-dom";
import { IntlProvider } from "react-intl";
import { vi, describe, it, expect } from "vitest";
import messages from "../../languages/en.json";

const { orderContextValue } = vi.hoisted(() => ({
  orderContextValue: { saveStatus: "saved", error: null, fieldErrors: {} },
}));
vi.mock("./OrderContext", () => ({
  useOrderContext: () => orderContextValue,
  SaveStatus: {
    SAVED: "saved",
    SAVING: "saving",
    ERROR: "error",
    UNSAVED: "unsaved",
  },
}));

import SaveFailureNotice from "./SaveFailureNotice";

const renderNotice = (inlineFields) =>
  render(
    <IntlProvider locale="en" messages={messages}>
      <SaveFailureNotice inlineFields={inlineFields} />
    </IntlProvider>,
  );

describe("SaveFailureNotice", () => {
  it("is absent unless a save was blocked", () => {
    orderContextValue.saveStatus = "saved";
    renderNotice([]);
    expect(
      screen.queryByText("The order was not saved"),
    ).not.toBeInTheDocument();
  });

  it("shows the message and the fields not already marked inline", () => {
    orderContextValue.saveStatus = "error";
    orderContextValue.error = "Validation failed";
    orderContextValue.fieldErrors = {
      "sampleOrderItems.labNo": "must not be blank",
      "patientProperties.lastName": "required",
    };
    renderNotice(["sampleOrderItems.labNo"]);
    expect(screen.getByText("The order was not saved")).toBeInTheDocument();
    expect(screen.getByText(/Validation failed/)).toBeInTheDocument();
    expect(
      screen.getByText("patientProperties.lastName: required"),
    ).toBeInTheDocument();
    expect(
      screen.queryByText(/sampleOrderItems.labNo/),
    ).not.toBeInTheDocument();
  });
});
