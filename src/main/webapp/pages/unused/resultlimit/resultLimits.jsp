<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.Date,org.openelisglobal.common.action.IActionConstants"%>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

 

<%!String allowEdits = "true";%>

<%
	if (request.getAttribute(IActionConstants.ALLOW_EDITS_KEY) != null) {
		allowEdits = (String) request.getAttribute(IActionConstants.ALLOW_EDITS_KEY);
	}
%>

<script>

var floatRegEx = new RegExp("^\\d{1,}\\.?\\d?$");
var intRegEx = new RegExp("^\\d*$");
var dayToYear = 0.00273790926;

function validateForm(form) {
    return true;
}

function /*void*/ updateYear( dayElement, yearId ){
	dayElement.value = Math.floor( dayElement.value );
	var value = dayElement.value;
	$(yearId).value = intRegEx.test(value) ? Math.round(10000*value*dayToYear)/10000 : 0;
}

function /*void*/ updateDay( yearElement, dayId ){
	var value = yearElement.value;
	$(dayId).value = floatRegEx(value) ? Math.floor(value * 365.0 + (value/4.0)) : 0;
}

</script>

<table>
	<tr>
		<td width="10%">&nbsp;</td>
		<td class="label" width="10%">
			<spring:message code="resultlimits.test" />
			:
			<span class="requiredlabel">*</span>
		</td>
		<td width="15%">
			<html:select name="${form.formName}" property="limit.testId">
				<app:optionsCollection name="${form.formName}" property="tests" label="description" value="id" allowEdits="true" />

			</html:select>
		</td>
		<td class="label" width="10%">
			<spring:message code="resultlimits.resulttype" />
			:
			<span class="requiredlabel">*</span>
		</td>
		<td width="15%">
			<html:select name="${form.formName}" property="limit.resultTypeId">
				<app:optionsCollection name="${form.formName}" property="resultTypes" label="description" value="id" allowEdits="true" />
			</html:select>
		</td>
		<td width="50%" >&nbsp;</td>
	</tr>
	<tr>
		<td></td>
		<td class="label">
			<spring:message code="resultlimits.gender" />
			:
		</td>
		<td>
			<html:select name="${form.formName}" property="limit.gender">
				<app:optionsCollection name="${form.formName}" property="genders" label="description" value="genderType" allowEdits="true" />
			</html:select>
		</td>
		<td>&nbsp;</td><td>&nbsp;</td>
		<td>
			<spring:message code="resultLimits.gender.instrutions"/>
		</td>
	</tr>
	<tr>
		<td><spring:message code="resultlimits.age" /></td>
		<td class="label">
			<spring:message code="resultlimits.min" />
			:
		</td>
		<td>
			<html:text name="${form.formName}" 
					   property="limit.minDayAgeDisplay" 
					   size="4" 
					   id="minDayAge" 
					   onchange="updateYear( this, 'minYearAge');" />
					   &nbsp;/&nbsp; 
		   <html:text name="${form.formName}" 
		   			  property="limit.minAgeDisplay" 
		   			  size="10" 
		   			  id="minYearAge" 
		   			  onchange="updateDay(this, 'minDayAge');" />
		</td>
		<td class="label">
			<spring:message code="resultlimits.max" />
			:
		</td>
		<td>
			<html:text name="${form.formName}" 
					   property="limit.maxDayAgeDisplay" 
					   size="4" 
					   id="maxDayAge"
					   onchange="updateYear( this, 'maxYearAge');" />
					   &nbsp;/&nbsp; 
			<html:text name="${form.formName}" 
					   property="limit.maxAgeDisplay" 
					   size="10" 
					   id="maxYearAge"
					   onchange="updateDay(this, 'maxDayAge');" />
		</td>
		<td>
			<spring:message code="resultLimits.age.instrutions"/>
		</td>
		
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td><spring:message code="resultlimits.normal" /></td>
		<td class="label">
			<spring:message code="resultlimits.low" />
			:
		</td>
		<td>
			<form:input path="limit.lowNormalDisplay" size="10" />
		</td>
		<td class="label">
			<spring:message code="resultlimits.high" />
			:
		</td>
		<td>
			<form:input path="limit.highNormalDisplay" size="10" />
		</td>
		<td>
			<spring:message code="resultLimits.normal.instrutions"/>
		</td>
		
	</tr>
	<tr>
		<td><spring:message code="resultlimits.valid" /></td>
		<td class="label">
			<spring:message code="resultlimits.low" />
			:
		</td>
		<td>
			<form:input path="limit.lowValidDisplay" size="10" />
		</td>
		<td class="label">
			<spring:message code="resultlimits.high" />
			:
		</td>
		<td>
			<form:input path="limit.highValidDisplay" size="10" />
		</td>
		<td>
			<spring:message code="resultLimits.valid.instrutions"/>
		</td>
		
	</tr>
	<tr>
		<td>
			&nbsp;
		</td>
	</tr>
</table>


