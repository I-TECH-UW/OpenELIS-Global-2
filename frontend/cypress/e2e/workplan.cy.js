import LoginPage from "../pages/LoginPage";

let homePage = null;
let loginPage = null;
let workplan = null

before('login', () => {
    loginPage = new LoginPage();
    loginPage.visit();
});


describe('Work plan by Test', function () {
    it('User Can select work plan by test from main menu drop-down. Work-plan test page appears.  ', function () {
        homePage = loginPage.goToHomePage();
        workplan = homePage.goToWorkPlanPlanByTest();
        workplan.getWorkPlanFilterTitle().should('contain.text', 'Workplan By Test');
        workplan.getTestTypeOrPanelSelector().should('be.visible');
    });

    it('User can select test from drop-down selector option', () => {
        cy.fixture('workplan').then((options) => {
            workplan.getTestTypeOrPanelSelector().select(options.testName);
            workplan.getPrintWorkPlanButton().should('be.visible');
        });
    });
    it('All known orders are present', () => {
        cy.fixture('workplan').then((options) => {
            workplan.getWorkPlanResultsTable().find("tr")
                .then((row) => {
                    expect(row.text()).contains(options.accessionNo)
                });
        });
    });
});

describe('Work plan by Panel', function () {
    it('User can select work plan by test from main menu drop-down. Workplan test page appears.   ', function () {
        homePage = loginPage.goToHomePage();
        cy.wait(500);
        workplan = homePage.goToWorkPlanPlanByPanel();
        workplan.getWorkPlanFilterTitle().should('contain.text', 'Workplan By Panel');
        workplan.getTestTypeOrPanelSelector().should('be.visible');
    });

    it('User should select panel type from drop-down list.', function () {
        cy.fixture('workplan').then((options) => {
            workplan.getTestTypeOrPanelSelector().select(options.panelType);
            workplan.getPrintWorkPlanButton().should('be.visible');
        });
    });

    it('All known orders are present', () => {
        cy.fixture('workplan').then((options) => {
            workplan.getWorkPlanResultsTable().find("tr")
                .then((row) => {
                    expect(row.text()).contains(options.accessionNo)
                });
        });
    });
});

