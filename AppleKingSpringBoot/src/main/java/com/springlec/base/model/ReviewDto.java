package com.springlec.base.model;

public class ReviewDto {

			int review_code;
			String cust_id;
			String product_code;
			String rating;
			String review_content;
			String review_date;
			int helpful_count;
			String review_image;
		    String product_name;

			public ReviewDto() {
				// TODO Auto-generated constructor stub
			}

			public int getReview_code() {
				return review_code;
			}

			public void setReview_code(int review_code) {
				this.review_code = review_code;
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

			public String getRating() {
				return rating;
			}

			public void setRating(String rating) {
				this.rating = rating;
			}

			public String getReview_content() {
				return review_content;
			}

			public void setReview_content(String review_content) {
				this.review_content = review_content;
			}

			public String getReview_date() {
				return review_date;
			}

			public void setReview_date(String review_date) {
				this.review_date = review_date;
			}

			public int getHelpful_count() {
				return helpful_count;
			}

			public void setHelpful_count(int helpful_count) {
				this.helpful_count = helpful_count;
			}

			public String getReview_image() {
				return review_image;
			}

			public void setReview_image(String review_image) {
				this.review_image = review_image;
			}

			public String getProduct_name() {
				return product_name;
			}

			public void setProduct_name(String product_name) {
				this.product_name = product_name;
			}



		
}