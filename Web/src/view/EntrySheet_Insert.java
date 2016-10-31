package view;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Seller;

/**
 * Servlet implementation class EntrySheet_Insert
 */
@WebServlet("/EntrySheet_Insert")
public class EntrySheet_Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;


	ArrayList<Seller> seller;
	//出品表番号
	int e_id = 0;
	//項番
	int I_NO[] = new int[10];


    public EntrySheet_Insert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//jsp
		request.setAttribute("ES_ID",e_id );
		request.setAttribute("e_id",e_id );
		//db
		seller = Seller.fetchAll();
		request.setAttribute("seller_db",seller);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/EntrySheet_Insert.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stu

		//出品表番号
		e_id = Integer.parseInt(request.getParameter("e_id")) ;


		int i = 0;
		int j = 1;
		for(i=0;i<10;i++){
			j++;
			I_NO[i] = Integer.parseInt(request.getParameter("I_NO"+ j)) ;
		}


		doGet(request, response);
	}

}
