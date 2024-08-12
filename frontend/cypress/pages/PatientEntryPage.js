class PatientEntryPage {
  subjectNumber = "input#subjectNumber";
  nationalId = "input#nationalId";
  firstNameSelector = "input#firstName";
  lastNameSelector = "input#lastName";
  personContactLastName = "input#patientContact\\.person\\.lastName";
  personContactFirstName = "input#patientContact\\.person\\.firstName";
  personContactPrimaryPhone = "input#patientContact\\.person\\.primaryPhone";
  personContactEmail = "input#patientContact\\.person\\.email";
  patientIdSelector = "input#patientId";
  labNoSelector = "#labNumber";
  city = "input#city";
  primaryPhone = "input#primaryPhone";
  dateOfBirth = "input#date-picker-default-id";
  savePatientBtn = "#submit";

  constructor() {}

  visit() {
    cy.visit("/PatientManagement");
  }

  getPatientEntryPageTitle() {
    return cy.get("section > h3");
  }

  clickNewPatientTab() {
    cy.get(":nth-child(1) > :nth-child(2) > .cds--btn").click();
  }

  enterPatientInfo(
    firstName,
    lastName,
    subjectNumber,
    NationalId,
    dateOfBirth,
  ) {
    cy.enterText(this.subjectNumber, subjectNumber);
    cy.enterText(this.nationalId, NationalId);
    cy.enterText(this.lastNameSelector, lastName);
    cy.enterText(this.firstNameSelector, firstName);
    cy.enterText(this.dateOfBirth, dateOfBirth);
    this.getMaleGenderRadioButton().click();
    cy.getElement("#submit").click();
  }

  clickSavePatientButton() {
    this.getSubmitButton().click();
  }

  getMaleGenderRadioButton() {
    return cy.getElement(
      ":nth-child(2) > .cds--radio-button__label > .cds--radio-button__appearance",
    );
  }

  clickSearchPatientButton() {
    cy.getElement("#local_search").click();
  }

  getExternalSearchButton() {
    cy.get("#external_search").should("be.disabled");
  }

  getLastName() {
    return cy.getElement(this.lastNameSelector);
  }

  getFirstName() {
    return cy.getElement(this.firstNameSelector);
  }
  searchPatientByFirstNameOnly(firstName) {
    cy.enterText(this.firstNameSelector, firstName);
  }

  searchPatientByLastNameOnly(lastName) {
    cy.enterText(this.lastNameSelector, lastName);
  }

  searchPatientByDateOfBirth(dateOfBirth) {
    cy.enterText(this.dateOfBirth, dateOfBirth);
  }
  getSubmitButton() {
    return cy.getElement(this.savePatientBtn);
  }

  searchPatientByFirstAndLastName(firstName, lastName) {
    cy.enterText(this.firstNameSelector, firstName);
    cy.enterText(this.lastNameSelector, lastName);
  }

  searchPatientByPatientId(PID) {
    cy.enterText(this.patientIdSelector, PID);
  }

  searchPatientBylabNo(labNo) {
    cy.enterText(this.labNoSelector, labNo);
  }

  getPatientSearchResultsTable() {
    return cy.getElement(
      ".cds--data-table.cds--data-table--lg.cds--data-table--sort > tbody",
    );
  }
  validatePatientSearchTablebyRespectiveField(expectedFieldValue, searchBy) {
    this.getPatientSearchResultsTable()
      .find("tr")
      .each(($el, index, $list) => {
        if (searchBy === "firstName") {
          cy.wrap($el)
            .find("td:nth-child(3)")
            .invoke("text")
            .then((cellText) => {
              const trimmedText = cellText.trim();
              expect(trimmedText).to.contain(expectedFieldValue);
            });
        } else if (searchBy === "lastName") {
          cy.wrap($el)
            .find("td:nth-child(2)")
            .invoke("text")
            .then((cellText) => {
              const trimmedText = cellText.trim();
              expect(trimmedText).to.contain(expectedFieldValue);
            });
        } else if (searchBy === "DOB") {
          cy.wrap($el)
            .find("td:nth-child(5)")
            .invoke("text")
            .then((cellText) => {
              const trimmedText = cellText.trim();
              expect(trimmedText).to.contain(expectedFieldValue);
            });
        }
      });
  }

  validatePatientSearchTable(actualName, inValidName) {
    this.getPatientSearchResultsTable()
      .find("tr")
      .last()
      .find("td:nth-child(3)")
      .invoke("text")
      .then((cellText) => {
        const trimmedText = cellText.trim();
        expect(trimmedText).to.contain(actualName);
        expect(trimmedText).not.eq(inValidName);
      });
  }

  validatePatientByGender(expectedGender) {
    this.getPatientSearchResultsTable()
      .find("tr")
      .last()
      .find("td:nth-child(4)")
      .invoke("text")
      .then((cellText) => {
        const trimmedText = cellText.trim();
        expect(trimmedText).to.eq(expectedGender);
      });
  }

  selectPatientFromSearchResults() {
    this.getPatientSearchResultsTable()
      .find("tr")
      .first()
      .find("td:nth-child(1)")
      .click();
  }
}

export default PatientEntryPage;
