/**
 * Project : LIS<br>
 * File name : PatientTypeMenuAction.java<br>
 * Description : 
 * @author TienDH
 * @date Aug 20, 2007
 */
package us.mn.state.health.lims.patienttype.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import us.mn.state.health.lims.common.action.BaseMenuAction;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.patienttype.dao.PatientTypeDAO;
import us.mn.state.health.lims.patienttype.daoimpl.PatientTypeDAOImpl;

public class PatientTypeMenuAction extends BaseMenuAction {

	protected List createMenuList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {


		List patientType = new ArrayList();

		String stringStartingRecNo = (String) request.getAttribute("startingRecNo");
		int startingRecNo = Integer.parseInt(stringStartingRecNo);

		PatientTypeDAO patientTypeDAO = new PatientTypeDAOImpl();

		patientType = patientTypeDAO.getPageOfPatientType(startingRecNo);
		
		request.setAttribute("menuDefinition", "PatientTypeMenuDefinition");

		request.setAttribute(MENU_TOTAL_RECORDS, String.valueOf(patientTypeDAO.getTotalPatientTypeCount()));
		request.setAttribute(MENU_FROM_RECORD, String.valueOf(startingRecNo));
		
		int numOfRecs = 0;
		
		if (patientType != null) {
			if (patientType.size() > SystemConfiguration.getInstance()
					.getDefaultPageSize()) {
				numOfRecs = SystemConfiguration.getInstance()
						.getDefaultPageSize();
			} else {
				numOfRecs = patientType.size();
			}
			numOfRecs--;
		}
		int endingRecNo = startingRecNo + numOfRecs;
		
		request.setAttribute(MENU_TO_RECORD, String.valueOf(endingRecNo));
		
		return patientType;
	}

	protected String getPageTitleKey() {
		return "patienttype.browse.title";
	}

	protected String getPageSubtitleKey() {
		return "patienttype.browse.title";
	}

	protected int getPageSize() {
		return SystemConfiguration.getInstance().getDefaultPageSize();
	}

	protected String getDeactivateDisabled() {
		return TRUE;
	}
}
