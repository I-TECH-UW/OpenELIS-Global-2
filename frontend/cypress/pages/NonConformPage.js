class NonConform {
  getReportNonConformTitle() {
    return cy.get("h2");
  }

  getViewNonConformTitle(){
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

  validateLabNoSearchResult(labNo){
    cy.get('.orderLegendBody > :nth-child(2) > :nth-child(7) > :nth-child(2)').invoke('text').should('eq', labNo);
  }

  validateNCESearchResult(NCENo){
    cy.get('[style="margin-bottom: 10px; color: rgb(85, 85, 85);"]').invoke('text').should('eq', NCENo);
  }

  validateLabNoSearchResultCorective(labNo){
    cy.get('.cds--subgrid > :nth-child(7) > :nth-child(2)').invoke('text').should('eq', labNo);
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

  enterNceCategory(nceCategory) {
    cy.get('#nceCategory').select(nceCategory);
  }

  enterNceType(nceType) {
    cy.get('#nceType').select(nceType);
  }

  enterConsequences(consequences) {
    cy.get('#consequences').select(consequences);
  }

  enterRecurrence(recurrence) {
    cy.get('#recurrence').select(recurrence);
  }

  enterLabComponent(labComponent) {
    cy.get('#labComponent').select(labComponent);
  }

  enterDescriptionAndComments(testText) {
    cy.get('#text-area-10').type(testText);
    cy.get('#text-area-3').type(testText);
    cy.get('#text-area-2').type(testText);
  }

  submitForm() {
    cy.get(':nth-child(28) > .cds--btn').click();
  }

  submitFormNce(){
    cy.get(':nth-child(14) > .cds--btn').click();
  }


  enterDiscussionDate(date) {
    cy.get('.cds--date-picker-input__wrapper > #tdiscussionDate').type(date);
  }
  
  enterProposedCorrectiveAction(action) {
    cy.get('#text-area-corrective').type(action, { force: true });
  }
  
  enterDateCompleted(date) {
    cy.get('.cds--date-picker-input__wrapper > #dateCompleted').type(date);
  }
  
  selectActionType() {
    cy.get(':nth-child(1) > .cds--checkbox-label').click();
  }
  
  selectResolution() {
    cy.get(':nth-child(1) > .cds--radio-button__label').click();
  }
  
  enterDateCompleted0(date) {
    cy.get('.cds--date-picker-input__wrapper > #dateCompleted-0').type(date);
  }
  
  clickSubmitButton() {
    cy.get(':nth-child(38) > .cds--btn').click();
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
