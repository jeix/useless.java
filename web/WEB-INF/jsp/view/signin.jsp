<%--@elvariable id="signin_failed" type="java.lang.String"--%>
<%--@elvariable id="username" type="java.lang.String"--%>
<%@ page import="s.dic.Dic" %>
<%--
String signin_failed = (String) request.getAttribute("signin_failed");
String username = (String) request.getAttribute("username");

response.setHeader("Pragma", "no-cache");
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="title.signin"/></title>
</head>
<body>

<c:if test="${signin_failed != null}">
	<b><c:out value="${signin_failed}" /></b><br><br>
</c:if>

<form method="POST" action="<c:url value="/signin" />">
<label for="username"><fmt:message key="label.username"/></label><br>
<input type="text" name="username" value="<c:if test="${username != null}"><c:out value="${username}" /></c:if>"><br>
<label for="password"><fmt:message key="label.password"/></label><br>
<input type="password" name="password"><br>
<br>
<input type="submit" value="<fmt:message key="button.signin"/>"/>
</form>

<ul>
<li><a href="<c:url value="/signup" />"><fmt:message key="action.signup"/></a></li>
</ul>

</body>
</html>