package model;

public class Item{
	private int esID;
	private int iNO;
	private String name;
	private int sPrice;
	private int ePrice;
	private int quantity;
	private boolean retFlg;
	private int price;
	private boolean printFlg;

	public Item(){
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

	public Item(int esID,int iNO,String name,int sPrice,int ePrice,int quantity,boolean retFlg, int price) {
		this.esID		= esID;
		this.iNO		= iNO;
		this.name		= name;
		this.sPrice		= sPrice;
		this.ePrice		= ePrice;
		this.quantity	= quantity;
		this.retFlg		= retFlg;
		this.price		= price;
		this.printFlg	= false;
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

}
