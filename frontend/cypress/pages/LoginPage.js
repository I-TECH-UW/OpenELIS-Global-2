import HomePage from "./HomePage";
import TestProperties from "../common/TestProperties";

class LoginPage {
    testProperties = null;

    constructor() {
        this.testProperties = new TestProperties();
    }

    visit() {
        cy.visit('/login');
    }

    getUsernameElement() {
        return cy.getElement(`.inputText .cds--text-input--md`);
    }

    getPasswordElement() {
        return cy.getElement(`input#password`);
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

    signIn() {
        const button = cy.getElement(`[type='submit']`);
        button.click();
    }

    acceptSelfAssignedCert() {
        const detailsOption = cy.get(`#details-button`);
        detailsOption.click();
        const link = cy.get(`#proceed-link`);
        link.click();

    }

    goToHomePage() {
        cy.wait(1000);
        cy.url().then(url => {
            if (url.includes('/login')) {
                this.enterUsername(this.testProperties.getUsername())
                this.enterPassword(this.testProperties.getPassword())
                this.signIn();
            }
        });
        return new HomePage();
    }
}

export default LoginPage;
