<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="us.mn.state.health.lims.common.action.IActionConstants,
			     us.mn.state.health.lims.dataexchange.order.valueholder.ElectronicOrder,
			     java.util.List" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>
<%!
    String basePath = "";
	String sortOrder = "";
	int startIndex;
	int endIndex;
	int total;
	int pageNumber;
	boolean firstPage;
	boolean lastPage;
%>
<%
    String path = request.getContextPath();
    basePath = request.getScheme() + "://" + request.getServerName() + ":"  + request.getServerPort() + path + "/";
    sortOrder = request.getParameter("sortOrder");
    startIndex = (Integer) request.getAttribute("startIndex");
    endIndex = (Integer) request.getAttribute("endIndex");
    total = (Integer) request.getAttribute("total");
    pageNumber = (startIndex / 50) + 1;
    firstPage = pageNumber == 1;
    lastPage = endIndex == total;
%>

 

<script type="text/javascript">
var pageNumber = <%=pageNumber%>;
var firstPage = <%=firstPage%>;
var lastPage = <%=lastPage%>;
	
function sortBy(sortOption) {
	window.location.href = "<%=basePath%>ElectronicOrders.do?sortOrder=" + sortOption;
}

function nextPage() {
	var sortOption = $jq("#sortSelect").val();
	window.location.href = "<%=basePath%>ElectronicOrders.do?sortOrder=" + sortOption + "&page=" + (pageNumber + 1);
}

function prevPage() {
	var sortOption = $jq("#sortSelect").val();
	window.location.href = "<%=basePath%>ElectronicOrders.do?sortOrder=" + sortOption + "&page=" + (pageNumber - 1);
}

$jq(window).load(function(){
	$jq('#sortSelect').val("<bean:write name="sortOrder" />");
	
	$jq('button.prevButton').each(function(){
		$jq(this).prop('disabled', <%=firstPage%>);
	});
	$jq('button.nextButton').each(function(){
		$jq(this).prop('disabled', <%=lastPage%>);
	});
});
</script>

<spring:message code="eorder.sort"/>: 
<select id="sortSelect" onchange="sortBy(this.value)">
	<option value="lastupdated"><spring:message code="eorder.lastupdated"/></option>
	<option value="externalId"><spring:message code="eorder.externalid"/></option>
	<option value="statusId"><spring:message code="eorder.status"/></option>
</select>

<logic:empty  name="${form.formName}" property="eOrders">
	<h2><spring:message code="eorder.noresults"/></h2>
</logic:empty>
<logic:notEmpty name="${form.formName}" property="eOrders">
	<h2>
		<spring:message code="eorder.results"/> <%=startIndex + 1 %> - <%=endIndex%> <spring:message code="eorder.of"/> <%=total%>
		<button class="prevButton" onClick="prevPage(); return false;"><spring:message code="label.button.previous"/></button>
		<button class="nextButton" onClick="nextPage(); return false;"><spring:message code="label.button.next"/></button>
	</h2>
	<table style="width:100%">
		<tr>
			<td style="width:1%"></td>
			<td style="width:98%">
				<logic:iterate name="${form.formName}" property="eOrders" id="data">
					
					<h3>
						<span><spring:message code="eorder.externalid"/>: <bean:write name="data" property="externalId"/></span>
						<span style="float:right"><spring:message code="eorder.lastupdated"/>: <bean:write name="data" property="lastupdated"/></span>
					</h3>
					<div id="info" >
						<b><spring:message code="eorder.timestamp"/>:</b> <bean:write name="data" property="orderTimestamp"/><br>
						<table>
						<tr>
							<td><b><spring:message code="eorder.patient"/>:</b></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							 <td></td
							 ><td><b><spring:message code="eorder.patient.name"/>: </b><bean:write name="data" property="patient.person.lastName"/>, <bean:write name="data" property="patient.person.firstName"/></td>
							 <td><b><spring:message code="eorder.patient.gender"/>: </b><bean:write name="data" property="patient.gender"/></td>
						</tr>
						<tr>
							 <td></td>
							 <td><b><spring:message code="eorder.patient.birthdate"/>: </b><bean:write name="data" property="patient.birthDateForDisplay"/></td>
							 <td><b><spring:message code="eorder.patient.id"/>: </b><bean:write name="data" property="patient.externalId"/></td>
						</tr>
						</table>
						<b><spring:message code="eorder.status"/>: </b> <bean:message name="data" property="status.nameKey"/><br>
						<b><spring:message code="eorder.message"/>: </b><br> 
						<div class="colorFill message" style="white-space:pre;padding:5px;"><bean:write name="data" property="data"/></div>
					</div>
					<hr>
				</logic:iterate>
			</td>
			<td style="width:1%"></td>
		</tr>
	</table>
	<h2>
		<spring:message code="eorder.results"/> <%=startIndex + 1 %> - <%=endIndex%> <spring:message code="eorder.of"/> <%=total%>
		<button class="prevButton" onClick="prevPage(); return false;"><spring:message code="label.button.previous"/></button>
		<button class="nextButton" onClick="nextPage(); return false;"><spring:message code="label.button.next"/></button>
	</h2>
</logic:notEmpty>
