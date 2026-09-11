import React from "react";
import { render, screen } from "@testing-library/react";
import "@testing-library/jest-dom";
import { IntlProvider } from "react-intl";
import { vi } from "vitest";
import messages from "../../../languages/en.json";

const { orderContextValue, programSectionProps } = vi.hoisted(() => ({
  orderContextValue: {
    orderData: {
      patientProperties: { lastName: "Ada" },
      sampleOrderItems: {
        environmentalFields: { workflowType: "clinical" },
      },
    },
    setOrderData: vi.fn(),
    samples: [
      {
        sampleTypeId: "blood",
        tests: [
          {
            id: "culture-test",
            cultureWorkflowType: "BACTERIOLOGY",
          },
        ],
      },
    ],
    setSamples: vi.fn(),
    labNumber: "LAB-1",
    isSubmitting: false,
    saveStatus: "saved",
    error: null,
    fieldErrors: {},
    saveOrderEntry: vi.fn(),
    markStepComplete: vi.fn(),
    isReadOnly: false,
    isEditMode: false,
    resetOrder: vi.fn(),
  },
  programSectionProps: vi.fn(),
}));

vi.mock("react-router-dom", () => ({
  useHistory: () => ({ push: vi.fn(), replace: vi.fn() }),
  useLocation: () => ({ pathname: "/order/clinical/enter", search: "" }),
}));

vi.mock("../OrderContext", () => ({
  useOrderContext: () => orderContextValue,
  SaveStatus: {
    SAVED: "saved",
    SAVING: "saving",
    ERROR: "error",
    UNSAVED: "unsaved",
  },
}));

vi.mock("../../layout/Layout", () => ({
  NotificationContext: React.createContext({
    notificationVisible: false,
    setNotificationVisible: vi.fn(),
    addNotification: vi.fn(),
  }),
}));

vi.mock("../../common/CustomNotification", () => ({
  AlertDialog: () => null,
  NotificationKinds: { error: "error", success: "success" },
}));

vi.mock("../../utils/Utils", () => ({
  getFromOpenElisServer: vi.fn(),
}));

vi.mock("../OrderWorkflowLayout", () => ({
  default: ({ children, extraButtons }) => (
    <div>
      {children}
      {extraButtons}
    </div>
  ),
}));

vi.mock("./sections/PatientSearchSection", () => ({
  default: () => null,
}));

vi.mock("./sections/ProgramSection", () => ({
  default: (props) => {
    programSectionProps(props);
    return <div data-testid="program-section" />;
  },
}));

vi.mock("./sections/ClinicalInfoSection", () => ({
  default: () => null,
}));

vi.mock("./sections/RequesterSection", () => ({
  default: () => null,
}));

vi.mock("./sections/SampleTestSection", () => ({
  default: () => null,
}));

import ClinicalOrderEnter from "./ClinicalOrderEnter";

describe("ClinicalOrderEnter", () => {
  beforeEach(() => {
    programSectionProps.mockClear();
  });

  it("does not offer a second draft save while one is in flight", () => {
    orderContextValue.isSubmitting = true;
    const { unmount } = render(
      <IntlProvider locale="en" messages={messages}>
        <ClinicalOrderEnter />
      </IntlProvider>,
    );

    expect(screen.getByRole("button", { name: "Save Draft" })).toBeDisabled();
    unmount();

    orderContextValue.isSubmitting = false;
    render(
      <IntlProvider locale="en" messages={messages}>
        <ClinicalOrderEnter />
      </IntlProvider>,
    );

    expect(screen.getByRole("button", { name: "Save Draft" })).toBeEnabled();
  });

  it("marks the lab number and lists the rest when the server blocks the save", () => {
    orderContextValue.saveStatus = "error";
    orderContextValue.error = "sampleOrderItems.labNo: must not be blank";
    orderContextValue.fieldErrors = {
      "sampleOrderItems.labNo": "must not be blank",
      "sampleOrderItems.receivedDateForDisplay": "invalid date",
    };
    render(
      <IntlProvider locale="en" messages={messages}>
        <ClinicalOrderEnter />
      </IntlProvider>,
    );

    expect(screen.getByRole("textbox", { name: /Lab Number/ })).toBeInvalid();
    expect(screen.getByText("must not be blank")).toBeInTheDocument();
    expect(screen.getByText("The order was not saved")).toBeInTheDocument();
    expect(
      screen.getByText("sampleOrderItems.receivedDateForDisplay: invalid date"),
    ).toBeInTheDocument();

    orderContextValue.saveStatus = "saved";
    orderContextValue.error = null;
    orderContextValue.fieldErrors = {};
  });

  it("shares selected samples with the Program section", () => {
    render(
      <IntlProvider locale="en" messages={messages}>
        <ClinicalOrderEnter />
      </IntlProvider>,
    );

    expect(screen.getByTestId("program-section")).toBeInTheDocument();
    expect(programSectionProps).toHaveBeenCalledWith(
      expect.objectContaining({ samples: orderContextValue.samples }),
    );
  });
});
