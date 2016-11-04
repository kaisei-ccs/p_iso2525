<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.SalesLog,java.util.ArrayList" %>
<%
ArrayList<SalesLog> SLogList = (ArrayList<SalesLog>) request.getAttribute("saleslog");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会計履歴画面</title>
<!-- スタイルシート読み込み -->
<link rel="stylesheet" type="text/css" href="./css/common.css">
<link rel="stylesheet" type="text/css" href="./css/Popup.css">
<link rel="stylesheet" type="text/css" href="./css/Sales_Log.css">

<!-- javascript読み込み -->
<script type="text/javascript" src="./js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="./js/jquery.tablesorter.js"></script>
<script type="text/javascript" src="./js/Popup.js"></script>
<script type="text/javascript" src="./js/Sales_Log.js"></script>
</head>
<body>
	<%-- タイトルバーを出力 --%>
	<jsp:include page="Title_Bar.jsp">
   	<jsp:param name="caller" value="Sales_Log" />
	</jsp:include>
	<article  class="posCenter">
	<section class="search">
	<form name="search" action="./Sales_Log" method="post">
		<table class="display_h">
			<tr>
				<th colspan="4">検索</th>
			</tr>
			<tr>
				<td>会計番号</td>
				<td><input type="text" name="id" value="${param.id}"></td>
			</tr>
			<tr>
				<td>日付</td>
				<td><input type="text" name="date" value="${param.date}"></td>
			</tr>
			<tr>
				<td>時間</td>
				<td><input type="text" name="time1" value="${param.time1}">
				<td>～</td>
				<td><input type="text" name="time2" value="${param.time2}"></td>
			</tr>
			<tr>
				<td>合計金額</td>
				<td><input type="text" name="total1" value="${param.total1}"></td>
				<td>≦</td>
				<td><input type="text" name="total2" value="${param.total2}"></td>
			</tr>
			<tr>
				<td>預り金額</td>
				<td><input type="text" name="charge1" value="${param.charge1}"></td>
				<td>≦</td>
				<td><input type="text" name="charge2" value="${param.charge2}"></td>
			</tr>
			<tr>
				<td>おつり</td>
				<td><input type="text" name="cashback1" value="${param.cashback1}"></td>
				<td>≦</td>
				<td><input type="text" name="cashback2" value="${param.cashback2}"></td>
			</tr>
			<tr>
				<td>出品表番号</td>
				<td><input type="text" name="es_id" value="${param.es_id}"></td>
			</tr>
			<tr>
				<td>出品表項番</td>
				<td><input type="text" name="i_no" value="${param.i_no}"></td>
			</tr>
			<tr>
				<td><input type="submit" value="検索"></td>
				<td><input type="button" onclick="Initialization()" value="クリア"></td>
			</tr>
		</table>

	</form>
	</section>

	<section class="stable">
		<table class="display_v scrollBody">
		<thead>
			<tr>
				<th>会計番号</th>
				<th>日付</th>
				<th>時間</th>
				<th>合計金額</th>
				<th>預り金額</th>
				<th>おつり</th>
			</tr>
		</thead>
		<tbody style="height: 500px;">
			<%for(SalesLog saleslog : SLogList){ %>
				<tr class="paddingTD posRight"">
					<td><%=saleslog.getTID() %></td>
					<td><%=saleslog.getTDate() %></td>
					<td><%=saleslog.getTTime() %></td>
					<td><%=saleslog.getTotal() %></td>
					<td><%=saleslog.getCharge() %></td>
					<td><%=saleslog.getCashBack() %></td>
				</tr>
			<%} %>
		</tbody>
		</table>
	</section>
	<div class="tclear"></div>
	</article>
	<div id="popup-content">
		<button id="popup-close">baka</button>
	</div>
</body>
</html>