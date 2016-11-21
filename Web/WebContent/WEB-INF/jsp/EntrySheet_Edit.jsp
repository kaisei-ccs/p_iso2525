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
<link rel="stylesheet" type="text/css" href="./css/Popup.css">
<style>
#dispSeller thead th:nth-child(1), #dispSeller tbody td:nth-child(1), #dispSeller tfoot td:nth-child(1){ width: 12.0rem; }
#dispSeller thead th:nth-child(2), #dispSeller tbody td:nth-child(2), #dispSeller tfoot td:nth-child(2){ width: 56.0rem; }
#dispSeller thead th:nth-child(3), #dispSeller tbody td:nth-child(3), #dispSeller tfoot td:nth-child(3){ width: 56.0rem; }
</style>
<script type="text/javascript" src="./js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="./js/Popup.js"></script>
<script type="text/javascript" src="./js/EntrySheet_Edit.js"></script>
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
<th>出品者番号</th>
</tr>
<tr>
<td><input type="text" id="inputESid"   name="inputESid" value="" maxlength="6" class="posRight"></td>
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
<th>出品者番号</th>
</tr>
</thead>

<tbody class="salestable" style="height: 31.5rem;">
<%

//ArrayList<EntrySheet> ESID = (ArrayList<EntrySheet>)request.getAttribute("ESID");
//ArrayList<EntrySheet> EntrySheetList = EntrySheet.fetchAll();
for (EntrySheet e : (ArrayList<EntrySheet>)request.getAttribute("ESID")) {
	out.write("<tr class=\"paddingTD posRight\">\n");
	out.write("<td>" + e.getESID() + "</td>");
	out.write("<td>" + e.getSID() + "</td>\n");
	out.write("</tr>\n");
}
%>
</tbody>

</table>
</section>
</form>
</article>
<div id="popup-content">
</div>
</body>
</html>