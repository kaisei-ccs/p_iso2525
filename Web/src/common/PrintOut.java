package common;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PrintOut
 */
@WebServlet("/PrintOut")
public class PrintOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrintOut() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forwardPath = null;
		String action = request.getParameter("action");
		//初期ログイン画面
		if(action == null){
			//forwardPath = "/WebContent/js/Pop_Up.jsp";
			//forwardPath = "/WEB-INF/jsp/Pop_Up.jsp";
			//forwardPath = "/WEB-INF/jsp/modal.html";
			//forwardPath = "/WEB-INF/jsp/Print_EntrySheet.jsp";
			forwardPath = "/WEB-INF/jsp/Print_PriceTag.jsp";
			//forwardPath = "/WEB-INF/jsp/Seller_Insert.jsp";
			//forwardPath = "/css/Print_EntrySheet.jsp";
		}

		//遷移先
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
