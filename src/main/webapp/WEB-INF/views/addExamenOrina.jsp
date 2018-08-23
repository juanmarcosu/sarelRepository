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
		    var els = document.getElementById('wholeForm'),
		        alls = els.getElementsByTagName('select'),
		        is;
		    for (is = 0; is < alls.length; is++) {
		        alls[is].disabled = true;
		    }
		    var elt = document.getElementById('wholeForm'),
		        allt = elt.getElementsByTagName('textarea'),
		        it;
		    for (it = 0; it < allt.length; it++) {
		        allt[it].disabled = true;
		    }
		}
	}
	</script>
</head>

<body class="ng-cloak" onload="checkMod()">
	<div class="generic-container" >
          <div class="panel panel-default">
	<div class="panel-heading"><span class="lead">Examen de Orina</span></div>
     	<div class="formcontainer">
     	<div class="tablecontainer">
     	<form:form modelAttribute="examenOrina" method="POST" class="form-horizontal"  >
     	<div class="has-error"><label>${alerta}</label></div>
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
				<td><label for="color">Color: </label> </td>
				<td><form:select path="color" id="color" items="${coloresOrina}" multiple="false" itemValue="name" itemLabel="colorOrina" class="form-control input-sm"/> </td>
				<td></td>
				<td><label for="aspecto">Aspecto: </label> </td>
				<td><form:select path="aspecto" id="aspecto" items="${aspectosOrina}" multiple="false" itemValue="name" itemLabel="aspectoOrina" class="form-control input-sm"/></td> 
				<td><div class="has-error"><form:errors path="color" class="help-inline"/></div></td>
				<td><div class="has-error"><form:errors path="aspecto" class="help-inline"/></div></td>
		    </tr>
		    <tr>
		    	<td><label for="otroColor">Otro color: </label> </td>
		    	<td><form:input path="otroColor" id="otroColor"/></td>
		    </tr> 
		    <tr>
				<td><label for="ph">Ph: </label> </td>
				<td><form:input path="ph" id="ph"/></td>
				<td></td>
				<td><label for="densidad">Densidad: </label> </td>
				<td><form:input path="densidad" id="densidad"/></td>
				<td><div class="has-error"><form:errors path="ph" class="help-inline"/></div></td>
				<td><div class="has-error"><form:errors path="densidad" cssClass="error"/></div></td>
		    </tr>
		    <tr>
		    	<td><label for="bioquimico">Bioqu&iacute;mico: </label> </td>
		    	<td><form:textarea path="bioquimico" id="bioquimico" rows="5" cols="25"></form:textarea></td>
		    </tr>
		    <tr>
				<td><label for="leucositos">Leucocitos: </label> </td>
				<td><form:input path="leucositos" id="leucositos"/></td>
				<td><label for="leucositosLabel">x campo </label> </td>
				
				<td><label for="eritrositos">&nbsp; &nbsp;Eritrocitos: </label> </td>
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
				<td><label for="cristales">Cristales: </label> </td>
				<td><form:textarea path="cristales" id="cristales" rows="5" cols="25"></form:textarea> 
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
	</div>     
	</div>
	</div>
	</div>
</body>
</html>
