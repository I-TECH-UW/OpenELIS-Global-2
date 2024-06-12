import PatientEntryPage from "./PatientEntryPage";

class OrderEntityPage {
  sampleTypeOptionDropDown = "";

  constructor() {}

  visit() {
    cy.visit("/AddOrder");
  }

  getPatientPage() {
    return new PatientEntryPage();
  }

  clickNextButton() {
    cy.getElement(".cds--btn.cds--btn--primary.forwardButton").click();
  }

  selectSampleTypeOption(sampleType) {
    cy.getElement("select#sampleId_0").select(sampleType);
  }
  checkPanelCheckBoxField() {
    cy.get(
      ".testPanels .cds--checkbox-wrapper:nth-child(5) .cds--checkbox",
    ).check({ force: true });
  }
  generateLabOrderNumber() {
    cy.getElement(
      ":nth-child(2) > :nth-child(1) > :nth-child(2) > .cds--link",
    ).click();
  }

  enterSiteName(siteName) {
    cy.enterText("input#siteName", siteName);
  }
  enterRequesterLastAndFirstName(requesterFirstName, requesterLastName) {
    cy.enterText("input#requesterFirstName", requesterFirstName);
    cy.enterText("input#requesterLastName", requesterLastName);
  }
  clickSubmitOrderButton() {
    cy.getElement(
      ".navigationButtonsLayout [type='button']:nth-of-type(2)",
    ).click();
  }
}

export default OrderEntityPage;
