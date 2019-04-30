package us.mn.state.health.lims.common.dao;

import us.mn.state.health.lims.address.daoimpl.AddressPartDAOImpl;
import us.mn.state.health.lims.address.daoimpl.OrganizationAddressDAOImpl;
import us.mn.state.health.lims.address.daoimpl.PersonAddressDAOImpl;
import us.mn.state.health.lims.address.valueholder.AddressPart;
import us.mn.state.health.lims.address.valueholder.OrganizationAddress;
import us.mn.state.health.lims.address.valueholder.PersonAddress;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;
import us.mn.state.health.lims.analyte.daoimpl.AnalyteDAOImpl;
import us.mn.state.health.lims.analyte.valueholder.Analyte;
import us.mn.state.health.lims.analyzer.daoimpl.AnalyzerDAOImpl;
import us.mn.state.health.lims.analyzer.valueholder.Analyzer;
import us.mn.state.health.lims.analyzerimport.daoimpl.AnalyzerTestMappingDAOImpl;
import us.mn.state.health.lims.analyzerimport.valueholder.AnalyzerTestMapping;
import us.mn.state.health.lims.analyzerresults.daoimpl.AnalyzerResultsDAOImpl;
import us.mn.state.health.lims.analyzerresults.valueholder.AnalyzerResults;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.audittrail.valueholder.History;
import us.mn.state.health.lims.barcode.daoimpl.BarcodeLabelInfoDAOImpl;
import us.mn.state.health.lims.barcode.valueholder.BarcodeLabelInfo;
import us.mn.state.health.lims.citystatezip.daoimpl.CityStateZipDAOImpl;
import us.mn.state.health.lims.citystatezip.valueholder.CityStateZip;
import us.mn.state.health.lims.common.daoimpl.BaseDAOImpl;
import us.mn.state.health.lims.common.daoimpl.DatabaseChangeLogDAOImpl;
import us.mn.state.health.lims.common.valueholder.BaseObject;
import us.mn.state.health.lims.common.valueholder.DatabaseChangeLog;
import us.mn.state.health.lims.dataexchange.aggregatereporting.daoimpl.ReportExternalExportDAOImpl;
import us.mn.state.health.lims.dataexchange.aggregatereporting.daoimpl.ReportExternalImportDAOImpl;
import us.mn.state.health.lims.dataexchange.aggregatereporting.daoimpl.ReportQueueTypeDAOImpl;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportExternalExport;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportExternalImport;
import us.mn.state.health.lims.dataexchange.aggregatereporting.valueholder.ReportQueueType;
import us.mn.state.health.lims.dataexchange.order.daoimpl.ElectronicOrderDAOImpl;
import us.mn.state.health.lims.dataexchange.order.valueholder.ElectronicOrder;
import us.mn.state.health.lims.dataexchange.orderresult.DAO.HL7MessageOutDAOImpl;
import us.mn.state.health.lims.dataexchange.orderresult.valueholder.HL7MessageOut;
import us.mn.state.health.lims.datasubmission.daoimpl.DataIndicatorDAOImpl;
import us.mn.state.health.lims.datasubmission.daoimpl.DataResourceDAOImpl;
import us.mn.state.health.lims.datasubmission.daoimpl.DataValueDAOImpl;
import us.mn.state.health.lims.datasubmission.daoimpl.TypeOfDataIndicatorDAOImpl;
import us.mn.state.health.lims.datasubmission.valueholder.DataIndicator;
import us.mn.state.health.lims.datasubmission.valueholder.DataResource;
import us.mn.state.health.lims.datasubmission.valueholder.DataValue;
import us.mn.state.health.lims.datasubmission.valueholder.TypeOfDataIndicator;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.dictionarycategory.daoimpl.DictionaryCategoryDAOImpl;
import us.mn.state.health.lims.dictionarycategory.valueholder.DictionaryCategory;
import us.mn.state.health.lims.gender.daoimpl.GenderDAOImpl;
import us.mn.state.health.lims.gender.valueholder.Gender;
import us.mn.state.health.lims.image.daoimpl.ImageDAOImpl;
import us.mn.state.health.lims.image.valueholder.Image;
import us.mn.state.health.lims.inventory.daoimpl.InventoryItemDAOImpl;
import us.mn.state.health.lims.inventory.daoimpl.InventoryLocationDAOImpl;
import us.mn.state.health.lims.inventory.daoimpl.InventoryReceiptDAOImpl;
import us.mn.state.health.lims.inventory.valueholder.InventoryItem;
import us.mn.state.health.lims.inventory.valueholder.InventoryLocation;
import us.mn.state.health.lims.inventory.valueholder.InventoryReceipt;
import us.mn.state.health.lims.localization.daoimpl.LocalizationDAOImpl;
import us.mn.state.health.lims.localization.valueholder.Localization;
import us.mn.state.health.lims.login.daoimpl.LoginDAOImpl;
import us.mn.state.health.lims.login.valueholder.Login;
import us.mn.state.health.lims.menu.daoimpl.MenuDAOImpl;
import us.mn.state.health.lims.menu.valueholder.Menu;
import us.mn.state.health.lims.method.daoimpl.MethodDAOImpl;
import us.mn.state.health.lims.method.valueholder.Method;
import us.mn.state.health.lims.note.daoimpl.NoteDAOImpl;
import us.mn.state.health.lims.note.valueholder.Note;
import us.mn.state.health.lims.observationhistory.daoimpl.ObservationHistoryDAOImpl;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.observationhistorytype.daoImpl.ObservationHistoryTypeDAOImpl;
import us.mn.state.health.lims.observationhistorytype.valueholder.ObservationHistoryType;
import us.mn.state.health.lims.organization.daoimpl.OrganizationContactDAOImpl;
import us.mn.state.health.lims.organization.daoimpl.OrganizationDAOImpl;
import us.mn.state.health.lims.organization.daoimpl.OrganizationTypeDAOImpl;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.organization.valueholder.OrganizationContact;
import us.mn.state.health.lims.organization.valueholder.OrganizationType;
import us.mn.state.health.lims.panel.daoimpl.PanelDAOImpl;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.panelitem.daoimpl.PanelItemDAOImpl;
import us.mn.state.health.lims.panelitem.valueholder.PanelItem;
import us.mn.state.health.lims.patient.daoimpl.PatientDAOImpl;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.patientidentitytype.daoimpl.PatientIdentityTypeDAOImpl;
import us.mn.state.health.lims.patientidentitytype.valueholder.PatientIdentityType;
import us.mn.state.health.lims.patienttype.daoimpl.PatientTypeDAOImpl;
import us.mn.state.health.lims.patienttype.valueholder.PatientType;
import us.mn.state.health.lims.person.daoimpl.PersonDAOImpl;
import us.mn.state.health.lims.person.valueholder.Person;
import us.mn.state.health.lims.project.daoimpl.ProjectDAOImpl;
import us.mn.state.health.lims.project.valueholder.Project;
import us.mn.state.health.lims.provider.daoimpl.ProviderDAOImpl;
import us.mn.state.health.lims.provider.valueholder.Provider;
import us.mn.state.health.lims.qaevent.daoimpl.QaEventDAOImpl;
import us.mn.state.health.lims.qaevent.daoimpl.QaObservationDAOImpl;
import us.mn.state.health.lims.qaevent.daoimpl.QaObservationTypeDAOImpl;
import us.mn.state.health.lims.qaevent.valueholder.QaEvent;
import us.mn.state.health.lims.qaevent.valueholder.QaObservation;
import us.mn.state.health.lims.qaevent.valueholder.QaObservationType;
import us.mn.state.health.lims.referencetables.daoimpl.ReferenceTablesDAOImpl;
import us.mn.state.health.lims.referencetables.valueholder.ReferenceTables;
import us.mn.state.health.lims.referral.daoimpl.ReferralReasonDAOImpl;
import us.mn.state.health.lims.referral.valueholder.ReferralReason;
import us.mn.state.health.lims.renametestsection.daoimpl.RenameTestSectionDAOImpl;
import us.mn.state.health.lims.renametestsection.valueholder.RenameTestSection;
import us.mn.state.health.lims.reports.daoimpl.DocumentTrackDAOImpl;
import us.mn.state.health.lims.reports.daoimpl.DocumentTypeDAOImpl;
import us.mn.state.health.lims.reports.valueholder.DocumentTrack;
import us.mn.state.health.lims.reports.valueholder.DocumentType;
import us.mn.state.health.lims.requester.daoimpl.RequesterTypeDAOImpl;
import us.mn.state.health.lims.requester.valueholder.RequesterType;
import us.mn.state.health.lims.result.daoimpl.ResultDAOImpl;
import us.mn.state.health.lims.result.daoimpl.ResultInventoryDAOImpl;
import us.mn.state.health.lims.result.daoimpl.ResultSignatureDAOImpl;
import us.mn.state.health.lims.result.valueholder.Result;
import us.mn.state.health.lims.result.valueholder.ResultInventory;
import us.mn.state.health.lims.result.valueholder.ResultSignature;
import us.mn.state.health.lims.resultlimits.daoimpl.ResultLimitDAOImpl;
import us.mn.state.health.lims.resultlimits.valueholder.ResultLimit;
import us.mn.state.health.lims.role.daoimpl.RoleDAOImpl;
import us.mn.state.health.lims.role.valueholder.Role;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.daoimpl.SampleHumanDAOImpl;
import us.mn.state.health.lims.samplehuman.valueholder.SampleHuman;
import us.mn.state.health.lims.sampleitem.daoimpl.SampleItemDAOImpl;
import us.mn.state.health.lims.sampleitem.valueholder.SampleItem;
import us.mn.state.health.lims.sampleorganization.daoimpl.SampleOrganizationDAOImpl;
import us.mn.state.health.lims.sampleorganization.valueholder.SampleOrganization;
import us.mn.state.health.lims.samplepdf.daoimpl.SamplePdfDAOImpl;
import us.mn.state.health.lims.samplepdf.valueholder.SamplePdf;
import us.mn.state.health.lims.sampleproject.daoimpl.SampleProjectDAOImpl;
import us.mn.state.health.lims.sampleproject.valueholder.SampleProject;
import us.mn.state.health.lims.sampleqaevent.daoimpl.SampleQaEventDAOImpl;
import us.mn.state.health.lims.sampleqaevent.valueholder.SampleQaEvent;
import us.mn.state.health.lims.scheduler.daoimpl.CronSchedulerDAOImpl;
import us.mn.state.health.lims.scheduler.valueholder.CronScheduler;
import us.mn.state.health.lims.scriptlet.daoimpl.ScriptletDAOImpl;
import us.mn.state.health.lims.scriptlet.valueholder.Scriptlet;
import us.mn.state.health.lims.siteinformation.daoimpl.SiteInformationDAOImpl;
import us.mn.state.health.lims.siteinformation.daoimpl.SiteInformationDomainDAOImpl;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformationDomain;
import us.mn.state.health.lims.statusofsample.daoimpl.StatusOfSampleDAOImpl;
import us.mn.state.health.lims.statusofsample.valueholder.StatusOfSample;
import us.mn.state.health.lims.systemmodule.daoimpl.SystemModuleDAOImpl;
import us.mn.state.health.lims.systemmodule.daoimpl.SystemModuleUrlDAOImpl;
import us.mn.state.health.lims.systemmodule.valueholder.SystemModule;
import us.mn.state.health.lims.systemmodule.valueholder.SystemModuleUrl;
import us.mn.state.health.lims.systemuser.daoimpl.SystemUserDAOImpl;
import us.mn.state.health.lims.systemuser.valueholder.SystemUser;
import us.mn.state.health.lims.systemusermodule.daoimpl.PermissionAgentFactory;
import us.mn.state.health.lims.systemusermodule.valueholder.PermissionModule;
import us.mn.state.health.lims.systemusersection.daoimpl.SystemUserSectionDAOImpl;
import us.mn.state.health.lims.systemusersection.valueholder.SystemUserSection;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.daoimpl.TestSectionDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestSection;
import us.mn.state.health.lims.testanalyte.daoimpl.TestAnalyteDAOImpl;
import us.mn.state.health.lims.testanalyte.valueholder.TestAnalyte;
import us.mn.state.health.lims.testcodes.daoimpl.OrgHL7SchemaDAOImpl;
import us.mn.state.health.lims.testcodes.daoimpl.TestCodeTypeDAOImpl;
import us.mn.state.health.lims.testcodes.valueholder.OrganizationHL7Schema;
import us.mn.state.health.lims.testcodes.valueholder.TestCodeType;
import us.mn.state.health.lims.testdictionary.daoimpl.TestDictionaryDAOImpl;
import us.mn.state.health.lims.testdictionary.valueholder.TestDictionary;
import us.mn.state.health.lims.testreflex.daoimpl.TestReflexDAOImpl;
import us.mn.state.health.lims.testreflex.valueholder.TestReflex;
import us.mn.state.health.lims.testresult.daoimpl.TestResultDAOImpl;
import us.mn.state.health.lims.testresult.valueholder.TestResult;
import us.mn.state.health.lims.testtrailer.daoimpl.TestTrailerDAOImpl;
import us.mn.state.health.lims.testtrailer.valueholder.TestTrailer;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleDAOImpl;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSamplePanelDAOImpl;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleTestDAOImpl;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSamplePanel;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSampleTest;
import us.mn.state.health.lims.typeoftestresult.daoimpl.TypeOfTestResultDAOImpl;
import us.mn.state.health.lims.typeoftestresult.valueholder.TypeOfTestResult;
import us.mn.state.health.lims.unitofmeasure.daoimpl.UnitOfMeasureDAOImpl;
import us.mn.state.health.lims.unitofmeasure.valueholder.UnitOfMeasure;
import us.mn.state.health.lims.userrole.daoimpl.UserRoleDAOImpl;
import us.mn.state.health.lims.userrole.valueholder.UserRole;

// if we want to support other connection styles other than database, this factory needs to change. Abstract Factory pattern?
public class DaoFactory<T> {

	private DaoFactory() {
	}

	@SuppressWarnings("unchecked")
	public static <T extends BaseObject> BaseDAO<T> getDAOForType(Class<T> type) {

		if (AddressPart.class.equals(type)) {
			return (BaseDAO<T>) new AddressPartDAOImpl();
		} else if (Analysis.class.equals(type)) {
			return (BaseDAO<T>) new AnalysisDAOImpl();
		} else if (Analyte.class.equals(type)) {
			return (BaseDAO<T>) new AnalyteDAOImpl();
		} else if (Analyzer.class.equals(type)) {
			return (BaseDAO<T>) new AnalyzerDAOImpl();
		} else if (AnalyzerResults.class.equals(type)) {
			return (BaseDAO<T>) new AnalyzerResultsDAOImpl();
		} else if (AnalyzerTestMapping.class.equals(type)) {
			return (BaseDAO<T>) new AnalyzerTestMappingDAOImpl();
		} else if (History.class.equals(type)) {
			return (BaseDAO<T>) new AuditTrailDAOImpl();
		} else if (BarcodeLabelInfo.class.equals(type)) {
			return (BaseDAO<T>) new BarcodeLabelInfoDAOImpl();
		} else if (CityStateZip.class.equals(type)) {
			return (BaseDAO<T>) new CityStateZipDAOImpl();
		} else if (CronScheduler.class.equals(type)) {
			return (BaseDAO<T>) new CronSchedulerDAOImpl();
		} else if (DatabaseChangeLog.class.equals(type)) {
			return (BaseDAO<T>) new DatabaseChangeLogDAOImpl();
		} else if (DataIndicator.class.equals(type)) {
			return (BaseDAO<T>) new DataIndicatorDAOImpl();
		} else if (DataResource.class.equals(type)) {
			return (BaseDAO<T>) new DataResourceDAOImpl();
		} else if (DataValue.class.equals(type)) {
			return (BaseDAO<T>) new DataValueDAOImpl();
		} else if (DictionaryCategory.class.equals(type)) {
			return (BaseDAO<T>) new DictionaryCategoryDAOImpl();
		} else if (Dictionary.class.equals(type)) {
			return (BaseDAO<T>) new DictionaryDAOImpl();
		} else if (DocumentTrack.class.equals(type)) {
			return (BaseDAO<T>) new DocumentTrackDAOImpl();
		} else if (DocumentType.class.equals(type)) {
			return (BaseDAO<T>) new DocumentTypeDAOImpl();
		} else if (ElectronicOrder.class.equals(type)) {
			return (BaseDAO<T>) new ElectronicOrderDAOImpl();
		} else if (Gender.class.equals(type)) {
			return (BaseDAO<T>) new GenderDAOImpl();
		} else if (HL7MessageOut.class.equals(type)) {
			return (BaseDAO<T>) new HL7MessageOutDAOImpl();
		} else if (Image.class.equals(type)) {
			return (BaseDAO<T>) new ImageDAOImpl();
		} else if (InventoryItem.class.equals(type)) {
			return (BaseDAO<T>) new InventoryItemDAOImpl();
		} else if (InventoryLocation.class.equals(type)) {
			return (BaseDAO<T>) new InventoryLocationDAOImpl();
		} else if (InventoryReceipt.class.equals(type)) {
			return (BaseDAO<T>) new InventoryReceiptDAOImpl();
		} else if (Localization.class.equals(type)) {
			return (BaseDAO<T>) new LocalizationDAOImpl();
		} else if (Login.class.equals(type)) {
			return (BaseDAO<T>) new LoginDAOImpl();
		} else if (Menu.class.equals(type)) {
			return (BaseDAO<T>) new MenuDAOImpl();
		} else if (Method.class.equals(type)) {
			return (BaseDAO<T>) new MethodDAOImpl();
		} else if (Note.class.equals(type)) {
			return (BaseDAO<T>) new NoteDAOImpl();
		} else if (ObservationHistory.class.equals(type)) {
			return (BaseDAO<T>) new ObservationHistoryDAOImpl();
		} else if (ObservationHistoryType.class.equals(type)) {
			return (BaseDAO<T>) new ObservationHistoryTypeDAOImpl();
		} else if (OrganizationAddress.class.equals(type)) {
			return (BaseDAO<T>) new OrganizationAddressDAOImpl();
		} else if (OrganizationContact.class.equals(type)) {
			return (BaseDAO<T>) new OrganizationContactDAOImpl();
		} else if (Organization.class.equals(type)) {
			return (BaseDAO<T>) new OrganizationDAOImpl();
		} else if (OrganizationType.class.equals(type)) {
			return (BaseDAO<T>) new OrganizationTypeDAOImpl();
		} else if (OrganizationHL7Schema.class.equals(type)) {
			return (BaseDAO<T>) new OrgHL7SchemaDAOImpl();
		} else if (Panel.class.equals(type)) {
			return (BaseDAO<T>) new PanelDAOImpl();
		} else if (PanelItem.class.equals(type)) {
			return (BaseDAO<T>) new PanelItemDAOImpl();
		} else if (Patient.class.equals(type)) {
			return (BaseDAO<T>) new PatientDAOImpl();
		} else if (PatientIdentityType.class.equals(type)) {
			return (BaseDAO<T>) new PatientIdentityTypeDAOImpl();
		} else if (PatientType.class.equals(type)) {
			return (BaseDAO<T>) new PatientTypeDAOImpl();
		} else if (PermissionModule.class.equals(type)) {
			return (BaseDAO<T>) PermissionAgentFactory.getPermissionAgentImpl();
		} else if (PersonAddress.class.equals(type)) {
			return (BaseDAO<T>) new PersonAddressDAOImpl();
		} else if (Person.class.equals(type)) {
			return (BaseDAO<T>) new PersonDAOImpl();
		} else if (Project.class.equals(type)) {
			return (BaseDAO<T>) new ProjectDAOImpl();
		} else if (Provider.class.equals(type)) {
			return (BaseDAO<T>) new ProviderDAOImpl();
		} else if (QaEvent.class.equals(type)) {
			return (BaseDAO<T>) new QaEventDAOImpl();
		} else if (QaObservation.class.equals(type)) {
			return (BaseDAO<T>) new QaObservationDAOImpl();
		} else if (QaObservationType.class.equals(type)) {
			return (BaseDAO<T>) new QaObservationTypeDAOImpl();
		} else if (ReferenceTables.class.equals(type)) {
			return (BaseDAO<T>) new ReferenceTablesDAOImpl();
		} else if (ReferralReason.class.equals(type)) {
			return (BaseDAO<T>) new ReferralReasonDAOImpl();
		} else if (RenameTestSection.class.equals(type)) {
			return (BaseDAO<T>) new RenameTestSectionDAOImpl();
		} else if (ReportExternalExport.class.equals(type)) {
			return (BaseDAO<T>) new ReportExternalExportDAOImpl();
		} else if (ReportExternalImport.class.equals(type)) {
			return (BaseDAO<T>) new ReportExternalImportDAOImpl();
		} else if (ReportQueueType.class.equals(type)) {
			return (BaseDAO<T>) new ReportQueueTypeDAOImpl();
		} else if (RequesterType.class.equals(type)) {
			return (BaseDAO<T>) new RequesterTypeDAOImpl();
		} else if (Result.class.equals(type)) {
			return (BaseDAO<T>) new ResultDAOImpl();
		} else if (ResultInventory.class.equals(type)) {
			return (BaseDAO<T>) new ResultInventoryDAOImpl();
		} else if (ResultLimit.class.equals(type)) {
			return (BaseDAO<T>) new ResultLimitDAOImpl();
		} else if (ResultSignature.class.equals(type)) {
			return (BaseDAO<T>) new ResultSignatureDAOImpl();
		} else if (Role.class.equals(type)) {
			return (BaseDAO<T>) new RoleDAOImpl();
		} else if (Sample.class.equals(type)) {
			return (BaseDAO<T>) new SampleDAOImpl();
		} else if (SampleHuman.class.equals(type)) {
			return (BaseDAO<T>) new SampleHumanDAOImpl();
		} else if (SampleItem.class.equals(type)) {
			return (BaseDAO<T>) new SampleItemDAOImpl();
		} else if (SampleOrganization.class.equals(type)) {
			return (BaseDAO<T>) new SampleOrganizationDAOImpl();
		} else if (SamplePdf.class.equals(type)) {
			return (BaseDAO<T>) new SamplePdfDAOImpl();
		} else if (SampleProject.class.equals(type)) {
			return (BaseDAO<T>) new SampleProjectDAOImpl();
		} else if (SampleQaEvent.class.equals(type)) {
			return (BaseDAO<T>) new SampleQaEventDAOImpl();
		} else if (Scriptlet.class.equals(type)) {
			return (BaseDAO<T>) new ScriptletDAOImpl();
		} else if (SiteInformation.class.equals(type)) {
			return (BaseDAO<T>) new SiteInformationDAOImpl();
		} else if (SiteInformationDomain.class.equals(type)) {
			return (BaseDAO<T>) new SiteInformationDomainDAOImpl();
		} else if (StatusOfSample.class.equals(type)) {
			return (BaseDAO<T>) new StatusOfSampleDAOImpl();
		} else if (SystemModule.class.equals(type)) {
			return (BaseDAO<T>) new SystemModuleDAOImpl();
		} else if (SystemModuleUrl.class.equals(type)) {
			return (BaseDAO<T>) new SystemModuleUrlDAOImpl();
		} else if (SystemUser.class.equals(type)) {
			return (BaseDAO<T>) new SystemUserDAOImpl();
		} else if (SystemUserSection.class.equals(type)) {
			return (BaseDAO<T>) new SystemUserSectionDAOImpl();
		} else if (TestAnalyte.class.equals(type)) {
			return (BaseDAO<T>) new TestAnalyteDAOImpl();
		} else if (TestCodeType.class.equals(type)) {
			return (BaseDAO<T>) new TestCodeTypeDAOImpl();
		} else if (Test.class.equals(type)) {
			return (BaseDAO<T>) new TestDAOImpl();
		} else if (TestDictionary.class.equals(type)) {
			return (BaseDAO<T>) new TestDictionaryDAOImpl();
		} else if (TestReflex.class.equals(type)) {
			return (BaseDAO<T>) new TestReflexDAOImpl();
		} else if (TestResult.class.equals(type)) {
			return (BaseDAO<T>) new TestResultDAOImpl();
		} else if (TestSection.class.equals(type)) {
			return (BaseDAO<T>) new TestSectionDAOImpl();
			// TODO this is also a DAO for the same object, make a service layer operation?
			// return (BaseDAO<T>) new UserTestSectionDAOImpl();
		} else if (TestTrailer.class.equals(type)) {
			return (BaseDAO<T>) new TestTrailerDAOImpl();
		} else if (TypeOfDataIndicator.class.equals(type)) {
			return (BaseDAO<T>) new TypeOfDataIndicatorDAOImpl();
		} else if (TypeOfSample.class.equals(type)) {
			return (BaseDAO<T>) new TypeOfSampleDAOImpl();
		} else if (TypeOfSamplePanel.class.equals(type)) {
			return (BaseDAO<T>) new TypeOfSamplePanelDAOImpl();
		} else if (TypeOfSampleTest.class.equals(type)) {
			return (BaseDAO<T>) new TypeOfSampleTestDAOImpl();
		} else if (TypeOfTestResult.class.equals(type)) {
			return (BaseDAO<T>) new TypeOfTestResultDAOImpl();
		} else if (UnitOfMeasure.class.equals(type)) {
			return (BaseDAO<T>) new UnitOfMeasureDAOImpl();
		} else if (UserRole.class.equals(type)) {
			return (BaseDAO<T>) new UserRoleDAOImpl();
		}

		return new BaseDAOImpl<>(type);
	}

}
