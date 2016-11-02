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
	//出品表番号
	int es = 0;
	//出品者ID
	int name_id = 0;
	//変換
	String id_name;
	//項番
	int i_no[] = new int[10];
	String name[] = new String[10];
	int s_price[] = new int[10];
	int price_tag[] = new int[10];
	boolean r_chk[] = new boolean[10];


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
		//request.setAttribute("e_id",e_id );
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
		String wk_name =  request.getParameter("e_idtest1");
		String[] fruit = wk_name.split(",", 0);

		String test = request.getParameter("ENTRYSHEET"+1);//なぞのエラーがあるここまで。
		//es =Integer.parseInt(request.getParameter("ENTRYSHEET"+1));//mull

		name_id =Integer.parseInt( fruit[0]);
		int i = 0;
		int j = 1;

		for(i=0;i<10;i++){

			name[i] = request.getParameter("NAME"+j) ;
			if(name[i] != null && name[i] != ""){

				i_no[i] = j;
				s_price[i] = Integer.parseInt(request.getParameter("S_PRICE"+j)) ;
				price_tag[i] = Integer.parseInt(request.getParameter("PRICE_TAG"+j)) ;
				r_chk[i] = request.getParameter("r_chk"+ j) != null;//返却有
			}else{
				break;
			}

			if(0 == i){
				new EntrySheet(es,name_id).save();
			}

			new Item(es,i_no[i],name[i],s_price[i],price_tag[i],1,r_chk[i],s_price[i],false).save();
			j++;
		}
		doGet(request, response);
	}

}
