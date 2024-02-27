package com.springlec.base.model;

public class OrderDto {

	
//Field 

//ORDER TABLE COLUMNS


String product_code; 
String order_code; 
String payment_method; 
int used_point;
String orderdate; 
int order_qty;
	

//PRODUCT TABLE COLUMNS 
String product_name; 
int price; 
String product_image; 




	
public OrderDto() {
	// TODO Auto-generated constructor stub
}




//SETTERS & GETTERS
public String getCust_id() {
	return cust_id;
}



public String getOrderdate() {
	return orderdate;
}




public void setOrderdate(String orderdate) {
	this.orderdate = orderdate;
}




public String getProduct_name() {
	return product_name;
}




public void setProduct_name(String product_name) {
	this.product_name = product_name;
}




public int getPrice() {
	return price;
}




public void setPrice(int price) {
	this.price = price;
}




public String getProduct_image() {
	return product_image;
}




public void setProduct_image_names(String product_image) {
	this.product_image = product_image;
}




public void setCust_id(String cust_id) {
	this.cust_id = cust_id;
}



public String getProduct_code() {
	return product_code;
}



public void setProduct_code(String product_code) {
	this.product_code = product_code;
}



public String getOrder_code() {
	return order_code;
}



public void setOrder_code(String order_code) {
	this.order_code = order_code;
}



public String getPayment_method() {
	return payment_method;
}



public void setPayment_method(String payment_method) {
	this.payment_method = payment_method;
}



public int getUsed_point() {
	return used_point;
}



public void setUsed_point(int used_point) {
	this.used_point = used_point;
}	
	


String cust_id; 
public int getOrder_qty() {
	return order_qty;
}




public void setOrder_qty(int order_qty) {
	this.order_qty = order_qty;
}




	





















	
}//END

