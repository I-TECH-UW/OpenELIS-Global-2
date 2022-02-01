<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="org.openelisglobal.common.action.IActionConstants" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>	 

	

<script type="text/javascript" >

function  /*void*/ setMyCancelAction(form, action, validate, parameters) 
{   
	//first turn off any further validation
	setAction(document.getElementById("mainForm"), 'Cancel', 'no', '');	
}

function doSelectPatientForResults(){	
	var form = document.getElementById("mainForm");

	form.action = "PatientResults.do?patientID="  + patientSelectID;

	form.submit();
}

function altAccessionSearchFunction(labNumber) {
	jQuery("#loading").hide();
	var url = new URL(window.location.href);
	url.searchParams.set('accessionNumber', labNumber);
	
	window.location.href = url.toString();
}

</script>

<div id="searchDiv" class="colorFill"  >
<tiles:insertAttribute name="searchPanel" />
</div>
<c:if test="${form.searchFinished}">
	<div id="resultsDiv" class="colorFill" >
		<tiles:insertAttribute name="resultsPanel" />
	</div>
</c:if>



