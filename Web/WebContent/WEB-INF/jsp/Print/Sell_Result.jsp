<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Seller,model.EntrySheet,model.Item,java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>個人精算表印刷</title>
<!-- スタイルシートの読み込み -->
<link rel="stylesheet" type="text/css" href="./css/common.css">
<link href="./css/Popup.css" rel="stylesheet" type="text/css">
<link href="./css/Print.css" rel="stylesheet" type="text/css">
<link href="./css/Print_Sell_Result.css" rel="stylesheet" type="text/css">
<!-- JavaScriptの読み込み -->
<script src="./js/jquery-3.1.1.min.js"></script>
<script src="./js/Popup.js"></script>
<script src="./js/Print.js"></script>
</head>
<body>

<%
ArrayList<Seller>SellerList = (ArrayList<Seller>)request.getAttribute("SellerList");
ArrayList<ArrayList<Item>>ItemList = (ArrayList<ArrayList<Item>>)request.getAttribute("ItemList");
ArrayList<Integer>SumList = (ArrayList<Integer>)request.getAttribute("SumList");
int num=0;
int j=0;
int count=0;
int total=0;
Fast:
for (int i=0; i<SellerList.size(); i++) {%>
	<section style=page-break-before:always>
	<h2 class="posCenter" style="width: 100rem;">個人精算表</h2>
	<table class="display_v top posCenter">
	<tr>
	<th>出品者番号</th>
	<th>名前</th>
	</tr>
	<tr>
	<td><%=SellerList.get(i).getSID() %></td>
	<td><%=SellerList.get(i).getSellerName() %></td>
	</tr>
	</table>
	<table class="display_v middle posCenter">
	<tr>
	<th>出品表番号</th>
	<th>項番</th>
	<th>商品名</th>
	<th>実売価格</th>
	<th>在庫有無</th>
	<th>返却有無</th>
	</tr>
	<%ArrayList<Item> item = ItemList.get(i);
	while(j<item.size()){
	String quantity = "無";
	String retFlg = "無";
	if(item.get(j).getQuantity() > 0){
		quantity = "有";
	}else{
		total = total + item.get(j).getPrice();
	}
	if(true == item.get(j).getRetFlg()) {
		retFlg = "有";
	}%>
		<tr>
		<td><%=item.get(j).getESID() %></td>
		<td><%=item.get(j).getINO() %></td>
		<td><%=item.get(j).getName() %></td>
		<td><%=item.get(j).getPrice() %>円</td>
		<td><%=quantity %></td>
		<td><%=retFlg %></td>
		</tr>
		<%
		j++;
		count++;
		if(count>29){
			i--;
			count=0;%>
			</table>
			</section>
			<%continue Fast;
		}%>
	<%}
	j=0;
	count=0;%>
	<tr>
		<td colspan=3>合計</td>
		<td colspan=3><%=total/2 %>円</td>
	</tr>
	</table>
	<%total=0; %>
	</section>
<%}%>

<div id="page" class="posLeft" style="width: 100rem;">
上記の内容を印刷します。
<form name="form" method="post" action="./Sell_Result">
	<p><input type="submit" name="Confirm" value="確定" style="width:100px;" onClick="Print_open()"></p>
</form>
</div>

</body>
</html>