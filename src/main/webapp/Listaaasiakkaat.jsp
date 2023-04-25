<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/Tyylit.css">
<script src="scripts/main.js"></script>
<title>Asiakashaku</title>
</head>
<body>
<table id="listaus">
		<thead>
			<tr>
				<th colspan="6" class="oikealle"><a href="Lisaaasiakas.jsp">Lisää uusi asiakas</a></th>
			</tr>
			<tr class="haku">
				<th colspan="2" class="hakusana">Hakusana:</th>
				<th colspan="2" class="hakukentta"><input type="text" id="hakusana"></th>
				<th colspan="2" class="hakunappi"><input type="button" value="Hae" id="hakunappi" onclick="haeAsiakkaat()"></th>
			</tr>
			<tr class="otsikko">
				<th class="tunnus">Tunnus</th>
				<th class="etunimi">Etunimi</th>
				<th class="sukunimi">Sukunimi</th>					
				<th class="puhelin">Puhelin</th>
				<th class="sposti">Sähköposti</th>
				<th></th>
			</tr>
		</thead>
		<tbody id="tbody" class="vastaus">
		</tbody>
</table>
<span id="ilmo">
</span>
<script>
haeAsiakkaat();
</script>
</body>
</html>