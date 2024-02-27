package com.springlec.base.service;

import java.util.List;

import com.springlec.base.model.ReviewDto;

public interface ProductReviewDaoService {

	
	
	
	public List<ReviewDto> reviewList(String product_code) throws Exception;
}
