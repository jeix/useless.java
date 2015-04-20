<%@ page import="java.util.List" %>
<%@ page import="s.dic.Dic" %>
<%
String kw = (String) request.getAttribute("kw");
@SuppressWarnings("unchecked")
List<Dic> dics = (List<Dic>) request.getAttribute("dics");

response.setHeader("Pragma", "no-cache");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dic.Find</title>
</head>
<body>

<%= (String) session.getAttribute("username") %>
<a href="<c:url value="/signout" />">로그아웃</a> |
<a href="<c:url value="/edit" />">New</a><br>
<br>

<%
if (dics.size() > 0) {
	%><ul><%
	for (Dic dic : dics) {
		int dic_id = dic.getId();
		String dic_txt = dic.getTxt();
		System.out.println("find.jsp [" + dic_txt + "]");
		%><li><a href="<c:url value="/read/" /><%= dic_id %>"><%= (dic_txt.length() > 10 ? dic_txt.substring(0,10) : dic_txt) %></a></li><%
	}
	%></ul><%
}
%>

</body>
</html>