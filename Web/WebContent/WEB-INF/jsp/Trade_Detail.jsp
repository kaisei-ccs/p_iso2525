<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.SalesLog,model.TradeDetailItem,java.util.ArrayList" %>
<%
SalesLog sales = (SalesLog)request.getAttribute("sales");
ArrayList<TradeDetailItem> TDList = (ArrayList<TradeDetailItem>) request.getAttribute("TDList");
ArrayList<TradeDetailItem> CTDList = (ArrayList<TradeDetailItem>) request.getAttribute("CTDList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- スタイルシート読み込み -->
<link rel="stylesheet" type="text/css" href="./css/Trade_Detail.css">

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
		console.log("bbbbbbb");
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
		<tr>
			<th>会計項番</th>
			<th>出品者番号</th>
			<th>項番</th>
			<th>商品名</th>
			<th>単価</th>
			<th>数量</th>
		</tr>
		<%for(TradeDetailItem trade : TDList){ %>
			<tr class="trade posRight">
				<td><%=trade.getTNO() %></td>
				<td><%=trade.getESID() %></td>
				<td><%=trade.getINO() %></td>
				<td><%=trade.getName() %></td>
				<td><%=trade.getPrice() %></td>
				<td><%=trade.getQuantity() %></td>
			</tr>
			<%if(!CTDList.isEmpty() && trade.getESID() == CTDList.get(0).getESID() && trade.getINO() == CTDList.get(0).getINO()){ %>
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
						<%for(TradeDetailItem ctrade : CTDList){ %>
							<tr class="posRight">
								<td><%=ctrade.getTID() %></td>
								<td><%=ctrade.getTNO() %></td>
								<td><%=ctrade.getESID() %></td>
								<td><%=ctrade.getINO() %></td>
								<td><%=trade.getName() %></td>
								<td><%=ctrade.getPrice() %></td>
								<td><%=ctrade.getQuantity() %></td>
							</tr>
						<%} %>
					</table>
				</td>
			</tr>
			<%} %>
		<%} %>
	</table>
	</div>
	</section>
	<div class="posRight">
		<input type="button" class="print" value="印刷">
	</div>
	</article>
</body>
</html>