<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Seller" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出品者登録画面</title>
<link rel="stylesheet" type="text/css" href="./css/common.css">
<script type="text/javascript" src="./js/jquery-3.1.1.min.js"></script>
</head>

<body>

<%-- タイトルバーを出力 --%>
<jsp:include page="Title_Bar.jsp">
    <jsp:param name="caller" value="Seller_Insert" />
</jsp:include>

<article>
<section>
<h2 class="hidden">登録情報</h2>
<div style="width:1220rem;" class="posRight">
<input type="file" value="CSV読込">
</div>

<form method="post" action="./Seller_Insert">
<table class="display_v">
<tr>
<th style="width: 100rem;">出品者番号</th>
<th style="width: 560rem;">氏　　名</th>
<th style="width: 560rem;">カ　　ナ</th>
</tr>
<tr>
<td><input type="text" id="inputId"   name="inputId"   value="" maxlength="8" class="posRight"></td>
<td><input type="text" id="inputName" name="inputName" value="" maxlength="30"></td>
<td><input type="text" id="inputKana" name="inputKana" value="" maxlength="30"></td>
</tr>
<tr class="non-line">
<td colspan="3">
<input type="reset" value="入力クリア">
<input type="submit" value="登　録">
</td>
</tr>
</table>
</form>
</section>

<section>
<h2 class="hidden">登録済一覧</h2>
<table id="dispSeller" class="display_v">
<tr>
<th style="width: 100rem;">出品者番号</th>
<th style="width: 560rem;">氏　　名</th>
<th style="width: 560rem;">カ　　ナ</th>
</tr>

<%-- 登録させている情報を読み込む --%>
<%
	ArrayList<Seller> sellerList = Seller.fetchAll();
	for (Seller s : sellerList ) {
		out.write("<tr class=\"paddingTD\">\n");
		out.write("<td class=\"posRight\">" + s.getSID() + "</td>");
		out.write("<td>" + s.getSellerName() + "</td>");
		out.write("<td>" + s.getSellerKana() + "</td>\n");
		out.write("</tr>\n");
	}
%>

</table>

<script>

$('#dispSeller td').click( function(){
	var $cur_tr = $(this).parent();
	$('#inputId'  ).val($cur_tr.children('td:eq(0)').text());
	$('#inputName').val($cur_tr.children('td:eq(1)').text());
	$('#inputKana').val($cur_tr.children('td:eq(2)').text());
});
</script>
</section>
</article>

</body>
</html>