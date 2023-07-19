import LoginPage from "./LoginPage";

class HomePage {
    constructor() {
    }

    visit() {
        cy.visit('/');
    }

    goToSign() {
        return new LoginPage();
    }
}

export default HomePage;
