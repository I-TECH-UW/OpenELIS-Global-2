/**
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/ 
 * 
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations under
 * the License.
 * 
 * The Original Code is OpenELIS code.
 * 
 * Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
 *
 */
package us.mn.state.health.lims.common.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.validator.GenericValidator;

import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.IdValuePair;
import us.mn.state.health.lims.common.util.LocaleChangeListener;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.dictionary.daoimpl.DictionaryDAOImpl;
import us.mn.state.health.lims.dictionary.valueholder.Dictionary;
import us.mn.state.health.lims.gender.daoimpl.GenderDAOImpl;
import us.mn.state.health.lims.gender.valueholder.Gender;
import us.mn.state.health.lims.organization.dao.OrganizationDAO;
import us.mn.state.health.lims.organization.daoimpl.OrganizationDAOImpl;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.panel.dao.PanelDAO;
import us.mn.state.health.lims.panel.daoimpl.PanelDAOImpl;
import us.mn.state.health.lims.panel.valueholder.Panel;
import us.mn.state.health.lims.panel.valueholder.PanelSortOrderComparator;
import us.mn.state.health.lims.qaevent.dao.QaEventDAO;
import us.mn.state.health.lims.qaevent.daoimpl.QaEventDAOImpl;
import us.mn.state.health.lims.qaevent.valueholder.QaEvent;
import us.mn.state.health.lims.referral.daoimpl.ReferralReasonDAOImpl;
import us.mn.state.health.lims.referral.valueholder.ReferralReason;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.daoimpl.TestSectionDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.test.valueholder.TestSection;
import us.mn.state.health.lims.typeofsample.dao.TypeOfSampleDAO;
import us.mn.state.health.lims.typeofsample.daoimpl.TypeOfSampleDAOImpl;
import us.mn.state.health.lims.typeofsample.valueholder.TypeOfSample;
import us.mn.state.health.lims.typeoftestresult.daoimpl.TypeOfTestResultDAOImpl;
import us.mn.state.health.lims.typeoftestresult.valueholder.TypeOfTestResult;
import us.mn.state.health.lims.unitofmeasure.daoimpl.UnitOfMeasureDAOImpl;
import us.mn.state.health.lims.unitofmeasure.valueholder.UnitOfMeasure;

public class DisplayListService implements LocaleChangeListener {

    private static DisplayListService instance = new DisplayListService();

    public enum ListType {
		HOURS, 
		MINS,
        SAMPLE_TYPE_ACTIVE,
        SAMPLE_TYPE_INACTIVE,
        SAMPLE_TYPE,
		INITIAL_SAMPLE_CONDITION,
        SAMPLE_PATIENT_PAYMENT_OPTIONS,
		PATIENT_HEALTH_REGIONS, 
		PATIENT_MARITAL_STATUS, 
		PATIENT_NATIONALITY, 
		PATIENT_EDUCATION, 
		GENDERS, 
		SAMPLE_PATIENT_REFERRING_CLINIC, 
		QA_EVENTS,
		TEST_SECTION,
        TEST_SECTION_INACTIVE,
		TEST_SECTION_BY_NAME,
		HAITI_DEPARTMENTS,
        PATIENT_SEARCH_CRITERIA,
        PANELS,
        PANELS_ACTIVE,
        PANELS_INACTIVE,
        ORDERABLE_TESTS,
        ALL_TESTS,
        REJECTION_REASONS,
        REFERRAL_REASONS,
        REFERRAL_ORGANIZATIONS,
        TEST_LOCATION_CODE,
        PROGRAM,
        RESULT_TYPE_LOCALIZED,
        RESULT_TYPE_RAW,
        UNIT_OF_MEASURE,
        UNIT_OF_MEASURE_ACTIVE,
        UNIT_OF_MEASURE_INACTIVE,
        DICTIONARY_TEST_RESULTS
	}

	private static Map<ListType, List<IdValuePair>> typeToListMap = new HashMap<ListType, List<IdValuePair>>();
    private static Map<String, List<IdValuePair>> dictionaryToListMap = new HashMap<String, List<IdValuePair>>( );

	static {
		typeToListMap.put(ListType.HOURS, createHourList());
		typeToListMap.put(ListType.MINS, createMinList());
		typeToListMap.put(ListType.SAMPLE_TYPE, createTypeOfSampleList());
        typeToListMap.put(ListType.SAMPLE_TYPE_ACTIVE, createSampleTypeList(false));
        typeToListMap.put(ListType.SAMPLE_TYPE_INACTIVE, createSampleTypeList(true));
        typeToListMap.put(ListType.INITIAL_SAMPLE_CONDITION, createFromDictionaryCategoryLocalizedSort("specimen reception condition"));
		typeToListMap.put(ListType.PATIENT_HEALTH_REGIONS,createPatientHealthRegions());
		typeToListMap.put(ListType.PATIENT_MARITAL_STATUS, createFromDictionaryCategoryLocalizedSort("Marital Status Demographic Information"));
		typeToListMap.put(ListType.PATIENT_NATIONALITY, createFromDictionaryCategoryLocalizedSort("Nationality Demographic Information"));
		typeToListMap.put(ListType.PATIENT_EDUCATION, createFromDictionaryCategoryLocalizedSort("Education Level Demographic Information"));
        typeToListMap.put(ListType.GENDERS, createGenderList());
		typeToListMap.put(ListType.SAMPLE_PATIENT_REFERRING_CLINIC,	createReferringClinicList());
        typeToListMap.put(ListType.QA_EVENTS, createSortedQAEvents());
        typeToListMap.put(ListType.TEST_SECTION, createTestSectionList());
        typeToListMap.put(ListType.TEST_SECTION_INACTIVE, createInactiveTestSection());
        typeToListMap.put(ListType.TEST_SECTION_BY_NAME, createTestSectionByNameList());
		typeToListMap.put(ListType.HAITI_DEPARTMENTS, createAddressDepartmentList());
        typeToListMap.put(ListType.SAMPLE_PATIENT_PAYMENT_OPTIONS, createFromDictionaryCategoryLocalizedSort("patientPayment"));
        typeToListMap.put(ListType.PATIENT_SEARCH_CRITERIA, createPatientSearchCriteria());
        typeToListMap.put(ListType.PANELS, createPanelList());
        typeToListMap.put(ListType.PANELS_ACTIVE, createPanelList(false));
        typeToListMap.put(ListType.PANELS_INACTIVE, createPanelList(true));
        typeToListMap.put(ListType.ORDERABLE_TESTS, createOrderableTestList());
        typeToListMap.put(ListType.ALL_TESTS, createTestList());
        typeToListMap.put(ListType.REJECTION_REASONS,createDictionaryListForCategory("resultRejectionReasons"));
        typeToListMap.put(ListType.REFERRAL_REASONS, createReferralReasonList());
        typeToListMap.put(ListType.REFERRAL_ORGANIZATIONS, createReferralOrganizationList());
        typeToListMap.put(ListType.TEST_LOCATION_CODE, createDictionaryListForCategory("testLocationCode"));
        typeToListMap.put(ListType.PROGRAM, createDictionaryListForCategory("programs")  );
        typeToListMap.put(ListType.RESULT_TYPE_LOCALIZED, createLocalizedResultTypeList());
        typeToListMap.put(ListType.RESULT_TYPE_RAW, createRawResultTypeList());
        typeToListMap.put(ListType.UNIT_OF_MEASURE, createUOMList());
        typeToListMap.put(ListType.UNIT_OF_MEASURE_ACTIVE, createUOMList());
        typeToListMap.put(ListType.UNIT_OF_MEASURE_INACTIVE, createUOMList());
        typeToListMap.put(ListType.DICTIONARY_TEST_RESULTS, createDictionaryTestResults());

        SystemConfiguration.getInstance().addLocalChangeListener(instance);
	}

    private static List<IdValuePair> createDictionaryTestResults() {
        List<IdValuePair> testResults = createFromDictionaryCategoryLocalizedSort("CG");
        testResults.addAll(createFromDictionaryCategoryLocalizedSort("HL"));
        testResults.addAll(createFromDictionaryCategoryLocalizedSort("KL"));
        testResults.addAll(createFromDictionaryCategoryLocalizedSort("Test Result"));
        testResults.addAll(createFromDictionaryCategoryLocalizedSort("HIV1NInd"));
        testResults.addAll(createFromDictionaryCategoryLocalizedSort("PosNegIndInv"));
        testResults.addAll(createFromDictionaryCategoryLocalizedSort("HIVResult"));

        Collections.sort(testResults, new Comparator<IdValuePair>() {
            @Override
            public int compare(IdValuePair o1, IdValuePair o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        return testResults;
    }


    @Override
    public void localeChanged(String locale) {
        //refreshes those lists which are dependent on local
    	typeToListMap.put(ListType.SAMPLE_TYPE, createTypeOfSampleList());
        typeToListMap.put(ListType.SAMPLE_TYPE_ACTIVE, createSampleTypeList(false));
        typeToListMap.put(ListType.SAMPLE_TYPE_INACTIVE, createSampleTypeList(true));
        typeToListMap.put(ListType.INITIAL_SAMPLE_CONDITION, createFromDictionaryCategoryLocalizedSort("specimen reception condition"));
        typeToListMap.put(ListType.PATIENT_HEALTH_REGIONS,createPatientHealthRegions());
        typeToListMap.put(ListType.PATIENT_MARITAL_STATUS, createFromDictionaryCategoryLocalizedSort("Marital Status Demographic Information"));
        typeToListMap.put(ListType.PATIENT_NATIONALITY, createFromDictionaryCategoryLocalizedSort("Nationality Demographic Information"));
        typeToListMap.put(ListType.PATIENT_EDUCATION, createFromDictionaryCategoryLocalizedSort("Education Level Demographic Information"));
        typeToListMap.put(ListType.GENDERS, createGenderList());
        typeToListMap.put(ListType.QA_EVENTS, createSortedQAEvents());
        typeToListMap.put(ListType.TEST_SECTION, createTestSectionList());
        typeToListMap.put(ListType.TEST_SECTION_INACTIVE, createInactiveTestSection());
        typeToListMap.put(ListType.TEST_SECTION_BY_NAME, createTestSectionByNameList());
        typeToListMap.put(ListType.SAMPLE_PATIENT_PAYMENT_OPTIONS, createFromDictionaryCategoryLocalizedSort("patientPayment"));
        typeToListMap.put(ListType.PATIENT_SEARCH_CRITERIA, createPatientSearchCriteria());
        typeToListMap.put(ListType.PANELS, createPanelList());
        typeToListMap.put(ListType.PANELS_ACTIVE, createPanelList(false));
        typeToListMap.put(ListType.PANELS_INACTIVE, createPanelList(true));
        dictionaryToListMap = new HashMap<String, List<IdValuePair>>( );
        typeToListMap.put(ListType.REJECTION_REASONS,createDictionaryListForCategory("resultRejectionReasons"));
        typeToListMap.put(ListType.REFERRAL_REASONS, createReferralReasonList());
        new TestService( (Test)null ).localeChanged(locale);
        typeToListMap.put(ListType.ORDERABLE_TESTS, createOrderableTestList());
        typeToListMap.put(ListType.ALL_TESTS, createTestList());
        typeToListMap.put(ListType.TEST_LOCATION_CODE, createDictionaryListForCategory("testLocationCode"));
        typeToListMap.put(ListType.PROGRAM, createDictionaryListForCategory( "programs" )  );
        typeToListMap.put(ListType.RESULT_TYPE_LOCALIZED, createLocalizedResultTypeList());
        typeToListMap.put(ListType.UNIT_OF_MEASURE, createUOMList());
        typeToListMap.put(ListType.DICTIONARY_TEST_RESULTS, createDictionaryTestResults());
    }

    public static List<IdValuePair> getList(ListType listType) {
		return typeToListMap.get(listType);
	}

    public static List<IdValuePair> getListWithLeadingBlank(ListType listType){
        List<IdValuePair> list = new ArrayList<IdValuePair>(  );
        list.add( new IdValuePair( "0", "" ) );
        list.addAll( getList( listType ) );
        return list;
    }

    public static List<IdValuePair> getNumberedList(ListType listType) {
        return addNumberingToDisplayList( getList( listType ) );
    }

    public static List<IdValuePair> getNumberedListWithLeadingBlank(ListType listType){
        List<IdValuePair> list = new ArrayList<IdValuePair>(  );
        list.add( new IdValuePair("0", "" ) );
        list.addAll( getNumberedList( listType ) );
        return list ;
    }
    public static List<IdValuePair> getDictionaryListByCategory(String category) {
       List<IdValuePair> list = dictionaryToListMap.get( category );
        if( list == null){
            list = createDictionaryListForCategory( category );
            if( !list.isEmpty()){
                dictionaryToListMap.put(category, list);
            }
        }

        return list;
    }

    private static List<IdValuePair> createUOMList() {
        List<IdValuePair> list = new ArrayList<IdValuePair>();
        List<UnitOfMeasure> uomList = new UnitOfMeasureDAOImpl().getAllUnitOfMeasures();
        for( UnitOfMeasure uom : uomList){
            list.add(new IdValuePair(uom.getId(), uom.getLocalizedName()));
        }

        return list;
    }

    private static List<IdValuePair> createLocalizedResultTypeList() {
        List<IdValuePair> typeList = new ArrayList<IdValuePair>();

        List<TypeOfTestResult> typeOfTestResultList = new TypeOfTestResultDAOImpl().getAllTypeOfTestResults();
        for(TypeOfTestResult typeOfTestResult : typeOfTestResultList){
            String description = typeOfTestResult.getDescription();
            if( "Dictionary".equals(description)){
                typeList.add(new IdValuePair(typeOfTestResult.getId(), StringUtil.getMessageForKey("result.type.select")));
            }else if( "Numeric".equals(description)){
                typeList.add(new IdValuePair(typeOfTestResult.getId(), StringUtil.getMessageForKey("result.type.numeric")));
            }else if( "Remark".equals(description)){
                typeList.add(new IdValuePair(typeOfTestResult.getId(), StringUtil.getMessageForKey("result.type.freeText")));
            }else if( "Alpha,no range check".equals(description)){
                typeList.add(new IdValuePair(typeOfTestResult.getId(), StringUtil.getMessageForKey("result.type.alpha")));
            }else if( "Multiselect".equals(description)){
                typeList.add(new IdValuePair(typeOfTestResult.getId(), StringUtil.getMessageForKey("result.type.multiselect")));
            }else if( "Cascading Multiselect".equals(description)){
                typeList.add(new IdValuePair(typeOfTestResult.getId(), StringUtil.getMessageForKey("result.type.cascading")));
            }
        }

        return typeList;
    }

    private static List<IdValuePair> createRawResultTypeList() {
        List<IdValuePair> typeList = new ArrayList<IdValuePair>();

        List<TypeOfTestResult> typeOfTestResultList = new TypeOfTestResultDAOImpl().getAllTypeOfTestResults();
        for(TypeOfTestResult typeOfTestResult : typeOfTestResultList){
                typeList.add(new IdValuePair(typeOfTestResult.getId(), typeOfTestResult.getDescription()));
        }

        return typeList;
    }

    private static List<IdValuePair> createDictionaryListForCategory( String category ){
        List<IdValuePair> list = new ArrayList<IdValuePair>( );
        List<Dictionary> dictionaryList = new DictionaryDAOImpl().getDictionaryEntrysByCategoryAbbreviation("categoryName", category, false);
        for( Dictionary dictionary : dictionaryList){
            list.add( new IdValuePair( dictionary.getId(), dictionary.getLocalizedName()  ) );
        }

        return list;
    }

    private static List<IdValuePair> createFromDictionaryCategoryLocalizedSort(String category) {
        List<IdValuePair> dictionaryList = new ArrayList<IdValuePair>();

        List<Dictionary> dictionaries = new DictionaryDAOImpl().getDictionaryEntrysByCategoryNameLocalizedSort(category);
        for (Dictionary dictionary : dictionaries) {
            dictionaryList.add(new IdValuePair(dictionary.getId(), dictionary.getLocalizedName()));
        }

        return dictionaryList;
    }

    public static List<IdValuePair> getFreshList(ListType listType) {
		refreshList( listType);
		return typeToListMap.get(listType);
	}

    public static void refreshList( ListType listType){

        switch (listType) {
            case SAMPLE_PATIENT_REFERRING_CLINIC: {
                typeToListMap.put(ListType.SAMPLE_PATIENT_REFERRING_CLINIC, createReferringClinicList());
                break;
            }
            case ALL_TESTS:{
                TestService.refreshTestNames();
                typeToListMap.put(ListType.ALL_TESTS, createTestList());
                break;
            }
            case ORDERABLE_TESTS:{
                TestService.refreshTestNames();
                typeToListMap.put(ListType.ORDERABLE_TESTS, createOrderableTestList());
                break;
            }
            case SAMPLE_TYPE:{
                typeToListMap.put(ListType.SAMPLE_TYPE, createTypeOfSampleList());
                break;
            }
            case SAMPLE_TYPE_ACTIVE:{
                typeToListMap.put(ListType.SAMPLE_TYPE_ACTIVE, createSampleTypeList(false));
                break;
            }
            case SAMPLE_TYPE_INACTIVE:{
                typeToListMap.put(ListType.SAMPLE_TYPE_INACTIVE, createSampleTypeList(true));
                break;
            }
            case TEST_SECTION:{
                TestSectionService.refreshNames();
                typeToListMap.put(ListType.TEST_SECTION, createTestSectionList());
                break;
            }
            case TEST_SECTION_INACTIVE:{
                TestSectionService.refreshNames();
                typeToListMap.put(ListType.TEST_SECTION_INACTIVE, createInactiveTestSection());
                break;
            }
            case REFERRAL_ORGANIZATIONS:{
                typeToListMap.put(ListType.REFERRAL_ORGANIZATIONS, createReferralOrganizationList());
                break;
            }
            case PANELS: {
                typeToListMap.put(ListType.PANELS, createPanelList());
                break;
            }
            case PANELS_ACTIVE: {
                typeToListMap.put(ListType.PANELS_ACTIVE, createPanelList(false));
                break;
            }
            case PANELS_INACTIVE: {
                typeToListMap.put(ListType.PANELS_INACTIVE, createPanelList(true));
                break;
            }
            case UNIT_OF_MEASURE: {
            	UnitOfMeasureService.refreshNames();
                typeToListMap.put(ListType.UNIT_OF_MEASURE, createUnitOfMeasureList());
                break;
            }
        }
    }

	private static List<IdValuePair> createReferringClinicList() {
		List<IdValuePair> requesterList = new ArrayList<IdValuePair>();

		OrganizationDAO organizationDAO = new OrganizationDAOImpl();
		List<Organization> orgList = organizationDAO.getOrganizationsByTypeName("shortName", RequesterService.REFERRAL_ORG_TYPE );

		for (Organization organization : orgList) {
			if (GenericValidator.isBlankOrNull(organization.getShortName())) {
				requesterList.add(new IdValuePair(organization.getId(), organization.getOrganizationName()));
			} else {
				requesterList.add(new IdValuePair(organization.getId(), organization.getShortName() + " - "
                        + organization.getOrganizationName()));
			}
		}

		return requesterList;
	}

	private static List<IdValuePair> createGenderList() {
		List<IdValuePair> genders = new ArrayList<IdValuePair>();

		@SuppressWarnings("unchecked")
		List<Gender> genderList = new GenderDAOImpl().getAllGenders();

		for (Gender gender : genderList) {
			genders.add(new IdValuePair(gender.getGenderType(), StringUtil.getContextualMessageForKey(gender.getNameKey())));
		}
		return genders;
	}

    private static List<IdValuePair> createReferralReasonList(){
            List<IdValuePair> referralReasons = new ArrayList<IdValuePair>();
            List<ReferralReason> reasonList = new ReferralReasonDAOImpl().getAllReferralReasons();

            for( ReferralReason reason : reasonList) {
                referralReasons.add(new IdValuePair(reason.getId(), reason.getLocalizedName()));
            }

        return referralReasons;
    }

    private static List<IdValuePair> createReferralOrganizationList(){
        List<IdValuePair> pairs = new ArrayList<IdValuePair>();

        OrganizationDAO orgDAO = new OrganizationDAOImpl();
        List<Organization> orgs = orgDAO.getOrganizationsByTypeName("organizationName", "referralLab");

        for (Organization org : orgs) {
            pairs.add(new IdValuePair(org.getId(), org.getOrganizationName()));
        }

        return pairs;
    }
    private static List<IdValuePair> createPanelList(){
        ArrayList<IdValuePair> panels = new ArrayList<IdValuePair>(  );

        List<Panel> panelList = new PanelDAOImpl().getAllPanels();
        
        Collections.sort(panelList, PanelSortOrderComparator.SORT_ORDER_COMPARATOR);
        for(Panel panel : panelList) {
            panels.add(new IdValuePair(panel.getId(), panel.getLocalizedName() ) );
        }

        return panels;
    }

    private static List<IdValuePair> createOrderableTestList(){
        ArrayList<IdValuePair> tests = new ArrayList<IdValuePair>(  );

        List<Test> testList = new TestDAOImpl().getAllActiveOrderableTests();
        for(Test test : testList){
            tests.add( new IdValuePair( test.getId(), TestService.getLocalizedTestNameWithType( test )) );
        }

        Collections.sort( tests, new Comparator<IdValuePair>(){
            @Override
            public int compare( IdValuePair o1, IdValuePair o2 ){
                return o1.getValue().compareTo( o2.getValue() );
            }
        });

        return tests;
    }

    private static List<IdValuePair> createTestList() {
        ArrayList<IdValuePair> tests = new ArrayList<IdValuePair>();

        List<Test> testList = new TestDAOImpl().getAllActiveTests(false);
        for(Test test : testList){
            tests.add( new IdValuePair( test.getId(), TestService.getLocalizedTestNameWithType( test ) ) );

            Collections.sort( tests, new Comparator<IdValuePair>(){
                @Override
                public int compare( IdValuePair o1, IdValuePair o2 ){
                    return o1.getValue().compareTo( o2.getValue() );
                }
            } );
        }

        return tests;
    }


	private static List<IdValuePair> createPatientHealthRegions() {
		List<IdValuePair> regionList = new ArrayList<IdValuePair>();
		List<Organization> orgList = new OrganizationDAOImpl().getOrganizationsByTypeName("id", "Health Region");
		for (Organization org : orgList) {
			regionList.add(new IdValuePair(org.getId(), org.getOrganizationName()));
		}
		return regionList;
	}

    public static List<IdValuePair> addNumberingToDisplayList(List<IdValuePair> displayList) {
        List<IdValuePair> numberedList = new ArrayList<IdValuePair>( displayList.size() );
        int cnt = 1;
        for (IdValuePair pair : displayList) {
            numberedList.add( new IdValuePair( pair.getId(), cnt++ + ". " + pair.getValue() ) );
        }

        return numberedList;
    }

	private static List<IdValuePair> createSampleTypeList(boolean inactiveTypes) {
		TypeOfSampleDAO typeOfSampleDAO = new TypeOfSampleDAOImpl();
		List<TypeOfSample> list = typeOfSampleDAO.getTypesForDomainBySortOrder(TypeOfSampleDAO.SampleDomain.HUMAN);

		List<IdValuePair> filteredList = new ArrayList<IdValuePair>();

		for (TypeOfSample type : list) {
			if ((!inactiveTypes && type.isActive()) || (inactiveTypes && !type.isActive()) ) {
				filteredList.add(new IdValuePair(type.getId(), type.getLocalizedName()));
			}
		}

		return filteredList;
	}

	private static List<IdValuePair> createPanelList(boolean inactiveTypes) {
		PanelDAO panelDAO = new PanelDAOImpl();
		List<Panel> list = panelDAO.getAllPanels();
		Collections.sort(list, PanelSortOrderComparator.SORT_ORDER_COMPARATOR);
		List<IdValuePair> filteredList = new ArrayList<IdValuePair>();

		for (Panel panel : list) {
			if ((!inactiveTypes && ("Y").equals(panel.getIsActive())) || (inactiveTypes && !("Y").equals(panel.getIsActive())) ) {
				filteredList.add(new IdValuePair(panel.getId(), panel.getLocalizedName()));
			}
		}

		return filteredList;
	}
		
	private static List<IdValuePair> createHourList() {
		List<IdValuePair> hours = new ArrayList<IdValuePair>();

		for (int i = 0; i < 24; i++) {
			hours.add(new IdValuePair(String.valueOf(i), String.valueOf(i)));
		}

		return hours;
	}

	private static List<IdValuePair> createMinList() {
		List<IdValuePair> minutes = new ArrayList<IdValuePair>();
		minutes.add(new IdValuePair("0", "00"));
		for (int i = 10; i < 60; i = i + 10) {
			minutes.add(new IdValuePair(String.valueOf(i), String.valueOf(i)));
		}
		return minutes;
	}

	@SuppressWarnings("unchecked")
	private static List<IdValuePair> createSortedQAEvents() {
		List<IdValuePair> qaEvents = new ArrayList<IdValuePair>();
		QaEventDAO qaEventDAO = new QaEventDAOImpl();
		List<QaEvent> qaEventList = qaEventDAO.getAllQaEvents();
		
		boolean sortList = ConfigurationProperties.getInstance().isPropertyValueEqual(Property.QA_SORT_EVENT_LIST, "true");
		if( sortList ){
			Collections.sort(qaEventList, new Comparator<QaEvent>() {
				@Override
				public int compare(QaEvent o1, QaEvent o2) {
					return o1.getLocalizedName().compareTo(o2.getLocalizedName());
				}
			});
		}
		
		QaEvent otherQaEvent = null;
		// Put the "Other" type of event at the bottom of the list.
		for (QaEvent event : qaEventList) {
			if ( sortList && "Other".equals(event.getQaEventName())) {
				otherQaEvent = event;
			} else {
				qaEvents.add(new IdValuePair(event.getId(), event.getLocalizedName()));
			}
		}

		if (otherQaEvent != null) {
			qaEvents.add(new IdValuePair(otherQaEvent.getId(), otherQaEvent
					.getLocalizedName()));
		}

		return qaEvents;
	}
	
	private static List<IdValuePair> createTestSectionList() {
		List<IdValuePair> testSectionsPairs = new ArrayList<IdValuePair>();
		List<TestSection> testSections = new TestSectionDAOImpl().getAllActiveTestSections();
		
		for(TestSection section : testSections){
			testSectionsPairs.add(new IdValuePair(section.getId(), section.getLocalizedName()));
		}
		
		return testSectionsPairs;
	}
	
	private static List<IdValuePair> createUnitOfMeasureList() {
		List<IdValuePair> unitOfMeasuresPairs = new ArrayList<IdValuePair>();
		List<UnitOfMeasure> unitOfMeasures = new UnitOfMeasureDAOImpl().getAllActiveUnitOfMeasures();
		
		for(UnitOfMeasure unitOfMeasure : unitOfMeasures){
			unitOfMeasuresPairs.add(new IdValuePair(unitOfMeasure.getId(), unitOfMeasure.getLocalizedName()));
		}
		
		return unitOfMeasuresPairs;
	}

	private static List<IdValuePair> createTypeOfSampleList() {
		List<IdValuePair> typeOfSamplePairs = new ArrayList<IdValuePair>();
		List<TypeOfSample> typeOfSamples = new TypeOfSampleDAOImpl().getAllTypeOfSamplesSortOrdered();
		
		for(TypeOfSample typeOfSample : typeOfSamples){
			typeOfSamplePairs.add(new IdValuePair(typeOfSample.getId(), typeOfSample.getLocalizedName()));
		}
		
		return typeOfSamplePairs;
	}
	
	private static List<IdValuePair> createInactiveTestSection(){
        List<IdValuePair> testSectionsPairs = new ArrayList<IdValuePair>();
        List<TestSection> testSections = new TestSectionDAOImpl().getAllInActiveTestSections();

        for(TestSection section : testSections){
            testSectionsPairs.add(new IdValuePair(section.getId(), section.getLocalizedName()));
        }

        return testSectionsPairs;
    }

	private static List<IdValuePair> createTestSectionByNameList() {
		List<IdValuePair> testSectionsPairs = new ArrayList<IdValuePair>();
		List<TestSection> testSections = new TestSectionDAOImpl().getAllActiveTestSections();
		
		for(TestSection section : testSections){
			testSectionsPairs.add(new IdValuePair(section.getId(), section.getTestSectionName()));
		}
		
		return testSectionsPairs;
	}

	private static List<IdValuePair> createAddressDepartmentList(){
		List<IdValuePair> departmentPairs = new ArrayList<IdValuePair>();
		List<Dictionary> departments = new DictionaryDAOImpl().getDictionaryEntrysByCategoryAbbreviation("description", "haitiDepartment", true);
		
		for(Dictionary dictionary : departments){
			departmentPairs.add(new IdValuePair(dictionary.getId(), dictionary.getDictEntry()));
		}
		
		return departmentPairs;
	}

    private static List<IdValuePair> createPatientSearchCriteria() {
        List<IdValuePair> searchCriteria = new ArrayList<IdValuePair>();

        //N.B.  If the order is to be changed just change the order but keep the id:value pairing the same
        searchCriteria.add(new IdValuePair("0", StringUtil.getMessageForKey( "label.select.search.by" )));
        searchCriteria.add(new IdValuePair("2", "1. " + StringUtil.getMessageForKey( "label.select.last.name" )));
        searchCriteria.add(new IdValuePair("1", "2. " + StringUtil.getMessageForKey("label.select.first.name")));
        searchCriteria.add(new IdValuePair("3", "3. " + StringUtil.getMessageForKey("label.select.last.first.name")));
        searchCriteria.add(new IdValuePair("4", "4. " + StringUtil.getMessageForKey("label.select.patient.ID")));
        searchCriteria.add(new IdValuePair("5", "5. " + StringUtil.getContextualMessageForKey( "quick.entry.accession.number" )));

        return searchCriteria;
    }

}
