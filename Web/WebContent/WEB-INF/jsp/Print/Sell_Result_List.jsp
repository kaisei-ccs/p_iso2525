<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Seller,java.util.ArrayList" %>
<%!
	public String makeSeparatData(String argVal) {
		return makeSeparatData(Integer.parseInt(argVal));
	}
	public String makeSeparatData(int argVal) {
		return String.format("%1$,3d",argVal);
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>精算画面</title>
<!-- スタイルシート読み込み -->
<link rel="stylesheet" type="text/css" href="./css/common.css">
<link href="./css/Popup.css" rel="stylesheet" type="text/css">
<link href="./css/Print.css" rel="stylesheet" type="text/css">
<style>
thead th:nth-child(1), tbody td:nth-child(1), tfoot td:nth-child(1){ width: 12.0rem; }
thead th:nth-child(2), tbody td:nth-child(2), tfoot td:nth-child(2){ width: 56.0rem; }
thead th:nth-child(3), tbody td:nth-child(3), tfoot td:nth-child(3){ width: 12.0rem; }
thead th:nth-child(4), tbody td:nth-child(4), tfoot td:nth-child(4){ width: 12.0rem; }
thead th:nth-child(5), tbody td:nth-child(5), tfoot td:nth-child(5){ width: 12.0rem; }
thead th:nth-child(6), tbody td:nth-child(6), tfoot td:nth-child(6){ width: 12.0rem; }
</style>
<script type="text/javascript" src="./js/jquery-3.1.1.min.js"></script>
<script src="./js/Popup.js"></script>
<script src="./js/Print.js"></script>
</head>

<body>

<article>
<section>
<table class="display_v">

<thead>
<tr>
<th>出品者番号</th>
<th>名前</th>
<th>出品数</th>
<th>販売数</th>
<th>販売額</th>
<th>還元額</th>
</tr>
</thead>

<tbody>
<%
ArrayList<ArrayList<String>> SellResultList = (ArrayList<ArrayList<String>>)request.getAttribute("Sell_Result_Data");
ArrayList<Integer> total = new ArrayList<Integer>();
for (int i=0; i<4; i++) {
	total.add(0);
}

for (ArrayList<String> Sell_Result : SellResultList ) {
	out.write("<tr class=\"paddingTD\">\n");
	out.write("<td>" + Sell_Result.get(0) + "</td>");
	out.write("<td>" + Sell_Result.get(1) + "</td>");
	for (int i=0; i<4; i++) {
		out.write("<td class=\"posRight\">" + makeSeparatData(Sell_Result.get(i + 2)) + "</td>");
		total.set(i, total.get(i) + Integer.parseInt( Sell_Result.get(i + 2)) );		// 金額の合計
	}
	out.write("</tr>\n");

}
%>
<tr>
</tr>
</tbody>

<tfoot>
<tr class="paddingTD totalLine">
<td></td>
<td class="posCenter">合　計</td>
<%
for (int i=0; i<4; i++) {
	out.print("<td class=\"posRight\">" + makeSeparatData(total.get(i)) + "");
}
%>
</tr>
</tfoot>
</table>

<script>
$("input[name='selId']").on('change', function(){
	changeDisabled();
});

$('#allCheck').on("change",function(){
	$(".checkRight input[name='selId']").prop("checked", $(this).prop("checked"));
	changeDisabled();
});

function changeDisabled(){
	var cnt = $("input[name='selId']:checked").length;
	if( cnt > 0) {
		$("#btnPrint").removeAttr("disabled");
	} else {
		$("#btnPrint").attr('disabled', "disabled");
	}
}
</script>

</section>
<div id="page" class="posLeft" style="width: 100rem;">
上記の内容を印刷します。
<form name="form" method="post" action="./Sell_Result">
	<p><input type="submit" name="Confirm" value="確定" style="width:100px;" onClick="Print_open()"></p>
</form>
</div>
</article>
</body>
</html>