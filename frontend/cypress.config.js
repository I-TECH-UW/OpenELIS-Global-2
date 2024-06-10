const { defineConfig } = require("cypress");

module.exports = defineConfig({
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
        "cypress/e2e/modifyOrder.cy.js",
      ];
      return config;
    },
    baseUrl: "https://localhost",
    testIsolation: false,
    watchForFileChanges: false,
  },
});
