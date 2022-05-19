<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!--<meta http-equiv="refresh" content="5">-->
    <title>Styles Testing Page #1 (styles1.jsp)</title>

    <!--расположение CSS файла: \src\main\webapp\static\css\styles2.css -->
    <link href="${pageContext.request.contextPath}/static/css/styles2.css" rel="stylesheet" type="text/css"/>

</head>
<body>

    <h1 class="style1">
        WebApp: Test CSS Styles #1 (styles1.jsp)
    </h1>

    <!--расположение JavaScript файла: \src\main\webapp\static\js\main.js -->
    <script src="${pageContext.request.contextPath}/static/js/main.js"></script>

</body>
</html>
