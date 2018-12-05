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
package us.mn.state.health.lims.analyzerimport.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import us.mn.state.health.lims.analyzer.dao.AnalyzerDAO;
import us.mn.state.health.lims.analyzer.daoimpl.AnalyzerDAOImpl;
import us.mn.state.health.lims.analyzer.valueholder.Analyzer;
import us.mn.state.health.lims.analyzerimport.analyzerreaders.AnalyzerLineInserter;
import us.mn.state.health.lims.analyzerimport.analyzerreaders.AnalyzerReaderUtil;
import us.mn.state.health.lims.analyzerresults.valueholder.AnalyzerResults;
import us.mn.state.health.lims.common.services.SampleService;
import us.mn.state.health.lims.common.services.StatusService;
import us.mn.state.health.lims.common.util.DateUtil;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.test.daoimpl.TestDAOImpl;
import us.mn.state.health.lims.test.valueholder.Test;
import us.mn.state.health.lims.analysis.dao.AnalysisDAO;
import us.mn.state.health.lims.analysis.daoimpl.AnalysisDAOImpl;
import us.mn.state.health.lims.analysis.valueholder.Analysis;




public class Cobas6800VLAnalyzerImplementation extends AnalyzerLineInserter
{
  private static final String UNDER_THREASHOLD = "< LL";
  private static final double THREASHOLD = 20.0;
  
  private static final String ABOVE_SUPLOG = "> Log7";
  private static final String SUPLOG = ">1.1E7";

  private static String RESULT_FLAG = "OBX";
  private static String VL_FLAG = "Viral Load";

  private static final String DATE_PATTERN = "yyyy/MM/dd HH:mm:ss";
  static String ANALYZER_ID ;
  private final String projectCode = StringUtil.getMessageForKey("sample.entry.project.LART");

  static HashMap<String, Test> testHeaderNameMap = new HashMap<String, Test>();
  HashMap<String, String> indexTestMap = new HashMap<String, String>();
  static HashMap<String, String> unitsIndexMap = new HashMap<String, String>();
	
  private AnalyzerReaderUtil readerUtil = new AnalyzerReaderUtil();
  private String error;
  Test test = (Test)new TestDAOImpl().getActiveTestByName("Viral Load").get(0);
  String validStatusId = StatusService.getInstance().getStatusID(StatusService.AnalysisStatus.Finalized);
  AnalysisDAO analysisDao =  new AnalysisDAOImpl();

  static
  {
	testHeaderNameMap.put("Viral Load", new TestDAOImpl().getActiveTestByName("Viral Load").get(0));//.getTestByGUID("0e240569-c095-41c7-bfd2-049527452f16"));
	testHeaderNameMap.put("DNA PCR", new TestDAOImpl().getActiveTestByName("DNA PCR").get(0));//.getTestByGUID("fe6405c8-f96b-491b-95c9-b1f635339d6a"));
	
	unitsIndexMap.put("CD4", "");
	unitsIndexMap.put("%CD4", "%");
			
    AnalyzerDAO analyzerDAO = new AnalyzerDAOImpl();
    Analyzer analyzer = analyzerDAO.getAnalyzerByName("Cobas6800VLAnalyzer");
    ANALYZER_ID =analyzer.getId();
  }

  public String getError() {
    return this.error;
  }

  private void addValueToResults(List<AnalyzerResults> resultList, AnalyzerResults result)  {
	if (result.getIsControl()){
		resultList.add(result);
		return;
	}
	SampleService sampleServ=new SampleService(result.getAccessionNumber());
	if (!result.getAccessionNumber().startsWith(projectCode) || sampleServ.getSample()==null )
		return;
			
	List<Analysis> analyses=analysisDao.getAnalysisByAccessionAndTestId(result.getAccessionNumber(), result.getTestId());
	for(Analysis analysis :analyses) {
		if(analysis.getStatusId().equals(validStatusId))
			return;
			
	}
    resultList.add(result);

    AnalyzerResults resultFromDB = this.readerUtil.createAnalyzerResultFromDB(result);
    if (resultFromDB != null)
      resultList.add(resultFromDB);
  }

  private String getAppropriateResults(String result){
		result = result.replace("\"", "").trim();
		if(result.contains(SUPLOG)){
			result = ABOVE_SUPLOG;
		}else if("Target N".equalsIgnoreCase(result) || "<20".equalsIgnoreCase( result )){
			result = UNDER_THREASHOLD;
		}else{

			try{
				Double resultAsDouble = Double.parseDouble(result);//Double.parseDouble(splitResult[0]) * Math.pow(10, Double.parseDouble(splitResult[1]));

				if(resultAsDouble <= THREASHOLD){
					result = UNDER_THREASHOLD;
				}else{
					result = String.valueOf((int)(Math.round(resultAsDouble))) ;//String.valueOf((int)(Math.round(resultAsDouble))) + result.substring(result.indexOf("("));
					result=result+"("+String.format("%.3g%n", Math.log10(resultAsDouble));
					result=result+")";
				}
			}catch(NumberFormatException e){
				return "XXXX";
			}
		}

		return result;
	}
  public void ordersExport(List<AnalyzerResults> results) {
	Connection c = null;
    Statement stmt = null;
    try {
       Class.forName("org.postgresql.Driver");
       c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/clinlims", "clinlims", "clinlims");
       c.setAutoCommit(false);
       System.out.println("Opened database successfully");

       String sql="SELECT s.accession_number, a.test_id,pat.national_id,pat.external_id,pat.gender,pat.birth_date "+
    		   "FROM clinlims.sample s,clinlims.sample_item si,clinlims.analysis a,clinlims.sample_human sh,clinlims.patient pat "+
    		   "WHERE  a.status_id=13  AND a.test_id IN ("+test.getId()+") AND "+
    		   "a.sampitem_id=si.id AND "+
    		   "si.samp_id=s.id AND "+
    		   "sh.samp_id=s.id AND "+
    		   "sh.patient_id=pat.id "+
    		   "ORDER BY 1";
       stmt = c.createStatement();
       ResultSet rs = stmt.executeQuery( sql );
       
       //String osname = System.getProperty("os.name", "").toLowerCase();
       //if (osname.startsWith("windows"))
       //else if (osname.startsWith("linux"))
       /////////
       //Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:/Temp/RetroCIPRESC.AST"), StandardCharsets.UTF_8)); 
      // Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/media/windowsshare/ARVPRESC.AST"), StandardCharsets.UTF_8)); 
      // Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/home/oeserver/Desktop/PRESCRIPTIONS.AST"), StandardCharsets.UTF_8)); 
         FileWriter writer = new FileWriter("/home/oeserver/Desktop/Prescriptions/ARVPRESC.AST", false);
      //FileWriter writer = new FileWriter("C:/Temp/RetroCIPRESC.AST", false);
       
       
     /*  String home = System.getProperty("user.home");

       File data_directory = new File(home, ".my_app_data");
       data_directory.mkdir();

       File log_file = new File(data_directory, "ARVPRESC.AST");
       try {
           log_file.createNewFile();
       } catch (IOException e) {
           // handle error
       }
       
       BufferedWriter writer = new BufferedWriter(new FileWriter(log_file));*/

       writer.write("H|^~\\&|||GLIMS||ORM|||MPL|||A2.2|200712120754|");writer.write("\r\n");
       int inc=0;		
       while ( rs.next() ) {
    	inc++;
        String  labno = rs.getString("accession_number");
        String  sujetno = rs.getString("national_id");
        String  external_id = rs.getString("external_id");
        String  sexe = rs.getString("gender");
        String  birth_date = rs.getString("birth_date");
        String patID=sujetno==null?external_id:sujetno;
        
        writer.write("P|"+inc+"|"+labno+"|||Patient-XXXXX||"+birth_date.substring(0, 10).replace("-", "")+"|"+sexe+"||||||||||||||||||");writer.write("\r\n");
        writer.write("OBR|1|"+labno+"||Viral Load|R|||||||||||||"+patID+"||||||||||");writer.write("\r\n");
        
        
       }
       writer.write("L|1|");
       rs.close();
       stmt.close();
       c.close();
       writer.close();
    } catch ( Exception e ) {
       System.err.println( e.getClass().getName()+": "+ e.getMessage() );
       //System.exit(0);
    }
    System.out.println("Operation done successfully");//	Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);  

    
   }
   public boolean  filterOrdersExport(List<AnalyzerResults> results,String labno) {
	   for(AnalyzerResults ar :results){
		   if (ar.getAccessionNumber().equalsIgnoreCase(labno))
			   return true;
	   }
	   return false;
	   
   }

public int getColumnsLine(List<String> lines) {
	  for (int k = 0; k < lines.size(); k++) {
	    if (lines.get(k).contains("Patient Name") && 
	      lines.get(k).contains("Patient ID") && 
	      lines.get(k).contains("Order Number") && 
	      lines.get(k).contains("Sample ID") && 
	      lines.get(k).contains("Test") && 
	      lines.get(k).contains("Result"))
	    
	      return k;
	    
       }

	  return -1;
   }

public boolean insert(List<String> lines, String currentUserId) {
    List<AnalyzerResults> results = new ArrayList<AnalyzerResults>();
    for (Entry<Integer, Integer> entry : getResultsLines(lines, RESULT_FLAG,VL_FLAG).entrySet()) {
      createVLResultFromEntry(lines,entry, results);
    }
     Collections.sort(results, new Comparator<AnalyzerResults>(){
		@Override
		public int compare(AnalyzerResults o1, AnalyzerResults o2) {
			return o1.getAccessionNumber().compareTo(o2.getAccessionNumber());
	}});
  //  ordersExport(results);
    return persistImport(currentUserId, results);
  }

public HashMap<Integer, Integer> getResultsLines(List<String> lines, String RESULT_FLAG,String TEST_FLAG) {
	HashMap<Integer, Integer> IdValuePair = new HashMap<Integer, Integer>();

	for(int i=0;i<lines.size();i++){
			
		if(lines.get(i).startsWith("P|")){
			int j=i;
			while(!(lines.get(j).contains(RESULT_FLAG) && lines.get(j).contains(TEST_FLAG))){
				j=j+1;
			}
			IdValuePair.put(i, j);
		}

	}
	
	return IdValuePair.size()==0 ? null : IdValuePair ;
  }

public void createVLResultFromEntry(List<String> lines,Entry<Integer, Integer> entry,List<AnalyzerResults> resultList){
	
	AnalyzerResults analyzerResults = new AnalyzerResults();
	//LABNO processing
    String line=lines.get(entry.getKey());
    for(int i=1;i<=2;i++){
    line=line.substring(1+line.indexOf("|"));
  //  line=line.substring(1+line.indexOf("|"));
    }
    String accessionNumber=line.substring(0, line.indexOf("|"));
    accessionNumber=accessionNumber.trim();
    accessionNumber=accessionNumber.replace(" ", "");
    if(accessionNumber.startsWith(projectCode) && accessionNumber.length()>=9)
    	accessionNumber=accessionNumber.substring(0, 9);
      
    //RESULT processing
    line=lines.get(entry.getValue());
    for(int i=1;i<=5;i++){
    line=line.substring(1+line.indexOf("|"));

    }
    String result=line.substring(0, line.indexOf("|"));
    result = getAppropriateResults(result);
  //COMPLETED_DATE processing
    for(int i=1;i<=7;i++){
    line=line.substring(1+line.indexOf("|"));

    }
    String completedDate=line.substring(0, line.indexOf("|"));
    analyzerResults.setAnalyzerId(ANALYZER_ID);
    analyzerResults.setResult(result);
    analyzerResults.setUnits(UNDER_THREASHOLD.equals(result) ? "" : "cp/ml");
	analyzerResults.setCompleteDate(DateUtil.convertStringDateToTimestampWithPattern(completedDate.substring(0, 4)+"/"+completedDate.substring(4, 6)+"/"+completedDate.substring(6, 8)+" 00:00:00", DATE_PATTERN));
    analyzerResults.setTestId(test.getId());
    analyzerResults.setIsControl(false);//!"S".equals(fields[SAMPLE_TYPE].replace("\"", "").trim()));
    analyzerResults.setTestName(test.getName());
    analyzerResults.setResultType("A");
    
  /*  if (analyzerResults.getIsControl())
    {
      accessionNumber = accessionNumber + ":" + fields[this.SAMPLE_TYPE].replace("\"", "").trim();
    }*/
  
    analyzerResults.setAccessionNumber(accessionNumber);

    addValueToResults(resultList, analyzerResults);
    
}

public void ordersExport2(List<AnalyzerResults> results) {
	Connection c = null;
    Statement stmt = null;
    try {
       Class.forName("org.postgresql.Driver");
       c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/clinlims", "clinlims", "clinlims");
       c.setAutoCommit(false);
       System.out.println("Opened database successfully");

       String sql="SELECT s.accession_number, a.test_id,pat.national_id,pat.external_id,pat.gender,pat.birth_date "+
    		   "FROM clinlims.sample s,clinlims.sample_item si,clinlims.analysis a,clinlims.sample_human sh,clinlims.patient pat "+
    		   "WHERE  a.status_id=13  AND a.test_id IN ("+test.getId()+") AND "+
    		   "a.sampitem_id=si.id AND "+
    		   "si.samp_id=s.id AND "+
    		   "sh.samp_id=s.id AND "+
    		   "sh.patient_id=pat.id "+
    		   "ORDER BY 1";
       stmt = c.createStatement();
       ResultSet rs = stmt.executeQuery( sql );
       
       //String osname = System.getProperty("os.name", "").toLowerCase();
       //if (osname.startsWith("windows"))
       //else if (osname.startsWith("linux"))
       /////////
       //Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:/Temp/RetroCIPRESC.AST"), StandardCharsets.UTF_8)); 
      // Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/media/windowsshare/ARVPRESC.AST"), StandardCharsets.UTF_8)); 
      // Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/home/oeserver/Desktop/PRESCRIPTIONS.AST"), StandardCharsets.UTF_8)); 
         FileWriter writer = new FileWriter("/home/oeserver/Desktop/Prescriptions/ARVPRESC.AST", false);
       
       
     /*  String home = System.getProperty("user.home");

       File data_directory = new File(home, ".my_app_data");
       data_directory.mkdir();

       File log_file = new File(data_directory, "ARVPRESC.AST");
       try {
           log_file.createNewFile();
       } catch (IOException e) {
           // handle error
       }
       
       BufferedWriter writer = new BufferedWriter(new FileWriter(log_file));*/

       writer.write("H|^~\\&|||GLIMS||ORM|||MPL|||A2.2|200712120754|");writer.write("\r\n");
       int inc=0;		
       while ( rs.next() ) {
    	inc++;
        String  labno = rs.getString("accession_number");
        String  sujetno = rs.getString("national_id");
        String  external_id = rs.getString("external_id");
        String  sexe = rs.getString("gender");
        String  birth_date = rs.getString("birth_date");
        String patID=sujetno==null?external_id:sujetno;
        
        writer.write("P|"+inc+"|"+labno+"|||Patient-XXXXX||"+birth_date.substring(0, 10).replace("-", "")+"|"+sexe+"||||||||||||||||||");writer.write("\r\n");
        writer.write("OBR|1|"+labno+"||Viral Load|R|||||||||||||"+patID+"||||||||||");writer.write("\r\n");
        
        
       }
       writer.write("L|1|");
       rs.close();
       stmt.close();
       c.close();
       writer.close();
    } catch ( Exception e ) {
       System.err.println( e.getClass().getName()+": "+ e.getMessage() );
       //System.exit(0);
    }
    System.out.println("Operation done successfully");//	Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);  

    
   }   

}