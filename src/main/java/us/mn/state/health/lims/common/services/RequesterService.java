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

package us.mn.state.health.lims.common.services;

import java.util.List;

import us.mn.state.health.lims.organization.dao.OrganizationDAO;
import us.mn.state.health.lims.organization.daoimpl.OrganizationDAOImpl;
import us.mn.state.health.lims.organization.daoimpl.OrganizationTypeDAOImpl;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.organization.valueholder.OrganizationType;
import us.mn.state.health.lims.person.dao.PersonDAO;
import us.mn.state.health.lims.person.daoimpl.PersonDAOImpl;
import us.mn.state.health.lims.person.valueholder.Person;
import us.mn.state.health.lims.requester.dao.RequesterTypeDAO;
import us.mn.state.health.lims.requester.dao.SampleRequesterDAO;
import us.mn.state.health.lims.requester.daoimpl.RequesterTypeDAOImpl;
import us.mn.state.health.lims.requester.daoimpl.SampleRequesterDAOImpl;
import us.mn.state.health.lims.requester.valueholder.RequesterType;
import us.mn.state.health.lims.requester.valueholder.SampleRequester;

/**
 */
public class RequesterService{
    private static final SampleRequesterDAO sampleRequesterDAO = new SampleRequesterDAOImpl();
    private static final PersonDAO personDAO = new PersonDAOImpl();
    private static final OrganizationDAO organizationDAO = new OrganizationDAOImpl();
    public static final String REFERRAL_ORG_TYPE = "referring clinic";
    public static final String REFERRAL_ORG_TYPE_ID;
    private final String sampleId;
    private List<SampleRequester> requesters;
    private PersonService personService;
    private Organization organization;

    public static enum Requester{
        PERSON,
        ORGANIZATION;

        long id;

        public long getId(){
            return id;
        }

        public void setId( long id ){
            this.id = id;
        }
    }

    static{
        RequesterTypeDAO requesterTypeDAO = new RequesterTypeDAOImpl();
        RequesterType requesterType = requesterTypeDAO.getRequesterTypeByName( "organization" );
        Requester.ORGANIZATION.setId( requesterType != null ? Long.parseLong( requesterType.getId()) : -1L );

        requesterType = requesterTypeDAO.getRequesterTypeByName( "provider" );
        Requester.PERSON.setId( requesterType != null ? Long.parseLong( requesterType.getId() ) : -1L);

        OrganizationType orgType = new OrganizationTypeDAOImpl().getOrganizationTypeByName( REFERRAL_ORG_TYPE );

        REFERRAL_ORG_TYPE_ID = orgType == null ? null : orgType.getId();
    }

    public RequesterService( String sampleId ){
        this.sampleId = sampleId;
    }

    public String getRequesterFirstName(){
        return getPersonService() == null ? null : getPersonService().getFirstName();
    }

    public String getRequesterLastName(){
        return getPersonService() == null ? null : getPersonService().getLastName();
    }

    public String getRequesterLastFirstName(){
        return getPersonService() == null ? null : getPersonService().getLastFirstName();
    }

    public String getWorkPhone(){
        return getPersonService() == null ? null : getPersonService().getWorkPhone();
    }

    public String getCellPhone(){
        return getPersonService() == null ? null : getPersonService().getCellPhone();
    }

    public String getFax(){
        return getPersonService() == null ? null : getPersonService().getFax();
    }

    public String getEmail(){
        return getPersonService() == null ? null : getPersonService().getEmail();
    }

    public String getReferringSiteId(){
        return getOrganization() == null ? null :getOrganization().getId();
    }

    public String getReferringSiteName(){
        return getOrganization() == null ? null : getOrganization().getOrganizationName();
    }
    public String getReferringSiteCode(){
        return getOrganization() == null ? null : getOrganization().getCode();
    }
    public Person getPerson(){
        return getPersonService() == null ? null : getPersonService().getPerson();
    }

    private PersonService getPersonService(){
        if( personService == null ){
            buildRequesters();
        }

        return personService;
    }

    public Organization getOrganization(){
        if( organization == null ){
            buildRequesters();
        }

        return organization;
    }

    public SampleRequester getSampleRequesterByType( Requester type, boolean createIfNotFound ){
        if( requesters == null ){
            buildRequesters();
        }

        for( SampleRequester requester : requesters ){
            if( requester.getRequesterTypeId() == type.getId() ){
                return requester;
            }
        }

        //reachable only if existing requester not found
        if( createIfNotFound ){
            SampleRequester newRequester = new SampleRequester();
            newRequester.setRequesterTypeId( type.getId() );
            newRequester.setSampleId( Long.parseLong( sampleId ) );

            return newRequester;
        }

        return null;
    }

    private void buildRequesters(){
        requesters = sampleRequesterDAO.getRequestersForSampleId( sampleId );
        for( SampleRequester requester : requesters ){
            if( requester.getRequesterTypeId() == Requester.PERSON.getId()){
                 Person person = personDAO.getPersonById( String.valueOf( requester.getRequesterId() ) );
                 personService = new PersonService( person );
            }else if( requester.getRequesterTypeId() == Requester.ORGANIZATION.getId()){
                 organization = organizationDAO.getOrganizationById( String.valueOf( requester.getRequesterId() ) );
            }
        }
    }
}
