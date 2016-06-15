<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Glucosa pre-pp</title>
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
		}
	}
	</script>
</head>

<body onload="checkMod()">
	<jsp:include page="expedienteLaboratorioSumario.jsp"/>
	<h2>Glucosa pre-pp</h2>
 
	<form:form method="POST" modelAttribute="glucosaPreYPost">
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
				<td><label for="prePrandial">Glucosa pre-prandial: </label> </td>
				<td><form:input path="prePrandial" id="prePrandial"/></td>
				<td><label for="prePrandialLabel1">mg/dl </label> </td>
				<td>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</td> 
				<td><label for="prePrandialLabel2">Referencia: &nbsp;</label> </td>
				<td><label for="prePrandialLabel3"> 75 - 115 mg/dl </label> </td>  
				<td><div class="has-error"><form:errors path="prePrandial" class="help-inline"/></div></td>
			</tr>
			<tr>
				<td><label for="postPrandial">Trigliceridos: </label> </td>
				<td><form:input path="postPrandial" id="postPrandial"/></td>
				<td><label for="postPrandialLabel1">mg/dl </label> </td>
				<td></td> 
				<td></td>
				<td></td>
				<td><div class="has-error"><form:errors path="postPrandial" class="help-inline"/></div></td>
			</tr>
		    <tr>
		    	<td><label for="quimicoBiologo">Glucosa post-prandial: </label> </td>
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