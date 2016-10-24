package model;

public class TradeDetail {
	private int tID;
	private int tNO;
	private int esID;
	private int iNO;
	private int price;
	private int quantity;

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



}
