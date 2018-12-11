<%@ page language="java" contentType="text/html; charset=utf-8"
         import="us.mn.state.health.lims.common.action.IActionConstants,
                 us.mn.state.health.lims.common.util.StringUtil" %>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/labdev-view" prefix="app" %>

<bean:define id="formName" value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>'/>
<bean:define id="currentValue" name="<%=formName %>" property="value"/>
<bean:define id="valueEditable" name="<%=formName %>" property="editable" type="java.lang.Boolean"/>
<bean:define id="siteInfoName" name="<%=formName%>" property="paramName" />

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


<bean:message key="generic.name"/>:&nbsp;<bean:write name="<%=formName %>" property="paramName"/><br/><br/>
<bean:write name="<%=formName %>" property="description"/><br/><br/>
<bean:message key="generic.value"/>:&nbsp;


<logic:equal name='<%=formName %>' property="valueType" value="text">
    <logic:equal name="<%=formName%>" property="encrypted" value="true">
        <html:password name="<%=formName%>" property="value" size="60" maxlength="120"/>
    </logic:equal>
    <logic:notEqual name="<%=formName%>" property="encrypted" value="true">
        <logic:equal name='<%=formName %>' property="tag" value="localization" >
            <label for="english" ><bean:message key="label.english" /></label>
            <html:text name="<%=formName%>" property="englishValue" size="60" maxlength="120" styleClass="inputWidget" styleId="english"/>
            &nbsp;&nbsp;&nbsp;&nbsp;<label for="french" ><bean:message key="label.french" /></label>
            <html:text name="<%=formName%>" property="frenchValue" size="60" maxlength="120" styleClass="inputWidget" styleId="french"/>
        </logic:equal>
        <logic:notEqual name='<%=formName %>' property="tag" value="localization" >
            <html:text name="<%=formName%>" property="value" size="60" maxlength="120" styleClass="inputWidget"/>
        </logic:notEqual>
    </logic:notEqual>
</logic:equal>
<logic:equal name='<%=formName %>' property="valueType" value="boolean">
    <html:radio name='<%=formName %>' property="value" value="true" styleClass="inputWidget"><bean:message key="label.true" /></html:radio>
    <html:radio name='<%=formName %>' property="value" value="false"><bean:message key="label.false" /></html:radio>
</logic:equal>
<logic:equal name='<%=formName %>' property="valueType" value="dictionary">
    <html:select name='<%=formName %>' property="value" styleClass="inputWidget">
        <logic:iterate id="entry" name="<%=formName %>" property="dictionaryValues">
            <option value="<%=entry%>" <%=entry.equals( currentValue ) ? "selected='selected' " : "" %> ><%=entry %>
            </option>
        </logic:iterate>
    </html:select>
</logic:equal>
<logic:equal name='<%=formName %>' property="valueType" value="freeText">
    <html:textarea name="<%=formName%>" property="value" rows="2" style="width:50%"/>
</logic:equal>
<logic:equal name='<%=formName %>' property="valueType" value="logoUpload">
    <input type="file" name="aFile" onchange="checklogLogoFile( this )" id="inputWidget"/><br/>
    <bean:message key="label.remove.image" /><input type="checkbox" id="removeImage" />
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


		