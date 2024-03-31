import LoginPage from "../pages/LoginPage";

let homePage = null;
let loginPage = null;
let modifyOrderPage = null;
let patientPage = null;

before("login", () => {
  loginPage = new LoginPage();
  loginPage.visit();
});

describe("Modify Order search by accession Number", function () {
  before(function () {
    // Checking if Order Entity tests have passed since if an order is added then only we can search with Lab number
    const orderEntityTestsPassed = localStorage.getItem(
      "orderEntityTestsPassed",
    );
    if (orderEntityTestsPassed !== "true") {
      // Skipping tests
      this.skip();
    }
  });

  it("User Visits Home Page and goes to Modify Order Page ", function () {
    homePage = loginPage.goToHomePage();
    modifyOrderPage = homePage.goToModifyOrderPage();
  });

  it("User searches with accession number", () => {
    cy.fixture("Order").then((order) => {
      modifyOrderPage.enterAccessionNo(order.labNo);
    });
    modifyOrderPage.clickSubmitButton();
  });

  it("should check for program selection button ", function () {
    modifyOrderPage.checkProgramButton();
  });

  it("should be able to assign test ", function () {
    modifyOrderPage.assignValues();
  });

  it("User should click next to go add order page and submit the order", function () {
    modifyOrderPage.clickNextButton();
    cy.wait(1000);
    modifyOrderPage.clickSubmitButton();
  });

  it("should be able to print barcode", function () {
    cy.intercept("POST", "/LabelMakerServlet?labNo=").as("printBarcode");
    cy.get("#submitButton").click();
    cy.wait("@printBarcode").then((interception) => {
      expect(interception.response.statusCode).to.eq(200);
    });
  });
});



