<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Item" %>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>値札用紙印刷</title>
<style type="text/css">
.table1 {
  border-collapse: collapse;
  width: 300px;
}
.table1 th {

}
h2{
	text-align:center;
}
</style>
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
int count = 0;
ArrayList<Item> priceList = (ArrayList<Item>)request.getAttribute("priceList");
//for(int i = 0; i > (priceList.size() / 10); i++){
for(Item item : priceList){
	if(0 == (count % 6)){
		out.write("</div>\n");
		out.write("<div style=\"page-break-before:always;\">\n");
	}
		out.write("<table class=\"table1\" border=1 style=\"float:left; margin-right:20px;\">\n");
		out.write("<tr><td colspan=3><center>商品名</center></td></tr>\n");
		out.write("<tr><td colspan=3 height=40px>" + item.getName() + "</td></tr>\n");
		out.write("<tr><td rowspan=3>　　　</td><td colspan=2><center>価格</center></td></tr>\n");
		out.write("<tr><td>" + item.getSPrice() + "</td></tr>\n");
		out.write("<tr><td>　</td></tr>\n");
		out.write("<tr><td><center>" + item.getESID() + "‐" + item.getINO() + "</center></td><td>　　　</td></tr>\n");
		out.write("<tr><td colspan=3><center>商品名</center></td></tr>\n");
		out.write("<tr><td colspan=3 height=40px>" + item.getName() + "</td></tr>\n");
		out.write("<tr><td><center>" + item.getESID() + "‐" + item.getINO() + "</center></td><td>　　　</td></tr>\n");
		out.write("</table>\n");
	count = count + 1;
}
out.write("</div>\n");
%>

<div id="page">
上記の内容を印刷します。
<form name="form" method="post" action="">
	<p><input type="submit" name="Confirm" value="確定" style="width:100px;" onClick="Print_open()"></p>
</form>
</div>
</body>

</html>
