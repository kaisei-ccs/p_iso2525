<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Seller" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出品者登録画面</title>
<link rel="stylesheet" type="text/css" href="./css/common.css">
<style>
thead th:nth-child(1), tbody td:nth-child(1), tfoot td:nth-child(1){ width: 12.0rem; }
thead th:nth-child(2), tbody td:nth-child(2), tfoot td:nth-child(2){ width: 56.0rem; }
thead th:nth-child(3), tbody td:nth-child(3), tfoot td:nth-child(3){ width: 56.0rem; }
</style>
<script type="text/javascript" src="./js/jquery-3.1.1.min.js"></script>
</head>

<body>

<%-- タイトルバーを出力 --%>
<jsp:include page="Title_Bar.jsp">
    <jsp:param name="caller" value="Seller_Insert" />
</jsp:include>

<article>
<form method="post" action="./Seller_Insert">
<section>
<h2 class="hidden">登録情報</h2>
<%
String seller[] = {"","",""};
String errMsg = (String) request.getAttribute("Seller_Insert_ErrMsg");
if (null != errMsg) {
	out.write("<div class=\"err\" style=\"width:1240rem;\">" + errMsg + "</div>\n");
	seller = (String[]) request.getAttribute("Seller_Insert_Data");
}
%>
<table class="display_v">
<tr>
<th>出品者番号</th>
<th>氏　　名</th>
<th>カ　　ナ</th>
</tr>
<tr>
<td><input type="text" id="inputId"   name="inputId"   value="<%= seller[0] %>" maxlength="6" class="posRight"></td>
<td><input type="text" id="inputName" name="inputName" value="<%= seller[1] %>" maxlength="30"></td>
<td><input type="text" id="inputKana" name="inputKana" value="<%= seller[2] %>" maxlength="30"></td>
</tr>
<tr class="non-line">
<td colspan="3">
<input type="reset" value="入力クリア">
<input type="submit" value="登　録" id="btnIns" name="btnIns">
</td>
</tr>
</table>
</section>

<section>
<h2 class="hidden">登録済一覧</h2>
<table id="dispSeller" class="display_v scrollBody">
<thead>
<tr>
<th>出品者番号</th>
<th>氏　　名</th>
<th>カ　　ナ</th>
</tr>
</thead>

<tbody style="height: 31.5rem;">
<%-- 登録させている情報を読み込む --%>
<%
ArrayList<Seller> sellerList = Seller.fetchAll();
for (Seller s : sellerList ) {
	out.write("<tr class=\"paddingTD\">\n");
	out.write("<td>" +
				"<dl class=\"checkRight\">" +
				"<dt><input type=\"checkbox\" id=\"delId\" name=\"delId\" value=\"" + s.getSID() + "\"></dt>" +
				"<dd>" + s.getSID() + "</dd>" +
				"</dl>" +
			  "</td>");
	out.write("<td>" + s.getSellerName() + "</td>");
	out.write("<td>" + s.getSellerKana() + "</td>\n");
	out.write("</tr>\n");
}
%>
</tbody>

<tfoot>
<tr class="non-line">
<td>
<dl class="checkRight">
<dt><input type="checkbox" id="allCheck"></dt>
<dd>全選択</dd>
</dl>
</td>
<td></td>
<td><input type="submit" value="削　除" id="btnDel" name="btnDel" disabled="disabled"></td>
</tr>
</tfoot>
</table>

<script>
$('#dispSeller tbody td').dblclick( function(){
	var $cur_tr = $(this).parent();
	$('#inputId'  ).val($cur_tr.children('td:eq(0)').text());
	$('#inputName').val($cur_tr.children('td:eq(1)').text());
	$('#inputKana').val($cur_tr.children('td:eq(2)').text());
});

$("input[name='delId']").on('change', function(){
	changeDisabled();
});

$('#allCheck').on("change",function(){
	$(".checkRight input[name='delId']").prop("checked", $(this).prop("checked"));
	changeDisabled();
});

function changeDisabled(){
	var cnt = $("input[name='delId']:checked").length;
	if( cnt > 0) {
		$("#btnIns").attr('disabled', "disabled");
		$("#btnDel").removeAttr("disabled");
	} else {
		$("#btnIns").removeAttr("disabled");
		$("#btnDel").attr('disabled', "disabled");
	}
}
</script>
</section>
</form>
</article>

</body>
</html>