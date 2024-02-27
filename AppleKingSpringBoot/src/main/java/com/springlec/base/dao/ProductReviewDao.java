package com.springlec.base.dao;

import java.util.List;

import com.springlec.base.model.ReviewDto;

public interface ProductReviewDao {
				
	
	public List<ReviewDto> reviewList(String product_code) throws Exception;
	
}
