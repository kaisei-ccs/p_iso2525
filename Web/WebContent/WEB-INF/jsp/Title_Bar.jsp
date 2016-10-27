<%@ page contentType="text/html; charset=UTF-8"%>
<header>
<%
	String caller = request.getParameter("caller");
	String title = "";
	switch (caller) {
		case "Seller_Insert":
			title="出品者登録";				break;
		case "Seller_Edit":
			title="出品者変更・削除";		break;
		case "EntrySheet_Insert":
			title="出品表登録";				break;
		case "EntrySheet_Edit":
			title="出品表変更・削除";		break;
		case "Item_Search":
			title="在庫一覧";				break;
		case "Sales_Log":
			title="会計履歴";				break;
		case "Sell_Result":
			title="精算";					break;
		case "Print_EntrySheet":
			title="出品表記入用紙印刷";		break;
		case "Print_PriceTag":
			title="値札印刷";				break;
		case "Manager_Menu":
			title = "管理者メニュー";		break;
		case "Register":
			title="レジ";					break;
	}

	String retPath = "./Manager_Menu";
	String retTitle = "もどる";

	if (true == caller.equals("Manager_Menu")) {
		retPath = "./Register";
		retTitle = "レジ";
	}

	if (true == caller.equals("Register")){
		retTitle = "メニュー";
	}

%>
<dl>
<dt><%= title %></dt>
<dd class="btn"><a href="<%= retPath %>"><%= retTitle %></a></dd>
</dl>
</header>