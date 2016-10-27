<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Scan" %>
<%@ page import="model.Item" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>レジ</title>
<script type="text/javascript" src="/Web/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="/Web/js/Title_Bar.js"></script>
<link rel="stylesheet" type="text/css" href="/Web/css/Title_Bar.css">
<link rel="stylesheet" type="text/css" href="./css/common.css">
<link rel="stylesheet" type="text/css" href="./css/Register.css">
</head>
<body>
	<header id="title">
		<script>
			button_name("メニュー");
			title_set("レジ");
			load();
		</script>
	</header>
<%int scanIno;
  int scanEsId;
  String itemName;
  int itemPrice;
  int totalPrice = 0;
  int charge = 0;
  int cashBack = 0;
  ArrayList<Item> item;%>
<% ArrayList<Scan> scan = Scan.fetchAll();%>

<%scanEsId = scan.get(0).getESID();
  scanIno = scan.get(0).getINO();
%>


	<section>

	<div class="left">
		<TABLE>
			<tr>
				<th>出品表番号</th>
				<th>項番</th>
				<th>商品名</th>
				<th>単価</th>
			</tr>
			<%for(int i = 0;i<scan.size();i++){
				scanEsId = scan.get(i).getESID();
				scanIno = scan.get(i).getINO();
				item = Item.findByBarcodeData(scanEsId, scanIno);
				itemName = item.get(0).getName();
				itemPrice = item.get(0).getPrice();
				totalPrice += item.get(0).getPrice();
			%>
			<tr><td><%=scanEsId %></td><td><%=scanIno %></td><td> <%=itemName %></td><td><%=itemPrice %></td></tr>
			<%} %>

		</TABLE>
	</div>

	<div class="right">
		<dl class="total">
			<dt>合計金額</dt>
			<dd><input type="text"value=<%=totalPrice %> name="TotalPrice"></dd>
			<dt>預り金</dt>
			<dd><input type="text" name="Charge"></dd>
			<dt></dt>
			<dd><input type="button" name="Confirm" value="確定" onClick="/Web/Register"></dd>
			<dt>おつり</dt>
			<dd><input type="text" name="CashBack" value=<%=cashBack %>></dd>
		</dl>
		<input type="button" name="Stop" value = "会計中止" onClick = "fauction(1)">
		<input type="button" name="Return" value="返品" onClick = "fauction(2)">
	</div>charge = ;
	<% cashBack = charge - totalPrice;%>
	<%=totalPrice %>
	<%=cashBack %>
	</section>

<%!
 public int cashBack(int charge,int totalPrice){
	int cashBack;
	cashBack = charge - totalPrice;
	return cashBack;
}
%>

</body>
</html>