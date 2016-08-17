<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Examen de Orina</title>
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
	<h2>Examen de Orina</h2>
 
	<form:form method="POST" modelAttribute="examenOrina">
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
				<td><label for="color">Color: </label> </td>
				<td><form:select path="color" id="color" items="${coloresOrina}" multiple="false" itemValue="name" itemLabel="colorOrina" class="form-control input-sm"/></td> 
				<td><label for="aspecto">Aspecto: </label> </td>
				<td><form:select path="aspecto" id="aspecto" items="${aspectosOrina}" multiple="false" itemValue="name" itemLabel="aspectoOrina" class="form-control input-sm"/></td> 
				<td><div class="has-error"><form:errors path="color" class="help-inline"/></div></td>
				<td><div class="has-error"><form:errors path="aspecto" class="help-inline"/></div></td>
		    </tr> 
		    <tr>
				<td><label for="ph">Ph: </label> </td>
				<td><form:input path="ph" id="ph"/></td>
				<td><label for="densidad">Densidad: </label> </td>
				<td><form:input path="densidad" id="densidad"/></td>
				<td><div class="has-error"><form:errors path="ph" class="help-inline"/></div></td>
				<td><div class="has-error"><form:errors path="densidad" cssClass="error"/></div></td>
		    </tr>
		    <tr>
				<td><label for="leucositos">Leucocitos: </label> </td>
				<td><form:input path="leucositos" id="leucositos"/></td>
				<td><label for="leucositosLabel">x campo </label> </td>
				
				<td><label for="eritrositos">&nbsp; &nbsp;Eritrositos: </label> </td>
				<td><form:input path="eritrositos" id="eritrositos"/></td>
				<td><label for="eritrositosLabel">x campo </label> </td>
				<td><div class="has-error"><form:errors path="eritrositos" cssClass="error"/></div></td>
				<td><div class="has-error"><form:errors path="leucositos" class="help-inline"/></div></td>
		    </tr>
		    <tr>
				<td><label for=celulasEpiteliales>Celulas Epiteliales: </label> </td>
				<td><form:select path="celulasEpiteliales" id="celulasEpiteliales" items="${cantidadPresente}" multiple="false" itemValue="name" itemLabel="cantidadPresente" class="form-control input-sm"/></td> 
				<td><div class="has-error"><form:errors path="celulasEpiteliales" class="help-inline"/></div></td>
		    </tr> 
		    <tr>
				<td><label for="moco">Moco: </label> </td>
				<td><form:select path="moco" id="moco" items="${cantidadPresente}" multiple="false" itemValue="name" itemLabel="cantidadPresente" class="form-control input-sm"/></td> 
				<td><div class="has-error"><form:errors path="moco" class="help-inline"/></div></td>
		    </tr> 
		    <tr>
				<td><label for="textoCristales">Cristales: </label> </td>
				<td><form:input path="textoCristales" id="textoCristales"/></td>
				<td><form:select path="cristales" id="cristales" items="${cantidadPresente}" multiple="false" itemValue="name" itemLabel="cantidadPresente" class="form-control input-sm"/></td> 
				<td><div class="has-error"><form:errors path="textoCristales" class="help-inline"/></div></td>
				<td><div class="has-error"><form:errors path="cristales" class="help-inline"/></div></td>
		    </tr>
		    <tr>
				<td><label for="textoCilindros">Cilindros: </label> </td>
				<td><form:input path="textoCilindros" id="textoCilindros"/></td>
				<td><form:select path="cilindros" id="cilindros" items="${cantidadPresente}" multiple="false" itemValue="name" itemLabel="cantidadPresente" class="form-control input-sm"/></td> 
				<td><div class="has-error"><form:errors path="textoCilindros" class="help-inline"/></div></td>
				<td><div class="has-error"><form:errors path="cilindros" class="help-inline"/></div></td>
		    </tr>  
		    <tr>
				<td><label for="textoBacterias">Bacterias: </label> </td>
				<td><form:input path="textoBacterias" id="textoBacterias"/></td>				
				<td><form:select path="bacterias" id="bacterias" items="${cantidadPresente}" multiple="false" itemValue="name" itemLabel="cantidadPresente" class="form-control input-sm"/></td> 
				<td><div class="has-error"><form:errors path="textoBacterias" class="help-inline"/></div></td>
				<td><div class="has-error"><form:errors path="bacterias" class="help-inline"/></div></td>
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
