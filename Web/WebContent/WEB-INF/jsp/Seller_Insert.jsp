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
<table border="1" cellspacing="0" cellpadding="4" id="table1">
  <tr>
    <th>出品者番号</th>
    <th>氏名</th>
    <th>カナ</th>
  </tr><tr>
    <td class="name"><input type="text" name="name" value="" size="10" maxlength="20" /></td>
    <td class="address"><input type="text" name="address" value="" size="10" maxlength="20" /></td>
    <td class="tel"><input type="text" name="tel" value="" size="10" maxlength="20" /></td>
  </tr>
</table>

<p>
 <span style="float: right"><input type="button" value="登録" onclick="history.back()"></span>
</p>

</body>
</html>