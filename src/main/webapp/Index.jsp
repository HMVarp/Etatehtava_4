<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="scripts/main.js"></script>
<script src="scripts/login.js"></script>
<link rel="stylesheet" type="text/css" href="css/Tyylit.css">
<title>Kirjaudu</title>
</head>
<body onload="loginForm.tunnus.focus();" class="index">
<div id="kirjaudu" class="kirjaudu">
	<form name="loginForm" class="tunnusSalasana">
	<label>&nbsp;</label><span id="ilmo"></span><br>
	<div id="oikea" class="oikea">
		<label class="vasen">Sähköposti: </label><input type="text" name="tunnus" id="tunnus" class="tunnus"><br>
	</div>
	<div id="oikea" class="oikea">
		<label class="vasen">Salasana:  </label><input type="password" name="salasana" id="salasana"><br>
	</div>
	<label>&nbsp;</label><input type="button" value="Kirjaudu" onclick="salasanaSalaus()" class="nappi"><br>
	<input type="hidden" name="salattuS" id="salattuS">
	</form>
</div>
<script>
if (requestURLParam("unknown") != null) {
	document.getElementById("ilmo").innerHTML="Käyttäjää ei tunneta!";
	setTimeout(function(){ document.getElementById("ilmo").innerHTML=""; }, 3000);
}
</script>
</body>
</html>