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
package us.mn.state.health.lims.analyzerresults.dao;

import java.util.List;

import us.mn.state.health.lims.analyzerresults.valueholder.AnalyzerResults;
import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;

public interface AnalyzerResultsDAO extends BaseDAO {

	public List<AnalyzerResults> getResultsbyAnalyzer(String analyzerId) throws LIMSRuntimeException;

	public void insertAnalyzerResults(List<AnalyzerResults> results, String sysUserId) throws LIMSRuntimeException;

	public void updateData(AnalyzerResults results) throws LIMSRuntimeException;

	public void getData(AnalyzerResults results) throws LIMSRuntimeException;

	public AnalyzerResults readAnalyzerResults(String idString);

	public void delete(List<AnalyzerResults> deletableAnalyzerResults) throws LIMSRuntimeException;
}
