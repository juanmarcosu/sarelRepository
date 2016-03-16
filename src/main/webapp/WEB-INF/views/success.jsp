<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Pagina de Confirmaci&oacute;n de Registro</title>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body>
	message : ${success}
	<br/>
	<br/>
	Regresar a <a href="<c:url value='/list' />">Listado de Personas</a>
	
</body>

</html>
