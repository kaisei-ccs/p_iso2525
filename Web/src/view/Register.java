package view;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	String data = "11";
	int width = 100;
	int height = 100;
	private static final long serialVersionUID = 1L;

        public Register() {
        	super();

		}


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("aaaaa");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/Register.jsp");
		dispatcher.forward(req, res);
		//new Create_QR(data, width, height);
	}

	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		doGet(req,res);
	}

}
