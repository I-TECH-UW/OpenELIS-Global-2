/*
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
 */

package us.mn.state.health.lims.sample.action.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;

import us.mn.state.health.lims.address.valueholder.OrganizationAddress;
import us.mn.state.health.lims.common.formfields.FormFields;
import us.mn.state.health.lims.common.formfields.FormFields.Field;
import us.mn.state.health.lims.common.provider.validation.IAccessionNumberValidator;
import us.mn.state.health.lims.common.services.ObservationHistoryService;
import us.mn.state.health.lims.common.services.ObservationHistoryService.ObservationType;
import us.mn.state.health.lims.common.services.SampleAddService;
import us.mn.state.health.lims.common.services.SampleAddService.SampleTestCollection;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.services.StatusService.ExternalOrderStatus;
import us.mn.state.health.lims.common.services.StatusService.OrderStatus;
import us.mn.state.health.lims.common.services.TableIdService;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.ConfigurationProperties.Property;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.common.util.validator.ActionError;
import us.mn.state.health.lims.dataexchange.order.dao.ElectronicOrderDAO;
import us.mn.state.health.lims.dataexchange.order.daoimpl.ElectronicOrderDAOImpl;
import us.mn.state.health.lims.dataexchange.order.valueholder.ElectronicOrder;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory.ValueType;
import us.mn.state.health.lims.organization.dao.OrganizationDAO;
import us.mn.state.health.lims.organization.daoimpl.OrganizationDAOImpl;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.patient.util.PatientUtil;
import us.mn.state.health.lims.person.valueholder.Person;
import us.mn.state.health.lims.provider.valueholder.Provider;
import us.mn.state.health.lims.requester.valueholder.SampleRequester;
import us.mn.state.health.lims.sample.bean.SampleOrderItem;
import us.mn.state.health.lims.sample.util.AccessionNumberUtil;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.valueholder.SampleHuman;

/**
 */
public class SamplePatientUpdateData{
    private boolean savePatient = false;
    private Person providerPerson;
    private Provider provider;
    private String patientId;
    private String accessionNumber;

    private Sample sample;
    private SampleHuman sampleHuman = new SampleHuman();
    private SampleRequester requesterSite;
    private List<SampleTestCollection> sampleItemsTests;
    private SampleAddService sampleAddService;
    private ActionMessages patientErrors;
    private Organization newOrganization;
    private Organization currentOrganization;
    private ElectronicOrder electronicOrder = null;

    private boolean useReceiveDateForCollectionDate = !FormFields.getInstance().useField( Field.CollectionDate);
    private String collectionDateFromReceiveDate = null;
    private OrganizationDAO orgDAO = new OrganizationDAOImpl();

    private ElectronicOrderDAO electronicOrderDAO = new ElectronicOrderDAOImpl();
    private List<ObservationHistory> observations  = new ArrayList<ObservationHistory>();
    private List<OrganizationAddress> orgAddressExtra = new ArrayList<OrganizationAddress>();
    private final String currentUserId;

    public SamplePatientUpdateData(String currentUserId){
        this.currentUserId = currentUserId;
    }

    public boolean isSavePatient(){
        return savePatient;
    }

    public void setSavePatient( boolean savePatient ){
        this.savePatient = savePatient;
    }

    public Person getProviderPerson(){
        return providerPerson;
    }

    public void setProviderPerson( Person providerPerson ){
        this.providerPerson = providerPerson;
    }

    public Provider getProvider(){
        return provider;
    }

    public void setProvider( Provider provider ){
        this.provider = provider;
    }

    public String getPatientId(){
        return patientId;
    }

    public void setPatientId( String patientId ){
        this.patientId = patientId;
    }

    public String getAccessionNumber(){
        return accessionNumber;
    }

    public void setAccessionNumber( String accessionNumber ){
        this.accessionNumber = accessionNumber;
    }

    public Sample getSample(){
        return sample;
    }

    public void setSample( Sample sample ){
        this.sample = sample;
    }

    public SampleHuman getSampleHuman(){
        return sampleHuman;
    }

    public void setSampleHuman( SampleHuman sampleHuman ){
        this.sampleHuman = sampleHuman;
    }

    public SampleRequester getRequesterSite(){
        return requesterSite;
    }

    public void setRequesterSite( SampleRequester requesterSite ){
        this.requesterSite = requesterSite;
    }

    public List<SampleTestCollection> getSampleItemsTests(){
        return sampleItemsTests;
    }

    public void setSampleItemsTests( List<SampleTestCollection> sampleItemsTests ){
        this.sampleItemsTests = sampleItemsTests;
    }

    public SampleAddService getSampleAddService(){
        return sampleAddService;
    }

    public void setSampleAddService( SampleAddService sampleAddService ){
        this.sampleAddService = sampleAddService;
    }

    public void setPatientErrors( ActionMessages patientErrors ){
        this.patientErrors = patientErrors;
    }

    public Organization getNewOrganization(){
        return newOrganization;
    }

    public void setNewOrganization( Organization newOrganization ){
        this.newOrganization = newOrganization;
    }

    public Organization getCurrentOrganization(){
        return currentOrganization;
    }

    public ElectronicOrder getElectronicOrder(){
        return electronicOrder;
    }

    public void setCollectionDateFromRecieveDateIfNeeded( String collectionDateFromRecieveDate ){
        if( useReceiveDateForCollectionDate){
            this.collectionDateFromReceiveDate = collectionDateFromRecieveDate;
        }
    }

    public List<ObservationHistory> getObservations(){
        return observations;
    }

    public List<OrganizationAddress> getOrgAddressExtra(){
        return orgAddressExtra;
    }

    public String getCurrentUserId(){
        return currentUserId;
    }

    public void addOrgAddressExtra(String value, String type, String addressPart) {
        if (!GenericValidator.isBlankOrNull( value )) {
            OrganizationAddress orgAddress = new OrganizationAddress();
            orgAddress.setSysUserId(currentUserId);
            orgAddress.setType(type);
            orgAddress.setValue(value);
            orgAddress.setAddressPartId(addressPart);
            orgAddressExtra.add(orgAddress);
        }
    }

    public void createObservation(String observationData, String observationType, ObservationHistory.ValueType valueType) {
        if (!GenericValidator.isBlankOrNull(observationData) && !GenericValidator.isBlankOrNull(observationType)) {
            ObservationHistory observation = new ObservationHistory();
            observation.setObservationHistoryTypeId(observationType);
            observation.setSysUserId(currentUserId);
            observation.setValue(observationData);
            observation.setValueType(valueType);
            observations.add(observation);
        }
    }

    public void validateSample(ActionMessages errors) {
        // assure accession number

        //TODO
        IAccessionNumberValidator.ValidationResults result = AccessionNumberUtil.checkAccessionNumberValidity( accessionNumber, null, null, null );

        if (result != IAccessionNumberValidator.ValidationResults.SUCCESS) {
            String message = AccessionNumberUtil.getInvalidMessage(result);
            errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionError(message));
        }

        // assure that there is at least 1 sample
        if (sampleItemsTests.isEmpty()) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionError("errors.no.sample"));
        }

        // assure that all samples have tests
        if (!allSamplesHaveTests()) {
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionError("errors.samples.with.no.tests"));
        }

        // check patient errors
        if (patientErrors.size(ActionErrors.GLOBAL_MESSAGE) > 0) {
            errors.add(patientErrors);
        }
    }

    private boolean allSamplesHaveTests() {

        for (SampleTestCollection sampleTest : sampleItemsTests) {
            if (sampleTest.tests.size() == 0) {
                return false;
            }
        }

        return true;
    }

    public void createPopulatedSample( String receivedDate, SampleOrderItem sampleOrder) {
        sample = new Sample();
        sample.setSysUserId(currentUserId);
        sample.setAccessionNumber(accessionNumber);

        sample.setEnteredDate( DateUtil.getNowAsSqlDate());

        sample.setReceivedTimestamp(DateUtil.convertStringDateToTimestamp(receivedDate));
        sample.setReferringId( sampleOrder.getRequesterSampleID() );

        if (useReceiveDateForCollectionDate) {
            sample.setCollectionDateForDisplay( collectionDateFromReceiveDate );
        }

        sample.setDomain( SystemConfiguration.getInstance().getHumanDomain());
        sample.setStatusId( StatusService.getInstance().getStatusID(OrderStatus.Entered));

        setElectronicOrderIfNeeded( sampleOrder );
    }

    private void setElectronicOrderIfNeeded( SampleOrderItem sampleOrder ){
        electronicOrder = null;
        String externalOrderNumber =  sampleOrder.getExternalOrderNumber();
        if( !GenericValidator.isBlankOrNull(externalOrderNumber)){
            List<ElectronicOrder> orders = electronicOrderDAO.getElectronicOrdersByExternalId(externalOrderNumber);
            if( !orders.isEmpty()){
                electronicOrder = orders.get(orders.size() - 1);
                electronicOrder.setStatusId(StatusService.getInstance().getStatusID(ExternalOrderStatus.Realized));
                electronicOrder.setSysUserId(currentUserId);

                sample.setReferringId(externalOrderNumber);
                sample.setClinicalOrderId(electronicOrder.getId());
            }
        }
    }

    public void initProvider(SampleOrderItem sampleOrder) {

        providerPerson = null;
        if (noRequesterInformation(sampleOrder)){
            provider = PatientUtil.getUnownProvider();
        } else {
            providerPerson = new Person();
            provider = new Provider();

            providerPerson.setFirstName(sampleOrder.getProviderFirstName());
            providerPerson.setLastName(sampleOrder.getProviderLastName());
            providerPerson.setWorkPhone(sampleOrder.getProviderWorkPhone());
            providerPerson.setFax(sampleOrder.getProviderFax());
            providerPerson.setEmail(sampleOrder.getProviderEmail());
            providerPerson.setSysUserId(currentUserId);
            provider.setExternalId(sampleOrder.getRequesterSampleID());
        }

        provider.setSysUserId(currentUserId);
    }

    private boolean noRequesterInformation( SampleOrderItem sampleOrder){
        return (GenericValidator.isBlankOrNull(sampleOrder.getProviderFirstName()) &&
                GenericValidator.isBlankOrNull(sampleOrder.getProviderWorkPhone()) &&
                GenericValidator.isBlankOrNull(sampleOrder.getProviderLastName()) &&
                GenericValidator.isBlankOrNull(sampleOrder.getRequesterSampleID()) &&
                GenericValidator.isBlankOrNull(sampleOrder.getProviderFax()) &&
                GenericValidator.isBlankOrNull(sampleOrder.getProviderEmail()));
    }

    public void buildSampleHuman(){
        sampleHuman.setSysUserId(currentUserId);
        sampleHuman.setSampleId(sample.getId());
        sampleHuman.setPatientId(patientId);
        if (provider != null) {
            sampleHuman.setProviderId(provider.getId());
        }
    }

    public void initializeNewOrganization( SampleOrderItem orderItem ){
        newOrganization.setCode(orderItem.getReferringSiteCode());

        newOrganization.setIsActive("Y");
        newOrganization.setOrganizationName(orderItem.getNewRequesterName());

        // this was left as a warning for copy and paste -- it causes a null
        // pointer exception in session.flush()
        // newOrganization.setOrganizationTypes(ORG_TYPE_SET);
        newOrganization.setSysUserId(currentUserId);
        newOrganization.setMlsSentinelLabFlag("N");
    }

    public void updateCurrentOrgIfNeeded(String code, String orgId){
        currentOrganization = orgDAO.getOrganizationById(orgId);
        if( StringUtil.compareWithNulls( code, currentOrganization.getCode() ) != 0){
            currentOrganization.setCode(code);
            currentOrganization.setSysUserId(currentUserId);
        }else{
            currentOrganization = null;
        }
    }


    public void initializeRequester( SampleOrderItem sampleOrder ){
        if ( FormFields.getInstance().useField( Field.RequesterSiteList )) {
            setRequesterSite( initSampleRequester( sampleOrder ) );
        }
    }

    private SampleRequester initSampleRequester( SampleOrderItem orderItem ) {
        SampleRequester requester = null;

        String orgId = orderItem.getReferringSiteId();

        if (!GenericValidator.isBlankOrNull(orgId)) {
            requester = createSiteRequester(orgId);
            if( FormFields.getInstance().useField(Field.SampleEntryReferralSiteCode)){
                updateCurrentOrgIfNeeded( orderItem.getReferringSiteCode(), orgId );
            }

        } else if (!GenericValidator.isBlankOrNull(orderItem.getNewRequesterName())) {
          
          if (confirmNewRequesterName(orderItem.getNewRequesterName())) {
          //will be corrected after newOrg is persisted
            requester = createSiteRequester("0");

            setNewOrganization( new Organization() );

            if (FormFields.getInstance().useField(Field.SampleEntryHealthFacilityAddress)) {
                addOrgAddressExtra( orderItem.getFacilityPhone(), "T", TableIdService.ADDRESS_PHONE_ID );
                addOrgAddressExtra( orderItem.getFacilityFax(), "T", TableIdService.ADDRESS_FAX_ID );
                addOrgAddressExtra( orderItem.getFacilityAddressCommune(), "T", TableIdService.ADDRESS_COMMUNE_ID );
                addOrgAddressExtra( orderItem.getFacilityAddressStreet(), "T", TableIdService.ADDRESS_STREET_ID );
            }

            initializeNewOrganization( orderItem );
          } else {
            Organization organization = new Organization();
            organization.setOrganizationName(orderItem.getNewRequesterName());
            organization = orgDAO.getOrganizationByName(organization, true);
            orgId = organization.getId();

            if (!GenericValidator.isBlankOrNull(orgId)) {
                requester = createSiteRequester(orgId);
            } 
          }
        }

        return requester;
    }

    /**
     * Check if new requester name is actually a new name
     * @param requesterName   The name to check 
     * @return                if the name is not stored in the database
     */
    private boolean confirmNewRequesterName(String requesterName) {
      boolean newName = true;
      Organization organization = new Organization();
      organization.setOrganizationName(requesterName);
      organization = orgDAO.getOrganizationByName(organization, true);

      if (organization == null) {
        newName = true;
      } else {
        newName = false;
      }
      return newName;
    }

    private SampleRequester createSiteRequester(String orgId) {
        SampleRequester requester;
        requester = new SampleRequester();
        requester.setRequesterId(orgId);
        requester.setRequesterTypeId(TableIdService.ORGANIZATION_REQUESTER_TYPE_ID);
        requester.setSysUserId(currentUserId);
        return requester;
    }

    public void initSampleData(String sampleXML, String receivedDate, boolean trackPayments, SampleOrderItem sampleOrder
    ) {
        createPopulatedSample( receivedDate, sampleOrder );

        addObservations(sampleOrder, trackPayments);

        SampleAddService sampleAddService = new SampleAddService(sampleXML, currentUserId, getSample(), receivedDate);
        setSampleItemsTests( sampleAddService.createSampleTestCollection() );
        setSampleAddService( sampleAddService );
    }

    private void addObservations( SampleOrderItem sampleOrder, boolean trackPayments ) {
        if (trackPayments) {
            createObservation( sampleOrder.getPaymentOptionSelection(), ObservationHistoryService.getObservationTypeIdForType( ObservationType.PAYMENT_STATUS ), ValueType.DICTIONARY );
        }

        createObservation( sampleOrder.getRequestDate(), ObservationHistoryService.getObservationTypeIdForType( ObservationType.REQUEST_DATE ), ValueType.LITERAL );
        createObservation( sampleOrder.getNextVisitDate(), ObservationHistoryService.getObservationTypeIdForType( ObservationType.NEXT_VISIT_DATE ), ValueType.LITERAL );
        createObservation( sampleOrder.getTestLocationCode(), ObservationHistoryService.getObservationTypeIdForType( ObservationType.TEST_LOCATION_CODE ), ValueType.DICTIONARY );
        createObservation( sampleOrder.getOtherLocationCode(), ObservationHistoryService.getObservationTypeIdForType( ObservationType.TEST_LOCATION_CODE_OTHER ), ValueType.LITERAL );
        createObservation( sampleOrder.getReferringPatientNumber(), ObservationHistoryService.getObservationTypeIdForType( ObservationType.REFERRERS_PATIENT_ID ), ValueType.LITERAL );
        if( ConfigurationProperties.getInstance().isPropertyValueEqual( Property.USE_BILLING_REFERENCE_NUMBER, "true" )){
            createObservation( sampleOrder.getBillingReferenceNumber(), ObservationHistoryService.getObservationTypeIdForType( ObservationType.BILLING_REFERENCE_NUMBER ), ValueType.LITERAL );
        }
        if( ConfigurationProperties.getInstance().isPropertyValueEqual( Property.ORDER_PROGRAM, "true" )){
            createObservation( sampleOrder.getProgram(), ObservationHistoryService.getObservationTypeIdForType( ObservationType.PROGRAM ), ValueType.DICTIONARY );
        }
    }


}
