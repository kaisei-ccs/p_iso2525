package view;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SalesLog;

/**
 * Servlet implementation class Sales_Log
 */
@WebServlet("/Sales_Log")
public class Sales_Log extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<SalesLog> slogList;
	private String wherestr = new String("");
	private String existsstr = new String("");
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sales_Log() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(wherestr.isEmpty()){
			slogList = SalesLog.fetchAll();
		}else{
			slogList = SalesLog.findBy(wherestr);
			wherestr = "";
			existsstr = "";
		}
		request.setAttribute("saleslog", slogList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Sales_Log.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		check1(id,0);
		String date = request.getParameter("date");
		check1(date,1);
		String time1 = request.getParameter("time1");
		String time2 = request.getParameter("time2");
		check2(time1, time2,2);
		String total1 = request.getParameter("total1");
		String total2 = request.getParameter("total2");
		check2(total1, total2,3);
		String charge1 = request.getParameter("charge1");
		String charge2 = request.getParameter("charge2");
		check2(charge1, charge2,4);
		String cashBack1 = request.getParameter("cashback1");
		String cashBack2 = request.getParameter("cashback2");
		check2(cashBack1, cashBack2,5);
		String es_id = request.getParameter("es_id");
		check3(es_id,true);
		String i_no = request.getParameter("i_no");
		check3(i_no,true);
		if(!existsstr.isEmpty()){
			existsstr += ")";
			wherestr += existsstr;
		}
		doGet(request, response);
	}

	//T_IDとT_DATEをWHEREに追加するか判定
	private void check1(String str, int number){
		if(!str.isEmpty()){
			if(!wherestr.isEmpty()){
				wherestr += " AND ";
			}
			switch(number){
				case 0:
					wherestr += "T_ID="+str;
					break;
				case 1:
					wherestr += "T_DATE='"+str+"'";
					break;
				default:
			}
		}
	}

	//T_TIMEとTOTALとCHSRGEとCASHBACKをWHEREに追加するか判定
	private void check2(String str1, String str2,int number){
		if(!str1.isEmpty() && !str2.isEmpty()){
			if(!wherestr.isEmpty()){
				wherestr += " AND ";
			}
			switch(number){
				case 2:
					wherestr += "T_TIME BETWEEN '" + str1 + "' AND '" + str2 + "'";
					System.out.println(wherestr);
					break;
				case 3:
					wherestr += "TOTAL BETWEEN " + str1 + " AND " + str2;
					break;
				case 4:
					wherestr += "CHSRGE BETWEEN " + str1 + " AND " + str2;
					break;
				case 5:
					wherestr += "CASHBACK BETWEEN " + str1 + " AND " + str2;
					break;
				default:
			}
		}
	}

	//EXISTSする時に追加
	private void check3(String str1, boolean bool){
		if(!str1.isEmpty()){
			if(!existsstr.isEmpty()){
				existsstr += " AND ";
			}else if(!wherestr.isEmpty()){
				existsstr += " AND EXISTS(SELECT * FROM TRADEDETAIL WHERE slog.T_ID =T_ID AND ";
			}else{
				existsstr += "EXISTS(SELECT * FROM TRADEDETAIL WHERE slog.T_ID =T_ID AND ";
			}
			if(bool){
				existsstr += "ES_ID="+str1;
			}else{
				existsstr += "I_NO="+str1;
			}
		}
	}
}
