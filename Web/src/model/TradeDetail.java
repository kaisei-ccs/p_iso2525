package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.BaseActiveRecord;
import common.DB_Interface;

public class TradeDetail extends BaseActiveRecord{
	private int tNO;
	private int tID;
	private int esID;
	private int iNO;
	private int price;
	private int quantity;

	public TradeDetail(){
		tID = 0;
		tNO = 0;
		esID = 0;
		iNO = 0;
		price = 0;
		quantity = 0;
	}

	public TradeDetail(int tID, int tNO, int esID, int iNO, int price, int quantity){
		this.tID = tID;
		this.tNO = tNO;
		this.esID = esID;
		this.iNO = iNO;
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

	//保存処理
	public boolean save(){
		Connection con = DB_Interface.getInstance().getConnection();
		PreparedStatement ps = null;
		String sql;

		try{
			if(false == getIsExistData()){
				sql = "insert into TRADEDETAIL values(?,?,?,?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setInt(1, tID);
				ps.setInt(2, tNO);
				ps.setInt(3, esID);
				ps.setInt(4, iNO);
				ps.setInt(5, price);
				ps.setInt(6, quantity);

			}else{
				sql = "update TRADEDETAIL set ES_ID=?, I_NO=?, PRICE=?, QUANTITY=? where T_ID=? and T_NO=?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, esID);
				ps.setInt(2, iNO);
				ps.setInt(3, price);
				ps.setInt(4, quantity);
				ps.setInt(5, tID);
				ps.setInt(6, tNO);
			}

			ps.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
			return false;
		}finally{
			if(ps != null){
				try{
					ps.close();
				}catch (SQLException e){
					e.printStackTrace();
				}
			}
		}
		setIsExistData();
		return true;
	}

	//削除処理
	public void delete(){
		Connection con = DB_Interface.getInstance().getConnection();
		PreparedStatement ps = null;
		String sql;


		try{
			sql = "delete from TRADEDETAIL where T_ID=? and T_NO=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, tID);
			ps.setInt(2, tNO);


			ps.executeUpdate();
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
	}

	private static ArrayList<TradeDetail> executeSelectQuery(String sql){
		Connection con = DB_Interface.getInstance().getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<TradeDetail> retList = new ArrayList<TradeDetail>();

		try{
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while(rs.next()){
				TradeDetail tmp = new TradeDetail(
						rs.getInt("T_ID"),
						rs.getInt("T_NO"),
						rs.getInt("ES_ID"),
						rs.getInt("I_NO"),
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
	public static ArrayList<TradeDetail> fetchAll(){
		return executeSelectQuery("select * from TRADEDETAIL");
	}
	public static ArrayList<TradeDetail> findByTID(int tID){
		return executeSelectQuery("select * from TRADEDETAIL where T_ID=" + tID);
	}
	public static ArrayList<TradeDetail> findByTNO(int tNO){
		return executeSelectQuery("select * from TRADEDETAIL where T_NO=" + tNO);
	}
	public static ArrayList<TradeDetail> findByESID(int esID){
		return executeSelectQuery("select * from TRADEDETAIL where ES_ID=" + esID);
	}
	public static ArrayList<TradeDetail> findByINO(int iNO){
		return executeSelectQuery("select * from TRADEDETAIL where I_NO=" + iNO);
	}
	public static ArrayList<TradeDetail> findByPrice(int price){
		return executeSelectQuery("select * from TRADEDETAIL where PRICE=" + price);
	}
	public static ArrayList<TradeDetail> findByQuantity(int quantity){
		return executeSelectQuery("select * from TRADEDETAIL where QUANTITY=" + quantity);
	}
	public static ArrayList<TradeDetail> findByESIDINO(int esID, int iNo){
		return executeSelectQuery("select * from TRADEDETAIL where ES_ID=" + esID+ " AND I_NO="+iNo);
	}
	public static TradeDetail findByMaxID(){
		return executeSelectQuery("select * from TRADEDETAIL WHERE T_ID = (select max(t_id) as maxNo  from TRADEDETAIL)").get(0);
	}

}
