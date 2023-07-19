import HomePage from "../pages/HomePage";
import LoginPage from "../pages/LoginPage";

const login = new LoginPage();

describe('Failing or Succeeding to Login', function () {

    before(() => {
        login.visit();
        // login.acceptSelfAssignedCert();
    });

    it('User should enter username', function () {
        login.enterUsername("admin");
        login.getUsernameElement().should('contain.value', 'admin')
    });

    it('User should enter password', function () {
        login.enterPassword("adminADMIN!");
        login.getPasswordElement().should('contain.value', 'adminADMIN!');
    });


});
