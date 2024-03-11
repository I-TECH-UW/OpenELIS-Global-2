<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	import="org.openelisglobal.common.util.ConfigurationProperties.Property,
			org.openelisglobal.common.action.IActionConstants,
			org.openelisglobal.login.valueholder.UserSessionData,
			org.openelisglobal.internationalization.MessageUtil"%>

<%
	UserSessionData usd = null;
	  if ( request.getSession().getAttribute(IActionConstants.USER_SESSION_DATA) != null ){
          usd = ( UserSessionData ) request.getSession().getAttribute( IActionConstants.USER_SESSION_DATA );
      }

    if ( usd != null ) {
        int timeOut = usd.getUserTimeOut();
        
        String key1 = "login.session.timeout.message";  
        String key2 = "login.error.session.message";
        
        String message1 = MessageUtil.getMessage("login.session.timeout.message");
        String message2 = MessageUtil.getMessage("login.error.session.message");
%>    

<script>
    var targetURL="<%=request.getContextPath()%>" + "/LoginPage";
    var milliseconds="<%=timeOut%>";
    
    var sec = 00;
    var min = milliseconds/60;

    function countDown() {
        sec--;
        if (sec == -01) {
            sec = 59;
            min = min - 1;
        }

        if (sec<=9) { 
            sec = "0" + sec; 
        }
        time = (min<=9 ? "0" + min : min) + ":" + sec;
        window.status = '<%=message1%> ' + time;
        SD=window.setTimeout("countDown();", 1000);
        if (min == '00' && sec == '00') { 
            sec = "00"; 
            window.clearTimeout(SD);
            window.status = '<%=message2%>';
            alert('<%=message2%>');
            window.location=targetURL; 
        }
    }
    
    countDown();

</SCRIPT> 

<%      
    }    
%>