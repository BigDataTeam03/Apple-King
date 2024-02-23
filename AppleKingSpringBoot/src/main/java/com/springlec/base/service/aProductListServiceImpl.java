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
	 * 		2. 상품 리스트 출력, 업데이트 기능 완료
	 *-------------------------------------- 
	 */

		@Autowired
		AdminDao dao;
	
	@Override
	public List<ProductListDto> productlist(String product_name, String selected, String orderby) throws Exception {
		// TODO Auto-generated method stub
		return dao.productlist(product_name, selected, orderby);
	}

	@Override
	public void updateProduct(String product_name, String product_qty, String origin, String manufacture_date,
			String weight, String size, String detail_image_name, String view_count, String product_reg_date,
			String kind, String product_image_names, String product_code) throws Exception {
		// TODO Auto-generated method stub
		dao.updateProduct(product_name, product_qty, origin,
						  manufacture_date, weight, size, 
						  detail_image_name, view_count, 
						  product_reg_date, kind, product_image_names, 
						  product_code);
		
		
	}
}
