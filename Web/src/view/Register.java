package view;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	private static final long serialVersionUID = 1L;

        public Register() {
        	super();

		}


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("aaaaa");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/Register.jsp");

		//jspに渡す値をセット
		req.setAttribute("Charge", charge);
		req.setAttribute("CashBack", cashBack);

		dispatcher.forward(req, res);
		//new Create_QR(data, width, height);
	}

	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

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
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        System.out.println(sqlDate);
		//現在の時.分.秒を取得を取得(形式：HH:mm:ss)
       /* java.util.Time utilTime = new java.util.Time();
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        String date =  sdfDate.format(utilDate);
        try {
			utilDate = sdfDate.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        SalesLog SL = new SalesLog(0,date,time,totalPrice,charge,cashBack);
        */
        doGet(req,res);
	}

}
