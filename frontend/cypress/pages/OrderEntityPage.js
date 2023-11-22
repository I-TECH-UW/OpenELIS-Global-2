import PatientEntryPage from "./PatientEntryPage";

class OrderEntityPage {
    sampleTypeOptionDropDown = "";
    alertDialog = 'div[role=\'status\']';
    collectionDate = 'collectionDate_0';
    collectionTime = 'collectionTime_0';
    collector = 'collector_0';
    programSelector = 'select#additionalQuestionsSelect';
    labNoField = 'input#labNo';

    constructor() {
    }

    visit() {
        cy.visit('/AddOrder');
    }

    getPatientPage() {
        return new PatientEntryPage();
    }

    alertDisplayed() {
        return cy.elementExists('div[role=\'status\']');
    }

    clickNextButton() {
        cy.getElement('.cds--btn.cds--btn--primary.forwardButton').click();
    }

    getProgramSelectOption() {
        return cy.getElement(this.programSelector);
    }

    getSampleTypeSelector() {
        return cy.getElement('select#sampleId_0');
    }

    getCollectionDate() {
        return cy.getElement(this.collectionDate);
    }

    getCollectionTime() {
        return cy.getElement(this.collectionTime);
    }

    getCollector() {
        return cy.getElement(this.collector);
    }

    enterCollectionDateTimeAndCollector(date, time, collector) {
        // cy.enterText(this.collectionDate, date);
        // cy.enterText(this.collectionTime, time);
        // cy.enterText(this.collector, collector);
    }


    checkPanelCheckBoxField() {
        cy.get('.testPanels .cds--checkbox-wrapper:nth-child(4) .cds--checkbox').check({force: true});
    }

    getLabNoField() {
        return cy.getElement(this.labNoField);
    }

    enterAccessionNo(labNo) {
        return cy.enterText(this.labNoField, labNo);
    }

    generateLabOrderNumber() {
        cy.getElement('.cds--link').click();
    }

    selectionPrioritySelectionOption() {
        return cy.getElement('select#priorityId');
    }

    getReceivedTime() {
        return cy.get('input#order_receivedTime');
    }

    enterRequestReceivedNextVisitDatesAndReceptionTime(requestDate, receivedDate, nextVisitDate, receptionTime) {
        cy.enterText('input#order_requestDate', requestDate);
        this.getReceivedTime().click({force: true});
        cy.enterText('input#order_receivedDate', receivedDate);
        this.getReceivedTime().click({force: true});
        cy.enterText('input#order_nextVisitDate', nextVisitDate);
        this.getReceivedTime().click({force: true});
        cy.enterText('input#order_receivedTime', receptionTime);
    }

    enterSiteName(siteName) {
        cy.enterText('input#siteName', siteName);
        cy.wait(200);
        cy.elementExists('.suggestions > .suggestion-active').click();
    }

    enterRequesterInfo(requesterFirstName, requesterLastName, email, phoneNo) {
        cy.enterText('input#requesterFirstName', requesterFirstName);
        cy.enterText('input#requesterLastName', requesterLastName);
        cy.enterText('input#providerWorkPhoneId', phoneNo);
        cy.enterText('input#providerEmailId', email);
    }

    clickSubmitOrderButton() {
        cy.getElement('.navigationButtonsLayout [type=\'button\']:nth-of-type(2)').click();
    }
}

export default OrderEntityPage;