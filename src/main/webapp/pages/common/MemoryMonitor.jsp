<%@ page import="java.lang.management.*,
				java.util.*" %>
<html>
<head>
  <title>JVM Memory Monitor</title>
</head>
<body>

<table border="0" width="100%">
<tr><td colspan="2" align="center"><h3>Memory MXBean</h3></td></tr>
<tr><td
width="200">Heap Memory Usage</td><td><%=ManagementFactory.getMemoryMXBean().getHeapMemoryUsage()%><br/>
Available: <%=java.text.NumberFormat.getIntegerInstance().format((ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getMax() - ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed())/1024) %> K
</td></tr>
<tr><td>Non-Heap Memory
Usage</td><td><%=ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage()%><br/>
Available: <%=java.text.NumberFormat.getIntegerInstance().format((ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getMax() - ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getUsed())/1024) %> K
</td></tr>

<tr><td colspan="2">&nbsp;</td></tr>
<tr><td colspan="2" align="center"><h3>Memory Pool MXBeans</h3></td></tr>
<%
        Iterator<MemoryPoolMXBean> iter = ManagementFactory.getMemoryPoolMXBeans().iterator();
        while (iter.hasNext()) {
            MemoryPoolMXBean item = iter.next();
%>
<tr><td colspan="2">
<table border="0" width="100%" style="border: 1px #98AAB1 solid;">
<tr><td colspan="2" align="center" width="70%"><b><%= item.getName() %></b></td><td width="30%">Available</td></tr>

<tr><td width="200">Type</td><td><%= item.getType() %></td></tr>

<tr><td>Usage</td><td><%= item.getUsage() %></td>
<td><%=java.text.NumberFormat.getIntegerInstance().format((item.getUsage().getMax() - item.getUsage().getUsed())/1024) %> K</td></tr>

<tr><td>Peak Usage</td><td><%= item.getPeakUsage() %></td></tr>

<tr><td>Collection Usage</td><td><%= item.getCollectionUsage() %></td></tr>
</table>
</td></tr>
<tr><td colspan="2">&nbsp;</td></tr>
</table>
<%
}
%>


</body>
</html>
