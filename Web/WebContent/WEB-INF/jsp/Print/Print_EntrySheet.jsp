<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出品表記入用紙印刷</title>
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
<%
int Sheet = Integer.valueOf(request.getAttribute("Sheet").toString());
int SheetSize = Integer.valueOf(request.getAttribute("SheetSize").toString());
for (int i = SheetSize; (SheetSize+Sheet) > i; i++) {
	out.write("<div style=\"page-break-before:always;\">\n");
	out.write("<table>\n");
	out.write("<caption>\n");
	out.write("<strong>出品表</strong>\n");
	out.write("</caption>\n");
	out.write("<tr>\n");
	out.write("<th width=\"200\">出品表番号</th>\n");
	out.write("<th width=\"200\">学籍番号</th>\n");
	out.write("<th width=\"200\">名前</th>\n");
	out.write("</tr>\n");
	out.write("<tr>\n");
	out.write("<td>&nbsp;</td>");
	out.write("<td>&nbsp;</td>");
	out.write("<td>&nbsp;</td>\n");
	out.write("</tr>\n");
	out.write("</table>\n<br>");

	out.write("例<br>");

	out.write("<table>\n");
	out.write("<tr>\n");
	out.write("<th width=\"50\">項番</th>\n");
	out.write("<th width=\"300\">商品名</th>\n");
	out.write("<th width=\"100\">開始価格</th>\n");
	out.write("<th width=\"100\">終了価格</th>\n");
	out.write("<th width=\"100\">個数</th>\n");
	out.write("<th width=\"100\">返却の有無</th>\n");
	out.write("</tr>\n");
	for (int j = 0; 10 > j; j++) {
		out.write("<tr>\n");
		out.write("<td>&nbsp;</td>\n");
		out.write("<td>&nbsp;</td>\n");
		out.write("<td>&nbsp;</td>\n");
		out.write("<td>&nbsp;</td>\n");
		out.write("<td>&nbsp;</td>\n");
		out.write("<td>&nbsp;</td>\n");
		out.write("</tr>\n");
	}
	out.write("</table>\n<br>");

	out.write("<table>\n");
	out.write("<tr>\n");
	out.write("<th>出品表番号</th>\n");
	out.write("<th>出品表番号</th>\n");
	out.write("<th>出品表番号</th>\n");
	out.write("<th>出品表番号</th>\n");
	out.write("<th>出品表番号</th>\n");
	out.write("<th>出品表番号</th>\n");
	out.write("<th>出品表番号</th>\n");
	out.write("<th>出品表番号</th>\n");
	out.write("<th>出品表番号</th>\n");
	out.write("<th>出品表番号</th>\n");
	out.write("</tr>\n");
	out.write("<tr>\n");
	out.write("<td>" + i + "</td>\n");
	out.write("<td>" + i + "</td>\n");
	out.write("<td>" + i + "</td>\n");
	out.write("<td>" + i + "</td>\n");
	out.write("<td>" + i + "</td>\n");
	out.write("<td>" + i + "</td>\n");
	out.write("<td>" + i + "</td>\n");
	out.write("<td>" + i + "</td>\n");
	out.write("<td>" + i + "</td>\n");
	out.write("<td>" + i + "</td>\n");
	out.write("</tr>\n");
	out.write("<tr>\n");
	out.write("<td>項番</td>\n");
	out.write("<td>項番</td>\n");
	out.write("<td>項番</td>\n");
	out.write("<td>項番</td>\n");
	out.write("<td>項番</td>\n");
	out.write("<td>項番</td>\n");
	out.write("<td>項番</td>\n");
	out.write("<td>項番</td>\n");
	out.write("<td>項番</td>\n");
	out.write("<td>項番</td>\n");
	out.write("</tr>\n");
	out.write("<tr>\n");
	out.write("<td>" + 1 + "</td>\n");
	out.write("<td>" + 2 + "</td>\n");
	out.write("<td>" + 3 + "</td>\n");
	out.write("<td>" + 4 + "</td>\n");
	out.write("<td>" + 5 + "</td>\n");
	out.write("<td>" + 6 + "</td>\n");
	out.write("<td>" + 7 + "</td>\n");
	out.write("<td>" + 8 + "</td>\n");
	out.write("<td>" + 9 + "</td>\n");
	out.write("<td>" + 10 + "</td>\n");
	out.write("</tr>\n");
	out.write("</table>\n");
	out.write("</div>\n");
}
%>


<div id="page">
上記の内容を印刷します。
<form name="form" method="post" action="">
	<p><input type="submit" name="Confirm" value="確定" style="width:100px;" onClick="Print_open()"></p>
</form>
</div>
</body>
</html>