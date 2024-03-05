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
    accessionNo = 'input#labNumber';
    city = 'input#city';
    town = 'input#city';
    region = 'select#health_region';
    district = 'select#health_district';
    eduction = 'select#education';
    martialStatus = 'select#maritialStatus';
    nationality = 'select#nationality';
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

    enterPatientInfo(firstName, lastName, subjectNumber, NationalId, dateOfBirth, gender,town,region,district,martialStatus,eduction,nationality) {
        cy.enterText(this.subjectNumber, subjectNumber);
        cy.enterText(this.nationalId, NationalId);
        cy.enterText(this.lastNameSelector, lastName);
        cy.enterText(this.firstNameSelector, firstName);
        cy.enterText(this.dateOfBirth, dateOfBirth);

        cy.getElement('.cds--form .cds--accordion--md:nth-child(7) .cds--accordion__heading').click();

        cy.enterText(this.town,town);
        cy.get(this.region).select(region);
        cy.wait(1000);
        cy.get(this.district).select(district);
        cy.get(this.martialStatus).select(martialStatus);
        cy.get(this.eduction).select(eduction);
        cy.get(this.nationality).select(nationality);
        if (gender === "Male") {
            this.getMaleGenderRadioButton().click();
        }else{
            this.getFemaleGenderRadioButton();
        }
    }

    clickSavePatientButton() {
        this.getSubmitButton().click();
    }

    getMaleGenderRadioButton() {
        return cy.getElement('div:nth-of-type(1) > .cds--radio-button__label > .cds--radio-button__appearance');
    }

    getFemaleGenderRadioButton(){
        return cy.getElement('div:nth-of-type(2) > .cds--radio-button__label > .cds--radio-button__appearance');
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

    getNationaID() {
        return cy.getElement(this.nationalId);
    }

    getSubjectNo() {
        return cy.getElement(this.subjectNumber);
    }

    getPhoneNo() {
        return cy.getElement(this.primaryPhone);
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

    searchPatientByAccessionNo(labNo) {
        cy.enterText(this.accessionNo, labNo);
    }

    getPatientSearchResultsTable() {
        return cy.getElement('.cds--data-table.cds--data-table--lg.cds--data-table--sort > tbody');
    }

     getSelectedSearchPatient(){
        return cy.getElement('tr:nth-of-type(1) > td:nth-of-type(1) > .cds--radio-button-wrapper > .cds--radio-button__label > .cds--radio-button__appearance');
     }

    validatePatientSearchTable(actualName, inValidName) {
        this.getPatientSearchResultsTable().find('tr')
            .last()
            .find('td:nth-child(3)')
            .invoke('text')
            .then((cellText) => {
                const trimmedText = cellText.trim();
                /** to review this **/
                // expect(trimmedText).to.contain(actualName);
                expect(trimmedText).not.eq(inValidName)
            });
    }

    selectPatientFromSearchResults() {
        this.getPatientSearchResultsTable().find('tr')
            .first().find('td:nth-child(1)').click();
    }

}

export default PatientEntryPage;
