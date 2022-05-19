<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Styles Testing Page #2 (styles2.jsp)</title>

    <!--расположение CSS файла: \src\main\webapp\static\css\styles2.css -->
    <link href="<c:url value="/static/css/styles2.css"/>" rel="stylesheet" type="text/css">

</head>
<body>

    <h1 class="style2">
        WebApp: Test CSS Styles #2 (styles2.jsp)
    </h1>

    <!--расположение JavaScript файла: \src\main\webapp\static\js\main.js -->
    <script src="${pageContext.request.contextPath}/static/js/main.js"></script>

</body>
</html>
