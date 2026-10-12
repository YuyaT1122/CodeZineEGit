package objects;

import java.io.Serializable;
import java.sql.Timestamp;

public class Products implements Serializable{
    private Integer _ProductNumber;
    
	 private String _ProductName;
	 /**
	  * 商品名
	  */
	 private Integer _ProductPrice;
	 /**
	  * 商品価格
	  */
	 private Timestamp _DeliveryDate;

	
	
	public Products() {
	}
	/**
	  * 配達日
	  */
	  
	  //アクセスメソッド
	  public Integer getProductNumber() {
	  return _ProductNumber;
	  }
	  public void setProductNumber(Integer ProductNumber){
	  _ProductNumber = ProductNumber;
	  }
	  public String getProductName() {
	  return _ProductName;
	  }
	  public void setProductName(String ProductName){
	  _ProductName = ProductName;
	  }
	  public Integer getProductPrice() {
	  return _ProductPrice;
	  }
	  public void setProductPrice(Integer ProductPrice){
	  _ProductPrice = ProductPrice;
	  }
	  public Timestamp getDeliveryDate(){
	  return _DeliveryDate;
	  }
	  public void setDeliveryDate(Timestamp DeliveryDate){
	  _DeliveryDate = DeliveryDate;
	  }

}