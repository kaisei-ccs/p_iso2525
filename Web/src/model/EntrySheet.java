package model;

public class EntrySheet{
	private int esID;
	private int sID;

	public EntrySheet(){
		esID	= 0;
		sID		= 0;
	}

	public EntrySheet(int esID,int sID){
		this.esID	= esID;
		this.sID	= sID;
	}

	public int getESID(){
		return esID;
	}
	public void setESID(int esID){
		this.esID = esID;
	}

	public int getSID(){
		return sID;
	}
	public void setSID(int sID){
		this.sID = sID;
	}
}
