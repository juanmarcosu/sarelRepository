<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Acido Urico</title>
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
	</script>
</head>

<body onload="checkMod()">
	<jsp:include page="expedienteLaboratorioSumario.jsp"/>
	<h2>Acido Urico</h2>
 
	<form:form method="POST" modelAttribute="acidoUrico">
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
				<td><label for="nivelAcidoUrico">Acido Urico: </label> </td>
				<td><form:input path="nivelAcidoUrico" id="colesterolTotal"/></td>
				<td><label for="nivelAcidoUrico"> mg/dl </label> </td> 
				<td><div class="has-error"><form:errors path="nivelAcidoUrico" class="help-inline"/></div></td>
				<td><label>Referencia: </label></td>
				<td><label>Hombres 3.4 - 7.0 mg/dl </label> </td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><label>Mujeres 2.4 - 5.7 mg/dl </label> </td> 
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
