<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Examen de Heces Fecales</title>
	<jsp:include page="heading.jsp"/>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>
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

<body onload="checkMod()">
	<jsp:include page="expedienteLaboratorioSumario.jsp"/>
	<h2>Examen de Heces Fecales</h2>
 
	<form:form method="POST" modelAttribute="examenHeces">
	<div id = "wholeForm">
		<form:input type="hidden" path="id" id="id"/>
		<form:input type="hidden" path="idExpediente" id="idExpediente" value="${idExpediente}"/>
		<div class="has-error"><form:errors path="idExpediente" class="help-inline"/></div>
		
		<table>
			<tr>
				<td><label for="fechaLaboratorio">Fecha de Laboratorio (dd/MM/yyyy): </label> </td>
				<td><form:input path="fechaLaboratorio" id="fechaLaboratorio"/></td>
				<td><div class="has-error"><form:errors path="fechaLaboratorio" class="help-inline"/></div></td>
		    </tr>
		</table>
		<br>
		<hr size=3>
		<br>
		<table>
			<tr>
				<td><label for="aspecto">Aspecto: </label> </td>
				<td><form:select path="aspecto" id="aspecto" items="${aspectosHeces}" multiple="false" itemValue="name" itemLabel="aspectoHeces" class="form-control input-sm"/></td> 
				<td><label for="otroAspecto">Otro: </label> </td>
				<td><form:input path="otroAspecto" id="otroAspecto"/></td>
				<td><label for="moco">Moco: </label> </td>
				<td><form:select path="moco" id="moco" items="${cantidadPresente}" multiple="false" itemValue="name" itemLabel="cantidadPresente" class="form-control input-sm"/></td>
				<td><label for="sangre">Sangre: </label> </td>
				<td><form:select path="sangre" id="sangre" items="${cantidadPresente}" multiple="false" itemValue="name" itemLabel="cantidadPresente" class="form-control input-sm"/></td>  
				<td><div class="has-error"><form:errors path="aspecto" class="help-inline"/></div>
				<div class="has-error"><form:errors path="otroAspecto" class="help-inline"/></div>
				<div class="has-error"><form:errors path="moco" class="help-inline"/></div>
				<div class="has-error"><form:errors path="sangre" class="help-inline"/></div></td>
		    </tr> 
		    <tr>
		    	<td><label for="textoCilindros">Restos Alimenticios: </label> </td>
		    	<td><form:select path="restosAlimenticios" id="restosAlimenticios" items="${cantidadPresente}" multiple="false" itemValue="name" itemLabel="cantidadPresente" class="form-control input-sm"/></td>
		    	<td><div class="has-error"><form:errors path="restosAlimenticios" class="help-inline"/></div></td>
		    </tr>
		    <tr>
				<td><label for="parasitos">Parasitos: </label> </td>
				<td><textarea path="parasitos" id="parasitos" rows="5" cols="25"></textarea></td> 
				<td><div class="has-error"><form:errors path="parasitos" class="help-inline"/></div></td>
		    </tr>
		    <tr>
				<td><label for="almidones">Almidones: </label> </td>
				<td><form:select path="almidones" id="almidones" items="${cantidadPresente}" multiple="false" itemValue="name" itemLabel="cantidadPresente" class="form-control input-sm"/></td> 
				<td><label for="grasas">Grasas: </label> </td>
				<td><form:select path="grasas" id="grasas" items="${cantidadPresente}" multiple="false" itemValue="name" itemLabel="cantidadPresente" class="form-control input-sm"/></td>
				<td><div class="has-error"><form:errors path="almidones" class="help-inline"/></div>
				<div class="has-error"><form:errors path="grasas" class="help-inline"/></div></td>
		    </tr>
		    <tr>
				<td><label for="fibrasMusculares">Fibras Musculares: </label> </td>
				<td><form:select path="fibrasMusculares" id="fibrasMusculares" items="${cantidadPresente}" multiple="false" itemValue="name" itemLabel="cantidadPresente" class="form-control input-sm"/></td> 
				<td><label for="jabones">Jabones: </label> </td>
				<td><form:select path="jabones" id="jabones" items="${cantidadPresente}" multiple="false" itemValue="name" itemLabel="cantidadPresente" class="form-control input-sm">
				<form:option value="" selected="true">Seleccionar</form:option>
				</form:select></td>
				<td><div class="has-error"><form:errors path="fibrasMusculares" class="help-inline"/></div>
				<div class="has-error"><form:errors path="jabones" class="help-inline"/></div></td>
		    </tr>    
		    <tr>
				<td><label for="textoOtros">Otros: </label> </td>
				<td><form:input path="textoOtros" id="textoOtros"/></td>
				<td><form:select path="otros" id="otros" items="${cantidadPresente}" multiple="false" itemValue="name" itemLabel="cantidadPresente" class="form-control input-sm"/></td> 
				<td><div class="has-error"><form:errors path="textoOtros" class="help-inline"/></div></td>
				<td><div class="has-error"><form:errors path="otros" class="help-inline"/></div></td>
		    </tr>  
				
		    <tr>
		    	<td><label for="quimicoBiologo">Quimico Biologo: </label> </td>
		    	<td><form:select path="idQuimicoBiologo" id="idQuimicoBiologo" items="${laboratoristas}" multiple="false" itemValue="id" itemLabel="ssoId" class="form-control input-sm"/></td>
		    	<td><div class="has-error"><form:errors path="idQuimicoBiologo" class="help-inline"/></div></td>
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
</body>
</html>
