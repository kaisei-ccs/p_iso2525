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
import model.TradeDetailItem;

@WebServlet("/Trade_Detail")
public class Trade_Detail extends HttpServlet {
	private ArrayList<SalesLog> slogList;
	private ArrayList<TradeDetailItem> TDList;
	private ArrayList<TradeDetailItem> CTDList = new ArrayList<TradeDetailItem>();
	private int bool = 2;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int index = Integer.valueOf(request.getParameter("list"));
		slogList = SalesLog.findByTID(index);
		TDList = TradeDetailItem.findByTID(index);
		request.setAttribute("sales", slogList.get(0));
		request.setAttribute("TDList", TDList);
		System.out.println("a:"+CTDList.size());
		request.setAttribute("CTDList", CTDList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Trade_Detail.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int esID = Integer.valueOf(request.getParameter("esID"));
		int iNo = Integer.valueOf(request.getParameter("iNo"));
		if(0 == bool%2){
			CTDList =TradeDetailItem.findByESIDINO(esID,iNo);
		}
		request.setAttribute("sales", slogList.get(0));
		request.setAttribute("TDList", TDList);
		request.setAttribute("CTDList", CTDList);
		System.out.println("b:"+CTDList.size());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Trade_Detail.jsp");
		dispatcher.forward(request, response);
		CTDList.clear();
		bool++;
	}

}
