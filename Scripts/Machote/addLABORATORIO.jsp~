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
		    document.getElementById('idQuimicoBiologo').disabled = true;
		}
	}
	function calcularFila4y3(){
		var fila4, fila3, fila6, fila7;
		fila4 = parseFloat(document.getElementById("trigliceridos").value, 1) / 5;
		document.getElementById("colesterolMuyBajaDensidad").value = fila4.toFixed(1);
		fila3 = parseFloat(document.getElementById("colesterolTotal").value,1) - parseFloat(document.getElementById("colesterolAltaDensidad").value, 1) - parseFloat(fila4,1);
		document.getElementById("colesterolBajaDensidad").value = fila3.toFixed(1);
		fila6 = parseFloat(document.getElementById("colesterolTotal").value,1) / parseFloat(document.getElementById("colesterolAltaDensidad").value, 1);
		document.getElementById("indiceRiesgo").value = fila6.toFixed(1);
		fila7 = parseFloat(document.getElementById("trigliceridos").value,1) / parseFloat(document.getElementById("colesterolAltaDensidad").value, 1);
		document.getElementById("resistenciaInsulina").value = fila7.toFixed(1);
	}
	</script>
</head>

<body onload="checkMod()">
	<jsp:include page="expedienteLaboratorioSumario.jsp"/>
	<h2>Perfil Lipidico</h2>
 
	<form:form method="POST" modelAttribute="perfilLipidico">
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
				<td><label for="colesterolTotal">Colesterol Total: </label> </td>
				<td><form:input path="colesterolTotal" id="colesterolTotal"/></td>
				<td><label for="colesterolTotalLabel">Hasta 150 mg/dl </label> </td> 
				<td><div class="has-error"><form:errors path="colesterolTotal" class="help-inline"/></div></td>
		    </tr>
			<tr>
				<td><label for="colesterolAltaDensidad">Colesterol Alta Densidad (HDL): </label> </td>
				<td><form:input path="colesterolAltaDensidad" id="colesterolAltaDensidad"/></td>
				<td><label for="colesterolAltaDensidadLabel">Mayor de 40 mg/dl </label> </td> 
				<td><div class="has-error"><form:errors path="colesterolAltaDensidad" class="help-inline"/></div></td>
		    </tr>
		    <tr>
				<td><label for="colesterolBajaDensidad">Colesterol Baja Densidad (LDL): </label> </td>
				<td><form:input path="colesterolBajaDensidad" id="colesterolBajaDensidad"/></td>
				<td><label for="colesterolBajaDensidadLabel">65 - 175 mg/dl </label> </td> 
				<td><div class="has-error"><form:errors path="colesterolBajaDensidad" cssClass="error"/></div></td>
		    </tr>
			<tr>
				<td><label for="colesterolMuyBajaDensidad">Colesterol de Muy Baja Densidad (VLDL): </label> </td>
				<td><form:input path="colesterolMuyBajaDensidad" id="colesterolMuyBajaDensidad"/></td>
				<td><div class="has-error"><form:errors path="colesterolMuyBajaDensidad" cssClass="error"/></div></td>
		    </tr>
		    <tr>
				<td><label for="trigliceridos">Trigliceridos: </label> </td>
				<td><form:input path="trigliceridos" id="trigliceridos" onchange="calcularFila4y3()"/></td>
				<td><label for="trigliceridosLabel">Hasta 150 mg/dl </label> </td> 
				<td><div class="has-error"><form:errors path="trigliceridos" cssClass="error"/></div></td>
		    </tr>
		    <tr>
				<td><label for="indiceRiesgo">Indice de Riesgo: </label> </td>
				<td><form:input path="indiceRiesgo" id="indiceRiesgo"/></td>
				<td><div class="has-error"><form:errors path="indiceRiesgo" cssClass="error"/></div></td>
		    </tr>
		    <tr>
				<td><label for="resistenciaInsulina">Resistencia a la Insulina: </label> </td>
				<td><form:input path="resistenciaInsulina" id="resistenciaInsulina"/></td> 
				<td><div class="has-error"><form:errors path="resistenciaInsulina" cssClass="error"/></div></td>
		    </tr> 
		    <tr>
		    	<td><label for="quimicoBiologo">Quimico Biologo: </label> </td>
		    	<%--
		    	<td><form:select path="quimicoBiologo" id="quimicoBiologo" class="form-control input-sm">
		    	<option value="" >Seleccionar</option>
		    	<c:forEach items="${laboratoristas}" var="user">
		    		<option value=${user}>${user.ssoId}</option>
		    	</c:forEach>
		    	</form:select><td>
		    	 --%>
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

