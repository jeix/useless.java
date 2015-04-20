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
<title>Dic.Read</title>
</head>
<body>

<div><%= dic.getTxt() %></div>

<ul>
<li><a href="<c:url value="/edit/"></c:url><%= dic.getId() %>">Edit</a></li>
<a href="<c:url value="/find"></c:url>">Find</a><br>
</ul>

</body>
</html>