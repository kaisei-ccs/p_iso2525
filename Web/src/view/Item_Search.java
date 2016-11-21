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
import model.Scan;
import model.Seller;

/**
 * Servlet implementation class Manager_Menu
 */
@WebServlet("/Item_Search")
public class Item_Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<Item> itemlist;
	private ArrayList<Seller> sellerlist = new ArrayList<Seller>();
	private String wherestr = "";
	private String existsstr = "";
    private static final String FD_PATH = "/WEB-INF/jsp/Item_Search.jsp";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Item_Search() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		itemlist = Item.fetchAll();
		request.setAttribute("Itemlist", itemlist);
		request.setAttribute("Sellerlist", sellerlist);
		RequestDispatcher dispatcher = request.getRequestDispatcher(FD_PATH);
		dispatcher.forward(request, response);
		itemlist.clear();
		sellerlist.clear();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String submit = request.getParameter("MySubmit");
		if(submit.equals("reference")){
			String esid = request.getParameter("es_id");
			check1(esid,0);
			String ino = request.getParameter("i_no");
			check1(ino,1);
			String quantity = request.getParameter("quantity");
			check1(quantity,2);
			String retflg = request.getParameter("retflg");
			check1(retflg,3);
			String sid = request.getParameter("s_id");
			check3(sid, 0);
			String name = request.getParameter("name");
			check3(name, 1);
			String kana = request.getParameter("kana");
			check3(kana, 2);
			if(!existsstr.isEmpty()){
				existsstr += ")";
				wherestr += existsstr;
			}
			if(wherestr.isEmpty()){
				itemlist = Item.fetchAll();
			}else{
				itemlist = Item.findBy(wherestr);
			}
		}else if(submit.equals("stock")){
			itemlist = Item.fetchAll();
			ArrayList<Scan> scanlist = Scan.fetchAll();
			stock(scanlist);
		}else{
			itemlist = Item.findByQuantity(1);
			for(Item item : itemlist){
				item.setPrice(item.getEPrice());
				item.save();
			}
			itemlist = Item.fetchAll();
		}
		request.setAttribute("Itemlist", itemlist);
		request.setAttribute("Sellerlist", sellerlist);
		RequestDispatcher dispatcher = request.getRequestDispatcher(FD_PATH);
		dispatcher.forward(request, response);
		wherestr = "";
		existsstr = "";
		itemlist.clear();
		sellerlist.clear();
	}

	//T_IDとT_DATEをWHEREに追加するか判定
	private void check1(String str, int number){
		if(!str.isEmpty()){
			if(!wherestr.isEmpty()){
				wherestr += " AND ";
			}
			switch(number){
				case 0:
					wherestr += "ES_ID="+str;
					break;
				case 1:
					wherestr += "I_NO="+str;
					break;
				case 2:
					wherestr += "QUANTITY="+str;
					break;
				case 3:
					wherestr += "RETFLG="+str;
					break;
				default:
			}
		}
	}

	//EXISTSする時に追加
	private void check3(String str1, int number){
		if(!str1.isEmpty()){
			if(!existsstr.isEmpty()){
				existsstr += " AND ";
			}else if(!wherestr.isEmpty()){
				existsstr += " AND EXISTS(SELECT * FROM ENTRYSHEET, SELLER WHERE ENTRYSHEET.S_ID = SELLER.S_ID AND it.ES_ID = ENTRYSHEET.ES_ID AND ";
			}else{
				existsstr += "EXISTS(SELECT * FROM ENTRYSHEET, SELLER WHERE ENTRYSHEET.S_ID = SELLER.S_ID AND it.ES_ID = ENTRYSHEET.ES_ID AND ";
			}
			switch(number){
				case 0:
					existsstr += "SELLER.S_ID="+str1;
					break;
				case 1:
					existsstr += "SELLER.SELLERNAME='"+str1+"'";
					break;
				case 2:
					existsstr += "SELLER.SELLERKANA='"+str1+"'";
					break;
				default:
			}
		}
	}

	private void stock(ArrayList<Scan> scanlist){

		for(Scan scan : scanlist){
			int index = 0;
			for(Item item : itemlist){
				if(item.getESID() == scan.getESID() && item.getINO() == scan.getINO()){
					itemlist.remove(index);
					break;
				}
				index++;
			}
		}
	}

}