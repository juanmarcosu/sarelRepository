<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Listado de Pacientes</title>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>
	<style>
		tr:first-child{
			font-weight: bold;
			background-color: #C6C9C4;
		}
	</style>

</head>


<body ng-app="myApp" class="ng-cloak">
	<div class="generic-container" ng-controller="UserController as ctrl">
          <div class="panel panel-default">
	<div class="panel-heading"><span class="lead">Listado de Pacientes </span></div>
     	<div class="formcontainer">
	<form name="myForm" class="form-horizontal">
	<div class="tablecontainer">
	${user}	
	<br>
	${unPaciente}
	<table class="table table-hover">
		<thead>
		<tr>
			<td>id Paciente</td><td>Nombre</td><td>Apellido</td><td>Fecha Nacimiento</td><td>Carne</td><td>Ver Perfil</td>
		</tr>
		</thead>
		<tbody>
		 
		<c:forEach items="${pacientes}" var="unPaciente">
			<tr>
			<td>${unPaciente.idPaciente}</td>
			<td>${unPaciente.nombre}</td>
			<td>${unPaciente.apellido}</td>
			<td>${unPaciente.fechaNac}</td>
			<td>${unPaciente.carne}</td>
			<td><a href="<c:url value='/edit-${unPaciente.idPaciente}-employee' />">Consultar</a></td>
			</tr>
		</c:forEach>
		<%--
		<c:forEach items="${perfilesDeUsuario}" var="unPerfil">
			<tr>
			<td>${unPerfil.id}</td>
			<td>${unPerfil.type}</td>
			</tr>
		</c:forEach>
		--%>
		</tbody>
	</table>
	</div>
	<br/>
	</form>
	</div>     
	</div>
	</div>
      </div>
</body>
</html>
