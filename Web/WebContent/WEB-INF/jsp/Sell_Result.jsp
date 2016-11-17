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
<style>
thead th:nth-child(1), tbody td:nth-child(1), tfoot td:nth-child(1){ width: 12.0rem; }
thead th:nth-child(2), tbody td:nth-child(2), tfoot td:nth-child(2){ width: 56.0rem; }
thead th:nth-child(3), tbody td:nth-child(3), tfoot td:nth-child(3){ width: 12.0rem; }
thead th:nth-child(4), tbody td:nth-child(4), tfoot td:nth-child(4){ width: 12.0rem; }
thead th:nth-child(5), tbody td:nth-child(5), tfoot td:nth-child(5){ width: 12.0rem; }
thead th:nth-child(6), tbody td:nth-child(6), tfoot td:nth-child(6){ width: 12.0rem; }
</style>
<script type="text/javascript" src="./js/jquery-3.1.1.min.js"></script>
</head>

<body>

<%-- タイトルバーを出力 --%>
<jsp:include page="Title_Bar.jsp">
    <jsp:param name="caller" value="Sell_Result" />
</jsp:include>

<article>
<section>
<form method="post" action="">
<h2 class="hidden">販売情報</h2>

<table class="display_v scrollBody">

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

<tbody style="height: 43.5rem;">
<%
ArrayList<ArrayList<String>> SellResultList = (ArrayList<ArrayList<String>>)request.getAttribute("Sell_Result_Data");
ArrayList<Integer> total = new ArrayList<Integer>();
for (int i=0; i<4; i++) {
	total.add(0);
}

for (ArrayList<String> Sell_Result : SellResultList ) {
	out.write("<tr class=\"paddingTD\">\n");
	out.write("<td>" +
				"<dl class=\"checkRight\">" +
				"<dt><input type=\"checkbox\" id=\"selId\" name=\"selId\" value=\"" + Sell_Result.get(0) + "\"></dt>" +
				"<dd>" + Sell_Result.get(0) + "</dd>" +
				"</dl>" +
			  "</td>");
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
<td>
<dl class="checkRight">
<dt><input type="checkbox" id="allCheck"></dt>
<dd>全選択</dd>
</dl>
</td>
<td class="posCenter">合　計</td>
<%
for (int i=0; i<4; i++) {
	out.print("<td class=\"posRight\">" + makeSeparatData(total.get(i)) + "");
}
%>
</tr>
<tr class="non-line">
<td colspan="6"><input type="submit" value="印　刷" id="btnPrint" name="btnPrint" disabled="disabled"></td>
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

</form>
</section>
</article>
</body>
</html>