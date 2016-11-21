package view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Seller>SellerList = new ArrayList<Seller>();
	    ArrayList<ArrayList<Item>>ItemList = new ArrayList<ArrayList<Item>>();
	    ArrayList<ArrayList<String>> SellerData = new ArrayList<ArrayList<String>>();
		String FD_PATH;

		// チェックボックスより出品者番号を取得
		String sellerId[ ] = request.getParameterValues("selId");
		ServletContext sc = getServletContext();
		ArrayList<ArrayList<String>> SellResultList = (ArrayList<ArrayList<String>>)sc.getAttribute("Sell_Result_Data");

		//if(){
			for(int i=0; i<sellerId.length; i++){
				int id = Integer.parseInt(sellerId[i]);
				// 出品者を格納
				SellerList.add(Seller.findBySID(id).get(0));

				// 出品表を格納
				ArrayList<EntrySheet> ESList = EntrySheet.findBySID( id );

				// 商品を格納
				ArrayList<Item> items = new ArrayList<Item>();
				for (EntrySheet es : ESList ) {
					ArrayList<Item> list = Item.findByESID(es.getESID());
					for(Item item : list){
						items.add(item);
					}
				}
				ItemList.add(items);

				// 出品者情報を格納
				for (ArrayList<String> Sell_Result : SellResultList ) {
					if (id == Integer.parseInt( Sell_Result.get(0))) {
						SellerData.add(Sell_Result);
						break;
					}
				}
			}

			request.setAttribute("SellerList", SellerList);
			request.setAttribute("ItemList", ItemList);
			request.setAttribute("SellerData", SellerData);

			FD_PATH = "/WEB-INF/jsp/Print/Sell_Result.jsp";
		/*}else{
			request.setAttribute("Sell_Result_Data", SellResultList);
			FD_PATH = "/WEB-INF/jsp/Print/Sell_Result_List.jsp";
		}*/
		RequestDispatcher dispatcher = request.getRequestDispatcher(FD_PATH);
		dispatcher.forward(request, response);
		SellerList.clear();
		ItemList.clear();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
