<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出品表登録</title>
<link rel="stylesheet" type="text/css" href="./css/common.css">
<link rel="stylesheet" type="text/css" href="./css/Manager_Menu.css">

</head>
<body>
<%-- タイトルバーを出力 --%>
<jsp:include page="Title_Bar.jsp">
    <jsp:param name="caller" value="EntrySheet_Insert" />
</jsp:include>

<%-- 出品者を出力 --%>
<p>
　</p>
<p>
	<label>出品表番号:</label>
	<input type="text" name="text1" size="3" maxlength="3">
	<label>出品者番号:</label>
	<input type="text" name="text1" size="3" maxlength="3">
	<label>出品者名:</label>
	<input type="text" name="text1" size="3" maxlength="3">
	<input type="submit" name="Sellr_Insert" size="3" value = "出品者登録">
</p>
<p>
　</p>
<%-- 出品表登録画面を出力 --%>
<table border="1" cellspacing="0" cellpadding="4" bgcolor="#A9A9A9" id="table1">
<tr>
	<th>表番</th>
	<th>項番</th>
	<th>商品名</th>
	<th>開始価格</th>
	<th>値札価格</th>
	<th>有無</th>
</tr>

<%
	int i=1;
	int j=1;
	String color = "";
	for(i=1;i<11;i++){
		if(j == 1){//モノクロトーンにするための処理
			out.println("<tr>");
			j=j-1;
		}else{
			out.println("<tr bgcolor=\"#D8D8D8\">");
			j=j+1;
		}
		out.println("<td class=\"ES_ID\"><input type=\"text\" name=\"ES_ID\" value=\""+ i + "\" size=\"2\" maxlength=\"3\"disabled></td>");
		out.println("<td class=\"I_NO\"><input type=\"text\" name=\"I_NO\" value=\""+ i + "\" size=\"2\" maxlength=\"2\"disabled></td>");
		out.println("<td class=\"kana\"><input type=\"text\" name=\"NAME\" value=\"\" size=\"35\" maxlength=\"30\"></td>");
		out.println("<td class=\"S_PRICE\"><input type=\"text\" name=\"S_PRICE\" value=\"\" size=\"6\" maxlength=\"5\"></td>");
		out.println("<td class=\"PRICE_TAG\"><input type=\"text\" name=\"PRICE_TAG\" value=\"\" size=\"6\" maxlength=\"5\"></td>");
		out.println("<td class=\"RETFLG\"align=\"center\"><input type=\"checkbox\" name=\"r_chk\"size=\"3\"></td>");
		out.println("</tr>");
	}
%>
</table>
<p>
 <span style="float: right"><input type="button" value="登録" onclick="history.back()"></span>
</p>


</body>
</html>