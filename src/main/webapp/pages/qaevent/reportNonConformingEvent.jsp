<%--
  Created by IntelliJ IDEA.
  User: kenny
  Date: 2019-09-10
  Time: 09:27
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=utf-8"
         import="org.openelisglobal.common.provider.validation.AccessionNumberValidatorFactory,
                org.openelisglobal.common.provider.validation.IAccessionNumberValidator,

                org.openelisglobal.internationalization.MessageUtil,
                org.openelisglobal.common.util.Versioning" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

<%! String basePath = "";
    IAccessionNumberValidator accessionNumberValidator;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String maxDate = df.format(Calendar.getInstance().getTime());
%>
<%
    String path = request.getContextPath();
    basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
            + "/";
    accessionNumberValidator = new AccessionNumberValidatorFactory().getValidator();

%>
<script>
    var labOrderNumberText="<%= MessageUtil.getContextualMessage("nonconforming.labOrderNumber")%>";
    var affectedSpecimenText="<%= MessageUtil.getContextualMessage("nonconforming.affectedSpecimen")%>";
</script>

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
    <h2><%= MessageUtil.getContextualMessage("nonconforming.page.title") %></h2>

    <select id="searchCriteria" tabindex="1" class="patientSearch">
        <c:forEach items="${form.patientSearch.searchCriteria}" var="criteria">
            <option value="<c:out value="${criteria.id}" />"><c:out value="${criteria.value}"/></option>
        </c:forEach>
    </select>
    <input type="text" name="searchValue"
           maxlength='<%=Integer.toString(accessionNumberValidator.getMaxAccessionLength())%>'
           value="" onkeyup="enableSearch()" onpaste="enableSearch()" id="searchValue">
    &nbsp;
    <input type="button" id="searchButtonId"
           value='<%=MessageUtil.getMessage("label.button.search")%>'
           onclick="searchPatients();" disabled />

    <table id="searchResults">

    </table>
    <button id="goToNCEForm">
        <spring:message code="nonconforming.event.goToNCEForm" />
    </button>
</div>
<c:if test="${not empty form.labOrderNumber}">
    <form:hidden path="currentUserId" />
    <form:hidden path="status" />
    <form:hidden path="id" />
<table>
    <tr>
        <td><spring:message code="nonconforming.event.date" /></td>
        <td><form:hidden path="reportDate" /><c:out value="${form.reportDate}" /></td>
    </tr>
    <tr>
        <td><spring:message code="nonconforming.event.name" /></td>
        <td><form:hidden path="name" /><c:out value="${form.name}" /></td>
    </tr>
    <tr>
        <td><spring:message code="nonconforming.event.reportername" /></td>
        <td><form:input path="reporterName" type="text" /></td>
    </tr>
    <tr>
        <td><spring:message code="nonconforming.event.ncenumber" /></td>
        <td><c:out value="${form.nceNumber}" /></td>
    </tr>
    <tr>
        <td><spring:message code="nonconforming.event.eventdate" /></td>
        <td><form:input path="dateOfEvent" type="date" max="<%= maxDate %>" onchange="checkIfValid()"/></td>
    </tr>
    <tr>
        <td><spring:message code="nonconforming.event.laborderNumber" /></td>
        <td><form:hidden path="labOrderNumber" /><c:out value="${form.labOrderNumber}" /></td>
    </tr>
    <tr>
        <td><spring:message code="nonconforming.event.specimen" /></td>
        <td>
            <c:forEach items="${form.specimens}" var="specimen">
                <p><c:out value="${form.labOrderNumber}" /> - <c:out value="${specimen.sortOrder}"/></p>
            </c:forEach>
        </td>
    </tr>
    <tr>
        <td><spring:message code="nonconforming.event.prescriberNameAndSite" /></td>
        <td>
            <form:hidden path="prescriberName" />
            <form:hidden path="site" />
            <c:out value="${form.prescriberName}" />
            <c:out value="${form.site}" />
        </td>
    </tr>
    <tr>
        <td><spring:message code="nonconforming.event.reportingUnit" /></td>
        <td>
            <form:select path="reportingUnit" id="reportingUnit" onchange="checkIfValid()">
                <option value=""></option>
                <form:options items="${form.reportingUnits}" itemLabel="value" itemValue="id" />
            </form:select>
        </td>
    </tr>
    <tr>
        <td><spring:message code="nonconforming.event.description" /></td>
        <td>
            <form:textarea path="description" onchange="checkIfValid()"></form:textarea>
        </td>
    </tr>
    <tr>
        <td><spring:message code="nonconforming.event.suspectedCauses" /></td>
        <td>
            <form:textarea path="suspectedCauses" onchange="checkIfValid()"></form:textarea>
        </td>
    </tr>
    <tr>
        <td><spring:message code="nonconforming.event.proposedAction" /></td>
        <td>
            <form:textarea path="proposedAction" onchange="checkIfValid()"></form:textarea>
        </td>
    </tr>
</table>
    <div class="center-caption">
        <button id="cancelButton" onclick="cancelPage()">
            <spring:message code="label.button.cancel" />
        </button>
        <button id="saveButtonId" onclick="savePage()">
            <spring:message code="button.label.submit" />
        </button>
    </div>
</c:if>
<script type="text/javascript">
    function setSave(disabled) {
        var saveButton = jQuery("#saveButtonId");
        if (saveButton) {
            saveButton.disabled = disabled;
        }
    }

    function checkIfValid() {
        var doe = jQuery('input[name="dateOfEvent"]').val();
        var desc = jQuery('input[name="description"]').val();
        var suspectedCauses = jQuery('input[name="suspectedCauses"]').val();
        var proposedAction = jQuery('input[name="proposedAction"]').val();
        // var reportingUnit = jQuery('input[name="reportingUnit"]').val();
        setSave(true);
        if (doe != '' && desc !== '' && suspectedCauses !== '' && proposedAction !== '') {
            setSave(false);
        }
    }

    /**
     *  Saves the form.
     */
    function savePage() {
        var form = document.getElementById("mainForm");
        window.onbeforeunload = null; // Added to flag that formWarning alert isn't needed.
        form.action = "ReportNonConformingEvent.do";
        form.submit();
    }

    function loadForm(e) {
        e.preventDefault();
        var specimens = jQuery('input[name="specimen"]:checked');
        var specimenId = [];
        var labNo = "";
        for (var i = 0; i < specimens.length; i++) {
            var input = specimens[0];
            specimenId.push(input.value);
            labNo = input.dataset.labordernumber
        }
        if (specimenId.length > 0 && labNo !== "") {
            window.location = "ReportNonConformingEvent.do?labNo=" + labNo + "&specimenId=" + specimenId.join(",");
        }
        return false;
    }

    function cancelPage(e) {
        e.preventDefault();
        window.location = "Home.do";
        return false;
    }
    /**
     *  Enable/Disable search button based on input
     */
    function enableSearch() {
        document.getElementById("searchButtonId").disabled = document.getElementById("searchValue").value === "";
    }

    function searchPatients() {
        var criteria = jQuery("#searchCriteria").val();
        var value = jQuery("#searchValue").val();
        var splitName;
        var lastName = "";
        var firstName = "";
        var STNumber = "";
        var subjectNumber = "";
        var nationalID = "";
        var labNumber = "";

        newSearchInfo = false;
        jQuery("#resultsDiv").hide();
        jQuery("#searchLabNumber").val('');
        if (criteria == 1) {
            firstName =  value.trim();
        } else if (criteria == 2) {
            lastName = value.trim();
        } else if (criteria == 3) {
            splitName = value.split(",");
            lastName = splitName[0].trim();
            firstName = splitName.size() == 2 ? splitName[1].trim() : "";
        } else if (criteria == 4) {
            STNumber = value.trim();
            subjectNumber = value.trim();
            nationalID = value.trim();
        } else if (criteria == 5) {
            labNumber = value;
            jQuery("#searchLabNumber").val(value);
        }

        doSearch(lastName, firstName, STNumber, subjectNumber, nationalID, labNumber, "", false, processSearchSuccess);
    }

    function doSearch(lastName, firstName, STNumber, subjectNumber, nationalId, labNumber, guid, suppressExternalSearch, success, failure){
        if( !failure){failure = defaultFailure;	}
        new Ajax.Request (
            'ajaxQueryXML',  //url
            {//options
                method: 'get', //http method
                parameters: "provider=NCESampleSearchProvider&lastName=" + lastName +
                    "&firstName=" + firstName +
                    "&STNumber=" + STNumber +
                    "&subjectNumber=" + subjectNumber +
                    "&nationalID=" + nationalId +
                    "&labNumber=" + labNumber +
                    "&guid=" + guid +
                    "&suppressExternalSearch=" + suppressExternalSearch,
                onSuccess:  success,
                onFailure:  failure
            }
        );
    }

    function processSearchSuccess(xhr) {
        var formField = xhr.responseXML.getElementsByTagName("formfield").item(0);
        var message = xhr.responseXML.getElementsByTagName("message").item(0);

        if (message.firstChild.nodeValue == "valid") {
            jQuery("#searchResults").html("");
            var resultNodes = formField.getElementsByTagName("sample");
            for (var i = 0; i < resultNodes.length; i++) {
                document.getElementById("searchResults").insertAdjacentHTML('beforeend', addSampleRow(resultNodes[i]));
            }
            document.getElementById("goToNCEForm").style.display = '';
        } else {
            jQuery("#searchResults").html("<tr><th>No valid results</th></tr>");
        }
    }

    function getValueFromXmlElement(parent, tag) {
        var element = parent.getElementsByTagName(tag).item(0);
        return element ? element.firstChild.nodeValue : "";
    }

    function addSampleRow(sample) {
        var items = sample.getElementsByTagName("item");
        var labOrderNumber = sample.getAttribute('labOrderNumber');
        var itemRows = [];
        for (var i = 0; i < items.length; i++) {
            var item = items[i];
            itemRows.push(
                '<tr><td><input name="specimen" type="checkbox" data-labOrderNumber="' + labOrderNumber+ '" value="' + getValueFromXmlElement(item, 'id') + '"/></td>' +
                '<td>' + labOrderNumber + '-' + getValueFromXmlElement(item, 'number') + '</td>' +
                '<td>' + getValueFromXmlElement(item, 'type') + '</td></tr>'
            )
        }
        var sampleTemplate = '<tr><td>' + labOrderNumberText + '</td>' +
            '<td>' + labOrderNumber + '</td></tr><tr>' +
            '<td>' + affectedSpecimenText + '</td>' +
            '<td><table id="sample-"><tr>\n' +
            '<th></th>' +
            '<th>Specimen number</th>' +
            '<th>Specimen type</th>' +
            '</tr>' + itemRows.join('') + '</table>' +
            '</td></tr>';
        return sampleTemplate;
    }

    jQuery(document).ready( function() {
        setSave(true);
        document.getElementById("goToNCEForm").addEventListener("click", loadForm);
        var cancelButton = document.getElementById("cancelButton");
        if (cancelButton) {
            cancelButton.addEventListener("click", cancelPage);
        }
        document.getElementById("goToNCEForm").style.display = "none";
    });
</script>
