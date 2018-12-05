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
* Copyright (C) CIRG, University of Washington, Seattle WA.  All Rights Reserved.
*
*/
package us.mn.state.health.lims.common.util;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.HibernateException;

import us.mn.state.health.lims.audittrail.valueholder.History;
import us.mn.state.health.lims.common.action.BaseAction;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.patient.util.PatientUtil;

public class DeletePatientTestDataAction extends BaseAction {

	protected ActionForward performAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		/*
		 * There is not data access layer for this command.  This is the ONLY way it should be
		 * executed.
		 */

		String sql = "truncate sample_projects, " +
             "sample_human, " +
             "result_inventory, " +
             "result_signature, " +
             "result, " +
             "analysis, " +
             "analyzer_results, " +
             "sample_item, " +
             "observation_history, " +
             "sample, " +
             "provider, " +
             "patient_identity, " +
             "patient_patient_type, " +
             "note, " +
             "sample_requester, " +
             "sample_qaevent, " +
             "referral, " +
             "patient, " +
             "person, " +
             "person_address, " +
             "report_external_export, " +
             "report_external_import, " +
             "document_track, " +
             "qa_observation,"  +
             "electronic_order," +
             "history CASCADE; " +
            "ALTER SEQUENCE note_seq restart 1; " +
            "ALTER SEQUENCE sample_human_seq restart 1; " +
            "ALTER SEQUENCE result_inventory_seq restart 1; " +
            "ALTER SEQUENCE result_signature_seq restart 1; " +
            "ALTER SEQUENCE result_seq restart 1; " +
            "ALTER SEQUENCE analysis_seq restart 1; " +
            "ALTER SEQUENCE sample_item_seq restart 1; " +
            "ALTER SEQUENCE sample_seq restart 1; " +
            "ALTER SEQUENCE provider_seq restart 1; " +
            "ALTER SEQUENCE patient_identity_seq restart 1; " +
            "ALTER SEQUENCE patient_patient_type_seq restart 1; " +
            "ALTER SEQUENCE patient_seq restart 1; " +
            "ALTER SEQUENCE person_seq restart 1; " +
            "ALTER SEQUENCE report_external_import_seq restart 1; " +
            "ALTER SEQUENCE report_queue_seq restart 1; " +
            "ALTER SEQUENCE sample_qaevent_seq restart 1; " +
            "ALTER SEQUENCE sample_proj_seq restart 1; " +
            "ALTER SEQUENCE qa_observation_seq restart 1; " +
            "ALTER SEQUENCE electronic_order_seq restart 1; " +
            "ALTER SEQUENCE history_seq restart 1; ";
		

		boolean fail = HibernateUtil.getSession().connection().prepareStatement(sql).execute();
		if(!fail){
			try {
				History history = new History();
				history.setActivity("T");
				history.setTimestamp( new Timestamp(System.currentTimeMillis()));

				history.setNameKey("Database");
				history.setReferenceId("0");
				history.setReferenceTable("0");
				history.setSysUserId(currentUserId);
				HibernateUtil.getSession().save(history);

				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
				PatientUtil.invalidateUnknownPatients();
			} catch (HibernateException e) {
	        	LogEvent.logError("DeletePatientTestData","performAction()",e.toString());
				throw new LIMSRuntimeException("Error in DeletePatientTestData performAction()", e);
			}
		}

		return mapping.findForward(FWD_SUCCESS);
	}

	@Override
	protected String getPageSubtitleKey() {
		return null;
	}

	@Override
	protected String getPageTitleKey() {
		return null;
	}
}
