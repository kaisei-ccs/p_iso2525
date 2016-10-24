package model;

public class Seller {
	private int sID;
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
	public void setSID(int sID){
		this.sID = sID;
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
}
