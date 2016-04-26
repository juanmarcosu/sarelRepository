<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div class="tablecontainer">
		<table width="100%">
				<tr>
					<td><label for="labelNombre">NOMBRE: </label> </td>
					<td><laber for="nombres">${expediente.nombres} ${expediente.apellidos}</laber></td>
					<td><label for="movil">MOVIL: ${expediente.movil} </label> </td>
				</tr>
				<tr>
					<td><label for="carne1">CARNE: ${expediente.carne} </label> </td>
					<td><laber for="codigoPersona">CODIGO PERSONAL: - </laber></td>
					<td><label for="fechaNac">FECHA NACIMIENTO: ${expediente.fechaNacimiento} </label> </td>
			    </tr>
			    <tr>
					<td><label for="email">EMAIL: ${expediente.email} </label> </td>
					<td><label for="sexo">SEXO: ${expediente.sexo} </label> </td>
					<td><label for="telefono">TELEFONO: ${expediente.telefono }</label>
			    </tr>
	    </table>
	</div>
	<div>
	<hr size="3">
	</div>
</body>
</html>