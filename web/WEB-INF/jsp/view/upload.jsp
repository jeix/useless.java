<%@ page import="s.dic.Dic" %>
<%--
response.setHeader("Pragma", "no-cache");
--%>
<%--
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="title.upload"/></title>
</head>
<body>

<c:out value="${sessionScope['username']}" /> |
<a href="<c:url value="/signout" />"><fmt:message key="action.signout"/></a><br>
<br>

<form method="POST" action="<c:url value="/upload" />"
		enctype="multipart/form-data" accept-charset="utf-8">
<input type="file" name="file"><br>
<input type="submit" value="<fmt:message key="button.upload"/>">
</form>

<ul>
<li><a href="<c:url value="/find" />"><fmt:message key="action.find"/></a></li>
</ul>

</body>
</html>
--%>
<fmt:message key="title.upload" var="title" />
<tmpl:main headTitle="${title}">

<c:out value="${sessionScope['username']}" /> |
<a href="<c:url value="/signout" />"><fmt:message key="action.signout"/></a><br>
<br>

<form method="POST" action="<c:url value="/upload" />"
		enctype="multipart/form-data" accept-charset="utf-8">
<input type="file" name="file"><br>
<input type="submit" value="<fmt:message key="button.upload"/>">
</form>

<ul>
<li><a href="<c:url value="/find" />"><fmt:message key="action.find"/></a></li>
</ul>

</tmpl:main>