<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Resultados VIH</title>
	<jsp:include page="heading.jsp"/>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<%--<link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>--%>
	<style>
		tr:first-child{
			font-weight: bold;
			background-color: #C6C9C4;
		}
	</style>
	<script type="text/javascript">
	function openPage()
	 {
		var url, context, nuevaRuta
		context ="${pageContext.request.contextPath}";
		url = "/agregarPRUEBA_VIH";
		nuevaRuta = context+url;
	 	window.location.href = nuevaRuta;
	 }
	</script>
</head>


<body ng-app="myApp" class="ng-cloak">
	<div class="generic-container" ng-controller="UserController as ctrl">
          <div class="panel panel-default">
	<div class="panel-heading"><span class="lead">Resultados VIH </span></div>
     	<div class="formcontainer">
	<form name="resultados" class="form-horizontal">
	<div class="tablecontainer">
	<h2>Busqueda:</h2>
	<table>
			<tr>
				<td><label for="orientador">Orientador: </label> </td>
				<td><input type="text" name="orientador"  id="orientador"/></td>
		    </tr>
		    <tr>
				<td><label for="fechaLaboratorio">Fecha de Laboratorio (dd/MM/yyyy): </label> </td>
				<td><input type="text" name="fechaLaboratorio" id="fechaLaboratorio"/></td>
		    </tr>
	    	<tr>
				<td><label for="codigo">Codigo: </label> </td>
				<td><input type="text" name="codigo" id="codigo"/></td>
		    </tr>
		    <tr>
		    	<td><input type="submit" value="Buscar"/></td>
		    </tr>
    </table>
    <h2>Resultados:</h2>
    			<div align="right">
					<input type="button" value="Agregar Nuevo Resultado VIH" name="crearLaboratorioVIH" onclick="openPage()"/>
				</div>
				<br>
	<table class="table table-hover">
		<tr>
			<td>Codigo Paciente</td><td>Fecha Laboratorio</td><td>Orientador</td><td>Consultar</td><td>Editar</td><td>Imprimir</td><td>Eliminar</td>
		</tr>
		 
		<c:forEach items="${resultados}" var="unResultado">
			<tr>
			<td>${unResultado.codigo}</td>
			<td>${unResultado.fechaLaboratorio}</td>
			<td>${unResultado.orientador}</td>
			<td><a href="<c:url value='/consultarPRUEBA_VIH?idPRUEBA_VIH=${unResultado.id}' />">Consultar</a></td>
			<td><a href="<c:url value='/editarPRUEBA_VIH?idPRUEBA_VIH=${unResultado.id}' />">Editar</a></td>
			<td><a href="<c:url value='/imprimirPRUEBA_VIH?idPRUEBA_VIH=${unResultado.id}' />">Imprimir</a></td>
			<td><a href="<c:url value='/eliminarPRUEBA_VIH?idPRUEBA_VIH=${unResultado.id}' />">Eliminar</a></td>
			</tr>
		</c:forEach>
	</table>
	</div>
	<br/>
	</form>
	</div>     
	</div>
	</div>
      </div>
</body>
</html>
