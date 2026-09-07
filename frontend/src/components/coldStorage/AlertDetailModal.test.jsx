import React from "react";
import { vi } from "vitest";
import { fireEvent, render, screen } from "@testing-library/react";
import "@testing-library/jest-dom";
import { IntlProvider } from "react-intl";
import messages from "../../languages/en.json";
import AlertDetailModal from "./AlertDetailModal";

vi.mock("./api", () => ({
  fetchAlertDetails: vi.fn(() =>
    Promise.resolve({
      id: 7,
      alertType: "FREEZER_TEMPERATURE",
      alertEntityType: "Freezer",
      alertEntityId: 100,
      severity: "CRITICAL",
      status: "OPEN",
      message: "Temperature threshold violated",
    }),
  ),
  acknowledgeAlert: vi.fn(),
  resolveAlert: vi.fn(),
  deleteAlert: vi.fn(),
  createCorrectiveAction: vi.fn(),
}));

/**
 * The action-type labels must come from the translation catalogue, not from a
 * hardcoded English list, so a non-English deployment does not get an English
 * dropdown inside an otherwise translated modal.
 */
describe("AlertDetailModal corrective-action types", () => {
  it("renders the action-type options from the translation catalogue", async () => {
    render(
      <IntlProvider
        locale="en"
        messages={{
          ...messages,
          "coldStorage.correctiveAction.type.calibration": "Étalonnage",
        }}
      >
        <AlertDetailModal alertId={7} open onClose={vi.fn()} />
      </IntlProvider>,
    );

    fireEvent.click(await screen.findByText("Log corrective action"));
    fireEvent.click(screen.getByRole("combobox"));

    expect(await screen.findByText("Étalonnage")).toBeInTheDocument();
  });
});
