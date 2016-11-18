<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Seller,model.EntrySheet,model.Item,java.util.ArrayList" %>
<%!
	public String makeSeparatData(String argVal) {
		return makeSeparatData(Integer.parseInt(argVal));
	}
	public String makeSeparatData(int argVal) {
		return String.format("%1$,3d",argVal);
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>個人精算表印刷</title>
<!-- スタイルシートの読み込み -->
<link rel="stylesheet" type="text/css" href="./css/common.css">
<link rel="stylesheet" type="text/css" href="./css/Print.css" >
<link rel="stylesheet" type="text/css" href="./css/Print_Sell_Result.css">
<!-- JavaScriptの読み込み -->
<script src="./js/jquery-3.1.1.min.js"></script>
<script src="./js/Print.js"></script>
</head>
<body>

<%
ArrayList<Seller>SellerList = (ArrayList<Seller>)request.getAttribute("SellerList");
ArrayList<ArrayList<String>> SellerData = (ArrayList<ArrayList<String>>)request.getAttribute("SellerData");
ArrayList<ArrayList<Item>>itemData = (ArrayList<ArrayList<Item>>)request.getAttribute("ItemList");

int rowNo=0;


for (int i=0; i < SellerList.size(); i++) {
	// タイトルの作成
	out.write("<section class=\"posCenter\">\n");

	out.write("<table class=\"print_v\">\n");
	out.write("<thead>\n");
	out.write("<tr class=\"title\">" +
			  "<td colspan=\"7\" class=\"posCenter\">精算表</td>" +
			  "</tr>" +
			"<tr class=\"header\">\n" +
			"<td class=\"line\" colspan=\"2\">出品者番号</td>\n" +
			"<td colspan=\"2\">名　　前</td>\n" +
			"<td>販売商品数</td>" +
			"<td>売上額</td>" +
			"<td>還元額</td>" +
			"</tr>\n" +
			"<tr class=\"seller\">\n" +
			"<td colspan=\"2\">" + SellerList.get(i).getSID() + "</td>\n" +
			"<td colspan=\"2\">" + SellerList.get(i).getSellerName() + "</td>\n" +
			"<td>" + SellerData.get(i).get(3) + "品</td>\n" +
			"<td>" + makeSeparatData(SellerData.get(i).get(4)) + "円</td>\n" +
			"<td>" + makeSeparatData(SellerData.get(i).get(5)) + "円</td>\n" +
			"</tr>");

	out.write("<tr><td class=\"non-line\">&nbsp;</td></tr>");

	out.write("<tr>\n" +
			"<th>№</th>\n" +
			"<th>出品表番号</th>\n" +
			"<th>項番</th>\n" +
			"<th>商品名</th>\n" +
			"<th>最終価格</th>\n" +
			"<th>商品返却</th>\n" +
			"<th>販売価格</th>\n" +
			"</tr>\n");
	out.write("</thead>\n");

	out.write("<tbody>\n" );

	// 変数の初期化
	rowNo=0;

	// 商品の出力
	ArrayList<Item> itemList = itemData.get(i);
	for (Item item : itemList ) {
		// 明細の出力
		out.write("<tr class=\"paddingTD\">\n");
		out.write("<td class=\"posRight\">" + (rowNo + 1)  + "</td>\n");
		out.write("<td class=\"posRight\">" + item.getESID()  + "</td>\n");
		out.write("<td class=\"posRight\">" + item.getINO()   + "</td>\n");
		out.write("<td class=\"posLeft\">"  + item.getName()  + "</td>\n");
		out.write("<td class=\"posRight\">" + item.getPrice() + "円</td>\n");

		String quantity = "無";
		if(0 < item.getQuantity()){
			if(true == item.getRetFlg()) {
				out.write("<td class=\"posCenter\">返却商品</td>\n");
			} else {
				out.write("<td class=\"posCenter\">不要</td>\n");
			}
			out.write("<td>&nbsp;</td>\n");
		}else{
			out.write("<td>&nbsp;</td>\n");
			out.write("<td class=\"posRight\">" + item.getPrice()  + "円</td>\n");
		}
		out.write("</tr>");

		rowNo++;
	}

	// フッターの出力
	out.write("</tbody>\n");
	out.write("</table>\n");
	out.write("</section>\n");
}
%>

<div id="page" class="posLeft" style="width: 100rem;">
上記の内容を印刷します。
<form name="form" method="post" action="./Sell_Result">
	<p><input type="submit" name="Confirm" value="印刷" style="width:100px;" onClick="Print_open()"></p>
</form>
</div>

</body>
</html>