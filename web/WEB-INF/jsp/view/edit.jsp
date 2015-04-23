<%--@elvariable id="dic" type="s.dic.Dic"--%>
<%@ page import="s.dic.Dic" %>
<%--
Dic dic = (Dic) request.getAttribute("dic");

response.setHeader("Pragma", "no-cache");
--%>
<%--
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="title.edit"/></title>
</head>
<body>

<c:out value="${sessionScope['username']}" /> |
<a href="<c:url value="/signout" />"><fmt:message key="action.signout"/></a><br>
<br>

<form method="POST" action="<c:url value="/save" /><c:if test="${dic.id > 0}">/<c:out value="${dic.id}" /></c:if>"
		accept-charset="utf-8">
<textarea name="txt" rows="5" cols="30"><c:if test="${dic.txt != null}"><c:out value="${dic.txt}" /></c:if></textarea><br>
<input type="submit" value="<fmt:message key="button.save"/>"/>
</form>

<ul>
<c:if test="${dic.id > 0}">
	<li><a href="<c:url value="/read/${dic.id}" />"><fmt:message key="action.read"/></a></li>
</c:if>
<li><a href="<c:url value="/find" />"><fmt:message key="action.find"/></a></li>
</ul>

</body>
</html>
--%>
<fmt:message key="title.edit" var="title" />
<tmpl:main headTitle="${title}">

<c:out value="${sessionScope['username']}" /> |
<a href="<c:url value="/signout" />"><fmt:message key="action.signout"/></a><br>
<br>

<form method="POST" action="<c:url value="/save" /><c:if test="${dic.id > 0}">/<c:out value="${dic.id}" /></c:if>"
		accept-charset="utf-8">
<textarea name="txt" rows="5" cols="30"><c:if test="${dic.txt != null}"><c:out value="${dic.txt}" /></c:if></textarea><br>
<input type="submit" value="<fmt:message key="button.save"/>"/>
</form>

<ul>
<c:if test="${dic.id > 0}">
	<li><a href="<c:url value="/read/${dic.id}" />"><fmt:message key="action.read"/></a></li>
</c:if>
<li><a href="<c:url value="/find" />"><fmt:message key="action.find"/></a></li>
</ul>

</tmpl:main>