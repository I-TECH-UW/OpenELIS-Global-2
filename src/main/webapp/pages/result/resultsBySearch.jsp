<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="us.mn.state.health.lims.common.action.IActionConstants" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

	

<script type="text/javascript" >

function  /*void*/ setMyCancelAction(form, action, validate, parameters) 
{   
	//first turn off any further validation
	setAction(window.document.forms[0], 'Cancel', 'no', '');	
}

function doSelectPatientForResults(){	
	var form = document.forms[0];

	form.action = "PatientResults.do?patientID="  + patientSelectID;

	form.submit();
}

</script>

<div id="searchDiv" class="colorFill"  >
	<tiles:insert attribute="searchPanel" />
</div>

<logic:equal  name="${form.formName}" property="searchFinished" value="true">
	<div id="resultsDiv" class="colorFill" >
		<tiles:insert attribute="resultsPanel" />
	</div>
</logic:equal>




