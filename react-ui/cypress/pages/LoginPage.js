class LoginPage {
    constructor() {
    }

    visit() {
        cy.visit('/login');
    }

    getUsernameElement() {
        return cy.get(`.inputText .cds--text-input--md`);
    }

    getPasswordElement() {
        return cy.get(`input#password`);
    }

    enterUsername(value) {
        const field = this.getUsernameElement();
        field.clear();
        field.type(value);
        return this;
    }

    enterPassword(value) {
        const field = this.getPasswordElement();
        field.clear();
        field.type(value);
        return this;
    }

    checkInvalidPassword() {

    }

    signIn() {
        const button = cy.get(`[type='submit']`);
        button.click();
    }

    acceptSelfAssignedCert() {
        const detailsOption = cy.get(`#details-button`);
        detailsOption.click();
        const link = cy.get(`#proceed-link`);
        link.click();

    }
}

export default LoginPage;
