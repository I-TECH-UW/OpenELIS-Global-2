<%--
  Created by IntelliJ IDEA.
  User: kenny
  Date: 2019-09-15
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         import="org.openelisglobal.common.action.IActionConstants,
				org.openelisglobal.common.formfields.FormFields,
                org.openelisglobal.common.formfields.FormFields.Field,
                org.openelisglobal.common.provider.validation.AccessionNumberValidatorFactory,
                org.openelisglobal.common.provider.validation.IAccessionNumberValidator,
                org.openelisglobal.common.provider.validation.NonConformityRecordNumberValidationProvider,
                org.openelisglobal.common.services.PhoneNumberService,
                org.openelisglobal.common.util.DateUtil,
                org.openelisglobal.internationalization.MessageUtil,
                org.openelisglobal.common.util.Versioning,
                org.openelisglobal.qaevent.valueholder.retroCI.QaEventItem,
                org.openelisglobal.common.util.ConfigurationProperties" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

<link rel="stylesheet" href="css/jquery_ui/jquery.ui.all.css?ver=<%= Versioning.getBuildNumber() %>">
<link rel="stylesheet" href="css/customAutocomplete.css?ver=<%= Versioning.getBuildNumber() %>">

<script src="scripts/ui/jquery.ui.core.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.widget.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.button.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.menu.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.position.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ui/jquery.ui.autocomplete.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/customAutocomplete.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/utilities.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script src="scripts/ajaxCalls.js?ver=<%= Versioning.getBuildNumber() %>"></script>

<div align="center">
    <h2><spring:message code="nonconforming.page.correctiveAction.title" /></h2>

    <select id="searchCriteria" tabindex="1" class="patientSearch">
        <c:forEach items="${form.patientSearch.searchCriteria}" var="criteria">
            <option value="<c:out value="${criteria.id}" />"><c:out value="${criteria.value}"/></option>
        </c:forEach>
    </select>
    <input type="text" name="searchValue"
           value="" onkeyup="enableSearch()" onpaste="enableSearch()" id="searchValue">
    &nbsp;
    <input type="button" id="searchButtonId"
           value='<spring:message code="label.button.search" />'
           onclick="searchPatients();" disabled />

    <table id="searchResults">

    </table>

</div>

<h2><spring:message code="nonconforming.page.correctiveAction.title" /></h2>
<form:hidden path="currentUserId" />
<form:hidden path="status" />
<form:hidden path="reportDate" />
<form:hidden path="reporterName" />
<form:hidden path="prescriberName" />
<form:hidden path="site" />
<form:hidden path="nceNumber" />
<form:hidden path="dateOfEvent" />
<form:hidden path="labOrderNumber" />
<form:hidden path="specimen" />
<form:hidden path="reportingUnit" />
<form:hidden path="description" />
<form:hidden path="suspectedCauses" />
<form:hidden path="proposedAction" />
<form:hidden path="laboratoryComponent" />
<form:hidden path="nceCategory" />
<form:hidden path="nceType" />
<form:hidden path="consequences" />
<form:hidden path="likelyRecurrence" />
<form:hidden path="severityScore" />
<form:hidden path="correctiveAction" />
<form:hidden path="controlAction" />
<form:hidden path="comments" />

<table class="corrective-action-section-1">
    <tr>
        <td><spring:message code="nonconforming.event.ncenumber" /><c:out value="${form.nceNumber}" /></td>
    </tr>
    <tr>
        <td><spring:message code="nonconforming.page.followUp.ncedate" /><c:out value="${form.dateOfEvent}" /></td>
    </tr>
    <tr>
        <td><spring:message code="nonconforming.page.followUp.severity" /><c:out value="${form.consequences}" /></td>
    </tr>

    <tr>
        <td><spring:message code="nonconforming.page.followUp.reportingPerson" /><c:out value="${form.name}" /></td>
    </tr>
    <tr>
        <td><spring:message code="nonconforming.page.correctiveAction.reportingDate" /><c:out value="${form.reportDate}" /></td>
    </tr>
    <tr>
        <td><spring:message code="nonconforming.event.reportingUnit" /><c:out value="${form.reportingUnit}" /></td>
    </tr>

    <tr>
        <td><spring:message code="nonconforming.event.laborderNumber" /><c:out value="${form.labOrderNumber}" /></td>
    </tr>
    <tr>
        <td><spring:message code="nonconforming.event.specimen" /><c:out value="${form.specimen}" /></td>
    </tr>
    <tr>
        <td><spring:message code="nonconforming.page.correctiveAction.laboratoryComponent" /><c:out value="${form.laboratoryComponent}" /></td>
    </tr>
    <tr>
        <td><spring:message code="nonconforming.page.followUp.nceCategory" /><c:out value="${form.nceCategory }" /></td>
    </tr>
    <tr>
        <td><spring:message code="nonconforming.page.followUp.nceType" /> <c:out value="${form.nceType}" /></td>
    </tr>

</table>

<table class="corrective-action-section-2">
    <tr>
        <td><spring:message code="nonconforming.page.correctiveAction.plannedAction" /><c:out value="${form.correctiveAction}" /></td>
    </tr>
    <tr>
        <td><spring:message code="nonconforming.page.correctiveAction.plannedPreventive" /><c:out value="${form.controlAction}" /></td>
    </tr>
    <tr>
        <td><spring:message code="nonconforming.page.followUp.comments" /><c:out value="${form.comments}" /></td>
    </tr>
</table>

<table class="full-table">
    <caption><spring:message code="nonconforming.page.correctiveAction.log" /></caption>
    <tr>
        <td><spring:message code="nonconforming.page.correctiveAction.discussionDate" /><c:out value="${form.discussionDate}" /></td>
    </tr>
</table>

<table class="full-table">
    <caption><spring:message code="nonconforming.page.correctiveAction.nceResolution" /></caption>
    <tr>
        <td></td>
    </tr>
    <tr>
        <td>
            <spring:message code="nonconforming.page.correctiveAction.nceResolutionLabel" />
            <form:radiobutton name="effective" path="effective" value="Yes"/> Yes
            <form:radiobutton name="effective" path="effective" value="No"/> No
        </td>
    </tr>
    <tr>
        <td><spring:message code="nonconforming.page.correctiveAction.signature" /></td>
        <td><spring:message code="nonconforming.page.correctiveAction.dateCompleted" /></td>
    </tr>
</table>
