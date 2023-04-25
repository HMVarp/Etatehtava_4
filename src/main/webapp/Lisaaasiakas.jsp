<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lisaa uusi asiakas</title>
<link rel="stylesheet" type="text/css" href="css/Tyylit.css">
<script src="scripts/main.js"></script>
</head>
<body>
<form name="lomake">
	<table>
		<thead>
			<tr>
				<th colspan="5" class="oikealle"><a class="linkki" href="Listaaasiakkaat.jsp">Takaisin listaukseen</a></th>
			</tr>
			<tr class="otsikko">
				<th>Etunimi</th>
				<th>Sukunimi</th>
				<th>Puhelinnumero</th>
				<th>Sähköposti</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><input type="text" name="etunimi" id="etunimi" /></td>
				<td><input type="text" name="sukunimi" id="sukunimi" /></td>
				<td><input type="text" name="puhelin" id="puhelin" /></td>
				<td><input type="text" name="sposti" id="sposti" /></td>
				<td><input type="button" value="Lisää" onclick="tutkiJaLisaa()"/></td>
			</tr>
		</tbody>
	</table>
</form>
<p id="ilmo"></p>
</body>
</html>