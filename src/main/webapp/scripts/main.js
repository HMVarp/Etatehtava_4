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
	for(let item of respObjList){//yksi kokoelmalooppeista		
    	htmlStr+="<tr id='rivi_"+item.asiakas_id+"'>";
    	htmlStr+="<td class='tunnus'>"+item.asiakas_id+"</td>";
    	htmlStr+="<td class='etunimi'>"+item.etunimi+"</td>";
    	htmlStr+="<td class='sukunimi'>"+item.sukunimi+"</td>";
    	htmlStr+="<td class='puhelin'>"+item.puhelin+"</td>";
    	htmlStr+="<td class='sposti'>"+item.sposti+"</td>";
    	htmlStr+="</tr>";    	
	}	
	document.getElementById("tbody").innerHTML = htmlStr;
}