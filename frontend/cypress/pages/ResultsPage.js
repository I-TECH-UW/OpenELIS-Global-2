class ResultsPage {

    constructor() {
    }

    visit() {
        cy.visit('/result?type=unit&doRange=false');
    }


}