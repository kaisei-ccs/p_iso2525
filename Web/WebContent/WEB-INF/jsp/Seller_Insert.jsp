<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出品者登録画面</title>
<link rel="stylesheet" type="text/css" href="./css/common.css">
<link rel="stylesheet" type="text/css" href="./css/Manager_Menu.css">
</head>
<body>


<%-- タイトルバーを出力 --%>
<jsp:include page="Title_Bar.jsp">
    <jsp:param name="caller" value="Seller_Insert" />
</jsp:include>

<script type="text/javascript">
var counter = 0;
function AddTableRows(){

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

<table border="1" cellspacing="0" cellpadding="4" id="table1">
  <tr>
    <th>出品者番号</th>
    <th>氏名</th>
    <th>カナ</th>
  </tr><tr>
    <td class="ID"><input type="text" name="ID" value="" size="10" maxlength="20" /></td>
    <td class="name"><input type="text" name="name" value="" size="10" maxlength="20" /></td>
    <td class="kana"><input type="text" name="kana" value="" size="10" maxlength="20" /></td>
  </tr>
</table>
<form method="GET" action="#">
  <input type="button" value="テーブルに行を追加" onClick="AddTableRows();" />

<p>
 <span style="float: right"><input type="button" value="登録" onclick="history.back()"></span>
</p>

</body>
</html>