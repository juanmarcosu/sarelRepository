<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Expediente Laboratorio</title>
	<jsp:include page="heading.jsp"/>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<script>
	function checkMod(){
		if ("${soloConsulta}" == "true") {
			var el = document.getElementById('wholeForm'),
		        all = el.getElementsByTagName('input'),
		        i;
		    for (i = 0; i < all.length; i++) {
		        all[i].disabled = true;
		    }
		    document.getElementById('idQuimicoBiologo').disabled = true;
		    document.getElementById('resultado').disabled = true;
		}
	}
	</script>
</head>

<body class="ng-cloak" onload="checkMod()">
	<div class="generic-container" >
          <div class="panel panel-default">
	<div class="panel-heading"><span class="lead">Expediente Laboratorio</span></div>
     	<div class="formcontainer">
     	<div class="tablecontainer">
     	<form:form modelAttribute="expedienteLaboratorio" method="POST" class="form-horizontal"  >
     	<div class="has-error"><label>${alerta}</label></div>
	<div id = "wholeForm">
		<form:input type="hidden" path="id" id="id"/>
		<form:input type="hidden" path="idPaciente" id="idPaciente" value="0"/>
		<div class="has-error"><form:errors path="idPaciente" class="help-inline"/></div>
		<table>
			<tr>
				<td><label for="nombres">Nombres: </label> </td>
				<td><form:input path="nombres" id="nombres"/></td> 
				<td><div class="has-error"><form:errors path="nombres" class="help-inline"/></div></td>
		    </tr> 
		    <tr>
				<td><label for="apellidos">Apellidos: </label> </td>
				<td><form:input path="apellidos" id="apellidos"/></td> 
				<td><div class="has-error"><form:errors path="apellidos" class="help-inline"/></div></td>
		    </tr>
		    <tr>
				<td><label for="fechaNacimiento">Fecha de Nacimiento (yyyy-mm-dd): </label> </td>
				<td><form:input path="fechaNacimiento" id="fechaNacimiento"/></td>
				<td><div class="has-error"><form:errors path="fechaNacimiento" class="help-inline"/></div></td>
		    </tr> 
			<tr>
				<td><label for="carne">Número de Registro: </label> </td>
				<td><form:input path="carne" id="carne"/></td> 
				<td><div class="has-error"><form:errors path="carne" class="help-inline"/></div></td>
		    </tr>
		    <tr>
				<td><label for="direccion">Dirección: </label> </td>
				<td><form:input path="direccion" id="direccion"/></td> 
				<td><div class="has-error"><form:errors path="direccion" class="help-inline"/></div></td>
		    </tr>
		    <tr>
				<td><label for="telefono">Telefono: </label> </td>
				<td><form:input path="telefono" id="telefono"/></td> 
				<td><div class="has-error"><form:errors path="telefono" class="help-inline"/></div></td>
		    </tr>
		    <tr>
				<td><label for="movil">Movil: </label> </td>
				<td><form:input path="movil" id="movil"/></td> 
				<td><div class="has-error"><form:errors path="movil" class="help-inline"/></div></td>
		    </tr>
		    <tr>
				<td><label for="email">eMail: </label> </td>
				<td><form:input path="email" id="email"/></td> 
				<td><div class="has-error"><form:errors path="email" class="help-inline"/></div></td>
		    </tr>
		    <tr>
		    	<td><label for="sexo">Sexo: </label> </td>
				<td><form:select path="sexo" id="sexo" items="${sexos}" multiple="false" itemValue="name" itemLabel="sexo" class="form-control input-sm"/></td>
				<td><div class="has-error"><form:errors path="sexo" class="help-inline"/></div></td>
		    </tr>
			<tr>
				<td colspan="3">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Actualizar"/>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Crear"/>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
		</div>
	</form:form>
	</div>     
	</div>
	</div>
	</div>
</body>
</html>
