package model;

public class Scan{
	private int esID;
	private int iID;

	public Scan(){
		esID	= 0;
		iID		= 0;
	}

	public Scan(int esID,int iID){
		this.esID	= esID;
		this.iID	= iID;
	}

	public int getESID(){
		return esID;
	}
	public void setESID(int esID){
		this.esID = esID;
	}

	public int getSID(){
		return iID;
	}
	public void setSID(int iID){
		this.iID = iID;
	}

	public void setBarcodeData(String BarcodeData){
		String[] data = BarcodeData.split("\t",0);
		esID = Integer.valueOf(data[0]).intValue();
		iID = Integer.valueOf(data[1]).intValue();
	}
}
