import LoginPage from "../pages/LoginPage";
import TestProperties from "../common/TestProperties";

let homePage = null;
let loginPage = null;
let modifyOrderPage = null;
let patientPage = null;

before('login', () => {
    loginPage = new LoginPage();
    loginPage.visit();
});

describe('Modify Order', function () {

    it('User Visits Home Page and goes to Modify Order Page ', function () {
        homePage = loginPage.goToHomePage();
        modifyOrderPage = homePage.goToModifyOrderPage();
    });

    it('User searches with an non existing order number', () => {
        cy.fixture('Order').then((order) => {
            modifyOrderPage.enterAccessionNo(order.labNo);
        });
        modifyOrderPage.clickSearchOrderButton();
    });

});