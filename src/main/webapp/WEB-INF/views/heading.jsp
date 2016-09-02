<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
</head>
<body >
	<div align="center">
			<img src="<c:url value='/recursos/imagenes/logousac_small.png' />" />
			<label style="color:#000050; font-size:200%;">Sistema de Administracion de REsultados de Laboratorio - SAREL</label> 
	</div>
	<div style="width:100%">
	<div align="center">
		<nav>
			<a href="<c:url value='/home' />">Pagina de Inicio</a>&nbsp; &nbsp; - &nbsp; &nbsp;
			<sec:authorize access="hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA') or hasRole('CONSULTOR')">
				<a href="<c:url value='/buscarPaciente?nombre=&apellido=&carne=' />">Buscar Paciente</a>&nbsp; &nbsp; - &nbsp; &nbsp;
			</sec:authorize>
			<sec:authorize access="hasRole('ADMINISTRADOR')">
           		<a href="<c:url value='/newUser' />">Crear Usuario</a>&nbsp; &nbsp; - &nbsp; &nbsp;
       		</sec:authorize>
			<c:if test="${user == 'anonymousUser'}">
				<a href="<c:url value='/login' />">Iniciar Sesion</a>
			</c:if>
			<c:if test="${user != 'anonymousUser'}">
				<a href="<c:url value="/logout" />">Cerrar Sesi&oacute;n</a>
			</c:if>
		</nav>
	</div>
	<div align="right">
		<c:if test="${user != 'anonymousUser'}">
			<label>Usuario: ${user} &nbsp; &nbsp; &nbsp;</label>
		</c:if>
	</div>
	</div>
	<hr size="1">
</body>
</html>
