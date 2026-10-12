package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentDAO{

	  //MYSQLとの接続。
	  //意図しない書き換えを防ぎたいString変数のURLを設定。"jdbc:mysql://localhost:3306/sampledb";をURLに代入する。
	  private final String URL = "jdbc:mysql://localhost:3306/sampledb";
	  private final String USER = "root";
	  private final String PASS = "root";
	  private Connection con = null;
	  
	  //Connectのコンストラクタ
	  public void connect(){
	  
	  //DBに接続
	     try{
	    	 //MYSQLに接続
	   Class.forName("com.mysql.cj.jdbc.Driver"); 
	     //Connectionの値を習得。値をconへ格納
	     con = DriverManager.getConnection(URL,USER,PASS);
	     
	     }catch(Exception e){
	     
	     //
	      e.printStackTrace();
	    }
	 }
	 
	 
	  public String  select(int stuNO){
		  
		     //stmt rsの値の設定。null
		     Statement stmt = null;
		     ResultSet rs = null;
		     String result = null;
		     
		     String sql = "SELECT name FROM student WHERE no = " + stuNO;
		     
	     try{
	     //ステートメントの生成
	     
	     stmt = con.createStatement();
	     //SQLの実行
	     rs = stmt.executeQuery(sql);
	     //検索結果
	     rs.next();
	     result = rs.getString("name");
	     }
	     catch(Exception e){
	       
	       e.printStackTrace();
	       
	       }finally{
	       
	       try{
	       if(rs != null)rs.close();
	       if(stmt != null)stmt.close();
	       }catch(Exception e){
	         e.printStackTrace();
	       }
	     }
	      return result;
	    }
	    public void disconnect(){
	      try{
	        if(con != null)con.close();
	        }catch(Exception e){
	          e.printStackTrace();
	       }
	     			
		}
	   }