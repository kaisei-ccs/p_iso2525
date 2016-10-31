package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.BaseActiveRecord;
import common.DB_Interface;

public class Scan extends BaseActiveRecord{
	private final int esID;
	private final int iNO;

	public Scan(){
		esID	= 0;
		iNO		= 0;
	}

	public Scan(int esID,int iID){
		this.esID	= esID;
		this.iNO	= iID;
	}

	public Scan(String BarcodeData){
		String[] data = BarcodeData.split("\t",0);
		esID = Integer.valueOf(data[0]).intValue();
		iNO = Integer.valueOf(data[1]).intValue();
	}

	public int getESID(){
		return esID;
	}

	public int getINO(){
		return iNO;
	}

	//保存処理
	public boolean save(){
		Connection con = DB_Interface.getInstance().getConnection();
		PreparedStatement ps = null;
		String sql;

		try{
			if(false == getIsExistData()){
				sql = "insert into SCAN values(?,?)";
				ps = con.prepareStatement(sql);
				ps.setInt(1, esID);
				ps.setInt(2, iNO);

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
			sql = "delete from SCAN where ES_ID=? and I_NO=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, esID);
			ps.setInt(2, iNO);


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

	private static ArrayList<Scan> executeSelectQuery(String sql){
		Connection con = DB_Interface.getInstance().getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<Scan> retList = new ArrayList<Scan>();

		try{
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while(rs.next()){
				Scan tmp = new Scan(
						rs.getInt("ES_ID"),
						rs.getInt("I_NO"));
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
	public static ArrayList<Scan> fetchAll(){
		return executeSelectQuery("select * from SCAN");
	}
	public static ArrayList<Scan> findByESID(int esID){
		return executeSelectQuery("select * from SCAN where T_ID=" + esID);
	}
	public static ArrayList<Scan> findByINO(int iNO){
		return executeSelectQuery("select * from SCAN where I_NO=" + iNO);
	}

}
