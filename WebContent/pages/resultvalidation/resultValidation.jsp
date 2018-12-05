<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="org.apache.commons.validator.GenericValidator, 
			us.mn.state.health.lims.common.action.IActionConstants,
			us.mn.state.health.lims.common.provider.validation.AccessionNumberValidatorFactory,
			us.mn.state.health.lims.common.provider.validation.IAccessionNumberValidator,
			us.mn.state.health.lims.common.util.IdValuePair,
			us.mn.state.health.lims.common.util.StringUtil,
			us.mn.state.health.lims.common.util.Versioning,
			us.mn.state.health.lims.common.services.TypeOfTestResultService.ResultType,
		    java.text.DecimalFormat,
			java.util.List,
			us.mn.state.health.lims.resultvalidation.bean.AnalysisItem,
			us.mn.state.health.lims.common.util.ConfigurationProperties,
			us.mn.state.health.lims.common.util.ConfigurationProperties.Property,
			us.mn.state.health.lims.common.services.TypeOfTestResultService,
			org.owasp.encoder.Encode" %>

<%@ taglib uri="/tags/struts-bean"		prefix="bean" %>
<%@ taglib uri="/tags/struts-html"		prefix="html" %>
<%@ taglib uri="/tags/struts-logic"		prefix="logic" %>
<%@ taglib uri="/tags/labdev-view"		prefix="app" %>
<%@ taglib uri="/tags/sourceforge-ajax" prefix="ajax" %>

<bean:define id="formName"	value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />
<bean:define id="testSection"	value='<%=request.getParameter("type")%>' />
<bean:define id="testName"	value='<%=request.getParameter("test")%>' />
<bean:define id="results" name="<%=formName%>" property="resultList" />
<bean:define id="pagingSearch" name='<%=formName%>' property="paging.searchTermToPage" type="List<IdValuePair>" />
<bean:define id="testSectionsByName" name='<%=formName%>' property="testSectionsByName" />
<bean:size id="resultCount" name="results" />

<%!
	boolean showAccessionNumber = false;
	String currentAccessionNumber = "";
	int rowColorIndex = 2;
	IAccessionNumberValidator accessionNumberValidator;
	String searchTerm = null;
	//boolean showTestSectionSelect = false;
%>
<%

	String basePath;
	String path = request.getContextPath();
	basePath = request.getScheme() + "://" + request.getServerName() + ":"
	+ request.getServerPort() + path + "/";
	currentAccessionNumber="";
	accessionNumberValidator = new AccessionNumberValidatorFactory().getValidator();
	searchTerm = request.getParameter("searchTerm");
	String url = request.getAttribute("javax.servlet.forward.servlet_path").toString();	
	//showTestSectionSelect = !ConfigurationProperties.getInstance().isPropertyValueEqual(Property.configurationName, "CI RetroCI");
	
		
%>


<script type="text/javascript" src="<%=basePath%>scripts/utilities.js?ver=<%= Versioning.getBuildNumber() %>" ></script>
<script type="text/javascript" src="<%=basePath%>scripts/math-extend.js?ver=<%= Versioning.getBuildNumber() %>" ></script>
<script type="text/javascript" src="<%=basePath%>scripts/utilities.js?ver=<%= Versioning.getBuildNumber() %>" ></script>
<script type="text/javascript" src="scripts/OEPaging.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<script type="text/javascript" src="scripts/jquery.asmselect.js?ver=<%= Versioning.getBuildNumber() %>"></script>
<link rel="stylesheet" type="text/css" href="css/jquery.asmselect.css?ver=<%= Versioning.getBuildNumber() %>" />
<script type="text/javascript" src="<%=basePath%>scripts/testReflex.js?ver=<%= Versioning.getBuildNumber() %>" ></script>
<script type="text/javascript" src="<%=basePath%>scripts/multiselectUtils.js?ver=<%= Versioning.getBuildNumber() %>" ></script>


<script type="text/javascript" >
var dirty = false;
var pager = new OEPager('<%=formName%>', '<%= testSection == "" ? "" : "&type=" + Encode.forJavaScript(testSection)  %>' + '<%= "&test=" + Encode.forJavaScript(testName)  %>');
pager.setCurrentPageNumber('<bean:write name="<%=formName%>" property="paging.currentPage"/>');

var pageSearch; //assigned in post load function

var pagingSearch = {};

<%
	for( IdValuePair pair : ((List<IdValuePair>)pagingSearch)){
		out.print( "pagingSearch[\'" + pair.getId()+ "\'] = \'" + pair.getValue() +"\';\n");
	}
%>

$jq(document).ready( function() {
			var searchTerm = '<%=Encode.forJavaScript(searchTerm)%>';
            loadMultiSelects();
            $jq("select[multiple]").asmSelect({
                removeLabel: "X"
            });

            $jq("select[multiple]").change(function(e, data) {
                handleMultiSelectChange( e, data );
            });

            pageSearch = new OEPageSearch( $("searchNotFound"), "td", pager );
			
			if( searchTerm != "null" ){
				 pageSearch.highlightSearch( searchTerm, false );
			}
            $jq(".asmContainer").css("display","inline-block");
			});


function removeReflexesFor(){
    //no-op
}
function showUserReflexChoices(){
    //no-op
}
function  /*void*/ setMyCancelAction(form, action, validate, parameters)
{
	//first turn off any further validation
	setAction(window.document.forms[0], 'Cancel', 'no', '');
}

function /*void*/ enableDisableCheckboxes( matchedElement, groupingNumber ){
	$(matchedElement).checked = false;
	
	$("sampleRejected_" + groupingNumber).checked = false;
	$("sampleAccepted_" + groupingNumber).checked = false;
	$("selectAllReject").checked = false;
	$("selectAllAccept").checked = false;
}

function /*void*/ acceptSample(element, groupingNumber ){
	$$(".accepted_" + groupingNumber).each( function(item){
		item.checked = element.checked;
		}
	);

	$$(".rejected_" + groupingNumber).each( function(item){
		item.checked = false;
		}
	);
	
	$("sampleRejected_" + groupingNumber).checked = false;
	$("selectAllReject").checked = false;
	$("selectAllAccept").checked = false;
}

function /*void*/ rejectSample(element, groupingNumber ){
	$$(".accepted_" + groupingNumber).each( function(item){
		item.checked = false;
		}
	);

	$$(".rejected_" + groupingNumber).each( function(item){
		item.checked = element.checked;
		}
	);
	
	$("sampleAccepted_" + groupingNumber).checked = false;
	$("selectAllAccept").checked = false;
	$("selectAllReject").checked = false;
}

function /*void*/ markUpdated(){
	$("saveButtonId").disabled = false;
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

function savePage() {
    if( !confirm("<%=StringUtil.getMessageForKey("validation.save.message")%>")){
        return;
    }

  window.onbeforeunload = null; // Added to flag that formWarning alert isn't needed.
	var form = window.document.forms[0];
	form.action = "ResultValidationSave.do" + '<%= "?type=" + Encode.forJavaScript(testSection) + "&test=" + Encode.forJavaScript(testName) %>';
	form.submit();
}

function submitTestSectionSelect( element ) {
	
	var testSectionNameIdHash = [];

	<%
		for( IdValuePair pair : (List<IdValuePair>) testSectionsByName){
			out.print( "testSectionNameIdHash[\"" + pair.getId()+ "\"] = \"" + pair.getValue() +"\";\n");
		}
	%>
		window.location.href = "ResultValidationSave.do?testSectionId=" + element.value + "&test=&type=" + testSectionNameIdHash[element.value];
	
}

function toggleSelectAll( element ) {
    var index, item, checkboxes,matchedCheckboxes;

	if (element.id == "selectAllAccept" ) {
		checkboxes = $$(".accepted");
		matchedCheckboxes = $$(".rejected");
	} else if (element.id == "selectAllReject" ) {
		checkboxes = $$(".rejected");
		matchedCheckboxes = $$(".accepted");
	}

	if (element.checked == true ) {
		for (index = 0; index < checkboxes.length; ++index) {
			  item = checkboxes[index];
			  item.checked = true;
		}
		for (index = 0; index < matchedCheckboxes.length; ++index) {
			  item = matchedCheckboxes[index];
			  item.checked = false;
		}
	} else if (element.checked == false ) {
		for (index = 0; index < checkboxes.length; ++index) {
			  item = checkboxes[index];
			  item.checked = false;
		}
	}

}

function updateLogValue(element, index ){
	var logField = $("log_" + index );

	if( logField ){
		var logValue = Math.baseLog(element.value).toFixed(2);

		if( isNaN(logValue) ){
			jQuery(logField).html("--");
		}else{
			jQuery(logField).html(logValue);
		}
	}
}

function trim(element, significantDigits){
    if( isNaN(significantDigits) || isNaN(element.value) ){
        return;
    }

    element.value = round(element.value, significantDigits);
}

function updateReflexChild( group){
 	var reflexGroup = $$(".reflexGroup_" + group);
	var childReflex = $$(".childReflex_" + group);
 	var i, childId, rowId, resultIds = "", values="", requestString = "";

 	if( childReflex ){
 		childId = childReflex[0].id.split("_")[1];
 		
		for( i = 0; i < reflexGroup.length; i++ ){
			if( childReflex[0] != reflexGroup[i]){
				rowId = reflexGroup[i].id.split("_")[1];
				resultIds += "," + $("resultIdValue_" + rowId).value;
				values += "," + reflexGroup[i].value;
			}
		}
		
		requestString +=   "results=" +resultIds.slice(1) + "&values=" + values.slice(1) + "&childRow=" + childId;

		new Ajax.Request (
                      'ajaxQueryXML',  //url
                      {//options
                      method: 'get', //http method
                      parameters: 'provider=TestReflexCD4Provider&' + requestString,
                      indicator: 'throbbing',
                      onSuccess:  processTestReflexCD4Success,
                      onFailure:  processTestReflexCD4Failure
                           }
                          );
 	}

}

function /*void*/ processTestReflexCD4Failure(){
	alert("failed");
}

function /*void*/ processTestReflexCD4Success(xhr)
{
	//alert( xhr.responseText );
	var formField = xhr.responseXML.getElementsByTagName("formfield").item(0);
	var message = xhr.responseXML.getElementsByTagName("message").item(0);
	var childRow, value;


	if (message.firstChild.nodeValue == "valid"){
		childRow = formField.getElementsByTagName("childRow").item(0).childNodes[0].nodeValue;
		value = formField.getElementsByTagName("value").item(0).childNodes[0].nodeValue;
		
		if( value && value.length > 0){
			$("resultId_" + childRow).value = value;
		}

	}

}

/*It is not clear why this is needed but what it prevents is the browser trying to do a submit on 'enter'  Other pages handle this
differently.  Overrides formTemplate.jsp handleEnterEvent
*/
function /*boolean*/ handleEnterEvent(){
	return false;
}

</script>

<% if( !(url.contains("RetroC"))){ %>
<div id="searchDiv" class="colorFill"  >
<div id="PatientPage" class="colorFill" style="display:inline" >
<h2><bean:message key="sample.entry.search"/></h2>
	<table width="30%">
		<tr>
			<td width="50%" align="right" >
				<%= StringUtil.getMessageForKey("workplan.unit.types") %>
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

<logic:notEqual name="resultCount" value="0">
<div  style="width:80%" >
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
	<bean:write name="<%=formName%>" property="paging.currentPage"/> <bean:message key="report.pageNumberOf" />
	<bean:write name="<%=formName%>" property="paging.totalPages"/>
	<span style="float : right" >
	<span style="visibility: hidden" id="searchNotFound"><em><%= StringUtil.getMessageForKey("search.term.notFound") %></em></span>
	<%=StringUtil.getContextualMessageForKey("result.sample.id")%> : &nbsp;
	<input type="text"
	       id="labnoSearch"
	       placeholder='<bean:message key="sample.search.scanner.instructions"/>'
	       maxlength='<%= Integer.toString(accessionNumberValidator.getMaxAccessionLength())%>' />
	<input type="button" onclick="pageSearch.doLabNoSearch($(labnoSearch))" value='<%= StringUtil.getMessageForKey("label.button.search") %>'>
	</span>
</div>
</logic:notEqual>
<html:hidden name="<%=formName%>"  property="testSection" value="<%=Encode.forHtml(testSection)%>" />
<html:hidden name="<%=formName%>"  property="testName" value="<%=Encode.forHtml(testName)%>" />
<logic:notEqual name="resultCount" value="0">
<Table style="width:80%" >
    <tr>
		<th colspan="3" style="background-color: white;width:15%;">
			<img src="./images/nonconforming.gif" /> = <%= StringUtil.getContextualMessageForKey("result.nonconforming.item")%>
		</th>
		<th style="text-align:center;width:3%;" style="background-color: white">&nbsp;
				<bean:message key="validation.accept.all" />
			<input type="checkbox"
				name="selectAllAccept"
				value="on"
				onclick="toggleSelectAll(this);"
				onchange="markUpdated(); makeDirty();"
				id="selectAllAccept"
				class="accepted acceptAll">
		</th>
		<th  style="text-align:center;width:3%;" style="background-color: white">&nbsp;
		<bean:message key="validation.reject.all" />
			<input type="checkbox"
					name="selectAllReject"
					value="on"
					onclick="toggleSelectAll(this);"
					onchange="markUpdated(); makeDirty();"
					id="selectAllReject"
					class="rejected rejectAll">
		</th>
		<th style="background-color: white;width:5%;">&nbsp;</th>
  	</tr>
</Table>
</logic:notEqual>
<Table style="width:80%" >
	<logic:notEqual name="resultCount" value="0">
	<tr>
	    <td colspan="9"><hr/></td>
    </tr>    
	<tr>
    	<th>
			<bean:message key="resultsentry.accessionNumber"/>
		</th>
		<th>
	  		<bean:message key="sample.entry.project.testName"/>
		</th>
		<th>
			<bean:message key="analyzer.results.result"/>
		</th>
		<th style="text-align:center">
			<bean:message key="validation.accept" />
		</th>
		<th style="text-align:center">
			<bean:message key="validation.reject" />
		</th>
		<th>
			<bean:message key="result.notes"/>
		</th>
  	</tr>

	<logic:iterate id="resultList" name="<%=formName%>"  property="resultList" indexId="index" type="AnalysisItem">
			 <% showAccessionNumber = !resultList.getAccessionNumber().equals(currentAccessionNumber);
				   if( showAccessionNumber ){
					currentAccessionNumber = resultList.getAccessionNumber();
					rowColorIndex++;}  %>
			<html:hidden name="resultList" property="sampleGroupingNumber" indexed="true" />
			<html:hidden name="resultList" property="noteId" indexed="true" />
			<html:hidden name="resultList" property="resultId"  indexed="true" styleId='<%="resultIdValue_" + index%>'/>
            <html:hidden name="resultList" property="hasQualifiedResult" indexed="true" styleId='<%="hasQualifiedResult_" + index %>' />

			<%if( resultList.isMultipleResultForSample() && showAccessionNumber ){ 
			     showAccessionNumber = false; %>
			<tr  class='<%=(rowColorIndex % 2 == 0) ? "evenRow" : "oddRow" %>'  >
				<td colspan="3" class='<%= currentAccessionNumber %>'>
	      			<bean:write name="resultList" property="accessionNumber"/>
	    		</td>
	    		<td style="text-align:center">
					<html:checkbox styleId='<%="sampleAccepted_" + resultList.getSampleGroupingNumber() %>'
								   name="resultList"
								   property="isAccepted"
								   styleClass="accepted"
								   indexed="true"
								   onchange="markUpdated(); makeDirty();" 
								   onclick='<%="acceptSample( this, \'" + resultList.getSampleGroupingNumber() + "\');" %>' />
				</td>
				<td style="text-align:center">
					<html:checkbox styleId='<%="sampleRejected_" + resultList.getSampleGroupingNumber() %>'
									   name="resultList"
									   property="isRejected"
									   styleClass="rejected"
									   indexed="true"
									   onchange="markUpdated(); makeDirty();"
									   onclick='<%="rejectSample( this, \'" + resultList.getSampleGroupingNumber() + "\');" %>' />
				</td>
				<td>&nbsp;</td>
			</tr>
			<% } %>
     		<tr id='<%="row_" + index %>' class='<%=(rowColorIndex % 2 == 0) ? "evenRow" : "oddRow" %>'  >
				<% if( showAccessionNumber ){%>
	    		<td class='<%= currentAccessionNumber %>' >
	      			<bean:write name="resultList" property="accessionNumber"/>
	    		</td>
	    		<% }else{ %>
	    			<td></td>
	    		<% } %>
				<td>
					<bean:write name="resultList" property="testName"/>
					<% if( resultList.isNonconforming()){ %>
						<img src="./images/nonconforming.gif" />
					<% } %>
				</td>
				<td>
					<% if( TypeOfTestResultService.ResultType.NUMERIC.matches(resultList.getResultType())){%>
						<% if( resultList.isReadOnly() ){%>
							<div
								class='results-readonly <%= (resultList.getIsHighlighted() ? "invalidHighlight " : " ") + (resultList.isReflexGroup() ? "reflexGroup_" + resultList.getSampleGroupingNumber()  : "")  +  
							              (resultList.isChildReflex() ? " childReflex_" + resultList.getSampleGroupingNumber(): "") %> '
							    id='<%= "resultId_" + index %>'
								name='<%="resultList[" + index + "].result" %>' >
							<%= resultList.getResult() %>
							</div>
						<% }else{ %>
	    					<input type="text" 
					           name='<%="resultList[" + index + "].result" %>' 
					           size="6" 
					           value='<%= resultList.getResult() %>' 
					           id='<%= "resultId_" + index %>'
							   class='<%= (resultList.getIsHighlighted() ? "invalidHighlight " : " ") + (resultList.isReflexGroup() ? "reflexGroup_" + resultList.getSampleGroupingNumber()  : "")  +  
							              (resultList.isChildReflex() ? " childReflex_" + resultList.getSampleGroupingNumber(): "") %> ' 
							   onchange='<%=  "markUpdated(); makeDirty(); updateLogValue(this, " + index + "); trim(this, " + resultList.getSignificantDigits() + ");" +
								                (resultList.isReflexGroup() && !resultList.isChildReflex() ? "updateReflexChild(" + resultList.getSampleGroupingNumber()  +  " ); " : "")  %>'/>
	    				<% } %>
						<bean:write name="resultList" property="units"/>
					<% }else if( TypeOfTestResultService.ResultType.DICTIONARY.matches(resultList.getResultType())){ %>
						<select name="<%="resultList[" + index + "].result" %>" 
						        id='<%="resultId_" + index%>' 
						        onchange= '<%= "markUpdated(); makeDirty();" +
						         (resultList.getQualifiedDictionaryId() != null ? "showQuanitiy( this, "+ index + ", " + resultList.getQualifiedDictionaryId() : "" ) + ");"  %>'  >
								<logic:iterate id="optionValue" name="resultList" property="dictionaryResults" type="IdValuePair" >
									<option value='<%=optionValue.getId()%>'  <%if(optionValue.getId().equals(resultList.getResult())) out.print("selected"); %>  >
										<bean:write name="optionValue" property="value"/>
									</option>
								</logic:iterate>
						</select>
						<input type="text" 
			           			name='<%="resultList[" + index + "].qualifiedResultValue" %>' 
			           			value='<%= resultList.getQualifiedResultValue() %>' 
			           			id='<%= "qualifiedDict_" + index %>'
			           			style = '<%= "display:" + (resultList.isHasQualifiedResult() ? "inline" : "none") %>'
					   			<%= resultList.isReadOnly() ? "disabled='disabled'" : ""%> />
                    <bean:write name="resultList" property="units"/>
					<% }else  if( TypeOfTestResultService.ResultType.MULTISELECT.matches(resultList.getResultType())){%>
                    <!-- multiple results -->
                    <select name="<%="resultList[" + index + "].multiSelectResultValues" %>"
                            id='<%="resultId_" + index + "_0"%>'
                            multiple="multiple"
                            <%=resultList.isReadOnly()? "disabled=\'disabled\'" : "" %>
                            title='<%= StringUtil.getMessageForKey("result.multiple_select")%>'
                            onchange='<%="markUpdated(" + index + "); "  +
						               ((!GenericValidator.isBlankOrNull(resultList.getMultiSelectResultValues())) ? "showNote( " + index + ");" : "") +
						               (resultList.getQualifiedDictionaryId() != null ? "showQuanitiy( this, "+ index + ", " + resultList.getQualifiedDictionaryId() + ", \"M\" );" :"")%>' >
                        <logic:iterate id="optionValue" name="resultList" property="dictionaryResults" type="IdValuePair" >
                            <option value='<%=optionValue.getId()%>'
                                    <%if(StringUtil.textInCommaSeperatedValues(optionValue.getId(), resultList.getMultiSelectResultValues())) out.print("selected"); %>  >
                                <bean:write name="optionValue" property="value"/>
                            </option>
                        </logic:iterate>
                    </select>
                    <html:hidden name="resultList" property="multiSelectResultValues" indexed="true" styleId='<%="multiresultId_" + index%>' styleClass="multiSelectValues"  />
                    <input type="text"
                           name='<%="resultList[" + index + "].qualifiedResultValue" %>'
                           value='<%= resultList.getQualifiedResultValue() %>'
                           id='<%= "qualifiedDict_" + index %>'
                           style = '<%= "display:" + ( resultList.isHasQualifiedResult() ? "inline" : "none") %>'
                            <%= resultList.isReadOnly() ? "disabled='disabled'" : ""%>
                           onchange='<%="markUpdated(" + index + ");" %>'
                            />
                    <bean:write name="resultList" property="units"/>
                    <%}else  if( TypeOfTestResultService.ResultType.CASCADING_MULTISELECT.matches(resultList.getResultType())){%>
                    <!-- cascading multiple results -->
                    <div id='<%="cascadingMulti_" + index + "_0"%>' class='<%="cascadingMulti_" + index %>' >
                    <input type="hidden" id='<%="divCount_" + index %>' value="0" >
                    <select name="<%="resultList[" + index + "].multiSelectResultValues" %>"
                            id='<%="resultId_" + index + "_0"%>'
                            multiple="multiple"
                            <%=resultList.isReadOnly()? "disabled=\'disabled\'" : "" %>
                            title='<%= StringUtil.getMessageForKey("result.multiple_select")%>'
                            onchange='<%="markUpdated(" + index + "); "  +
						               ((!GenericValidator.isBlankOrNull(resultList.getMultiSelectResultValues())) ? "showNote( " + index + ");" : "") +
						               (resultList.getQualifiedDictionaryId() != null ? "showQuanitiy( this, "+ index + ", " + resultList.getQualifiedDictionaryId() + ", \"M\" );" :"")%>' >
                        <logic:iterate id="optionValue" name="resultList" property="dictionaryResults" type="IdValuePair" >
                            <option value='<%=optionValue.getId()%>' >
                                <bean:write name="optionValue" property="value"/>
                            </option>
                        </logic:iterate>
                    </select>
                        <input class='<%="addMultiSelect" + index%>' type="button" value="+" onclick='<%="addNewMultiSelect(" + index + ", this);showNewNote( " + index + ");"%>'/>
                        <input class='<%="removeMultiSelect" + index%>' type="button" value="-" onclick='<%="removeMultiSelect(\"target\");showNewNote( " + index + ");"%>' style="display: none" />
                        <html:hidden name="resultList" property="multiSelectResultValues" indexed="true" styleId='<%="multiresultId_" + index%>'  styleClass="multiSelectValues" />
                    <input type="text"
                           name='<%="resultList[" + index + "].qualifiedResultValue" %>'
                           value='<%= resultList.getQualifiedResultValue() %>'
                           id='<%= "qualifiedDict_" + index %>'
                           style = '<%= "display:" + ( resultList.isHasQualifiedResult() ? "inline" : "none") %>'
                            <%= resultList.isReadOnly() ? "disabled='disabled'" : ""%>
                           onchange='<%="markUpdated(" + index + ");" %>'
                            />
                    <bean:write name="resultList" property="units"/>
                     </div>
                    <% }else if( TypeOfTestResultService.ResultType.ALPHA.matches(resultList.getResultType())){%>
                    <app:text name="resultList"
                              indexed="true"
                              property="result"
                              size="16"
                              allowEdits='<%= !resultList.isReadOnly() %>'
                              styleId='<%="resultId_" + index %>'
                              onchange='<%="markUpdated(); makeDirty(); updateLogValue(this, " + index + ");" %>'/>
                    <bean:write name="resultList" property="units"/>

                    <%}else if( TypeOfTestResultService.ResultType.REMARK.matches(resultList.getResultType())){ %>
						<app:textarea name="resultList"
								  indexed="true"
								  property="result"
								  rows="2"
								  allowEdits='<%= !resultList.isReadOnly() %>'
								  styleId='<%="resultId_" + index %>'
								  onkeyup='value = value.substr(0, 200); markUpdated();'
								  />
						<bean:write name="resultList" property="units"/>
			 			<br/>200 char max
					<%}%>
					<% if(resultList.isDisplayResultAsLog()){ %>
						<br/>
						<div id='<%= "log_" + index %>'
								class='results-readonly'>
								<% try{
												Double value = Math.log10(Double.parseDouble(resultList.getResult()));
												DecimalFormat twoDForm = new DecimalFormat("##.##");
												out.print(Double.valueOf(twoDForm.format(value)));
												}catch(Exception e){
													out.print("--");} %>		
						</div> log
					<% } %>
				</td>
				<% if(resultList.isShowAcceptReject()){ %>
				<td style="text-align:center">
					<html:checkbox styleId='<%="accepted_" + index %>'
								   name="resultList"
								   property="isAccepted"
								   styleClass='<%= "accepted accepted_" + resultList.getSampleGroupingNumber() %>'
								   indexed="true"
								   onchange="markUpdated(); makeDirty();"
								   onclick='<%="enableDisableCheckboxes(\'rejected_" + index + "\', \'" + resultList.getSampleGroupingNumber() + "\');" %>' />
				</td>
				<td style="text-align:center">
					<html:checkbox styleId='<%="rejected_" + index %>'
									   name="resultList"
									   property="isRejected"
									   styleClass='<%= "rejected rejected_" + resultList.getSampleGroupingNumber() %>'
									   indexed="true"
									   onchange="markUpdated(); makeDirty();"
									   onclick='<%="enableDisableCheckboxes(\'accepted_" + index + "\', \'" + resultList.getSampleGroupingNumber() + "\');" %>' />
				</td>
				<% }else{ %>
				<td><bean:message key="label.computed"/></td><td><bean:message key="label.computed"/></td>
				<% } %>
				<td style="text-align:center">
					<% if( !resultList.isReadOnly()){ %>
				    	<logic:empty name="resultList" property="note">
						 	<img src="./images/note-add.gif"
						 	     onclick='<%= "showHideNotes( " + index + ");" %>'
						 	     id='<%="showHideButton_" + index %>'
						    />
						 </logic:empty>
						 <logic:notEmpty name="resultList" property="note">
						 	<img src="./images/note-edit.gif"
						 	     onclick='<%= "showHideNotes( " + index + ");" %>'
						 	     id='<%="showHideButton_" + index %>'
						    />
						 </logic:notEmpty>
                    <input type="hidden" value="hidden" id='<%="hideShow_" + index %>' >
					<% } %>
				</td>
      		</tr>
      		<logic:notEmpty name="resultList" property="pastNotes">
			<tr  >
				<td colspan="2" style="text-align:right;vertical-align:top"><bean:message key="label.prior.note" />: </td>
				<td colspan="6" style="text-align:left">
				<%= resultList.getPastNotes() %>
				</td>
			</tr>
	</logic:notEmpty>
      		<tr id='<%="noteRow_" + index %>'
				style="display: none;">
				<td colspan="2" style="text-align:right;vertical-align:top;"><bean:message key="note.note"/>:</td>
				<td colspan="6" style="text-align:left" >
					<html:textarea styleId='<%="note_" + index %>'
								   onchange='<%="markUpdated(" + index + ");"%>'
							   	   name="resultList"
					           	   property="note"
					           	   indexed="true"
					           	   cols="100"
					           	   rows="3" />
				</td>
			</tr>
  	</logic:iterate>
	<tr>
	    <td colspan="8"><hr/></td>
    </tr>



  	</logic:notEqual>
  	
	<logic:equal  name="<%=formName %>" property="displayTestSections" value="true">
		<logic:equal name="resultCount"  value="0">
			<logic:notEmpty name="<%=formName %>" property="testSectionId">
			<h2><%= StringUtil.getContextualMessageForKey("result.noTestsFound") %></h2>
			</logic:notEmpty>
		</logic:equal>
	</logic:equal>

	<logic:notEqual  name="<%=formName %>" property="displayTestSections" value="true">
		<logic:equal name="resultCount"  value="0">
		<h2><%= StringUtil.getContextualMessageForKey("result.noTestsFound") %></h2>
		</logic:equal>
	</logic:notEqual>
	  	
</Table>

   <logic:notEqual name="resultCount" value="0">
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