<%--
  Created by IntelliJ IDEA.
  User: hyp
  Date: 19-12-30
  Time: 下午6:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>">
<html>
<head>
    <title>登录页面</title>
</head>
<body>
<h3>登录页面</h3>
<form action="securityLogin" method="post">
    用户名:<input type="text" name="username"/><br/>
    用户名:<input type="password" name="password"/><br/>
    <input type="submit" value="登录"/>
</form>
</body>
</html>
