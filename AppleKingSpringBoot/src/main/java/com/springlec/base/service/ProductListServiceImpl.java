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
	 * 					1. 정렬기능
	 *  
	 * Author		: pdg
	 * Date			: 2024.02.21
	 * Update 		:
	 * 	1.sortQuery 	
	 * 		
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
	public List<ProductListDto> productListDao(	String searchQuery,
												String searchContent,
												String sortingOption,
												int startProduct,
												int pageSize) throws Exception {
		System.out.println(">> productListDaoService 실행");
	
		searchContent = '%' + searchContent + '%';

		String sortQuery= "";
		// sortingOption 에따라서 들어가는 query변환
		
		switch(sortingOption) {
		// 랭킹 순 
		case "productRank": sortQuery= "order by productRank asc "; break;
		// 높은 가격순
		case "highPrice": sortQuery= "order by price asc "; break;
		// 낮은 가격순
		case "lowPrice": sortQuery= "order by price desc "; break;
		// 판매량순
		case "sold_qty": sortQuery= "order by sold_qty asc "; break;
		// 최신순 (상품 등록일 순) 
		case "product_reg_date": sortQuery= "order by product_reg_date asc "; break;
			
		}
		sortingOption =sortQuery;
		System.out.println(">> SQL query :"+"select * from product where "
				+ searchQuery +" like " +searchContent+ " "+sortingOption +" limit"+ Integer.toString(startProduct-1) +" ,"+ pageSize
			);
		// 검색시 limit 을 이용함. startProduct 의 -1 해야 0 번째 부터 출력가능. 
		return dao.productListDao(searchQuery, searchContent, sortingOption, startProduct - 1, pageSize);
	}

}
