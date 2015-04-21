<%--@elvariable id="signin_failed" type="java.lang.String"--%>
<%--@elvariable id="username" type="java.lang.String"--%>
<%@ page import="s.dic.Dic" %>
<%
String signin_failed = (String) request.getAttribute("signin_failed");
String name = (String) request.getAttribute("username");

response.setHeader("Pragma", "no-cache");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dic.Signin</title>
</head>
<body>

<%
if (signin_failed != null) {
	%><b>${signin_failed}</b><br><br><%
}
%>

<form method="POST" action="<c:url value="/signin" />">
<label for="username">이름</label><br>
<input type="text" name="username" value="<%= (name != null ? name : "") %>"><br>
<label for="password">패스워드</label><br>
<input type="password" name="password"><br>
<br>
<input type="submit" value="로그인"/>
</form>

<ul>
<li><a href="<c:url value="/signup" />">가입</a></li>
</ul>

</body>
</html>