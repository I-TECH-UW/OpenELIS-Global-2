class Validation {
    checkForHeading() {
      cy.get('section > h3').should("contain.text", "Validation");
    }
  
    selectTestUnit(unitType) {
      cy.get('#unitType').select(unitType);
    }

    validateTestUnit(unitType) {
      cy.get('#cell-testName-0 > .sampleInfo').should("contain.text", unitType);
    }
  
    enterLabNumberAndSearch(labNo) {
      cy.get('#accessionNumber').type(labNo);
      cy.get('.cds--sm\\:col-span-4.cds--lg\\:col-span-16 > #submit').click();
      cy.get('#cell-sampleInfo-0 > .sampleInfo').should("contain.text", labNo);
    }
  
    saveResults(note) {
      cy.get('#cell-save-0 > .cds--form-item > .cds--checkbox-label').click();
      cy.get('#resultList0\\.note').type(note);
      cy.get(':nth-child(3) > #submit').click();
    }
  }
  
  export default Validation;
  