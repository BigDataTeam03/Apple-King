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

}
