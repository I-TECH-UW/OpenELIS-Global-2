class NonConform {
  getReportNonConformTitle() {
    return cy.get("h2");
  }

  selectSearchType(type) {
    cy.get("#type").select(type);
  }

  enterSearchField(value) {
    cy.get("#field\\.name").type(value);
  }

  clickSearchButton() {
    cy.get(":nth-child(4) > .cds--btn").click();
  }

  validateSearchResult(expectedValue) {
    cy.get("tbody > tr > :nth-child(2)")
      .invoke("text")
      .should("eq", expectedValue);
  }

  clickCheckbox() {
    cy.get(".cds--checkbox-label").click();
  }

  clickGoToNceFormButton() {
    cy.get(":nth-child(2) > :nth-child(2) > .cds--btn").click();
  }

  enterStartDate(date) {
    cy.get(".cds--date-picker-input__wrapper > #startDate").type(date);
  }

  submitForm() {
    cy.get(":nth-child(16) > .cds--btn").click();
  }
}

export default NonConform;
