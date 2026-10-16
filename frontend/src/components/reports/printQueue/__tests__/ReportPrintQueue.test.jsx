import React from "react";
import { vi } from "vitest";
import { render, screen, fireEvent } from "@testing-library/react";
import { waitFor } from "@testing-library/dom";
import "@testing-library/jest-dom";
import { IntlProvider } from "react-intl";
import { MemoryRouter } from "react-router-dom";
import messages from "../../../../languages/en.json";
import ReportPrintQueue from "../ReportPrintQueue";

const MOCK_PAGE = {
  rows: [
    {
      sampleId: 9301,
      labNumber: "V1RPT0000009301",
      patientName: "Kila, Mary",
      patientCode: "PAT-9301",
      facility: "Port Moresby General Hospital",
      ward: "Ward 4",
      requester: "Mek, Paul",
      released: "2026-10-01T10:15:00+03:00",
      releasedCount: 2,
      totalCount: 3,
      state: "PARTIAL",
      printStatus: "UNPRINTED",
      critical: true,
      version: null,
      legacy: false,
      nothingReleased: false,
    },
    {
      sampleId: 9302,
      labNumber: "V1RPT0000009302",
      patientName: "Okello, Ben",
      patientCode: "PAT-9302",
      facility: "9 Mile Urban Clinic",
      ward: null,
      requester: null,
      released: "2026-10-02T08:00:00+03:00",
      releasedCount: 2,
      totalCount: 2,
      state: "AMENDED",
      printStatus: "PRINTED",
      critical: false,
      version: 3,
      legacy: false,
      nothingReleased: false,
    },
  ],
  total: 2,
  page: 1,
  pageSize: 20,
  toPrint: 1,
  amendedToPrint: 0,
  timeWindow: "7d",
  lookup: false,
};

const MOCK_DETAIL = {
  sampleId: 9302,
  labNumber: "V1RPT0000009302",
  facility: "9 Mile Urban Clinic",
  ward: null,
  requester: null,
  collected: "2026-10-02T07:00:00+03:00",
  received: "2026-10-02T07:30:00+03:00",
  installDate: "2026-01-01T00:00:00+03:00",
  tests: [
    {
      analysisId: 1,
      testName: "Glucose",
      result: "7.1 mmol/L",
      flag: "E",
      status: "FINALIZED",
      releasedAt: "2026-10-02T08:00:00+03:00",
      validatedBy: "Sarah Chen",
      released: true,
    },
  ],
  versions: [
    {
      id: 11,
      version: 1,
      issuedAt: "2026-10-02T09:00:00+03:00",
      issuedBy: "Sarah Chen",
      amended: false,
      changeSummary: null,
      releasedCount: 1,
      totalCount: 2,
      sha256: "abcdef0123456789abcdef0123456789abcdef0123456789abcdef0123456789",
      archived: true,
      legacy: false,
      archiveFailed: false,
      reprints: [{ printedAt: "2026-10-02T10:00:00+03:00", printedBy: "Ben Okello" }],
    },
  ],
};

const requests = [];
const failures = { page: false };

vi.mock("../../../utils/Utils", async (importOriginal) => ({
  toLocalIsoDate: (await importOriginal()).toLocalIsoDate,
  parseIsoDate: (await importOriginal()).parseIsoDate,
  getFromOpenElisServer: vi.fn((url, callback) => {
    requests.push(url);
    if (url.startsWith("/rest/reportQueue/orders/")) {
      callback(MOCK_DETAIL);
    } else if (url === "/rest/reportQueue/preferences") {
      callback({ timeWindow: "7d", pageSize: 20 });
    } else if (url.startsWith("/rest/reportQueue?")) {
      callback(failures.page ? null : MOCK_PAGE);
    } else {
      callback([]);
    }
  }),
  postToOpenElisServerForBlob: vi.fn((url, body, callback) => {
    requests.push(`POST ${url} ${body}`);
    callback(new Blob(["%PDF"], { type: "application/pdf" }), {
      headers: { get: (name) => (name === "X-Report-Version" ? "1" : "false") },
    });
  }),
  putToOpenElisServer: vi.fn((url, body, callback) => {
    requests.push(`PUT ${url} ${body}`);
    callback(200);
  }),
}));

vi.mock("../../../../config.json", () => ({
  default: { serverBaseUrl: "/api/OpenELIS-Global" },
}));

vi.mock("../../../patient/SearchPatientForm", () => ({
  default: ({ getSelectedPatient }) => (
    <button
      type="button"
      onClick={() =>
        getSelectedPatient({ patientPK: "77", lastName: "Kila", firstName: "Mary" })
      }
    >
      pick-patient
    </button>
  ),
}));

vi.mock("../../../common/PageBreadCrumb", () => ({
  default: () => <nav data-testid="breadcrumb" />,
}));

vi.mock("../../../common/CustomLabNumberInput", () => ({
  default: (props) => (
    <input
      id={props.id}
      name={props.name}
      aria-label={props.labelText}
      value={props.value}
      onChange={(event) => props.onChange(event, event.target.value)}
      onKeyDown={props.onKeyDown}
    />
  ),
}));

const renderPage = (path = "/ReportPrintQueue") =>
  render(
    <IntlProvider locale="en" messages={messages}>
      <MemoryRouter initialEntries={[path]}>
        <ReportPrintQueue />
      </MemoryRouter>
    </IntlProvider>,
  );

describe("ReportPrintQueue", () => {
  beforeEach(() => {
    requests.length = 0;
    window.open = vi.fn(() => ({}));
    URL.createObjectURL = vi.fn(() => "blob:report");
    URL.revokeObjectURL = vi.fn();
  });

  it("loads the queue on open without a search button and shows state, print status and tiles", async () => {
    renderPage();
    expect(screen.getByRole("heading", { name: "Report Print Queue" })).toBeInTheDocument();
    await waitFor(() => expect(screen.getByTestId("queue-row-V1RPT0000009301")).toBeInTheDocument());
    expect(requests[0]).toBe("/rest/reportQueue/preferences");
    expect(requests[1]).toMatch(/^\/rest\/reportQueue\?.*window=7d.*pageSize=20/);
    expect(requests.filter((url) => url.startsWith("/rest/reportQueue?"))).toHaveLength(1);
    expect(screen.queryByRole("button", { name: /^Search$/ })).not.toBeInTheDocument();
    expect(screen.getByTestId("state-9301")).toHaveTextContent("Partial 2/3");
    expect(screen.getByTestId("print-status-9301")).toHaveTextContent("Unprinted");
    expect(screen.getByText("Critical")).toBeInTheDocument();
    expect(screen.getByTestId("state-9302")).toHaveTextContent("Amended");
    expect(screen.getByTestId("print-status-9302")).toHaveTextContent("Printed");
    expect(screen.getByText("v3")).toBeInTheDocument();
    expect(screen.getByText("Not printed")).toBeInTheDocument();
    expect(screen.getByTestId("tile-to-print")).toHaveTextContent("1");
    expect(screen.getByTestId("tile-amended-to-print")).toHaveTextContent("0");
    expect(screen.getByText("Kila, Mary")).toBeInTheDocument();
    expect(screen.getByText("Port Moresby General Hospital / Ward 4")).toBeInTheDocument();
  });

  it("applies a lab number lookup on Enter, shows the active search strip and clears it", async () => {
    renderPage();
    await waitFor(() => expect(screen.getByTestId("queue-row-V1RPT0000009301")).toBeInTheDocument());
    const input = screen.getByLabelText("Lab Number");
    fireEvent.change(input, { target: { value: "V1RPT0000009301" } });
    fireEvent.keyDown(input, { key: "Enter" });
    await waitFor(() =>
      expect(requests.some((url) => url.includes("labNo=V1RPT0000009301"))).toBe(true),
    );
    expect(screen.getByTestId("active-search")).toHaveTextContent("Lab No: V1RPT0000009301");
    expect(screen.getByRole("button", { name: "Clear search" })).toBeInTheDocument();
    fireEvent.click(screen.getByRole("button", { name: "Clear search" }));
    await waitFor(() => expect(screen.queryByTestId("active-search")).not.toBeInTheDocument());
  });

  it("looks up every order of a chosen patient", async () => {
    renderPage();
    await waitFor(() => expect(screen.getByTestId("queue-row-V1RPT0000009301")).toBeInTheDocument());
    fireEvent.click(screen.getByRole("button", { name: "Search by patient" }));
    fireEvent.click(screen.getByText("pick-patient"));
    await waitFor(() => expect(requests.some((url) => url.includes("patientId=77"))).toBe(true));
    expect(screen.getByTestId("active-search")).toHaveTextContent("Patient: Kila, Mary");
  });

  it("prints a row through the queue endpoint, opens the PDF and reloads", async () => {
    renderPage();
    await waitFor(() => expect(screen.getByTestId("print-V1RPT0000009301")).toBeInTheDocument());
    const before = requests.filter((url) => url.startsWith("/rest/reportQueue?")).length;
    fireEvent.click(screen.getByTestId("print-V1RPT0000009301"));
    await waitFor(() =>
      expect(requests).toContain('POST /rest/reportQueue/print {"sampleId":9301}'),
    );
    expect(window.open).toHaveBeenCalledWith("blob:report", "_blank");
    await waitFor(() =>
      expect(requests.filter((url) => url.startsWith("/rest/reportQueue?")).length).toBe(before + 1),
    );
    expect(screen.getByText("Printed version 1 of V1RPT0000009301")).toBeInTheDocument();
  });

  it("loads the row detail on expand with tests and the version history", async () => {
    renderPage();
    await waitFor(() => expect(screen.getByTestId("queue-row-V1RPT0000009302")).toBeInTheDocument());
    const expanders = screen.getAllByRole("button", { name: /expand/i });
    fireEvent.click(expanders[1]);
    await waitFor(() => expect(screen.getByTestId("history")).toBeInTheDocument());
    expect(requests).toContain("/rest/reportQueue/orders/9302");
    expect(screen.getByText("7.1 mmol/L")).toBeInTheDocument();
    expect(screen.getAllByText("Sarah Chen")).toHaveLength(2);
    expect(screen.getByText("abcdef012345")).toBeInTheDocument();
    expect(screen.getByTestId("open-pdf-1")).toHaveAttribute(
      "href",
      "/api/OpenELIS-Global/rest/reportQueue/versions/11/pdf",
    );
    expect(screen.getByText(/Reprinted .* by Ben Okello/)).toBeInTheDocument();
  });

  it("persists the time window and page size per user", async () => {
    renderPage();
    await waitFor(() => expect(screen.getByTestId("queue-row-V1RPT0000009301")).toBeInTheDocument());
    fireEvent.click(screen.getByText("Last 7 days"));
    fireEvent.click(screen.getByText("Last 30 days"));
    await waitFor(() =>
      expect(requests).toContain('PUT /rest/reportQueue/preferences {"timeWindow":"30d","pageSize":20}'),
    );
    await waitFor(() => expect(requests.some((url) => url.includes("window=30d"))).toBe(true));
  });

  it("starts with the lab number from the URL", async () => {
    renderPage("/ReportPrintQueue?type=patient&labNo=V1RPT0000009301");
    await waitFor(() =>
      expect(requests.some((url) => url.includes("labNo=V1RPT0000009301"))).toBe(true),
    );
    expect(screen.getByTestId("active-search")).toHaveTextContent("Lab No: V1RPT0000009301");
  });

  it("offers Retry when the queue fails to load and loads it on click", async () => {
    failures.page = true;
    try {
      renderPage();
      await waitFor(() => expect(screen.getByTestId("queue-retry")).toBeInTheDocument());
      expect(screen.queryByTestId("queue-row-V1RPT0000009301")).not.toBeInTheDocument();
      failures.page = false;
      fireEvent.click(screen.getByTestId("queue-retry"));
      await waitFor(() => expect(screen.getByTestId("queue-row-V1RPT0000009301")).toBeInTheDocument());
    } finally {
      failures.page = false;
    }
  });

  it("offers a link to the report when the browser blocks the window", async () => {
    window.open = vi.fn(() => null);
    renderPage();
    await waitFor(() => expect(screen.getByTestId("print-V1RPT0000009301")).toBeInTheDocument());
    fireEvent.click(screen.getByTestId("print-V1RPT0000009301"));
    await waitFor(() => expect(screen.getByTestId("open-report")).toBeInTheDocument());
    expect(screen.getByTestId("open-report")).toHaveAttribute("href", "blob:report");
    expect(screen.getByText("Printed version 1 of V1RPT0000009301")).toBeInTheDocument();
  });
});
