package dto;

public class productDto {

	// Field
	String 	product_code;  			//1
	String 	product_name;  			//2
	int 	product_qty;			//3
	String 	origin;					//4
	String 	manufacture_date;		//5
	int 	weight;					//6
	String 	size;					//7
	String 	detail_image_name;		//8
	int 	view_count;				//9
	String 	product_reg_date;		//10
	String 	kind;					//11
	String 	product_image_names;	//12
	int 	price; 					//13
	String 	rating; 			    //14

	// Constructor
	public productDto() {
		// TODO Auto-generated constructor stub
	}

	public productDto(
			String product_code, String product_name, int product_qty,
			String origin, String manufacture_date,
			int weight, String size, String detail_image_name,
			int view_count, String product_reg_date, String kind,
			String product_image_names) {
		super();
		
		this.product_code = product_code;
		this.product_name = product_name;
		this.product_qty = product_qty;
		this.origin = origin;
		this.manufacture_date = manufacture_date;
		this.weight = weight;
		this.size = size;
		this.detail_image_name = detail_image_name;
		this.view_count = view_count;
		this.product_reg_date = product_reg_date;
		this.kind = kind;
		this.product_image_names = product_image_names;
	}

	
	
	public productDto(String product_name, String product_image_names, int price) {
		super();
		this.product_name = product_name;
		this.price = price;
		this.product_image_names = product_image_names;
		
	}
	
	
	public productDto(String detail_image_name, String product_name, String origin, int price,
			String size, int weight) {
		super();
		this.detail_image_name = detail_image_name;
		this.product_name = product_name;
		this.origin = origin;
		this.price = price;
		this.size = size;
		this.weight = weight;
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

	public int getProduct_qty() {
		return product_qty;
	}

	public void setProduct_qty(int product_qty) {
		this.product_qty = product_qty;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getManufacture_date() {
		return manufacture_date;
	}

	public void setManufacture_date(String manufacture_date) {
		this.manufacture_date = manufacture_date;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getDetail_image_name() {
		return detail_image_name;
	}

	public void setDetail_image_name(String detail_image_name) {
		this.detail_image_name = detail_image_name;
	}

	public int getView_count() {
		return view_count;
	}

	public void setView_count(int view_count) {
		this.view_count = view_count;
	}

	public String getProduct_reg_date() {
		return product_reg_date;
	}

	public void setProduct_reg_date(String product_reg_date) {
		this.product_reg_date = product_reg_date;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getProduct_image_names() {
		return product_image_names;
	}

	public void setProduct_image_names(String product_image_names) {
		this.product_image_names = product_image_names;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	

	

	

	// Method
}
