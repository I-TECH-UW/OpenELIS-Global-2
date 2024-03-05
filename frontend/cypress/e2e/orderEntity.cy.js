import LoginPage from "../pages/LoginPage";
import TestProperties from "../common/TestProperties";

let homePage = null;
let loginPage = null;
let orderEntityPage = null;
let patientPage = null;

before('login', () => {
    loginPage = new LoginPage();
    loginPage.visit();
});

describe('Patient Information', function () {

    it('User Visits Home Page and goes to Order entity Page ', function () {
        homePage = loginPage.goToHomePage();
        orderEntityPage = homePage.goToOrderPage();
        patientPage = orderEntityPage.getPatientPage();
    });

    it('should search patient By PatientId', function () {
        cy.wait(1000);
        cy.fixture('Patient').then((patient) => {
            patientPage.searchPatientByPatientId(patient.nationalId);
            patientPage.clickSearchPatientButton();
            cy.wait(200);
            patientPage.getPatientSearchResultsTable().then(($element) => {
                if ($element.find('tr').length) {
                    cy.fixture('Patient').then((patient) => {
                        patientPage.validatePatientSearchTable(patient.firstName, patient.inValidName);
                    });
                } else {
                    assert.isOk('OK', 'No patient found')
                }
            });
        });
        cy.wait(200).reload();
    });

    it('Should search Patient By First and LastName', function () {
        cy.wait(1000);
        cy.fixture('Patient').then((patient) => {
            patientPage.searchPatientByFirstAndLastName(patient.firstName, patient.lastName)
            patientPage.getFirstName().should('have.value', patient.firstName)
            patientPage.getLastName().should('have.value', patient.lastName)

            patientPage.getLastName().should('not.have.value', patient.inValidName)

            patientPage.clickSearchPatientButton();
            patientPage.getPatientSearchResultsTable().then(($element) => {
                if ($element.find('tr').length) {
                    cy.fixture('Patient').then((patient) => {
                        patientPage.validatePatientSearchTable(patient.firstName, patient.inValidName);
                    });
                } else {
                    assert.isOk('OK', 'No patient found')
                }
            });
        });
        cy.wait(200).reload();
    });

    it.skip('User should be able to search patient by Previous Lab Number', function () {
        cy.fixture('Patient').then((patient) => {
            cy.wait(1000);
            patientPage.searchPatientByAccessionNo(patient.accessionNumber);
            patientPage.clickSearchPatientButton();
            cy.wait(1000);
            patientPage.getPatientSearchResultsTable().then(($element) => {
                if ($element.find('tr').length) {
                    cy.fixture('Patient').then((patient) => {
                        patientPage.validatePatientSearchTable(patient.firstName, patient.inValidName);
                    });
                } else {
                    assert.isOk('OK', 'No patient found with this accession number')
                }
            });
        });
        cy.wait(200).reload();
    });

    it('Should be able to search patients By gender', function () {
        cy.wait(1000);
        patientPage.getMaleGenderRadioButton().should('be.visible');
        patientPage.getMaleGenderRadioButton().click();
        cy.wait(200)
        patientPage.clickSearchPatientButton();
        patientPage.getPatientSearchResultsTable().then(($element) => {
            if ($element.find('tr').length) {
                cy.fixture('Patient').then((patient) => {
                    patientPage.validatePatientSearchTable(patient.firstName, patient.inValidName);
                });
            } else {
                assert.isOk('OK', 'No patient found')
            }
        });
    });

    it('User Selects a patient from the search Results', function () {
        patientPage.getPatientSearchResultsTable().then(($element) => {
            if ($element.find('tr').length) {
                patientPage.selectPatientFromSearchResults();
                patientPage.getLastName().invoke('val').should('not.be.empty');
                patientPage.getFirstName().invoke('val').should('not.be.empty');
                patientPage.getSubjectNo().invoke('val').should('not.be.empty');
                patientPage.getNationaID().invoke('val').should('not.be.empty');
                cy.wait(100);

                patientPage.getSelectedSearchPatient().click();
            } else {
                patientPage.clickNewPatientTab();
                cy.wait(100);
                cy.fixture('Order').then((order) => {
                    let patient = order.patient;

                    patientPage.enterPatientInfo(patient.firstName, patient.lastName,
                        patient.subjectNumber, patient.nationalId,
                        patient.DOB, patient.gender, "Lift Valley East", "HAUT SASSANDRA",
                        "VAVOUA", "Married", "Primary", "BOLIVIAN");
                });
            }
        });

    });
});

describe('Program selection', function () {

    it("User Clicks next tab to go to programs selection", () => {
        orderEntityPage.clickNextButton();
        cy.fixture('Order').then((order) => {
            orderEntityPage.getProgramSelectOption()
                .select(order.program);
        });
        cy.wait(100);
        orderEntityPage.clickNextButton();
        cy.wait(100);
    });

});
describe('Add order samples', function () {

    it('User Should select Sample type from the dropdown', () => {

        orderEntityPage.getSampleTypeSelector().should('exist');

        cy.fixture('Order').then((order) => {

            order.samples.forEach((sample) => {
                orderEntityPage.getSampleTypeSelector().select(sample.sampleType);
                orderEntityPage.enterCollectionDateTimeAndCollector(sample.collectionDate,sample.collectionTime,sample.collector);
                it('User should select Order panel checkbox', function () {
                    orderEntityPage.checkPanelCheckBoxField();
                });

            });
        });
        cy.wait(500);
        orderEntityPage.clickNextButton();
    });


});

 describe('Process Order', function () {

    it.skip('User can enter labNo Or generate Lab Order Number by clicking on the generate button', () => {
        let accessionNo = "";
        cy.fixture('Order').then((order) => {
            orderEntityPage.enterAccessionNo(order.labNo);
            accessionNo = order.labNo;
        });
        cy.wait(400);

        orderEntityPage.getLabNoField().invoke('val').then(val => {
            accessionNo = val
        }).then(() => {
            cy.request("GET", TestProperties.TEST_SERVER_URL + "api/OpenELIS-Global/rest/SampleEntryAccessionNumberValidation?ignoreYear=false&ignoreUsage=false&field=labNo&accessionNumber=" + accessionNo).then((response) => {
                if (response.body.status === false) {
                    expect(response.body.status).to.eq(false);
                    orderEntityPage.generateLabOrderNumber();
                } else {
                    expect(response.body.status).to.eq(true);
                }
            });
        });
    });

    it.skip('User selects Priority of the test from the select Option', () => {
        cy.fixture('Order').then((order) => {
            orderEntityPage.selectionPrioritySelectionOption().select(order.priority);
        });
    });

    it.skip('Set Order request date,received date and Reception Time plus the Date of next visit in a format of dd/mm/yyyy', () => {
        cy.fixture('Order').then((order) => {
            orderEntityPage.enterRequestReceivedNextVisitDatesAndReceptionTime(order.requestDate, order.receivedDate, order.nextVisitDate, order.receptionTime);
        });
    });


    it.skip('should Enter or select site name', function () {
        cy.scrollTo('top');
        cy.wait(1000);
        cy.fixture('Order').then((order) => {
            orderEntityPage.enterSiteName(order.siteName);
        });
    });

    it.skip('should enter requester first and last name\'s , email, phone number', function () {
        cy.fixture('Order').then((order) => {
            orderEntityPage.enterRequesterInfo(order.requester.firstName, order.requester.lastName, order.requester.email, order.requester.phoneNumber)
        });
    });

    it.skip('should click submit order button', function () {
        orderEntityPage.clickSubmitOrderButton();
    });
});

after('Close Browser', () => {
    loginPage.logoutSession();
});