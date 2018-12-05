<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="us.mn.state.health.lims.common.action.IActionConstants" %>

<%@ taglib uri="/tags/struts-bean"		prefix="bean" %>
<%@ taglib uri="/tags/struts-html"		prefix="html" %>
<%@ taglib uri="/tags/struts-logic"		prefix="logic" %>
<%@ taglib uri="/tags/labdev-view"		prefix="app" %>
<%@ taglib uri="/tags/sourceforge-ajax" prefix="ajax"%>
<%@ taglib uri="/tags/struts-tiles"     prefix="tiles" %>

<bean:define id="formName"	value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />

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

<logic:equal  name="<%=formName%>" property="searchFinished" value="true">
	<div id="resultsDiv" class="colorFill" >
		<tiles:insert attribute="resultsPanel" />
	</div>
</logic:equal>




