<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
	<jsp:include page="heading.jsp"/>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Pagina de Bievenida</title>
</head>
<body>
    Hola <strong>${user}</strong>, Esta es la pagina de Bienvenida. <a href="<c:url value="/logout" />">Cerrar Sesi&oacute;n</a>
 
    <br/>
    <br/>
    <div>
        <label>Esta Parte es visible para todos los usarios</label>
        <br>
        <a href="<c:url value="/buscarPaciente?nombre=&apellido=&carne=" />">Buscar Pacientes:</a>
    </div>
 
    <br/>
    <div>
        <sec:authorize access="hasRole('ADMINISTRADOR')">
            <label><a href="#">Editar pagina </a> | Esta parte es visible solo para los Administradores</label>
        </sec:authorize>
    </div>
 
    <br/>
    <div>
        <sec:authorize access="hasRole('ADMINISTRADOR') and hasRole('LABORATORISTA')">
            <label><a href="#">Crear Backup</a> | Esta parte es visible para los Administradores y para el DBA</label>
        </sec:authorize>
    </div>
</html>
