package com.springlec.base.model;

public class CartDto {

	// Filed

		// 카트 테이블에 있는 컬럼
		String cart_code;
		String cust_id;
		String product_code;
		int cart_qty;

		// 상품 테이블에 있는 컬럼(join 문을 사용하기 위해 필요함)
		String product_name;
		int price;
		String product_image;

		// Constructor

		public CartDto() {
			// TODO Auto-generated constructor stub
		}

		public CartDto(String cart_code, String cust_id, String product_code, int cart_qty, String product_name,
				int price, String product_image) {
			super();
			this.cart_code = cart_code;
			this.cust_id = cust_id;
			this.product_code = product_code;
			this.cart_qty = cart_qty;
			this.product_name = product_name;
			this.price = price;
			this.product_image = product_image;
		}

		public String getCart_code() {
			return cart_code;
		}

		public void setCart_code(String cart_code) {
			this.cart_code = cart_code;
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

		public int getCart_qty() {
			return cart_qty;
		}

		public void setCart_qty(int cart_qty) {
			this.cart_qty = cart_qty;
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

		// Method
	
	
}
