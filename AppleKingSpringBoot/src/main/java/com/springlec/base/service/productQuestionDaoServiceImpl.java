package com.springlec.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlec.base.dao.ProductQuestionDao;
import com.springlec.base.model.InquireDto;

@Service
public class productQuestionDaoServiceImpl implements productQuestionDaoService {

	@Autowired
	ProductQuestionDao dao;
	// 고객이 보는 상품문의 리스트 
	@Override
	public List<InquireDto> ShowList(String product_code) throws Exception {
		// TODO Auto-generated method stub
		return dao.ShowList(product_code);
	}
	// 고객이 문의한 내용을 인서트
	@Override
	public void insertQuestion(String cust_id, String product_code, String question) throws Exception {
		// TODO Auto-generated method stub
			dao.insertQuestion(cust_id, product_code, question);
			
		
	}

}
