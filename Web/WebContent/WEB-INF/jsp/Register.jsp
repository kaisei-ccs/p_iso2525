<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>レジ</title>
<script type="text/javascript" src="/Web/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="/Web/js/Title_Bar.js"></script>
<link rel="stylesheet" type="text/css" href="/Web/css/Title_Bar.css">
</head>
<body>
	<header>
		<div id="title"></div>
		<script>
			button_name("メニュー");
			button_url("/Web/Manager_Menu");
			title_set("レジ");
			load();
		</script>
	</header>
	<p>
		aaaaaaa float:left;
	</p>
	<div  style= "height:300px; width:50%; overflow-y:auto;">
		<TABLE border="1" >
			<TR><TD width = "300" align="center">出品表番号</TD><td width ="25%"align="center">項番</td><TD width ="25%"align="center">商品名</TD><td width ="25%"align="center">単価</td></TR>
			<TR><TD> 2-1</TD><td>2-2</td><TD> 2-3</TD><td>2-4</td></TR>
			<TR><TD> 3-1</TD><td>3-2</td><TD> 3-3</TD><td>3-4</td></TR>
			<TR><TD> 4-1</TD><td>4-2</td><TD> 4-3</TD><td>4-4</td></TR>
			<TR><TD> 5-1</TD><td>5-2</td><TD> 5-3</TD><td>5-4</td></TR>
		</TABLE>
	</div>


	<input type = "button" style="position: absolute; left: 60%; top: 20%" name = Stop value = "会計中止" onClick = fauction(1)>
	<input type = "button" style="position: absolute; left: 75%; top: 20%" name = Return value="返品" onClick = fauction(2)>

	<div>
	合計金額<input type="text" name="TotalPrice"><br>
	</div>

	<div>
	預り金<input type="text" name="Charge"><br>
	</div>

	<div>
	<input type="button" name = Confirm value="確定" onClick = fauction(3)><br>
	</div>

	<div>
	おつり<input type="text" name="CashBack">
	</div>

</body>
</html>