import React from "react";
import { render, screen } from "@testing-library/react";
import "@testing-library/jest-dom";
import { IntlProvider } from "react-intl";
import { vi, describe, it, expect, beforeEach } from "vitest";
import userEvent from "@testing-library/user-event";
import messages from "../../languages/en.json";

const { orderContextValue, push } = vi.hoisted(() => ({
  orderContextValue: {
    saveStatus: "saved",
    isDirty: false,
    labNumber: "LAB-42",
    isReadOnly: false,
    isEditMode: false,
    enableEditMode: vi.fn(),
    orderData: {},
  },
  push: vi.fn(),
}));

vi.mock("react-router-dom", () => ({
  useLocation: () => ({ pathname: "/order/clinical/enter", search: "" }),
  useHistory: () => ({ push }),
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
vi.mock("../common/PageBreadCrumb", () => ({ default: () => null }));
vi.mock("./OrderContextCard", () => ({ default: () => null }));
vi.mock("./BarcodeScannerBar", () => ({ default: () => null }));
vi.mock("./SaveNavigationButtons", () => ({ default: () => null }));
vi.mock("./OrderStepper", async () => {
  const actual = await vi.importActual("./OrderStepper");
  return { ...actual, default: () => null };
});

import OrderWorkflowLayout from "./OrderWorkflowLayout";

const renderLayout = () =>
  render(
    <IntlProvider locale="en" messages={messages}>
      <OrderWorkflowLayout title="order.step.enter">
        <div>form</div>
      </OrderWorkflowLayout>
    </IntlProvider>,
  );

describe("the state after the entry step saves", () => {
  beforeEach(() => {
    push.mockClear();
    orderContextValue.saveStatus = "saved";
    orderContextValue.isDirty = false;
    orderContextValue.labNumber = "LAB-42";
  });

  it("names the saved order and offers the next step", async () => {
    renderLayout();

    const collect = messages["order.step.collect"];
    expect(screen.getByText("Order LAB-42 saved")).toBeInTheDocument();
    expect(screen.getByText(`Next: ${collect}`)).toBeInTheDocument();
    await userEvent
      .setup()
      .click(screen.getByRole("button", { name: collect }));
    expect(push).toHaveBeenCalledWith("/order/clinical/collect");
  });

  it("says nothing while the order is unsaved or has changed since", () => {
    orderContextValue.labNumber = null;
    const first = renderLayout();
    expect(screen.queryByText(/saved$/)).not.toBeInTheDocument();
    first.unmount();

    orderContextValue.labNumber = "LAB-42";
    orderContextValue.isDirty = true;
    renderLayout();
    expect(screen.queryByText("Order LAB-42 saved")).not.toBeInTheDocument();
  });
});
