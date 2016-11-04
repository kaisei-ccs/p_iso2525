<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Seller,java.util.ArrayList" %>
<%
ArrayList<Seller> SellerList = (ArrayList<Seller>) request.getAttribute("sellerlist");
ArrayList<Integer> SellResult = (ArrayList<Integer>) request.getAttribute("sellresult");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>精算画面</title>
<!-- スタイルシート読み込み -->
<link rel="stylesheet" type="text/css" href="./css/common.css">
</head>
<body>
	<input type="button" value="全選択">
	<input type="button" value="全解除">
	<table>
		<tr>
			<th></th>
			<th>出品者番号</th>
			<th>名前</th>
			<th>売上</th>
			<th>還元分</th>
		</tr>
		<%for(int i=0; i<SellerList.size(); i++){ %>
			<tr>
				<td><input type="checkbox"></td>
				<td><%=SellerList.get(i).getSID() %></td>
				<td><%=SellerList.get(i).getSellerName() %></td>
				<td><%=SellResult.get(i) %></td>
				<td><%=SellResult.get(i)/2 %></td>
			</tr>
		<%} %>
		<tr>
			<td colspan="3">合計</td>
			<td><%=SellResult.get(SellResult.size()-1) %></td>
			<td><%=SellResult.get(SellResult.size()-1)/2 %></td>
		</tr>
	</table>
</body>
</html>