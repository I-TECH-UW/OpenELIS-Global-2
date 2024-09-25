class StudyReportPage {
  visitHomePage() {
    homePage = loginPage.goToHomePage();
  }

  visitStudyReports() {
    report = homePage.goToStudyReports();
  }

  clickSideNavMenuItem(nthChild, submenuChild) {
    cy.get(
      `.cds--side-nav__items > :nth-child(${nthChild}) > .cds--side-nav__item > .cds--side-nav__submenu`,
    ).click();
    cy.get(
      `:nth-child(${nthChild}) > .cds--side-nav__item > .cds--side-nav__menu > :nth-child(${submenuChild}) > .cds--side-nav__menu-item > .cds--side-nav__link`,
    ).click();
  }

  verifyButtonDisabled() {
    cy.get("section > .cds--btn").should("be.disabled");
  }

  typeInField(fieldId, value) {
    cy.get(`#${fieldId}`).type(value, { force: true });
  }

  verifyButtonVisible() {
    cy.get("section > .cds--btn").should("be.visible");
  }

  // verifyHeaderText(expectedText) {
  //   cy.get('.cds--sm\\:col-span-4 > section > h3').should('have.text', expectedText);
  // }

  verifyHeaderText(selector, expectedText) {
    cy.get(selector).should("have.text", expectedText);
  }

  typeInDate(fieldId, value) {
    cy.get(`#${fieldId}`).type(value);
  }

  clickAccordionItem(nthChild) {
    cy.get(
      `:nth-child(${nthChild}) >.cds--accordion__item > .cds--accordion__heading`,
    ).click();
  }

  clickAccordionPatient(nthChild) {
    cy.get(
      `:nth-child(${nthChild}) >.cds--accordion > .cds--accordion__item > .cds--accordion__heading`,
    ).click();
  }

  verifyElementVisible(selector) {
    cy.get(selector).should("be.visible");
  }

  selectDropdown(optionId, value) {
    cy.get(`#${optionId}`).select(value);
  }

  visitStudyReports() {
    cy.get(":nth-child(2) > .cds--link").click({ force: true });
  }

  visitARVInitialVersion1() {
    this.clickSideNavMenuItem(1, 1);
    this.verifyButtonDisabled();
    this.typeInField("from", "DEV0124000000000000");
    this.verifyButtonVisible();
  }

  visitARVInitialVersion2() {
    this.visitStudyReports();
    this.clickSideNavMenuItem(1, 2);
    this.verifyHeaderText(
      ".cds--sm\\:col-span-4 > section > h3",
      "ARV-initial",
    );
    this.verifyButtonDisabled();
    this.typeInField("from", "DEV0124000000000000");
    this.verifyButtonVisible();
  }

  visitARVFollowUpVersion1() {
    this.visitStudyReports();

    this.clickSideNavMenuItem(1, 3);
    this.verifyHeaderText(
      ".cds--sm\\:col-span-4 > section > h3",
      "ARV-Follow-up",
    );
    this.verifyButtonDisabled();
    this.typeInField("from", "DEV0124000000000000");
    this.verifyButtonVisible();
  }

  visitARVFollowUpVersion2() {
    this.visitStudyReports();

    this.clickSideNavMenuItem(1, 4);
    this.verifyHeaderText(
      ".cds--sm\\:col-span-4 > section > h3",
      "ARV-Follow-up",
    );
    this.verifyButtonDisabled();
    this.typeInField("from", "DEV0124000000000000");
    this.verifyButtonVisible();
  }

  visitARVFollowUpVersion3() {
    this.visitStudyReports();

    this.clickSideNavMenuItem(1, 5);
    this.verifyHeaderText(
      ".cds--sm\\:col-span-4 > section > h3",
      "ARV -->Initial-FollowUp-VL",
    );
    this.verifyButtonDisabled();
    this.typeInField("from", "DEV0124000000000000");
    this.verifyButtonVisible();
  }

  visitAuditTrailReport() {
    cy.get(
      ":nth-child(11) > .cds--side-nav__item > .cds--side-nav__submenu",
    ).click();
    cy.get(
      ":nth-child(11) > .cds--side-nav__item > .cds--side-nav__menu > div > .cds--side-nav__menu-item > .cds--side-nav__link",
    ).click();
  }

  validateAudit() {
    cy.get("section > .cds--btn").click();
    cy.get(":nth-child(8) > :nth-child(2)").should(
      "have.text",
      "Optimus Prime",
    );
  }

  visitEIDVersion1() {
    this.visitStudyReports();

    this.clickSideNavMenuItem(2, 1);
    this.verifyHeaderText(
      ":nth-child(1) > .cds--sm\\:col-span-4 > :nth-child(1) > section > h3",
      "Diagnostic for children with DBS-PCR",
    );
    this.clickAccordionPatient(2);
    this.verifyElementVisible("#patientId");
    this.verifyElementVisible("#local_search");
    this.clickAccordionPatient(2);
    this.clickAccordionItem(3);
    this.verifyElementVisible("#from");
    this.verifyElementVisible("#to");
    this.clickAccordionItem(3);
    this.clickAccordionItem(6);
    this.verifyElementVisible("#siteName");
    this.clickAccordionItem(6);
    cy.get(":nth-child(7) > :nth-child(2) > .cds--btn").should("be.visible");
  }

  visitEIDVersion2() {
    this.visitStudyReports();

    this.clickSideNavMenuItem(2, 2);
    this.verifyHeaderText(
      ".cds--sm\\:col-span-4 > section > h3",
      "Diagnostic for children with DBS-PCR",
    );
    this.verifyButtonDisabled();
    this.typeInField("from", "DEV0124000000000000");
    this.verifyButtonVisible();
  }

  visitVLVersion() {
    this.visitStudyReports();

    this.clickSideNavMenuItem(3, 1);
    this.verifyHeaderText(
      ":nth-child(1) > .cds--sm\\:col-span-4 > :nth-child(1) > section > h3",
      "Viral Load",
    );
    this.clickAccordionPatient(2);
    this.verifyElementVisible("#patientId");
    this.verifyElementVisible("#local_search");
    this.clickAccordionPatient(2);
    this.clickAccordionItem(3);
    this.verifyElementVisible("#from");
    this.verifyElementVisible("#to");
    this.clickAccordionItem(3);
    this.clickAccordionItem(6);
    this.verifyElementVisible("#siteName");
    this.clickAccordionItem(6);
    cy.get(":nth-child(7) > :nth-child(2) > .cds--btn").should("be.visible");
  }

  visitIntermediateVersion1() {
    this.visitStudyReports();

    this.clickSideNavMenuItem(4, 1);
    this.verifyHeaderText(
      ".cds--sm\\:col-span-4 > section > h3",
      "Indeterminate",
    );
    this.verifyButtonDisabled();
    this.typeInField("from", "DEV0124000000000000");
    this.verifyButtonVisible();
  }

  visitIntermediateVersion2() {
    this.visitStudyReports();

    this.clickSideNavMenuItem(4, 2);
    this.verifyHeaderText(
      ".cds--sm\\:col-span-4 > section > h3",
      "Indeterminate",
    );
    this.verifyButtonDisabled();
    this.typeInField("from", "DEV0124000000000000");
    this.verifyButtonVisible();
  }

  visitIntermediateByService() {
    this.visitStudyReports();

    this.clickSideNavMenuItem(4, 3);
    this.verifyHeaderText(
      ".cds--lg\\:col-span-16 > section > h3",
      "Indeterminate",
    );
    this.typeInDate("startDate", "01/02/2023");
    this.typeInField("siteName", "CAME", { force: true });
    this.verifyElementVisible("#siteName");
  }

  visitSpecialRequest() {
    this.visitStudyReports();

    this.clickSideNavMenuItem(5, 1);
    this.verifyHeaderText(
      ".cds--sm\\:col-span-4 > section > h3",
      "Special Request",
    );
    this.verifyButtonDisabled();
    this.typeInField("from", "DEV0124000000000000");
    this.verifyButtonVisible();
  }

  visitCollectedARVPatientReport() {
    this.visitStudyReports();

    this.clickSideNavMenuItem(6, 1);
    this.verifyHeaderText(
      ":nth-child(1) > .cds--sm\\:col-span-4 > :nth-child(1) > section > h3",
      "Collected ARV Patient Report",
    );
    this.verifyButtonDisabled();
    this.typeInField("nationalID", "UG-23SLHD7DBD");
    this.verifyButtonVisible();
  }

  visitAssociatedPatientReport() {
    this.visitStudyReports();

    this.clickSideNavMenuItem(7, 1);
    this.verifyHeaderText(
      ":nth-child(1) > .cds--sm\\:col-span-4 > :nth-child(1) > section > h3",
      "Associated Patient Report",
    );
    this.verifyButtonDisabled();
    this.typeInField("nationalID", "UG-23SLHD7DBD");
    this.verifyButtonVisible();
  }

  visitNonConformityReportByDate() {
    this.visitStudyReports();

    this.clickSideNavMenuItem(9, 1);
    this.verifyHeaderText("h1", "Non-conformity Report By Date");
    this.verifyButtonDisabled();
    this.typeInDate("startDate", "01/02/2023");
    this.verifyButtonVisible();
  }

  visitNonConformityReportByUnitAndReason() {
    this.visitStudyReports();

    this.clickSideNavMenuItem(9, 2);
    this.verifyHeaderText("h1", "Non Conformity Report by Unit and Reason");
    this.verifyButtonDisabled();
    this.typeInDate("startDate", "01/02/2023");
    this.verifyButtonVisible();
  }

  visitNonConformityReportByLabNo() {
    this.visitStudyReports();

    this.clickSideNavMenuItem(9, 3);
    this.verifyHeaderText(
      ".cds--sm\\:col-span-4 > section > h3",
      "ARV -->Initial-FollowUp-VL",
    );
    this.verifyButtonDisabled();
    this.typeInField("from", "DEV0124000000000000");
    this.verifyButtonVisible();
  }

  visitNonConformityReportByNotification() {
    this.visitStudyReports();

    this.clickSideNavMenuItem(9, 4);
    this.verifyHeaderText(
      ".cds--sm\\:col-span-4 > section > h3",
      "Non-conformity notification",
    );
    this.verifyButtonVisible();
  }

  visitNonConformityReportFollowUpRequired() {
    this.visitStudyReports();

    this.clickSideNavMenuItem(9, 5);
    this.verifyHeaderText("h1", "Follow-up Required");
    this.verifyButtonDisabled();
    this.typeInDate("startDate", "01/02/2023");
    this.verifyButtonVisible();
  }

  visitGeneralReportInExportByDate() {
    this.visitStudyReports();

    this.clickSideNavMenuItem(10, 1);
    this.verifyHeaderText("h1", "Export a CSV File by Date");
    this.selectDropdown("studyType", "Testing");
    this.selectDropdown("dateType", "Order Date");
  }
}

export default StudyReportPage;
