package view;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.DB_Interface;

/**
 * Servlet implementation class Sell_Result
 */
@WebServlet("/Sell_Result")
public class Sell_Result extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FD_PATH = "/WEB-INF/jsp/Sell_Result.jsp";

	ArrayList<Integer> SellResult = new ArrayList<Integer>();


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<ArrayList<String>> SellResultList = getSellResultData();
		request.setAttribute("Sell_Result_Data", SellResultList);
		ServletContext sc = getServletContext();
		sc.setAttribute("Sell_Result_Data", SellResultList);
		RequestDispatcher dispatcher = request.getRequestDispatcher(FD_PATH);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private ArrayList<ArrayList<String>> getSellResultData() {
		ArrayList<ArrayList<String>> retData = new ArrayList<ArrayList<String>>();

		Connection con = DB_Interface.getInstance().getConnection();
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT s.S_ID, s.SELLERNAME, COUNT(*) as ItemCNT, COUNT(t.TOTAL_QUANTITY) as SellCNT," +
					       " ISNULL(SUM(t.TOTAL_PRICE),0) as Sell_Price, ISNULL(SUM(t.TOTAL_PRICE),0) / 2 as Ret_Price" +
					  " FROM ENTRYSHEET e" +
	                       " INNER JOIN SELLER s on (e.S_ID = s.S_ID)" +
	                       " INNER JOIN ITEM i on (e.ES_ID = i.ES_ID)" +
	                       " LEFT OUTER JOIN (" +
	                                        " SELECT ES_ID,I_NO, SUM(PRICE) as TOTAL_PRICE,SUM(QUANTITY) AS TOTAL_QUANTITY" +
	                                          " FROM TRADEDETAIL" +
	                                      " GROUP BY ES_ID, I_NO" +
	                                         ") t on (i.ES_ID = t.ES_ID) AND (i.I_NO = t.I_NO) AND (t.TOTAL_QUANTITY <> 0)" +
	              " GROUP BY s.S_ID, s.SELLERNAME" +
	              " ORDER BY s.S_ID";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while(rs.next()){
				ArrayList<String> lineData = new ArrayList<String>();
				lineData.add(rs.getString("S_ID"));			// 出品者番号
				lineData.add(rs.getString("SELLERNAME"));	// 出品者名
				lineData.add(rs.getString("itemcnt"));		// 出品件数
				lineData.add(rs.getString("sellcnt"));		// 販売件数
				lineData.add(rs.getString("sell_price"));	// 販売額
				lineData.add(rs.getString("ret_price"));	// 還元額
				retData.add( lineData );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return retData;
	}
}
