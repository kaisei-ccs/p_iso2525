package common;

public class BaseActiveRecord{

	private boolean isExistData;

	public BaseActiveRecord(){
		isExistData = false;
	}

	public boolean save(){
		return false;
	}

	public void delete(){

	}

	public void setIsExistData(){
		isExistData = true;
	}
	protected boolean getIsExistData(){
		return isExistData;
	}

}
