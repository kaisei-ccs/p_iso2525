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
	var checkHTML1val = toHalfWidth(document.getElementById('cell1').value);
	var checkHTML2val = toHalfWidth(document.getElementById('cell2').value);

	//出品表番号と項番に数字が入力されていたらそのデータをPOSTする
	if(true == isNumber(checkHTML1val) && true == isNumber(checkHTML2val)){
		var margeData = checkHTML1val + "\t" + checkHTML2val;

		$.post("/Web/Scan",
				{ postData: margeData },
				function(data){
					insertScanData();
				}
		);
	}
}
//全角入力を半角に変換
function toHalfWidth(strVal){
	  // 半角変換
	  var halfVal = strVal.replace(/[！-～]/g,
	    function( tmpStr ) {
	      // 文字コードをシフト
	      return String.fromCharCode( tmpStr.charCodeAt(0) - 0xFEE0 );
	    }
	  );

	  // 文字コードシフトで対応できない文字の変換
	  return halfVal.replace(/”/g, "\"")
	    .replace(/’/g, "'")
	    .replace(/‘/g, "`")
	    .replace(/￥/g, "\\")
	    .replace(/　/g, " ")
	    .replace(/〜/g, "~");
}

//スキャンデータ取得
function insertScanData(){
	//データ数取得
	$.get("/Web/Register",
			function(data){
				html = data.substr(8);
				//テーブル確認
				var newTable = $(html).find('#table_Item').html();
				var oldTable = $('#table_Item').html();

				var newTotalPrice = $(html).find('#TotalPrice').val();
				var newCharge = $(html).find('#Charge').val();
				var newCashBack = $(html).find('#CashBack').val();

				var oldTotalPrice = $('#TotalPrice').html();
				var oldCharge = $('#Charge').html();
				var oldCashBack = $('#CashBack').html();

				if(newTable.indexOf(oldTable) == -1 ||
						newTotalPrice.indexOf(oldTotalPrice) == -1 ||
						newCharge.indexOf(oldCharge) == -1 ||
						newCashBack.indexOf(oldCashBack) == -1){
					$('#table_Item').html(newTable);

					$('#TotalPrice').val(newTotalPrice);
					$('#Charge').val(newCharge);
					$('#CashBack').val(newCashBack);
					goScrollTop();
				}
	}
	);
}
//スクロールバーを下にする
function goScrollTop() {
    var obj = document.getElementById("cell1");
    if(!obj) return;
    obj.focus();
}
//Scanデータをポーリング
function pollingScanData(){

	insertScanData();

	setTimeout(function(){
		pollingScanData();
	},500);
}
pollingScanData();

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
	window.open("./Register_Sub","window1","toolbar=no, height=600, width=600");
}
