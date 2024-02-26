package com.springlec.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlec.base.dao.AdminDao;
import com.springlec.base.model.InquireDto;
import com.springlec.base.model.MemberDto;
import com.springlec.base.model.ProductListDto;

@Service
public class AdminDaoServiceImpl implements AdminDaoService {
	@Autowired
	AdminDao dao;
	
	// 고객 리스트를 출력, 정렬, 검색
	@Override
	public List<MemberDto> custList(String name,String notThis, String orderby) throws Exception {
		// TODO Auto-generated method stub
		//System.out.println(" 갖져온거" + name + notThis +orderby);
		//System.out.println("asdasdsd " +dao.custList(name,notThis,orderby).get(0));
		return dao.custList(name,notThis,orderby);
		
		
	}

	// 상품 리스트 출력
	@Override
	public List<ProductListDto> productlist(String product_name, String selected, String orderby2) throws Exception {
		// TODO Auto-generated method stub		
		return dao.productlist(product_name, selected, orderby2);
	}

	// 상품 수정
	@Override
	public void updateProduct(String product_name, String product_qty, String origin, String manufacture_date,
			String weight, String size, String detail_image_name, String view_count, String product_reg_date,
			String kind, String product_image_names, String product_code) throws Exception {
		
			dao.updateProduct(product_name, product_qty, origin, manufacture_date, weight, size, detail_image_name,
					view_count, product_reg_date, kind, product_image_names, product_code);
			
		
	}
	// 상품 문의내역 출력
	@Override
	public List<InquireDto> questionList() throws Exception {
		// TODO Auto-generated method stub
		return dao.questionList();
	}
	// 상품 문의 답변 입력
	@Override
	public void updateQuestion(String answer, String inquire_code) throws Exception {
		// TODO Auto-generated method stub
			dao.updateQuestion(answer, inquire_code);
			
			
	}

}
