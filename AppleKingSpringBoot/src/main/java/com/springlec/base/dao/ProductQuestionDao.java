package com.springlec.base.dao;

import java.util.List;

import com.springlec.base.model.InquireDto;

public interface ProductQuestionDao {

	
	public List<InquireDto> ShowList(String product_code)throws Exception;
	public void insertQuestion(String cust_id, String product_code, String question)throws Exception;
}
