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

import spring.service.address.AddressPartService;
import spring.service.patient.PatientService;
import spring.service.patient.PatientServiceImpl;
import spring.util.SpringContext;
import us.mn.state.health.lims.address.valueholder.AddressPart;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.patient.valueholder.Patient;

/**
 */
public class PatientManagementBridge {

	public String ADDRESS_PART_VILLAGE_ID;
	public String ADDRESS_PART_COMMUNE_ID;
	public String ADDRESS_PART_DEPT_ID;

	AddressPartService addressPartService = SpringContext.getBean(AddressPartService.class);

	public PatientManagementBridge() {
		List<AddressPart> partList = addressPartService.getAll();

		for (AddressPart addressPart : partList) {
			if ("department".equals(addressPart.getPartName())) {
				ADDRESS_PART_DEPT_ID = addressPart.getId();
			} else if ("commune".equals(addressPart.getPartName())) {
				ADDRESS_PART_COMMUNE_ID = addressPart.getId();
			} else if ("village".equals(addressPart.getPartName())) {
				ADDRESS_PART_VILLAGE_ID = addressPart.getId();
			}
		}
	}

	public PatientManagementInfo getPatientManagementInfoFor(Patient patient, boolean readOnly) {
		PatientManagementInfo info = new PatientManagementInfo();
		info.setReadOnly(readOnly);

		if (patient != null) {
			PatientService patientPatientService = SpringContext.getBean(PatientService.class);
			patientPatientService.setPatient(patient);
			Map<String, String> addressComponents = patientPatientService.getAddressComponents();
			info.setFirstName(patientPatientService.getFirstName());
			info.setLastName(patientPatientService.getLastName());
			info.setAddressDepartment(addressComponents.get(PatientServiceImpl.ADDRESS_DEPT));
			info.setCommune(addressComponents.get(PatientServiceImpl.ADDRESS_COMMUNE));
			info.setCity(addressComponents.get(PatientServiceImpl.ADDRESS_CITY));
			info.setStreetAddress(addressComponents.get(PatientServiceImpl.ADDRESS_STREET));
			info.setGender(readOnly ? patientPatientService.getLocalizedGender() : patientPatientService.getGender());
			info.setBirthDateForDisplay(patientPatientService.getBirthdayForDisplay());
			info.setNationalId(patientPatientService.getNationalId());
			info.setSTnumber(patientPatientService.getSTNumber());
			info.setMothersInitial(patientPatientService.getMothersInitial());
			if (readOnly) {
				info.setAge(DateUtil.getCurrentAgeForDate(
						DateUtil.convertStringDateStringTimeToTimestamp(patientPatientService.getBirthdayForDisplay(), null),
						DateUtil.convertStringDateStringTimeToTimestamp(DateUtil.getCurrentDateAsText(), null)));
			}
		}

		return info;
	}
}
