var name;
var url;

function button_name(str){
	name = str;
}

function button_url(str){
	url = str;
}

function title_set(str){
	var form = document.createElement("From");
	form.action = url;
	document.body.appendChild(form);
	var submit = document.createElement("input");
	submit.type = "submit";
	submit.id = "sid";
	submit.value = name;
	form.appendChild(submit);
	var elmP = document.createElement("p");
	elmP.id = "pid";
	elmP.innerHTML = str;
	form.appendChild(elmP);
	var div = document.createElement("div");
	div.id = "clear";
	form.appendChild(div);
}