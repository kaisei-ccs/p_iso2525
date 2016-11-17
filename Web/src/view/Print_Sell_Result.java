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
import model.Item;
import model.Seller;

/**
 * Servlet implementation class Print_Sell_Result
 */
@WebServlet("/Print_Sell_Result")
public class Print_Sell_Result extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ArrayList<Seller>SellerList = new ArrayList<Seller>();
    private ArrayList<ArrayList<Item>>ItemList = new ArrayList<ArrayList<Item>>();
    private ArrayList<Integer>SumList = new ArrayList<Integer>();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*int size = Integer.parseInt(request.getParameter("size"));
		for(int i=0; i<size; i++){
			SellerList.add(Seller.findBySID(Integer.parseInt(request.getParameter("sid"+i))).get(0));
		}*/
		int size = 1;
		for(int i=0; i<size; i++){
			SellerList.add(Seller.findBySID(100).get(0));
		}
		for(int i=0; i<SellerList.size(); i++){
			ArrayList<Item> items = new ArrayList<Item>();
			ArrayList<EntrySheet> ESList = EntrySheet.findBySID(SellerList.get(i).getSID());
			for(int j=0; j<ESList.size(); j++){
				ArrayList<Item> list = Item.findByESID(ESList.get(j).getESID());
				for(Item item : list){
					items.add(item);
				}
			}
			ItemList.add(items);
		}
		request.setAttribute("SellerList", SellerList);
		request.setAttribute("ItemList", ItemList);
		request.setAttribute("SumList", SumList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Print/Sell_Result.jsp");
		dispatcher.forward(request, response);
		SellerList.clear();
		ItemList.clear();
		SumList.clear();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
