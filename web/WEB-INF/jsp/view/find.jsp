<%--@elvariable id="kw" type="java.lang.String"--%>
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

${sessionScope["username"]}
<a href="<c:url value="/signout" />">로그아웃</a> |
<a href="<c:url value="/edit" />">New</a><br>
<br>

<%
if (dics.size() > 0) {
	%><ul><%
	for (Dic dic : dics) {
		int dic_id = dic.getId();
		String dic_txt = dic.getTxt();
		//System.out.println("find.jsp [" + dic_txt + "]");
		%><li><a href="<c:url value="/read/" /><%= dic_id %>"><%= (dic_txt.length() > 20 ? dic_txt.substring(0,20) + "..." : dic_txt) %></a></li><%
	}
	%></ul><%
}
%>

<ul>
<li><a href="<c:url value="/upload" />">Import</a></li>
<li><a href="<c:url value="/download" />">Export</a></li>
</ul>

</body>
</html>