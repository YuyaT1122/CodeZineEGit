package objects;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class User {
	   /**
	   * ユーザーID
	   */
	   private Integer _id;
	   /**
	   * パスワード
	   */
	   private String _password;
	    /**
	   * 名前
	   */
	   private String _name;
	    /**
	   * eメール
	   */
	   private String _email;
	    /**
	   * 性別
	   */
	   private String _gender;
	    /**
	   */
	  
	   private Integer _authority;
	   
	   
	   //アクセスメソッド
	   
	   public Integer getId(){
	    return _id;
	   }
	   public void setId(Integer id){
	     _id = id;
	   }
	   public String getPassword(){
	    return _password;
	   }
	   public void setPassword(String password){
	    _password = password;
	   }
	   public String getName(){
	   return _name;
	   }
	   public void setName(String name){
	    _name = name;
	   }
	   public void setEmail(String Email){
	     _email = Email;
	   }
	   public String getEmail(){
	    return _email;
	   }
	   public void setGender(String gender){
	     _gender = gender;
	   }
	   public String getgender(){
	    return _gender;
	   }
	   public Integer getAuthority(){
     return _authority;
	   }
	    public void setAuthority(Integer authority){
	     _authority = authority;
		   }
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO 自動生成されたメソッド・スタブ
		
	} 
}