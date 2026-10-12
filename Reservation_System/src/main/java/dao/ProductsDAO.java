package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import objects.Products;
import objects.ProductsDTO;

public class ProductsDAO {

	  private final String URL = "jdbc:mysql://localhost:3306/reservation_system";
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
			 
	 public ProductsDTO select(){
			 
			   Statement stmt = null;
			   ResultSet rs = null;
			   ProductsDTO pdto = new ProductsDTO();
			   
			 String sql = "SELECT * FROM product";
			 
			 try{
			   connect();
			   
			   stmt = con.createStatement();
			   
			   rs = stmt.executeQuery(sql);
			   
			   //rsの繰り返し処理
			   while(rs.next()){
			  //の新しいインスタンスを生成し、番号、名前、価格、配達日を設定する
			      Products pd = new Products();
			      pd.setProductNumber(rs.getInt("PRODUCT_NUMBER"));
			      pd.setProductName(rs.getString("PRODUCT_NAME"));
			      pd.setProductPrice(rs.getInt("PRODUCT_PRICE"));
			      pd.setDeliveryDate(rs.getTimestamp("DELIVERING_DATE"));
			      
			      
			  //設定した番号、名前、価格、配達日をpdtoに追加
			      pdto.add(pd);
			  }
			  }catch(Exception e){
			   e.printStackTrace();
			 }finally{
			  
			  //close処理
			   try{
			     //rsがnullなら閉じる
			     if(rs != null)rs.close();
			     //stmtがnullなら閉じる
			     if(stmt != null)stmt.close();
			 
			 }catch(Exception e){
			  
			  e.printStackTrace();
			}
		}
		disconnect();
		return pdto;
		}
	 
	 
	 
	 // 商品IDで1件検索する
	 public Products findByProductNumber(int productNumber){

		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 Products pd = null;

		 String sql = "SELECT * FROM product WHERE PRODUCT_NUMBER = ?";

		 try{
			 connect();

			 pstmt = con.prepareStatement(sql);
			 pstmt.setInt(1, productNumber);

			 rs = pstmt.executeQuery();

			 if(rs.next()){
				 pd = new Products();
				 pd.setProductNumber(rs.getInt("PRODUCT_NUMBER"));
				 pd.setProductName(rs.getString("PRODUCT_NAME"));
				 pd.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				 pd.setDeliveryDate(rs.getTimestamp("DELIVERING_DATE"));
			 }

		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 try{
				 if(rs != null) rs.close();
				 if(pstmt != null) pstmt.close();
			 }catch(Exception e){
				 e.printStackTrace();
			 }
		 }
		 disconnect();
		 return pd;
	 }

	 public void disconnect(){
		  
		  try{
		  
		  if(con != null)con.close();
		  
		  }catch(Exception e){
		  
		   e.printStackTrace();
		}
}

	
}
