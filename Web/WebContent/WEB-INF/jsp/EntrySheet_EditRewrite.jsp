<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.EntrySheet" %>
<%@ page import="model.Seller" %>
<%@ page import="model.Item" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出品表変更・削除</title>
</head>
<body>
<input type="button" class="close" onclick=popup_close() value="✕">
<section>
<h2 class="hidden">検索一覧</h2>
<table id="dispSeller" class="display_v scrollBody">
<thead>
<tr>
<th>出品表番号</th>
<th>項　　番</th>
<th>出品者番号</th>
<th>商品名</th>
<th>開始価格</th>
<th>終了価格</th>
<th>返却有</th>
</tr>
</thead>

<tbody style="height: 33rem; overflow:hidden;">
<%
int ESid  = Integer.valueOf(request.getAttribute("ESid").toString());
EntrySheet e = EntrySheet.findByESID(ESid).get(0);

ArrayList<Item> ItemList = Item.findByESID(ESid);
for (Item i : ItemList ) {
	out.write("<tr>\n");
	out.write("<td><input type=\"text\" maxlength=\"6\" name=\"ESID" + i + "\" value=\"" + i.getESID() + "\"></td>");
	out.write("<td><input type=\"text\" maxlength=\"2\" name=\"INO" + i + "\" value=\"" + i.getINO() + "\"></td>");
	out.write("<td><input type=\"text\" maxlength=\"6\" name=\"SID" + i + "\" value=\"" + e.getSID() + "\"></td>");
	out.write("<td><input type=\"text\" maxlength=\"30\" name=\"Name" + i + "\" value=\"" + i.getName() + "\"></td>");
	out.write("<td><input type=\"text\" maxlength=\"5\" name=\"SPrice" + i + "\" value=\"" + i.getSPrice() + "\"></td>");
	out.write("<td><input type=\"text\" maxlength=\"5\" name=\"EPrice" + i + "\" value=\"" + i.getEPrice() + "\"></td>");
	if(true == i.getRetFlg()){
		out.write("<td><input type=\"checkbox\" name=\"RetFlg" + i + "checked=\"checked\"></td>");
	}else{
		out.write("<td><input type=\"checkbox\" name=\"RetFlg" + i + "></td>");
	}
	out.write("</tr>\n");
}
request.setAttribute("ESid",ESid);
%>
</tbody>

</table>
</section>
<form method="post" action="EntrySheet_EditRewrite">
	<input type="submit" name="Change" value="変更">
	<input type="submit" name="Delete" value="削除">
</form>
</body>
</html>