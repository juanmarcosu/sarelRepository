<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Pruebas Serológicas</title>
	<jsp:include page="heading.jsp"/>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<script>
	function checkMod(){
		checkResultado1();
		checkResultado2();
		checkResultado3();
		if ("${soloConsulta}" == "true") {
			var el = document.getElementById('wholeForm'),
		        all = el.getElementsByTagName('input'),
		        i;
		    for (i = 0; i < all.length; i++) {
		        all[i].disabled = true;
		    }
		    document.getElementById('idQuimicoBiologo').disabled = true;
		    document.getElementById('resultadoFactorReumatoide').disabled = true;
		    document.getElementById('resultadoProteinaCReactiva').disabled = true;
		    document.getElementById('resultadoAntiEstreptolisinaO').disabled = true;
		}
	}
	function checkResultado1(){
		if(document.getElementById('resultadoFactorReumatoide').value != 'POSITIVO'){
			document.getElementById('factorReumatoide').disabled = true;
		}else{
			document.getElementById('factorReumatoide').disabled = false;
		}
	}
	function checkResultado2(){
		if(document.getElementById('resultadoProteinaCReactiva').value != 'POSITIVO'){
			document.getElementById('proteinaCReactiva').disabled = true;
		}else{
			document.getElementById('proteinaCReactiva').disabled = false;
		}
	}
	function checkResultado3(){
		if(document.getElementById('resultadoAntiEstreptolisinaO').value != 'POSITIVO'){
			document.getElementById('antiEstreptolisinaO').disabled = true;
		}else{
			document.getElementById('antiEstreptolisinaO').disabled = false;
		}
	}
	</script>
</head>

<body class="ng-cloak" onload="checkMod()">
	<div class="generic-container" >
          <div class="panel panel-default">
	<div class="panel-heading"><span class="lead">Pruebas Serológicas</span></div>
     	<div class="formcontainer">
     	<div class="tablecontainer">
     	<form:form modelAttribute="pruebaSerologica" method="POST" class="form-horizontal"  >
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
				<td><label for="resultadoFactorReumatoide">Factor Reumatoide: </label> </td>
				<td><form:select path="resultadoFactorReumatoide" id="resultadoFactorReumatoide" class="form-control input-sm" onchange="checkResultado1();">
					<form:option value="">No Realizado</form:option>
				    <form:options items="${posiblesResultados}" multiple="false" itemValue="name" itemLabel="resultado"/>
				</form:select></td>
				<td><div class="has-error"><form:errors path="resultadoFactorReumatoide" class="help-inline"/></div></td>
	
				<td><form:input path="factorReumatoide" id="factorReumatoide"/></td>
				<td><label for="factorReumatoide">UI/ml </label> </td>
				<td>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</td>
				<td><label for="factorReumatoide2">Hasta 8 UI/ml</label> </td> 
				<td><div class="has-error"><form:errors path="factorReumatoide" class="help-inline"/></div></td>
			</tr>
			<tr>
				<td><label for="resultadoProteinaCReactiva">Proteína "C" Reactiva: </label> </td>
				<td><form:select path="resultadoProteinaCReactiva" id="resultadoProteinaCReactiva" class="form-control input-sm" onchange="checkResultado2();">
					<form:option value="">No Realizado</form:option>
				    <form:options items="${posiblesResultados}" multiple="false" itemValue="name" itemLabel="resultado"/>
				</form:select></td>
			<%-- 	<td><form:select path="resultadoProteinaCReactiva" id="resultadoProteinaCReactiva" items="${posiblesResultados}" multiple="false" itemValue="name" itemLabel="resultado" class="form-control input-sm" onchange="checkResultado2();"/></td> --%>
				<td><div class="has-error"><form:errors path="resultadoProteinaCReactiva" class="help-inline"/></div></td>
				
				<td><form:input path="proteinaCReactiva" id="proteinaCReactiva"/></td>
				<td><label for="proteinaCReactiva">mg/l</label> </td>
				<td>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</td>
				<td><label for="proteinaCReactiva2">Hasta 6 mg/l</label> </td>  
				<td><div class="has-error"><form:errors path="proteinaCReactiva" class="help-inline"/></div></td>
			</tr>
			<tr>
				<td><label for="resultadoAntiEstreptolisinaO">Anti Estreptolisina "O": </label> </td>
				<td><form:select path="resultadoAntiEstreptolisinaO" id="resultadoAntiEstreptolisinaO" class="form-control input-sm" onchange="checkResultado3();">
					<form:option value="">No Realizado</form:option>
				    <form:options items="${posiblesResultados}" multiple="false" itemValue="name" itemLabel="resultado"/>
				</form:select></td>
				<td><div class="has-error"><form:errors path="resultadoAntiEstreptolisinaO" class="help-inline"/></div></td>
				
				<td><form:input path="antiEstreptolisinaO" id="antiEstreptolisinaO"/></td> 
				<td><label for="antiEstreptolisinaO">UI/ml</label> </td>
				<td>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</td>
				<td><label for="antiEstreptolisinaO2">Hasta 200 UI/ml</label> </td> 
				<td><div class="has-error"><form:errors path="antiEstreptolisinaO" class="help-inline"/></div></td>
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