package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.BaseActiveRecord;
import common.DB_Interface;

public class TradeDetailItem extends BaseActiveRecord{
	private int tNO;
	private int tID;
	private int esID;
	private int iNO;
	private String name;
	private int price;
	private int quantity;


	public TradeDetailItem(){
		tID = 0;
		tNO = 0;
		esID = 0;
		iNO = 0;
		name= "";
		price = 0;
		quantity = 0;
	}

	public TradeDetailItem(int tID, int tNO, int esID, int iNO, String name, int price, int quantity){
		this.tID = tID;
		this.tNO = tNO;
		this.esID = esID;
		this.iNO = iNO;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public int getTID(){
		return tID;
	}
	public void setTID(int tID){
		this.tID = tID;
	}

	public int getTNO(){
		return tNO;
	}
	public void setTNO(int tNO){
		this.tNO = tNO;
	}

	public int getESID(){
		return esID;
	}
	public void setESID(int esID){
		this.esID = esID;
	}

	public int getINO(){
		return iNO;
	}
	public void setINO(int iNO){
		this.iNO = iNO;
	}

	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}

	public int getPrice(){
		return price;
	}
	public void setPrice(int price){
		this.price = price;
	}

	public int getQuantity(){
		return quantity;
	}
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	private static ArrayList<TradeDetailItem> executeSelectQuery(String sql){
		Connection con = DB_Interface.getInstance().getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<TradeDetailItem> retList = new ArrayList<TradeDetailItem>();

		try{
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while(rs.next()){
				TradeDetailItem tmp = new TradeDetailItem(
						rs.getInt("T_ID"),
						rs.getInt("T_NO"),
						rs.getInt("ES_ID"),
						rs.getInt("I_NO"),
						rs.getString("NAME"),
						rs.getInt("PRICE"),
						rs.getInt("QUANTITY"));
				tmp.setIsExistData();
				retList.add(tmp);
				}

		}catch (SQLException e){
			e.printStackTrace();
		}finally{
			if(ps != null){
				try{
					ps.close();
				}catch (SQLException e){
					e.printStackTrace();
				}
			}
		}

		return retList;
	}

	//取得
	public static ArrayList<TradeDetailItem> fetchAll(){
		String sql = "select * "
				+ "from TRADEDETAIL INNER JOIN ITEM ON TRADEDETAIL.ES_ID = ITEM.ES_ID AND TRADEDETAIL.I_NO = ITEM.I_NO ";
		return executeSelectQuery(sql);
	}
	public static ArrayList<TradeDetailItem> findByTID(int tID){
		String sql = "select * "
					+ "from TRADEDETAIL INNER JOIN ITEM ON TRADEDETAIL.ES_ID = ITEM.ES_ID AND TRADEDETAIL.I_NO = ITEM.I_NO "
					+ "where TRADEDETAIL.T_ID=" + tID;
		return executeSelectQuery(sql);
	}
	public static ArrayList<TradeDetailItem> findByTNO(int tNO){
		String sql = "select * "
				+ "from TRADEDETAIL INNER JOIN ITEM ON TRADEDETAIL.ES_ID = ITEM.ES_ID AND TRADEDETAIL.I_NO = ITEM.I_NO "
				+ "where TRADEDETAIL.T_NO=" + tNO;
		return executeSelectQuery(sql);
	}
	public static ArrayList<TradeDetailItem> findByESID(int esID){
		String sql = "select * "
				+ "from TRADEDETAIL INNER JOIN ITEM ON TRADEDETAIL.ES_ID = ITEM.ES_ID AND TRADEDETAIL.I_NO = ITEM.I_NO "
				+ "where TRADEDETAIL.ES_ID=" + esID;
		return executeSelectQuery(sql);
	}
	public static ArrayList<TradeDetailItem> findByINO(int iNO){
		String sql = "select * "
				+ "from TRADEDETAIL INNER JOIN ITEM ON TRADEDETAIL.ES_ID = ITEM.ES_ID AND TRADEDETAIL.I_NO = ITEM.I_NO "
				+ "where TRADEDETAIL.I_NO=" + iNO;
		return executeSelectQuery(sql);
	}
	public static ArrayList<TradeDetailItem> findByPrice(int price){
		String sql = "select * "
				+ "from TRADEDETAIL INNER JOIN ITEM ON TRADEDETAIL.ES_ID = ITEM.ES_ID AND TRADEDETAIL.I_NO = ITEM.I_NO "
				+ "where TRADEDETAIL.PRICE=" + price;
		return executeSelectQuery(sql);
	}
	public static ArrayList<TradeDetailItem> findByQuantity(int quantity){
		String sql = "select * "
				+ "from TRADEDETAIL INNER JOIN ITEM ON TRADEDETAIL.ES_ID = ITEM.ES_ID AND TRADEDETAIL.I_NO = ITEM.I_NO "
				+ "where TRADEDETAIL.QUANTITY=" + quantity;
		return executeSelectQuery(sql);
	}
	public static ArrayList<TradeDetailItem> findByESIDINO(int esID, int iNo){
		String sql = "select * "
				+ "from TRADEDETAIL INNER JOIN ITEM ON TRADEDETAIL.ES_ID = ITEM.ES_ID AND TRADEDETAIL.I_NO = ITEM.I_NO "
				+ "where TRADEDETAIL.ES_ID=" + esID+ " AND TRADEDETAIL.I_NO="+iNo;
		return executeSelectQuery(sql);
	}
	public static TradeDetailItem findByMaxID(){
		String sql = "select * "
				+ "from TRADEDETAIL INNER JOIN ITEM ON TRADEDETAIL.ES_ID = ITEM.ES_ID AND TRADEDETAIL.I_NO = ITEM.I_NO "
				+ "where TRADEDETAIL.T_ID = (select max(t_id) as maxNo  from TRADEDETAIL)";
		return executeSelectQuery(sql).get(0);
	}

}
