<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="us.mn.state.health.lims.common.action.IActionConstants,
			     us.mn.state.health.lims.dataexchange.order.valueholder.ElectronicOrder,
			     java.util.List" %>

<%@ taglib uri="/tags/struts-bean"		prefix="bean" %>
<%@ taglib uri="/tags/struts-html"		prefix="html" %>
<%@ taglib uri="/tags/struts-logic"		prefix="logic" %>
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

<bean:define id="formName" value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />

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

<bean:message key="eorder.sort"/>: 
<select id="sortSelect" onchange="sortBy(this.value)">
	<option value="lastupdated"><bean:message key="eorder.lastupdated"/></option>
	<option value="externalId"><bean:message key="eorder.externalid"/></option>
	<option value="statusId"><bean:message key="eorder.status"/></option>
</select>

<logic:empty  name="<%=formName%>" property="eOrders">
	<h2><bean:message key="eorder.noresults"/></h2>
</logic:empty>
<logic:notEmpty name="<%=formName%>" property="eOrders">
	<h2>
		<bean:message key="eorder.results"/> <%=startIndex + 1 %> - <%=endIndex%> <bean:message key="eorder.of"/> <%=total%>
		<button class="prevButton" onClick="prevPage(); return false;"><bean:message key="label.button.previous"/></button>
		<button class="nextButton" onClick="nextPage(); return false;"><bean:message key="label.button.next"/></button>
	</h2>
	<table style="width:100%">
		<tr>
			<td style="width:1%"></td>
			<td style="width:98%">
				<logic:iterate name="<%=formName%>" property="eOrders" id="data">
					
					<h3>
						<span><bean:message key="eorder.externalid"/>: <bean:write name="data" property="externalId"/></span>
						<span style="float:right"><bean:message key="eorder.lastupdated"/>: <bean:write name="data" property="lastupdated"/></span>
					</h3>
					<div id="info" >
						<b><bean:message key="eorder.timestamp"/>:</b> <bean:write name="data" property="orderTimestamp"/><br>
						<table>
						<tr>
							<td><b><bean:message key="eorder.patient"/>:</b></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							 <td></td
							 ><td><b><bean:message key="eorder.patient.name"/>: </b><bean:write name="data" property="patient.person.lastName"/>, <bean:write name="data" property="patient.person.firstName"/></td>
							 <td><b><bean:message key="eorder.patient.gender"/>: </b><bean:write name="data" property="patient.gender"/></td>
						</tr>
						<tr>
							 <td></td>
							 <td><b><bean:message key="eorder.patient.birthdate"/>: </b><bean:write name="data" property="patient.birthDateForDisplay"/></td>
							 <td><b><bean:message key="eorder.patient.id"/>: </b><bean:write name="data" property="patient.externalId"/></td>
						</tr>
						</table>
						<b><bean:message key="eorder.status"/>: </b> <bean:message name="data" property="status.nameKey"/><br>
						<b><bean:message key="eorder.message"/>: </b><br> 
						<div class="colorFill message" style="white-space:pre;padding:5px;"><bean:write name="data" property="data"/></div>
					</div>
					<hr>
				</logic:iterate>
			</td>
			<td style="width:1%"></td>
		</tr>
	</table>
	<h2>
		<bean:message key="eorder.results"/> <%=startIndex + 1 %> - <%=endIndex%> <bean:message key="eorder.of"/> <%=total%>
		<button class="prevButton" onClick="prevPage(); return false;"><bean:message key="label.button.previous"/></button>
		<button class="nextButton" onClick="nextPage(); return false;"><bean:message key="label.button.next"/></button>
	</h2>
</logic:notEmpty>
