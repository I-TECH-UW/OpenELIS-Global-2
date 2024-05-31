import LoginPage from "../pages/LoginPage";

const login = new LoginPage();

describe("Failing or Succeeding to Login", function () {
  before("User visits login page", () => {
    cy.intercept("/api/OpenELIS-Global/LoginPage").as("backend");
    login.visit();
    cy.wait("@backend", {
      //wait up to configured time minutes for application to startup
      timeout: Cypress.env("STARTUP_WAIT_MILLISECONDS"),
    });
    // login.acceptSelfAssignedCert();
  });

  after("Close Browser", () => {
    cy.clearLocalStorage();
  });
  it("Should validate user authentication", function () {
    cy.fixture("Users").then((users) => {
      users.forEach((user) => {
        login.enterUsername(user.username);
        login.enterPassword(user.password);
        login.signIn();

        if (user.correctPass === true) {
          cy.get("header#mainHeader > button[title='Open menu']")
            .should("exist")
            .and(
              "span:nth-of-type(3) > .cds--btn.cds--btn--icon-only.cds--btn--primary.cds--header__action > svg > path:nth-of-type(1)",
              "exist",
            );
        } else {
          cy.get("div[role='status']").should("be.visible");
        }
      });
    });
  });
});
