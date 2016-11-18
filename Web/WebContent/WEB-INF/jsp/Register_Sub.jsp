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
	<script type="text/javascript" src= "./js/Register_Sub.js"></script>
	<script type="text/javascript" src="./js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src= "./js/Register.js"></script>

	<!-- 戻るボタンで前のページに戻らないようにする -->
	<script type="text/javascript">
		window.onunload = function(){};
		history.forward();
	</script>

	<link rel="stylesheet" type="text/css" href="./css/common.css">
	<link rel="stylesheet" type="text/css" href="./css/Register.css">
</head>
<body>

	<%int scanIno;
	  int scanEsId;
	  String itemName;
	  int itemPrice;
	  int totalPrice = 0;
	  int charge = 0;
	  int cashBack = 0;
	  int getItem = 0;
	  int cells;
	  ArrayList<Item> item;%>

	<article>
		<section>
			<div class="left">
				<TABLE class="display_v" id ="table_Item">
					<tr>
						<th>出品表番号</th>
						<th>項番</th>
						<th>商品名</th>
						<th>単価</th>
					</tr>
				</TABLE>
			</div>
			<div class="right">
				<form method="POST" action="/Web/Register">
					<table class="display_h">
						<tr>
							<td>合計金額</td>
							<td><input type="text" readonly name="TotalPrice" id="TotalPrice" value="0"></td>
						</tr>
						<tr>
							<td>預り金</td>
							<td><input type="text" readonly name="Charge" id="Charge"  value = "0" ></td>
						</tr>

						<tr>
							<td>おつり</td>
							<td><input type="text" name="CashBack" readonly id="CashBack" value="0"></td>
						</tr>

					</table>
				</form>
			</div>
		</section>
	</article>
</body>
</html>