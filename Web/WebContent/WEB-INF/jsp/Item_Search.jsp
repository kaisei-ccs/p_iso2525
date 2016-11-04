<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Item,model.Seller,java.util.ArrayList" %>
<%
ArrayList<Item> ItemList = (ArrayList<Item>) request.getAttribute("Itemlist");
ArrayList<Seller> SellerList = (ArrayList<Seller>) request.getAttribute("Sellerlist");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出品者登録画面</title>
<link rel="stylesheet" type="text/css" href="./css/common.css">
<link rel="stylesheet" type="text/css" href="./css/Item_Search.css">
</head>
<body>
	<%-- タイトルバーを出力 --%>
	<jsp:include page="Title_Bar.jsp">
   	<jsp:param name="caller" value="Item_Search" />
	</jsp:include>
	<article>
	<form class="search" action="./Item_Search" method="post">
		<h1>検索</h1>
		<table border="1">
			<tr>
				<td width="150px">出品表番号</td>
				<td><input type="text" name="es_id"></td>
			</tr>
			<tr>
				<td width="150px">出品表項番</td>
				<td><input type="text" name="i_no"></td>
			</tr>
			<tr>
				<td width="150px">出品者番号</td>
				<td><input type="text" name="s_id"></td>
			</tr>
			<tr>
				<td width="150px">出品者名</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td width="150px">カナ</td>
				<td><input type="text" name="kana"></td>
			</tr>
			<tr>
				<td width="150px">在庫の有無</td>
				<td><select name="quantity">
					<option value=""> </option>
					<option value="1">有</option>
					<option value="0">無</option>
				</select></td>
			</tr>
			<tr>
				<td width="150px">返却の有無</td>
				<td><select name="retflg">
					<option value=""> </option>
					<option value="true">有</option>
					<option value="false">無</option>
				</select></td>
			</tr>
		</table>
		<input type="submit" value="検索">
		<input type="reset" value="クリア">
	</form>
	<div class="IStable">
	<h2 class="posRight">検索のヒット数：<%=ItemList.size() %>件</h2>
		<table class="itemtable display_v">
			<tr>
				<th>出品表番号</th>
				<th>出品表項番</th>
				<th>出品者番号</th>
				<th>出品者名</th>
				<th>カナ</th>
				<th>在庫の有無</th>
				<th>返却の有無</th>
			</tr>
			<%for(int i=0; i<ItemList.size(); i++){ %>
				<tr class="posRight">
					<td><%=ItemList.get(i).getESID() %></td>
					<td><%=ItemList.get(i).getINO() %></td>
					<td><%=SellerList.get(i).getSID() %></td>
					<td><%=SellerList.get(i).getSellerName() %></td>
					<td><%=SellerList.get(i).getSellerKana() %></td>
					<%if(ItemList.get(i).getQuantity() > 0){ %>
						<td><%="有" %></td>
					<%}else{ %>
						<td><%="無" %></td>
					<%} %>

					<%if(true == ItemList.get(i).getRetFlg()){ %>
						<td><%="有" %></td>
					<%}else{ %>
						<td><%="無" %></td>
					<%} %>
				</tr>
			<%} %>
		</table>
	</div>
	<div class="tclear"></div>
	</article>
</body>
</html>