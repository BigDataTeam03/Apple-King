package dto;

public class PurchaseDto {
	// Filed

		// order 테이블에 있는 컬럼
		String cust_id;
		String product_code;
		String order_code;
		String payment_method;
		int used_method;
		
		// customer 테이블에 있는 컬럼
		String name;
		String email;
		String tel;
		int cust_point;
		
		// product 테이블에 있는 컬럼
		int price;
		
		// Constructor

		public PurchaseDto() {
			// TODO Auto-generated constructor stub
		}

		public PurchaseDto(String cust_id, String product_code, String order_code, String payment_method,
				int used_method, String name, String email, String tel, int cust_point, int price) {
			super();
			this.cust_id = cust_id;
			this.product_code = product_code;
			this.order_code = order_code;
			this.payment_method = payment_method;
			this.used_method = used_method;
			this.name = name;
			this.email = email;
			this.tel = tel;
			this.cust_point = cust_point;
			this.price = price;
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

		public int getUsed_method() {
			return used_method;
		}

		public void setUsed_method(int used_method) {
			this.used_method = used_method;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getTel() {
			return tel;
		}

		public void setTel(String tel) {
			this.tel = tel;
		}

		public int getCust_point() {
			return cust_point;
		}

		public void setCust_point(int cust_point) {
			this.cust_point = cust_point;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		
		
		
}
