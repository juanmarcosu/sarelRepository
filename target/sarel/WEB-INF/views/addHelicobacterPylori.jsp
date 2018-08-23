<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Prueba de Helicobacter Pylori</title>
	<jsp:include page="heading.jsp"/>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/jquery-ui.css" />
	<script src="${pageContext.request.contextPath}/static/js/jquery-1.9.1.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/jquery-ui.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/datepicker-es.js"></script>
	<script>
		$(function () {
			var datePickerEs=$.datepicker.regional[ "es" ];
		    datePickerEs.dateFormat='dd/mm/yy';
		    datePickerEs.changeMonth=true;
		    datePickerEs.changeYear=true;
		    jQuery("#fechaLaboratorio").datepicker(datePickerEs);
		});
	</script>
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
	<div class="generic-container">
          <div class="panel panel-default">
	<div class="panel-heading"><span class="lead">Prueba de Helicobacter Pylori</span></div>
     	<div class="formcontainer">
     	<div class="tablecontainer">
	<form:form modelAttribute="helicobacterPylori" method="POST" class="form-horizontal"  >
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
				<td><label for="resultado">Resultado: </label> </td>
				<td><form:select path="resultado" id="resultado" items="${posiblesResultados}" multiple="false" itemValue="name" itemLabel="resultado" class="form-control input-sm"/></td> 
				<td><div class="has-error"><form:errors path="resultado" class="help-inline"/></div></td>
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