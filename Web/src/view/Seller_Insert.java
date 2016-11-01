package view;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Seller_Insert
 */
@WebServlet("/Seller_Insert")
public class Seller_Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FD_PATH = "/WEB-INF/jsp/Seller_Insert.jsp";
	/**
     * @see HttpServlet#HttpServlet()
     */
    public Seller_Insert() {
        super();
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(FD_PATH);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setAttribute("Seller_Insert_ErrMsg", "更新された予定");
		doGet(request, response);
	}
}
