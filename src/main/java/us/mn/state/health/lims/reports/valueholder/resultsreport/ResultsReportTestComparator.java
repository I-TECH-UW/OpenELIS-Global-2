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
*/
package us.mn.state.health.lims.reports.valueholder.resultsreport;

import java.util.Comparator;

import us.mn.state.health.lims.common.util.StringUtil;

/**
 * @author benzd1 bugzilla 2264
 * 
 */
public class ResultsReportTestComparator implements Comparable {
   String name;

   
   // You can put the default sorting capability here
   public int compareTo(Object obj) {
	   ResultsReportTest test = (ResultsReportTest)obj;
       return this.name.compareTo(test.getTestDescription());
   }
   
 

 
   public static final Comparator NAME_COMPARATOR =
     new Comparator() {
      public int compare(Object a, Object b) {
    	  ResultsReportTest test_a = (ResultsReportTest)a;
    	  ResultsReportTest test_b = (ResultsReportTest)b;

          //bugzilla 2184: handle null sort value
    	  String aValue = "";
    	  if (test_a != null && test_a.getTestDescription() != null) {
    		  aValue = test_a.getTestDescription();
    	  } 
    	  
    	  String bValue = "";
    	  if (test_b != null && test_b.getTestDescription() != null) {
    		  bValue = test_b.getTestDescription();
    	  } 
          return (aValue.toLowerCase().compareTo(bValue.toLowerCase()));

      }
   };
   
   //bugzilla 1856
   public static final Comparator SORT_ORDER_COMPARATOR =
	     new Comparator() {
	      public int compare(Object a, Object b) {
	       	  ResultsReportTest test_a = (ResultsReportTest)a;
	    	  ResultsReportTest test_b = (ResultsReportTest)b;
	    	  String aValue = test_a.getAnalysis().getTest().getSortOrder();
	    	  String bValue = test_b.getAnalysis().getTest().getSortOrder();
	    	  
	    	  if (StringUtil.isNullorNill(aValue)) {
	    		  aValue = "0";
	    	  }
	    	  
	    	  if (StringUtil.isNullorNill(bValue)) {
	    		  bValue = "0";
	    	  }
	    	  
	          return (aValue.compareTo(bValue));
	 
	      }
	   };
   

}
