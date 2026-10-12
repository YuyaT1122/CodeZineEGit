package objects;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductsDTO implements Serializable{
	
		 
    //Productsを格納したArraylistを作成
     private ArrayList<Products> list;

     
     public ArrayList<Products> getList() {
		return list;
	}
	 public void setList(ArrayList<Products> list) {
		 this.list = list;
	 }
	 //StudentDTOというコンストラクタ
     public ProductsDTO(){
     
     //ArrayListの内容をlistに格納
     list = new ArrayList<Products>();
    }
    //addというコンストラクタを作成
    public void add(Products pd){
    
    list.add(pd);
 }
 
    public Products get(int i){
    	 
        return list.get(i);
     }
 
 public int size(){
 
  return list.size();
}
 
 
 
}