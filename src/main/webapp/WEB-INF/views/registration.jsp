<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Crear Persona Nueva</title>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<style>

	.error {
		color: #ff0000;
	}
</style>

</head>

<body>

	<h2>Formulario de Registro</h2>
 
	<form:form method="POST" modelAttribute="employee">
		<form:input type="hidden" path="id" id="id"/>
		<table>
			<tr>
				<td><label for="name">Nombre: </label> </td>
				<td><form:input path="name" id="name"/></td>
				<td><form:errors path="name" cssClass="error"/></td>
		    </tr>
	    
			<tr>
				<td><label for="joiningDate">Fecha de Nacimiento: </label> </td>
				<td><form:input path="joiningDate" id="joiningDate"/></td>
				<td><form:errors path="joiningDate" cssClass="error"/></td>
		    </tr>
	
			<tr>
				<td><label for="salary">Numero de Tel&eacute;fono: </label> </td>
				<td><form:input path="salary" id="salary"/></td>
				<td><form:errors path="salary" cssClass="error"/></td>
		    </tr>
	
			<tr>
				<td><label for="ssn">No. Identificaci&oacute;n: </label> </td>
				<td><form:input path="ssn" id="ssn"/></td>
				<td><form:errors path="ssn" cssClass="error"/></td>
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
	</form:form>
	<br/>
	<br/>
	Regresar a <a href="<c:url value='/list' />">Listado de Personas</a>
</body>
</html>
