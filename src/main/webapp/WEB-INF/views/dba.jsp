<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Pagina DBA</title>
</head>
<body>
	Hola <strong>${user}</strong>! Bienvenido a la Pagina del DBA.
	<a href="<c:url value="/logout" />">Cerrar esta Sesi&oacute;n</a>
</body>
</html>
