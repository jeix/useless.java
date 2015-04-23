<%@ tag body-content="scriptless" dynamic-attributes="dynamicAttributes"
        trimDirectiveWhitespaces="true" %>
<%@ attribute name="headTitle" type="java.lang.String" rtexprvalue="true"
              required="true" %>
<%@ include file="/WEB-INF/jsp/base.jspf" %>
<!DOCTYPE html>
<html<c:forEach items="${dynamicAttributes}" var="a">
	<c:out value=' ${a.key}="${fn:escapeXml(a.value)}"' escapeXml="false" />
</c:forEach>>
<head>
<meta charset="UTF-8">
<title><c:out value="${fn:trim(headTitle)}" /></title>
</head>
<body>
<jsp:doBody />
</body>
</html>
