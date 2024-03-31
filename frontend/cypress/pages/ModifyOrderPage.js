class ModifyOrderPage {

    constructor() {
    }

    visit() {
        cy.visit('/FindOrder');
    }

    enterAccessionNo(accessionNo) {
        cy.enterText('.cds--text-input.cds--text-input--md.cds--text__input', accessionNo);
    }

    clickSubmitButton() {
        return cy.getElement('.pageContent > div:nth-of-type(2) > .cds--form .cds--btn.cds--btn--primary').click();
    }

    checkProgramButton(){
        cy.get("#myButton").should("be.disabled");
    }

    assignValues(){
        cy.get("#myButton").click();

    }
}

export default ModifyOrderPage;