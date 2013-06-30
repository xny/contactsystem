<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>宝盛人脉管理系统</title>
        <link rel="stylesheet" href="/extjs/resources/css/ext-all.css" />
    </head>
    <body>
    <script type="text/javascript">
    	var g_obj = {};
    	g_obj.name = '${userName}';
    	g_obj.role = ${role}
    </script>
    <script type="text/javascript" src="/extjs/bootstrap.js"></script>
    <script src="/js/AM/app.js"></script>
    </body>
</html>