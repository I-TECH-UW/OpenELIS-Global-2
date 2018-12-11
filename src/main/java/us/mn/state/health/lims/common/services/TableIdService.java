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

import us.mn.state.health.lims.address.dao.AddressPartDAO;
import us.mn.state.health.lims.address.daoimpl.AddressPartDAOImpl;
import us.mn.state.health.lims.address.valueholder.AddressPart;
import us.mn.state.health.lims.observationhistorytype.dao.ObservationHistoryTypeDAO;
import us.mn.state.health.lims.observationhistorytype.daoImpl.ObservationHistoryTypeDAOImpl;
import us.mn.state.health.lims.observationhistorytype.valueholder.ObservationHistoryType;
import us.mn.state.health.lims.organization.daoimpl.OrganizationTypeDAOImpl;
import us.mn.state.health.lims.organization.valueholder.OrganizationType;
import us.mn.state.health.lims.patientidentitytype.daoimpl.PatientIdentityTypeDAOImpl;
import us.mn.state.health.lims.patientidentitytype.valueholder.PatientIdentityType;
import us.mn.state.health.lims.requester.dao.RequesterTypeDAO;
import us.mn.state.health.lims.requester.daoimpl.RequesterTypeDAOImpl;
import us.mn.state.health.lims.requester.valueholder.RequesterType;

/**
 */
public class TableIdService{

    //address parts
    public static final String ADDRESS_COMMUNE_ID;
    public static final String ADDRESS_FAX_ID;
    public static final String ADDRESS_PHONE_ID;
    public static final String ADDRESS_STREET_ID;
    public static final String ADDRESS_VILLAGE_ID;
    public static final String ADDRESS_DEPARTMENT_ID;

    //requester type
    public static  long ORGANIZATION_REQUESTER_TYPE_ID;
    public static long PROVIDER_REQUESTER_TYPE_ID;

    //organization type
    public static final String REFERRING_ORG_TYPE_ID;

    //Observations types
    public static String DOCTOR_OBSERVATION_TYPE_ID;
    public static String SERVICE_OBSERVATION_TYPE_ID;

    //Patient identity
    public static final String PATIENT_SUBJECT_IDENTITY;
    public static final String PATIENT_ST_IDENTITY;






    private static ObservationHistoryTypeDAO ohtDAO = new ObservationHistoryTypeDAOImpl();

    static{
        RequesterTypeDAO requesterTypeDAO = new RequesterTypeDAOImpl();
        RequesterType type = requesterTypeDAO.getRequesterTypeByName("organization");
        if (type != null) {
            ORGANIZATION_REQUESTER_TYPE_ID = Long.parseLong(type.getId());
        }
         type = requesterTypeDAO.getRequesterTypeByName("provider");
        if (type != null) {
            PROVIDER_REQUESTER_TYPE_ID = Long.parseLong(type.getId());
        }


        OrganizationType orgType = new OrganizationTypeDAOImpl().getOrganizationTypeByName("referring clinic");
        REFERRING_ORG_TYPE_ID = orgType != null ? orgType.getId() : "";


        AddressPartDAO partDAO = new AddressPartDAOImpl();
        AddressPart part = partDAO.getAddresPartByName("commune");
        ADDRESS_COMMUNE_ID = part == null ? "" : part.getId();

        part = partDAO.getAddresPartByName("village");
        ADDRESS_VILLAGE_ID = part == null ? "" : part.getId();

        part = partDAO.getAddresPartByName("department");
        ADDRESS_DEPARTMENT_ID = part == null ? "" : part.getId();

        part = partDAO.getAddresPartByName("fax");
        ADDRESS_FAX_ID  = part == null ? "" : part.getId();

        part = partDAO.getAddresPartByName("phone");
        ADDRESS_PHONE_ID  = part == null ? "" : part.getId();

        part = partDAO.getAddresPartByName("street");
        ADDRESS_STREET_ID  = part == null ? "" : part.getId();

        PatientIdentityType patientType = new PatientIdentityTypeDAOImpl().getNamedIdentityType("SUBJECT");
        PATIENT_SUBJECT_IDENTITY = patientType != null ? patientType.getId() : "";

        patientType = new PatientIdentityTypeDAOImpl().getNamedIdentityType("ST");
        PATIENT_ST_IDENTITY = patientType != null ? patientType.getId() : "";


        DOCTOR_OBSERVATION_TYPE_ID = getOHTypeIdByName("nameOfDoctor");
        SERVICE_OBSERVATION_TYPE_ID = getOHTypeIdByName("service");

    }

    private static final String getOHTypeIdByName(String name){
        ObservationHistoryType oht = ohtDAO.getByName(name);
        return (oht == null) ? null : oht.getId();
    }

}
