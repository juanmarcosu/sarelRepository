<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
	<jsp:include page="heading.jsp"/>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Pagina de Bienvenida</title>
</head>
<body>
	<div class="has-success"><label>${message}</label></div>
	<div align ="center">
		<img src="<c:url value='/recursos/imagenes/tricentenaria.jpg' />" /></td>
	</div>
	<%-- 
    <div>
        <sec:authorize access="hasRole('ADMINISTRADOR') and hasRole('LABORATORISTA')">
            <label><a href="#">Crear Backup</a> | Esta parte es visible para los Administradores y para el DBA</label>
        </sec:authorize>
    </div>
    --%>
</html>
