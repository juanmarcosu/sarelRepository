<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Hemoglobina Glicosilada</title>
	<jsp:include page="heading.jsp"/>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<%-- <link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>--%>
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

<body class="ng-cloak" onload="checkMod()">
	<div class="generic-container" >
          <div class="panel panel-default">
	<div class="panel-heading"><span class="lead">Hemoglobina Glicosilada </span></div>
     	<div class="formcontainer">
     	<div class="tablecontainer">
	<form:form modelAttribute="hemoglobinaGlucosa" method="POST" class="form-horizontal"  >
	<jsp:include page="expedienteLaboratorioSumario.jsp"/>
	
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
		<hr size=3>
		<table>
			<tr>
				<td><label for="hemoglobinaGlicosilada">Hemoglobina Glucosilada: </label> </td>
				<td><form:input path="hemoglobinaGlicosilada" id="hemoglobinaGlicosilada"/></td>
				<td><label for="hemoglobinaGlicosilada"> % &nbsp; &nbsp; - &nbsp;</label> </td> 
				<td><div class="has-error"><form:errors path="hemoglobinaGlicosilada" class="help-inline"/></div></td>
				<td><label>Referencia: </label></td>
				<td><label>No diabeticos: 4.0 - 6.0 % </label> </td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><label>Diabetico controlado: 6.0 - 8.0 % </label> </td> 
		    </tr>
		    <tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><label>Diabetico descontrolado: > 8.0 % </label> </td> 
		    </tr>
		    <tr>
				<td><label for="nivelPromedioGlucosa">Nivel Promedio de Glucosa (eAG): </label> </td>
				<td><form:input path="nivelPromedioGlucosa" id="nivelPromedioGlucosa"/></td>
				<td><label for="nivelPromedioGlucosa"> mg/dl &nbsp; &nbsp; - &nbsp;</label> </td> 
				<td><div class="has-error"><form:errors path="nivelPromedioGlucosa" class="help-inline"/></div></td>
				<td><label></label></td>
				<td><label></label> </td>
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
	</div>     
	</div>
	</div>
	</div>
</body>
</html>
