function haeAsiakkaat() {
	let url = "myynnit?hakusana=" + document.getElementById("hakusana").value;
	let requestOptions = {
			method: "GET",
			headers: { "Content-Type": "application/x-www-form-urlencoded" }
	};
	fetch (url, requestOptions)
	.then(response => response.json())
	.then (response => printItems(response))
	.catch (errorText => console.error("Fetch failed: " + errorText));
}

function printItems(respObjList) {
	let htmlStr="";
	for(let item of respObjList){		
    	htmlStr+="<tr asiakas_id='rivi_"+item.asiakas_id+"'>";
    	htmlStr+="<td class='tunnus'>"+item.asiakas_id+"</td>";
    	htmlStr+="<td class='etunimi'>"+item.etunimi+"</td>";
    	htmlStr+="<td class='sukunimi'>"+item.sukunimi+"</td>";
    	htmlStr+="<td class='puhelin'>"+item.puhelin+"</td>";
    	htmlStr+="<td class='sposti'>"+item.sposti+"</td>";
    	htmlStr+="<td><a class='muuta' href='Muutaasiakas.jsp?asiakas_id="+item.asiakas_id+"'>Muuta</a>&nbsp;"; // merkki = puuttui
    	htmlStr+="<span class='poista' onclick=varmistaPoisto("+item.asiakas_id+",'"+encodeURI(item.etunimi)+"','"+encodeURI(item.sukunimi)+"')>Poista</span></td>";
    	htmlStr+="</tr>";    	
	}	
	document.getElementById("tbody").innerHTML = htmlStr;
}

function lisaaTiedot() {
	let formData = serialize_form(document.lomake);
	//console.log(formData); //onnistuu
	let url = "myynnit";    
    let requestOptions = {
        method: "POST",
        headers: { "Content-Type": "application/json" },  
    	body: formData
    };
    fetch(url, requestOptions)
    .then(response => response.json())
   	.then(responseObj => {
		//console.log(responseObj); //onnistuu
   		if(responseObj.response == 0){
   			document.getElementById("ilmo").innerHTML = "Asiakkaan lisääminen epäonnistui";	
        }else if(responseObj.response == 1){ 
        	document.getElementById("ilmo").innerHTML = "Asiakkaan lisääminen onnistui";
			document.lomake.reset();	        	
		}
		setTimeout(function(){ document.getElementById("ilmo").innerHTML=""; }, 3000);
   	})
   	.catch(errorText => console.error("Fetch failed: " + errorText));
}

function poistaAsiakas(asiakas_id, etunimi, sukunimi){
	let url = "myynnit?asiakas_id=" + asiakas_id;    
    let requestOptions = {
        method: "DELETE"             
    };  
    //console.log(url); //toimii  
    fetch(url, requestOptions)
    .then(response => response.json())
   	.then(responseObj => {	
   		console.log(responseObj);
   		if(responseObj.response == 0){
			alert("Asiakkaan poistaminen epäonnistui");	        	
        }else if(responseObj.response == 1){ 
			document.getElementById("rivi_"+asiakas_id).style.backgroundColor="red";
			alert("Asiakkaan " + decodeURI(etunimi) + " " + decodeURI(sukunimi) +" poistaminen onnistui");
			haeAsiakkaat();        	
		}
   	})
   	.catch(errorText => console.error("Fetch failed: " + errorText));
}	//palauttaa error vaikka poistaa asiakkaan

function haeAsiakas() {  // tietokannan taulun nimi oli väärin => expected token '<', "<!doctype "... is not valid JSON
	let url = "myynnit?asiakas_id=" +requestURLParam("asiakas_id");
	console.log(url); //vastaus "myynnit?asiakas_id=undefined" => tietokannan taulun nimi oli väärä
	let requestOptions = {
		method: "GET",
		headers: { "Content-Type": "application/x-www-form-urlencoded" }
		
	};
	console.log(requestOptions)
	fetch(url, requestOptions)
    .then(response => response.json())
   	.then(response => {
		   console.log(response); // vastaus "null"
		   document.getElementById("etunimi").value=response.etunimi;
		   document.getElementById("sukunimi").value=response.sukunimi;
		   document.getElementById("puhelin").value=response.puhelin;
		   document.getElementById("sposti").value=response.sposti;
		   document.getElementById("asiakas_id").value=response.asiakas_id; // lisätty, jolloin löysi asiakas_id
		   })
		   .catch(errorText => console.error("Fetch failed: " + errorText));	
}

function paivitaTiedot() {
	let formData = serialize_form(lomake);
	//console.log(formData); //asiakas_id ei välity, on tyhjä => korjaantui sillä, että lisäsin haeAsiakas() asiakas_id
	let url = "myynnit"; //tämä url put metodin löytämiseen
	//console.log(url);
	let requestOptions = {
		method: "PUT",
		headers: { "Content-Type": "application/json; charset=UTF-8" },
		body: formData
	};
	fetch(url, requestOptions)
    .then(response => response.json())
   	.then(responseObj => {
		   console.log(responseObj); 
			if(responseObj.response == 0){
				document.getElementById("ilmo").innerHTML = "Asiakastietojen muuttaminen epäonnistui";	        	
        	} else if(responseObj.response == 1){ 
				document.getElementById("ilmo").innerHTML = "Asiakastietojen muuttaminen onnistui";
				document.lomake.reset();
		   	}
		   	setTimeout(function(){ document.location="Listaaasiakkaat.jsp"; }, 3000);
		  })
	.catch(errorText => console.error("Fetch failed: " + errorText));
}