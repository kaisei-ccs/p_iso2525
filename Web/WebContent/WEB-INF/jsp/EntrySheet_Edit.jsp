<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.EntrySheet" %>
<%@ page import="model.Item" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出品表変更・削除</title>
<link rel="stylesheet" type="text/css" href="./css/common.css">
<style>
thead th:nth-child(1), tbody td:nth-child(1), tfoot td:nth-child(1){ width: 12.0rem; }
thead th:nth-child(2), tbody td:nth-child(2), tfoot td:nth-child(2){ width: 56.0rem; }
thead th:nth-child(3), tbody td:nth-child(3), tfoot td:nth-child(3){ width: 56.0rem; }
</style>
<script type="text/javascript" src="./js/jquery-3.1.1.min.js"></script>
</head>
<body>

<%-- タイトルバーを出力 --%>
<jsp:include page="Title_Bar.jsp">
    <jsp:param name="caller" value="EntrySheet_Edit" />
</jsp:include>

<article>
<form method="post" action="./EntrySheet_Edit">
<section>
<h2 class="hidden">出品表情報</h2>
<table class="display_v">
<tr>
<th>出品表番号</th>
<th>項　　番</th>
<th>出品者番号</th>
</tr>
<tr>
<td><input type="text" id="inputESid"   name="inputESid" value="" maxlength="6" class="posRight"></td>
<td><input type="text" id="inputIno" name="inputIno" value="" maxlength="30"></td>
<td><input type="text" id="inputSid" name="inputSid" value="" maxlength="30"></td>
</tr>
<tr class="non-line">
<td colspan="3">
<input type="reset" value="入力クリア">
<input type="submit" value="検　索" id="btnSea" name="btnSea">
</td>
</tr>
</table>
</section>

<section>
<h2 class="hidden">検索一覧</h2>
<table id="dispSeller" class="display_v scrollBody">
<thead>
<tr>
<th>出品表番号</th>
<th>項　　番</th>
<th>出品者番号</th>
</tr>
</thead>

<tbody style="height: 31.5rem;">
<%

//ArrayList<EntrySheet> ESID = (ArrayList<EntrySheet>)request.getAttribute("ESID");
//ArrayList<EntrySheet> EntrySheetList = EntrySheet.fetchAll();
for (EntrySheet e : (ArrayList<EntrySheet>)request.getAttribute("ESID")) {
	ArrayList<Item> ItemList = Item.findByESID(e.getESID());
	for (Item i : ItemList ) {
		out.write("<tr class=\"paddingTD\">\n");
		out.write("<td>" + e.getESID() + "</td>");
		out.write("<td>" + i.getINO() + "</td>");
		out.write("<td>" + e.getSID() + "</td>\n");
		out.write("</tr>\n");
	}
}
%>
</tbody>

</table>
</section>
</form>
</article>

</body>
</html>