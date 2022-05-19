<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Styles Testing Page #3 (styles3.jsp)</title>

    <!--НЕ_ПРАВИЛЬНО -- т.к поиск будет в http://localhost:8081/css/styles3.css -->
    <!--<link href="/css/styles3.css" rel="stylesheet" type="text/css" />-->

    <!--ПРАВИЛЬНО    -- поиск будет в http://localhost:8081/webapp/css/styles3.css -->
    <!--расположение CSS файла: \src\main\resources\static\css\styles3.css -->
    <link href="<c:url value="/css/styles3.css"/>" rel="stylesheet" type="text/css">

</head>
<body>

    <h1 class="style3">
        WebApp: Test CSS Styles #3 (styles3.jsp)
    </h1>

    <!--расположение JavaScript файла: \src\main\resources\static\js\main3.js -->
    <!--<script src="${pageContext.request.contextPath}/static/js/main.js"></script>-->
    <script src="<c:url value="/js/main3.js"/>"></script>

</body>
</html>
