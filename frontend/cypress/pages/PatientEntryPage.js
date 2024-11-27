class PatientEntryPage {
    subjectNumber = 'input#subjectNumber';
    nationalId = 'input#nationalId';
    firstNameSelector = 'input#firstName';
    lastNameSelector = 'input#lastName';
    personContactLastName = 'input#patientContact\\.person\\.lastName';
    personContactFirstName = 'input#patientContact\\.person\\.firstName';
    personContactPrimaryPhone = 'input#patientContact\\.person\\.primaryPhone';
    personContactEmail = 'input#patientContact\\.person\\.email';
    patientIdSelector = 'input#patientId';
    city = 'input#city';
    primaryPhone = 'input#primaryPhone';
    dateOfBirth = 'input#date-picker-default-id';
    savePatientBtn = 'button#submit';

    constructor() {
    }

    visit() {
        cy.visit('/PatientManagement');
    }

    getPatientEntryPageTitle() {
        return cy.get('section > h3');
    }

    clickNewPatientTab() {
        cy.get('.tabsLayout [type=\'button\']:nth-of-type(2)').click();
    }

    enterPatientInfo(firstName, lastName, subjectNumber, NationalId, dateOfBirth) {
        cy.enterText(this.subjectNumber, subjectNumber);
        cy.enterText(this.nationalId, NationalId);
        cy.enterText(this.lastNameSelector, lastName);
        cy.enterText(this.firstNameSelector, firstName);
        cy.enterText(this.dateOfBirth, dateOfBirth);
        this.getMaleGenderRadioButton().click();
        cy.getElement('.cds--accordion__heading > .cds--accordion__title').click();
    }

    clickSavePatientButton() {
        this.getSubmitButton().click();
    }

    getMaleGenderRadioButton() {
        return cy.getElement('div:nth-of-type(1) > .cds--radio-button__label > .cds--radio-button__appearance');
    }

    clickSearchPatientButton() {
        cy.getElement('.cds--form .cds--btn.cds--btn--primary').click();
    }

    getLastName() {
        return cy.getElement(this.lastNameSelector);
    }

    getFirstName() {
        return cy.getElement(this.firstNameSelector);
    }

    getSubmitButton() {
        return cy.getElement(this.savePatientBtn)
    }

    searchPatientByFirstAndLastName(firstName, lastName) {
        cy.enterText(this.firstNameSelector, firstName);
        cy.enterText(this.lastNameSelector, lastName);
    }

    searchPatientByPatientId(PID) {
        cy.enterText(this.patientIdSelector, PID);
    }

    getPatientSearchResultsTable() {
        return cy.getElement('.cds--data-table.cds--data-table--lg.cds--data-table--sort > tbody');
    }

    validatePatientSearchTable(actualName, inValidName) {
        this.getPatientSearchResultsTable().find('tr')
            .last()
            .find('td:nth-child(3)')
            .invoke('text')
            .then((cellText) => {
                const trimmedText = cellText.trim();
                expect(trimmedText).to.contain(actualName);
                expect(trimmedText).not.eq(inValidName)
            });
    }

    selectPatientFromSearchResults() {
        this.getPatientSearchResultsTable().find('tr')
            .first().find('td:nth-child(1)').click();
    }

}

export default PatientEntryPage;
