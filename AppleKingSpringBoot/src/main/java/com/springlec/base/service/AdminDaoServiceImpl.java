package com.springlec.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlec.base.model.MemberDto;
import com.springlec.base.model.ProductListDto;

@Service
public class AdminDaoServiceImpl implements AdminDaoService {
	@Autowired
	AdminDaoService dao;
	
	
	@Override
	public List<MemberDto> custList(String name,String notThis, String orderby) throws Exception {
		// TODO Auto-generated method stub
		return dao.custList(name,notThis,orderby);
		
		
	}


	@Override
	public List<ProductListDto> productlist(String product_name, String selected, String orderby) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void updateProduct(String product_name, String product_qty, String origin, String manufacture_date,
			String weight, String size, String detail_image_name, String view_count, String product_reg_date,
			String kind, String product_image_names, String product_code) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
