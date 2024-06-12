import LoginPage from "../pages/LoginPage";

const login = new LoginPage();

describe("Failing or Succeeding to Login", function () {
  beforeEach("User visits login page", () => {
    login.visit();
    // login.acceptSelfAssignedCert();
  });

  afterEach("Close Browser", () => {
    cy.clearLocalStorage();
  });
  it("Attempts to login without providing username and password", function () {
    login.signIn();
  });

  it("Attempts to login with only a username", function () {
    cy.fixture("Users").then((users) => {
      let user = users[3];
      login.enterUsername(user.username);
      login.signIn();
    });
  });

  it("Attempts to login with only a password", function () {
    cy.wait(500);
    cy.fixture("Users").then((users) => {
      let user = users[3];
      login.enterPassword(user.password);
      login.signIn();
    });
  });
  it("Should validate user authentication", function () {
    cy.wait(500);
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
