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
package us.mn.state.health.lims.common.util.validator;
  
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import us.mn.state.health.lims.common.log.LogEvent;

/** 
 * &lt;p&gt;Perform date validations.&lt;/p&gt; 
 * &lt;p&gt; 
 * This class is a Singleton; you can retrieve the instance via the 
 * getInstance() method. 
 * &lt;/p&gt; 
 * 
 * @since Validator 1.1 
 */ 
public class DateValidator extends org.apache.commons.validator.DateValidator { 
   
    /** 
     * Protected constructor for subclasses to use. 
    */ 
    protected DateValidator() { 
        super(); 
    } 
     
   /** 
     * &lt;p&gt;Checks if the field is a valid date.  The pattern is used with 
     * &lt;code&gt;java.text.SimpleDateFormat&lt;/code&gt;.  If strict is true, then the 
     * length will be checked so '2/12/1999' will not pass validation with 
     * the format 'MM/dd/yyyy' because the month isn't two digits. 
     * The setLenient method is set to &lt;code&gt;false&lt;/code&gt; for all.&lt;/p&gt; 
     * 
     * @param value The value validation is being performed on. 
     * @param datePattern The pattern passed to &lt;code&gt;SimpleDateFormat&lt;/code&gt;. 
     * @param strict Whether or not to have an exact match of the datePattern. 
    */ 
    public boolean isValid(String value, String datePattern, boolean strict) { 
        if (value == null || datePattern == null || datePattern.length() <= 0) { 
            return false; 
        } 
        //System.out.println("value & datePattern " + value + " " + datePattern);
        SimpleDateFormat formatter = new SimpleDateFormat(datePattern); 
        formatter.setLenient(false); 
        try { 
            formatter.parse(value); 
        } catch (ParseException e) { 
            //bugzilla 2154
			LogEvent.logError("DateValidator","isValid()",e.toString());
            return false; 
        } 
        if (strict && (datePattern.length() != value.length())) { 
            return false; 
        } 
        return true; 
    } 
     
    /** 
     * &lt;p&gt;Checks if the field is a valid date.  The &lt;code&gt;Locale&lt;/code&gt; is 
     * used with &lt;code&gt;java.text.DateFormat&lt;/code&gt;.  The setLenient method 
     * is set to &lt;code&gt;false&lt;/code&gt; for all.&lt;/p&gt; 
     * 
     * @param value The value validation is being performed on. 
     * @param locale The locale to use for the date format, defaults to the default 
     * system default if null. 
    */ 
    public boolean isValid(String value, Locale locale) { 
        if (value == null) { 
            return false; 
        } 
        //System.out.println("value & locale " + value + " " + locale);

        DateFormat formatter = null; 
         if (locale != null) { 
              formatter = DateFormat.getDateInstance(DateFormat.SHORT, locale); 
           } else  { 
              formatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault()); 
          } 
          formatter.setLenient(false); 
          try { 
              formatter.parse(value); 
          } catch (ParseException e) { 
                //bugzilla 2154
        	  LogEvent.logError("DateValidator","isValid()",e.toString());              
              return false; 
          } 
          return true; 
      } 
  }
