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
			int ESid = Integer.parseInt(request.getParameter("ESID1"));
			ArrayList<Item> ItemInsertList = Item.findByESID(ESid);
			int count = 1;
			for(Item i : ItemInsertList){
				int SPrice = Integer.valueOf(request.getParameter("SPrice" + count).toString());
				int EPrice = Integer.valueOf(request.getParameter("EPrice" + count).toString());
				i.setName(request.getParameter("Name" + count));
				i.setSPrice(SPrice);
				i.setEPrice(EPrice);
				if(request.getParameter("RetFlg" + count) != null){
					i.setRetFlg(true);
				}else{
					i.setRetFlg(false);
				}
				i.save();
				count = 1 + count;
			}
		}

		if(request.getParameter("Delete") != null && request.getParameter("Delete").equals("削除")){
			int ESid = Integer.parseInt(request.getParameter("ESID1"));
			ArrayList<Item> ItemDeleteList = Item.findByESID(ESid);
			EntrySheet EntrySheetDelete = EntrySheet.findByESID(ESid).get(0);
			for(Item i : ItemDeleteList){
				i.delete();
			}
			EntrySheetDelete.setSID(0);
			EntrySheetDelete.save();
		}

		ArrayList<EntrySheet> ESID = EntrySheet.fetchAll();
		request.setAttribute("ESID", ESID);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/EntrySheet_Edit.jsp");
		dispatcher.forward(request, response);
	}

}
