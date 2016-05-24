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
	<script>
		function openPage()
		 {
			var tipo, url, nuevaRuta, indiceSelect, context
			context ="${pageContext.request.contextPath}"
			url = "?idExpediente="+${expediente.id};
			indiceSelect = document.getElementById('selectTipoLaboratorio');
			tipo = indiceSelect.options[indiceSelect.selectedIndex].value;
			tipo = tipo.toLowerCase();
			tipo = tipo.replace("_","");
			nuevaRuta = context+"/agregar"+tipo+url;
		 	window.location.href = nuevaRuta;
		 }
	</script>

</head>


<body ng-app="myApp" class="ng-cloak">
	<div class="generic-container" ng-controller="UserController as ctrl">
          <div class="panel panel-default">
	<div class="panel-heading"><span class="lead">Expediente de Laboratorio </span></div>
     	<div class="formcontainer">
	<form name="pacientes" class="form-horizontal">
	<div class="has-error"><label>${message}</label></div>
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
	<br>
	<table align="right">
		<tr>
			<td>
				<div>
					<select id="selectTipoLaboratorio" class="form-control input-sm">
					    	<c:forEach items="${tiposLaboratorio}" var="tipo">
					    		<option value="${tipo}">${tipo.toString()}</option>
					    	</c:forEach>
					    	</select>
				</div>
			</td>
			<td>
				<div>
					<input type="button" value="Agregar Nuevo Resultado" name="crearLaboratorio" onclick="openPage()"/>
				</div>
		    </td>
	    </tr>
    </table>
	
	<hr size="3">
	<div id="laboratorios">
		<table class="table table-hover">
		<thead>
		<tr>
			<td>Tipo de Laboratorio</td><td>Responsable</td><td>Fecha</td><td>Mostrar</td><td>Modificar</td><td>Eliminar</td><td>Imprimir</td>
		</tr>
		</thead>
		<tbody>
		 
		<c:forEach items="${labs}" var="unLaboratorio">
			<tr>
			<td>${unLaboratorio.tipoLaboratorio}</td>
			<td>${unLaboratorio.quimicoBiologo}</td>
			<td>${unLaboratorio.fechaLaboratorio}</td>
			<td><a href="<c:url value='/consultarPerfilLipidico?idPerfilLipidico=${unLaboratorio.id}' />">Consultar</a></td>
			<td><a href="<c:url value='/editarPerfilLipidico?idPerfilLipidico=${unLaboratorio.id}' />">Editar</a></td>
			<td><a href="<c:url value='/eliminarPerfilLipidico?idPerfilLipidico=${unLaboratorio.id}&idExpediente=${expediente.id}' />">Eliminar</a></td>
			<td>IMPRIMIR</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	</form>
	</div>     
	</div>
	</div>
      </div>
</body>
</html>
