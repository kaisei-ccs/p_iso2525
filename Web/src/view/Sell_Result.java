package view;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EntrySheet;
import model.Seller;
import model.TradeDetail;

/**
 * Servlet implementation class Sell_Result
 */
@WebServlet("/Sell_Result")
public class Sell_Result extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FD_PATH = "/WEB-INF/jsp/Sell_Result.jsp";
	ArrayList<Seller> SellerList;
	ArrayList<Integer> SellResult = new ArrayList<Integer>();


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SellerList = Seller.fetchAll();
		for(Seller seller : SellerList){
			ArrayList<EntrySheet> ESList = EntrySheet.findBySID(seller.getSID());
			SR(ESList);
		}
		request.setAttribute("sellerlist", SellerList);
		request.setAttribute("sellresult", SellResult);
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

	private void SR(ArrayList<EntrySheet> ESList){
		int max = 0;
		for(EntrySheet es : ESList){
			ArrayList<TradeDetail>TDlist = TradeDetail.findByESID(es.getESID());
			for(TradeDetail trade : TDlist){
				max += trade.getPrice();
			}
		}
		SellResult.add(max);
	}

}
