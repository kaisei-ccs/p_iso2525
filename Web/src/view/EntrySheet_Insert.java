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
	int item_no[] = new int[10];
	//商品名
	String item_name[] = new String[10];
	String wk_name[] = new String[10];
	int start_price[] = new int[10];
	int sale_price[] = new int[10];
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
		request.setCharacterEncoding("UTF8");
		//出品表番号

		wk_name[0] =  request.getParameter("exhibitor_ID");
		name_id =Integer.parseInt(wk_name[0]);
		wk_name[1] =  request.getParameter("entry_sheet_ID");
		int es_id = Integer.parseInt(wk_name[1]);

		int i = 0;
		int j = 1;

		for(i=0;i<10;i++){

			item_name[i] = request.getParameter("ITEM_NAME"+j) ;

			if(item_name[i] != null && item_name[i] != "" &&request.getParameter("START_PRICE"+j) != null &&request.getParameter("START_PRICE"+j) != ""
					 && request.getParameter("SALE_PRICE"+j) != null  && request.getParameter("SALE_PRICE"+j) != ""){

				item_no[i] = j;
				start_price[i] = Integer.parseInt(request.getParameter("START_PRICE"+j)) ;
				sale_price[i] = Integer.parseInt(request.getParameter("SALE_PRICE"+j)) ;
				r_chk[i] = request.getParameter("RETFLG"+ j) != null;//返却有

			}else{
				break;
			}

			if(0 == i){
				EntrySheet es = new EntrySheet(es_id,name_id);
				es.setIsExistData();
				es.save();
			}

			if(EntrySheet.findByESID(es_id).size() != 0 && Item.findByESID(es_id).size() == 0){
				new Item(es_id,item_no[i],item_name[i],start_price[i],sale_price[i],1,r_chk[i],start_price[i],false).save();
			}


			j++;
		}
		doGet(request, response);
	}

}
