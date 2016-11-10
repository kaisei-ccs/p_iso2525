

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
					var table = document.getElementById("table_Item");
					table.deleteRow(table.rows.length-2);
				}
			}
	);
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
