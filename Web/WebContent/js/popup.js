$(function(){
	
//モーダルウィンドウを出現させるクリックイベント
$("#popup-open").click( function(){
	//キーボード操作などにより、オーバーレイが多重起動するのを防止する
	$( this ).blur() ;	//ボタンからフォーカスを外す
	if( $( "#popup-overlay" )[0] ) return false ;	//新しくpopupを起動しない
	//オーバーレイを出現させる
	$( "body" ).append( '<div id="popup-overlay"></div>' ) ;
	$( "#popup-overlay" ).fadeIn( "slow" ) ;
	//コンテンツをセンタリングする
	centeringModalSyncer() ;
	//コンテンツをフェードインする
	$( "#popup-content" ).fadeIn( "slow" ) ;
	//popup-overlayまたはpopup-closeをクリックしたら
	$( "#popup-close" ).unbind().click( function(){
		//popup-contentとpopup-overlayをフェードアウトした後に
		//$( "#popup-content" ).fadeOut( "slow" , function(){
		$( "#popup-content,#popup-overlay" ).fadeOut( "slow" , function(){
			//popup-overlayを削除する
			$('#popup-overlay').remove() ;
		} ) ;
	} ) ;
} ) ;

//リサイズされたら、センタリングをする関数[centeringModalSyncer()]を実行する
$( window ).resize( centeringModalSyncer ) ;
	//センタリングを実行する関数
	function centeringModalSyncer() {
		//画面(ウィンドウ)の幅、高さを取得
		var w = $( window ).width() ;
		var h = $( window ).height() ;
		// コンテンツ(popup-content)の幅、高さを取得
		var cw = $( "#popup-content" ).outerWidth();
		var ch = $( "#popup-content" ).outerHeight();
		//センタリングを実行する
		$( "#popup-content" ).css( {"left": ((w - cw)/2) + "px","top": ((h - ch)/2) + "px"} ) ;
	}
} ) ;