import LoginPage from "../pages/LoginPage";

let homePage = null;
let loginPage = null;
let patientPatient = null;

before('login', () => {
    loginPage = new LoginPage();
    loginPage.visit();
});
describe('Patient Search', function () {

    it('User Visits Home Page and goes to Add Add|Modify Patient Page', () => {
        homePage = loginPage.goToHomePage();
        patientPatient = homePage.goToPatientEntry();
    });

    it('Add|Modify Patient page should appear with search field', function () {
        patientPatient.getPatientEntryPageTitle().should('contain.text', 'Add/Modify Patient');

    });


});
