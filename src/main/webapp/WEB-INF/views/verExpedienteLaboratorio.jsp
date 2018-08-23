<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Expediente de Laboratorio</title>
	<jsp:include page="heading.jsp"/>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>
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
		    jQuery("#fechaInicial").datepicker(datePickerEs);
		    jQuery("#fechaFinal").datepicker(datePickerEs);
		});
	</script>
	<style>
		tr:first-child{
			font-weight: bold;
			background-color: #C6C9C4;
		}
	</style>
	<script>
		function openPage()
		 {
			var tipo, url, nuevaRuta, indiceSelect, context
			context ="${pageContext.request.contextPath}"
			url = "?idExpediente="+${expediente.id};
			indiceSelect = document.getElementById('selectTipoLaboratorio');
			tipo = indiceSelect.options[indiceSelect.selectedIndex].value;
			nuevaRuta = context+"/agregar"+tipo+url;
		 	window.location.href = nuevaRuta;
		 }
		function setDates(){
			var js_fechainicio = document.getElementById("fechaInicial").value;
			var js_fechafin = document.getElementById("fechaFinal").value;
			var lnk = document.getElementById('alink');
			lnk.href = lnk.href + "&fechaInicial=" + js_fechainicio + "&fechaFinal=" + js_fechafin;
		}
	</script>

</head>


<body ng-app="myApp" class="ng-cloak">
	<div class="generic-container" ng-controller="UserController as ctrl">
          <div class="panel panel-default">
	<div class="panel-heading"><span class="lead">Expediente de Laboratorio </span></div>
     	<div class="formcontainer">
	<form name="pacientes" class="form-horizontal">
	<div class="has-success"><label>${message}</label></div>
	<div class="tablecontainer">
	<h3>${expediente.nombres} ${expediente.apellidos}</h3>
	<table width="600">
			<tr>
				<td><label for="carne1">CARNE: ${expediente.carne} </label> </td>
				<td><label for="fechaNac">FECHA NACIMIENTO: ${expediente.fechaNacimiento} </label> </td>
		    </tr>
		    <tr>
				<td><label for="email">EMAIL: ${expediente.email} </label> </td>
				<td><label for="movil">SEXO: ${expediente.sexo} </label> </td>
		    </tr>
    </table>
    <table>
    	<tr>
    		<td>
    			<label>Desde:</label>
    			<input id="fechaInicial" name="fechaInicial"/>
    		</td>
    		<td>
    			<label>Hasta:</label>
    			<input id="fechaFinal" name="fechaFinal"/>
    		</td>
    		<td>
    			<strong><a id=alink onmousedown='setDates()' href="<c:url value='/verExpedienteLaboratorio?idPaciente=${expediente.idPaciente}&carne=${expediente.carne}' />">Filtrar</a></strong>
    		</td>
    	</tr>
    </table>
    <sec:authorize access="hasRole('ADMINISTRADOR')">
    	<div align="right"><strong><a href="<c:url value='/editarEXPEDIENTE_LABORATORIO?idEXPEDIENTE_LABORATORIO=${expediente.id}' />">Modificar Expediente Laboratorio</a></strong></div>
    </sec:authorize>
    <sec:authorize access="hasRole('ADMINISTRADOR')">
    	<div align="right"><strong><a href="<c:url value='/eliminarEXPEDIENTE_LABORATORIO?idExpediente=${expediente.id}' />">Eliminar Expediente Laboratorio</a></strong></div>
    </sec:authorize>
	</div>
	<br>
	<table align="right">
		<tr>
			<td>
				<div>
					<input type="button" value="Agregar Nuevo" name="crearLaboratorio" onclick="openPage()"/>
				</div>
		    </td>
			<td>
				<div>
					<select id="selectTipoLaboratorio" class="form-control input-sm">
					    	<c:forEach items="${tiposLaboratorio}" var="tipo">
					    		<option value="${tipo}">${tipo.toString()}</option>
					    	</c:forEach>
					</select>
				</div>
			</td>
	    </tr>
    </table>
	
	<hr size="3">
	<div id="laboratorios">
		<table class="table table-hover">

		<tr>
			<td>Tipo de Laboratorio</td><td>Responsable</td><td>Fecha</td><td>Consultar</td><td>Editar</td><td>Eliminar</td><td>Imprimir</td>
		</tr>

		 
		<c:forEach items="${labs}" var="unLaboratorio">
			<tr>
			<td>${unLaboratorio.tipoLaboratorio.toString()}</td>
			<td>${unLaboratorio.quimicoBiologo}</td>
			<td>${unLaboratorio.fechaLaboratorio.toString("dd/MM/yyyy")}</td>
			<td><a href="<c:url value='/consultar${unLaboratorio.tipoLaboratorio.name}?id${unLaboratorio.tipoLaboratorio.name}=${unLaboratorio.id}' />">Consultar</a></td>
			<td><a href="<c:url value='/editar${unLaboratorio.tipoLaboratorio.name}?id${unLaboratorio.tipoLaboratorio.name}=${unLaboratorio.id}' />">Editar</a></td>
			<td><a href="<c:url value='/eliminar${unLaboratorio.tipoLaboratorio.name}?id${unLaboratorio.tipoLaboratorio.name}=${unLaboratorio.id}&idExpediente=${expediente.id}' />">Eliminar</a></td>
			<td><a href="<c:url value='/imprimir${unLaboratorio.tipoLaboratorio.name}?id${unLaboratorio.tipoLaboratorio.name}=${unLaboratorio.id}&idExpediente=${expediente.id}' />">Imprimir</a></td>
			</tr>
		</c:forEach>

	</table>
	</div>
	</form>
	</div>     
	</div>
	</div>
      </div>
</body>
</html>
