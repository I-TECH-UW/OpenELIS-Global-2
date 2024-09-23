class Result {
  getResultTitle() {
    return cy.get("section > h3");
  }

  selectUnitType(unitType) {
    cy.get("#unitType").select(unitType);
  }

  acceptSample(index = 0) {
    cy.get(`.cds--checkbox-label`).eq(index).click();
  }

  acceptResult() {
    cy.get("#cell-accept-0 > .cds--form-item > .cds--checkbox-label").click();
  }

  expandSampleDetails(index = 0) {
    cy.get(`[data-testid="expander-button-${index}"]`).click();
  }

  selectTestMethod(index = 0, method) {
    cy.get(`#testMethod${index}`).select(method);
  }

  selectPatient() {
    cy.get(
      "tbody > :nth-child(1) > :nth-child(1) > .cds--radio-button-wrapper > .cds--radio-button__label > .cds--radio-button__appearance",
    ).click();
  }

  search() {
    cy.get(":nth-child(1) > :nth-child(5) > .cds--btn").click();
  }

  searchByTest() {
    cy.get(":nth-child(8) > #submit").click();
  }

  validatePatientResult(patient) {
    cy.get("tbody > :nth-child(1) > :nth-child(2)").should(
      "contain.text",
      patient.lastName,
    );
    cy.get("tbody > :nth-child(1) > :nth-child(3)").should(
      "contain.text",
      patient.firstName,
    );
  }

  referSample(index = 0, reason, institute) {
    cy.get(`#referralReason${index}`).select(reason);
    cy.get(`#institute${index}`).select(institute);
  }

  selectRefferedTest() {
    cy.get(
      "tbody > tr > .cds--table-column-checkbox > .cds--checkbox--inline > .cds--checkbox-label",
    ).click();
  }

  selectAnalysisStatus(status) {
    cy.get("#analysisStatus").select(status);
  }
  selectTestName(testName) {
    cy.get("#testName").select(testName);
  }

  printReport() {
    cy.get(":nth-child(6) > :nth-child(2) > .cds--btn")
      .should("be.visible")
      .click();
  }

  setResultValue(index = 0, value) {
    cy.get(`#resultValue${index}`).select(value);
  }

  submitResults() {
    cy.get("#submit").click();
  }
}

export default Result;
