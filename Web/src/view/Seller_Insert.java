package view;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Seller;

/**
 * Servlet implementation class Seller_Insert
 */
@WebServlet("/Seller_Insert")
public class Seller_Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FD_PATH = "/WEB-INF/jsp/Seller_Insert.jsp";

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

		request.setCharacterEncoding("UTF-8");

		if(request.getParameter("btnIns") != null) {
			/** 登録処理・更新処理 **/

			// データを取得
			String data[] = {
								request.getParameter("inputId"),
								request.getParameter("inputName"),
								request.getParameter("inputKana")
							};

			// 値のチェック
			Pattern pattern = Pattern.compile("^[0-9]+$");
			Matcher matcher = pattern.matcher( data[0] );

			String errMsg = "";
			if ( (null == data[0]) || (0 == data[0].length()) ) {
				errMsg = "出品者番号を入力してください。";
			} else if ( false == matcher.find() ) {
				errMsg = "半角数字のみを入力してください。";
			} else if ( (null == data[1]) || (0 == data[1].length()) ) {
				errMsg = "出品者名を入力してください。";
			} else if ( (null == data[2]) || (0 == data[2].length()) ) {
				errMsg = "出品者名を入力してください。";
			}

			// 登録処理
			if ( "" != errMsg ) {
				request.setAttribute("Seller_Insert_ErrMsg", errMsg);
				request.setAttribute("Seller_Insert_Data", data);
			} else {
				int sellerId = Integer.parseInt(data[0]);
				Seller seller = new Seller(sellerId, data[1], data[2]);
				seller.save();
			}
		} else if(request.getParameter("btnDel") != null) {
			/** 削除処理 **/
			String sellerId[ ] = request.getParameterValues("delId");
			for (int i = 0; i < sellerId.length; i++ ) {
	        	int id = Integer.parseInt(sellerId[i]);
	        	Seller seller = new Seller(id,"","");
	        	seller.delete();
	        }
		}

		doGet(request, response);
	}

}
