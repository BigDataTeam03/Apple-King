package com.springlec.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlec.base.dao.ProductListDao;
import com.springlec.base.model.ProductListDto;

@Service
public class ProductListServiceImpl implements ProductListDaoService {

	@Autowired
	ProductListDao dao;

	@Override
	public List<ProductListDto> listDao() throws Exception {
		// TODO Auto-generated method stub
		return dao.listDao();
	}

}
