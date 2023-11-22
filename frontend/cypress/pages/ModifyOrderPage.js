class ModifyOrderPage {

    constructor() {
    }

    visit() {
        cy.visit('/FindOrder');
    }

    enterAccessionNo(accessionNo) {
        cy.enterText('.cds--text-input.cds--text-input--md.cds--text__input', accessionNo);
    }

    clickSearchOrderButton() {
        return cy.getElement('.pageContent > div:nth-of-type(2) > .cds--form .cds--btn.cds--btn--primary').click();
    }
}

export default ModifyOrderPage;