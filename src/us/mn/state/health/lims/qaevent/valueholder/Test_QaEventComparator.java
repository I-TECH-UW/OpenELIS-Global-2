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
package us.mn.state.health.lims.qaevent.valueholder;

import java.util.Comparator;

import us.mn.state.health.lims.common.services.TestService;

/**
 * @author benzd1
 * bugzilla 1856
 */
public class Test_QaEventComparator implements Comparable {
   String name;

   
   // You can put the default sorting capability here
   public int compareTo(Object obj) {
      Test_QaEvents q = (Test_QaEvents)obj;
      return this.name.compareTo( TestService.getLocalizedTestNameWithType( q.getAnalysis().getTest() ) );
   }
   
 

 
   public static final Comparator DESCRIPTION_COMPARATOR =
	     new Comparator() {
	      public int compare(Object a, Object b) {
	    	  Test_QaEvents q_a = (Test_QaEvents)a;
	    	  Test_QaEvents q_b = (Test_QaEvents)b;
	 
	         return ((TestService.getLocalizedTestNameWithType( q_a.getAnalysis().getTest() ).toLowerCase()).compareTo(TestService.getLocalizedTestNameWithType( q_b.getAnalysis().getTest() ).toLowerCase()));

	      }
	   };
   

}
