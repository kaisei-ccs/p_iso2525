<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Item" %>
<%@ page import="model.TradeDetail" %>
<%@ page import="model.SalesLog" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>値札用紙印刷</title>
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

//ArrayList<Item> priceList = (ArrayList<Item>)request.getAttribute("priceList");

SalesLog salesLog = SalesLog.findByTID(1).get(0);
ArrayList<TradeDetail> DetailList = TradeDetail.findByTID(1);

out.write("<div style=\"page-break-before:always;\">\n");
out.write(salesLog.getTDate() + "/" + salesLog.getTTime() + "\n");
out.write(salesLog.getTID() + "\n");
for(TradeDetail detail : DetailList){
	detail.getTNO();
	ArrayList<Item> itemName = Item.findByBarcodeData(detail.getESID(), detail.getINO());
	detail.getPrice();
}
out.write(salesLog.getTotal() + "\n");
out.write(salesLog.getCharge() + "\n");
out.write(salesLog.getCashBack() + "\n");

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