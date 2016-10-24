package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Item;

/**
 * Servlet implementation class Scan
 */
@WebServlet("/Scan")
public class Scan extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Scan() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

		Item readItem = new Item();
		readItem.setESID(1);
		readItem.setINO(1);
		readItem.setName("うどん県のオーパーツdayo");

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println(readItem.getName());
		out.close();
	}

}
