<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Expediente de Laboratorio</title>
	<jsp:include page="heading.jsp"/>
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
	<div class="panel-heading"><span class="lead">Expediente de Laboratorio </span></div>
     	<div class="formcontainer">
	<form name="pacientes" class="form-horizontal">
	<div class="tablecontainer">
	<h2>${expediente.nombres} ${expediente.apellidos}</h2>
	<table width="600">
			<tr>
				<td><label for="carne1">CARNE: ${expediente.carne} </label> </td>
				<td><label for="fechaNac">FECHA NACIMIENTO: ${expediente.fechaNacimiento} </label> </td>
		    </tr>
		    <tr>
				<td><label for="email">EMAIL: ${expediente.email} </label> </td>
				<td><label for="movil">SEXO: ${expediente.sexo} </label> </td>
		    </tr>
    </table>
    <label for="direccion">ID PACIENTE: ${expediente.idPaciente} </label>
	</div>
	<br/>
	<div>
		<a href="<c:url value='/agregarPerfilLipido?idExpediente=${expediente.id}' />">Crear Perfil Lipido</a>
	</div>
	</form>
	</div>     
	</div>
	</div>
      </div>
</body>
</html>
