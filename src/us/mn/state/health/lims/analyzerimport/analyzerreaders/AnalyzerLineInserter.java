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
* Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
*
* Contributor(s): CIRG, University of Washington, Seattle WA.
*/
package us.mn.state.health.lims.analyzerimport.analyzerreaders;

import java.util.List;

import org.hibernate.Transaction;

import us.mn.state.health.lims.analyzerresults.dao.AnalyzerResultsDAO;
import us.mn.state.health.lims.analyzerresults.daoimpl.AnalyzerResultsDAOImpl;
import us.mn.state.health.lims.analyzerresults.valueholder.AnalyzerResults;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.util.HibernateProxy;

public abstract class AnalyzerLineInserter {
	private static AnalyzerResultsDAO analyzerResultDAO = null;

	protected void persistResults(List<AnalyzerResults> results, String systemUserId) {
		getAnalyzerResultDAO().insertAnalyzerResults(results, systemUserId);
	}

    protected boolean persistImport(String currentUserId, List<AnalyzerResults> results) {

        if (results.size() > 0) {
            for(AnalyzerResults analyzerResults : results ){
                if( analyzerResults.getTestId().equals("-1")){
                    analyzerResults.setTestId(null);
                    analyzerResults.setReadOnly(true);
                }
            }

            Transaction tx = HibernateProxy.beginTransaction();

            try {

                persistResults(results, currentUserId);

                tx.commit();

            } catch (LIMSRuntimeException lre) {
                tx.rollback();
                return false;
            } finally {
                HibernateProxy.closeSession();
            }
        }
        return true;
    }
	private AnalyzerResultsDAO getAnalyzerResultDAO() {
		if( analyzerResultDAO == null){
			analyzerResultDAO = new AnalyzerResultsDAOImpl();
		}
		
		return analyzerResultDAO;
	}

	public static void setAnalyzerResultDAO(AnalyzerResultsDAO analyzerResultDAO) {
		AnalyzerLineInserter.analyzerResultDAO = analyzerResultDAO;
	}
	
	public abstract boolean insert(List<String> lines, String currentUserId);

	public abstract String getError();
	
}
