package view;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Create_QR;
import model.Item;

@WebServlet("/Print_PriceTag")
public class Print_PriceTag extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FD_PATH = "/WEB-INF/jsp/Print_PriceTag.jsp";
	private String path;

    public Print_PriceTag() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		path = getServletContext().getRealPath("/");
		RequestDispatcher dispatcher = request.getRequestDispatcher(FD_PATH);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//追加ボタンでPRINTFLGをFALSEに変える処理
		if(request.getParameter("addition") != null && request.getParameter("addition").equals("追加")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Print_PriceTag.jsp");
			System.out.println("出品表番号" + request.getParameter("ESID_text"));
			System.out.println("項番" + request.getParameter("INO_text"));
			Item printFlg = Item.findByBarcodeData(Integer.parseInt(request.getParameter("ESID_text")), Integer.parseInt(request.getParameter("INO_text"))).get(0);
			printFlg.setPrintFlg(false);
			printFlg.save();
			dispatcher.forward(request, response);
		}
		//印刷ボタンでPrint/Print_PriceTag.jspを呼び出す
		if(request.getParameter("print") != null && request.getParameter("print").equals("印刷")){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Print/Print_PriceTag.jsp");
			ArrayList<Item> priceList = Item.findByPrintFlg(false);
			for(Item item: priceList){
				String contents = String.valueOf(item.getESID()) + "\t" + String.valueOf(item.getINO());
				new Create_QR(contents, 100, 100, path);
			}
			request.setAttribute("priceList", priceList);
			dispatcher.forward(request, response);
		}
		//印刷終了時にPRINTFLGをTRUEに変える処理（※印刷キャンセル時にも実行される）
		if(request.getParameter("Confirm") != null && request.getParameter("Confirm").equals("確定")){
			ArrayList<Item> ItemList = Item.findByPrintFlg(false);
			Item printFlg;

			File filedir = new File(path+"img");
			File[] files = filedir.listFiles();
			for(File file : files){
				file.delete();
			}
			for(Item item : ItemList){
				printFlg = Item.findByBarcodeData(item.getESID(), item.getINO()).get(0);
				printFlg.setPrintFlg(true);
				printFlg.save();
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Print_PriceTag.jsp");
			dispatcher.forward(request, response);
		}
	}

}
