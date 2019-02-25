INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/UpdateDictionary',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'Dictionary'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/CancelDictionary',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'Dictionary'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/Dictionary',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'Dictionary'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/DictionaryMenu',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'Dictionary'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/SearchDictionaryMenu',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'Dictionary'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/DeleteDictionary',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'Dictionary'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/NextPreviousDictionary',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'Dictionary'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/CancelOrganization',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'Organization'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/UpdateOrganization',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'Organization'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/Organization',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'Organization'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/NextPreviousOrganization',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'Organization'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/OrganizationMenu',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'Organization'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/SearchOrganizationMenu',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'Organization'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/DeleteOrganization',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'Organization'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/PatientResults',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'PatientResults'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/DeleteResultLimits',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'ResultLimits'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/NextPreviousResultLimits',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'ResultLimits'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/ResultLimits',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'ResultLimits'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/ResultLimitsMenu',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'ResultLimits'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/UpdateNextPreviousResultLimits',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'ResultLimits'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/UpdateResultLimits',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'ResultLimits'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/DeleteRole',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'Role'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/NextPreviousRole',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'Role'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/Role',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'Role'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/RoleMenu',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'Role'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/UpdateNextPreviousRole',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'Role'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/UpdateRole',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'Role'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/ManageInventory',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'Inventory'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/ManageInventoryUpdate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'Inventory'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/StatusResults',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'StatusResults'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/DeleteUserRole',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'UserRole'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/NextPreviousSystemUserOnePage',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'UserRole'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/NextPreviousUserRole',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'UserRole'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/UpdateNextPreviousSystemUserOnePage',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'UserRole'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/UpdateNextPreviousUserRole',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'UserRole'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/UpdateUserRole',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'UserRole'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/UserRole',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'UserRole'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/UserRoleMenu',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'UserRole'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/PatientType',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'PatientType'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/PatientTypeMenu',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'PatientType'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/UpdatePatientType',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'PatientType'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/DeleteTypeOfSamplePanel',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'TypeOfSamplePanel'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/TypeOfSamplePanel',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'TypeOfSamplePanel'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/TypeOfSamplePanelMenu',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'TypeOfSamplePanel'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/UpdateTypeOfSamplePanel',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'TypeOfSamplePanel'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/DeleteTypeOfSampleTest',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'TypeOfSampleTest'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/TypeOfSampleTest',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'TypeOfSampleTest'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/TypeOfSampleTestMenu',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'TypeOfSampleTest'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/UpdateTypeOfSampleTest',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'TypeOfSampleTest'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/DeleteUnifiedSystemUser',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'UnifiedSystemUser'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/UnifiedSystemUser',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'UnifiedSystemUser'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/UnifiedSystemUserMenu',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'UnifiedSystemUser'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/UpdateUnifiedSystemUser',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'UnifiedSystemUser'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/LogbookResults',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'LogbookResults'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/LogbookResultsUpdate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'LogbookResults'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/PatientResultsUpdate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'LogbookResults'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/AccessionResultsUpdate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'LogbookResults'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/StatusResultsUpdate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'LogbookResults'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/PatientManagementUpdate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'SamplePatientEntry'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/SamplePatientEntry',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'SamplePatientEntry'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/SamplePatientEntrySave',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'SamplePatientEntry'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/PatientManagement',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'SamplePatientEntry'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/CancelAnalyzerTestName',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'AnalyzerTestName'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/AnalyzerTestName',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'AnalyzerTestName'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/UpdateAnalyzerTestName',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'AnalyzerTestName'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/AnalyzerTestNameMenu',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'AnalyzerTestName'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/DeleteAnalyzerTestName',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'AnalyzerTestName'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/AnalyzerResults',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'AnalyzerResults'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/AnalyzerResultsSave',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'AnalyzerResults'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/AccessionResults',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'AccessionResults'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/SampleEdit',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'SampleEdit'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/SampleEditUpdate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'SampleEdit'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/NonConformity',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'NonConformity'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/NonConformityUpdate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'NonConformity'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/ReferredOutTests',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'ReferredOutTests'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/referredOutTestsUpdate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'ReferredOutTests'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/SampleConfirmationEntry',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'SampleConfirmationEntry'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/SampleConfirmationUpdate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'SampleConfirmationEntry'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/AuditTrailReport',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'AuditTrailView'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/ResultValidation',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'ResultValidation'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/ResultValidationRetroC',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'ResultValidation'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/ResultValidationSave',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'ResultValidation'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/TestRenameEntry',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'TestRenameEntry'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/TestRenameEntry',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'TestRenameEntry'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/TestRenameUpdate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'TestRenameEntry'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/TestActivation',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'TestActivation'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/TestActivation',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'TestActivation'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/TestActivationUpdate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'TestActivation'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/ElisaAlgorithmWorkplan',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'Workplan'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/PrintWorkplanReport',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'Workplan'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/WorkPlanByTest',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'Workplan'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/WorkPlanByTestSection',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'Workplan'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/WorkPlanByPanel',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'Workplan'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/PanelRenameEntry',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'PanelRenameEntry'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/PanelRenameEntry',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'PanelRenameEntry'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/PanelRenameUpdate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'PanelRenameEntry'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/TestOrderability',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'TestOrderability'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/TestOrderabilityUpdate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'TestOrderability'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/TestCatalog',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'TestCatalog'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/TestSectionOrder',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'TestSectionOrder'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/TestSectionOrderUpdate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'TestSectionOrder'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/TestSectionTestAssign',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'TestSectionTestAssign'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/TestSectionTestAssignUpdate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'TestSectionTestAssign'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/TestSectionCreate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'TestSectionCreate'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/TestSectionCreateUpdate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'TestSectionCreate'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/SampleTypeRenameEntry',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'SampleTypeRenameEntry'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/SampleTypeRenameEntry',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'SampleTypeRenameEntry'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/SampleTypeRenameUpdate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'SampleTypeRenameEntry'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/PrintBarcode',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'PrintBarcode'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/PrintBarcode',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'PrintBarcode'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/TestSectionRenameEntry',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'TestSectionRenameEntry'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/TestSectionRenameEntry',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'TestSectionRenameEntry'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/TestSectionRenameUpdate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'TestSectionRenameEntry'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/TestAdd',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'TestAdd'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/TestAddUpdate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'TestAdd'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/UomCreate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'UomCreate'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/UomCreateUpdate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'UomCreate'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/SampleTypeCreate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'SampleTypeCreate'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/SampleTypeCreateUpdate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'SampleTypeCreate'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/SampleTypeOrder',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'SampleTypeOrder'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/SampleTypeOrderUpdate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'SampleTypeOrder'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/SampleTypeTestAssign',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'SampleTypeTestAssign'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/SampleTypeTestAssignUpdate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'SampleTypeTestAssign'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/PanelCreate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'PanelCreate'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/PanelCreateUpdate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'PanelCreate'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/PanelOrder',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'PanelOrder'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/PanelOrderUpdate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'PanelOrder'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/PanelTestAssign',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'PanelTestAssign'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/PanelTestAssignUpdate',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'PanelTestAssign'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/ElectronicOrders',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'ElectronicOrderView'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/ExternalConnectionsConfigMenu',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'ExternalConnectionsConfig'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/ResultReportingConfiguration',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'ResultReportingConfiguration'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/UpdateResultReportingConfiguration',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'ResultReportingConfiguration'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/SampleBatchEntryByProject',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'SampleBatchEntry'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/SampleBatchEntry',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'SampleBatchEntry'));


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id")
VALUES(nextval('clinlims.system_module_url_seq'),
       '/SampleBatchEntrySetup',
         (SELECT id
          FROM clinlims.system_module
          WHERE name = 'SampleBatchEntry'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'AB7500VLAnalyzer'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'AB7500VLAnalyzer'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:AB7500VLAnalyzer'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'AB7500VLAnalyzer'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'AB7500VLAnalyzer'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'AB7500VLAnalyzer'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResultsSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:AB7500VLAnalyzer'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'AB7500VLAnalyzer'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'cobas_integra'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'cobas_integra'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:cobas_integra'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'cobas_integra'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'cobas_integra'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'cobas_integra'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResultsSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:cobas_integra'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'cobas_integra'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Cobas6800VLAnalyzer'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Cobas6800VLAnalyzer'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:Cobas6800VLAnalyzer'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Cobas6800VLAnalyzer'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Cobas6800VLAnalyzer'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Cobas6800VLAnalyzer'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResultsSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:Cobas6800VLAnalyzer'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Cobas6800VLAnalyzer'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'CobasC111Analyzer'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'CobasC111Analyzer'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:CobasC111Analyzer'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'CobasC111Analyzer'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'CobasC111Analyzer'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'CobasC111Analyzer'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResultsSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:CobasC111Analyzer'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'CobasC111Analyzer'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'cobasc311'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'cobasc311'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:cobasc311'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'cobasc311'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'cobasc311'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'cobasc311'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResultsSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:cobasc311'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'cobasc311'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'cobasDBS'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'cobasDBS'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:cobasDBS'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'cobasDBS'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'cobasDBS'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'cobasDBS'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResultsSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:cobasDBS'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'cobasDBS'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'CobasIntegra400'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'CobasIntegra400'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:CobasIntegra400'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'CobasIntegra400'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'CobasIntegra400'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'CobasIntegra400'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResultsSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:CobasIntegra400'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'CobasIntegra400'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'evolis'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'evolis'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:evolis'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'evolis'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'evolis'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'evolis'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResultsSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:evolis'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'evolis'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'facscalibur'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'facscalibur'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:facscalibur'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'facscalibur'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'facscalibur'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'facscalibur'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResultsSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:facscalibur'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'facscalibur'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'FacsCalibur'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'FacsCalibur'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:FacsCalibur'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'FacsCalibur'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'FacsCalibur'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'FacsCalibur'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResultsSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:FacsCalibur'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'FacsCalibur'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'FacsCalibur2Analyzer'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'FacsCalibur2Analyzer'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:FacsCalibur2Analyzer'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'FacsCalibur2Analyzer'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'FacsCalibur2Analyzer'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'FacsCalibur2Analyzer'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResultsSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:FacsCalibur2Analyzer'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'FacsCalibur2Analyzer'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'facscanto'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'facscanto'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:facscanto'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'facscanto'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'facscanto'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'facscanto'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResultsSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:facscanto'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'facscanto'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'FacsCantoII'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'FacsCantoII'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:FacsCantoII'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'FacsCantoII'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'FacsCantoII'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'FacsCantoII'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResultsSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:FacsCantoII'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'FacsCantoII'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'FACSPrestoAnalyzer'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'FACSPrestoAnalyzer'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:FACSPrestoAnalyzer'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'FACSPrestoAnalyzer'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'FACSPrestoAnalyzer'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'FACSPrestoAnalyzer'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResultsSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:FACSPrestoAnalyzer'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'FACSPrestoAnalyzer'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'FullyAnalyzer'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'FullyAnalyzer'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:FullyAnalyzer'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'FullyAnalyzer'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'FullyAnalyzer'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'FullyAnalyzer'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResultsSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:FullyAnalyzer'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'FullyAnalyzer'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'ordersImportVL'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'ordersImportVL'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:ordersImportVL'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'ordersImportVL'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'ordersImportVL'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'ordersImportVL'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResultsSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:ordersImportVL'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'ordersImportVL'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'sysmex'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'sysmex'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:sysmex'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'sysmex'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'sysmex'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'sysmex'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResultsSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:sysmex'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'sysmex'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Sysmex4000iAnalyzer'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Sysmex4000iAnalyzer'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:Sysmex4000iAnalyzer'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Sysmex4000iAnalyzer'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Sysmex4000iAnalyzer'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Sysmex4000iAnalyzer'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResultsSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:Sysmex4000iAnalyzer'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Sysmex4000iAnalyzer'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'SysmexAnalyzer'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'SysmexAnalyzer'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:SysmexAnalyzer'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'SysmexAnalyzer'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'SysmexAnalyzer'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'SysmexAnalyzer'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResultsSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:SysmexAnalyzer'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'SysmexAnalyzer'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'SysmexKX21Analyzer'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'SysmexKX21Analyzer'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:SysmexKX21Analyzer'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'SysmexKX21Analyzer'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'SysmexKX21Analyzer'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'SysmexKX21Analyzer'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResultsSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:SysmexKX21Analyzer'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'SysmexKX21Analyzer'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'TaqMan48DBSAnalyzer'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'TaqMan48DBSAnalyzer'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:TaqMan48DBSAnalyzer'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'TaqMan48DBSAnalyzer'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'TaqMan48DBSAnalyzer'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'TaqMan48DBSAnalyzer'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResultsSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:TaqMan48DBSAnalyzer'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'TaqMan48DBSAnalyzer'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'TaqMan48VLAnalyzer'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'TaqMan48VLAnalyzer'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:TaqMan48VLAnalyzer'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'TaqMan48VLAnalyzer'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'TaqMan48VLAnalyzer'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'TaqMan48VLAnalyzer'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResultsSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:TaqMan48VLAnalyzer'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'TaqMan48VLAnalyzer'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'TaqMan96DBSAnalyzer'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'TaqMan96DBSAnalyzer'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:TaqMan96DBSAnalyzer'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'TaqMan96DBSAnalyzer'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'TaqMan96DBSAnalyzer'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'TaqMan96DBSAnalyzer'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResultsSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:TaqMan96DBSAnalyzer'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'TaqMan96DBSAnalyzer'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'TaqMan96VLAnalyzer'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'TaqMan96VLAnalyzer'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:TaqMan96VLAnalyzer'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'TaqMan96VLAnalyzer'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'TaqMan96VLAnalyzer'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'TaqMan96VLAnalyzer'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AnalyzerResultsSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'AnalyzerResults:TaqMan96VLAnalyzer'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'TaqMan96VLAnalyzer'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'bacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'bacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:bacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'bacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'bacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'bacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:bacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'bacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'bacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'bacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:bacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'bacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'bacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'bacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AccessionResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:bacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'bacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'bacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'bacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/StatusResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:bacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'bacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Biochemistry'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Biochemistry'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Biochemistry'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Biochemistry'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Biochemistry'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Biochemistry'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Biochemistry'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Biochemistry'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Biochemistry'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Biochemistry'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Biochemistry'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Biochemistry'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Biochemistry'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Biochemistry'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AccessionResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Biochemistry'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Biochemistry'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Biochemistry'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Biochemistry'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/StatusResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Biochemistry'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Biochemistry'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'chem'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'chem'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:chem'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'chem'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'chem'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'chem'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:chem'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'chem'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'chem'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'chem'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:chem'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'chem'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'chem'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'chem'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AccessionResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:chem'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'chem'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'chem'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'chem'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/StatusResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:chem'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'chem'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'cytobacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'cytobacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:cytobacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'cytobacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'cytobacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'cytobacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:cytobacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'cytobacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'cytobacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'cytobacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:cytobacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'cytobacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'cytobacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'cytobacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AccessionResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:cytobacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'cytobacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'cytobacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'cytobacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/StatusResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:cytobacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'cytobacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'ECBU'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'ECBU'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:ECBU'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'ECBU'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'ECBU'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'ECBU'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:ECBU'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'ECBU'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'ECBU'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'ECBU'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:ECBU'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'ECBU'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'ECBU'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'ECBU'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AccessionResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:ECBU'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'ECBU'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'ECBU'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'ECBU'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/StatusResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:ECBU'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'ECBU'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'EID'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'EID'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:EID'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'EID'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'EID'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'EID'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:EID'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'EID'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'EID'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'EID'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:EID'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'EID'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'EID'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'EID'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AccessionResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:EID'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'EID'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'EID'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'EID'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/StatusResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:EID'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'EID'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'endocrin'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'endocrin'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:endocrin'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'endocrin'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'endocrin'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'endocrin'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:endocrin'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'endocrin'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'endocrin'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'endocrin'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:endocrin'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'endocrin'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'endocrin'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'endocrin'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AccessionResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:endocrin'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'endocrin'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'endocrin'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'endocrin'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/StatusResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:endocrin'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'endocrin'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'hemato-immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'hemato-immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:hemato-immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'hemato-immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'hemato-immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'hemato-immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:hemato-immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'hemato-immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'hemato-immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'hemato-immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:hemato-immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'hemato-immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'hemato-immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'hemato-immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AccessionResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:hemato-immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'hemato-immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'hemato-immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'hemato-immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/StatusResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:hemato-immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'hemato-immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Hematology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Hematology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Hematology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Hematology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Hematology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Hematology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Hematology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Hematology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Hematology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Hematology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Hematology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Hematology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Hematology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Hematology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AccessionResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Hematology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Hematology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Hematology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Hematology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/StatusResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Hematology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Hematology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'HIV'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'HIV'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:HIV'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'HIV'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'HIV'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'HIV'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:HIV'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'HIV'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'HIV'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'HIV'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:HIV'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'HIV'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'HIV'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'HIV'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AccessionResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:HIV'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'HIV'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'HIV'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'HIV'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/StatusResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:HIV'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'HIV'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'immuno'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'immuno'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:immuno'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'immuno'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'immuno'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'immuno'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:immuno'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'immuno'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'immuno'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'immuno'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:immuno'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'immuno'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'immuno'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'immuno'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AccessionResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:immuno'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'immuno'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'immuno'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'immuno'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/StatusResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:immuno'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'immuno'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AccessionResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/StatusResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'liquidBio'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'liquidBio'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:liquidBio'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'liquidBio'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'liquidBio'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'liquidBio'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:liquidBio'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'liquidBio'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'liquidBio'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'liquidBio'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:liquidBio'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'liquidBio'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'liquidBio'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'liquidBio'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AccessionResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:liquidBio'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'liquidBio'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'liquidBio'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'liquidBio'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/StatusResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:liquidBio'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'liquidBio'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Molecular Biology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Molecular Biology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Molecular Biology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Molecular Biology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Molecular Biology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Molecular Biology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Molecular Biology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Molecular Biology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Molecular Biology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Molecular Biology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Molecular Biology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Molecular Biology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Molecular Biology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Molecular Biology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AccessionResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Molecular Biology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Molecular Biology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Molecular Biology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Molecular Biology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/StatusResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Molecular Biology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Molecular Biology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'mycobacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'mycobacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:mycobacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'mycobacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'mycobacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'mycobacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:mycobacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'mycobacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'mycobacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'mycobacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:mycobacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'mycobacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'mycobacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'mycobacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AccessionResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:mycobacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'mycobacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'mycobacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'mycobacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/StatusResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:mycobacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'mycobacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'mycology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'mycology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:mycology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'mycology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'mycology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'mycology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:mycology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'mycology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'mycology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'mycology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:mycology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'mycology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'mycology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'mycology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AccessionResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:mycology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'mycology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'mycology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'mycology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/StatusResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:mycology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'mycology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'mycrobacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'mycrobacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:mycrobacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'mycrobacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'mycrobacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'mycrobacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:mycrobacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'mycrobacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'mycrobacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'mycrobacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:mycrobacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'mycrobacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'mycrobacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'mycrobacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AccessionResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:mycrobacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'mycrobacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'mycrobacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'mycrobacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/StatusResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:mycrobacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'mycrobacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Parasitology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Parasitology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Parasitology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Parasitology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Parasitology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Parasitology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Parasitology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Parasitology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Parasitology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Parasitology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Parasitology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Parasitology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Parasitology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Parasitology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AccessionResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Parasitology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Parasitology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Parasitology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Parasitology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/StatusResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Parasitology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Parasitology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'serologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'serologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:serologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'serologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'serologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'serologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:serologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'serologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'serologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'serologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:serologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'serologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'serologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'serologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AccessionResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:serologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'serologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'serologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'serologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/StatusResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:serologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'serologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'serology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'serology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:serology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'serology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'serology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'serology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:serology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'serology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'serology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'serology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:serology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'serology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'serology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'serology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AccessionResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:serology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'serology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'serology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'serology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/StatusResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:serology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'serology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Serology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Serology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Serology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Serology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Serology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Serology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Serology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Serology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Serology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Serology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Serology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Serology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Serology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Serology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AccessionResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Serology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Serology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Serology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Serology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/StatusResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Serology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Serology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Serology-Immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Serology-Immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Serology-Immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Serology-Immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Serology-Immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Serology-Immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Serology-Immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Serology-Immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Serology-Immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Serology-Immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Serology-Immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Serology-Immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Serology-Immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Serology-Immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AccessionResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Serology-Immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Serology-Immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Serology-Immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Serology-Immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/StatusResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Serology-Immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Serology-Immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Virologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Virologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Virologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Virologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Virologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Virologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Virologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Virologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Virologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Virologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Virologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Virologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Virologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Virologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AccessionResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Virologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Virologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Virologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Virologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/StatusResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:Virologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Virologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'VL'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'VL'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResults',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:VL'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'VL'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'VL'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'VL'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/LogbookResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:VL'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'VL'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'VL'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'VL'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:VL'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'VL'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'VL'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'VL'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/AccessionResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:VL'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'VL'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'VL'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'VL'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/StatusResultsUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'LogbookResults:VL'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'VL'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'readonly'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'readonly'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientEditByProject',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'PatientEditByProject:readonly'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'readonly'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'readonly'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'readonly'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientEditByProjectSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'PatientEditByProject:readonly'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'readonly'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'readwrite'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'readwrite'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientEditByProject',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'PatientEditByProject:readwrite'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'readwrite'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'readwrite'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'readwrite'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientEditByProjectSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'PatientEditByProject:readwrite'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'readwrite'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'initial'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'initial'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientEntryByProject',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'PatientEntryByProject:initial'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'initial'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'initial'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'initial'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientEntryByProjectUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'PatientEntryByProject:initial'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'initial'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'verify'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'verify'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientEntryByProject',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'PatientEntryByProject:verify'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'verify'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'verify'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'verify'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PatientEntryByProjectUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'PatientEntryByProject:verify'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'verify'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'indicator'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'indicator'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/Report',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Report:indicator'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'indicator'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'indicator'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'indicator'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ReportPrint',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Report:indicator'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'indicator'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'patient'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'patient'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/Report',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Report:patient'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'patient'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'patient'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'patient'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ReportPrint',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Report:patient'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'patient'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'summary'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'summary'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/Report',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Report:summary'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'summary'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'summary'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'summary'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ReportPrint',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Report:summary'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'summary'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Bacteria'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Bacteria'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidation',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Bacteria'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Bacteria'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Bacteria'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Bacteria'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationRetroC',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Bacteria'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Bacteria'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Bacteria'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Bacteria'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Bacteria'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Bacteria'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Biochemistry'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Biochemistry'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidation',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Biochemistry'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Biochemistry'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Biochemistry'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Biochemistry'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationRetroC',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Biochemistry'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Biochemistry'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Biochemistry'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Biochemistry'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Biochemistry'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Biochemistry'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Cytobacteriologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Cytobacteriologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidation',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Cytobacteriologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Cytobacteriologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Cytobacteriologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Cytobacteriologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationRetroC',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Cytobacteriologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Cytobacteriologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Cytobacteriologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Cytobacteriologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Cytobacteriologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Cytobacteriologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'ECBU'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'ECBU'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidation',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:ECBU'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'ECBU'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'ECBU'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'ECBU'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationRetroC',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:ECBU'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'ECBU'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'ECBU'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'ECBU'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:ECBU'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'ECBU'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'EID'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'EID'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidation',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:EID'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'EID'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'EID'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'EID'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationRetroC',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:EID'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'EID'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'EID'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'EID'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:EID'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'EID'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Endocrinologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Endocrinologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidation',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Endocrinologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Endocrinologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Endocrinologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Endocrinologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationRetroC',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Endocrinologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Endocrinologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Endocrinologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Endocrinologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Endocrinologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Endocrinologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Hematology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Hematology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidation',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Hematology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Hematology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Hematology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Hematology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationRetroC',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Hematology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Hematology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Hematology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Hematology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Hematology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Hematology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Hemto-Immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Hemto-Immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidation',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Hemto-Immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Hemto-Immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Hemto-Immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Hemto-Immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationRetroC',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Hemto-Immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Hemto-Immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Hemto-Immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Hemto-Immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Hemto-Immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Hemto-Immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidation',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationRetroC',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Liquides biologique'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Liquides biologique'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidation',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Liquides biologique'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Liquides biologique'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Liquides biologique'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Liquides biologique'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationRetroC',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Liquides biologique'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Liquides biologique'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Liquides biologique'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Liquides biologique'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Liquides biologique'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Liquides biologique'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Molecular Biology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Molecular Biology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidation',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Molecular Biology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Molecular Biology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Molecular Biology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Molecular Biology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationRetroC',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Molecular Biology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Molecular Biology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Molecular Biology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Molecular Biology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Molecular Biology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Molecular Biology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Mycobacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Mycobacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidation',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Mycobacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Mycobacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Mycobacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Mycobacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationRetroC',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Mycobacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Mycobacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Mycobacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Mycobacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Mycobacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Mycobacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'mycology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'mycology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidation',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:mycology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'mycology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'mycology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'mycology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationRetroC',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:mycology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'mycology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'mycology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'mycology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:mycology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'mycology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Parasitology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Parasitology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidation',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Parasitology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Parasitology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Parasitology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Parasitology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationRetroC',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Parasitology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Parasitology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Parasitology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Parasitology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Parasitology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Parasitology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Serologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Serologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidation',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Serologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Serologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Serologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Serologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationRetroC',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Serologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Serologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Serologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Serologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Serologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Serologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'serology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'serology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidation',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:serology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'serology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'serology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'serology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationRetroC',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:serology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'serology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'serology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'serology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:serology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'serology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Serology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Serology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidation',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Serology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Serology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Serology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Serology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationRetroC',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Serology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Serology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Serology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Serology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Serology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Serology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'serology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'serology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidation',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:serology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'serology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'serology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'serology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationRetroC',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:serology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'serology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'serology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'serology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:serology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'serology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Serology-Immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Serology-Immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidation',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Serology-Immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Serology-Immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Serology-Immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Serology-Immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationRetroC',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Serology-Immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Serology-Immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Serology-Immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Serology-Immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Serology-Immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Serology-Immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'VCT'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'VCT'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidation',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:VCT'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'VCT'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'VCT'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'VCT'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationRetroC',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:VCT'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'VCT'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'VCT'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'VCT'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:VCT'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'VCT'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Virologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Virologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidation',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Virologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Virologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Virologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Virologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationRetroC',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Virologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Virologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Virologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Virologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:Virologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Virologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'virology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'virology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidation',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:virology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'virology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'virology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'virology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationRetroC',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:virology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'virology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'virology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'virology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:virology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'virology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'virology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'virology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidation',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:virology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'virology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'virology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'virology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationRetroC',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:virology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'virology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'virology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'virology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:virology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'virology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'VL'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'VL'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidation',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:VL'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'VL'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'VL'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'VL'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationRetroC',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:VL'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'VL'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'VL'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'VL'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ResultValidationSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'ResultValidation:VL'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'VL'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'readonly'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'readonly'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/SampleEdit',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'SampleEdit:readonly'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'readonly'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'readonly'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'readonly'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/SampleEditUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'SampleEdit:readonly'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'readonly'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'readwrite'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'readwrite'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/SampleEdit',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'SampleEdit:readwrite'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'readwrite'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'readwrite'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'readwrite'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/SampleEditUpdate',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'SampleEdit:readwrite'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'readwrite'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'initial'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'initial'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/SampleEntryByProject',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'SampleEntryByProject:initial'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'initial'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'initial'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'initial'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/SampleEntryByProject',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'SampleEntryByProject:initial'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'initial'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'initial'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'initial'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/SampleEntryByProjectSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'SampleEntryByProject:initial'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'initial'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'initial'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'initial'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/SampleEntryEID',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'SampleEntryByProject:initial'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'initial'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'initial'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'initial'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/SampleEntryEIDSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'SampleEntryByProject:initial'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'initial'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'initial'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'initial'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/SampleEntryVL',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'SampleEntryByProject:initial'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'initial'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'initial'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'initial'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/SampleEntryVLSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'SampleEntryByProject:initial'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'initial'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'verify'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'verify'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/SampleEntryByProject',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'SampleEntryByProject:verify'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'verify'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'verify'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'verify'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/SampleEntryByProject',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'SampleEntryByProject:verify'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'verify'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'verify'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'verify'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/SampleEntryByProjectSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'SampleEntryByProject:verify'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'verify'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'verify'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'verify'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/SampleEntryEID',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'SampleEntryByProject:verify'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'verify'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'verify'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'verify'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/SampleEntryEIDSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'SampleEntryByProject:verify'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'verify'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'verify'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'verify'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/SampleEntryVL',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'SampleEntryByProject:verify'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'verify'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'verify'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'verify'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/SampleEntryVLSave',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'SampleEntryByProject:verify'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'verify'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'bacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'bacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ElisaAlgorithmWorkplan',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:bacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'bacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'bacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'bacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PrintWorkplanReport',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:bacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'bacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'bacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'bacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTest',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:bacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'bacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'bacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'bacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTestSection',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:bacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'bacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'bacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'bacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByPanel',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:bacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'bacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Biochemistry'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Biochemistry'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ElisaAlgorithmWorkplan',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Biochemistry'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Biochemistry'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Biochemistry'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Biochemistry'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PrintWorkplanReport',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Biochemistry'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Biochemistry'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Biochemistry'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Biochemistry'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTest',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Biochemistry'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Biochemistry'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Biochemistry'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Biochemistry'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTestSection',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Biochemistry'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Biochemistry'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Biochemistry'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Biochemistry'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByPanel',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Biochemistry'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Biochemistry'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'chem'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'chem'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ElisaAlgorithmWorkplan',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:chem'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'chem'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'chem'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'chem'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PrintWorkplanReport',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:chem'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'chem'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'chem'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'chem'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTest',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:chem'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'chem'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'chem'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'chem'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTestSection',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:chem'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'chem'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'chem'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'chem'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByPanel',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:chem'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'chem'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'cytobacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'cytobacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ElisaAlgorithmWorkplan',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:cytobacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'cytobacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'cytobacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'cytobacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PrintWorkplanReport',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:cytobacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'cytobacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'cytobacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'cytobacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTest',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:cytobacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'cytobacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'cytobacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'cytobacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTestSection',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:cytobacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'cytobacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'cytobacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'cytobacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByPanel',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:cytobacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'cytobacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'ECBU'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'ECBU'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ElisaAlgorithmWorkplan',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:ECBU'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'ECBU'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'ECBU'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'ECBU'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PrintWorkplanReport',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:ECBU'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'ECBU'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'ECBU'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'ECBU'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTest',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:ECBU'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'ECBU'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'ECBU'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'ECBU'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTestSection',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:ECBU'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'ECBU'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'ECBU'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'ECBU'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByPanel',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:ECBU'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'ECBU'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'EID'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'EID'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ElisaAlgorithmWorkplan',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:EID'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'EID'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'EID'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'EID'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PrintWorkplanReport',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:EID'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'EID'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'EID'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'EID'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTest',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:EID'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'EID'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'EID'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'EID'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTestSection',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:EID'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'EID'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'EID'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'EID'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByPanel',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:EID'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'EID'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'endocrin'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'endocrin'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ElisaAlgorithmWorkplan',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:endocrin'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'endocrin'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'endocrin'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'endocrin'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PrintWorkplanReport',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:endocrin'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'endocrin'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'endocrin'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'endocrin'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTest',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:endocrin'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'endocrin'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'endocrin'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'endocrin'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTestSection',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:endocrin'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'endocrin'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'endocrin'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'endocrin'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByPanel',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:endocrin'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'endocrin'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'hemato-immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'hemato-immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ElisaAlgorithmWorkplan',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:hemato-immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'hemato-immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'hemato-immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'hemato-immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PrintWorkplanReport',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:hemato-immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'hemato-immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'hemato-immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'hemato-immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTest',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:hemato-immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'hemato-immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'hemato-immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'hemato-immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTestSection',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:hemato-immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'hemato-immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'hemato-immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'hemato-immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByPanel',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:hemato-immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'hemato-immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Hematology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Hematology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ElisaAlgorithmWorkplan',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Hematology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Hematology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Hematology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Hematology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PrintWorkplanReport',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Hematology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Hematology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Hematology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Hematology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTest',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Hematology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Hematology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Hematology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Hematology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTestSection',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Hematology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Hematology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Hematology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Hematology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByPanel',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Hematology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Hematology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'HIV'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'HIV'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ElisaAlgorithmWorkplan',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:HIV'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'HIV'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'HIV'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'HIV'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PrintWorkplanReport',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:HIV'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'HIV'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'HIV'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'HIV'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTest',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:HIV'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'HIV'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'HIV'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'HIV'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTestSection',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:HIV'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'HIV'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'HIV'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'HIV'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByPanel',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:HIV'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'HIV'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'immuno'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'immuno'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ElisaAlgorithmWorkplan',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:immuno'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'immuno'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'immuno'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'immuno'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PrintWorkplanReport',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:immuno'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'immuno'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'immuno'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'immuno'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTest',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:immuno'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'immuno'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'immuno'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'immuno'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTestSection',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:immuno'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'immuno'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'immuno'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'immuno'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByPanel',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:immuno'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'immuno'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ElisaAlgorithmWorkplan',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PrintWorkplanReport',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTest',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTestSection',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByPanel',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'liquidBio'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'liquidBio'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ElisaAlgorithmWorkplan',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:liquidBio'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'liquidBio'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'liquidBio'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'liquidBio'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PrintWorkplanReport',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:liquidBio'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'liquidBio'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'liquidBio'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'liquidBio'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTest',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:liquidBio'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'liquidBio'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'liquidBio'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'liquidBio'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTestSection',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:liquidBio'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'liquidBio'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'liquidBio'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'liquidBio'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByPanel',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:liquidBio'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'liquidBio'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Molecular Biology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Molecular Biology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ElisaAlgorithmWorkplan',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Molecular Biology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Molecular Biology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Molecular Biology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Molecular Biology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PrintWorkplanReport',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Molecular Biology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Molecular Biology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Molecular Biology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Molecular Biology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTest',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Molecular Biology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Molecular Biology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Molecular Biology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Molecular Biology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTestSection',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Molecular Biology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Molecular Biology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Molecular Biology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Molecular Biology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByPanel',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Molecular Biology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Molecular Biology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'mycology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'mycology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ElisaAlgorithmWorkplan',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:mycology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'mycology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'mycology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'mycology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PrintWorkplanReport',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:mycology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'mycology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'mycology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'mycology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTest',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:mycology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'mycology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'mycology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'mycology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTestSection',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:mycology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'mycology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'mycology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'mycology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByPanel',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:mycology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'mycology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'mycrobacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'mycrobacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ElisaAlgorithmWorkplan',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:mycrobacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'mycrobacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'mycrobacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'mycrobacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PrintWorkplanReport',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:mycrobacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'mycrobacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'mycrobacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'mycrobacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTest',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:mycrobacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'mycrobacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'mycrobacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'mycrobacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTestSection',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:mycrobacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'mycrobacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'mycrobacteriology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'mycrobacteriology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByPanel',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:mycrobacteriology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'mycrobacteriology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'panel'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'panel'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ElisaAlgorithmWorkplan',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:panel'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'panel'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'panel'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'panel'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PrintWorkplanReport',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:panel'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'panel'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'panel'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'panel'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTest',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:panel'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'panel'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'panel'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'panel'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTestSection',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:panel'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'panel'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'panel'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'panel'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByPanel',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:panel'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'panel'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Parasitology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Parasitology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ElisaAlgorithmWorkplan',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Parasitology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Parasitology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Parasitology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Parasitology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PrintWorkplanReport',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Parasitology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Parasitology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Parasitology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Parasitology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTest',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Parasitology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Parasitology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Parasitology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Parasitology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTestSection',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Parasitology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Parasitology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Parasitology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Parasitology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByPanel',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Parasitology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Parasitology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'serologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'serologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ElisaAlgorithmWorkplan',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:serologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'serologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'serologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'serologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PrintWorkplanReport',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:serologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'serologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'serologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'serologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTest',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:serologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'serologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'serologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'serologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTestSection',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:serologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'serologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'serologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'serologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByPanel',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:serologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'serologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Serology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Serology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ElisaAlgorithmWorkplan',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Serology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Serology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Serology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Serology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PrintWorkplanReport',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Serology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Serology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Serology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Serology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTest',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Serology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Serology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Serology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Serology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTestSection',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Serology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Serology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Serology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Serology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByPanel',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Serology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Serology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Serology-Immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Serology-Immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ElisaAlgorithmWorkplan',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Serology-Immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Serology-Immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Serology-Immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Serology-Immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PrintWorkplanReport',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Serology-Immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Serology-Immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Serology-Immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Serology-Immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTest',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Serology-Immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Serology-Immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Serology-Immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Serology-Immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTestSection',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Serology-Immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Serology-Immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Serology-Immunology'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Serology-Immunology'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByPanel',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Serology-Immunology'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Serology-Immunology'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'test'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'test'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ElisaAlgorithmWorkplan',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:test'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'test'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'test'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'test'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PrintWorkplanReport',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:test'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'test'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'test'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'test'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTest',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:test'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'test'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'test'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'test'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTestSection',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:test'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'test'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'test'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'test'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByPanel',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:test'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'test'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Virologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Virologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ElisaAlgorithmWorkplan',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Virologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Virologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Virologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Virologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PrintWorkplanReport',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Virologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Virologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Virologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Virologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTest',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Virologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Virologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Virologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Virologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTestSection',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Virologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Virologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'Virologie'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'Virologie'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByPanel',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:Virologie'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'Virologie'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'VL'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'VL'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/ElisaAlgorithmWorkplan',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:VL'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'VL'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'VL'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'VL'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/PrintWorkplanReport',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:VL'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'VL'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'VL'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'VL'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTest',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:VL'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'VL'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'VL'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'VL'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByTestSection',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:VL'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'VL'
             AND name = 'type'));


INSERT INTO clinlims.system_module_param ("id", "name", "value")
SELECT nextval('clinlims.system_module_param_seq'),
       'type',
       'VL'
WHERE NOT EXISTS
    (SELECT 1
     FROM clinlims.system_module_param
     WHERE value = 'VL'
       AND name = 'type');


INSERT INTO clinlims.system_module_url ("id", "url_path", "system_module_id", "system_module_param_id")
VALUES (nextval('clinlims.system_module_url_seq'),
        '/WorkPlanByPanel',
          (SELECT id
           FROM clinlims.system_module
           WHERE name = 'Workplan:VL'
           LIMIT 1),
          (SELECT id
           FROM clinlims.system_module_param
           WHERE value = 'VL'
             AND name = 'type'));

