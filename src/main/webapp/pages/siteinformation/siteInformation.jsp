<%@ page language="java" contentType="text/html; charset=utf-8"
         import="us.mn.state.health.lims.common.action.IActionConstants,
                 spring.mine.internationalization.MessageUtil" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

 
<c:set var="valueEditable" value="${form.editable}"/>

<%!String allowEdits = "true";%>

<%
    if( request.getAttribute( IActionConstants.ALLOW_EDITS_KEY ) != null ){
        allowEdits = ( String ) request.getAttribute( IActionConstants.ALLOW_EDITS_KEY );
    }
%>

<script type="text/javascript">
    $jq(document).ready(function () {
    	<c:if test='${not form.editable}'>
            $jq(".inputWidget").prop('disabled', true);
       </c:if>
    });


    function validateForm(form) {
        return true;
    }

    function checklogLogoFile(uploader) {
        if (uploader.value.search("\.jpg") == -1 &&
                uploader.value.search("\.png") == -1 &&
                uploader.value.search("\.gif") == -1) {
            alert("<%= MessageUtil.getMessage("siteInformation.logo.warning") %>");
            $jq('input[name="save"]').attr('disabled', '');
        } else {
            $jq('input[name="save"]').removeAttr('disabled');
        }
    }
</script>


<spring:message code="generic.name"/>:&nbsp;<c:out value="${form.paramName}"/><br/><br/>
<c:out value="${form.description}"/><br/><br/>
<spring:message code="generic.value"/>:&nbsp;

<form:hidden path="paramName" id="siteInfoName"/>
<c:if test="${form.valueType == 'text'}">
    <c:if test="${form.encrypted}">
        <form:password path="${form.value}" size="60" maxlength="120"/>
    </c:if>
    <c:if test="${not form.encrypted}">
        <c:if test="${form.tag == 'localization'}" >
            <label for="english" ><spring:message code="label.english" /></label>
            <form:input path="englishValue" size="60" maxlength="120" cssClass="inputWidget" id="english"/>
            &nbsp;&nbsp;&nbsp;&nbsp;<label for="french" ><spring:message code="label.french" /></label>
            <form:input path="frenchValue" size="60" maxlength="120" cssClass="inputWidget" id="french"/>
        </c:if>
        <c:if test="${form.tag != 'localization'}">
            <form:input path="value" size="60" maxlength="120" cssClass="inputWidget"/>
        </c:if>
    </c:if>
</c:if>
<c:if test="${form.valueType == 'boolean'}">
	<spring:message code="label.true" var="trueMsg"/>
	<spring:message code="label.false" var="falseMsg"/>
    <form:radiobutton path="value" value="true" cssClass="inputWidget" label="${trueMsg}"/>
    <form:radiobutton path="value" value="false" label="${falseMsg}"/>
</c:if>
<c:if test="${form.valueType == 'dictionary'}">
    <form:select path="value" cssClass="inputWidget">
    	<form:options items="${form.dictionaryValues}" />
    </form:select>
</c:if>
<c:if test="${form.valueType == 'freeText'}">
    <form:textarea path="value" rows="2" style="width:50%"/>
</c:if>
<c:if test="${form.valueType == 'logoUpload'}">
    <input type="file" name="aFile" onchange="checklogLogoFile( this )" id="inputWidget"/><br/>
    <spring:message code="label.remove.image" /><input type="checkbox" id="removeImage" />
    <script type="text/javascript">
        $jq("form").attr("enctype", "multipart/form-data");
        $jq(":file").css("width", "600px");
        function setAction(form, action, validate, parameters) {
        	var siteInfoName = $jq("#siteInfoName").val()
            form.action = '<%= request.getContextPath() %>' + "/logoUpload?logo=" + siteInfoName + "&removeImage=" + $jq("#removeImage").is(":checked");
            form.validateDocument = new Object();
            form.validateDocument.value = validate;

            validateAndSubmitForm(form);
        }
    </script>

</c:if>


		