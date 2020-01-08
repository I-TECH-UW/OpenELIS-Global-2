<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="org.openelisglobal.common.action.IActionConstants"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="ajax" uri="/tags/ajaxtags" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


	<table border="0" cellpadding="0" cellspacing="4" width="100%">
		<tbody valign="middle">
			<tr>
				<td align="middle">
				<spring:message code="footer.button.finish" var="finishButton"></spring:message>
				<input type="button" onclick="finish();" id="finishButtonId" value="${finishButton}" />
				</td>
			</tr>
		</tbody>
	</table>

<script type="text/javascript">

<%if( request.getAttribute(IActionConstants.FWD_SUCCESS) != null &&
      ((Boolean)request.getAttribute(IActionConstants.FWD_SUCCESS)) ) { %>
if( typeof(showSuccessMessage) != 'undefined' ){
	showSuccessMessage( true );
}
<% } %>

</script>