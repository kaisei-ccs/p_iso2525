<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.SalesLog,model.TradeDetail,model.Item,java.util.ArrayList" %>
<%
SalesLog sales = (SalesLog)request.getAttribute("sales");
ArrayList<TradeDetail> TDList = (ArrayList<TradeDetail>) request.getAttribute("TDList");
ArrayList<TradeDetail> CTDList = (ArrayList<TradeDetail>) request.getAttribute("CTDList");
ArrayList<Item> TDname = (ArrayList<Item>) request.getAttribute("TDname");
ArrayList<Item> CTDname = (ArrayList<Item>) request.getAttribute("CTDname");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- javascript読み込み -->
<script type="text/javascript">
var bool = true
$(".display_v .trade td:last-child").each(function () {
		//返却の列かどうかを判断
		if ($(this).text() < 0){
			//返却の列の背景色を変更
			$(this).parent().css("background-color","yellow");
	    }
	});
$(".display_v tr td table .posRight td:last-child").each(function () {
	//返却の列かどうかを判断
	if ($(this).text() < 0){
		//返却の列の背景色を変更
		$(this).parent().css("background-color","yellow");
    }
});
$(function () {
	$(".display_v .trade").dblclick( function(){
		var esID = $(this).children().eq(1).text();
		var iNo = $(this).children().eq(2).text();
		$("#popup-content").load("./Trade_Detail", { esID:esID,iNo:iNo});
	});
});
</script>
</head>
<body>
	<article>
	<div class="posRight">
		<input type="button" class="close" onclick=popup_close() value="✕">
	</div>
	<section>
	<table class="display_v">
		<tr>
			<th>会計番号</th>
			<th>日付</th>
			<th>時間</th>
			<th>合計金額</th>
		</tr>
		<tr class="posRight">
			<td><%=sales.getTID() %></td>
			<td><%=sales.getTDate() %></td>
			<td><%=sales.getTTime() %></td>
			<td><%=sales.getTotal() %></td>
		</tr>
	</table>
	</section>
	<section>
	<div  class="table">
	<table class="display_v TDtable">
	<thead>
		<tr>
			<th>会計項番</th>
			<th>出品表番号</th>
			<th>項番</th>
			<th>商品名</th>
			<th>単価</th>
			<th>数量</th>
		</tr>
	</thead>
	<tbody>
		<%for(int i=0; i<TDList.size(); i++){ %>
			<tr class="trade">
				<td class="posRight"><%=TDList.get(i).getTNO() %></td>
				<td class="posRight"><%=TDList.get(i).getESID() %></td>
				<td class="posRight"><%=TDList.get(i).getINO() %></td>
				<td class="posLeft"><%=TDname.get(i).getName() %></td>
				<td class="posRight"><%=TDList.get(i).getPrice() %></td>
				<td class="posRight"><%=TDList.get(i).getQuantity() %></td>
			</tr>
			<%if(!CTDList.isEmpty() && TDList.get(i).getESID() == CTDList.get(0).getESID() && TDList.get(i).getINO() == CTDList.get(0).getINO()){ %>
			<tr>
				<td colspan="6">
					<table class="CTDtable display_v">
						<tr>
							<th>会計番号</th>
							<th>会計項番</th>
							<th>出品者番号</th>
							<th>項番</th>
							<th>商品名</th>
							<th>単価</th>
							<th>数量</th>
						</tr>
						<%for(int j=0; j<CTDList.size(); j++){ %>
							<tr>
								<td class="posRight"><%=CTDList.get(j).getTID() %></td>
								<td class="posRight"><%=CTDList.get(j).getTNO() %></td>
								<td class="posRight"><%=CTDList.get(j).getESID() %></td>
								<td class="posRight"><%=CTDList.get(j).getINO() %></td>
								<td class="posLeft"><%=CTDname.get(i).getName() %></td>
								<td class="posRight"><%=CTDList.get(j).getPrice() %></td>
								<td class="posRight"><%=CTDList.get(j).getQuantity() %></td>
							</tr>
						<%} %>
					</table>
				</td>
			</tr>
			<%} %>
		<%} %>
	</tbody>
	</table>
	</div>
	</section>
	<div class="posRight">
		<input type="button" class="print" value="印刷">
	</div>
	</article>
</body>
</html>