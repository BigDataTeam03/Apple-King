package com.springlec.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlec.base.dao.CartDao;
import com.springlec.base.model.CartDto;
@Service
public class CartDaoServiceImpl implements CartDaoService {

	@Autowired
	CartDao dao;
	
	@Override
	public List<CartDto> cartlistdao(String userId) throws Exception {
		// TODO Auto-generated method stub
		return dao.cartlistdao(userId);
	}

}
