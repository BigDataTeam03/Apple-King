package com.springlec.base.service;

import java.awt.SplashScreen;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlec.base.dao.ProductReviewDao;
import com.springlec.base.model.ReviewDto;
@Service
public class ProductReviewDaoServiceImpl implements ProductReviewDaoService {
	
	@Autowired
	ProductReviewDao dao;

	@Override
	public List<ReviewDto> reviewList(String product_code) throws Exception {
		// TODO Auto-generated method stub
		return   dao.reviewList(product_code);
		
	}

	@Override
	public void upHelpful(String review_code) throws Exception {
		dao.upHelpful(review_code);
		
	}

	@Override
	public void insertReview(String cust_id, String product_code, String rating, String content,
			String product_name) throws Exception {
		dao.insertReview(cust_id, product_code, rating, content, product_name);
		
		
	}

}
