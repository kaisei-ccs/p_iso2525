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
<script type="text/javascript" src="/Web/js/Register.js"></script>
<link rel="stylesheet" type="text/css" href="./css/common.css">
<link rel="stylesheet" type="text/css" href="./css/Register.css">
</head>
<body>
	<%-- タイトルバーを出力 --%>
	<jsp:include page="Title_Bar.jsp">
	    <jsp:param name="title" value="Register" />
	</jsp:include>
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
			<%
				for(int i = 0;i<scan.size();i++){
					scanEsId = scan.get(i).getESID();
					scanIno = scan.get(i).getINO();
					item = Item.findByBarcodeData(scanEsId, scanIno);
					itemName = item.get(0).getName();
					itemPrice = item.get(0).getPrice();
					totalPrice += item.get(0).getPrice();
			%>
			<tr><td><%=scanEsId %></td><td><%=scanIno %></td><td> <%=itemName %></td><td><%=itemPrice %></td></tr>
				<%
				}%>
			<tr><td><input type="text" name="CashBack" id="CashBack" style = "height:100%"></td><td></td><td></td><td></td></tr>


		</TABLE>
	</div>

	<div class="right">
	<form method="POST" action="/Web/Register">
		<dl class="total">

			<dt>合計金額</dt>
			<dd><input type="text"value=<%=totalPrice %> name="TotalPrice"></dd>
			<dt>預り金</dt>
			<dd><input type="text" name="Charge" id ="Charge" value = <%=request.getAttribute("Charge") %> ></dd>
			<dt></dt>
			<dd><input type="submit" value="確定"></dd>
			<dt>おつり</dt>
			<dd><input type="text" name="CashBack" id="CashBack" value=<%=request.getAttribute("CashBack") %>></dd>
		</dl>
		</form>
		<input type="button" name="Stop" value = "会計中止" onClick = "fauction(1)">
		<input type="button" name="Return" value="返品" onClick = "fauction(2)">

	</div>
	</section>
<%--日　時　合計金額　預り金額　おつり 
	ES_ID I_NO 単価　数量
 --%>
</body>
</html>