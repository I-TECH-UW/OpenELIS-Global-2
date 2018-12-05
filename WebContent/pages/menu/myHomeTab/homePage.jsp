
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<table width="100%">
<tr width="100%">
<td width="15%"><tiles:insertAttribute name="left"/></td>
<td width="85%"><tiles:insertAttribute name="right"/></td>
</tr>
<tr><td colspan="2">
<tiles:insertAttribute name="homePageContent"/>
</td>
</tr>
</table>
