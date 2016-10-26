var name;
var url;

function load(){
	$("#title").load("/Web/include/titlebar.html");
}

function button_name(str){
	$(function() {
		$(".sid").text(str);
	});
}

function button_url(str){
	$(function() {
	    $(".aid").attr("href", str);
	});
}

function title_set(str){
	$(function() {
		$(".pid").text(str);
	});
}