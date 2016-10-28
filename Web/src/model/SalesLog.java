package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import common.BaseActiveRecord;
import common.DB_Interface;

public class SalesLog extends BaseActiveRecord{
	private final int tID;
	private Date tDate;
	private Time tTime;
	private int total;
	private int charge;
	private int cashBack;

	public SalesLog(){
		tID			= 0;
		tDate		= null;
		tTime		= null;
		total		= 0;
		charge		= 0;
		cashBack	= 0;
	}

	public SalesLog(int tID,Date tDate,Time tTime,int total,int charge,int cashBack){
		this.tID		= tID;
		this.tDate		= tDate;
		this.tTime		= tTime;
		this.total		= total;
		this.charge		= charge;
		this.cashBack	= cashBack;
	}

	public int getTID(){
		return tID;
	}

	public Date getTDate(){
		return tDate;
	}
	public void setTDate(Date tDate){
		this.tDate = tDate;
	}

	public Time getTTime(){
		return tTime;
	}
	public void setTTime(Time tTime){
		this.tTime = tTime;
	}

	public int getTotal(){
		return total;
	}
	public void setTotal(int total){
		this.total = total;
	}

	public int getCharge(){
		return charge;
	}
	public void setCharge(int chsrge){
		this.charge = chsrge;
	}

	public int getCashBack(){
		return cashBack;
	}
	public void setCashBack(int cashBack){
		this.cashBack = cashBack;
	}

	//保存処理
	public boolean save(){
		Connection con = DB_Interface.getInstance().getConnection();
		PreparedStatement ps = null;
		String sql;

		try{
			if(false == getIsExistData()){
				sql = "insert into SALESLOG values(?,?,?,?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setInt(1, tID);
				ps.setDate(2, tDate);
				ps.setTime(3, tTime);
				ps.setInt(4, total);
				ps.setInt(5, charge);
				ps.setInt(6, cashBack);

			}else{
				sql = "update SALESLOG set T_DATE=?, T_TIME=?, TOTAL=?, CHARGE=?, CASHBACK=? where T_ID=?";
				ps = con.prepareStatement(sql);
				ps.setDate(1, tDate);
				ps.setTime(2, tTime);
				ps.setInt(3, total);
				ps.setInt(4, charge);
				ps.setInt(5, cashBack);
				ps.setInt(6, tID);

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
			sql = "delete from SALESLOG where T_ID=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, tID);


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

	private static ArrayList<SalesLog> executeSelectQuery(String sql){
		Connection con = DB_Interface.getInstance().getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<SalesLog> retList = new ArrayList<SalesLog>();

		try{
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while(rs.next()){
				SalesLog tmp = new SalesLog(
						rs.getInt("T_ID"),
						rs.getDate("T_DATE"),
						rs.getTime("T_TIME"),
						rs.getInt("TOTAL"),
						rs.getInt("CHARGE"),
						rs.getInt("CASHBACK"));
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
	public static ArrayList<SalesLog> fetchAll(){
		return executeSelectQuery("select * from SALESLOG");
	}
	public static ArrayList<SalesLog> findByTID(int tID){
		return executeSelectQuery("select * from SALESLOG where T_ID=" + tID);
	}
	public static ArrayList<SalesLog> findByTDate(Date tDate){
		return executeSelectQuery("select * from SALESLOG where T_DATE=" + tDate);
	}
	public static ArrayList<SalesLog> findByTTime(Time tTime){
		return executeSelectQuery("select * from SALESLOG where T_TIME=" + tTime);
	}
	public static ArrayList<SalesLog> findByTotal(int total){
		return executeSelectQuery("select * from SALESLOG where TOTAL=" + total);
	}
	public static ArrayList<SalesLog> findByChsrge(int chsrge){
		return executeSelectQuery("select * from SALESLOG where CHSRGE=" + chsrge);
	}
	public static ArrayList<SalesLog> findByCashBack(int cashBack){
		return executeSelectQuery("select * from SALESLOG where CASHBACK=" + cashBack);
	}


}
