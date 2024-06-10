class ModifyOrderPage {
  constructor() {}

  visit() {
    cy.visit("/FindOrder");
  }

  enterAccessionNo(accessionNo) {
    cy.enterText(
      ":nth-child(2) > .cds--form-item > .cds--text-input__field-outer-wrapper > .cds--text-input__field-wrapper > #labNumber",
      accessionNo,
    );
  }

  clickSubmitButton() {
    return cy
      .getElement(
        "div[class='cds--lg:col-span-2 cds--css-grid-column'] button[type='submit']",
      )
      .should("be.visible")
      .click();
  }

  clickNextButton() {
    return cy.get(".forwardButton").should("be.visible").click();
  }

  checkProgramButton() {
    return cy.get("#additionalQuestionsSelect").should("be.disabled");
  }

  assignValues() {
    cy.get(
      ":nth-child(1) > :nth-child(4) > .cds--form-item > .cds--checkbox-label",
    ).click();
  }

  clickPrintBarcodeButton() {
    return cy.get(".orderEntrySuccessMsg > :nth-child(3) > .cds--btn").click();
  }
  clickSearchPatientButton() {
    return cy.get(":nth-child(12) > .cds--btn").click({ force: true });
  }

  clickRespectivePatient() {
    return cy.get('tbody > :nth-child(1) > :nth-child(1) > .cds--radio-button-wrapper > .cds--radio-button__label > .cds--radio-button__appearance')
      .click();
  }
}

export default ModifyOrderPage;
