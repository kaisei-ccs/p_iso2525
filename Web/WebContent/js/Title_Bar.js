var name;
var url;

function load(){
	$("#title").load("/Web/include/titleber.html");
}

function button_name(str){
	$(function() {
		$(".sid").text(str);
	});
}

function button_url(str){
	$(function() {
	    $(".aid").removeattr("href");
	    $(".aid").attr("href", str);
	});
}

function title_set(str){
	$(function() {
		$(".pid").text(str);
	});
}