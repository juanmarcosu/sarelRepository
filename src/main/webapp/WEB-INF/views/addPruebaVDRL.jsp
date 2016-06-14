<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Prueba VDRL</title>
	<jsp:include page="heading.jsp"/>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>
	<script>
	function checkMod(){
		checkResultado();
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
	function checkResultado(){
		if(document.getElementById('resultado').value=='NEGATIVO'){
			document.getElementById('nivelVDRL').disabled = true;
		}else{
			document.getElementById('nivelVDRL').disabled = false;
		}
	}
	</script>
</head>

<body onload="checkMod()">
	<jsp:include page="expedienteLaboratorioSumario.jsp"/>
	<h2>Prueba VDRL</h2>
 
	<form:form method="POST" modelAttribute="pruebaVDRL">
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
				<td><label for="resultado">Resultado: </label> </td>
				<td><form:select path="resultado" id="resultado" items="${posiblesResultados}" multiple="false" itemValue="name" itemLabel="resultado" class="form-control input-sm" onchange="checkResultado();"/></td>
				<td><div class="has-error"><form:errors path="resultado" class="help-inline"/></div></td>
				
					<td><label for="nivelVDRL">Prueba VDRL: </label> </td>
					<td><form:input path="nivelVDRL" id="nivelVDRL"/></td> 
					<td><div class="has-error"><form:errors path="nivelVDRL" class="help-inline"/></div></td>
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