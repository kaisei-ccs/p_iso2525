$(function () {
	$(".salestable .posRight").dblclick( function(){
		var index = $(this).children().eq(0).text();
		var url = "./EntrySheet_EditRewrite?list="+index;
		//var url = "./EntrySheet_EditRewrite";
		popupopen(url);
	});
	$(document).ready(function() {
		$(".display_v").tablesorter();
	});
});

function Initialization(){
	var form = document.getElementsByName("search");
	//var childs = form.childNodes;
	for(i=0; i<form[0].length-3; i++){
		form[0][i].value="";
	}
}