<%@ page import="s.dic.Dic" %>
<%
String signup_failed = (String) request.getAttribute("signup_failed");
String name = (String) request.getAttribute("username");
String mail = (String) request.getAttribute("email");

response.setHeader("Pragma", "no-cache");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dic.Signup</title>
</head>
<body>

<%
if (signup_failed != null) {
	%><b><%= signup_failed %></b><br><br><%
}
%>

<form method="POST" action="<c:url value="/signup" />">
<label for="username">이름</label><br>
<input type="text" name="username" value="<%= (name != null ? name : "") %>"><br>
<label for="email">이메일</label><br>
<input type="text" name="email" value="<%= (mail != null ? mail : "") %>"><br>
<label for="password">패스워드</label><br>
<input type="password" name="password"><br>
<label for="repassword">패스워드</label><br>
<input type="password" name="repassword"><br>
<br>
<input type="submit" value="가입"/>
</form>

<ul>
<li><a href="<c:url value="/signin" />">로그인</a></li>
</ul>

</body>
</html>