import LoginPage from "../pages/LoginPage";

let homePage = null;
let loginPage = null;
let batchOrder = null;

const navigateToBatchOrderEntryPage = () => {
  homePage = loginPage.goToHomePage();
  batchOrder = homePage.goToBatchOrderEntry();
};

before(() => {
  cy.fixture("BatchOrder").as("batchOrderData");
});

before("login", () => {
  loginPage = new LoginPage();
  loginPage.visit();
});

describe("Batch Order Entry On Demand", function () {
  before("navigate to Batch Order Entry Page", function () {
    navigateToBatchOrderEntryPage();
  });

  it("User visits Batch Order Entry Setup Page", function () {
    batchOrder.visitSetupPage();
    batchOrder.checkNextButtonDisabled();
  });

  it("Should Select Form And Samples", function () {
    cy.fixture("BatchOrder").then((batchOrderData) => {
      batchOrder.selectForm(batchOrderData.formTypeRoutine);
      batchOrder.selectSampleType(batchOrderData.sampleType);
      batchOrder.selectPanel(3);
    });
  });

  it("Should Select Methods, Site Name and Move to Next Page", function () {
    cy.fixture("BatchOrder").then((batchOrderData) => {
      batchOrder.selectMethod(batchOrderData.methodOnDemand);
      batchOrder.checkFacilityCheckbox();
      batchOrder.enterSiteName(batchOrderData.siteName);
      batchOrder.checkNextButtonEnabled();
    });
  });

  it("Should Visit Batch Order Entry Page", function () {
    batchOrder.visitBatchOrderEntryPage();
  });

  it("Should Validate Fields And Generate BarCode", function () {
    cy.fixture("BatchOrder").then((batchOrderData) => {
      batchOrder.validateField(":nth-child(1) > .cds--subgrid > :nth-child(8)", batchOrderData.sampleType);
      batchOrder.validateField(".cds--lg\\:col-span-12", batchOrderData.panel);
      batchOrder.validateField(":nth-child(1) > .cds--subgrid > :nth-child(13)", batchOrderData.facility);
      batchOrder.checkNextLabel().should("be.disabled");
      batchOrder.clickGenerateAndSaveBarcode();
      batchOrder.checkNextLabel().should("be.visible");
    });
  });
});

describe("Batch Order Entry Pre Printed", function () {
  before("navigate to Batch Order Entry Page", function () {
    navigateToBatchOrderEntryPage();
  });

  it("User visits Batch Order Entry Setup Page", function () {
    batchOrder.visitSetupPage();
    batchOrder.checkNextButtonDisabled();
  });

  it("Should Select Form And Samples", function () {
    cy.fixture("BatchOrder").then((batchOrderData) => {
      batchOrder.selectForm(batchOrderData.formTypeRoutine);
      batchOrder.selectSampleType(batchOrderData.sampleType);
      batchOrder.selectPanel(3);
    });
  });

  it("Should Select Methods, Site Name and Move to Next Page", function () {
    cy.fixture("BatchOrder").then((batchOrderData) => {
      batchOrder.selectMethod(batchOrderData.methodPrePrinted);
      batchOrder.checkFacilityCheckbox();
      batchOrder.checkPatientCheckbox();
      batchOrder.enterSiteName(batchOrderData.siteName);
      batchOrder.checkNextButtonEnabled();
    });
  });

  it("Should Visit Batch Order Entry Page", function () {
    batchOrder.visitBatchOrderEntryPage();
  });

  it("Should Validate Fields", function () {
    cy.fixture("BatchOrder").then((batchOrderData) => {
      batchOrder.validateField(":nth-child(1) > .cds--subgrid > :nth-child(8)", batchOrderData.sampleType);
      batchOrder.validateField(".cds--lg\\:col-span-12", batchOrderData.panel);
      batchOrder.validateField(":nth-child(1) > .cds--subgrid > :nth-child(13)", batchOrderData.facility);
    });
  });

  it("Should Search For Patient And Generate Barcode", function () {
    batchOrder.selectPatientGender(2);
    batchOrder.clickSearchPatient();
    batchOrder.selectPatient(1);

    batchOrder.clickGenerateButton();
    batchOrder.saveOrder();
  });
});
