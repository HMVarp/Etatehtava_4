<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Muuta asiakkaan tiedot</title>
<link rel="stylesheet" type="text/css" href="css/Tyylit.css">
<script src="scripts/main.js"></script>
<script src="scripts/io.js"></script>
</head>
<body onload="asetaFocus('etunimi')" onkeydown="tutkiKey(event, 'paivita')">
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
				<td><input type="button" value="Hyväksy" onclick="tutkiJaPaivita()"/></td>
			</tr>
		</tbody>
	</table>
	<input type="hidden" name="asiakas_id" id="asiakas_id">
</form>
<p id="ilmo"></p>
</body>
<script>
haeAsiakas();
</script>
</html>