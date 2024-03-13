import LoginPage from "../pages/LoginPage";

const login = new LoginPage();
beforeEach("user visits login page",()=>{
    cy.clearAllCookies();
    login.visit();
});
afterEach("close browser",()=>{
    cy.clearAllLocalStorage();
})
describe('Failing or Succeeding to Login', function () {
    it('Should validate user authentication', function () {
        cy.fixture('Users').then((users) => {
            users.forEach((user) => {
                login.enterUsername(user.username);
                login.getUsernameElement().should('contain.value', user.username)

                login.enterPassword(user.password);
                login.getPasswordElement().should('contain.value', user.password);
                login.signIn();

                if (user.correctPass === true) {
                    cy.get('header#mainHeader > button[title=\'Open menu\']').should('exist')
                        .and('span:nth-of-type(3) > .cds--btn.cds--btn--icon-only.cds--btn--primary.cds--header__action > svg > path:nth-of-type(1)', 'exist');
                } else {
                    cy.get('div[role=\'status\']').should('be.visible');
                }
            });
            cy.wait(2000);
            cy.url().should('include','/');
            cy.get('[data-test="panelSwitchIcon"').should('be.visible');
            cy.get('[data-test="openMenu"').should('be.visible');
            cy.get('[data-test="logo"').should('be.visible').should('have.id','header-logo');
        });
    });


});
