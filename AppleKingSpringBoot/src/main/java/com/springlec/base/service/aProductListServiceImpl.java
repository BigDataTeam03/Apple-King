package com.springlec.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlec.base.dao.AdminDao;
import com.springlec.base.model.ProductListDto;

@Service
public class aProductListServiceImpl implements aProductListDaoService {
	/*--------------------------------------
	 * Description: aProductList Service 에서 다오로 연결해주는 클라스
	 * Author :  KBS
	 * Date : 2024.02.23
	 * Update : 2024.02.23 KBS 
	 * 		1. 페이지 생성
	 *-------------------------------------- 
	 */

		@Autowired
		AdminDao dao;
	
	@Override
	public List<ProductListDto> productlist(String product_name, String selected, String orderby) throws Exception {
		// TODO Auto-generated method stub
		return dao.productlist(product_name, selected, orderby);
	}
}
