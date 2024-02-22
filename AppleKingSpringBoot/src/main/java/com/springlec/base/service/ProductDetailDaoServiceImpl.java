package com.springlec.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlec.base.dao.ProductDetailDao;
import com.springlec.base.model.ProductListDto;

@Service
public class ProductDetailDaoServiceImpl implements ProductDetailDaoService {

	@Autowired
	ProductDetailDao dao;

	@Override
	public List<ProductListDto> productDetailDao(String product_code) throws Exception {
		// TODO Auto-generated method stub
		return dao.productDetailDao(product_code);
	}

}
