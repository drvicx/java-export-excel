<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Hello from Spain (helloEsp.jsp)</title>

    <link href="<c:url value="/static/css/styles1.css"/>" rel="stylesheet" type="text/css">

</head>
<body>

    <h1 class="greet-base greet-esp">
        WebApp: Pagina de Bienvenida (helloEsp.jsp)
    </h1>
    <!--project_file_path: \src\main\resources\static\images\SimpsonBart.jpg -->
    <img class="img2" src="${pageContext.request.contextPath}/images/SimpsonBart.jpg" alt="Bart Simpson">

    <script src="${pageContext.request.contextPath}/static/js/main.js"></script>

</body>
</html>
