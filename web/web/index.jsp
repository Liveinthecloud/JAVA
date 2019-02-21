
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page errorPage="error.jsp" %>
<%--
  Created by IntelliJ IDEA.
  User: 我
  Date: 2019/2/16
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Hello Tomcat</title>
  </head>
  <body style="background: aqua">
  now = <%= new Date() %>
  <br>
  <%
  //显示日期
  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd-hh-mm-ss");
  Date now=new Date();
  //输出到页面中
  out.println("now= "+sdf.format(now));
 /* int a=8 ,b=0;
  a=a/b;*/

  %>
  <br>
  <%--在类中--%>
  <%! public int a=100;%>

  <%--在方法类--%>
  <% System.out.println("java 代码块");
      out.println("hello");
      //取
    String str=(String)session.getAttribute("user");
    out.println(str);
  %>
<%--表达式语句块--%>
  <%=a%>




  <table border="2" width="200" height="100" align="center">
    <tr>
      <th> 姓名</th>
      <th> 语文</th>
      <th> 数学</th>
      <th> 英语</th>
    </tr>

    <tr>
      <td> 小明</td>
      <td> 100</td>
      <td> 99</td>
      <td> 100</td>
    </tr>

    <tr>
      <td> 小红</td>
      <td> 100</td>
      <td> 96</td>
      <td> 99</td>
    </tr>
  </table>
  </body>
</html>
