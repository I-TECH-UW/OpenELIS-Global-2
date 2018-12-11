package us.mn.state.health.lims.reports.action.implementation;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import us.mn.state.health.lims.common.action.BaseActionForm;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.observationhistory.dao.ObservationHistoryDAO;
import us.mn.state.health.lims.observationhistory.daoimpl.ObservationHistoryDAOImpl;
import us.mn.state.health.lims.observationhistory.valueholder.ObservationHistory;
import us.mn.state.health.lims.observationhistorytype.ObservationHistoryTypeMap;
import us.mn.state.health.lims.patient.valueholder.Patient;
import us.mn.state.health.lims.sample.valueholder.Sample;
import us.mn.state.health.lims.samplehuman.daoimpl.SampleHumanDAOImpl;

public class RetroCIPatientCollectionReport extends CollectionReport implements IReportParameterSetter {

	private ObservationHistoryDAO ohDAO = new ObservationHistoryDAOImpl();
	
	@Override
	public void setRequestParameters(BaseActionForm dynaForm) {
        try {
			PropertyUtils.setProperty(dynaForm, "reportName", StringUtil.getMessageForKey("patient.report.collection.name"));
	        PropertyUtils.setProperty(dynaForm, "usePatientNumberDirect", Boolean.TRUE);
        } catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}


	@Override
	protected List<byte[]> generateReports() {
		List<byte[]> byteList = new ArrayList<byte[]>();
		
		Patient patient = getPatient();
		
		if( patient != null){
			String formNameId = ObservationHistoryTypeMap.getInstance().getIDForType("projectFormName");
			List<Sample> samples = new SampleHumanDAOImpl().getSamplesForPatient(patient.getId());
			
			for( Sample sample : samples){
				List<ObservationHistory> projects = ohDAO.getAll(patient, sample, formNameId);
				
				if( !projects.isEmpty()){
					try {
						PropertyUtils.setProperty(dynaForm, "accessionDirect", sample.getAccessionNumber());
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
		
					if( "InitialARV_Id".equals(projects.get(0).getValue())){
						byteList.add(createReport("patientARVInitial1"));				
						byteList.add(createReport("patientARVInitial2"));
					}else if( "FollowUpARV_Id".equals(projects.get(0).getValue())){
						byteList.add(createReport("patientARVFollowup1"));				
						byteList.add(createReport("patientARVFollowup2"));				
					}
				}
			}
		}
		return byteList;
	}

}
