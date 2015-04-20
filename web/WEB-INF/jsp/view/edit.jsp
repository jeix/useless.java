<%@ page session="false" %>
<%@ page import="s.dic.Dic" %>
<%
Dic dic = (Dic) request.getAttribute("dic");

response.setHeader("Pragma", "no-cache");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dic.Find</title>
</head>
<body>

<form method="POST" action="<c:url value="/save"></c:url><%= (dic.getId() > 0 ? "/" + dic.getId() : "") %>"
		accept-charset="utf-8">
<textarea name="txt" rows="5" cols="30"><%= (dic.getTxt() != null ? dic.getTxt() : "") %></textarea><br>
<input type="submit" value="Save"/>
</form>

<ul>
<%
if (dic.getId() > 0) {
	%><li><a href="<c:url value="/read/"></c:url><%= dic.getId() %>">Read</a></li><%
}
%>
<a href="<c:url value="/find"></c:url>">Find</a><br>
</ul>

</body>
</html>