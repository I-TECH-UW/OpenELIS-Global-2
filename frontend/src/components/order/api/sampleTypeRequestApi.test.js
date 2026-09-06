import { describe, expect, it } from "vitest";
import {
  convertRequestsToSamples,
  toRequestedSampleTypes,
} from "./sampleTypeRequestApi";

describe("convertRequestsToSamples", () => {
  it("preserves workflow and Method metadata when restoring selected tests", () => {
    const selectedTest = {
      id: "42",
      name: "Blood culture",
      cultureWorkflowType: "BACTERIOLOGY",
      methods: [
        {
          methodId: "7",
          methodName: "Blood Culture Standard",
          isDefault: true,
        },
      ],
    };

    const samples = convertRequestsToSamples([
      {
        id: "11",
        typeOfSampleId: "5",
        typeOfSampleName: "Blood",
        requestedTests: "42",
        requestedTestNames: "Blood culture",
        requestedTestDetails: [selectedTest],
        status: "REQUESTED",
      },
    ]);

    expect(samples[0].tests).toEqual([selectedTest]);
  });

  it("retains compatibility with pending requests that only contain IDs and names", () => {
    const samples = convertRequestsToSamples([
      {
        id: "11",
        typeOfSampleId: "5",
        requestedTests: "42",
        requestedTestNames: "Blood culture",
      },
    ]);

    expect(samples[0].tests).toEqual([{ id: "42", name: "Blood culture" }]);
  });
});

describe("requested specimens sent with the order", () => {
  it("carries the entered type, quantity, tests and panels in entry order", () => {
    const requested = toRequestedSampleTypes([
      {
        sampleTypeId: "5",
        quantity: "2.5",
        quantityUnit: "9",
        tests: [{ id: "42" }, { id: "43" }],
        panels: [{ id: "7" }],
      },
      { sampleTypeId: "6" },
    ]);

    expect(requested).toEqual([
      {
        typeOfSampleId: "5",
        requestedQuantity: 2.5,
        unitOfMeasureId: "9",
        requestedTests: "42,43",
        requestedPanels: "7",
      },
      {
        typeOfSampleId: "6",
        requestedQuantity: null,
        unitOfMeasureId: null,
        requestedTests: "",
        requestedPanels: "",
      },
    ]);
  });

  it("ignores rows where no sample type was chosen", () => {
    expect(toRequestedSampleTypes([{ quantity: "3" }, {}])).toEqual([]);
    expect(toRequestedSampleTypes()).toEqual([]);
  });
});
