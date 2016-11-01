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

}
</script>

</head>
<body>
<%-- タイトルバーを出力 --%>
<jsp:include page="Title_Bar.jsp">
    <jsp:param name="caller" value="EntrySheet_Insert" />
</jsp:include>

<%-- 出品者を出力 --%>
<p></p>
<div>
	<form method = "POST" action = "/Web/EntrySheet_Insert">
		<label>出品表番号:</label>
		<input type="text"id = "e_id" name="e_id" size="3"value="0"maxlength="3">
		<label>出品者番号:出品者名</label>
		<%
			ArrayList<Seller> seller = (ArrayList<Seller>)request.getAttribute("seller_db");
			int i = 0;
		%>
		<select id = "name_id" name="example" >
		<%
			for(i = 0;i < seller.size(); i++){
				out.println("<option value=\" +"+ seller.get(i).getSID() +" \"> ");
				out.println(""+seller.get(i).getSID() +","+seller.get(i).getSellerName());
				out.println("</option>");
			}
		%>
		</select>
		<input type="button"  size="3" value = "出品者セット"onclick="check_id('e_id')"><!--submit  -->
		<a href="./Seller_Insert">出品者登録</a>
	</form>
</div>
	<div id="output"></div>
<p>


<%-- 出品表登録画面を出力 --%>
<table border="1" cellspacing="0" cellpadding="4" bgcolor="#A9A9A9" id="table1">
<tr>
	<th>表番</th>
	<th>項番</th>
	<th>商品名</th>
	<th>開始価格</th>
	<th>値札価格</th>
	<th>返却有</th>

<form method = "POST" action = "/Web/EntrySheet_Insert">
<input type="hidden"id = "e_id" name="e_id"value=<%=request.getAttribute("e_id") %> >
<input type="hidden"id = "e_id" name="e_id"value=<%=request.getAttribute("name_id")%>>
	<%
		int J=1;
		String color = "";
		for(J=1;J<11;J++){
			out.println("<tr>");
			out.println("<td class=\"ES_ID\"><input type=\"text\"id = \"ES_ID"+J+"\" name=\"ES_ID\" value=\" \" size=\"2\" maxlength=\"3\"disabled></td>");
			out.println("<td class=\"I_NO\"><input type=\"text\"id = \"I_NO"+J+"\" name=\"I_NO\" value=\""+ J + "\" size=\"2\" maxlength=\"2\"disabled></td>");
			out.println("<td class=\"kana\"><input type=\"text\"id = \"NAME"+J+"\" name=\"NAME\" value=\"\" size=\"35\" maxlength=\"30\"></td>");
			out.println("<td class=\"S_PRICE\"><input type=\"text\"id = \"S_PRICE"+J+"\" name=\"S_PRICE\" value=\"\" size=\"6\" maxlength=\"5\"></td>");
			out.println("<td class=\"PRICE_TAG\"><input type=\"text\"id = \"PRICE_TAG"+J+"\" name=\"PRICE_TAG\" value=\"\" size=\"6\" maxlength=\"5\"></td>");
			out.println("<td class=\"RETFLG\"align=\"center\"><input type=\"checkbox\"id = \"r_chk"+J+"\"  name=\"r_chk\"size=\"3\"></td>");
			out.println("</tr>");
		}
	%>
	<p>
		<span style="float: right"><input type="submit" value="登録"></span>
	</p>
</form>
</table>


</body>
</html>