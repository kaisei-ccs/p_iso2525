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
<link href="./css/Popup.css" rel="stylesheet" type="text/css">
<link href="./css/Print.css" rel="stylesheet" type="text/css">
<!-- JavaScriptの読み込み -->
<script src="./js/jquery-3.1.1.min.js"></script>
<script src="./js/Popup.js"></script>
<script src="./js/Print.js"></script>
</head>
<body>

<table>
<caption>
<strong>
値札印刷フォーマット
</strong>
</caption>
<tr>
<th>商品名</th>
<th width="150">開始価格</th>
<th width="200">最終価格</th>
</tr>
	<%
		ArrayList<Item> ItemList = Item.findByPrintFlg(false);
		for (Item item : ItemList ) {
			out.write("<tr class=\"paddingTD\">\n");
			out.write("<td class=\"posRight\">" + item.getESID() + "</td>");
			out.write("<td>" + item.getINO() + "</td>");
			out.write("<td>" + item.getName() + "</td>\n");
			out.write("</tr>\n");
		}
	%>
</table>

<div id="page">
上記の内容を印刷します。
<form name="form" method="post" action="">
	<p><input type="submit" name="Confirm" value="確定" style="width:100px;" onClick="Print_open()"></p>
</form>
</div>
</body>
</html>