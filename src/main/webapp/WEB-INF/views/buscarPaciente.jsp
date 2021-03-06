<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Listado de Pacientes</title>
	<jsp:include page="heading.jsp"/>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<%--<link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>--%>
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
	<form name="pacientes" class="form-horizontal">
	<div class="tablecontainer">
	<h3>Buscar Paciente:</h3>
	<table>
			<tr>
				<td><label for="nombre">Nombre(s): </label> </td>
				<td><input type="text" name="nombre"  id="nombre"/></td>
				<%-- <td><form:errors path="name" cssClass="error"/></td> --%>
		    </tr>
		    <tr>
				<td><label for="apellido">Apellido(s): </label> </td>
				<td><input type="text" name="apellido" id="apellido"/></td>
		    </tr>
	    	<tr>
				<td><label for="carne">Numero de Registro: </label> </td>
				<td><input type="text" name="carne" id="carne"/></td>
		    </tr>
		    <tr>
		    	<td><input type="submit" value="Buscar"/></td>
		    </tr>
    </table>
    <sec:authorize access="hasRole('ADMINISTRADOR') or hasRole('LABORATORISTA')">
    	<div align="right"><strong><a href="<c:url value='/agregarEXPEDIENTE_LABORATORIO' />">Crear Expediente Laboratorio</a></strong></div>
    </sec:authorize>
    <h3>Resultados:</h3>
	<table class="table table-hover">
		<tr>
			<td>id Paciente</td><td>Nombre</td><td>Apellido</td><td>Numero de Registro</td><td>Fecha Nacimiento</td><td>Ver Perfil</td>
		</tr>
		 
		<c:forEach items="${pacientes}" var="unPaciente">
			<tr>
			<td>${unPaciente.idPaciente}</td>
			<td>${unPaciente.nombre}</td>
			<td>${unPaciente.apellido}</td>
			<td>${unPaciente.carne}</td>
			<td>${unPaciente.fechaNac}</td>
			<td><a href="<c:url value='/verExpedienteLaboratorio?idPaciente=${unPaciente.idPaciente}&carne=${unPaciente.carne}' />">Consultar</a></td>
			</tr>
		</c:forEach>
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
