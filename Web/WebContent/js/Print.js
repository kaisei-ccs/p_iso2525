/**
 * 印刷処理
 */

function Print_open(){
		//id="page"要素を印刷表示オフにする
		$("#page").addClass('print-off');
		window.print();
		//print-offクラスを削除する
		$('#page').removeClass('print-off');
}

