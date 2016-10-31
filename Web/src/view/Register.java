package view;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Item;
import model.SalesLog;
import model.Scan;
import model.TradeDetail;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	String data = "11";
	int width = 100;
	int height = 100;
	//預り金
	int charge = 0;
	//合計金額
	int totalPrice = 0;
	//おつり
	int cashBack = 0;
	//単価
	int itemPrice = 0;
	//SCANの中身を取得用変数
	int scanEsId;
	int scanIno;

	ArrayList<Scan> scan;
	ArrayList<Item> item;
	private static final long serialVersionUID = 1L;

        public Register() {
        	super();

		}


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/Register.jsp");

		//jspに渡す値をセット
		req.setAttribute("Charge", charge);
		req.setAttribute("CashBack", cashBack);
		scan = Scan.fetchAll();
		req.setAttribute("scan",scan);
		dispatcher.forward(req, res);
		//new Create_QR(data, width, height);
	}

	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		//確定が押されたとき
		if(req.getParameter("Confirm") != null && req.getParameter("Confirm").equals("確定")){
			//会計中止が押された場合
			System.out.println("ababbbababa" + req.getParameter("regiStop"));
			System.out.println("return" + req.getParameter("Return"));

			//預り金額から合計金額を引いた値を取得
			charge = (Integer.parseInt(req.getParameter("Charge"))) ;
			totalPrice = (Integer.parseInt(req.getParameter("TotalPrice")));
			cashBack = charge - totalPrice;

			//現在の年月日を取得(形式：yyyy-mm-dd)
			java.util.Date utilDate = new java.util.Date();
	        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	        String date =  sdfDate.format(utilDate);
	        try {
				utilDate = sdfDate.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	        Date sqlDate = new java.sql.Date(utilDate.getTime());

	        //現在の時.分.秒を取得を取得(形式：HH:mm:ss)
	        java.util.Date utilTime = new java.util.Date();
	        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
	        String time = sdfTime.format(utilTime);
	        try {
				utilTime = sdfTime.parse(time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	        java.sql.Time sqlTime = new java.sql.Time(utilTime.getTime());

	        //会計履歴に会計情報を書き込む(T_ID,T_DATE,T_TIME,TOTAL,ChARGE,CASHBACK)
	        SalesLog SL = new SalesLog(0,sqlDate,sqlTime,totalPrice,charge,cashBack);
	        SL.save();

	        //会計明細に会計情報を書き込む(T_ID,T_NO.ES_ID,I_NO,PRICE,QUNTITY)
	        for(int i = 0; i < scan.size() ;i ++){
	        	//jはTRADEDETAILの項番用
	        	scanEsId = scan.get(i).getESID();
		        scanIno = scan.get(i).getINO();
		        item = Item.findByBarcodeData(scanEsId, scanIno);
		        itemPrice = item.get(0).getPrice();
		        //最後の引数の1はレジから送られてくる返品処理の結果によって値を変えるようにする
		        TradeDetail TD = new TradeDetail(SL.getTID(),i + 1,scanEsId,scanIno,itemPrice,1);
		        TD.save();
		        //ITEMから売れた商品の数量を-1する
		        Item quantity = Item.findByESID(scanEsId).get(scanIno-1);
		        quantity.setQuantity(quantity.getQuantity() - 1);
		        quantity.save();
		    }
		}

		if(req.getParameter("regiStop").equals("会計中止")){
			//DBのSCANの中身をすべて削除する
				for(int i= 0;i < scan.size();i++){
				scan.get(i).delete();
			}
			//レジ画面のテーブルの中身をすべて削除する
			//合計金額、預り金、おつりの値を0にする
		}


        doGet(req,res);
	}

}
