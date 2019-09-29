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

<p>Report configuration</p>

<script>
    var reports= [];
    <c:forEach items="${form.reportList}" var="report">
    var report = {};
    report['name'] = '${report.name}';
    report['type'] = '${report.id}';
    report['category'] = '${report.category}';
    report['menuElementId'] = '${report.menuElementId}';
    report['visible'] = '${report.visible}';
    report['sortOrder'] = '${report.sortOrder}';
    reports.push(report);
    </c:forEach>
</script>
<table>
    <tr>
        <th>Name</th><th>Type</th><th>Category</th><th>Visible</th>
    </tr>
    <c:forEach items="${form.reportList}" var="report">
        <tr>
            <td><c:out value="${report.name}" /></td>
            <td><c:out value="${report.type}" /></td>
            <td><c:out value="${report.category}" /></td>
            <td><c:out value="${report.visible}" /></td>
        </tr>
    </c:forEach>
</table>

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
        <form:select path="currentReport.type" cssClass="form-control">
            <form:option value="">Select one</form:option>
            <form:options items="${form.types}" itemLabel="value" itemValue="id" />
        </form:select>
    </div>
    <div class="form-div">
        <label>Type</label>
        <form:select path="currentReport.category" cssClass="form-control">
            <form:option value="">Select one</form:option>
            <form:options items="${form.categories}" itemLabel="value" itemValue="id"/>
        </form:select>
    </div>
    <div class="form-div">
        <label>Visible</label>
        <input type="checkbox" name="visible" />
    </div>

    <ul>

    </ul>

    <button>
        Save
    </button>
    <button>
        Cancel
    </button>

</form:form>

<script>
    function setReportOrderPanel(reportId, type, category) {
        var visibleReports = [];
        for (var i = 0; i < reports.length; i++) {
            var report = reports[i];
            if (report.type == type && report.category == category) {
                visibleReports.push(report);
            }
        }
        var html = [];
        for (var i = 0; i < visibleReports.length; i++) {
            html.push('<li class=""></li>')
        }
    }
</script>