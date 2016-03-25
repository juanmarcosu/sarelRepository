<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
</head>
<body background="<c:url value='/recursos/imagenes/background.jpg' />">
	
	<table>
		<tr>
			<td><img src="<c:url value='/recursos/imagenes/logousac.png' />" /></td>
			<td>
				<h1 style="color:#000050;">Sistema de Administracion de REsultados de Laboratorio</h1><h2 style="color:#000050;">SAREL</h2> 
			</td>
		</tr>
	</table>
	<div align="right">
		<label>Usuario: ${user} - </label>
	</div>
	<hr>
	<div align="center">
	<table width="400">
		<tr>
			<td><a href="<c:url value='/home' />">Pagina de Inicio</a></td>
			<td><a href="<c:url value='/buscarPaciente?nombre=&apellido=&carne=' />">Buscar Paciente</a></td>
			<td><a href="<c:url value='/login' />">Iniciar Sesion</a></td>
		</tr>
	</table>
	</div>
	<hr>
</body>
</html>
