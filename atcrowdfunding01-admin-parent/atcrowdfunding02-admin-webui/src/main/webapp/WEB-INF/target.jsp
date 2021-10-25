<%--
  Created by IntelliJ IDEA.
  User: 17583
  Date: 2021/10/18
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
</head>
<body>
<h1>Success</h1>
${sessionScope.user}
</body>
</html>
