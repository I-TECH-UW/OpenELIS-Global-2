import LoginPage from "./LoginPage";
import PatientEntryPage from "./PatientEntryPage";
import OrderEntityPage from "./OrderEntityPage";
import ModifyOrderPage from "./ModifyOrderPage";
import WorkPlan from "./WorkPlan";
import NonConform from "./NonConformPage";
import Result from "./ResultsPage";
import Validation from "./Validation";
import BatchOrderEntry from "./BatchOrderEntryPage";


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

  goToBatchOrderEntry() {
    this.openNavigationMenu();
    cy.get("#menu_sample").click();
    cy.get("#menu_sample_batch_entry").click();
    return new BatchOrderEntry();
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

  goToResultsByUnit() {
    this.openNavigationMenu();
    cy.get("#menu_results").click();
    cy.get("#menu_results_logbook").click();
    return new Result();
  }

  goToResultsByOrder() {
    this.openNavigationMenu();
    cy.get("#menu_results").click();
    cy.get("#menu_results_accession").click();
    return new Result();
  }

  goToResultsByPatient() {
    this.openNavigationMenu();
    cy.get("#menu_results").click();
    cy.get("#menu_results_patient").click();
    return new Result();
  }

  goToResultsForRefferedOut() {
    this.openNavigationMenu();
    cy.get("#menu_results").click();
    cy.get("#menu_results_referred ").click();
    return new Result();
  }

  goToResultsByRangeOrder() {
    this.openNavigationMenu();
    cy.get("#menu_results").click();
    cy.get("#menu_results_range").click();
    return new Result();
  }

  goToResultsByTestAndStatus() {
    this.openNavigationMenu();
    cy.get("#menu_results").click();
    cy.get("#menu_results_status").click();
    return new Result();
  }

  goToValidationByRoutine() {
    this.openNavigationMenu();
    cy.get('#menu_resultvalidation').click();
    cy.get('#menu_resultvalidation_routine ').click();
    return new Validation();
  }
  goToValidationByOrder() {
    this.openNavigationMenu();
    cy.get("#menu_resultvalidation").click();
    cy.get('#menu_accession_validation ').click();
    return new Validation();
  }
  goToValidationByRangeOrder() {
    this.openNavigationMenu();
    cy.get("#menu_resultvalidation").click();
    cy.get('#menu_accession_validation_range ').click();
    return new Validation();
  }
}

export default HomePage;
