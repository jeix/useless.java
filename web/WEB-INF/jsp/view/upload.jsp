<%@ page import="s.dic.Dic" %>
<%
response.setHeader("Pragma", "no-cache");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dic.Upload</title>
</head>
<body>

${sessionScope["username"]}
<a href="<c:url value="/signout" />">로그아웃</a><br>
<br>

<form method="POST" action="<c:url value="/upload" />"
		enctype="multipart/form-data" accept-charset="utf-8">
<input type="file" name="file"><br>
<input type="submit" value="업로드">
</form>

<ul>
<li><a href="<c:url value="/find" />">Find</a></li>
</ul>

</body>
</html>