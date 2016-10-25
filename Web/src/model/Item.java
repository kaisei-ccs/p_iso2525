package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.BaseActiveRecord;
import common.DB_Interface;

public class Item extends BaseActiveRecord{
	private final int esID;
	private final int iNO;
	private String name;
	private int sPrice;
	private int ePrice;
	private int quantity;
	private boolean retFlg;
	private int price;
	private boolean printFlg;

	private DB_Interface dbi;

	public Item(){
		dbi = DB_Interface.getInstance();
		esID		= 0;
		iNO			= 0;
		name		= "";
		sPrice		= 0;
		ePrice		= 0;
		quantity	= 0;
		retFlg		= false;
		price		= 0;
		printFlg	= false;
	}

	public Item(int esID,int iNO,String name,int sPrice,int ePrice,int quantity,boolean retFlg, int price,boolean printFlg) {
		dbi = DB_Interface.getInstance();
		this.esID		= esID;
		this.iNO		= iNO;
		this.name		= name;
		this.sPrice		= sPrice;
		this.ePrice		= ePrice;
		this.quantity	= quantity;
		this.retFlg		= retFlg;
		this.price		= price;
		this.printFlg	= printFlg;
	}

	public int getESID(){
		return esID;
	}

	public int getINO(){
		return iNO;
	}

	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}

	public int getSPrice(){
		return sPrice;
	}
	public void setSPrice(int sPrice){
		this.sPrice = sPrice;
	}

	public int getEPrice(){
		return ePrice;
	}
	public void setEPrice(int ePrice){
		this.ePrice = ePrice;
	}

	public int getQuantity(){
		return quantity;
	}
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public boolean getRetFlg(){
		return retFlg;
	}
	public void setRetFlg(boolean retFlg){
		this.retFlg = retFlg;
	}

	public int getPrice(){
		return price;
	}
	public void setPrice(int price){
		this.price = price;
	}

	public boolean getPrintFlg(){
		return printFlg;
	}
	public void setPrintFlg(boolean printFlg){
		this.printFlg = printFlg;
	}

	public String getBarcodeData(){
		return Integer.toString(esID) + "\t" + Integer.toString(iNO);
	}

	//保存処理
	public boolean save(){
		Connection con = dbi.getConnection();
		PreparedStatement ps = null;
		String sql;

		try{
			if(false == getIsExistData()){
				sql = "insert into ITEM values(?,?,?,?,?,?,?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setInt(1, esID);
				ps.setInt(2, iNO);
				ps.setString(3, name);
				ps.setInt(4, sPrice);
				ps.setInt(5, ePrice);
				ps.setInt(6, quantity);
				ps.setBoolean(7, retFlg);
				ps.setInt(8, price);
				ps.setBoolean(9, printFlg);

			}else{
				sql = "update ITEM set NAME=?, S_PRICE=?, E_PRICE=?, QUANTITY=?, RETFLG=?, PRICE=?, PRINTFLG=? where ES_ID=? and  I_NO=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, name);
				ps.setInt(2, sPrice);
				ps.setInt(3, ePrice);
				ps.setInt(4, quantity);
				ps.setBoolean(5, retFlg);
				ps.setInt(6, price);
				ps.setBoolean(7, printFlg);
				ps.setInt(8, esID);
				ps.setInt(9, iNO);

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
			sql = "delete from ITEM where ES_ID=? and I_NO=?";
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

	private ArrayList<Item> executeSelectQuery(String sql){
		Connection con = dbi.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<Item> retList = new ArrayList<Item>();

		try{
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while(rs.next()){
				Item tmp = new Item(
						esID,
						iNO,
						name,
						sPrice,
						ePrice,
						quantity,
						retFlg,
						price,
						printFlg);
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
	public ArrayList<Item> fetchAll(){
		return executeSelectQuery("select * from ITEM");
	}
	public ArrayList<Item> findByESID(int esID){
		return executeSelectQuery("select * from ITEM where ES_ID=" + esID);
	}
	public ArrayList<Item> findByIID(int iNO){
		return executeSelectQuery("select * from ITEM where I_NO=" + iNO);
	}
	public ArrayList<Item> findByName(String name){
		return executeSelectQuery("select * from ITEM where NAME=" + name);
	}
	public ArrayList<Item> findBySPrice(int sPrice){
		return executeSelectQuery("select * from ITEM where S_PRICE=" + sPrice);
	}
	public ArrayList<Item> findByEPrice(int ePrice){
		return executeSelectQuery("select * from ITEM where E_PRICE=" + ePrice);
	}
	public ArrayList<Item> findByQuantity(int quantity){
		return executeSelectQuery("select * from ITEM where QUANTITY=" + quantity);
	}
	public ArrayList<Item> findByRetFlg(boolean retFlg){
		return executeSelectQuery("select * from ITEM where RETFLG=" + retFlg);
	}
	public ArrayList<Item> findByPrice(int price){
		return executeSelectQuery("select * from ITEM where PRICE=" + price);
	}
	public ArrayList<Item> findByPrintFlg(boolean printFlg){
		return executeSelectQuery("select * from ITEM where PRINTFLG=" + printFlg);
	}


}
