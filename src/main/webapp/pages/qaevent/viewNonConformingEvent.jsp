<%--
  Created by IntelliJ IDEA.
  User: kenny
  Date: 2019-09-15
  Time: 12:48
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
    <h2><spring:message code="nonconforming.page.view.title" /></h2>

    <select id="searchCriteria" tabindex="1" class="patientSearch">
        <option value="<c:out value="1" />">Lab Order Number</option>
        <option value="<c:out value="2" />">NCE Number</option>
    </select>
    <input type="text" name="searchValue"
           value="" onkeyup="enableSearch()" onpaste="enableSearch()" id="searchValue">
    &nbsp;
    <input type="button" id="searchButtonId"
           value='<spring:message code="label.button.search" />'
           onclick="searchNCE();" disabled />

    <table id="searchResults">

    </table>

</div>
<c:if test="${not empty form.labOrderNumber}">
<h2><spring:message code="nonconforming.page.followUp.title" /></h2>
<form:hidden path="currentUserId" />
<form:hidden path="status" />
<form:hidden path="reporterName" />
<form:hidden path="site" />

<table class="full-table">
    <tr class="view-non-conforming-section-1">
        <td><spring:message code="nonconforming.event.ncenumber" /></td>
        <td><form:hidden path="nceNumber" /><c:out value="${form.reportDate}" /></td>

        <td><spring:message code="nonconforming.event.date" /></td>
        <td><form:hidden path="reportDate" /><c:out value="${form.reportDate}" /></td>
    </tr>
    <tr>
        <td><spring:message code="nonconforming.page.followUp.ncedate" /></td>
        <td><form:hidden path="dateOfEvent" /><c:out value="${form.dateOfEvent}" /></td>

        <td><spring:message code="nonconforming.page.followUp.reportingPerson" /></td>
        <td><form:hidden path="name" /><c:out value="${form.name}" /></td>
    </tr>

    <tr>
        <td><spring:message code="nonconforming.labOrderNumber" /></td>
        <td><form:hidden path="labOrderNumber" /><c:out value="${form.labOrderNumber}" /></td>

        <td><spring:message code="nonconforming.event.reportingUnit" /></td>
        <td><form:hidden path="reportingUnit" /><c:out value="${form.reportingUnit}" /></td>
    </tr>
    <tr>
        <td><spring:message code="nonconforming.event.specimen" /></td>
        <td><form:hidden path="specimen" /><c:out value="${form.specimen}" /></td>
    </tr>
    <tr>
        <td><spring:message code="nonconforming.event.prescriberNameAndSite" /></td>
        <td><form:hidden path="prescriberName" /><c:out value="${form.prescriberName}" /> - <c:out value="${form.site}" /></td>

        <td><spring:message code="nonconforming.page.followUp.laboratoryComponent" /></td>
        <td>
            <form:select path="laboratoryComponent" >
                <form:option value="">Select one</form:option>
                <form:options items="${form.labComponentList}" itemLabel="value" itemValue="id" />
            </form:select>
        </td>
    </tr>
</table>

<table class="full-table">
    <tr>
        <td class="view-non-conforming-descriptions">
            <spring:message code="nonconforming.event.description" />
            <p><form:hidden path="description" /><c:out value="${form.description}" /></p>
        </td>
        <td>
            <strong><spring:message code="nonconforming.page.followUp.nceType" /></strong>
            <spring:message code="nonconforming.page.followUp.nceCategory" />
            <form:select path="nceCategory" id="nceCategory">
                <option value="">Select one</option>
                <form:options items="${form.nceCategories}" itemLabel="name" itemValue="id" />
            </form:select>
            <spring:message code="nonconforming.page.followUp.nceType" />
            <form:select path="nceType" id="nceType">
                <option value="">Select one</option>
                <form:options items="${form.nceTypes}" itemLabel="name" itemValue="id" />
            </form:select>
        </td>
    </tr>
    <tr>
        <td>
            <spring:message code="nonconforming.event.suspectedCauses" />
            <p><form:hidden path="suspectedCauses" /><c:out value="${form.suspectedCauses}" /></p>
        </td>
        <td>
            <label><strong><spring:message code="nonconforming.page.followUp.severity" /></strong></label>
            <spring:message code="nonconforming.page.followUp.howSevere" />
            <form:select path="consequences" >
                <form:options items="${form.severityConsequencesList}" itemLabel="value" itemValue="id" />
            </form:select>
        </td>
    </tr>
    <tr>
        <td>
            <spring:message code="nonconforming.event.proposedAction" />
            <p><form:hidden path="proposedAction" /><c:out value="${form.proposedAction}" /></p>
        </td>
        <td>
            <spring:message code="nonconforming.page.followUp.likelyRecurrence" />
            <form:select path="recurrence" >
                <form:options items="${form.severityRecurranceList}" itemLabel="value" itemValue="id" />
            </form:select>
            <p><spring:message code="nonconforming.page.followUp.lowSeverity" /></p>
            <p><spring:message code="nonconforming.page.followUp.highSeverity" /></p>
            <p><spring:message code="nonconforming.page.followUp.severityScore" /><c:out value="${form.severityScore}" /></p>
        </td>
    </tr>
</table>

<table class="full-table">
    <caption class="center-caption"><spring:message code="nonconforming.page.followUp.correctiveAction" /></caption>
    <tr>
        <td>
            <spring:message code="nonconforming.page.followUp.correctiveActionDescription" />
            <p><form:textarea path="correctiveAction"></form:textarea></p>
        </td>
    </tr>
    <tr>
        <td>
            <spring:message code="nonconforming.page.followUp.prevConcActionDescription" />
            <p><form:textarea path="controlAction"></form:textarea></p>
        </td>
    </tr>
    <tr>
        <td>
            <spring:message code="nonconforming.page.followUp.comments" />
            <p><form:textarea path="comments"></form:textarea></p>
        </td>
    </tr>
</table>
<div class="center-caption"><button onclick="savePage()">Submit</button></div>
</c:if>
<script type="text/javascript">
    function setSave() {
        var saveButton = $("saveButtonId");
        saveButton.disabled = false;
    }

    function enableSearch() {
        document.getElementById("searchButtonId").disabled = document.getElementById("searchValue").value === "";
    }

    /**
     *  Saves the form.
     */
    function savePage() {
        var form = document.getElementById("mainForm");
        window.onbeforeunload = null; // Added to flag that formWarning alert isn't needed.
        form.action = "ViewNonConformingEvent.do";
        form.submit();
    }

    /**
     *  Enable/Disable search button based on input
     */
    function enableSearch() {
        document.getElementById("searchButtonId").disabled = document.getElementById("searchValue").value === "";
    }

    function searchNCE() {
        var criteria = jQuery("#searchCriteria").val();
        var value = jQuery("#searchValue").val();
        var nceNumber;
        var labNumber = "";


        if (criteria == 2) {
            nceNumber =  value.trim();
        } else if (criteria == 1) {
            labNumber = value;
        }

        nceSearch(nceNumber, labNumber, "", false, processSearchSuccess);
    }

    function nceSearch(nceNumber, labNumber, guid, suppressExternalSearch, success, failure){
        if( !failure){failure = defaultFailure;	}
        new Ajax.Request (
            'ajaxQueryXML',  //url
            {//options
                method: 'get', //http method
                parameters: "provider=NonConformingEventSearchProvider&nceNumber=" + nceNumber +
                    "&labNumber=" + labNumber +
                    "&suppressExternalSearch=" + suppressExternalSearch,
                onSuccess:  success,
                onFailure:  failure
            }
        );
    }

    function processSearchSuccess(xhr) {
        console.log(xhr)
        var formField = xhr.responseXML.getElementsByTagName("formfield").item(0);
        var message = xhr.responseXML.getElementsByTagName("message").item(0);

        if (message.firstChild.nodeValue == "valid") {
            jQuery("#searchResults").html("<tr><th>Date</th><th>NCE Number</th><th>Lab Section/Unit</th></tr>");
            var resultNodes = formField.getElementsByTagName("nce");
            for (var i = 0; i < resultNodes.length; i++) {
                document.getElementById("searchResults").insertAdjacentHTML('beforeend', addNCERow(resultNodes[i]));
            }

        }
    }

    function addNCERow(nce) {
        var date = nce.getElementsByTagName("date").item(0);
        var nceNumberEl = nce.getElementsByTagName('ncenumber').item(0);
        var unit = nce.getElementsByTagName('unit').item(0);
        var nceNumber = (nceNumberEl ? nceNumberEl.firstChild.nodeValue : "#")
        var row = '<tr><td>' + (date ? date.firstChild.nodeValue : "") + '</td>' +
            '<td><a href="ViewNonConformingEvent.do?nceNumber=' + nceNumber + '">' + nceNumber + '</a></td>' +
            '<td>' + (unit ? unit.firstChild.nodeValue : "") + '</td>' +
            '</tr><tr>';
        return row;
    }

</script>