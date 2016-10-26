<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者メニュー</title>
<link rel="stylesheet" type="text/css" href="./css/common.css">
<link rel="stylesheet" type="text/css" href="./css/Manager_Menu.css">
<link rel="stylesheet" type="text/css" href="./css/Title_Bar.css">
<script src="./js/jquery-3.1.1.min.js"></script>
<script src="./js/Title_Bar.js"></script>
</head>
<body>
<header id="title">
<script> load(); </script>
</header>

<article>
<section>
<h2 class="hidden">マスタメンテナンス</h2>
<ul>
<li><a href="./Seller_Insert">出品者登録</a></li>
<li><a href="./Seller_Edit">出品者変更・削除</a></li>
<li><a href="./EntrySheet_Insert">出品表登録</a></li>
<li><a href="./EntrySheet_Edit">品表変更・削除</a></li>
</ul>
</section>

<section>
<h2 class="hidden">日時処理</h2>
<ul>
<li><a href="./Item_Search">在庫一覧</a></li>
<li><a href="./Sales_Log">会計履歴</a></li>
<li><a href="./Sell_Result">精算</a></li>
</ul>
</section>

<section>
<h2 class="hidden">印刷処理</h2>
<ul>
<li><a href="./Print_EntrySheet">出品表記入用紙印刷</a></li>
<li><a href="./Print_PriceTag">札印刷</a></li>
</ul>
</section>

</article>

</body>
</html>