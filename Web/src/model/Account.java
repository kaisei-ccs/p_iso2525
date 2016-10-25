package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.BaseActiveRecord;
import common.DB_Interface;

public class Account extends BaseActiveRecord{
	private final int aID;
	private String host;
	private String pw;
	private int permission;

	private DB_Interface dbi;

	public Account(){
		super();
		dbi = DB_Interface.getInstance();
		aID				= 0;
		host			= "";
		pw				= "";
		permission		= 0;
	}

	public Account(int aID,String host,String pw,int permission){
		super();
		dbi = DB_Interface.getInstance();
		this.aID			= aID;
		this.host			= host;
		this.pw				= pw;
		this.permission		= permission;
	}

	public int getAID(){
		return aID;
	}

	public String getHost(){
		return host;
	}
	public void setHost(String host){
		this.host = host;
	}

	public String getPW(){
		return pw;
	}
	public void setPW(String pw){
		this.pw = pw;
	}

	public int getPermission(){
		return permission;
	}
	public void setPermission(int permission){
		this.permission = permission;
	}

	//保存処理
	public boolean save(){
		Connection con = dbi.getConnection();
		PreparedStatement ps = null;
		String sql;

		try{
			if(false == getIsExistData()){
				sql = "insert into ACCOUNT values(?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, host);
				ps.setString(2, pw);
				ps.setInt(3, permission);
			}else{
				sql = "update ACCOUNT set HOST=?, PW=?, PERMISSION=? where A_ID=?";
				ps = con.prepareStatement(sql);
				ps.setInt(4, aID);
				ps.setString(1, host);
				ps.setString(2, pw);
				ps.setInt(3, permission);
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

		return true;
	}

	//削除処理
	public void delete(){
		Connection con = dbi.getConnection();
		PreparedStatement ps = null;
		String sql;


		try{
			sql = "delete from ACCOUNT where A_ID=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, aID);

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

	private ArrayList<Account> executeSelectQuery(String sql){
		Connection con = dbi.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<Account> retList = new ArrayList<Account>();

		try{
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while(rs.next()){
				Account tmp = new Account(
						rs.getInt("A_ID"),
						rs.getString("HOST"),
						rs.getString("PW"),
						rs.getInt("PERMISSION")
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
	public ArrayList<Account> fetchAll(){
		return executeSelectQuery("select * from ACCOUNT");
	}
	public ArrayList<Account> findById(int aID){
		return executeSelectQuery("select * from ACCOUNT where A_ID=" + aID);
	}
	public ArrayList<Account> findByHost(String host){
		return executeSelectQuery("select * from ACCOUNT where HOST=" + host);
	}
	public ArrayList<Account> findByPW(String pw){
		return executeSelectQuery("select * from ACCOUNT where PW=" + pw);
	}
	public ArrayList<Account> findByPermission(int permission){
		return executeSelectQuery("select * from ACCOUNT where PERMISSION=" + permission);
	}


}
