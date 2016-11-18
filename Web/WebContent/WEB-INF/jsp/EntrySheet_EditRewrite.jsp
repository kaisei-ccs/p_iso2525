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
<form method="post" action="EntrySheet_EditRewrite">
<section>
<h2 class="hidden">検索一覧</h2>
<table>
<thead>
<tr>
<th style="width:3rem;">出品表番号</th>
<th style="width:3rem;">項　　番</th>
<th style="width:3rem;">出品者番号</th>
<th style="width:5rem;">商品名</th>
<th style="width:3rem;">開始価格</th>
<th style="width:3rem;">終了価格</th>
<th style="width:3rem;">返却有</th>
</tr>
</thead>

<tbody style="height: 33rem; overflow:hidden;">
<%
int ESid  = Integer.valueOf(request.getAttribute("ESid").toString());
request.setAttribute("ESid",ESid);
EntrySheet e = EntrySheet.findByESID(ESid).get(0);
ArrayList<Item> ItemList = Item.findByESID(ESid);
int count = 1;
for (Item i : ItemList ) {
	out.write("<tr>\n");
	out.write("<td><input type=\"text\" maxlength=\"6\" name=\"ESID" + count + "\" value=\"" + i.getESID() + "\" readonly=\"readonly\"></td>");
	out.write("<td>" + i.getINO() + "</td>");
	out.write("<td>" + e.getSID() + "</td>");

	out.write("<td><input type=\"text\" maxlength=\"30\" name=\"Name" + count + "\" value=\"" + i.getName() + "\"></td>");
	out.write("<td><input type=\"text\" maxlength=\"5\" name=\"SPrice" + count + "\" value=\"" + i.getSPrice() + "\"></td>");
	out.write("<td><input type=\"text\" maxlength=\"5\" name=\"EPrice" + count + "\" value=\"" + i.getEPrice() + "\"></td>");
	if(true == i.getRetFlg()){
		out.write("<td><input type=\"checkbox\" name=\"RetFlg" + count + "\" checked=\"checked\"></td>");
	}else{
		out.write("<td><input type=\"checkbox\" name=\"RetFlg" + count + "\"></td>");
	}
	out.write("</tr>\n");
	count = 1 + count;
}
%>
</tbody>

</table>
</section>
	<input type="submit" name="Change" value="変更">
	<input type="submit" name="Delete" value="削除">
</form>
</body>
</html>