package view;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Manager_Menu
 */
@WebServlet("/Manager_Menu")
public class Manager_Menu extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String FD_PATH = "/WEB-INF/jsp/Manager_Menu.jsp";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manager_Menu() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(FD_PATH);
		dispatcher.forward(request, response);
	}
}
