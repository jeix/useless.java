<%--@elvariable id="dic" type="s.dic.Dic"--%>
<%@ page import="s.dic.Dic" %>
<%
//Dic dic = (Dic) request.getAttribute("dic");

response.setHeader("Pragma", "no-cache");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dic.Read</title>
</head>
<body>

${sessionScope["username"]}
<a href="<c:url value="/signout" />">로그아웃</a><br>
<br>

<div>${dic.txt.replaceAll("\\n", "<br>")}</div>

<ul>
<li><a href="<c:url value="/edit/${dic.id}" />">Edit</a></li>
<li><a href="<c:url value="/find" />">Find</a></li>
</ul>

</body>
</html>