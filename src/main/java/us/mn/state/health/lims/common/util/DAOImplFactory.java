package us.mn.state.health.lims.common.util;

import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.referencetables.dao.ReferenceTablesDAO;
import us.mn.state.health.lims.referencetables.daoimpl.ReferenceTablesDAOImpl;
import us.mn.state.health.lims.result.dao.ResultDAO;
import us.mn.state.health.lims.result.daoimpl.ResultDAOImpl;
import us.mn.state.health.lims.resultlimits.dao.ResultLimitDAO;
import us.mn.state.health.lims.resultlimits.daoimpl.ResultLimitDAOImpl;
import us.mn.state.health.lims.sample.dao.SampleDAO;
import us.mn.state.health.lims.sample.daoimpl.SampleDAOImpl;
import us.mn.state.health.lims.sampleitem.dao.SampleItemDAO;
import us.mn.state.health.lims.sampleitem.daoimpl.SampleItemDAOImpl;
import us.mn.state.health.lims.test.dao.TestDAO;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;

/*
 * The purpose of this factory is to facilitate unit testing which is
 * dependent on database contents.  For every DAO object there should be
 * a getter and a setter.  Unless the setter is called the default
 * should be the object accessing the database
 *
 */

public class DAOImplFactory {

	private static final DAOImplFactory instance = new DAOImplFactory();
	private ReferenceTablesDAO referenceTablesDAOImpl = null;
	private SampleItemDAO sampleItemDAOImpl = null;
	private AnalysisDAO analysisDAOImpl = null;
	private ResultDAO resultDAOImpl = null;
	private ResultLimitDAO resultLimitsDAOImpl = null;
	private TestDAO testDAOImpl = null;
	private SampleDAO sampleDAOImpl = null;

	public static final DAOImplFactory getInstance(){
		return instance;
	}

	public void revertAll() {
		referenceTablesDAOImpl = null;
		sampleItemDAOImpl = null;
		analysisDAOImpl = null;
		resultDAOImpl = null;
		resultLimitsDAOImpl = null;
		testDAOImpl = null;
		sampleDAOImpl = null;
	}

	public void setReferenceTablesDAOImpl(ReferenceTablesDAO referenceTablesDAOImpl) {
		this.referenceTablesDAOImpl = referenceTablesDAOImpl;
	}


	public ReferenceTablesDAO getReferenceTablesDAOImpl() {
		if( referenceTablesDAOImpl == null){
			referenceTablesDAOImpl = new ReferenceTablesDAOImpl();
		}

		return referenceTablesDAOImpl;
	}


	public void setSampleItemDAOImpl(SampleItemDAO sampleItemDAO) {
		this.sampleItemDAOImpl = sampleItemDAO;
	}


	public SampleItemDAO getSampleItemDAOImpl() {
		if( sampleItemDAOImpl == null){
			sampleItemDAOImpl = new SampleItemDAOImpl();
		}

		return sampleItemDAOImpl;
	}

	public void setAnalysisDAOImpl(AnalysisDAO analysisDAOImpl) {
		this.analysisDAOImpl = analysisDAOImpl;
	}


	public AnalysisDAO getAnalysisDAOImpl() {
		if( analysisDAOImpl == null){
			analysisDAOImpl = new AnalysisDAOImpl();
		}

		return analysisDAOImpl;
	}

	public void setResultDAOImpl(ResultDAO resultDAOImpl) {
		this.resultDAOImpl = resultDAOImpl;
	}

	public ResultDAO getResultDAOImpl() {
		if( resultDAOImpl == null){
			resultDAOImpl = new ResultDAOImpl();
		}

		return resultDAOImpl;
	}


	public void setResultLimitsDAOImpl(ResultLimitDAO resultLimitsDAOImpl) {
		this.resultLimitsDAOImpl = resultLimitsDAOImpl;
	}

	public ResultLimitDAO getResultLimitsDAOImpl() {
		if( resultLimitsDAOImpl == null){
			resultLimitsDAOImpl = new ResultLimitDAOImpl();
		}
		return resultLimitsDAOImpl;
	}

	public TestDAO getTestDAOImpl() {
		if( testDAOImpl == null){
			testDAOImpl = new TestDAOImpl();
		}
		return testDAOImpl;
	}

	public void setTestDAOImpl(TestDAO testDAOImpl) {
		this.testDAOImpl = testDAOImpl;
	}

	public SampleDAO getSampleDAOImpl() {
		if( sampleDAOImpl == null){
			sampleDAOImpl = new SampleDAOImpl();
		}
		
		return sampleDAOImpl;
	}

	public void setSampleDAOImp(SampleDAO sampleDAOImp) {
		this.sampleDAOImpl = sampleDAOImp;
	}
}
