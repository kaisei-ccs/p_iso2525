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
<link rel="stylesheet" type="text/css" href="./css/Popup.css">
<link rel="stylesheet" type="text/css" href="./css/Print.css">
<link rel="stylesheet" type="text/css" href="./css/Print_EntrySheet.css">
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
	out.write("<section class=\"pageBreak\">\n");
	out.write("<h2 class=\"posCenter\" style=\"width: 100rem;\">出品表</h2>\n");

	out.write("<table class=\"display_v top posCenter\" >\n");
	out.write("<tr>\n");
	out.write("<th>出品表番号</th>\n");
	out.write("<th>学籍番号</th>\n");
	out.write("<th>名前</th>\n");
	out.write("</tr>\n");
	out.write("<tr>\n");
	out.write("<td class=\"posCenter\">" + i + "</td>");
	out.write("<td>&nbsp;</td>");
	out.write("<td>&nbsp;</td>\n");
	out.write("</tr>\n");
	out.write("</table>\n");

	out.write("<div class=\"posLeft\" style=\"width: 100rem;\">例</div>");
	out.write("<table class=\"display_v middle posCenter\">\n");
	out.write("<tr>\n");
	out.write("<th>項番</th>\n");
	out.write("<th>商品名</th>\n");
	out.write("<th>開始価格</th>\n");
	out.write("<th>終了価格</th>\n");
	out.write("<th>個数</th>\n");
	out.write("<th>返却有無</th>\n");
	out.write("</tr>\n");
	out.write("<tr class=\"posCenter\">\n");
	out.write("<td>" + 1 + "</td>\n");
	out.write("<td>タオル</td>\n");
	out.write("<td>１００円</td>\n");
	out.write("<td>５０円</td>\n");
	out.write("<td>３個</td>\n");
	out.write("<td>×</td>\n");
	out.write("</tr>\n");
	out.write("</table>\n");

	out.write("<table class=\"display_v middle posCenter\">\n");
	out.write("<tr>\n");
	out.write("<th>項番</th>\n");
	out.write("<th>商品名</th>\n");
	out.write("<th>開始価格</th>\n");
	out.write("<th>終了価格</th>\n");
	out.write("<th>個数</th>\n");
	out.write("<th>返却有無</th>\n");
	out.write("</tr>\n");
	for (int j = 1; j <= 10; j++) {
		out.write("<tr>\n");
		out.write("<td class=\"posCenter\">" + j + "</td>\n");
		out.write("<td>&nbsp;</td>\n");
		out.write("<td>&nbsp;</td>\n");
		out.write("<td>&nbsp;</td>\n");
		out.write("<td>&nbsp;</td>\n");
		out.write("<td>&nbsp;</td>\n");
		out.write("</tr>\n");
	}
	out.write("</table>\n");


	out.write("<table class=\"display_v bottom posCenter\">\n");
	out.write("<tr>\n");
	for (int k=0; k < 10; k++) {
		out.write("<th>出品表番号</th>\n");
	}
	out.write("</tr>\n");
	out.write("<tr>\n");
	for (int k=0; k < 10; k++) {
		out.write("<td class=\"posCenter\">" + i + "</td>\n");
	}
	out.write("</tr>\n");
	out.write("<tr>\n");
	for (int k=0; k < 10; k++) {
		out.write("<th>項　番</th>\n");
	}
	out.write("</tr>\n");
	out.write("<tr>\n");
	for (int k=0; k < 10; k++) {
		out.write("<td class=\"posCenter\">" + (k + 1) + "</td>\n");
	}
	out.write("</tr>\n");
	out.write("</table>\n");
	out.write("</div>\n");
	out.write("</section>");
}
%>


<div id="page" class="posLeft" style="width: 100rem;">
上記の内容を印刷します。
<form name="form" method="post" action="">
	<p><input type="submit" name="Confirm" value="確定" style="width:100px;" onClick="Print_open()"></p>
</form>
</div>

</body>
</html>