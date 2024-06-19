import LoginPage from "./LoginPage";
import PatientEntryPage from "./PatientEntryPage";
import OrderEntityPage from "./OrderEntityPage";
import ModifyOrderPage from "./ModifyOrderPage";
import WorkPlan from "./WorkPlan";

class HomePage {
  constructor() {}

  visit() {
    cy.visit("/");
  }

  goToSign() {
    return new LoginPage();
  }

  goToOrderPage() {
    this.openNavigationMenu();
    cy.getElement(
      ":nth-child(1) > .cds--side-nav__item > .cds--side-nav__submenu",
    ).click();
    cy.getElement(
      ':nth-child(1) > .cds--side-nav__item > .cds--side-nav__menu > :nth-child(1) > .cds--side-nav__link > .cds--side-nav__link-text > [style="display: flex; width: 100%;"] > .custom-sidenav-button',
    ).click();
    return new OrderEntityPage();
  }

  openNavigationMenu() {
    cy.get("header#mainHeader > button[title='Open menu']", {
      timeout: 30000,
    }).click();
  }

  goToPatientEntry() {
    this.openNavigationMenu();
    cy.get(
      ":nth-child(2) > .cds--side-nav__item > .cds--side-nav__submenu",
    ).click();
    cy.get(
      ':nth-child(2) > .cds--side-nav__item > .cds--side-nav__menu > :nth-child(1) > .cds--side-nav__link > .cds--side-nav__link-text > [style="display: flex; width: 100%;"] > .custom-sidenav-button',
    ).click();
    return new PatientEntryPage();
  }

  goToModifyOrderPage() {
    this.openNavigationMenu();
    cy.getElement(
      ":nth-child(1) > .cds--side-nav__item > .cds--side-nav__submenu",
    ).click();
    cy.get(
      ':nth-child(1) > .cds--side-nav__item > .cds--side-nav__menu > :nth-child(7) > .cds--side-nav__link > .cds--side-nav__link-text > [style="display: flex; width: 100%;"] > .custom-sidenav-button',
    ).click();
    return new ModifyOrderPage();
  }
  goToWorkPlanPlanByTest() {
    this.openNavigationMenu();
    cy.get(
      ":nth-child(4) > .cds--side-nav__item > .cds--side-nav__submenu",
    ).click();
    cy.get(
      ':nth-child(4) > .cds--side-nav__item > .cds--side-nav__menu > :nth-child(1) > .cds--side-nav__link > .cds--side-nav__link-text > [style="display: flex; width: 100%;"] > .custom-sidenav-button',
    ).click();
    return new WorkPlan();
  }

  goToWorkPlanPlanByPanel() {
    this.openNavigationMenu();
    cy.get(
      ":nth-child(4) > .cds--side-nav__item > .cds--side-nav__submenu",
    ).click();
    cy.get(
      ':nth-child(4) > .cds--side-nav__item > .cds--side-nav__menu > :nth-child(2) > .cds--side-nav__link > .cds--side-nav__link-text > [style="display: flex; width: 100%;"] > .custom-sidenav-button',
    ).click();
    return new WorkPlan();
  }

  goToWorkPlanPlanByUnit() {
    this.openNavigationMenu();
    cy.get(
      ":nth-child(4) > .cds--side-nav__item > .cds--side-nav__submenu",
    ).click();
    cy.get(
      ':nth-child(4) > .cds--side-nav__item > .cds--side-nav__menu > :nth-child(3) > .cds--side-nav__link > .cds--side-nav__link-text > [style="display: flex; width: 100%;"] > .custom-sidenav-button',
    ).click();
    return new WorkPlan();
  }

  goToWorkPlanPlanByPriority() {
    this.openNavigationMenu();
    cy.get(
      ":nth-child(4) > .cds--side-nav__item > .cds--side-nav__submenu",
    ).click();
    cy.get(
      ':nth-child(4) > .cds--side-nav__item > .cds--side-nav__menu > :nth-child(4) > .cds--side-nav__link > .cds--side-nav__link-text > [style="display: flex; width: 100%;"] > .custom-sidenav-button',
    ).click();
    return new WorkPlan();
  }
}

export default HomePage;
