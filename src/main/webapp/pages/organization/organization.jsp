<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="us.mn.state.health.lims.common.action.IActionConstants,
			us.mn.state.health.lims.common.formfields.FormFields,
			us.mn.state.health.lims.common.formfields.FormFields.Field,
			us.mn.state.health.lims.common.util.StringUtil" %>

<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/labdev-view" prefix="app" %>
<%@ taglib uri="/tags/sourceforge-ajax" prefix="ajax"%>


<div id="sound"></div>

<bean:define id="formName" value='<%= (String)request.getAttribute(IActionConstants.FORM_NAME) %>' />
<%--bugzilla 2069--%>

<%!

String allowEdits = "true";
String path = "";
String basePath = "";

boolean useOrgLocalAbbrev = true;
boolean useState = true;
boolean useZip = true;
boolean useMLS = true;
boolean useInlineOrganizationTypes = false;
boolean useAddressInfo = true;
boolean useCLIA = true;
boolean useParent = true;
boolean useShortName = true;
boolean useMultiUnit = true;
boolean showId = true;
boolean showCity = true;
boolean showDepartment = false;
boolean showCommune = false;
boolean showVillage = false;
%>
<%
path = request.getContextPath();
basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

if (request.getAttribute(IActionConstants.ALLOW_EDITS_KEY) != null) {
 allowEdits = (String)request.getAttribute(IActionConstants.ALLOW_EDITS_KEY);
}

useOrgLocalAbbrev = FormFields.getInstance().useField( Field.OrgLocalAbrev);
useState = FormFields.getInstance().useField( Field.OrgState);
useZip = FormFields.getInstance().useField( Field.ZipCode);
useMLS = FormFields.getInstance().useField( Field.MLS);
useInlineOrganizationTypes = FormFields.getInstance().useField(Field.InlineOrganizationTypes);
useAddressInfo = FormFields.getInstance().useField(Field.OrganizationAddressInfo);
useCLIA = FormFields.getInstance().useField(Field.OrganizationCLIA);
useParent = FormFields.getInstance().useField(Field.OrganizationParent);
useShortName = FormFields.getInstance().useField(Field.OrganizationShortName);
useMultiUnit = FormFields.getInstance().useField(Field.OrganizationMultiUnit);
showId = FormFields.getInstance().useField(Field.OrganizationOrgId);
showCity = FormFields.getInstance().useField(Field.ADDRESS_CITY );
showDepartment = FormFields.getInstance().useField(Field.ADDRESS_DEPARTMENT );
showCommune= FormFields.getInstance().useField(Field.ADDRESS_COMMUNE );
showVillage = FormFields.getInstance().useField(Field.ADDRESS_VILLAGE );

%>

<script language="JavaScript1.2">
var useOrganizationTypes = <%= useInlineOrganizationTypes %>;
function validateForm(form) {
	if( useOrganizationTypes ){
		var orgTypeList = $$(".orgTypeId");

		for( var i = 0; i < orgTypeList.length; ++i ){
			if(orgTypeList[i].checked ){
				return validateOrganizationForm(form);
			}
		}
		alert("<%= StringUtil.getMessageForKey("error.organizationType.required")%>");
		return false;
	}

 return validateOrganizationForm(form);
}
</script>

<table>
		<% if(showId){ %>
		<tr>
						<td class="label">
							<bean:message key="organization.id"/>:
						</td>
						<td>
							<app:text name="<%=formName%>" property="id" allowEdits="false"/>
						</td>
		</tr>
		<% } %>
		<% if( useParent ){ %>
		<tr>
			<td class="label">
				<bean:message key="organization.parent"/>:
			</td>
			<td>
	   			<html:text styleId="parentOrgName" size="30" name="<%=formName%>" property="parentOrgName" />
	   			<span id="indicator1" style="display:none;"><img src="<%=basePath%>images/indicator.gif"/></span>

	   			<input id="selectedOrgId" name="selectedOrgId" type="hidden" size="30" />
						 <%--html:select name="<%=formName%>" property="selectedOrgId">
							   	   <app:optionsCollection
										name="<%=formName%>"
							    		property="parentOrgs"
										label="organizationName"
										value="id"
							        	filterProperty="isActive"
							        	filterValue="N"
							 			allowEdits="true"
							/>
                         </html:select--%>
							<%--html:text styleId="organizationName" styleClass="form-autocomplete" size="30" name="<%=formName%>" property="organizationName" /> &nbsp;&nbsp;&nbsp;&nbsp;Org ID: <input id="selectedOrgId" name="selectedOrgId" type="text" size="30" /--%>

			</td>
		</tr>
		<% } %>
		<% if( useOrgLocalAbbrev ){ %>
		<tr>
						<td class="label">
						    <bean:message key="organization.localAbbreviation"/>:<span class="requiredlabel">*</span>
						</td>
						<td>
						 	<html:text name="<%=formName%>" property="organizationLocalAbbreviation" />
						</td>
		</tr>
		<% } %>
		<tr>
						<td class="label">
							<bean:message key="organization.organizationName"/>:<span class="requiredlabel">*</span>
						</td>
						<td>
							<html:text name="<%=formName%>" property="organizationName" />
						</td>
		</tr>
		<% if(useShortName){ %>
		<tr>
						<td class="label">
						    <%= StringUtil.getContextualMessageForKey("organization.short") %>
						</td>
						<td>
						 	<html:text name="<%=formName%>" property="shortName" />
						</td>
		</tr>
		<% } %>
        <tr>
						<td class="label">
							<bean:message key="organization.isActive"/>:<span class="requiredlabel">*</span>
						</td>
						<td width="1">
							<html:text name="<%=formName%>" property="isActive" size="1" onblur="this.value=this.value.toUpperCase()"/>
						</td>
		</tr>
		<% if( useAddressInfo){ %>
		<% if(useMultiUnit){ %>
		<tr>
						<td class="label">
							<bean:message key="organization.multipleUnit"/>:
						</td>
						<td>
							<html:text name="<%=formName%>" property="multipleUnit" />
						</td>
		</tr>
		<% } %>
		<tr>
						<td class="label">
							<bean:message key="organization.streetAddress"/>:
						</td>
						<td>
						   	<app:text name="<%=formName%>" property="streetAddress" styleClass="text" size="35"/>
						</td>
		</tr>
		<% if( showCity){ %>
		<tr>
						<td class="label">
							<bean:message key="organization.city"/>:
						</td>
						<td>
							<html:text styleId="city" size="30" name="<%=formName%>" property="city" />
				   			<span id="indicator2" style="display:none;"><img src="<%=basePath%>images/indicator.gif"/></span>
							<input id="cityID" name="cityID" type="hidden" size="30" />
						</td>
		</tr>
	    <% } %>
		<% if( showDepartment ){ %>
		<tr>
			<td class="label">
				<bean:message key="organization.department" />
			</td>
			<td>
				<html:select name='<%=formName%>' property="department" >
					<option value="0"></option>
				<html:optionsCollection name='<%=formName %>' property="departmentList" value="id" label="dictEntry"/>
				</html:select>
			</td>
		</tr>
		<% } %>
		<% if( showCommune){ %>
		<tr>
			<td class="label">
				<bean:message key="organization.commune" />
			</td>
			<td>
				<html:text name='<%=formName%>' property="commune" />
			</td>
		</tr>
		<% } %>
		<% if( showVillage){ %>
		<tr>
			<td class="label">
				<bean:message key="organization.village" />
			</td>
			<td>
				<html:text name='<%=formName%>' property="village" />
			</td>
		</tr>
		<% } %>
		<% if( useState ){ %>
		<tr>
						<td class="label">
							<bean:message key="organization.state"/>:<span class="requiredlabel">*</span>
						</td>
						<td>
							<%--html:text property="state" /--%>

						  <html:select name="<%=formName%>" property="state">
					   	  <app:optionsCollection
										name="<%=formName%>"
							    		property="states"
										label="state"
										value="state"  />

                       	   </html:select>
						</td>
		</tr>
		<% } %>
		<% if( useZip ){ %>
		<tr>
						<td class="label">
							<bean:message key="organization.zipCode"/>:<span class="requiredlabel">*</span>
						</td>
						<td>
							<html:text name="<%=formName%>" property="zipCode" />
						</td>
		</tr>
		<% } %>
		<% } %>
		<tr>
						<td class="label">
							<bean:message key="organization.internetAddress"/>:
						</td>
						<td>
							<html:text name="<%=formName%>" property="internetAddress" />
						</td>
		</tr>
		<% if( useMLS ) { %>
		<tr>
						<td class="label">
							<bean:message key="organization.mls.sentinelLab"/>:<span class="requiredlabel">*</span>
						</td>
						<td>
							<html:text name="<%=formName%>" property="mlsSentinelLabFlag" />
						</td>
		</tr>
		<% } %>
		<% if( useCLIA ){ %>
		<tr>
						<td class="label">
							<bean:message key="organization.clia.number"/>:
						</td>
						<td>
							<html:text name="<%=formName%>" property="cliaNum" />
						</td>
		</tr>
		<% } %>
		<% if( useMLS ){ %>
		<tr>
						<td class="label">
							<bean:message key="organization.mls.lab"/>:<span class="requiredlabel">*</span>
						</td>
						<td>
							<html:text name="<%=formName%>" property="mlsLabFlag" onblur="this.value=this.value.toUpperCase()"/>
						</td>
		</tr>
		<% } %>
 		<tr>
		<td>&nbsp;</td>
		</tr>
</table>
<% if( useInlineOrganizationTypes ){ %>

<h3><%=StringUtil.getContextualMessageForKey("organization.type")%><span class="requiredlabel">*</span></h3><br/>

<table >
	<tr>
		<th >&nbsp;</th>
		<th ><bean:message key="generic.name"/></th>
	</tr>
<logic:iterate  name="<%=formName%>" property="orgTypes" id="organizationType" type="us.mn.state.health.lims.organization.valueholder.OrganizationType" >
	<tr>
	<td>
		<html:multibox name="<%=formName %>" property="selectedTypes" styleClass="orgTypeId">
			<bean:write name="organizationType" property="id" />
		</html:multibox>
	</td>
	<td>
		<bean:write name="organizationType" property="localizedName"  />&nbsp;
	</td>
	</tr>
	</logic:iterate>
</table>


<% } %>
<% if( useAddressInfo){ %>
  <ajax:autocomplete
  source="city"
  target="cityID"
  baseUrl="ajaxAutocompleteXML"
  className="autocomplete"
  parameters="city={city},provider=CityAutocompleteProvider,fieldName=city,idName=id"
  indicator="indicator2"
  minimumCharacters="1"
  />
<% } %>
  <%--postFunction="playSound"--%>
<% if( useParent){ %>
  <ajax:autocomplete
  source="parentOrgName"
  target="selectedOrgId"
  baseUrl="ajaxAutocompleteXML"
  className="autocomplete"
  parameters="organizationName={parentOrgName},provider=OrganizationAutocompleteProvider,fieldName=organizationName,idName=id"
  indicator="indicator1"
  minimumCharacters="1" />
<% } %>


<html:javascript formName="organizationForm"/>


