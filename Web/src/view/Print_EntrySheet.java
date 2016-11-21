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

@WebServlet("/Print_EntrySheet")
public class Print_EntrySheet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FD_PATH = "/WEB-INF/jsp/Print_EntrySheet.jsp";
	int Sheet = 0;
	ArrayList<EntrySheet> EntrySheetSize;

    public Print_EntrySheet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(FD_PATH);
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//印刷ボタンでPrint/Print_EntrySheet.jspを呼び出す
		if(request.getParameter("print") != null && request.getParameter("print").equals("印刷")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Print/Print_EntrySheet.jsp");
			EntrySheetSize = EntrySheet.fetchAll();
			Sheet = Integer.parseInt(request.getParameter("Sheet"));
			request.setAttribute("Sheet", Sheet);
			request.setAttribute("SheetSize", EntrySheetSize.size());
			dispatcher.forward(request, response);
		}
		if(request.getParameter("Confirm") != null && request.getParameter("Confirm").equals("確定")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Print_EntrySheet.jsp");
			for(int i = 0; Sheet > i; i++){
				new EntrySheet(EntrySheetSize.size() + i, 0).save();
			}
			dispatcher.forward(request, response);
		}
	}
}
