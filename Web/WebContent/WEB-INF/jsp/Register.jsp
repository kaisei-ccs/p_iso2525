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
<link rel="stylesheet" type="text/css" href="./css/common.css">
<link rel="stylesheet" type="text/css" href="./css/Register.css">
</head>
<body>
	<%-- タイトルバーを出力 --%>
	<jsp:include page="Title_Bar.jsp">
	    <jsp:param name="caller" value="Register" />
	</jsp:include>
<%int scanIno;
  int scanEsId;
  String itemName;
  int itemPrice;
  int totalPrice = 0;
  int charge = 0;
  int cashBack = 0;
  ArrayList<Item> item;%>
<% ArrayList<Scan> scan = (ArrayList<Scan>) request.getAttribute("scan");%>



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
			<dd><input type="text" name="Charge"  value = <%=request.getAttribute("Charge") %> ></dd>
			<dt></dt>
			<dd><input type="submit" name = "Confirm" value="確定"></dd>
			<dt>おつり</dt>
			<dd><input type="text" name="CashBack" id="CashBack" value=<%=request.getAttribute("CashBack") %>></dd>
		</dl>

		<input type="submit" name="regiStop" value="会計中止">
		<input type="submit" name="Return" value="返品">
	</form>

	</div>
	</section>

</body>
</html>