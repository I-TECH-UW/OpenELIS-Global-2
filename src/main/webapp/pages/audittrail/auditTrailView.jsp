<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants,
	us.mn.state.health.lims.common.util.StringUtil,
	us.mn.state.health.lims.common.provider.validation.AccessionNumberValidatorFactory,
    us.mn.state.health.lims.common.util.Versioning,
	us.mn.state.health.lims.common.provider.validation.IAccessionNumberValidator"
%>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

<%!
	IAccessionNumberValidator accessionValidator;
	String basePath = "";
	String formName;
 %>

<%
	accessionValidator = new AccessionNumberValidatorFactory().getValidator();
	String path = request.getContextPath();
	basePath = request.getScheme() + "://" + request.getServerName() + ":"	+ request.getServerPort() + path + "/";
	formName = (String)request.getAttribute(IActionConstants.FORM_NAME);
%>

<link rel="stylesheet" media="screen" type="text/css" href="<%=basePath%>css/bootstrap.css?ver=<%= Versioning.getBuildNumber() %>" />
<link rel="stylesheet" media="screen" type="text/css" href="<%=basePath%>css/openElisCore.css?ver=<%= Versioning.getBuildNumber() %>" />

<script type="text/javascript" src="scripts/utilities.js?ver=<%= Versioning.getBuildNumber() %>" ></script>

<script type="text/javascript">

function submit(){

	var form = window.document.forms[0];
	form.action = "AuditTrailReport.do";
	form.submit();
	return false;
}

</script>

<h1 class="page-title"><spring:message code="reports.auditTrail" /></h1>

<form class="form-horizontal">

	<div class="row-fluid">
	    <div class="span12">
	
		<%=StringUtil.getContextualMessageForKey("quick.entry.accession.number")%>: 
		<html:text name='${form.formName}'
				   styleClass="input-medium" 
	    	       property="accessionNumberSearch"
	        	   maxlength="<%= Integer.toString(accessionValidator.getMaxAccessionLength()) %>" />
		<input class="btn" type="button" onclick="submit();" value='<%=StringUtil.getMessageForKey("label.button.view") %>'>
		</div>
	</div>
</form>
	
	<hr>
		
	<logic:notEmpty name='${form.formName}' property="accessionNumber" >
	
		<logic:empty name='${form.formName}' property="log" >
		<div class="row-fluid">
		    <div class="span6">
		    	<em><spring:message code="sample.edit.sample.notFound" /></em>
		    </div>
		</div>
		</logic:empty>
		
		<logic:notEmpty name='${form.formName}' property="log" >
		
		<div class="row-fluid order-details">
		    <div class="span12">
		        <span class="order-number"><bean:write name='${form.formName}' property="accessionNumber"  /></span> 
		        <spring:message code="reports.auditTrail.creation" />: <span id="dateCreated"></span>
		        <spring:message code="reports.auditTrail.days" />: <span id="daysInSystem"></span>
		    </div>
		</div>
	    <div class="current" >
            <h2><spring:message code="order.information" /></h2>
            <tiles:insert attribute="orderInfo" />
            <tiles:insert attribute="patientInfo" />
        </div>
		<div class="row-fluid">
			<div class="span12">		
				<div id="loading" class="loading-note"><img src="<%=basePath%>/images/indicator.gif" /><spring:message code="loading" /></div>
				<table class="table table-small table-hover table-bordered table-striped" id="advancedTable">
					<thead>
				    	<tr id="rowHeader">
						    <th>ID</th>
							<th><span><spring:message code="reports.auditTrail.time"/></span></th>
						    <th><span><spring:message code="reports.auditTrail.item"/></span></th>
							<th><span><spring:message code="reports.auditTrail.action"/></span></th>
						    <th><span><spring:message code="reports.auditTrail.identifier"/></span></th>
							<th><span><spring:message code="reports.auditTrail.user"/></span></th>
							<th><span><spring:message code="reports.auditTrail.old.value"/></span></th>
							<th><span><spring:message code="reports.auditTrail.new.value"/></span></th>
						</tr>
					</thead>
					<tbody>
					<logic:iterate id="log" indexId="rowIndex" name='${form.formName}' property="log" type="us.mn.state.health.lims.audittrail.action.workers.AuditTrailItem">
						<tr class="<%=log.getItem()%>">
							<td><%=rowIndex%></td>
							<td class="time-stamp"><%= log.getDate() + " " +  log.getTime()%></td>
							<td class="item-cell"><%=log.getItem()%></td>
							<td><%=log.getAttribute()%></td>
							<td class="id-number"><%= log.getIdentifier() %></td>
							<td><%= log.getUser() %></td>
							<td><%=log.getOldValue()%></td>
							<td><%=log.getNewValue()%></td>
						</tr>
					</logic:iterate>
					</tbody>
				</table>
				
				<div id="showOptions" class="show-table-options">
					<button class="reset-sort btn btn-mini" disabled="disabled"><i class="icon-refresh"></i> Reset</button>
					<label> <spring:message code="audit.show" /> :
				        <select id="filterByType">
				        <!--  Options for filter are added via filterByType jquery function -->
				            <option value=""><spring:message code="audit.show.all"/></option>
				        </select>
				    </label>
				</div>
						
			</div>
		</div>
	
		</logic:notEmpty>
				
	</logic:notEmpty>
	
<logic:notEmpty name='${form.formName}' property="log" >
    <script type="text/javascript">
        function getAuditSearchText(){  return '<spring:message code="audit.search.text" />';  }
        function getAuditFilteredFrom(){  return '<spring:message code="audit.filtered.from" />';  }
        function getAuditNoPrefix(){  return '<spring:message code="audit.no.prefix" />';  }
        function getAuditEntriesDisplayed(){  return '<spring:message code="audit.entries.displayed" />';  }
        function getAuditNoRecords(){  return '<spring:message code="audit.no.records" />';  }
    </script>
<script type="text/javascript" src="<%=basePath%>scripts/oe.datatables.functions.js?ver=<%= Versioning.getBuildNumber() %>"></script>
</logic:notEmpty>

<script type="text/javascript">

    jQuery(document).ready( function() {
        jQuery(".current input").each( function(index,elem){ jQuery(elem).attr('readonly', true) });
        jQuery(".current .spacerRow").each( function(index, elem){jQuery(elem).hide()});
    } );
</script>