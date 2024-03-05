const { defineConfig } = require('cypress')
const fs = require('fs');
const path = require('path');

module.exports = defineConfig({
  viewportWidth: 1200,
  viewportHeight: 700,
  reporter: 'cypress-mochawesome-reporter',
  trashAssetsBeforeRuns: true,
  reporterOptions: {
    charts: true,
    html: true,
    json: false,
    reportPageTitle: 'OpenELIS Global Version 3.0 QA Report',
    embeddedScreenshots: true,
    inlineAssets: true,
    saveAllAttempts: false,
  },
  e2e: {
    setupNodeEvents(on, config) {
      require('cypress-mochawesome-reporter/plugin')(on);

      /**
       *  To be used later
       *
      on('after:spec', (spec, results) => {
        if (results && results.video) {
          // Do we have failures for any retry attempts?
          const failures = results.tests.some((test) =>
              test.attempts.some((attempt) => attempt.state === 'failed')
          )
          if (!failures) {
            // delete the video if the spec passed and no tests retried
            fs.unlinkSync(results.video)
          }
        }
      });

      on('before:run', () =>{
        const screenshotsDirectory = 'cypress/screenshots';
        fs.promises.readdir(screenshotsDirectory)
            .then(files => {
              for (let file of files) {
                const filePath = path.join(screenshotsDirectory, file);
                 fs.unlinkSync(filePath);
              }
            })
            .catch(err => {
              console.log(err)
            });
      });
       **/
    },
    baseUrl: "https://localhost",
    // baseUrl: "https://openelis28.openelis-global.org",
    testIsolation: false,
  },
});
