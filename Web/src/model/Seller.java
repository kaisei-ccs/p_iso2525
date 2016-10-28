package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.BaseActiveRecord;
import common.DB_Interface;

public class Seller extends BaseActiveRecord{
	private final int sID;
	private String sellerName;
	private String sellerKana;

	public Seller() {
		sID			= 0;
		sellerName	= "";
		sellerKana	= "";
	}

	public Seller(int sID,String sellerName,String sellerKana) {
		this.sID			= sID;
		this.sellerName		= sellerName;
		this.sellerKana		= sellerKana;
	}

	public int getSID(){
		return sID;
	}

	public String getSellerName(){
		return sellerName;
	}
	public void setSellerName(String sellerName){
		this.sellerName = sellerName;
	}

	public String getSellerKana(){
		return sellerKana;
	}
	public void setSellerKana(String sellerKana){
		this.sellerKana = sellerKana;
	}


	//保存処理
	public boolean save(){
		Connection con = DB_Interface.getInstance().getConnection();
		PreparedStatement ps = null;
		String sql;

		try{
			if(false == getIsExistData()){
				sql = "insert into SELLER values(?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setInt(1, sID);
				ps.setString(2, sellerName);
				ps.setString(3, sellerKana);

			}else{
				sql = "update SELLER set SELLERNAME=?, SELLERKANA=? where S_ID=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, sellerName);
				ps.setString(2, sellerKana);
				ps.setInt(3, sID);
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
			sql = "delete from SELLER where ES_ID=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, sID);


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

	private static ArrayList<Seller> executeSelectQuery(String sql){
		Connection con = DB_Interface.getInstance().getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<Seller> retList = new ArrayList<Seller>();

		try{
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while(rs.next()){
				Seller tmp = new Seller(
						rs.getInt("S_ID"),
						rs.getString("SELLERNAME"),
						rs.getString("SELLERKANA"));
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
	public static ArrayList<Seller> fetchAll(){
		return executeSelectQuery("select * from SELLER");
	}
	public static ArrayList<Seller> findBySID(int sID){
		return executeSelectQuery("select * from SELLER where S_ID=" + sID);
	}
	public static ArrayList<Seller> findBySellerName(int sellerName){
		return executeSelectQuery("select * from SELLER where SELLERNAME=" + sellerName);
	}
	public static ArrayList<Seller> findBySellerKana(int sellerKana){
		return executeSelectQuery("select * from SELLER where SELLERKANA=" + sellerKana);
	}
}
