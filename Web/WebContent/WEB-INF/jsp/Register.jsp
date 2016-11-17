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
	<%-- タイトルバーを出力 --%>
	<jsp:include page="Title_Bar.jsp">
	    <jsp:param name="caller" value="Register_0" />
	</jsp:include>
	<%int scanIno;
	  int scanEsId;
	  String itemName;
	  int itemPrice;
	  int totalPrice = 0;
	  int charge = 0;
	  int cashBack = 0;
	  int getItem = 0;
	  int cells;
	  boolean frag = true;
	  ArrayList<Item> item;%>
	<% ArrayList<Scan> scan = (ArrayList<Scan>) request.getAttribute("scan");%>
<%if(true == frag){%>
<script>registerSub();</script>
<%frag = false; }%>
	<article>
		<section>
			<div class="left">
				<TABLE class="display_v scrollBody" id ="table_Item">
				<thead>
					<tr>
						<th>出品表番号</th>
						<th>項番</th>
						<th>商品名</th>
						<th>単価</th>
					</tr>
				</thead>
				<tbody style="height: 500px;">
					<%
						for(int i = 0;i<scan.size();i++){
							scanEsId = scan.get(i).getESID();
							scanIno = scan.get(i).getINO();
							item = Item.findByBarcodeData(scanEsId, scanIno);
							itemName = item.get(0).getName();
							itemPrice = item.get(0).getPrice();
							totalPrice += item.get(0).getPrice();
					%>
					<tr>
						<td><%=scanEsId %></td>
						<td><%=scanIno %></td>
						<td> <%=itemName %></td>
						<td><%=itemPrice %></td>
					</tr>
						<%
						}%>
					<tr>
						<td>
							<input type="text" id="cell1" onblur="insertCheck()" value="" autofocus />
						</td>
						<td>
							<input type="text" id="cell2" onblur="insertCheck()" value="" />
						</td>
						<td></td>
						<td></td>
					</tr>
					<tr class="non-line">
				<!--  	<td colspan="4"><input type="submit" name="regiStop" value="会計中止"></td>-->
					</tr>
				</tbody>
				</TABLE>
			</div>
			<div class="right">
				<form method="POST" action="/Web/Register">
					<table class="display_h">
						<tr><!--  checked ="checked"-->
							<td><input type="radio" name="Return" checked ="checked" value="1">レジ</td>
							<td><input type="radio" name="Return" value="-1">返品</td>
						</tr>

						<tr>
							<td>合計金額</td>
							<td><input type="text" readonly id="TotalPrice" name="TotalPrice" value="<%=totalPrice %>"></td>
						</tr>
						<tr>
							<td>預り金</td>
							<td><input type="text" name="Charge" id="Charge"  value = <%=request.getAttribute("Charge") %> ></td>
						</tr>
						<tr>
							<td><input type="submit" name = "Confirm" value="確定"></td>
							<%--<td><input type="submit" name = "Confirm" value="確定"></td> --%>
						</tr>
						<tr>
							<td>おつり</td>
							<td><input type="text" name="CashBack" readonly id="CashBack" value=<%=request.getAttribute("CashBack") %>></td>
						</tr>
						<tr>
							<td><input type="submit" name="regiStop" value="会計中止"></td>
							<%-- <td><input type="submit" name="regiStop" value="会計中止"></td> --%>
							<td>
						</tr>
					</table>
				</form>
				<script>
				$(function(){
					$("input").on("keydown", function(e) {
						if ((e.which && e.which === 13) || (e.keyCode && e.keyCode === 13)) {
							return false;
							} else {
								return true;
								}
						});
					});
				</script>
			</div>
		</section>
	</article>
</body>
</html>