<%--@elvariable id="kw" type="java.lang.String"--%>
<%--@elvariable id="dics" type="java.util.List<Dic>"--%>
<%@ page import="java.util.List" %>
<%@ page import="s.dic.Dic" %>
<%--
String kw = (String) request.getAttribute("kw");
@SuppressWarnings("unchecked")
List<Dic> dics = (List<Dic>) request.getAttribute("dics");

response.setHeader("Pragma", "no-cache");
--%>
<%--
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><fmt:message key="title.find"/></title>
</head>
<body>

<c:out value="${sessionScope['username']}" /> |
<a href="<c:url value="/signout" />"><fmt:message key="action.signout"/></a> |
<a href="<c:url value="/edit" />"><fmt:message key="action.edit_new"/></a><br>
<br>

<c:if test="${dics.size() > 0}">
	<ul>
	<c:forEach items="${dics}" var="dic">
		<li><a href="<c:url value="/read/${dic.id}" />"><c:choose>
			<c:when test="${dic.txt.length() > 20}">
				<c:out value="${dic.txt.substring(0,20) += '...'}" />
			</c:when>
			<c:otherwise>
				<c:out value="${dic.txt}"/>
			</c:otherwise>
		</c:choose></a></li>
	</c:forEach>
	</ul>
</c:if>

<ul>
<li><a href="<c:url value="/upload" />"><fmt:message key="action.import"/></a></li>
<li><a href="<c:url value="/download" />"><fmt:message key="action.export"/></a></li>
</ul>

</body>
</html>
--%>
<fmt:message key="title.find" var="title" />
<tmpl:main headTitle="${title}">

<c:out value="${sessionScope['username']}" /> |
<a href="<c:url value="/signout" />"><fmt:message key="action.signout"/></a> |
<a href="<c:url value="/edit" />"><fmt:message key="action.edit_new"/></a><br>
<br>

<c:if test="${dics.size() > 0}">
	<ul>
	<c:forEach items="${dics}" var="dic">
		<li><a href="<c:url value="/read/${dic.id}" />"><c:out value="${idle:abbreviate(dic.txt, 20)}"/></a></li>
	</c:forEach>
	</ul>
</c:if>

<ul>
<li><a href="<c:url value="/upload" />"><fmt:message key="action.import"/></a></li>
<li><a href="<c:url value="/download" />"><fmt:message key="action.export"/></a></li>
</ul>

</tmpl:main>