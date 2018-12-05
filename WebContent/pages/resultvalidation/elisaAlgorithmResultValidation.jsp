<%@ page language="java"
	contentType="text/html; charset=utf-8"
	import="java.util.List,
			us.mn.state.health.lims.common.action.IActionConstants,
			us.mn.state.health.lims.common.provider.validation.AccessionNumberValidatorFactory,
			us.mn.state.health.lims.common.provider.validation.IAccessionNumberValidator,
			us.mn.state.health.lims.resultvalidation.bean.AnalysisItem,
			us.mn.state.health.lims.common.util.IdValuePair,
			us.mn.state.health.lims.common.util.Versioning,
			us.mn.state.health.lims.common.util.StringUtil,	
			org.owasp.encoder.Encode" %>

<%@ taglib uri="/tags/struts-bean"		prefix="bean" %>
<%@ taglib uri="/tags/struts-html"		prefix="html" %>
<%@ taglib uri="/tags/struts-logic"		prefix="logic" %>
<%@ taglib uri="/tags/labdev-view"		prefix="app" %>
<%@ taglib uri="/tags/sourceforge-ajax" prefix="ajax" %>

<bean:define id="formName"	value='<%=(String) request.getAttribute(IActionConstants.FORM_NAME)%>' />
<bean:define id="testSection"	value='<%=(String) request.getParameter("type")%>' />
<bean:define id="results" name="<%=formName%>" property="resultList" />
<bean:size id="resultCount" name="results" />
<bean:define id="pageSearchList" name='<%=formName%>' property='paging.searchTermToPage' type='List<IdValuePair>' />	

<%!
	boolean showAccessionNumber = false;
	String currentAccessionNumber = "";
	int rowColorIndex = 1;
	IAccessionNumberValidator accessionNumberValidator;
	String searchTerm = null;

%>

<%
	boolean dirty = false;
	String basePath = "";
	String path = request.getContextPath();
	basePath = request.getScheme() + "://" + request.getServerName() + ":"
	+ request.getServerPort() + path + "/";
	accessionNumberValidator = new AccessionNumberValidatorFactory().getValidator();
	searchTerm = request.getParameter("searchTerm");

%>
	
<script type="text/javascript" src="scripts/OEPaging.js?ver=<%= Versioning.getBuildNumber() %>"></script>

<script type="text/javascript" language="JavaScript1.2">

<% if( formName.equals("ResultValidationForm") ){ %>
var pager = new OEPager('<%=formName%>', '<%= testSection == "" ? "" : "&type=" + Encode.forJavaScript(testSection)  %>');
pager.setCurrentPageNumber('<bean:write name="<%=formName%>" property="paging.currentPage"/>');

var pageSearch; //assigned in post load function

var pagingSearch = new Object();

<%

	for( IdValuePair pair : ((List<IdValuePair>)pageSearchList)){
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
<% } %>

function  /*void*/ setMyCancelAction(form, action, validate, parameters)
{
	//first turn off any further validation
	setAction(window.document.forms[0], 'Cancel', 'no', '');
}

function /*void*/ enableDisableCheckboxes( matchedElement ){
	$(matchedElement).checked = false;
}

function /*void*/ setSaveButton( matchedElement ){
	$(matchedElement).checked = false;
}

function /*void*/ markUpdated(){
	$("saveButtonId").disabled = false;
}

function /*void*/ makeDirty(){
	$("saveButtonId").disabled = false;
  dirty = true;
  
	// do other work
  if( typeof(showSuccessMessage) != 'undefinded' ){
    showSuccessMessage(false); //refers to last save
  }
		
	// Adds warning when leaving page if content has been entered into makeDirty form fields
	function formWarning(){ 
		return "<bean:message key="banner.menu.dataLossWarning"/>";
	}
	window.onbeforeunload = formWarning;
}

function printWorkplan() {

	var form = window.document.forms[0];
	form.action = "PrintWorkplanReport.do";
	form.target = "_blank";
	form.submit();
}

function savePage() {

  window.onbeforeunload = null; // Added to flag that formWarning alert isn't needed.
	var form = window.document.forms[0];
	form.action = "ResultValidationSave.do";
	form.submit();
}

function /*void*/ showHideNotes( index ){
	var current = $("hideShow_" + index ).value;

	if( current == "hidden" ){
		$("showHideButton_" + index).value = '<bean:message key="label.button.hide"/>';
		$("hideShow_" + index ).value = "showing";
		$("noteRow_" + index ).show();
	}else{
		$("showHideButton_" + index).value = $("note_" + index ).value.blank() ? '<bean:message key="label.button.add"/>' : '<bean:message key="label.button.edit"/>' ;
		$("hideShow_" + index ).value = "hidden";
		$("noteRow_" + index ).hide();
	}
}

function toggleSelectAll( element ) {

	if (element.id == "selectAllAccept" ) {
		var checkboxes = $$(".accepted");
		var matchedCheckboxes = $$(".rejected");
	} else if (element.id == "selectAllReject" ) {
		var checkboxes = $$(".rejected");
		var matchedCheckboxes = $$(".accepted");
	}

	if (element.checked == true ) {
		for (var index = 0; index < checkboxes.length; ++index) {
			  var item = checkboxes[index];
			  item.checked = true;
		}
		for (var index = 0; index < matchedCheckboxes.length; ++index) {
			  var item = matchedCheckboxes[index];
			  item.checked = false;
		}
	} else if (element.checked == false ) {
		for (var index = 0; index < checkboxes.length; ++index) {
			  var item = checkboxes[index];
			  item.checked = false;
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
<% if( formName.equals("ResultValidationForm") ){ %>
<logic:notEqual name="<%=formName%>" property="paging.totalPages" value="0">
<div style="width:80%" >
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
	
	<span style="float : right" >
	<span style="visibility: hidden" id="searchNotFound"><em><%= StringUtil.getMessageForKey("search.term.notFound") %></em></span>
	<%=StringUtil.getContextualMessageForKey("result.sample.id")%> : &nbsp;
	<input type="text"
	       id="labnoSearch"
	       maxlength='<%= Integer.toString(accessionNumberValidator.getMaxAccessionLength())%>' />
	<input type="button" onclick="pageSearch.doLabNoSearch($(labnoSearch))" value='<%= StringUtil.getMessageForKey("label.button.search") %>'>
	</span>
</div>	
</logic:notEqual>
<% }  %>

<html:hidden name="<%=formName%>"  property="testSection" value="<%=Encode.forHtml(testSection)%>" />
<Table width="80%" >
	<logic:notEqual name="resultCount" value="0">
	<% if( formName.equals("WorkplanForm") ){ %>
	<tr>
		<td>
      		<html:button property="print" styleId="print"  onclick="printWorkplan();"  >
				<bean:message key="workplan.print"/>
			</html:button>
		</td>
	</tr>
	<tr>
		<td colspan="11" >
			<img src="./images/nonconforming.gif" /> = <bean:message key="result.nonconforming.item"/>		
		</td>
	</tr>
	<tr>
	    <td colspan="11"><hr/></td>
    </tr>
	<% } if( formName.equals("ResultValidationForm") ){ %>
	<tr>
		<td colspan="11" >
			<img src="./images/nonconforming.gif" /> = <bean:message key="result.nonconforming.item"/>		
		</td>
	</tr>
	<tr>
	    <td colspan="14"><hr/></td>
    </tr>
    <% }  %>
	<tr>
    	<th width="7%">
	  		<bean:message key="quick.entry.accession.number.CI"/>
		</th>
		<th width="5%">
	  		Murex
		</th>
		<th width="5%">
	  		Integral
		</th>
		<th width="5%">
	  		Vironostika
		</th>
		<th width="5%">
			Innolia
		</th>
		<th width="5%">
			Bioline
		</th>
		<th width="5%">
	  		Genie II
		</th>
		<th width="5%">
	  		Genie II 1/100
		</th>
		<th width="5%">
	  		Genie II 1/10
		</th>
		<th width="5%">
	  		Western Blot 1
		</th>
		<th width="5%">
	  		Western Blot 2
		</th>
		<th width="5%">
	  		p24 Ag
		</th>
		<% if( formName.equals("WorkplanForm") ){ %>
			<th width="10%">
		  		<bean:message key="result.validation.next.test"/>
			</th>
		<% } if( formName.equals("ResultValidationForm") ) { %>
			<th width="8%">
				<bean:message key="result.validation.final.result"/>
			</th>
			<th width="3%" align="center">
				Accept
				<br/>
				<input type="checkbox"
					name="selectAllAccept"
					value="on"
					onclick="toggleSelectAll(this);"
					onchange="markUpdated(); makeDirty();"
					id="selectAllAccept"
					class="accepted">
			</th>
			<th width="3%" align="center">
				Reject
				<br/>
				<input type="checkbox"
						name="selectAllReject"
						value="on"
						onclick="toggleSelectAll(this);"
						onchange="markUpdated(); makeDirty();"
						id="selectAllReject"
						class="rejected">
			</th>
		<%-- <th width="5%">
	  		Notes
		</th>--%>
		<% }  %>
  	</tr>

	<logic:iterate id="resultList" name="<%=formName%>"  property="resultList" indexId="index" type="AnalysisItem">
			<html:hidden name="resultList" property="sampleGroupingNumber" indexed="true" />
			<bean:define id="accessionNumber" name="resultList" property="accessionNumber"/>
     		<tr id='<%="row_" + index %>' class='<%=(rowColorIndex++ % 2 == 0) ? "evenRow" : "oddRow" %>'  >
	    		<td class='<%= accessionNumber %>'>
	      			<bean:write name="resultList" property="accessionNumber"/>
					<% if( resultList.isNonconforming()){ %>
						<img src="./images/nonconforming.gif" />
					<% } %>
	    		</td>
				<td>
					<bean:write name="resultList" property="murexResult"/>
				</td>
				<td>
					<bean:write name="resultList" property="integralResult"/>
				</td>
				<td>
					<bean:write name="resultList" property="vironostikaResult"/>
				</td>
				<td>
					<bean:write name="resultList" property="innoliaResult"/>
				</td>
				<td>
					<bean:write name="resultList" property="biolineResult"/>
				</td>
				<td>
					<bean:write name="resultList" property="genieIIResult"/>
				</td>
				<td>
					<bean:write name="resultList" property="genieII100Result"/>
				</td>
				<td>
					<bean:write name="resultList" property="genieII10Result"/>
				</td>
				<td>
					<bean:write name="resultList" property="westernBlot1Result"/>
				</td>
				<td>
					<bean:write name="resultList" property="westernBlot2Result"/>
				</td>
				<td>
					<bean:write name="resultList" property="p24AgResult"/>
				</td>
				<% if( formName.equals("WorkplanForm") ){ %>
				<td>
					<bean:write name="resultList" property="nextTest"/>
				</td>
				<% } if( formName.equals("ResultValidationForm") ) { %>
				<td>
					<bean:write name="resultList" property="finalResult"/>
				</td>
     			<td align="center">
				<html:checkbox styleId='<%="accepted_" + index %>'
							   name="resultList"
							   property="isAccepted"
							   indexed="true"
							   onchange="makeDirty();"
							   styleClass="accepted"
							   onclick='<%="enableDisableCheckboxes(\'rejected_" + index + "\');" %>' />
				</td>
				<td align="center">

					<html:checkbox styleId='<%="rejected_" + index %>'
								   name="resultList"
								   property="isRejected"
								   indexed="true"
								   onchange="makeDirty();"
								   styleClass="rejected"
								   onclick='<%="enableDisableCheckboxes(\'accepted_" + index + "\');" %>' />
				</td>
				<% } %>
				<%-- <td align="center">
					<html:button property="notes"
								 style="background-color: transparent"
								 onclick='<%= "showHideNotes( " + index + ");" %>'
								 styleId='<%="showHideButton_" + index %>'
								 tabindex='-1'
								 styleClass="textButton">
								 <logic:empty name="resultList" property="note">
								 	<bean:message key="label.button.add"/>
								 </logic:empty>
								 <logic:notEmpty name="resultList" property="note">
								 	<bean:message key="label.button.edit"/>
								 </logic:notEmpty>
								 </html:button>
					<html:hidden property="hideShowFlag"  styleId='<%="hideShow_" + index %>' value="hidden" />
				</td>
      		</tr>
      		 <tr id='<%="noteRow_" + index %>' style="display: none;">
				<td colspan="2" valign="top" align="right"><bean:message key="note.note"/>:</td>
				<td colspan="6" align="left" >
					<html:textarea styleId='<%="note_" + index %>'
								   onchange='<%="markUpdated(" + index + ");"%>'
							   	   name="resultList"
					           	   property="note"
					           	   indexed="true"
					           	   cols="100"
					           	   rows="3" />
				</td>--%>
			</tr>

  	</logic:iterate>
	<tr>
	    <td colspan="13"><hr/></td>
    </tr>
    <% if( formName.equals("WorkplanForm") ){ %>
    <tr>
		<td>
      		<html:button property="print" styleId="print"  onclick="printWorkplan();"  >
				<bean:message key="workplan.print"/>
			</html:button>
		</td>
	</tr>
	<% } %>
  	</logic:notEqual>
  	<logic:equal name="resultCount"  value="0">
		<h2><%= StringUtil.getContextualMessageForKey("result.noTestsFound") %></h2>
	</logic:equal>
</Table>
<% if( formName.equals("ResultValidationForm") ) { %>
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
<% } %>
