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

  enterReportingUnit(unit) {
    cy.get("#reportingUnits").select(unit);
  }

  enterDescription(description) {
    cy.get('#text-area-1').type(description);
  
  }
  enterSuspectedCause(SuspectedCause) {
    cy.get('#text-area-2').type(SuspectedCause);
  
  }
  enterCorrectiveAction(correctiveaction) {
    cy.get('#text-area-3').type(correctiveaction);
  
  }

  submitForm() {
    cy.get(":nth-child(14) > .cds--btn").click();
  }

  getAndSaveNceNumber() {
    cy.get(".orderLegendBody > :nth-child(2) > :nth-child(3) > :nth-child(2)")
      .invoke("text")
      .then((text) => {
        cy.readFile("cypress/fixtures/NonConform.json").then((existingData) => {
          const newData = {
            ...existingData,
            NceNumber: text.trim(),
          };
          cy.writeFile("cypress/fixtures/NonConform.json", newData);
        });
      });
  }
  
}

export default NonConform;
