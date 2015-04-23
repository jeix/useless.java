<%--@elvariable id="signup_failed" type="java.lang.String"--%>
<%--@elvariable id="username" type="java.lang.String"--%>
<%--@elvariable id="email" type="java.lang.String"--%>
<%@ page import="s.dic.Dic" %>
<%--
String signup_failed = (String) request.getAttribute("signup_failed");
String username = (String) request.getAttribute("username");
String email = (String) request.getAttribute("email");

response.setHeader("Pragma", "no-cache");
--%>
<%--
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="title.signup"/></title>
</head>
<body>

<c:if test="${signup_failed != null}">
	<b><c:out value="${signup_failed}" /></b><br><br>
</c:if>

<form method="POST" action="<c:url value="/signup" />">
<label for="username"><fmt:message key="label.username"/></label><br>
<input type="text" name="username" value="<c:if test="${username != null}"><c:out value="${username}" /></c:if>"><br>
<label for="email"><fmt:message key="label.email"/></label><br>
<input type="text" name="email" value="<c:if test="${email != null}"><c:out value="${email}" /></c:if>"><br>
<label for="password"><fmt:message key="label.password"/></label><br>
<input type="password" name="password"><br>
<label for="repassword"><fmt:message key="label.repassword"/></label><br>
<input type="password" name="repassword"><br>
<br>
<input type="submit" value="<fmt:message key="button.signup"/>"/>
</form>

<ul>
<li><a href="<c:url value="/signin" />"><fmt:message key="action.signin"/></a></li>
</ul>

</body>
</html>
--%>
<fmt:message key="title.signup" var="title" />
<tmpl:main headTitle="${title}">

<c:if test="${signup_failed != null}">
	<b><c:out value="${signup_failed}" /></b><br><br>
</c:if>

<form method="POST" action="<c:url value="/signup" />">
<label for="username"><fmt:message key="label.username"/></label><br>
<input type="text" name="username" value="<c:if test="${username != null}"><c:out value="${username}" /></c:if>"><br>
<label for="email"><fmt:message key="label.email"/></label><br>
<input type="text" name="email" value="<c:if test="${email != null}"><c:out value="${email}" /></c:if>"><br>
<label for="password"><fmt:message key="label.password"/></label><br>
<input type="password" name="password"><br>
<label for="repassword"><fmt:message key="label.repassword"/></label><br>
<input type="password" name="repassword"><br>
<br>
<input type="submit" value="<fmt:message key="button.signup"/>"/>
</form>

<ul>
<li><a href="<c:url value="/signin" />"><fmt:message key="action.signin"/></a></li>
</ul>

</tmpl:main>