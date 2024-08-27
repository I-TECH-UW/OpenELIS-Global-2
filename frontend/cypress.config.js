const { defineConfig } = require("cypress");

module.exports = defineConfig({
  defaultCommandTimeout: 8000,
  viewportWidth: 1200,
  viewportHeight: 700,
  e2e: {
    setupNodeEvents(on, config) {
      // implement node event listeners here
      config.specPattern = [
        "cypress/e2e/login.cy.js",
        "cypress/e2e/patientEntry.cy.js",
        "cypress/e2e/orderEntity.cy.js",
        "cypress/e2e/workplan.cy.js",
        "cypress/e2e/nonConform.cy.js",
        "cypress/e2e/modifyOrder.cy.js",
        "cypress/e2e/batchOrderEntry.cy.js",
        "cypress/e2e/dashboard.cy.js",
      ];
      return config;
    },
    baseUrl: "https://localhost",
    testIsolation: false,
    env: {
      STARTUP_WAIT_MILLISECONDS: 300000,
    },
  },
});
