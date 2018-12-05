<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants,
			us.mn.state.health.lims.common.provider.validation.AccessionNumberValidatorFactory,
			us.mn.state.health.lims.common.provider.validation.IAccessionNumberValidator,
		    us.mn.state.health.lims.common.util.DateUtil,
			us.mn.state.health.lims.common.util.StringUtil,
			us.mn.state.health.lims.common.util.Versioning,
			org.owasp.encoder.Encode" %>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/labdev-view" prefix="app" %>
<%@ taglib uri="/tags/sourceforge-ajax" prefix="ajax"%>

<%!
	IAccessionNumberValidator accessionValidator;
	String basePath = "";
 %>

<%
	accessionValidator = new AccessionNumberValidatorFactory().getValidator();
	String path = request.getContextPath();
	basePath = request.getScheme() + "://" + request.getServerName() + ":"	+ request.getServerPort() + path + "/";
%>

<!-- Creates updated UI. Removing for current release 
<link rel="stylesheet" media="screen" type="text/css" href="<%=basePath%>css/bootstrap.min.css?ver=<%= Versioning.getBuildNumber() %>" />
<link rel="stylesheet" media="screen" type="text/css" href="<%=basePath%>css/openElisCore.css?ver=<%= Versioning.getBuildNumber() %>" />
-->

<script type="text/javascript" src="scripts/utilities.js?ver=<%= Versioning.getBuildNumber() %>" ></script>

<bean:define id="formName" value='<%= (String)request.getAttribute(IActionConstants.FORM_NAME) %>' />
<bean:define id="reportType" name='<%=formName %>' property="reportType"/>
<bean:define id="reportRequest" name='<%=formName %>' property="reportRequest"/>
<bean:define id="instructions" name='<%=formName %>' property="instructions"/>


<script type="text/javascript">


function datePeriodUpdated(selectionElement){
	var selectedValue = selectionElement.options[selectionElement.selectedIndex].value;

    if( selectedValue == "custom"){
    	$("lowerMonth").disabled = false;
    	$("lowerYear").disabled = false;
    	$("upperMonth").disabled = false;
    	$("upperYear").disabled = false;
    }else{
    	clearDate( "lowerMonth");
    	clearDate( "lowerYear");
    	clearDate( "upperMonth");
    	clearDate( "upperYear");
    	
    	$("dateWarning").style.visibility = "hidden";
    }
}

function clearAllDates(){
		setDateWarning( false, "" );
}

function clearDate( id ){
	var element = $(id);
	element.value="";
	element.disabled = true;
	element.style.borderColor = "";	
}
function /*boolean*/ formCorrect(){
	var datePeriod = $("datePeriod");
	
	if( datePeriod ){
		var selectedValue = datePeriod.options[datePeriod.selectedIndex].value;
		if( selectedValue == "custom"){
			var hasMissingValue = false;
						
			if( missingValue("lowerYear")){ hasMissingValue = true;}
			if( missingValue("lowerMonth")){ hasMissingValue = true;}
			if( missingValue("upperYear")){ hasMissingValue = true;}
			if( missingValue("upperMonth")){ hasMissingValue = true;}
		
			if( hasMissingValue){ 
				$("dateWarning").innerHTML = "Dates are required for custom range";
				return false;
			}
			
			var lowerYearValue = $("lowerYear").value;
			var lowerMonthValue = $("lowerMonth").value;
			var upperYearValue = $("upperYear").value;
			var upperMonthValue = $("upperMonth").value;
			
			var low = getCompoundDate( lowerYearValue, lowerMonthValue);
			var upper = getCompoundDate(upperYearValue, upperMonthValue);
			
			if( low >= upper){
				setDateWarning( true, "Start date must be before end date" );
				return false;	
			}
			
			var yearDiff = parseInt(upperYearValue - lowerYearValue );
			
			if( yearDiff > 1 ){
			 	setDateWarning( true, "Maximum date span is 12 months" );
				return false;
			}else if( yearDiff == 1 ){
				var monthDiff = parseInt(upperMonthValue - lowerMonthValue );
				
				if( monthDiff >= 0){
					setDateWarning( true, "Maximum date span is 12 months"  );
					return false;
				}
			}
		}
	}

	return true;
}

function setDateWarning( on, warning ){
				$("lowerYear").style.borderColor = on ? "red" : "";	
				$("lowerMonth").style.borderColor = on ? "red" : "";	
				$("upperYear").style.borderColor = on ? "red" : "";	
				$("upperMonth").style.borderColor = on ? "red" : "";
				$("dateWarning").innerHTML = warning;
}

function /*int*/ getCompoundDate( year, month){
	month = month.length == 1 ? "0" + month : month;
	return parseInt( year + month); 

}

function /*boolean*/ missingValue( id ){
	if( $(id).value == ""){
		$(id).style.borderColor = "red";
		return true;
	}

	return false;
}

function onCancel(){
	var form = window.document.forms[0];
	form.action = "CancelReport.do";
	form.submit();
	return true;
}

function onPrint(){
	if( formCorrect()){
		var form = window.document.forms[0];
		form.action = "ReportPrint.do?type=" + '<%= Encode.forJavaScript((String) reportType) %>' + "&report=" + '<%=Encode.forJavaScript((String) reportRequest)%>' + "&cacheBreaker=" + Math.random();
		form.target = "_blank";
		form.submit();
		return false;
	}
	
	return true;
}

</script>

<h2><bean:write name="<%=formName%>" property="reportName"/></h2>

<div class="oe-report">

<html:hidden name='<%=formName%>' property="reportRequest"/>

<logic:equal  name='<%= formName %>' property='noRequestSpecifications' value="false">

  <logic:equal name='<%=formName%>' property="useAccessionDirect" value="true">
	  <div><strong><%= StringUtil.getContextualMessageForKey("report.enter.labNumber.headline") %></strong></div>
  </logic:equal>
  
  <div>

	  <logic:equal name='<%=formName%>' property="useAccessionDirect" value="true">
		<span style="padding-left: 10px">
		<logic:equal name='<%=formName%>' property="useHighAccessionDirect" value="true">
			<%= StringUtil.getContextualMessageForKey("report.from") %>
		</logic:equal>
		</span>
		<html:text name='<%=formName%>'
				   styleClass="input-medium" 
				   property="accessionDirect"
				   maxlength='<%= Integer.toString(accessionValidator.getMaxAccessionLength())%>'
				   />
	  </logic:equal>
	  <logic:equal name='<%=formName%>' property="useHighAccessionDirect" value="true">
		<span style="padding-left: 10px"><%= StringUtil.getContextualMessageForKey("report.to") %></span>
			<html:text name='<%=formName%>'
			           property="highAccessionDirect"
			           styleClass="input-medium"
			           maxlength='<%= Integer.toString(accessionValidator.getMaxAccessionLength())%>'/>
	  </logic:equal>
	  <logic:equal name='<%=formName%>' property="useAccessionDirect" value="true">
	  	<bean:message key="sample.search.scanner.instructions"/>
	  </logic:equal>
	  <logic:notEqual name='<%=formName%>' property="useAccessionDirect" value="true">
	  	<logic:equal name='<%=formName%>' property="useHighAccessionDirect" value="true">
	  		<bean:message key="sample.search.scanner.instructions"/>
	  	</logic:equal>
	  </logic:notEqual>
  </div>
  <logic:equal name='<%=formName%>' property="useHighAccessionDirect" value="true">
    <div><span style="padding-left: 10px"><%= StringUtil.getContextualMessageForKey("report.enter.labNumber.detail") %></span></div>
  </logic:equal>
  <logic:equal name='<%=formName%>' property="usePatientNumberDirect" value="true">
	<div><strong><%= StringUtil.getContextualMessageForKey("report.enter.subjectNumber") %></strong></div>
  </logic:equal>
  <div>

	  <logic:equal name='<%=formName%>' property="usePatientNumberDirect" value="true">
		<div style="padding: 5px 0 0 10px"><html:text styleClass="input-medium" name='<%=formName%>' property="patientNumberDirect" /></div>
	  </logic:equal>

	  <logic:equal name='<%=formName%>' property="useUpperPatientNumberDirect" value="true">
	   <span style="padding-left: 10px"><%= StringUtil.getContextualMessageForKey("report.to") %></span>
		<html:text styleClass="input-medium" name='<%=formName%>' property="patientUpperNumberDirect" />
	  </logic:equal>
  </div>
  
  <div>
	  <logic:equal name='<%=formName%>' property="useLowerDateRange" value="true">
	  	<span style="padding-left: 10px"><bean:message key="report.date.start"/>&nbsp;<%=DateUtil.getDateUserPrompt()%></span>
		<html:text styleClass="input-medium" name='<%=formName%>' property="lowerDateRange" onkeyup="addDateSlashes(this, event);" maxlength="10"/>
	  </logic:equal>
	  <logic:equal name='<%=formName%>' property="useUpperDateRange" value="true">
	  	<span style="padding-left: 10px"><bean:message key="report.date.end"/>&nbsp;<%=DateUtil.getDateUserPrompt()%></span>
	  	<html:text styleClass="input-medium" name='<%=formName%>' property="upperDateRange" maxlength="10" onkeyup="addDateSlashes(this, event);"/>
	  </logic:equal>
  </div>
 
  <logic:equal name='<%=formName%>' property="useLocationCode" value="true">
  	<div>
   	  <span style="padding-left: 10px"><bean:message key="report.select.service.location"/></span>
      <html:select name="<%=formName%>"  property="locationCode" styleClass="text" >
		<app:optionsCollection name="<%=formName%>" property="locationCodeList" label="organizationName" value="id" />
	  </html:select>
	</div>
  </logic:equal>
 
  <logic:equal name='<%=formName%>' property="useProjectCode" value="true">
   	<div>
	  <bean:message key="report.select.project"/>
	  <html:select name="<%=formName%>"  property="projectCode" styleClass="text" >
		<app:optionsCollection  name="<%=formName%>" property="projectCodeList" label="localizedName" value="id" />
	  </html:select>
	</div>
  </logic:equal>
  
  <logic:equal name='<%=formName%>' property="usePredefinedDateRanges" value="true">
   	<div>
	   <bean:message key="report.select.date.period"/>
	     <html:select name="<%=formName%>"  property="datePeriod" styleClass="text" onchange="datePeriodUpdated(this)" styleId="datePeriod">
	     	<option value='year'><%= StringUtil.getMessageForKey("report.select.date.period.year") %></option>
	     	<option value='months3'><%= StringUtil.getMessageForKey("report.select.date.period.months.3") %></option>
	     	<option value='months6'><%= StringUtil.getMessageForKey("report.select.date.period.months.6") %></option>
	     	<option value='months12'><%= StringUtil.getMessageForKey("report.select.date.period.months.12") %></option>
	     	<option value='custom'><%= StringUtil.getMessageForKey("report.selecte.date.period.custom") %></option>
		 </html:select>
	</div>
	<div>
  	    <bean:message key="report.date.start"/>
			<html:select name="<%= formName %>" 
						 property="lowerMonth" 
						 styleClass="text" 
						 disabled="true" 
						 styleId="lowerMonth"
						 onchange="clearAllDates();" >
				<app:optionsCollection  name="<%=formName%>" property="monthList" label="value" value="id" />
			</html:select>
			<html:select name="<%= formName %>" 
						 property="lowerYear" 
						 styleClass="text"  
						 disabled="true" 
						 styleId="lowerYear" 
						 onchange="clearAllDates();">
				<app:optionsCollection  name="<%=formName%>" property="yearList" label="value" value="id" />
			</html:select>
	    <bean:message key="report.date.end"/>
			<html:select name="<%= formName %>" 
			             property="upperMonth" 
			             styleClass="text"   
			             disabled="true" 
			             styleId="upperMonth"
			             onchange="clearAllDates();">
				<app:optionsCollection  name="<%=formName%>" property="monthList" label="value" value="id" />
			</html:select>
			<html:select name="<%= formName %>" 
			             property="upperYear" 
			             styleClass="text"   
			             disabled="true" 
			             styleId="upperYear"
			             onchange="clearAllDates();">
				<app:optionsCollection  name="<%=formName%>" property="yearList" label="value" value="id" />
			</html:select>
  			<div id="dateWarning" ></div>
	</div>
  </logic:equal>
    <logic:notEmpty name='<%=formName%>'  property="selectList" >
   	<div>
       <bean:define id="selectList" name='<%=formName %>' property="selectList" type="us.mn.state.health.lims.reports.action.implementation.ReportSpecificationList"/>
       <span style="padding-left: 10px"><label for="selectList">
       <%= selectList.getLabel()%></label>
	   <html:select name="<%=formName%>"  property="selectList.selection" styleClass="text" styleId="selectList">
		   <app:optionsCollection  name="<%=formName%>" property="selectList.list" label="value" value="id" />
       </html:select></span>
	</div>
    </logic:notEmpty>
</logic:equal>

<br/>
<div align="left" style="width:80%" >
<logic:notEmpty name='<%= formName %>' property="instructions" >
<%= instructions %>
</logic:notEmpty>
</div>
<div style="margin-left: 50px">
	<input type="button" class="btn" name="printNew" onclick="onPrint();" value="<%=StringUtil.getMessageForKey("label.button.print.new.window") %>">
</div>

</div>