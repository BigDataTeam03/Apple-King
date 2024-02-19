package dto;

public class OrderDto {

	
//Field 

String cust_id; 
String product_code; 
String order_code; 
String payment_method; 
int used_point;
	
	
	
public OrderDto() {
	// TODO Auto-generated constructor stub
}


public OrderDto(String cust_id, String product_code, String order_code, String payment_method, int used_point) {
	super();
	this.cust_id = cust_id;
	this.product_code = product_code;
	this.order_code = order_code;
	this.payment_method = payment_method;
	this.used_point = used_point;
}




public OrderDto(String product_code, String order_code, String payment_method, int used_point) {
	super();
	this.product_code = product_code;
	this.order_code = order_code;
	this.payment_method = payment_method;
	this.used_point = used_point;
}


public String getCust_id() {
	return cust_id;
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
	






	





















	
}//END
