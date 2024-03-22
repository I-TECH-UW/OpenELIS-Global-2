import LoginPage from "./LoginPage";
import PatientEntryPage from "./PatientEntryPage";
import OrderEntityPage from "./OrderEntityPage";

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
    cy.get("header#mainHeader > button[title='Open menu']").click();
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
}

export default HomePage;
