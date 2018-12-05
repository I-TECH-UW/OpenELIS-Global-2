<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.List,
                us.mn.state.health.lims.common.action.IActionConstants,
				us.mn.state.health.lims.common.provider.validation.AccessionNumberValidatorFactory,
			    us.mn.state.health.lims.common.provider.validation.IAccessionNumberValidator,
				us.mn.state.health.lims.analyzerresults.action.beanitems.AnalyzerResultItem,
				us.mn.state.health.lims.common.util.IdValuePair,
                us.mn.state.health.lims.common.util.Versioning,
				us.mn.state.health.lims.common.util.StringUtil,
				org.owasp.encoder.Encode" %>



<%@ taglib uri="/tags/struts-bean"		prefix="bean" %>
<%@ taglib uri="/tags/struts-html"		prefix="html" %>
<%@ taglib uri="/tags/struts-logic"		prefix="logic" %>
<%@ taglib uri="/tags/struts-nested"	prefix="nested" %>
<%@ taglib uri="/tags/labdev-view"		prefix="app" %>
<%@ taglib uri="/tags/sourceforge-ajax" prefix="ajax" %>

<bean:define id="formName"	value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />
<bean:define id="analyzerType" name="<%=formName%>" property="analyzerType" />
<bean:define id="pagingSearch" name='<%=formName%>' property="paging.searchTermToPage"  />

<%!
	String basePath = "";
	IAccessionNumberValidator accessionNumberValidator;
	String currentAccessionNumber = "";
	boolean showAccessionNumber = false;
	boolean groupReadOnly = false;
	boolean itemReadOnly = false;
	String searchTerm = null;
%>
<%
	String path = request.getContextPath();
	basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";

	accessionNumberValidator = new AccessionNumberValidatorFactory().getValidator();
	searchTerm = request.getParameter("searchTerm");
    currentAccessionNumber = "";
    showAccessionNumber = false;
    groupReadOnly = false;
    itemReadOnly = false;
%>
<!-- N.B. testReflex.js is dependent on utilities.js so order is important  -->
<script type="text/javascript" src="<%=basePath%>scripts/utilities.js?ver=<%= Versioning.getBuildNumber() %>" ></script>
<script type="text/javascript" src="<%=basePath%>scripts/ajaxCalls.js?ver=<%= Versioning.getBuildNumber() %>" ></script>
<script type="text/javascript" src="<%=basePath%>scripts/testReflex.js?ver=<%= Versioning.getBuildNumber() %>" ></script>
<script type="text/javascript" src="scripts/OEPaging.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript" >

var dirty = false;

var pager = new OEPager('<%=formName%>', '<%= analyzerType == "" ? "" : "&type=" + Encode.forJavaScript((String) analyzerType)  %>');
pager.setCurrentPageNumber('<bean:write name="<%=formName%>" property="paging.currentPage"/>');

var pageSearch; //assigned in post load function

var pagingSearch = new Object();

<%
	for( IdValuePair pair : ((List<IdValuePair>)pagingSearch)){
		out.print( "pagingSearch[\'" + pair.getId()+ "\'] = \'" + pair.getValue() +"\';\n");
	}
%>


$jq(document).ready( function() {
			var searchTerm = '<%=Encode.forJavaScript(searchTerm)%>';

			pageSearch = new OEPageSearch( $("searchNotFound"), "td", pager );

			if( searchTerm != "null" ){
				 pageSearch.highlightSearch( searchTerm, false );
			}

			});

function /*void*/ setModifiedFlag( index ){
	$("isModified_" + index ).value = true;

}

function /*void*/ makeDirty(){
	dirty=true;
	if( typeof(showSuccessMessage) != 'undefinded' ){
		showSuccessMessage(false); //refers to last save
	}
	// Adds warning when leaving page if content has been entered into makeDirty form fields
	function formWarning(){ 
    return "<bean:message key="banner.menu.dataLossWarning"/>";
	}
	window.onbeforeunload = formWarning;
}

function  /*void*/ setMyCancelAction(form, action, validate, parameters)
{
	//first turn off any further validation
	setAction(window.document.forms[0], 'Cancel', 'no', '');
}

function  /*void*/ savePage()
{
	window.onbeforeunload = null; // Added to flag that formWarning alert isn't needed.
	var form = window.document.forms[0];
	form.action = "AnalyzerResultsSave.do"  + '<%= analyzerType == "" ? "" : "?type=" + Encode.forJavaScript((String) analyzerType)  %>';
	form.submit();

}

function validateAccessionNumberOnServer(field )
{
	new Ajax.Request (
                      'ajaxXML',  //url
                      {//options
                      method: 'get', //http method
                      parameters: 'provider=SampleEntryAccessionNumberValidationProvider&field=' + field.id + '&accessionNumber=' + field.value,
                      indicator: 'throbbing',
                      onSuccess:  processAccessionSuccess,
                      onFailure:  processAccessionFailure
                           }
                          );
}

function processAccessionSuccess(xhr)
{

	var formField = xhr.responseXML.getElementsByTagName("formfield").item(0);
	var message = xhr.responseXML.getElementsByTagName("message").item(0);
	var success = false;

	if (message.firstChild.nodeValue == "valid"){
		success = true;
	}

	setValidIndicaterOnField(success, formField.firstChild.nodeValue );
}

function processAccessionFailure(xhr)
{
	//unhandled error: someday we should be nicer to the user
}


function checkAccessionNumber( field )
{
	if ( field.value != null) {
		validateAccessionNumberOnServer( field );
	} else {
		setValidIndicaterOnField(false, name);
	}

}

function /*void*/ enableDisableCheckboxes( callingElement, index ){
	disableCheckbox( "accepted_" + index, callingElement, index);
	disableCheckbox( "rejected_" + index, callingElement, index);
	disableCheckbox( "deleted_" + index, callingElement, index);
}

function disableCheckbox( tag, callingElement){
	var box = $(tag);
	if( box && box != callingElement){
		box.checked = false;
	}
}

function /*void*/ markUpdated(){
	$("saveButtonId").disabled = false;
	makeDirty();
}

</script>
<html:hidden name="<%=formName%>" property="analyzerType"/>
<logic:notEmpty name="<%=formName%>" property="notFoundMsg">
	 <div class="indented-important-message"><bean:write name="<%=formName%>"  property="notFoundMsg"/></div>
</logic:notEmpty>
<logic:equal name="<%=formName%>" property="missingTestMsg" value="true">
     <h2><bean:message key="error.missing.test.mapping" /></h2><br/><br/>
</logic:equal>

<logic:empty name="<%=formName%>" property="notFoundMsg">
	<div class="alert alert-info" style="width: 540px">
		<div><strong><bean:message key="validation.accept"/></strong>: <bean:message key="validation.accept.explanation"/></div>
		<div><strong><bean:message key="validation.reject"/></strong>: <bean:message key="validation.reject.explanation"/></div>
		<div><strong><bean:message key="validation.delete"/></strong>: <bean:message key="validation.delete.explanation"/></div>
	</div>
</logic:empty>

<logic:notEqual name="<%=formName%>" property="paging.totalPages" value="0">
	<html:hidden styleId="currentPageID" name="<%=formName%>" property="paging.currentPage"/>
	<bean:define id="total" name="<%=formName%>" property="paging.totalPages"/>
	<bean:define id="currentPage" name="<%=formName%>" property="paging.currentPage"/>

	<%if( "1".equals(currentPage)) {%>
		<input type="button" value='<%=StringUtil.getMessageForKey("label.button.previous") %>' style="width:100px;" disabled="disabled" >
	<% } else { %>
		<input type="button" value='<%=StringUtil.getMessageForKey("label.button.previous") %>' style="width:100px;" onclick="pager.pageBack();" />
	<% } %>
	<%if( total.equals(currentPage)) {%>
		<input type="button" value='<%=StringUtil.getMessageForKey("label.button.next") %>' style="width:100px;" disabled="disabled" />
	<% }else{ %>
		<input type="button" value='<%=StringUtil.getMessageForKey("label.button.next") %>' style="width:100px;" onclick="pager.pageFoward();"   />
	<% } %>

	&nbsp;
	<bean:write name="<%=formName%>" property="paging.currentPage"/> of
	<bean:write name="<%=formName%>" property="paging.totalPages"/>
	<div class='textcontent' style="float: right" >
	<span style="visibility: hidden" id="searchNotFound"><em><%= StringUtil.getMessageForKey("search.term.notFound") %></em></span>
	<%=StringUtil.getContextualMessageForKey("result.sample.id")%> : &nbsp;
	<input type="text"
	       id="labnoSearch"
	       maxlength='<%= Integer.toString(accessionNumberValidator.getMaxAccessionLength())%>' />
	<input type="button" onclick="pageSearch.doLabNoSearch($(labnoSearch))" value='<%= StringUtil.getMessageForKey("label.button.search") %>'>
	</div>
</logic:notEqual>
<br/><br/><img src="./images/nonconforming.gif" /> = <bean:message key="result.nonconforming.item"/>
<table id="importDataTable" width="85%" border="0" cellspacing="0" >
	<tr><td><b><bean:message key="analyzer.results.results"/></b></td></tr>
	<tr>
		<th width="4%"><bean:message key="validation.accept"/></th>
		<th width="4%"><bean:message key="validation.reject"/></th>
		<th width="4%"><bean:message key="validation.delete"/></th>
		<th width="12%">
			<bean:message key="analyzer.results.labno"/>
		</th>
		<th width="15%">
			<bean:message key="analyzer.results.test"/>
		</th>
		<th width="13%">
			<bean:message key="analyzer.results.result"/>
		</th>
		<th width="8%">
			<bean:message key="result.test.date"/>
		</th>
		<th width="5%" ><bean:message key="analyzer.results.notes"/></th>
	</tr>
	<logic:iterate id="resultList" name="<%=formName%>" property="resultList" indexId="dataIndex" type="AnalyzerResultItem" >
        <%
           showAccessionNumber = !resultList.getAccessionNumber().equals(currentAccessionNumber);
           itemReadOnly = resultList.isReadOnly();
		   if( showAccessionNumber ){
					currentAccessionNumber = resultList.getAccessionNumber();
					groupReadOnly = resultList.isGroupIsReadOnly();} %>
		<html:hidden name="resultList" property="sampleGroupingNumber" indexed="true" />
		<html:hidden name="resultList" property="readOnly" indexed="true" />
		<html:hidden name="resultList" property="testResultType" indexed="true"/>
		<html:hidden name="resultList" property="testId"  styleId='<%="testId_" + dataIndex%>'/>
		<html:hidden name="resultList" property="accessionNumber"  styleId='<%="accessionNumberId_" + dataIndex%>'/>
		<logic:equal name="resultList" property="isHighlighted" value="true">
			<tr class="yellowHighlight">
		</logic:equal>
		<logic:equal name="resultList" property="isHighlighted" value="false">
			<tr>
		</logic:equal>
			<td  align="center">
			 <% if( showAccessionNumber && !groupReadOnly ){ %>
				<html:checkbox styleId='<%="accepted_" + dataIndex %>'
							   name="resultList"
							   property="isAccepted"
							   indexed="true"
							   onchange="markUpdated();"
							   onclick='<%="enableDisableCheckboxes(this, " + dataIndex + " );" %>' />
			 <% } %>
			</td>
			<td  align="center">
			<% if( showAccessionNumber && !groupReadOnly ){ %>
				<html:checkbox styleId='<%="rejected_" + dataIndex %>'
							   name="resultList"
							   property="isRejected"
							   indexed="true"
							   onchange="markUpdated();"
							   onclick='<%="enableDisableCheckboxes(this, " + dataIndex + " );" %>' />
			<% } %>
			</td>
				<td align="center">
			 <% if( showAccessionNumber  ){ %>
				<html:checkbox styleId='<%="deleted_" + dataIndex %>'
							   name="resultList"
							   property="isDeleted"
							   indexed="true"
							   onchange="markUpdated();"
							   onclick='<%="enableDisableCheckboxes(this, " + dataIndex + " );" %>' />
			 <% } %>
			</td>
			<td class='<%= resultList.getAccessionNumber()%>'>
				<% if( showAccessionNumber ){ %>
						<html:text name="resultList" property="accessionNumber" readonly="true" indexed="true" style="border-style:hidden" />
						<% if(resultList.isNonconforming()){ %>
							<img src="./images/nonconforming.gif" />
						<% } %>
				<% }  %>
			</td>
			<td>
				<span style="color: #000000"><%=resultList.getTestName()%></span>
			</td>
			<td>
			    <% if(groupReadOnly || itemReadOnly){ %>
				<html:text name="resultList"
						   property="result"
						   readonly="true"
						   size="20"
						   style="text-align:right;border-style:hidden;background-color:transparent" />
				<% }else{ %>
				<logic:equal name="resultList" property="testResultType"  value="D">
					<html:select name="resultList" 
								 styleId='<%= "resultId_" + dataIndex %>'
					             property="result" 
					             indexed="true"  
					             onchange='<%= "markUpdated();" + (resultList.isUserChoiceReflex() ? "showUserReflexChoices(" + dataIndex + ", null );" : "") %>' >
						<html:optionsCollection name="resultList" property="dictionaryResultList" label="dictEntry" value="id" />
					</html:select>
				</logic:equal>
				<logic:notEqual name="resultList"  property="testResultType"  value="D">
					<html:text name="resultList"
							   styleId='<%= "resultId_" + dataIndex %>' 
					           property="result" 
					           style="text-align:right;"
					           indexed="true"
					           onchange="markUpdated();"/>
				</logic:notEqual>
				<% if(resultList.isUserChoiceReflex()){ %>
				
				<% } %>
				<% } %>
				<%= resultList.getUnits() %>
			</td>
			<td >
			    <% if(groupReadOnly || itemReadOnly){ %>
				<html:text name="resultList"
						   property="completeDate"
						   readonly="true"
						   size="10"
						   style="border-style:hidden;text-align:right;background-color:transparent" />
				<% }else{ %>
				<html:text name="resultList"
						   property="completeDate"
						   onchange="markUpdated();"
						   indexed="true"
						   size="10"
						   style="text-align:right" />
				<% } %>
			</td>
			<td align="center">
			<% if( !(groupReadOnly || itemReadOnly)){ %>
						<logic:empty name="resultList" property="note">
						 	<img src="./images/note-add.gif"
						 	     onclick='<%= "showHideNotes(" + dataIndex + ");" %>'
						 	     id='<%="showHideButton_" + dataIndex %>'
						    />
						 </logic:empty>
						 <logic:notEmpty name="resultList" property="note">
						 	<img src="./images/note-edit.gif"
						 	     onclick='<%= "showHideNotes( " + dataIndex + ");" %>'
						 	     id='<%="showHideButton_" + dataIndex %>'
						    />
						 </logic:notEmpty>
				<html:hidden  property="hideShowFlag"  styleId='<%="hideShow_" + dataIndex %>' value="hidden" />
            <% } %>

		</td>
		</tr>
		<tr id='<%="noteRow_" +  dataIndex %>' style="display: none;">
			<td colspan="2" valign="top" align="right"><bean:message key="note.note"/>:</td>
			<td colspan="6" align="left" >
			<html:textarea styleId='<%="note_" +  dataIndex %>'
			               name="resultList"
			               indexed="true"
			           	   property="note"
			           	   cols="100"
			           	   rows="3"
			           	   onchange="markUpdated();"/>
			</td>
		</tr>
		<logic:equal name="resultList" property="userChoiceReflex"  value="true">
		<logic:equal name="resultList" property="isHighlighted" value="false">
		<tr id='<%="reflexInstruction_" + dataIndex %>' <%= resultList.getSelectionOneText() == "" ? "style='display:none'" : " " %> class="alert alert-info" >
            <td colspan="4" >&nbsp;</td>
			<td colspan="4" valign="top"  ><bean:message key="testreflex.actionselection.instructions" /></td>
		</tr>
		<tr id='<%="reflexSelection_" + dataIndex %>' <%= resultList.getSelectionOneText() == "" ? "style='display:none'" : " " %> class="alert alert-info" >
		<td colspan="4" >&nbsp;</td>
		<td colspan="4" >
				<html:radio name="resultList"
						    styleId = '<%="selectionOne_" + dataIndex %>'
							property="reflexSelectionId"
							indexed="true"
							value='<%=resultList.getSelectionOneValue() %>'
							onchange='<%="reflexChoosen(" + dataIndex + ", null, \'" + resultList.getSiblingReflexKey() +  "\');"%>' />
							<label id='<%="selectionOneLabel_" + dataIndex %>'  for='<%="selectionOne_" + dataIndex %>' ><%= resultList.getSelectionOneText() %></label>
				<br/>
				<html:radio name="resultList"
							styleId = '<%="selectionTwo_" + dataIndex %>'
							property="reflexSelectionId"
							indexed="true"
							value='<%=resultList.getSelectionTwoValue() %>'
							onchange='<%="reflexChoosen(" + dataIndex + ", null , \'" + resultList.getSiblingReflexKey() +  "\');"%>' />
							<label id='<%="selectionTwoLabel_" + dataIndex %>'  for='<%="selectionTwo_" + dataIndex %>' ><%=resultList.getSelectionTwoText() %></label>
			</td>
		</tr>
	</logic:equal>
	</logic:equal>



	</logic:iterate>

</table>
<logic:notEqual name="<%=formName%>" property="paging.totalPages" value="0">
	<html:hidden styleId="currentPageID" name="<%=formName%>" property="paging.currentPage"/>
	<bean:define id="total" name="<%=formName%>" property="paging.totalPages"/>
	<bean:define id="currentPage" name="<%=formName%>" property="paging.currentPage"/>

	<%if( "1".equals(currentPage)) {%>
		<input type="button" value='<%=StringUtil.getMessageForKey("label.button.previous") %>' style="width:100px;" disabled="disabled" >
	<% } else { %>
		<input type="button" value='<%=StringUtil.getMessageForKey("label.button.previous") %>' style="width:100px;" onclick="pager.pageBack();" />
	<% } %>
	<%if( total.equals(currentPage)) {%>
		<input type="button" value='<%=StringUtil.getMessageForKey("label.button.next") %>' style="width:100px;" disabled="disabled" />
	<% }else{ %>
		<input type="button" value='<%=StringUtil.getMessageForKey("label.button.next") %>' style="width:100px;" onclick="pager.pageFoward();"   />
	<% } %>

	&nbsp;
	<bean:write name="<%=formName%>" property="paging.currentPage"/> of
	<bean:write name="<%=formName%>" property="paging.totalPages"/>
</logic:notEqual>

	<ajax:autocomplete
  var="autocomplete"
  source="testName"
  target="selectedTestID"
  baseUrl="ajaxAutocompleteXML"
  className="autocomplete"
  parameters="testName={testName},provider=TestAutocompleteProvider,fieldName=testName,idName=id"
  indicator="indicator"
  minimumCharacters="1"
  parser="new ResponseXmlToHtmlListParser()"
  />
