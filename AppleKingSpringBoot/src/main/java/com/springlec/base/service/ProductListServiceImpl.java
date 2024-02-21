package com.springlec.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlec.base.dao.ProductListDao;
import com.springlec.base.model.ProductListDto;

@Service
public class ProductListServiceImpl implements ProductListDaoService {
	/*
	 * Description 	: productListDAO service interface 를 사용하는 Class 
	 * Detail 		: 
	 * 					1.
	 * Author		: pdg
	 * Date			: 2024.02.21
	 * Update 		:
	 * 
	 */
	@Autowired
	ProductListDao dao;


	@Override
	public int productCntDao() throws Exception {
		System.out.println("상품총 개수 :"+Integer.toString(dao.productCntDao()));
		return dao.productCntDao();
	}


	@Override
	public List<ProductListDto> productListDao(String query, String searchContent, int startRow, int pageSize) throws Exception {
		System.out.println(">> productListDaoService 실행");
		
		searchContent = '%' + searchContent + '%';
		return dao.productListDao(query, searchContent, startRow,pageSize);
	}

}
