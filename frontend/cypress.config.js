const { defineConfig } = require('cypress')

module.exports = defineConfig({
  viewportWidth: 1200,
  viewportHeight: 700,
  e2e: {
    setupNodeEvents(on, config) {
      // implement node event listeners here
    },
    baseUrl: "https://openelis28.openelis-global.org",
    testIsolation: false,
  },
});
