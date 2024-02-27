package com.springlec.base.service;

import java.util.List;

import com.springlec.base.model.ReviewDto;

public interface ProductReviewDaoService {

	
	
	
	public List<ReviewDto> reviewList(String product_code) throws Exception;
	public void upHelpful(String review_code) throws Exception;
	public void insertReview(String cust_id, String product_code, String rating, 
							 String content, String image, String product_name) throws Exception;
	
}
