<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Hematología Completa</title>
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
		}
	}
	</script>
</head>

<body class="ng-cloak" onload="checkMod()">
	<div class="generic-container" >
          <div class="panel panel-default">
	<div class="panel-heading"><span class="lead">Hematología Completa</span></div>
     	<div class="formcontainer">
     	<div class="tablecontainer">
     	<form:form modelAttribute="hematologiaCompleta" method="POST" class="form-horizontal"  >
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
				<td><label for="rbc">RBC:</label> </td>
				<td><form:input path="rbc" id="rbc"/></td>
				<td><label for="dimentionalrbc"> x10<SUP>12</SUP> /L &nbsp; &nbsp;</label> </td>
				<td><label for="rbc2">Recuento de Globulos Rojos</label> </td> 
				<td><div class="has-error"><form:errors path="rbc" class="help-inline"/></div></td>
			</tr>
			<tr>
				<td><label for="hct">HCT:</label> </td>
				<td><form:input path="hct" id="hct"/></td>
				<td><label for="dimentionalhct"> % </label> </td>
				<td><label for="hct2">Hematocrito</label> </td> 
				<td><div class="has-error"><form:errors path="hct" class="help-inline"/></div></td>
			</tr>
			<tr>
				<td><label for="mcv">MCV:</label> </td>
				<td><form:input path="mcv" id="mcv"/></td>
				<td><label for="dimentionalmcv"> fl </label> </td> 
				<td><label for="mcv2"></label> </td>
				<td><div class="has-error"><form:errors path="mcv" class="help-inline"/></div></td>
			</tr>
			<tr>
				<td><label for="hgb">HGB:</label> </td>	
				<td><form:input path="hgb" id="hgb"/></td>
				<td><label for="dimentionalhgb"> g/dl </label> </td>
				<td><label for="hgb2">Hemoglobina</label> </td> 
				<td><div class="has-error"><form:errors path="hgb" class="help-inline"/></div></td>
			</tr>
			<tr>
				<td><label for="mch">MCH:</label> </td>
				<td><form:input path="mch" id="mch"/></td>
				<td><label for="dimentionalmch"> pg </label> </td>
				<td><label for="mch2"></label> </td> 
				<td><div class="has-error"><form:errors path="mch" class="help-inline"/></div></td>
			</tr>
			<tr>
				<td><label for="mchc">MCHC:</label> </td>
				<td><form:input path="mchc" id="mchc"/></td>
				<td><label for="dimentionalmchc"> g/dl </label> </td> 
				<td><label for="mchc2"></label> </td>
				<td><div class="has-error"><form:errors path="mchc" class="help-inline"/></div></td>
			</tr>
			<tr>
				<td><label for="plt">PLT:</label> </td>
				<td><form:input path="plt" id="plt"/></td>
				<td><label for="dimentionalplt"> x10<SUP>9</SUP> /L </label> </td> 
				<td><label for="plt2">Plaquetas</label> </td>
				<td><div class="has-error"><form:errors path="plt" class="help-inline"/></div></td>
			</tr>
			<tr>
				<td><label for="wbc">WBC:</label> </td>
				<td><form:input path="wbc" id="wbc"/></td>
				<td><label for="dimentionalwbc"> x10<SUP>9</SUP> /L </label> </td> 
				<td><label for="wbc2">Recuento de Globulos Blancos</label></td>
				<td><div class="has-error"><form:errors path="wbc" class="help-inline"/></div></td>
			</tr>
			<tr>
				<td><label for="lym">LYM:</label> </td>
				<td><form:input path="lym" id="lym"/></td>
				<td><label for="dimentionallym"> 29.1% </label> </td> 
				<td><label for="lym2"></label> </td>
				<td><div class="has-error"><form:errors path="lym" class="help-inline"/></div></td>
			</tr>
			<tr>
				<td><label for="gran">GRAN:</label> </td>
				<td><form:input path="gran" id="gran"/></td>
				<td><label for="dimentionalgran"> 65.5% </label> </td> 
				<td><label for="gran2"></label> </td>
				<td><div class="has-error"><form:errors path="gran" class="help-inline"/></div></td>
			</tr>
			<tr>
				<td><label for="mid">MID:</label> </td>
				<td><form:input path="mid" id="mid"/></td>
				<td><label for="dimentionalmid"> 5.4% </label> </td>
				<td><label for="mid2"></label> </td> 
				<td><div class="has-error"><form:errors path="mid" class="help-inline"/></div></td>
			</tr>
			<tr>
				<td><label for="vse">VSE:</label> </td>
				<td><form:input path="vse" id="vse"/></td>
				<td><label for="dimentionalvse">mm/hr</label> </td> 
				<td><label for="vse2"></label> </td>
				<td><div class="has-error"><form:errors path="vse" class="help-inline"/></div></td>
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
