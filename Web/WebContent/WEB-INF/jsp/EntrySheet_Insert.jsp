<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import ="java.util.ArrayList" %>
    <%@page import ="model.Seller" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="./css/common.css">
<link rel="stylesheet" type="text/css" href="./css/Manager_Menu.css">


<!--  -->
<script>
function check_id(e_id){
	//出品表IDをテーブルに送る為の処理
	document.getElementById("ES_ID1").value = document.getElementById(e_id).value;
	document.getElementById("ES_ID2").value = document.getElementById(e_id).value;
	document.getElementById("ES_ID3").value = document.getElementById(e_id).value;
	document.getElementById("ES_ID4").value = document.getElementById(e_id).value;
	document.getElementById("ES_ID5").value = document.getElementById(e_id).value;
	document.getElementById("ES_ID6").value = document.getElementById(e_id).value;
	document.getElementById("ES_ID7").value = document.getElementById(e_id).value;
	document.getElementById("ES_ID8").value = document.getElementById(e_id).value;
	document.getElementById("ES_ID9").value = document.getElementById(e_id).value;
	document.getElementById("ES_ID10").value =document.getElementById(e_id).value;
	//出品表のID
	document.getElementById("entry_sheet_ID").value = document.getElementById(e_id).value;

}

function seller_Change(){
	//現在のドロップダウンリストの値を取ってくる
	var wk_id_name = document.getElementById("name_id").selectedIndex ;
	var seller_id_name = document.getElementById("name_id").options[wk_id_name].value ;
	//Exhibitor_ID  出品者ID
	document.getElementById("exhibitor_ID").value =seller_id_name;
	//出品表のID
	//document.getElementById("entry_sheet_ID").value = document.getElementById(e_id).value;

}


</script>

</head>
<body>
<%-- タイトルバーを出力 --%>
<jsp:include page="Title_Bar.jsp">
    <jsp:param name="caller" value="EntrySheet_Insert" />
</jsp:include>

<%-- 出品者を出力 --%>

<%
	if(request.getAttribute("ErrorMessage") != null){
		out.println("<p style=\"background-color: #ff0000;\">" + request.getAttribute("ErrorMessage") + "</p>");
	}
%>

<div>
	<form method = "POST" action = "/Web/EntrySheet_Insert">
		<label>出品表番号:</label>
		<input type="text"id = "e_id" name="e_id" size="3"value="0"maxlength="3" onInput="check_id('e_id')">
		<label>出品者番号,出品者名</label>
		<select id = "name_id" name="example"  onChange="seller_Change()">
			<%
			//ドロップダウンリストに出品者IDと出品者名を送る
			ArrayList<Seller> seller = (ArrayList<Seller>)request.getAttribute("seller_db");
			int i = 0;
				for(i = 0;i < seller.size(); i++){
					out.println("<option value=\""+ seller.get(i).getSID()+"\"> ");
					out.println(""+seller.get(i).getSID() +","+seller.get(i).getSellerName());
					out.println("</option>");
				}
			%>
		</select>
		<input type="button"  size="3" value = "出品者セット"onclick="check_id('e_id')"><!--submit  -->
		<a href="./Seller_Insert">出品者登録へ</a>
	</form>
</div>
	<div id="output"></div>
<p>


<%-- 出品表登録画面を出力 --%>
<form method = "POST" action = "/Web/EntrySheet_Insert">
<%--出品者IDを送る --%>
<input type="hidden"id = "exhibitor_ID" name="exhibitor_ID"value="1">
<%--出品表IDを送る --%>
<input type="hidden"id = "entry_sheet_ID" name="entry_sheet_ID"value=""><%-- ※エラー""が送信されている --%>
<table border="1" cellspacing="0" cellpadding="4" bgcolor="#A9A9A9" id="table1">
<tr>
	<th>表番</th>
	<th>項番</th>
	<th>商品名</th>
	<th>開始価格</th>
	<th>値札価格</th>
	<th>返却有</th>

	<%
		int J=1;
		for(J=1;J<11;J++){
			out.println("<tr>");
			//出品表ID　ES_ID
			out.println("<td class=\"ES_ID\"><input type=\"text\"id = \"ES_ID"+J+"\" name=\"ES_ID"+J+"\" value=\" \" size=\"2\" maxlength=\"3\"disabled></td>");
			//出品表項番　I_NO
			out.println("<td class=\"I_NO\"><input type=\"text\"id = \"I_NO"+J+"\" name=\"I_NO"+J+"\" value=\""+ J + "\" size=\"2\" maxlength=\"2\"disabled></td>");
			//商品名　ITEM_NAME
			out.println("<td class=\"kana\"><input type=\"text\"id = \"ITEM_NAME"+J+"\" name=\"ITEM_NAME"+J+"\" value=\"\" size=\"35\" maxlength=\"30\"></td>");
			//開始金額　START_PRICE
			out.println("<td class=\"START_PRICE\"><input type=\"text\"id = \"START_PRICE"+J+"\" name=\"START_PRICE"+J+"\" value=\"\" size=\"6\" maxlength=\"5\"></td>");
			//値下げ価格　SALE_PRICE
			out.println("<td class=\"SALE_PRICE\"><input type=\"text\"id = \"SALE_PRICE"+J+"\" name=\"SALE_PRICE"+J+"\" value=\"\" size=\"6\" maxlength=\"5\"></td>");
			//返却の有無　RETFLG
			out.println("<td class=\"RETFLG\"align=\"center\"><input type=\"checkbox\"id = \"RETFLG"+J+"\"  name=\"RETFLG"+J+"\"size=\"3\"></td>");
			out.println("</tr>");
		}
	%>
</table>
<%-- <input type="submit" value="登録">--%>
<button type="button"accesskey="b" onclick="submit();">(Alt+B)登録</button>
</form>



</body>
</html>