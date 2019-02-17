<%--
  Created by IntelliJ IDEA.
  User: 我
  Date: 2019/2/17
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isErrorPage="true" %>
<html>
<head>
    <title>ERROR</title>
</head>
<body>
error page<br>
 异常：<br>
 <%=exception.getMessage()%>
 <%

     session.setAttribute("user","zhang san");
 %>

</body>
</html>
