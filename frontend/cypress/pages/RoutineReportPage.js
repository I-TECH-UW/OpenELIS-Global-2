class RoutineReportPage {

  navigateToSection(sectionNumber, subsectionNumber) {
    cy.get(`.cds--white > :nth-child(1) > .cds--side-nav__navigation > .cds--side-nav__items > :nth-child(${sectionNumber}) > .cds--side-nav__item > .cds--side-nav__submenu`).click();
    cy.get(`:nth-child(${sectionNumber}) > .cds--side-nav__item > .cds--side-nav__menu > :nth-child(${subsectionNumber}) > .cds--side-nav__menu-item > .cds--side-nav__link`).click();
  }

  validatePageHeader(expectedText) {
    cy.get('section > h3, h1').should('have.text', expectedText);
  }

  validateFieldVisibility(selector) {
    cy.get(selector).should('be.visible');
  }

  validateButtonDisabled(selector) {
    cy.get(selector).should('be.disabled');
  }

  validateButtonVisible(selector) {
    cy.get(selector).should('be.visible');
  }

  visitRoutineReports() {
    cy.get(':nth-child(2) > .cds--link').click();
  }

  toggleAccordion(accordionNumber) {
    cy.get(`:nth-child(${accordionNumber})> .cds--accordion__item > .cds--accordion__heading`).click();
  }

  toggleAccordionPatient(accordionNumber) {
    cy.get(`:nth-child(${accordionNumber}) >.cds--accordion > .cds--accordion__item > .cds--accordion__heading`).click();
  }

  toggleCheckbox(checkboxNumber, containerSelector) {
    cy.get(`${containerSelector} > :nth-child(${checkboxNumber}) input[type="checkbox"]`).click({force: true});
  }

  checkAllCheckboxes(start, end, containerSelector) {
    for (let i = start; i <= end; i++) {
      this.toggleCheckbox(i, containerSelector);

    }

  }

  validateAllCheckBox(check){
    cy.get(':nth-child(1) > .cds--sm\\:col-span-4 > :nth-child(2) > :nth-child(1) > .cds--checkbox-label').should(check);
  }

  uncheckCheckbox(checkboxNumber, containerSelector) {
    this.toggleCheckbox(checkboxNumber, containerSelector);
  }

  selectDropdown(selector, value) {
    cy.get(selector).select(value, { force: true });
  }

  typeInDatePicker(selector, date) {
    cy.get(selector).type(date);
  }

}


export default RoutineReportPage;