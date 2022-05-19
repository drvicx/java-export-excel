<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Hello from US (helloEng.jsp)</title>

    <link href="<c:url value="/static/css/styles1.css"/>" rel="stylesheet" type="text/css">

</head>
<body>

    <h1 class="greet-base greet-eng">
        WebApp: Welcome Page (helloEng.jsp)
    </h1>
    <!--project_file_path: \src\main\webapp\static\images\SimpsonHomer.jpg -->
    <img class="img1" src="${pageContext.request.contextPath}/static/images/SimpsonHomer.jpg" alt="Homer Simpson">

    <script src="${pageContext.request.contextPath}/static/js/main.js"></script>

</body>
</html>
