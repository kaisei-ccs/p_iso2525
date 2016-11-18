<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Item" %>

<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>値札印刷画面</title>
<!-- スタイルシートの読み込み -->
<link rel="stylesheet" type="text/css" href="./css/common.css">
<link rel="stylesheet" type="text/css" href="./css/Manager_Menu.css">
<link rel="stylesheet" type="text/css" href="./css/Popup.css">
<link rel="stylesheet" type="text/css" href="./css/Print.css">
<!-- JavaScriptの読み込み -->
<script src="./js/jquery-3.1.1.min.js"></script>
<script src="./js/Popup.js"></script>

</head>
<body>

<%-- タイトルバーを出力 --%>
<jsp:include page="Title_Bar.jsp">
    <jsp:param name="caller" value="/Web/Print_PriceTag" />
</jsp:include>
<div style="float: left; width: 70rem; margin-left: 4rem;">
	<p>未印刷一覧</p>
	<table class="display_v" style="width:100%;">
	<tr>
	<th>出品表番号</th>
	<th>項番</th>
	<th>商品名</th>
	</tr>
	<%
		ArrayList<Item> ItemList = Item.findByPrintFlg(false);
		for (Item item : ItemList ) {
			out.write("<tr class=\"paddingTD\">\n");
			out.write("<td class=\"posRight\">" + item.getESID() + "</td>");
			out.write("<td class=\"posRight\">" + item.getINO() + "</td>");
			out.write("<td>" + item.getName() + "</td>\n");
			out.write("</tr>\n");
		}
	%>
	</table>
</div>

<div style="float: right; margin-right: 4rem;">
	<form name="form" method="post" action="">
	<p>
	出品表番号
	</p>
	<p><input type="text" name="ESID_text" size="25" maxlength="20"></p>
	<p>
	項番
	</p>
	<p><input type="text" name="INO_text" size="25" maxlength="20"></p>
	<p><input type="submit" name="addition" value="追加" style="width:48%;">
	<input type="submit" name="print" value="印刷" style="width:48%;">
	</p>

	</form>

	<div>
		<p>　　<%= ItemList.size() %>件印刷予定(A4　<%= ItemList.size() / 6 + 1 %>枚必要)</p>
	</div>

</div>


</body>
</html>