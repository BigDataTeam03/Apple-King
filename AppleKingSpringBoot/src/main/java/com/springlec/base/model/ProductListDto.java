package com.springlec.base.model;

import org.springframework.web.multipart.MultipartFile;

public class ProductListDto {

	Integer product_code;
	String product_name;
	Integer product_rank;
	int product_qty;
	String origin;
	String manufacture_date;
	Double weight;
	String size;
	String product_reg_date;
	String kind;
	String product_image;
	String detail_image;
	Integer view_count;
	Integer price;
	Integer sold_qty;
	String seller_id;

	public ProductListDto() {
		// TODO Auto-generated constructor stub
	}

	public Integer getProduct_code() {
		return product_code;
	}

	public void setProduct_code(Integer product_code) {
		this.product_code = product_code;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public Integer getProduct_rank() {
		return product_rank;
	}

	public void setProduct_rank(Integer product_rank) {
		this.product_rank = product_rank;
	}

	public Integer getProduct_qty() {
		return product_qty;
	}

	public void setProduct_qty(Integer product_qty) {
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

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
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

	public String getProduct_image() {
		return product_image;
	}

	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}

	public String getDetail_image() {
		return detail_image;
	}

	public void setDetail_image(String detail_image) {
		this.detail_image = detail_image;
	}

	public Integer getView_count() {
		return view_count;
	}

	public void setView_count(Integer view_count) {
		this.view_count = view_count;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getSold_qty() {
		return sold_qty;
	}

	public void setSold_qty(Integer sold_qty) {
		this.sold_qty = sold_qty;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

}
