<%@ page language="java" contentType="text/html; charset=utf-8"
         import="us.mn.state.health.lims.common.action.IActionConstants,
                 us.mn.state.health.lims.common.util.StringUtil" %>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="app" uri="/tags/labdev-view" %>
<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

 
<bean:define id="currentValue" name="${form.formName}" property="value"/>
<bean:define id="valueEditable" name="${form.formName}" property="editable" type="java.lang.Boolean"/>
<bean:define id="siteInfoName" name="${form.formName}" property="paramName" />

<%!String allowEdits = "true";%>

<%
    if( request.getAttribute( IActionConstants.ALLOW_EDITS_KEY ) != null ){
        allowEdits = ( String ) request.getAttribute( IActionConstants.ALLOW_EDITS_KEY );
    }
%>

<script type="text/javascript">
    $jq(document).ready(function () {
	if( <%=valueEditable %> != true){
            $jq(".inputWidget").prop('disabled', true);
        }
    });


    function validateForm(form) {
        return true;
    }

    function checklogLogoFile(uploader) {
        if (uploader.value.search("\.jpg") == -1 &&
                uploader.value.search("\.png") == -1 &&
                uploader.value.search("\.gif") == -1) {
            alert("<%= StringUtil.getMessageForKey("siteInformation.logo.warning") %>");
            $jq('input[name="save"]').attr('disabled', '');
        } else {
            $jq('input[name="save"]').removeAttr('disabled');
        }
    }
</script>


<spring:message code="generic.name"/>:&nbsp;<c:out value="${form.paramName}"/><br/><br/>
<c:out value="${form.description}"/><br/><br/>
<spring:message code="generic.value"/>:&nbsp;


<logic:equal name='${form.formName}' property="valueType" value="text">
    <logic:equal name="${form.formName}" property="encrypted" value="true">
        <html:password name="${form.formName}" property="value" size="60" maxlength="120"/>
    </logic:equal>
    <logic:notEqual name="${form.formName}" property="encrypted" value="true">
        <logic:equal name='${form.formName}' property="tag" value="localization" >
            <label for="english" ><spring:message code="label.english" /></label>
            <form:input path="englishValue" size="60" maxlength="120" styleClass="inputWidget" id="english"/>
            &nbsp;&nbsp;&nbsp;&nbsp;<label for="french" ><spring:message code="label.french" /></label>
            <form:input path="frenchValue" size="60" maxlength="120" styleClass="inputWidget" id="french"/>
        </logic:equal>
        <logic:notEqual name='${form.formName}' property="tag" value="localization" >
            <form:input path="value" size="60" maxlength="120" styleClass="inputWidget"/>
        </logic:notEqual>
    </logic:notEqual>
</logic:equal>
<logic:equal name='${form.formName}' property="valueType" value="boolean">
    <html:radio name='${form.formName}' property="value" value="true" styleClass="inputWidget"><spring:message code="label.true" /></html:radio>
    <html:radio name='${form.formName}' property="value" value="false"><spring:message code="label.false" /></html:radio>
</logic:equal>
<logic:equal name='${form.formName}' property="valueType" value="dictionary">
    <html:select name='${form.formName}' property="value" styleClass="inputWidget">
        <logic:iterate id="entry" name="${form.formName}" property="dictionaryValues">
            <option value="<%=entry%>" <%=entry.equals( currentValue ) ? "selected='selected' " : "" %> ><%=entry %>
            </option>
        </logic:iterate>
    </html:select>
</logic:equal>
<logic:equal name='${form.formName}' property="valueType" value="freeText">
    <html:textarea name="${form.formName}" property="value" rows="2" style="width:50%"/>
</logic:equal>
<logic:equal name='${form.formName}' property="valueType" value="logoUpload">
    <input type="file" name="aFile" onchange="checklogLogoFile( this )" id="inputWidget"/><br/>
    <spring:message code="label.remove.image" /><input type="checkbox" id="removeImage" />
    <script type="text/javascript">
        $jq("form").attr("enctype", "multipart/form-data");
        $jq(":file").css("width", "600px");
        function setAction(form, action, validate, parameters) {
            form.action = '<%= request.getContextPath() %>' + "/logoUpload?logo=" + '<%=siteInfoName%>' + "&removeImage=" + $jq("#removeImage").is(":checked");
            form.validateDocument = new Object();
            form.validateDocument.value = validate;

            validateAndSubmitForm(form);
        }
    </script>

</logic:equal>


		