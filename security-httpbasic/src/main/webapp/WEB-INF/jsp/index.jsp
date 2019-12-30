<%--
  Created by IntelliJ IDEA.
  User: hyp
  Date: 19-12-30
  Time: 下午7:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>首页</title>
</head>
<body>
以下是网站的功能：<br/>
<a href="product/add">商品添加</a><br/>
<a href="product/update">商品修改</a><br/>
<a href="product/list">商品查询</a><br/>
<a href="product/delete">商品删除</a><br/>
</body>
</html>
