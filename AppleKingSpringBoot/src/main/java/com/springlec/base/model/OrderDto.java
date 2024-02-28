package com.springlec.base.model;

/*
 * Description : Order DTO
 * Date 		: 2024.02.27
 * Author 		: pdg, diana
 * Detail		:
 * Update		: 
 * 		1. insert 할때 일일히 비교하기 귀찮아서 구매 테이블 칼럼을 이 dto field 에 맞추어서 다시 설계함.  
 */

public class OrderDto {

	// Field

	// Field
	// ORDER TABLE COLUMNS

	String cust_id; // 상품을 구매한 구매자 id
	String name; // 구매자 이름
	String product_code; // 구매한 상품 코드
	String product_name; // 구매한 상품 이름
	int price; // 구매한 상품 가격
	int product_qty; // 구매한 상품 재고?
	String order_code; // 결제 코드
	String payment_method; // 결제 방법
	int used_point; // 결제시 사용한 포인트
	int order_qty; // 결제상품 개수
	String orderdate; // 결제일
	int soldout; // 매진 정보

	// Constructor

	String product_image;

	public OrderDto() {
	}

	public String getCust_id() {
		return cust_id;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
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

	public int getProduct_qty() {
		return product_qty;
	}

	public void setProduct_qty(int product_qty) {
		this.product_qty = product_qty;
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

	public int getOrder_qty() {
		return order_qty;
	}

	public void setOrder_qty(int order_qty) {
		this.order_qty = order_qty;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public int getSoldout() {
		return soldout;
	}

	public void setSoldout(int soldout) {
		this.soldout = soldout;
	}

	public String getProduct_image() {
		return product_image;
	}

	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}

	// Getters and setters

}// END
