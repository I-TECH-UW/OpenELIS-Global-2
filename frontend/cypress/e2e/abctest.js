import LoginPage from "../pages/LoginPage";

let homePage = null;
let loginPage = null;
let report = null;

before("login", () => {
  loginPage = new LoginPage();
  loginPage.visit();
});

describe("Routine Reports", function () {
  it("User Visits Routine Reports", function () {
    homePage = loginPage.goToHomePage();
    report = homePage.goToRoutineReports();
    // workplan
    //   .getWorkPlanFilterTitle()
    //   .should("contain.text", "Workplan By Test");
    // workplan.getTestTypeOrPanelSelector().should("be.visible");
  });

  it("User Visits Patint Status Report and check for Respective Forms", () => {
    cy.get('.cds--white > :nth-child(1) > .cds--side-nav__navigation > .cds--side-nav__items > :nth-child(1) > .cds--side-nav__item > .cds--side-nav__submenu').click();
    cy.get(':nth-child(1) > .cds--side-nav__item > .cds--side-nav__menu > div > .cds--side-nav__menu-item > .cds--side-nav__link').click();


    cy.get('section > h3').should('have.text', 'Patient Status Report');
    //clicks on report by patient
    cy.get(':nth-child(2) > .cds--accordion > .cds--accordion__item > .cds--accordion__heading').click();
    cy.get('#patientId').should('be.visible');
    cy.get('#local_search').should('be.visible');
    cy.get(':nth-child(2) > .cds--accordion > .cds--accordion__item > .cds--accordion__heading').click();


    //clicks  on Report By Lab No and validates field forms
    cy.get(':nth-child(3) > .cds--accordion__item > .cds--accordion__heading').click();
    cy.get('#from').should('be.visible');
    cy.get('#to').should('be.visible');
    cy.get(':nth-child(3) > .cds--accordion__item > .cds--accordion__heading').click();


    //clicks on generate report by site and checks for respective fields
    cy.get(':nth-child(6) > .cds--accordion__item > .cds--accordion__heading').click();
    cy.get(':nth-child(6) > .cds--accordion__item > .cds--accordion__heading').should('be.visible');
    cy.get('#downshift-1-toggle-button').should('be.visible');
    cy.get('.cds--date-picker-input__wrapper > #startDate').should('be.visible');

    //checks for print button
    cy.get(':nth-child(7) > :nth-child(2) > .cds--btn').should('be.visible');
  });


  it("Should Visit Statistics Reports ", () => {

    cy.get(':nth-child(2) > .cds--link').click(); //visits routine reports

    cy.get('.cds--white > :nth-child(1) > .cds--side-nav__navigation > .cds--side-nav__items > :nth-child(2) > .cds--side-nav__item > .cds--side-nav__submenu').click();

    cy.get(':nth-child(2) > .cds--side-nav__item > .cds--side-nav__menu > :nth-child(1) > .cds--side-nav__menu-item > .cds--side-nav__link').click();

    cy.get('section > h3').should('have.text', 'Statistics Report');
    for (let i = 2; i <= 11; i++) {
      cy.get(`:nth-child(1) > .cds--sm\\:col-span-4 > :nth-child(2) > :nth-child(${i}) input[type="checkbox"]`)
        .check({ force: true }) // Force the check action
        .should('be.checked');
    }

    
    

    // Verify that the "All" checkbox is now checked
    // cy.get(':nth-child(1) > .cds--sm\\:col-span-4 > :nth-child(2) > :nth-child(1)').should('be.checked');
    // Verify that the "All" checkbox is now checked
cy.get(':nth-child(1) > .cds--sm\\:col-span-4 > :nth-child(2) > :nth-child(1) input[type="checkbox"]')
.should('be.checked');

  });

  it('Should uncheck the "All" checkbox if any individual checkbox is unchecked', () => {
    // First, check all individual checkboxes
    for (let i = 2; i <= 11; i++) {
      cy.get(`:nth-child(1) > .cds--sm\\:col-span-4 > :nth-child(2) > :nth-child(${i})`).click();
    }

    // Ensure the "All" checkbox is checked
    cy.get(':nth-child(1) > .cds--sm\\:col-span-4 > :nth-child(2) > :nth-child(1) input[type="checkbox"]')
.should('be.checked');
    // cy.get(':nth-child(1) > .cds--sm\\:col-span-4 > :nth-child(2) > :nth-child(1)').check({ force: true }).should('be.checked');

    // Now, uncheck one individual checkbox (e.g., the first one)
    cy.get(':nth-child(1) > .cds--sm\\:col-span-4 > :nth-child(2) > :nth-child(2)').click().should('not.be.checked');

    // Verify that the "All" checkbox is now unchecked
    cy.get(':nth-child(1) > .cds--sm\\:col-span-4 > :nth-child(2) > :nth-child(1)').should('not.be.checked');


  });


  //for checking checkboxes of priority wala
  // ------------------------------------------------------------------------------------------------------------------------------------

  it('Should check the "All" checkbox when all individual checkboxes in .inlineDiv are checked', () => {
    // Click on each individual checkbox to check them (from 2 to 6)
    for (let i = 2; i <= 6; i++) {
      cy.get(`.inlineDiv > :nth-child(${i}) > .cds--checkbox-label`).click();
    }

    // Verify that the "All" checkbox is now checked
    cy.get('.inlineDiv > :nth-child(1) input[type="checkbox"]')
    .should('be.checked');
 
  });

  it('Should uncheck the "All" checkbox if any individual checkbox in .inlineDiv is unchecked', () => {
    // First, check all individual checkboxes (from 2 to 6)
    for (let i = 2; i <= 6; i++) {
      cy.get(`.inlineDiv > :nth-child(${i}) > .cds--checkbox-label`).click();
    }

    // Ensure the "All" checkbox is checked
    // cy.get('.inlineDiv > :nth-child(1) > .cds--checkbox-label ').should('be.checked');
    // Verify that the "All" checkbox is checked
cy.get('.inlineDiv > :nth-child(1)  input[type="checkbox"]')
.should('not.be.checked');


    // Now, uncheck one individual checkbox (e.g., the second one)
    cy.get('.inlineDiv > :nth-child(2) > .cds--checkbox-label').click();

    // Verify that the "All" checkbox is now unchecked
    cy.get('.inlineDiv > :nth-child(1)  input[type="checkbox"]').should('not.be.checked');

    //checks all the time frames
    cy.get(':nth-child(3) > .cds--sm\\:col-span-4 > :nth-child(2) > :nth-child(1) > .cds--checkbox-label').click();

  });


  it("should check for print button", () => {
    cy.get(':nth-child(6) > .cds--btn').should('be.visible');
  });


  // Summary of all test
  it("Visits Summary of all tests", () => {

    cy.get(':nth-child(2) > .cds--link').click(); //visits routine reports

    cy.get('.cds--white > :nth-child(1) > .cds--side-nav__navigation > .cds--side-nav__items > :nth-child(2) > .cds--side-nav__item > .cds--side-nav__submenu').click();
    cy.get(':nth-child(2) > .cds--side-nav__item > .cds--side-nav__menu > :nth-child(2) > .cds--side-nav__menu-item > .cds--side-nav__link').click();
    
    cy.get('h1').should('have.text', 'Test Report Summary');
    cy.get('.cds--form > :nth-child(3) > .cds--btn').should('be.disabled');
    cy.get('.cds--date-picker-input__wrapper > #startDate').type('01/02/2023');
    cy.get('.cds--form > :nth-child(3) > .cds--btn').should('be.visible');

  
  
  });
  it("Visits HIV Test Summary and validates", () => {

    cy.get(':nth-child(2) > .cds--link').click(); //visits routine reports

    cy.get('.cds--white > :nth-child(1) > .cds--side-nav__navigation > .cds--side-nav__items > :nth-child(2) > .cds--side-nav__item > .cds--side-nav__submenu').click();
    cy.get(':nth-child(2) > .cds--side-nav__item > .cds--side-nav__menu > :nth-child(3) > .cds--side-nav__menu-item > .cds--side-nav__link').click();
    cy.get('.cds--form > :nth-child(3) > .cds--btn').should('be.disabled');
    cy.get('.cds--date-picker-input__wrapper > #startDate').type('01/02/2023');
    cy.get('.cds--form > :nth-child(3) > .cds--btn').should('be.visible');

  
  
  });
  it("Visits Rejection Report and validates", () => {

    cy.get(':nth-child(2) > .cds--link').click(); //visits routine reports

    cy.get('.cds--white > :nth-child(1) > .cds--side-nav__navigation > .cds--side-nav__items > :nth-child(3) > .cds--side-nav__item > .cds--side-nav__submenu').click();
    cy.get(':nth-child(3) > .cds--side-nav__item > .cds--side-nav__menu > div > .cds--side-nav__menu-item > .cds--side-nav__link').click();
    cy.get('h1').should('have.text', 'Rejection Report');
    cy.get('.cds--form > :nth-child(3) > .cds--btn').should('be.disabled');
    cy.get('.cds--date-picker-input__wrapper > #startDate').type('01/02/2023');
    cy.get('.cds--form > :nth-child(3) > .cds--btn').should('be.visible');

  
  
  });

  it("Visits Activity Report By Test Type", () => {

    cy.get(':nth-child(2) > .cds--link').click(); //visits routine reports

    cy.get('.cds--white > :nth-child(1) > .cds--side-nav__navigation > .cds--side-nav__items > :nth-child(4) > .cds--side-nav__item > .cds--side-nav__submenu').click();
    cy.get(':nth-child(4) > .cds--side-nav__item > .cds--side-nav__menu > :nth-child(1) > .cds--side-nav__menu-item > .cds--side-nav__link').click();
    cy.get('h1').should('have.text', 'Activity report By test');
    cy.get('.cds--form > :nth-child(3) > .cds--btn').should('be.disabled');
    cy.get('.cds--date-picker-input__wrapper > #startDate').type('01/02/2023');
    cy.get('#type').select('Amylase(Serum)', { force: true });
    cy.get('.cds--form > :nth-child(3) > .cds--btn').should('be.visible');

  
  
  });
  it("Visits Activity Report By Panel Type", () => {

    cy.get(':nth-child(2) > .cds--link').click(); //visits routine reports

    cy.get('.cds--white > :nth-child(1) > .cds--side-nav__navigation > .cds--side-nav__items > :nth-child(4) > .cds--side-nav__item > .cds--side-nav__submenu').click();
    cy.get(':nth-child(4) > .cds--side-nav__item > .cds--side-nav__menu > :nth-child(2) > .cds--side-nav__menu-item > .cds--side-nav__link').click();
    cy.get('h1').should('have.text', 'Activity report By Panel');
    cy.get('.cds--form > :nth-child(3) > .cds--btn').should('be.disabled');
    cy.get('.cds--date-picker-input__wrapper > #startDate').type('01/02/2023');
    cy.get('#type').select('NFS', { force: true });
    
    cy.get('.cds--form > :nth-child(3) > .cds--btn').should('be.visible');

  
  
  });
  it("Visits Activity Report By Unit", () => {

    cy.get(':nth-child(2) > .cds--link').click(); //visits routine reports

    cy.get('.cds--white > :nth-child(1) > .cds--side-nav__navigation > .cds--side-nav__items > :nth-child(4) > .cds--side-nav__item > .cds--side-nav__submenu').click();
    cy.get(':nth-child(4) > .cds--side-nav__item > .cds--side-nav__menu > :nth-child(3) > .cds--side-nav__menu-item > .cds--side-nav__link').click();
    cy.get('h1').should('have.text', 'Activity report By Test Section');
    cy.get('.cds--form > :nth-child(3) > .cds--btn').should('be.disabled');
    cy.get('.cds--date-picker-input__wrapper > #startDate').type('01/02/2023');
    cy.get('#type').select('Biochemistry', { force: true });
    
    cy.get('.cds--form > :nth-child(3) > .cds--btn').should('be.visible');

  
  });

  it("Visits Reffered Out Test Report", () => {

    cy.get(':nth-child(2) > .cds--link').click(); //visits routine reports

    cy.get('.cds--white > :nth-child(1) > .cds--side-nav__navigation > .cds--side-nav__items > :nth-child(5) > .cds--side-nav__item > .cds--side-nav__submenu').click();
    cy.get(':nth-child(5) > .cds--side-nav__item > .cds--side-nav__menu > div > .cds--side-nav__menu-item > .cds--side-nav__link').click();
    cy.get('h3').should('have.text', 'External Refferals Report');
    cy.get('.cds--date-picker-input__wrapper > #startDate').type('01/02/2023');
    cy.get('.cds--date-picker-input__wrapper > #endDate').type('02/02/2023');
    cy.get('#locationcode').select('CEDRES', { force: true });
    
    cy.get('.cds--form > :nth-child(3) > .cds--btn').should('be.visible');

  
  });


  it("Visits Non Conformity Report By Date", () => {

    cy.get(':nth-child(2) > .cds--link').click(); //visits routine reports

    cy.get('.cds--white > :nth-child(1) > .cds--side-nav__navigation > .cds--side-nav__items > :nth-child(5) > .cds--side-nav__item > .cds--side-nav__submenu').click();
    cy.get(':nth-child(5) > .cds--side-nav__item > .cds--side-nav__menu > :nth-child(1) > .cds--side-nav__menu-item > .cds--side-nav__link').click();
    cy.get('h1').should('have.text', 'Non ConformityReport by Date');
    cy.get('.cds--form > :nth-child(3) > .cds--btn').should('be.disabled');
    cy.get('.cds--date-picker-input__wrapper > #startDate').type('01/02/2023');
    
    
    cy.get('.cds--form > :nth-child(3) > .cds--btn').should('be.visible');

  
  });

  
  it("Visits Non Conformity Report By Unit and Reason", () => {

    cy.get(':nth-child(2) > .cds--link').click(); //visits routine reports

    cy.get('.cds--white > :nth-child(1) > .cds--side-nav__navigation > .cds--side-nav__items > :nth-child(5) > .cds--side-nav__item > .cds--side-nav__submenu').click();
    cy.get(':nth-child(5) > .cds--side-nav__item > .cds--side-nav__menu > :nth-child(2) > .cds--side-nav__menu-item > .cds--side-nav__link').click();
    cy.get('h1').should('have.text', 'Non Conformity Report by Unit and Reason');
    cy.get('.cds--form > :nth-child(3) > .cds--btn').should('be.disabled');
    cy.get('.cds--date-picker-input__wrapper > #startDate').type('01/02/2023');
    
    
    cy.get('.cds--form > :nth-child(3) > .cds--btn').should('be.visible');

  
  });

   
  it("Visits Export Routine CSV file", () => {

    cy.get(':nth-child(2) > .cds--link').click(); //visits routine reports

    cy.get('.cds--white > :nth-child(1) > .cds--side-nav__navigation > .cds--side-nav__items > :nth-child(9) > .cds--side-nav__item > .cds--side-nav__submenu').click();
    cy.get(':nth-child(9) > .cds--side-nav__item > .cds--side-nav__menu > div > .cds--side-nav__menu-item > .cds--side-nav__link').click();
    cy.get('h1').should('have.text', 'Export Routine CSV file');
    cy.get('.cds--form > :nth-child(3) > .cds--btn').should('be.disabled');
    cy.get('.cds--date-picker-input__wrapper > #startDate').type('01/02/2023');
    
    
    cy.get('.cds--form > :nth-child(3) > .cds--btn').should('be.visible');

  
  });
});