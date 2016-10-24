package model;

public class Account {
	private int aID;
	private String host;
	private String pw;
	private int permission;

	public Account(){
		aID			= 0;
		host			= "";
		pw				= "";
		permission		= 0;
	}

	public Account(int aID,String host,String pw,int permission){
		this.aID			= aID;
		this.host			= host;
		this.pw				= pw;
		this.permission		= permission;
	}

	public int getAID(){
		return aID;
	}
	public void setAID(int aID){
		this.aID = aID;
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



}
