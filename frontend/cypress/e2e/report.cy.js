import LoginPage from "../pages/LoginPage";
import ReportPage from "../pages/ReportPage";

let homePage = null;
let loginPage = null;
let reportPage =null;

before("login", () => {
  loginPage = new LoginPage();
  loginPage.visit();
});

describe("Routine Reports", function () {
  it("User Visits Routine Reports", function () {
    homePage = loginPage.goToHomePage();
    // reportPage.visitRoutineReports();
    reportPage = homePage.goToRoutineReports();

    // Additional checks can go here
  });

  it("User Visits Patient Status Report and check for Respective Forms", () => {
    // reportPage.visitRoutineReports();
    reportPage.navigateToReport(1, 1);
    cy.get('section > h3').should('have.text', 'Patient Status Report');
    reportPage.checkAccordionVisibility(2, ['#patientId', '#local_search']);
    reportPage.checkAccordionVisibility(3, ['#from', '#to']);
    reportPage.checkAccordionVisibility(6, ['#downshift-1-toggle-button', '.cds--date-picker-input__wrapper > #startDate']);
    reportPage.checkPrintButton(':nth-child(7) > :nth-child(2) > .cds--btn');
  });

  it("Should Visit Statistics Reports", () => {
    reportPage.visitRoutineReports();
    reportPage.navigateToReport(2, 1);
    cy.get('section > h3').should('have.text', 'Statistics Report');
    reportPage.checkCheckboxGroup(':nth-child(1) > .cds--sm\\:col-span-4 > :nth-child(2)', 2, 11);
    cy.get(':nth-child(1) > .cds--sm\\:col-span-4 > :nth-child(2) > :nth-child(1) input[type="checkbox"]').should('be.checked');
  });

  it('Should uncheck the "All" checkbox if any individual checkbox is unchecked', () => {
    reportPage.visitRoutineReports();
    reportPage.navigateToReport(2, 1);
    reportPage.uncheckIndividualCheckbox(':nth-child(1) > .cds--sm\\:col-span-4 > :nth-child(2)', 2);
    cy.get(':nth-child(1) > .cds--sm\\:col-span-4 > :nth-child(2) > :nth-child(1) input[type="checkbox"]').should('not.be.checked');
  });

  it('Should check the "All" checkbox when all individual checkboxes in .inlineDiv are checked', () => {
    reportPage.checkAllCheckboxes('.inlineDiv', 2, 6);
  });

  it("should check for print button", () => {
    reportPage.checkPrintButton(':nth-child(6) > .cds--btn');
  });

  it("Visits Summary of all tests", () => {
    reportPage.visitSummaryTest(2, 'Test Report Summary');
    reportPage.setDateField('.cds--date-picker-input__wrapper > #startDate', '01/02/2023');
    reportPage.checkPrintButton('.cds--form > :nth-child(3) > .cds--btn');
  });

  it("Visits HIV Test Summary and validates", () => {
    reportPage.visitSummaryTest(3, 'HIV Test Summary');
    reportPage.setDateField('.cds--date-picker-input__wrapper > #startDate', '01/02/2023');
    reportPage.checkPrintButton('.cds--form > :nth-child(3) > .cds--btn');
  });

  it("Visits Rejection Report and validates", () => {
    reportPage.visitSummaryTest(3, 'Rejection Report');
    reportPage.setDateField('.cds--date-picker-input__wrapper > #startDate', '01/02/2023');
    reportPage.checkPrintButton('.cds--form > :nth-child(3) > .cds--btn');
  });

  it("Visits Activity Report By Test Type", () => {
    reportPage.visitSummaryTest(4, 'Activity report By test');
    reportPage.setDateField('.cds--date-picker-input__wrapper > #startDate', '01/02/2023');
    reportPage.selectDropdown('#type', 'Amylase(Serum)');
    reportPage.checkPrintButton('.cds--form > :nth-child(3) > .cds--btn');
  });

  it("Visits Activity Report By Panel Type", () => {
    reportPage.visitSummaryTest(4, 'Activity report By Panel');
    reportPage.setDateField('.cds--date-picker-input__wrapper > #startDate', '01/02/2023');
    reportPage.selectDropdown('#type', 'NFS');
    reportPage.checkPrintButton('.cds--form > :nth-child(3) > .cds--btn');
  });

  it("Visits Activity Report By Unit", () => {
    reportPage.visitSummaryTest(4, 'Activity report By Test Section');
    reportPage.setDateField('.cds--date-picker-input__wrapper > #startDate', '01/02/2023');
    reportPage.selectDropdown('#type', 'Biochemistry');
    reportPage.checkPrintButton('.cds--form > :nth-child(3) > .cds--btn');
  });

  it("Visits Referred Out Test Report", () => {
    reportPage.visitSummaryTest(5, 'External Referrals Report');
    reportPage.setDateField('.cds--date-picker-input__wrapper > #startDate', '01/02/2023');
    reportPage.setDateField('.cds--date-picker-input__wrapper > #endDate', '02/02/2023');
    reportPage.selectDropdown('#locationcode', 'CEDRES');
    reportPage.checkPrintButton('.cds--form > :nth-child(3) > .cds--btn');
  });

  it("Visits Non Conformity Report By Date", () => {
    reportPage.visitSummaryTest(5, 'Non Conformity Report by Date');
    reportPage.setDateField('.cds--date-picker-input__wrapper > #startDate', '01/02/2023');
    reportPage.checkPrintButton('.cds--form > :nth-child(3) > .cds--btn');
  });

  it("Visits Non Conformity Report By Unit and Reason", () => {
    reportPage.visitSummaryTest(5, 'Non Conformity Report by Unit and Reason');
    reportPage.setDateField('.cds--date-picker-input__wrapper > #startDate', '01/02/2023');
    reportPage.checkPrintButton('.cds--form > :nth-child(3) > .cds--btn');
  });

  it("Visits Patient Reports", () => {
    reportPage.visitSummaryTest(7, 'Patient Report');
    reportPage.setDateField('.cds--date-picker-input__wrapper > #startDate', '01/02/2023');
    reportPage.checkPrintButton('.cds--form > :nth-child(3) > .cds--btn');
  });
});
