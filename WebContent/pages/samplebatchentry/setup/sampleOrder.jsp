<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="us.mn.state.health.lims.common.action.IActionConstants,
          	     us.mn.state.health.lims.common.util.ConfigurationProperties,
                 us.mn.state.health.lims.common.util.ConfigurationProperties.Property,
                 us.mn.state.health.lims.common.util.StringUtil,
                 us.mn.state.health.lims.common.util.Versioning,
                 us.mn.state.health.lims.common.util.DateUtil,
                 us.mn.state.health.lims.siteinformation.dao.SiteInformationDAO,
                 us.mn.state.health.lims.siteinformation.daoimpl.SiteInformationDAOImpl" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/labdev-view" prefix="app" %>

<bean:define id="formName" value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>'/>

<%!
    String path = "";
    String basePath = "";
    boolean acceptExternalOrders = false;
    SiteInformationDAO siteInfoDAO = new SiteInformationDAOImpl();
%>
<%
    path = request.getContextPath();
    basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    acceptExternalOrders = ConfigurationProperties.getInstance().isPropertyValueEqual( Property.ACCEPT_EXTERNAL_ORDERS, "true" );
    String siteInfo = siteInfoDAO.getSiteInformationByName("Study Management tab").getValue();
%>

<link rel="stylesheet" type="text/css" href="css/jquery.asmselect.css?ver=<%= Versioning.getBuildNumber() %>"/>

<script type="text/javascript" src="<%=basePath%>scripts/utilities.jsp"></script>
<script type="text/javascript" src="scripts/jquery.asmselect.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript" src="scripts/ajaxCalls.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript" src="scripts/laborder.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript">

//validation logic for this 'page'
function /*bool*/ sampleOrderValid() {
	if( acceptExternalOrders){ 
    	if (missingRequiredValues())
    		return false;
    }
   	if( $("currentDate").value.blank() ){
		return false;
    } else if ($("receivedDateForDisplay").value.blank() ){
        return false;
    }
    return true;
}



function checkValidTime(time, blankAllowed)
{
    var lowRangeRegEx = new RegExp("^[0-1]{0,1}\\d:[0-5]\\d$");
    var highRangeRegEx = new RegExp("^2[0-3]:[0-5]\\d$");

    if (time.value.blank() && blankAllowed == true) {
        clearFieldErrorDisplay(time);
        setSampleFieldValid(time.name);
        checkValidSubPages();
        return;        
    }

    if( lowRangeRegEx.test(time.value) ||
        highRangeRegEx.test(time.value) )
    {
        if( time.value.length == 4 )
        {
            time.value = "0" + time.value;
        }
        clearFieldErrorDisplay(time);
        setSampleFieldValid(time.name);
    }
    else
    {
        setFieldErrorDisplay(time);
        setSampleFieldInvalid(time.name);
    }

    checkValidSubPages();
}

function studyChanged(studyElement) {
	var study = studyElement.value;
	if (study == "routine") {
		$jq("#psuedoPatientInfo").removeAttr("disabled");
		$jq("#facility-combobox").show();
		$jq("#routineSampleAdd").show();
		$jq("#viralLoadSampleAdd").hide();
		$jq("#EIDSampleAdd").hide();
	} else if (study == "viralLoad"){
		$jq("#psuedoPatientInfo").attr('checked', false);
		$jq("#psuedoPatientInfo").attr('disabled', 'disabled');
		document.getElementsByName('patientInfoCheck')[0].disabled = true;
		$jq("select#requesterId").prop("selectedIndex", 0);
		$jq("#facility-combobox").hide();
		$jq("#routineSampleAdd").hide();
		$jq("#viralLoadSampleAdd").show();
		$jq("#EIDSampleAdd").hide();
	} else if (study == "EID"){
		$jq("#psuedoPatientInfo").attr('checked', false);
		$jq("#psuedoPatientInfo").attr('disabled', 'disabled');
		document.getElementsByName('patientInfoCheck')[0].disabled = true;
		$jq("select#requesterId").prop("selectedIndex", 0);
		$jq("#facility-combobox").hide();
		$jq("#routineSampleAdd").hide();
		$jq("#viralLoadSampleAdd").hide();
		$jq("#EIDSampleAdd").show();
	}
}
</script>


<!-- This define may not be needed, look at usages (not in any other jsp or js page may be radio buttons for ci LNSP-->
<!--bean:define id="orderTypeList" name='<%=formName%>' property="sampleOrderItems.orderTypes"  type="java.util.Collection"/> -->
<bean:define id="sampleOrderItem" name='<%=formName%>' property="sampleOrderItems" type="us.mn.state.health.lims.sample.bean.SampleOrderItem" />

<div id=orderDisplay >
<table style="width:100%">

<tr>
<td>
<table>
	<tr>
    	<td><%= StringUtil.getContextualMessageForKey("sample.batchentry.order.currentdate") %>
    		:
	        <span class="requiredlabel">*</span>
	        <span style="font-size: xx-small; "><%=DateUtil.getDateUserPrompt()%></span>
   	 	</td>
    	<td>
	        <html:text name='<%=formName %>'
                   property="currentDate"
                   styleId="currentDate"
                   styleClass="required"
                   readonly="true"
                   maxlength="10"/>
     	</td>
     	<td>
	     	<%= StringUtil.getContextualMessageForKey("sample.batchentry.order.currenttime") %>
	     	:
	        <span style="font-size: xx-small; "><%=DateUtil.getTimeUserPrompt()%></span> 
		</td>
	   	<td>
	    	<html:text name="<%=formName %>"
                   onkeyup="filterTimeKeys(this, event);"
                   property="currentTime"
                   styleId="currentTime"
                   maxlength="5"/>
   		</td>
	</tr>
	<tr>
    	<td>
    		<%= StringUtil.getContextualMessageForKey( "quick.entry.received.date" ) %>
        	:
	        <span class="requiredlabel">*</span>
	        <span style="font-size: xx-small; "><%=DateUtil.getDateUserPrompt()%></span>
    	</td>
    	<td>
    		<html:text name="<%=formName%>"
                  property="sampleOrderItems.receivedDateForDisplay"
                  onchange="checkValidSubPages();checkValidEntryDate(this, 'past');"
                  onkeyup="addDateSlashes(this, event);"
                  maxlength="10"
                  styleClass="text required"
                  styleId="receivedDateForDisplay"/>
     	</td>
     	<td>
	        <bean:message key="sample.batchentry.order.receptiontime"/>
	        :
	        <span style="font-size: xx-small; "><%=DateUtil.getTimeUserPrompt()%></span>
		</td>
		<td>
			<html:text name="<%=formName %>"
                   onkeyup="filterTimeKeys(this, event);"
                   property="sampleOrderItems.receivedTime"
                   styleId="receivedTime"
                   maxlength="5"
                   onblur="checkValidSubPages(); checkValidTime(this, true);"/>
    	</td>
	</tr>
	<tr <%= "false".equals(siteInfo) ? "style='display:none'" : ""  %>>
    	<td>
    		<bean:message key="sample.entry.project.form"/>: 
    	</td>
    	<td>
    		<html:select name="<%=formName %>"
    				property="study"
    				styleId="study" 
    				onchange="studyChanged(this);checkValidSubPages();">
    			<option value="routine"><bean:message key="dictionary.program.routine" /></option>
    			<option value="viralLoad"><bean:message key="sample.entry.project.VL.simple.title" /></option>
    			<option value="EID"><bean:message key="sample.entry.project.EID.title" /></option>
    		</html:select>
    	</td>
	</tr>
	<tr class="spacerRow">
    	<td>&nbsp;</td>
	</tr>
</table>
</td>
</tr>
</table>
</div>
