<%--@elvariable id="dic" type="s.dic.Dic"--%>
<%@ page import="s.dic.Dic" %>
<%
Dic dic = (Dic) request.getAttribute("dic");

response.setHeader("Pragma", "no-cache");
%>
<%!
private static String break_lines(String txt) {
	return txt.replaceAll("\\n", "<br>");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="title.read"/></title>
</head>
<body>

<c:out value="${sessionScope['username']}" /> |
<a href="<c:url value="/signout" />"><fmt:message key="action.signout"/></a><br>
<br>

<%--
<div><%= dic.getTxt().replaceAll("\\n", "<br>") %></div>
--%>
<div><%= break_lines(dic.getTxt()) %></div>

<ul>
<li><a href="<c:url value="/edit/${dic.id}" />"><fmt:message key="action.edit"/></a></li>
<li><a href="<c:url value="/find" />"><fmt:message key="action.find"/></a></li>
</ul>

</body>
</html>