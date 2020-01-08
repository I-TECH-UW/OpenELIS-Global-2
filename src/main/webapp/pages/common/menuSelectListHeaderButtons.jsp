<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	import="org.openelisglobal.common.action.IActionConstants,
			org.openelisglobal.common.util.resources.ResourceLocator,
			java.util.Locale,
			org.openelisglobal.internationalization.MessageUtil"
%>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

<%
	String paginationMessage = "";
	String totalCount = "0";
	String fromCount = "0";
	String toCount = "0";
	boolean allowDeactivate = false;

       if (request.getAttribute(IActionConstants.DEACTIVATE_DISABLED) != null) {
	      allowDeactivate = request.getAttribute(IActionConstants.DEACTIVATE_DISABLED) != "true";
       } 
 

       String addDisabled = "true";
       if (request.getAttribute(IActionConstants.ADD_DISABLED) != null) {
            addDisabled = (String)request.getAttribute(IActionConstants.ADD_DISABLED);
       }

       //This is added for testAnalyteTestResult (we need to disable ADD until test is selected
       String allowEdits = "true";
       if (request.getAttribute(IActionConstants.ALLOW_EDITS_KEY) != null) {
            allowEdits = (String)request.getAttribute(IActionConstants.ALLOW_EDITS_KEY);
       }

       String editDisabled = "false"; 
       if (request.getAttribute(IActionConstants.EDIT_DISABLED) != null) {
            editDisabled = (String)request.getAttribute(IActionConstants.EDIT_DISABLED);
       }

       boolean disableEdit = !Boolean.valueOf(allowEdits).booleanValue() && Boolean.valueOf(editDisabled).booleanValue();

      String notAllowSearching="true";
      if (request.getAttribute(IActionConstants.MENU_SEARCH_BY_TABLE_COLUMN) != null) {
          notAllowSearching = "false";

       }

       String searchColumn="";
       if (request.getAttribute(IActionConstants.MENU_SEARCH_BY_TABLE_COLUMN) != null )  {
          {
             searchColumn = (String) request.getAttribute(IActionConstants.MENU_SEARCH_BY_TABLE_COLUMN);
          }
       }
%>
<%
	if(null != request.getAttribute(IActionConstants.FORM_NAME))
	{
%>

<table width="100%" border="0" cellspacing="0" cellpadding="0" >
	<tr>
		<td class="pageTitle" align="center">
			<b> &nbsp;&nbsp;&nbsp;&nbsp;
				<c:if test="${not empty subtitle}">
					<c:out value="${subtitle}" />
				</c:if>
		 	&nbsp;&nbsp;
		 	</b>
		</td>
	</tr>
</table>
<%
	}
%>


<script>


function submitSearchForEnter(e){
    if (enterKeyPressed(e)) {
       var button = document.getElementById("searchButton");
       e.returnValue=false;
       e.preventDefault();
       e.cancel = true;
       button.click();
    }
    return false;
}

function submitSearchForClick(button){
	
     setMenuAction( button, document.getElementById("searchForm"), 'Search', 'yes', '?search=Y');
}
</script>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody >
	<tr>
			<td>&nbsp;</td>
  	</tr>
  	<tr>

			<%
				if (request.getAttribute(IActionConstants.MENU_TOTAL_RECORDS) != null) {
					totalCount = (String) request
							.getAttribute(IActionConstants.MENU_TOTAL_RECORDS);
				}
				if (request.getAttribute(IActionConstants.MENU_FROM_RECORD) != null) {
					fromCount = (String) request
							.getAttribute(IActionConstants.MENU_FROM_RECORD);
				}
				if (request.getAttribute(IActionConstants.MENU_TO_RECORD) != null) {
					toCount = (String) request
							.getAttribute(IActionConstants.MENU_TO_RECORD);
				}

				String msgResults = MessageUtil.getMessage("list.showing");
				String msgOf = MessageUtil.getMessage("list.of");

				paginationMessage = msgResults + " " + fromCount + " - " + toCount
						+ " " + msgOf + " " + totalCount;
			%>

			<td><spring:message code="label.form.selectand" /></td>


			<td colspan="4" align="right"><%=paginationMessage%></td>

	    <%
		    String previousDisabled = "false";
            String nextDisabled = "false";
            if (request.getAttribute(IActionConstants.PREVIOUS_DISABLED) != null) {
					previousDisabled = (String) request
							.getAttribute(IActionConstants.PREVIOUS_DISABLED);
            }
            if (request.getAttribute(IActionConstants.NEXT_DISABLED) != null) {
               nextDisabled = (String)request.getAttribute(IActionConstants.NEXT_DISABLED);
            }

        %>
      </tr>
      <tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
		<%-- we put "!" before disableEdit then the "Editer" button will be  always disabled at the  initialization of this page   --%>
			<td><button type="button" id="edit"
					onclick="setMenuAction(this, document.getElementById('menuForm'), '', 'yes', '?ID=');return false;"
					name="edit"
					<%if ( !disableEdit ) {%>
					disabled="disabled"
					<%} %>>
					<spring:message code="label.button.edit" />
				</button> &nbsp; 
				<button type="button" id="deactivate"
					onclick="setMenuAction(this, document.getElementById('menuForm'), 'Delete', 'yes', '?ID=');return false;"
					name="deactivate"
					disabled="disabled"> 
					<spring:message code="label.button.deactivate" />
  			</button>
				
				&nbsp;
				<spring:message code="label.form.or" />&nbsp; 
				
				<button type="button" id="add"
					onclick="setMenuAction(this, document.getElementById('menuForm'), '', 'yes', '?ID=0');return false;"
					name="add"
					<%if ( Boolean.valueOf(addDisabled).booleanValue() ) {%>
					disabled="disabled"
					<%} %>
					>
					<spring:message code="label.button.add" />
  			</button>
	   </td>

			<c:if test="${not empty menuSearchByTableColumn}">
			<form:form name="${form.formName}" 
				   action="${form.formAction}" 
				   modelAttribute="form" 
				   onSubmit="return submitForm(this);" 
				   method="${form.formMethod}"
				   id="searchForm">
				<td></td>

				<td align="right"><spring:message code="label.form.searchby" /> <spring:message code="<%=searchColumn%>" /> 
				<form:input path="searchString" onkeypress="submitSearchForEnter(event);"
						size="20" maxlength="20"
						disabled="<%=Boolean.valueOf(notAllowSearching).booleanValue()%>" />


					<button type="button" name="search" id="searchButton"
						onclick="submitSearchForClick(this);return false;"
						<%if ( Boolean.valueOf(notAllowSearching).booleanValue() ) {%>
						disabled="disabled"
						<%} %>
						>
  			       <spring:message code="label.button.search"/>
  		       </button>
          </td>
          </form:form>


       </c:if>

			<td></td>


      
			<td align="right">
			   <button type="button" id="previous"
					onclick="setMenuAction(this, document.getElementById('menuForm'), '', 'yes', '?paging=1');return false;"
					name="previous"
					<%if ( Boolean.valueOf(previousDisabled).booleanValue() ) {%>
					disabled="disabled"
					<%} %>
					>
					<spring:message code="label.button.previous" />
				</button> &nbsp; 
				<button type="button" id="next"
					onclick="setMenuAction(this, document.getElementById('menuForm'), '', 'yes', '?paging=2');return false;"
					name="next"
					<%if ( Boolean.valueOf(nextDisabled).booleanValue() ) {%>
					disabled="disabled"
					<%} %>
					>
					<spring:message code="label.button.next" />
				</button>
       </td>
 	</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
   </tbody>
</table>

<script>
   var textName = document.getElementById ("searchString");
 
   if (textName != null && textName.value != null)
   {  textName.focus();
      textName.value+='';
   }

function output() {
 var total = 0;
   for ( var i = 0; i < document.getElementById("menuForm").length; i++ ) {
      if ( document.getElementById("menuForm").elements[ i ].type == 'checkbox' ) {
         if (document.getElementById("menuForm").elements[ i ].checked == true ) {
            total++;
         }
      }
   }
     if(total == 0){
    	 document.getElementById("edit").disabled=true;
    	 <% if( allowDeactivate){ %>
    	 	document.getElementById("deactivate").disabled=true;
    	 <% } %>	
     } else if(total == 1){
    	 document.getElementById("edit").disabled=false;
    	 <% if( allowDeactivate){ %>
    	 	document.getElementById("deactivate").disabled=false;
    	 <% } %>		
     } else {
    	 document.getElementById("edit").disabled=true;
    	 <% if( allowDeactivate){ %>
    	 	document.getElementById("deactivate").disabled=false;
    	 <% } %>		
     }
}
</script> 
