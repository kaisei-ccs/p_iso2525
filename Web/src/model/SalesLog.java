package model;

import java.sql.Date;
import java.sql.Time;

public class SalesLog {
	private int tID;
	private Date tDate;
	private Time tTime;
	private int total;
	private int chsrge;
	private int cashBack;

	public SalesLog(){
		tID			= 0;
		tDate		= null;
		tTime		= null;
		total		= 0;
		chsrge		= 0;
		cashBack	= 0;
	}

	public SalesLog(int tID,Date tDate,Time tTime,int total,int chsrge,int cashBack){
		this.tID		= tID;
		this.tDate		= tDate;
		this.tTime		= tTime;
		this.total		= total;
		this.chsrge		= chsrge;
		this.cashBack	= cashBack;
	}

	public int getTID(){
		return tID;
	}
	public void setTID(int tID){
		this.tID = tID;
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

	public int getChsrge(){
		return chsrge;
	}
	public void setChsrge(int chsrge){
		this.chsrge = chsrge;
	}

	public int getCashBack(){
		return cashBack;
	}
	public void setCashBack(int cashBack){
		this.cashBack = cashBack;
	}
}
