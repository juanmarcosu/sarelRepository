<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div>
		<table width="100%">
				<tr>
					<td><label for="labelNombre">NOMBRE:&nbsp; &nbsp;</label>${expediente.nombres} ${expediente.apellidos} </td>
					<td><label for="sexo">SEXO:</label>&nbsp; &nbsp;${expediente.sexo} </td>
					<td><label for="movil">MOVIL:</label>&nbsp; &nbsp;${expediente.movil} </td>
				</tr>
				<tr>
					<td><label for="carne1">CODIGO REGISTRO:</label>&nbsp; &nbsp;${expediente.carne} </td>
					<td><label for="email">EMAIL:</label>&nbsp; &nbsp;${expediente.email} </td>
					<td><label for="fechaNac">FECHA NACIMIENTO:</label>&nbsp; &nbsp;${expediente.fechaNacimiento} </td>
			    </tr>
			    <tr>
					<td><label for="telefono">TELEFONO:</label>&nbsp; &nbsp;${expediente.telefono}</td>
					<td></td>
			    	<td><a href="verExpedienteLaboratorio?idPaciente=${expediente.idPaciente}&&carne=${expediente.carne}">Regresar Expediente</a></td>
			    </tr>
	    </table>
	</div>
	<div>
	<hr size="3">
	</div>
</body>
</html>