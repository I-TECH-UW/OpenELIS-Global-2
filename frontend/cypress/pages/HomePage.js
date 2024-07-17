import LoginPage from "./LoginPage";
import PatientEntryPage from "./PatientEntryPage";
import OrderEntityPage from "./OrderEntityPage";
import ModifyOrderPage from "./ModifyOrderPage";
import WorkPlan from "./WorkPlan";
import NonConform from "./NonConformPage";

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
    cy.get("#menu_sample").click();
    cy.get("#menu_sample_add").click();
    return new OrderEntityPage();
  }

  openNavigationMenu() {
    cy.get("header#mainHeader > button[title='Open menu']", {
      timeout: 30000,
    }).click();
  }

  goToPatientEntry() {
    this.openNavigationMenu();
    cy.get("#menu_patient").click();
    cy.get("#menu_patient_add_or_edit").click();
    return new PatientEntryPage();
  }

  goToModifyOrderPage() {
    this.openNavigationMenu();
    cy.get("#menu_sample").click();
    cy.get("#menu_sample_edit").click();
    return new ModifyOrderPage();
  }
  goToWorkPlanPlanByTest() {
    this.openNavigationMenu();
    cy.get("#menu_workplan").click();
    cy.get("#menu_workplan_test").click();
    return new WorkPlan();
  }

  goToWorkPlanPlanByPanel() {
    this.openNavigationMenu();
    cy.get("#menu_workplan").click();
    cy.get("#menu_workplan_panel").click();
    return new WorkPlan();
  }

  goToWorkPlanPlanByUnit() {
    this.openNavigationMenu();
    cy.get("#menu_workplan").click();
    cy.get("#menu_workplan_bench").click();
    return new WorkPlan();
  }

  goToWorkPlanPlanByPriority() {
    this.openNavigationMenu();
    cy.get("#menu_workplan").click();
    cy.get("#menu_workplan_priority").click();
    return new WorkPlan();
  }

  goToReportNCE() {
    this.openNavigationMenu();
    cy.get("#menu_nonconformity").click();
    cy.get("#menu_non_conforming_report").click();
    return new NonConform();
  }

  goToViewNCE() {
    this.openNavigationMenu();
    cy.get("#menu_nonconformity").click();
    cy.get("#menu_non_conforming_view").click();
    return new NonConform();
  }
  goToCorrectiveActions() {
    this.openNavigationMenu();
    cy.get("#menu_nonconformity").click();
    cy.get("#menu_non_conforming_corrective_actions").click();
    return new NonConform();
  }
}

export default HomePage;
