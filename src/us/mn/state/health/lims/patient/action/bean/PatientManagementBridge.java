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

package us.mn.state.health.lims.patient.action.bean;

import java.util.List;
import java.util.Map;

import us.mn.state.health.lims.address.dao.AddressPartDAO;
import us.mn.state.health.lims.address.daoimpl.AddressPartDAOImpl;
import us.mn.state.health.lims.address.valueholder.AddressPart;
import us.mn.state.health.lims.common.services.PatientService;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.patient.valueholder.Patient;

/**
 */
public class PatientManagementBridge{

    public static String ADDRESS_PART_VILLAGE_ID;
    public static String ADDRESS_PART_COMMUNE_ID;
    public static String ADDRESS_PART_DEPT_ID;

    static{
        AddressPartDAO addressPartDAO = new AddressPartDAOImpl();
        List<AddressPart> partList = addressPartDAO.getAll();

        for( AddressPart addressPart : partList){
            if( "department".equals(addressPart.getPartName())){
                ADDRESS_PART_DEPT_ID = addressPart.getId();
            }else if( "commune".equals(addressPart.getPartName())){
                ADDRESS_PART_COMMUNE_ID = addressPart.getId();
            }else if( "village".equals(addressPart.getPartName())){
                ADDRESS_PART_VILLAGE_ID = addressPart.getId();
            }
        }
    }

    public PatientManagementInfo getPatientManagementInfoFor( Patient patient, boolean readOnly){
        PatientManagementInfo info = new PatientManagementInfo();
        info.setReadOnly( readOnly );

        if( patient != null){
            PatientService patientService = new PatientService( patient );
            Map<String, String> addressComponents = patientService.getAddressComponents();
            info.setFirstName( patientService.getFirstName() );
            info.setLastName( patientService.getLastName() );
            info.setAddressDepartment( addressComponents.get( PatientService.ADDRESS_DEPT ) );
            info.setCommune( addressComponents.get( PatientService.ADDRESS_COMMUNE ) );
            info.setCity( addressComponents.get( PatientService.ADDRESS_CITY ) );
            info.setStreetAddress( addressComponents.get( PatientService.ADDRESS_STREET ) );
            info.setGender( readOnly ? patientService.getLocalizedGender() : patientService.getGender() );
            info.setBirthDateForDisplay( patientService.getBirthdayForDisplay() );
            info.setNationalId( patientService.getNationalId() );
            info.setSTnumber( patientService.getSTNumber() );
            info.setMothersInitial( patientService.getMothersInitial() );
            if(readOnly){
                info.setAge( DateUtil.getCurrentAgeForDate( DateUtil.convertStringDateStringTimeToTimestamp( patientService.getBirthdayForDisplay(), null ),
                        DateUtil.convertStringDateStringTimeToTimestamp(DateUtil.getCurrentDateAsText(), null )));
            }
        }

        return info;
    }
}
