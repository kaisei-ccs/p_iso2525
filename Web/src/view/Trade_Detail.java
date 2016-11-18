package view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Item;
import model.SalesLog;
import model.TradeDetail;

@WebServlet("/Trade_Detail")
public class Trade_Detail extends HttpServlet {
	private ArrayList<SalesLog> slogList;
	private ArrayList<TradeDetail> TDList;
	private ArrayList<TradeDetail> CTDList = new ArrayList<TradeDetail>();
	private ArrayList<Item> TDIList = new ArrayList<Item>();
	private ArrayList<Item> CTDIList = new ArrayList<Item>();
	private boolean bool = true;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int index = Integer.valueOf(request.getParameter("list"));
		slogList = SalesLog.findByTID(index);
		TDList = TradeDetail.findByTID(index);
		TDIList.clear();
		for(int i=0; i<TDList.size(); i++){
			TDIList.add(addItem(TDList.get(i)));
		}
		request.setAttribute("sales", slogList.get(0));
		request.setAttribute("TDList", TDList);
		request.setAttribute("TDname", TDIList);
		request.setAttribute("CTDList", CTDList);
		request.setAttribute("CTDname", CTDIList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Trade_Detail.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int esID = Integer.valueOf(request.getParameter("esID"));
		int iNo = Integer.valueOf(request.getParameter("iNo"));
		if(true == bool){
			CTDList = set_list(esID,iNo);
			for(int i=0; i<CTDList.size(); i++){
				CTDIList.add(addItem(CTDList.get(i)));
			}
			bool = false;
		}else{
			bool = true;
		}
		request.setAttribute("sales", slogList.get(0));
		request.setAttribute("TDList", TDList);
		request.setAttribute("TDname", TDIList);
		request.setAttribute("CTDList", CTDList);
		request.setAttribute("CTDname", CTDIList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Trade_Detail.jsp");
		dispatcher.forward(request, response);
		CTDList.clear();
		CTDIList.clear();
	}

	private ArrayList<TradeDetail> set_list(int esID, int INo){
		ArrayList<TradeDetail> list = new ArrayList<TradeDetail>();
		ArrayList<TradeDetail> IDlist = TradeDetail.findByESID(esID);
		for(TradeDetail IDtrade : IDlist){
			if(IDtrade.getINO() == INo){
				list.add(IDtrade);
			}
		}
		return list;
	}

	private Item addItem(TradeDetail trade){
		ArrayList<Item> itemlist = Item.findByESID(trade.getESID());
		for(Item item : itemlist){
			if(item.getINO() == trade.getINO()){
				return item;
			}
		}
		return new Item();
	}

}
