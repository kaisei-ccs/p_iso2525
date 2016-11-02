$(function () {
	$(".salestable .posRight").dblclick( function(){
		var index = $(this).children().eq(0).text();
		var url = "./Trade_Detail?list="+index;
		popupopen(url);
	});
	$(document).ready(function() {
		$(".salestable").tablesorter();
	});
});