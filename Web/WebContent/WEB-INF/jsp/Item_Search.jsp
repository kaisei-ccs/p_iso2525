<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Item,java.util.ArrayList" %>
<%
ArrayList<Item> ItemList = (ArrayList<Item>) request.getAttribute("Itemlist");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>在庫一覧画面</title>
<!-- スタイルシート読み込み -->
<link rel="stylesheet" type="text/css" href="./css/common.css">
<link rel="stylesheet" type="text/css" href="./css/Item_Search.css">

<!-- javascript読み込み -->
<script type="text/javascript">
	function MyClick(MyCommand){
		document.forms.search.elements.MySubmit.value=MyCommand;
		document.forms.search.submit();
	}
	function Initialization(){
		var form = document.getElementsByName("search");
		//var childs = form.childNodes;
		for(i=0; i<form[0].length-5; i++){
			form[0][i].value="";
		}
	}
</script>
</head>
<body>
	<%-- タイトルバーを出力 --%>
	<jsp:include page="Title_Bar.jsp">
   	<jsp:param name="caller" value="Item_Search" />
	</jsp:include>
	<article class="posCenter">
	<section class="search">
	<form name="search" action="./Item_Search" method="post">
		<table class="display_h">
			<tr>
				<th colspan="2">検索</th>
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
				<td>出品者番号</td>
				<td><input type="text" name="s_id" value="${param.s_id}"></td>
			</tr>
			<tr>
				<td>出品者名</td>
				<td><input type="text" name="name" value="${param.name}"></td>
			</tr>
			<tr>
				<td>カナ</td>
				<td><input type="text" name="kana" value="${param.kana}"></td>
			</tr>
			<tr>
				<td>在庫の有無</td>
				<td><select name="quantity">
					<option value=""> </option>
					<option value="1">有</option>
					<option value="0">無</option>
				</select></td>
			</tr>
			<tr>
				<td>返却の有無</td>
				<td><select name="retflg">
					<option value=""> </option>
					<option value="1">有</option>
					<option value="0">無</option>
				</select></td>
			</tr>
			<tr>
				<td><input type="button" onclick="MyClick('reference')" value="検索"></td>
				<td><input type="button" onclick="Initialization()" value="クリア"></td>
			</tr>
			<tr>
				<td><input type="button" onclick="MyClick('stock')" value="在庫確認"></td>
				<td><input type="button" onclick="MyClick('change')" value="価格変更"></td>
				<td><input type="hidden" name="MySubmit"></td>
			</tr>

		</table>

	</form>
	</section>

	<section class="IStable">
	<h3 class="posRight" style="width: 70rem;">検索のヒット数：<%=ItemList.size() %>件</h3>
		<table class="display_v scrollBody">
		<thead>
			<tr>
				<th>出品表番号</th>
				<th>出品表項番</th>
				<th>商品名</th>
				<th>現在価格</th>
				<th>在庫有無</th>
				<th>返却有無</th>
			</tr>
		</thead>
		<tbody style="height: 500px;">
<%
for(Item item : ItemList) {
	String quantity = "無";
	String retFlg = "無";
	if(item.getQuantity() > 0){
		quantity = "有";
	}
	if(true == item.getRetFlg()) {
		retFlg = "有";
	}

%>
				<tr class="paddingTD">
					<td class="posRight"><%=item.getESID() %></td>
					<td class="posRight"><%=item.getINO() %></td>
					<td class="posLeft"><%=item.getName() %></td>
					<td class="posRight"><%=item.getPrice() %>円</td>
					<td><%=quantity %></td>
					<td><%=retFlg %></td>
				</tr>
<%
}
%>
		</tbody>
		</table>
	</section>
	<div class="tclear"></div>
	</article>
</body>
</html>