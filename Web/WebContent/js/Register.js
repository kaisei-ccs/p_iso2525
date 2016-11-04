function insertRow(id) {
    // テーブル取得
    var table = document.getElementById(id);
    // 行を行末に追加
    var row = table.insertRow( -1 );
    // セルの挿入
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);

    // 行数取得
    var row_len = table.rows.length;

    // セルの内容入力
    var HTML1 = '<input type="text" id="cell1" onblur="insertCheck()" value="" />';
    var HTML2 = '<input type="text" id="cell2" onblur="insertCheck()" value="" />';
    var HTML3 = '<input type="text" value="" />';
    var HTML4 = '<input type="text" value="" />';
    cell1.innerHTML = HTML1;
    cell2.innerHTML = HTML2;
}
//出品表番号と項番に数字が入力されているか
function insertCheck(){
	var checkHTML1val = document.getElementById('cell1').value;
	var checkHTML2val = document.getElementById('cell2').value;

	//出品表番号と項番に数字が入力されていたらそのデータをPOSTする
	if(true == isNumber(checkHTML1val) && true == isNumber(checkHTML2val)){
		var margeData = checkHTML1val + "\t" + checkHTML2val;

		$.post("/Web/Scan",
				{ postData: margeData },
				function(data){
					//document.location.reload(false);
					location.replace(location.href);
				}
		);
	}


}
//数値チェック
function isNumber(n){
	  if(typeof(n) != 'number' && typeof(n) != 'string'){
	    return false;
	  }

	  if(n == ''){
	    return false;
	  }

	  if(isNaN(n)){
	    return false;
	  }

	  return true;
}

//お客様用画面表示
function registerSub(){
	window.open("/WEB-INF/jsp/Register_Sub.jsp","window1","toolbar=no, height=600, width=600");
}