class PatientEntryPage {

    constructor() {
    }

    visit() {
        cy.visit('/PatientManagement');
    }


    getPatientEntryPageTitle() {
        return cy.get('section > h3');
    }

}

export default PatientEntryPage;
