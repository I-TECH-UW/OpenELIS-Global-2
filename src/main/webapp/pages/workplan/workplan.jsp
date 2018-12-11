<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="java.util.List,
			us.mn.state.health.lims.common.action.IActionConstants,
			java.util.Collection,
			us.mn.state.health.lims.test.beanItems.TestResultItem,
			us.mn.state.health.lims.common.util.IdValuePair,
			us.mn.state.health.lims.common.util.StringUtil,
			us.mn.state.health.lims.common.util.ConfigurationProperties,
			us.mn.state.health.lims.common.util.ConfigurationProperties.Property,
			org.owasp.encoder.Encode" %>
<%@ taglib uri="/tags/struts-bean"		prefix="bean" %>
<%@ taglib uri="/tags/struts-html"		prefix="html" %>
<%@ taglib uri="/tags/struts-logic"		prefix="logic" %>
<%@ taglib uri="/tags/labdev-view"		prefix="app" %>
<%@ taglib uri="/tags/sourceforge-ajax" prefix="ajax" %>

<bean:define id="formName"	value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />
<bean:define id="workplanType"	value='<%=(String) request.getParameter("type")%>' />
<bean:define id="tests" name="<%=formName%>" property="workplanTests" />
<bean:size id="testCount" name="tests" />

<% if( !workplanType.equals("test") && !workplanType.equals("panel") ){ %>
<bean:define id="testSectionsByName" name="<%=formName%>" property="testSectionsByName" />
	<script type="text/javascript" >
	var testSectionNameIdHash = [];
	<%
		for( IdValuePair pair : (List<IdValuePair>) testSectionsByName){
			out.print( "testSectionNameIdHash[\"" + pair.getId()+ "\"] = \"" + pair.getValue() +"\";\n");
		}
	%>
	</script>
<% } %>

<%!
	boolean showAccessionNumber = false;
	String currentAccessionNumber = "";
	int rowColorIndex = 2;
%>
<%

	String basePath = "";
	String path = request.getContextPath();
	basePath = request.getScheme() + "://" + request.getServerName() + ":"
	+ request.getServerPort() + path + "/";
	currentAccessionNumber = "";

%>

<script type="text/javascript" >

function  /*void*/ setMyCancelAction(form, action, validate, parameters)
{
	//first turn off any further validation
	setAction(window.document.forms[0], 'Cancel', 'no', '');
}

function disableEnableTest(checkbox, index){

	if (checkbox.checked) {
		$("row_" + index).style.background = "#cccccc";
	}else {
		checkbox.checked = "";
		$("row_" + index).style.backgroundColor = "";
	}
}

function submitTestSectionSelect( element ) {

	window.location.href = "WorkPlanByTestSection.do?testSectionId=" + element.value + "&type=" + testSectionNameIdHash[element.value] ;
}

function printWorkplan() {

	var form = window.document.forms[0];
	form.action = "PrintWorkplanReport.do";
	form.target = "_blank";
	form.submit();
}

</script>
<% if( !workplanType.equals("test") && !workplanType.equals("panel") ){ %>
<div id="searchDiv" class="colorFill"  >
<div id="PatientPage" class="colorFill" style="display:inline" >
<input type="hidden" name="testName"	value='<%=Encode.forHtml(workplanType)%>' />
<h2><bean:message key="sample.entry.search"/></h2>
	<table width="30%">
		<tr>
			<td width="50%" align="right" >
				<bean:write name="<%=formName %>" property="searchLabel"/>
			</td>
			<td>
			<html:select name='<%= formName %>' property="testSectionId" 
				 onchange="submitTestSectionSelect(this);" >
				<app:optionsCollection name="<%=formName%>" property="testSections" label="value" value="id" />
			</html:select>
	   		</td>
		</tr>
	</table>
	<br/>
	<h1>
		
	</h1>
</div>
</div>
<% }%>

<br/>
<logic:notEqual name="testCount" value="0">
<bean:size name='<%= formName %>' property="workplanTests" id="size" />
<html:button property="print" styleId="print"  onclick="printWorkplan();"  >
	<bean:message key="workplan.print"/>
</html:button>
<br/><br/>
<bean:message key="label.total.tests"/> = <bean:write name="size"/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img src="./images/nonconforming.gif" /> = <bean:message key="result.nonconforming.item"/>
<br/><br/>
<Table width="90%" border="0" cellspacing="0">
	<tr>
		<th width="5%" style="text-align: left;">
			<bean:message key="label.button.remove"/>
		</th>
		<% if( workplanType.equals("test") ){ %>
			<th width="3%">&nbsp;</th>
		<% } %>
    	<th width="10%" style="text-align: left;">
    		<%= StringUtil.getContextualMessageForKey("quick.entry.accession.number") %>
		</th>
		<% if(ConfigurationProperties.getInstance().isPropertyValueEqual(Property.SUBJECT_ON_WORKPLAN, "true")){ %>
		<th width="5%" style="text-align: left;">
			<%= StringUtil.getContextualMessageForKey("patient.subject.number") %>
		</th>	
		<% } %>
		<% if(ConfigurationProperties.getInstance().isPropertyValueEqual(Property.NEXT_VISIT_DATE_ON_WORKPLAN, "true")){ %>
	    <th width="5%" style="text-align: left;">
	    	<bean:message key="sample.entry.nextVisit.date"/>
	    </th>
	    <% } %>
		<% if( !workplanType.equals("test") ){ %>
		<th width="3%">&nbsp;</th>
		<th width="30%" style="text-align: left;">
			<% if(ConfigurationProperties.getInstance().isPropertyValueEqual(Property.configurationName, "Haiti LNSP")){ %>
                <bean:message key="sample.entry.project.patient.and.testName"/>
	        <% } else { %>
	  		   <bean:message key="sample.entry.project.testName"/>
	  		<% } %>  
		</th>
		<% } %>
		<th width="20%" style="text-align: left;">
	  		<bean:message key="sample.receivedDate"/>&nbsp;&nbsp;
		</th>
  	</tr>

	<logic:iterate id="workplanTests" name="<%=formName%>"  property="workplanTests" indexId="index" type="TestResultItem">
			<% showAccessionNumber = !workplanTests.getAccessionNumber().equals(currentAccessionNumber);
				   if( showAccessionNumber ){
					currentAccessionNumber = workplanTests.getAccessionNumber();
					rowColorIndex++; } %>
     		<tr id='<%="row_" + index %>' class='<%=(rowColorIndex % 2 == 0) ? "evenRow" : "oddRow" %>'  >
     			<td id='<%="cell_" + index %>'>
     			<% if (!workplanTests.isServingAsTestGroupIdentifier()) { %>
					<html:checkbox name="workplanTests"
						   property="notIncludedInWorkplan"
						   styleId='<%="includedCheck_" + index %>'
						   styleClass="includedCheck"
						   indexed="true"
						   onclick='<%="disableEnableTest(this," + index + ");" %>' />
			    <% } %>
				</td>
				<% if( workplanType.equals("test") ){ %>
				<td>
					<logic:equal name="workplanTests" property="nonconforming" value="true">
						<img src="./images/nonconforming.gif" />
					</logic:equal>
				</td>	
				<% } %>
	    		<td>
	      		<% if( showAccessionNumber ){%>
	      			<bean:write name="workplanTests" property="accessionNumber"/>
				<% } %>
	    		</td>
	    		<% if( ConfigurationProperties.getInstance().isPropertyValueEqual(Property.SUBJECT_ON_WORKPLAN, "true")){ %>
	    		<td>
	    			<% if(showAccessionNumber ){ %>
	    			<bean:write name="workplanTests" property="patientInfo"/>
	    			<% } %>
	    		</td>
	    		<% } %>
	    			<% if(ConfigurationProperties.getInstance().isPropertyValueEqual(Property.NEXT_VISIT_DATE_ON_WORKPLAN, "true")){ %>
	    			<td>
	    			<% if(showAccessionNumber ){ %>
		    			<bean:write name="workplanTests" property="nextVisitDate"/>
		    		<% } %>	
	    			</td>
	    		<% } %>
	    		<% if( !workplanType.equals("test") ){ %>
	    		<td>
		    		<logic:equal name="workplanTests" property="nonconforming" value="true">
						<img src="./images/nonconforming.gif" />
					</logic:equal>
				</td>
				<td>
					<%=workplanTests.getTestName() %> 
				</td>
				<% } %>
	    		<td>
	      			<bean:write name="workplanTests" property="receivedDate"/>
	    		</td>
      		</tr>
  	</logic:iterate>
	<tr>
	    <td colspan="8"><hr/></td>
    </tr>
	<tr>
		<td>
      		<html:button property="print" styleId="print"  onclick="printWorkplan();"  >
				<bean:message key="workplan.print"/>
			</html:button>
		</td>
	</tr>
</Table>
</logic:notEqual>

<% if( workplanType.equals("test") || workplanType.equals("panel") ){ %>
	<logic:equal name="testCount"  value="0">
		<h2><%= StringUtil.getContextualMessageForKey("result.noTestsFound") %></h2>
	</logic:equal>
<% } else { %>
	<logic:equal name="testCount"  value="0">
		<logic:notEmpty name="<%=formName %>" property="testSectionId">
		<h2><%= StringUtil.getContextualMessageForKey("result.noTestsFound") %></h2>
		</logic:notEmpty>
	</logic:equal>
<% } %>


