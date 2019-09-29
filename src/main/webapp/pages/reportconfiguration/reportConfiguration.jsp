<%--
  Created by IntelliJ IDEA.
  User: kenny
  Date: 2019-09-29
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%! String basePath = "";
    IAccessionNumberValidator accessionNumberValidator;
    boolean useProject = FormFields.getInstance().useField(Field.Project);
    boolean useSiteList = FormFields.getInstance().useField(Field.NON_CONFORMITY_SITE_LIST);
    boolean useSubjectNo = FormFields.getInstance().useField(Field.QASubjectNumber);
    boolean useNationalID = FormFields.getInstance().useField(Field.NationalID);
%>
<%
    String path = request.getContextPath();
    basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
            + "/";
    accessionNumberValidator = new AccessionNumberValidatorFactory().getValidator();

%>

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
<script type="text/javascript" src="scripts/jquery-ui.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<link rel="stylesheet" media="screen" type="text/css"
      href="<%=basePath%>css/jquery_ui/jquery.ui.theme.css?ver=<%= Versioning.getBuildNumber() %>"/>

<p>Report configuration</p>

<script>
    var reports= [];
    <c:forEach items="${form.reportList}" var="report">
    var report = {};
    report['id'] = '${report.id}';
    report['name'] = '${report.name}';
    report['type'] = '${report.id}';
    report['category'] = '${report.category}';
    report['menuElementId'] = '${report.menuElementId}';
    report['visible'] = '${report.visible}';
    report['sortOrder'] = '${report.sortOrder}';
    reports.push(report);
    </c:forEach>
</script>
<table id="report-table">
    <tr>
        <th>Name</th><th>Type</th><th>Category</th><th>Visible</th><th></th>
    </tr>
    <c:forEach items="${form.reportList}" var="report">
        <tr>
            <td><c:out value="${report.name}" /></td>
            <td><c:out value="${report.type}" /></td>
            <td><c:out value="${report.category}" /></td>
            <td><c:out value="${report.visible}" /></td>
            <td>
                <button onclick="editReport('<c:out value="${report.id}" />', '<c:out value="${report.type}" />', '<c:out value="${report.category}" />')">
                    Edit
                </button>
            </td>
        </tr>
    </c:forEach>
</table>

<div id="edit-report-div">
<form:form name="${form.formName}"
           action="${form.formAction}"
           modelAttribute="form"
           onSubmit="return submitForm(this);"
           method="${form.formMethod}"
           cssClass="edit-report-form"
           id="mainForm">

    <div class="form-div">
        <label>Name</label>
        <input name="name" type="text" class="form-control"/>
    </div>
    <div class="form-div">
        <label>Type</label>
        <form:select path="currentReport.type" cssClass="form-control" onchange="onTypeCategoryChange()">
            <form:option value="">Select one</form:option>
            <form:options items="${form.types}" itemLabel="value" itemValue="id" />
        </form:select>
    </div>
    <div class="form-div">
        <label>Type</label>
        <form:select path="currentReport.category" cssClass="form-control" onchange="onTypeCategoryChange()">
            <form:option value="">Select one</form:option>
            <form:options items="${form.categories}" itemLabel="value" itemValue="id"/>
        </form:select>
    </div>
    <div class="form-div">
        <label>Visible</label>
        <input type="checkbox" name="currentReport.visible" />
    </div>

    <ul id="report-ordering-panel" class="sortable sortable-tag ui-sortable report-ordering-panel">

    </ul>
    <input name="idOrder" type="hidden" />
    <button>
        Save
    </button>
    <button onclick="return cancelEdit()">
        Cancel
    </button>

</form:form>
</div>

<script>

    function cancelEdit() {
        jQuery('#edit-report-div').hide();
        jQuery('#report-table').show();
        return false;
    }

    function onTypeCategoryChange() {
        console.log('changed');
    }

    function editReport(reportId, type, category) {
        jQuery('#edit-report-div').show();
        jQuery('#report-table').hide();
        var visibleReports = [];
        for (var i = 0; i < reports.length; i++) {
            var report = reports[i];
            if (report.type == type && report.category == category) {
                visibleReports.push(report);
            }
        }
        var html = [];
        var currentReport;
        for (var i = 0; i < visibleReports.length; i++) {
            var rep = visibleReports[i];
            if (rep.id == reportId) {
                currentReport = rep;
            }
            if (report.visible) {
                html.push('<li class="ui-state-default_oe ui-state-default_oe-tag report-sort-order ' + (rep.id == reportId ? 'current-report' : '') + '" report-id="' + rep.id + '"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>' + rep.name + '</li>');
            }
        }
        if (currentReport) {
            jQuery('input[name="name"]').val(currentReport.name);
            jQuery('input[name="id"]').val(currentReport.id);
            jQuery('input[name="currentReport.type"]').val(currentReport.type);
            jQuery('input[name="currentReport.category"]').val(currentReport.category);
            jQuery('input[name="currentReport.visible"]').val(currentReport.visible);
        }
        jQuery('#report-ordering-panel').html(html.join(''));
        jQuery('#report-ordering-panel').sortable();
    }

    function onSave() {
        var order = jQuery(".report-sort-order");
        var idOrder = [];
        for (var i = 0; i < order.length; i++) {
            var o = order[i];
            var reportId = o.getAttribute('report-id');
            idOrder.push(reportId);
        }

        var form = document.getElementById("mainForm");
        window.onbeforeunload = null; // Added to flag that formWarning alert isn't needed.
        jQuery('input[name="idOrder"]').val(idOrder.join(','));
        form.action = "ReportConfiguration.do";
        form.submit();
    }

    jQuery(document).ready(function() {
        jQuery('#edit-report-div').hide();
    })
</script>