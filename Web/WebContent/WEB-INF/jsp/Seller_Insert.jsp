<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出品者登録画面</title>
<link rel="stylesheet" type="text/css" href="./css/common.css">
</head>

<body>

<%-- タイトルバーを出力 --%>
<jsp:include page="Title_Bar.jsp">
    <jsp:param name="caller" value="Seller_Insert" />
</jsp:include>

<article>
<section>
<h2 class="hidden">登録情報</h2>
<div style="width:1220rem;"" class="posRight">
<input type="button" value="CSV読込">
</div>

<table class="display_v">
<tr>
<th style="width: 100rem;">出品者番号</th>
<th style="width: 560rem;">氏　　名</th>
<th style="width: 560rem;">カ　　ナ</th>
</tr>
<tr>
<td><input type="text" name="inputId"   value="" maxlength="8" ></td>
<td><input type="text" name="inputName" value="" maxlength="30"></td>
<td><input type="text" name="inputKana" value="" maxlength="30"></td>
</tr>
<tr class="non-line">
<td colspan="3">
<input type="button" value="入力クリア">
<input type="button" value="登　録">
</td>
</tr>
</table>
</section>

<section>
<h2 class="hidden">登録済一覧</h2>
<table class="display_v">
<tr>
<th style="width: 100rem;">出品者番号</th>
<th style="width: 560rem;">氏　　名</th>
<th style="width: 560rem;">カ　　ナ</th>
</tr>
<tr>
<td>あいうえお</td>
<td>あいうえおあいうえおあいうえおあいうえおあいうえおあいうえお</td>
<td>あいうえおあいうえおあいうえおあいうえおあいうえおあいうえお</td>
</tr>
<tr>
<td>あいうえお</td>
<td>あいうえおあいうえおあいうえおあいうえおあいうえおあいうえお</td>
<td>あいうえおあいうえおあいうえおあいうえおあいうえおあいうえお</td>
</tr>
<tr>
<td>あいうえお</td>
<td>あいうえおあいうえおあいうえおあいうえおあいうえおあいうえお</td>
<td>あいうえおあいうえおあいうえおあいうえおあいうえおあいうえお</td>
</tr>
</table>
</section>
</article>

<script type="text/javascript">
var counter = 0;
function AddTableRows(){kou

	counter++;

	var table1 = document.getElementById("table1");
	var row1 = table1.insertRow(counter);
	var cell1 = row1.insertCell(0);
	var cell2 = row1.insertCell(1);
	var cell3 = row1.insertCell(2);


	cell1.setAttribute("class","ID");
	cell2.setAttribute("class","name");
	cell3.setAttribute("class","kana");
	cell1.className = 'ID';
	cell2.className = 'name';
	cell3.className = 'kana';

	var HTML1 = '<input type="text" name="ID' + counter + '" value="" size="10" maxlength="20" />';
	var HTML2 = '<input type="text" name="name' + counter + '" value="" size="10" maxlength="20" />';
	var HTML3 = '<input type="text" name="kana' + counter + '" value="" size="10" maxlength="20" />';
	cell1.innerHTML = HTML1;
	cell2.innerHTML = HTML2;
	cell3.innerHTML = HTML3;

}

</script>

</body>
</html>