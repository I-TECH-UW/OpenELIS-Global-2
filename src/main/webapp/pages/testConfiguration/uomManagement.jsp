<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         import="org.openelisglobal.internationalization.MessageUtil"
        %>


<%--
  ~ The contents of this file are subject to the Mozilla Public License
  ~ Version 1.1 (the "License"); you may not use this file except in
  ~ compliance with the License. You may obtain a copy of the License at
  ~ http://www.mozilla.org/MPL/
  ~
  ~ Software distributed under the License is distributed on an "AS IS"
  ~ basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
  ~ License for the specific language governing rights and limitations under
  ~ the License.
  ~
  ~ The Original Code is OpenELIS code.
  ~
  ~ Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
  --%>

<form id="mainForm">
    <script type="text/javascript">

        function submitAction(target) {
            var form = document.getElementById("mainForm");
            form.action = target;
            form.submit();
        }


    </script>
    <br>
    <input type="button" value="<%= MessageUtil.getMessage("banner.menu.administration") %>"
           onclick="submitAction('MasterListsPage');"
           class="textButton"/>&rarr;
    <input type="button" value="<%= MessageUtil.getMessage("configuration.test.management") %>"
           onclick="submitAction('TestManagementConfigMenu');"
           class="textButton"/>&rarr;
    <%=MessageUtil.getMessage( "configuration.uom.manage" )%>

    <ul>
        <li><input type="button" value="<%= MessageUtil.getMessage("configuration.uom.create") %>"
                   onclick="submitAction('UomCreate');"
                   class="textButton"/><br>
            </li>       
     </ul>


</form>