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

/**
 * Servlet implementation class EntrySheet_EditRewrite
 */
@WebServlet("/EntrySheet_EditRewrite")
public class EntrySheet_EditRewrite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int ESid = Integer.valueOf(request.getParameter("list"));
		//EntrySheet.findByESID(ESid);
		request.setAttribute("ESid", ESid);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/EntrySheet_EditRewrite.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		if(request.getParameter("Change") != null && request.getParameter("Change").equals("変更")){
			int ESid = Integer.valueOf(request.getAttribute("ESid").toString());
			ArrayList<Item> ItemList = Item.findByESID(ESid);
			//ArrayList<Item> ItemList = (ArrayList<Item>) request.getAttribute("ItemList");
			//Item Item
			for(Item i : ItemList){
				//printFlg = Item.findByBarcodeData(item.getESID(), item.getINO()).get(0);
				//printFlg.setPrintFlg(true);
				//printFlg.save();
			}
		}

		if(request.getParameter("Delete") != null && request.getParameter("Delete").equals("削除")){

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/EntrySheet_Edit.jsp");
		dispatcher.forward(request, response);
	}

}
