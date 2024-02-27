package com.springlec.base.model;

public class ReviewDto {
	
	
	String review_date;
	String product_name;
	String review_content;
	int rating; 
	String review_image; 
	
	
	
	public ReviewDto() {
		// TODO Auto-generated constructor stub
	}



	public String getReview_date() {
		return review_date;
	}



	public void setReview_date(String review_date) {
		this.review_date = review_date;
	}



	public String getProduct_name() {
		return product_name;
	}



	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}



	public String getReview_content() {
		return review_content;
	}



	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}



	public int getRating() {
		return rating;
	}



	public void setRating(int rating) {
		this.rating = rating;
	}



	public String getReview_image() {
		return review_image;
	}



	public void setReview_image(String review_image) {
		this.review_image = review_image;
	}
	
	
	
	
	

}
