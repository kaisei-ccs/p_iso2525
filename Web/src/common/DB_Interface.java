package common;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DB_Interface {
	//singletonパターン用
	private static DB_Interface DBI = new DB_Interface();

	//DB
	private Connection con;
	
	private DB_Interface(){
		String dbName = null;
		String dbUser = null;
		String dbPass = null;

		//プロパティから接続DB情報取得
		InputStream is = getClass().getClassLoader().getResourceAsStream("db.properties");
		Properties prop = new Properties();
		try{
			prop.load(is);
			dbName = prop.getProperty("db.name");
			dbUser = prop.getProperty("db.user");
			dbPass = prop.getProperty("db.pass");
		}catch (IOException e){
			e.printStackTrace();
		}finally{
			if(is != null){
				try{
					is.close();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		
		//SQLServerに接続
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(
					"jdbc:sqlserver://localhost\\SQLEXPRESS;database="+ dbName +";",
					dbUser,dbPass);
		}catch (SQLException e){
			e.printStackTrace();
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		
	}
	
	public static DB_Interface getInstance(){
		return DBI;
	}
	
	public Connection getConnection(){
		return con;
	}
	
}
