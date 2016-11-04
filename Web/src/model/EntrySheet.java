package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.BaseActiveRecord;
import common.DB_Interface;

public class EntrySheet extends BaseActiveRecord{
	private final int esID;
	private int sID;

	public EntrySheet(){
		esID	= 0;
		sID		= 0;
	}

	public EntrySheet(int esID,int sID){
		this.esID	= esID;
		this.sID	= sID;
	}

	public int getESID(){
		return esID;
	}

	public int getSID(){
		return sID;
	}

	public void setSID(int sID){
		this.sID = sID;
	}


	//保存処理
	public boolean save(){
		Connection con = DB_Interface.getInstance().getConnection();
		PreparedStatement ps = null;
		String sql;

		try{
			if(false == getIsExistData()){
				sql = "insert into ENTRYSHEET values(?,?)";
				ps = con.prepareStatement(sql);
				ps.setInt(1, esID);
				ps.setInt(2, sID);
			}else{
				return false;
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
			sql = "delete from ENTRYSHEET where ES_ID=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, esID);

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

	private static ArrayList<EntrySheet> executeSelectQuery(String sql){
		Connection con = DB_Interface.getInstance().getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<EntrySheet> retList = new ArrayList<EntrySheet>();

		try{
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while(rs.next()){
				EntrySheet tmp = new EntrySheet(
						rs.getInt("ES_ID"),
						rs.getInt("S_ID")
						);
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
	public static ArrayList<EntrySheet> fetchAll(){
		return executeSelectQuery("select * from ENTRYSHEET");
	}
	public static ArrayList<EntrySheet> findByESID(int esID){
		return executeSelectQuery("select * from ENTRYSHEET where ES_ID=" + esID);
	}
	public static ArrayList<EntrySheet> findBySID(int sID){
		return executeSelectQuery("select * from ENTRYSHEET where S_ID=" + sID);
	}


}
