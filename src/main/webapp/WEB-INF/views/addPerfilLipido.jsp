<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Perfil Lipido</title>
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
		}
	}
	</script>
</head>

<body onload="checkMod()">

	<h2>Perfil Lipido</h2>
 
	<form:form method="POST" modelAttribute="perfilLipido">
	<div id = "wholeForm">
		<form:input type="hidden" path="id" id="id"/>
		<form:input type="hidden" path="idExpediente" id="idExpediente" value="${idExpediente}"/>
		
		<table>
			<tr>
				<td><label for="fechaLaboratorio">Fecha de Laboratorio (dd/MM/yyyy): </label> </td>
				<td><form:input path="fechaLaboratorio" id="fechaLaboratorio"/></td>
				<td><form:errors path="fechaLaboratorio" cssClass="error"/></td>
		    </tr>
		</table>
		
		<table>
			<tr>
				<td><label for="colesterolTotal">Colesterol Total: </label> </td>
				<td><form:input path="colesterolTotal" id="colesterolTotal"/></td>
				<td><label for="colesterolTotalLabel">Hasta 150 mg/dl </label> </td> 
				<td><form:errors path="colesterolTotal" cssClass="error"/></td>
		    </tr>
			<tr>
				<td><label for="colesterolAltaDensidad">Colesterol Alta Densidad (HDL): </label> </td>
				<td><form:input path="colesterolAltaDensidad" id="colesterolAltaDensidad"/></td>
				<td><label for="colesterolAltaDensidadLabel">Mayor de 40 mg/dl </label> </td> 
				<td><form:errors path="colesterolAltaDensidad" cssClass="error"/></td>
		    </tr>
		    <tr>
				<td><label for="colesterolBajaDensidad">Colesterol Baja Densidad (LDL): </label> </td>
				<td><form:input path="colesterolBajaDensidad" id="colesterolBajaDensidad"/></td>
				<td><label for="colesterolBajaDensidadLabel">65 - 175 mg/dl </label> </td> 
				<td><form:errors path="colesterolBajaDensidad" cssClass="error"/></td>
		    </tr>
			<tr>
				<td><label for="colesterolMuyBajaDensidad">Colesterol de Muy Baja Densidad (VLDL): </label> </td>
				<td><form:input path="colesterolMuyBajaDensidad" id="colesterolMuyBajaDensidad"/></td>
				<td><form:errors path="colesterolMuyBajaDensidad" cssClass="error"/></td>
		    </tr>
		    <tr>
				<td><label for="trigliceridos">Trigliceridos: </label> </td>
				<td><form:input path="trigliceridos" id="trigliceridos"/></td>
				<td><label for="trigliceridosLabel">Hasta 150 mg/dl </label> </td> 
				<td><form:errors path="trigliceridos" cssClass="error"/></td>
		    </tr>
		    <tr>
				<td><label for="indiceRiesgo">Indice de Riesgo: </label> </td>
				<td><form:input path="indiceRiesgo" id="indiceRiesgo"/></td>
				<td><form:errors path="indiceRiesgo" cssClass="error"/></td>
		    </tr>
		    <tr>
				<td><label for="resistenciaInsulina">Resistencia a la Insulina: </label> </td>
				<td><form:input path="resistenciaInsulina" id="resistenciaInsulina"/></td> 
				<td><form:errors path="resistenciaInsulina" cssClass="error"/></td>
		    </tr>
		    <%-- 
		    <tr>
		    	<form:select path="quimicoBiologo" items="${laboratoristas}" multiple="false" itemValue="id" itemLabel="LAST_NAME" class="form-control input-sm"/>
		    </tr>
		    --%>
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
