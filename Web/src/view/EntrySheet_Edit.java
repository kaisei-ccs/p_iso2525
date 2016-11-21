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

/**
 * Servlet implementation class EntrySheet_Edit
 */
@WebServlet("/EntrySheet_Edit")
public class EntrySheet_Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FD_PATH = "/WEB-INF/jsp/EntrySheet_Edit.jsp";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(FD_PATH);
		ArrayList<EntrySheet> ESID = EntrySheet.fetchAll();
		request.setAttribute("ESID", ESID);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// データを取得
		String data[] = {
							request.getParameter("inputESid"),
							request.getParameter("inputSid")
						};

		if(request.getParameter("btnSea") != null && request.getParameter("btnSea").equals("検　索")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/EntrySheet_Edit.jsp");
			if(data[0] != ""){
				ArrayList<EntrySheet> ESID = EntrySheet.findByESID(Integer.parseInt(data[0]));
				request.setAttribute("ESID", ESID);
			}else if(data[1] != ""){
				ArrayList<EntrySheet> ESID = EntrySheet.findBySID(Integer.parseInt(data[1]));
				request.setAttribute("ESID", ESID);
			}else{
				ArrayList<EntrySheet> ESID = EntrySheet.fetchAll();
				request.setAttribute("ESID", ESID);
			}
			dispatcher.forward(request, response);
		}

	}

}
