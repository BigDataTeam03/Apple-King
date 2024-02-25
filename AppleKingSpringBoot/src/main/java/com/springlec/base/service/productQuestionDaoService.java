package com.springlec.base.service;

import java.util.List;

import com.springlec.base.model.InquireDto;

public interface productQuestionDaoService {

		// 고객이 보는 문의내역을 출력하는 서비스
		public List<InquireDto> ShowList(String product_code)throws Exception;
		// 고객이 남긴 문의를 인서트하는 서비스
		public void insertQuestion(String cust_id, String product_code, String question)throws Exception;
}
