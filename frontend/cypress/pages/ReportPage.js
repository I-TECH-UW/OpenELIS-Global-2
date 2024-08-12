class ReportPage {
    visitRoutineReports() {
      cy.get(':nth-child(2) > .cds--link').click(); // visits routine reports
    }
  
    visitPatientStatusReport() {
      cy.get('.cds--white > :nth-child(1) > .cds--side-nav__navigation > .cds--side-nav__items > :nth-child(1) > .cds--side-nav__item > .cds--side-nav__submenu').click();
      cy.get(':nth-child(1) > .cds--side-nav__item > .cds--side-nav__menu > div > .cds--side-nav__menu-item > .cds--side-nav__link').click();
    }
  
    validatePatientStatusReport() {
      cy.get('section > h3').should('have.text', 'Patient Status Report');
    }
  
    validateReportByPatient() {
      cy.get(':nth-child(2) > .cds--accordion > .cds--accordion__item > .cds--accordion__heading').click();
      cy.get('#patientId').should('be.visible');
      cy.get('#local_search').should('be.visible');
      cy.get(':nth-child(2) > .cds--accordion > .cds--accordion__item > .cds--accordion__heading').click();
    }
  
    validateReportByLabNo() {
      cy.get(':nth-child(3) > .cds--accordion__item > .cds--accordion__heading').click();
      cy.get('#from').should('be.visible');
      cy.get('#to').should('be.visible');
      cy.get(':nth-child(3) > .cds--accordion__item > .cds--accordion__heading').click();
    }
  
    validateReportBySite() {
      cy.get(':nth-child(6) > .cds--accordion__item > .cds--accordion__heading').click();
      cy.get(':nth-child(6) > .cds--accordion__item > .cds--accordion__heading').should('be.visible');
      cy.get('#downshift-1-toggle-button').should('be.visible');
      cy.get('.cds--date-picker-input__wrapper > #startDate').should('be.visible');
    }
  
    validatePrintButton() {
      cy.get(':nth-child(7) > :nth-child(2) > .cds--btn').should('be.visible');
    }
  
    visitStatisticsReport() {
      cy.get('.cds--white > :nth-child(1) > .cds--side-nav__navigation > .cds--side-nav__items > :nth-child(2) > .cds--side-nav__item > .cds--side-nav__submenu').click();
      cy.get(':nth-child(2) > .cds--side-nav__item > .cds--side-nav__menu > :nth-child(1) > .cds--side-nav__menu-item > .cds--side-nav__link').click();
    }
  
    validateStatisticsReport() {
      cy.get('section > h3').should('have.text', 'Statistics Report');
    }
  
    checkAllStatisticsCheckboxes() {
      for (let i = 2; i <= 11; i++) {
        cy.get(`:nth-child(1) > .cds--sm\\:col-span-4 > :nth-child(2) > :nth-child(${i}) input[type="checkbox"]`)
          .check({ force: true })
          .should('be.checked');
      }
      cy.get(':nth-child(1) > .cds--sm\\:col-span-4 > :nth-child(2) > :nth-child(1) input[type="checkbox"]')
        .should('be.checked');
    }
  
    uncheckAllCheckboxes() {
      for (let i = 2; i <= 11; i++) {
        cy.get(`:nth-child(1) > .cds--sm\\:col-span-4 > :nth-child(2) > :nth-child(${i})`).click();
      }
      cy.get(':nth-child(1) > .cds--sm\\:col-span-4 > :nth-child(2) > :nth-child(1) input[type="checkbox"]')
        .should('not.be.checked');
    }
  
    checkPriorityAllCheckboxes() {
      for (let i = 2; i <= 6; i++) {
        cy.get(`.inlineDiv > :nth-child(${i}) > .cds--checkbox-label`).click();
      }
      cy.get('.inlineDiv > :nth-child(1) input[type="checkbox"]').should('be.checked');
    }
  
    uncheckPriorityCheckboxes() {
      for (let i = 2; i <= 6; i++) {
        cy.get(`.inlineDiv > :nth-child(${i}) > .cds--checkbox-label`).click();
      }
      cy.get('.inlineDiv > :nth-child(1) input[type="checkbox"]').should('not.be.checked');
    }
  
    visitSummaryOfAllTests() {
      cy.get('.cds--white > :nth-child(1) > .cds--side-nav__navigation > .cds--side-nav__items > :nth-child(2) > .cds--side-nav__item > .cds--side-nav__submenu').click();
      cy.get(':nth-child(2) > .cds--side-nav__item > .cds--side-nav__menu > :nth-child(2) > .cds--side-nav__menu-item > .cds--side-nav__link').click();
    }
  
    validateSummaryOfAllTests() {
      cy.get('h1').should('have.text', 'Test Report Summary');
      cy.get('.cds--form > :nth-child(3) > .cds--btn').should('be.disabled');
      cy.get('.cds--date-picker-input__wrapper > #startDate').type('01/02/2023');
      cy.get('.cds--form > :nth-child(3) > .cds--btn').should('be.visible');
    }
  
    visitHIVTestSummary() {
      cy.get(':nth-child(2) > .cds--side-nav__menu > :nth-child(3) > .cds--side-nav__menu-item > .cds--side-nav__link').click();
    }
  
    visitRejectionReport() {
      cy.get(':nth-child(3) > .cds--side-nav__item > .cds--side-nav__submenu').click();
      cy.get(':nth-child(3) > .cds--side-nav__item > .cds--side-nav__menu > div > .cds--side-nav__menu-item > .cds--side-nav__link').click();
    }
  
    visitActivityReportByTestType() {
      cy.get(':nth-child(4) > .cds--side-nav__submenu').click();
      cy.get(':nth-child(4) > .cds--side-nav__menu > :nth-child(1) > .cds--side-nav__link').click();
    }
  
    validateActivityReportByTestType() {
      cy.get('h1').should('have.text', 'Activity report By test');
      cy.get('.cds--form > :nth-child(3) > .cds--btn').should('be.disabled');
      cy.get('.cds--date-picker-input__wrapper > #startDate').type('01/02/2023');
      cy.get('#type').select('Amylase(Serum)', { force: true });
      cy.get('.cds--form > :nth-child(3) > .cds--btn').should('be.visible');
    }
  }
  
  export default ReportPage;
  