function salasanaSalaus() {
	const tunnus = document.getElementById("tunnus");
	const salasana = document.getElementById("salasana");
	const salattuS = document.getElementById("salattuS");
	console.log(salasana.value, tunnus.value);
	sha256(salasana.value + tunnus.value)
		.then((hashStr) => {
			salattuS.value = hashStr;
			salasana.value = "";
			tarkastaKirjautuminen();
		});
}

async function sha256(str) {
	const msgUint8 = new TextEncoder().encode(str);
	const hashBuffer = await crypto.subtle.digest('SHA-256', msgUint8);
	const hashArray = Array.from(new Uint8Array(hashBuffer));
	const hashHex = hashArray.map((b) => b.toString(16).padStart(2, '0')).join('');
	return hashHex;
}

function tarkastaKirjautuminen() {
	let data = new URLSearchParams();
	console.log(data);
	for (const pair of new FormData(loginForm)) {
		data.append(pair[0], pair[1]);
	}
	//console.log(Object.fromEntries(data)); //tieto kulkee tähän asti
	let url = "login";
	let requestOptions = {
		method: "POST",
		headers: { "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8" },
		body: data
	};
	fetch(url, requestOptions)
		.then(response => response.text())//Muutetaan vastaus tekstiksi
		.then(response => document.location = response); //Lähetetään käyttäjä eteenpäin
}
