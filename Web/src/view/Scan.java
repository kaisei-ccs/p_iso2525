package view;

import java.io.IOException;
import java.util.ArrayList;

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
	ArrayList<model.Scan> scan;
	private int postData1;
	private int postData2;
	private int es_Id;
	private int i_Id;
	private static final long serialVersionUID = 1L;

    public Scan() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postData = request.getParameter("postData");
		String[] split = postData.split("\t");
		postData1 = Integer.parseInt(split[0]);
		postData2 = Integer.parseInt(split[1]);

		//utf8にする
		response.setContentType("text/html; charset=UTF-8");

		//読み込まれたデータがDBのscanの中に既にある場合追加しないようにする
		boolean collationFrg = false;
		scan = model.Scan.fetchAll();
		for(int i = 0 ; i < scan.size(); i++){
			es_Id = scan.get(i).getESID();
			i_Id = scan.get(i).getINO();
			if(postData1 == es_Id && postData2 == i_Id){
				collationFrg = true;
			}
		}
		if(collationFrg == false){
			new model.Scan(postData1,postData2).save();
		}else{
			response.getWriter().append("重複スキャンです::");
		}

		//商品名取得
		Item item = Item.findByBarcodeData(postData1, postData2).get(0);
		response.getWriter().append(item.getName());
	}

}
