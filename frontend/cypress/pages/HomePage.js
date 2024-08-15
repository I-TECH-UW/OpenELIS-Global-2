import LoginPage from "./LoginPage";
import PatientEntryPage from "./PatientEntryPage";
import OrderEntityPage from "./OrderEntityPage";
import ModifyOrderPage from "./ModifyOrderPage";
import WorkPlan from "./WorkPlan";
import NonConform from "./NonConformPage";
import BatchOrderEntry from "./BatchOrderEntryPage";
import RoutineReportPage from "./RoutineReportPage";
import StudyReportPage from "./StudyReportPage";

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
    cy.get("#menu_sample_dropdown").click();
    cy.get("#menu_sample_add_nav").click();
    return new OrderEntityPage();
  }

  openNavigationMenu() {
    cy.get("header#mainHeader > button[title='Open menu']", {
      timeout: 30000,
    }).click();
  }

  goToBatchOrderEntry() {
    this.openNavigationMenu();
    cy.get("#menu_sample_dropdown").click();
    cy.get("#menu_sample_batch_entry_nav").click();
    return new BatchOrderEntry();
  }

  goToPatientEntry() {
    this.openNavigationMenu();
    cy.get("#menu_patient_dropdown").click();
    cy.get("#menu_patient_add_or_edit_nav").click();
    return new PatientEntryPage();
  }

  goToModifyOrderPage() {
    this.openNavigationMenu();
    cy.get("#menu_sample_dropdown").click();
    cy.get("#menu_sample_edit_nav").click();
    return new ModifyOrderPage();
  }
  goToWorkPlanPlanByTest() {
    this.openNavigationMenu();
    cy.get("#menu_workplan_dropdown").click();
    cy.get("#menu_workplan_test_nav").click();
    return new WorkPlan();
  }

  goToWorkPlanPlanByPanel() {
    this.openNavigationMenu();
    cy.get("#menu_workplan_dropdown").click();
    cy.get("#menu_workplan_panel_nav").click();
    return new WorkPlan();
  }

  goToWorkPlanPlanByUnit() {
    this.openNavigationMenu();
    cy.get("#menu_workplan_dropdown").click();
    cy.get("#menu_workplan_bench_nav").click();
    return new WorkPlan();
  }

  goToWorkPlanPlanByPriority() {
    this.openNavigationMenu();
    cy.get("#menu_workplan_dropdown").click();
    cy.get("#menu_workplan_priority_nav").click();
    return new WorkPlan();
  }

  goToReportNCE() {
    this.openNavigationMenu();
    cy.get("#menu_nonconformity_dropdown").click();
    cy.get("#menu_non_conforming_report_nav").click();
    return new NonConform();
  }

  goToViewNCE() {
    this.openNavigationMenu();
    cy.get("#menu_nonconformity_dropdown").click();
    cy.get("#menu_non_conforming_view_nav").click();
    return new NonConform();
  }
  goToCorrectiveActions() {
    this.openNavigationMenu();
    cy.get("#menu_nonconformity_dropdown").click();
    cy.get("#menu_non_conforming_corrective_actions_nav").click();
    return new NonConform();
  }
  goToRoutineReports() {
    this.openNavigationMenu();
    cy.get("#menu_reports").click();
    cy.get("#menu_reports_routine_nav").click();
    return new RoutineReportPage();
  }
  goToStudyReports() {
    this.openNavigationMenu();
    cy.get("#menu_reports").click();
    cy.get("#menu_reports_study_nav").click();
    return new StudyReportPage();
  }
}

export default HomePage;
