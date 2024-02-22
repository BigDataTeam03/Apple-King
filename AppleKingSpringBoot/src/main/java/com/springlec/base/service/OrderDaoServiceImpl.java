package com.springlec.base.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlec.base.dao.OrderDao;
import com.springlec.base.model.OrderDto;
@Service
public class OrderDaoServiceImpl implements OrderDaoService {

	@Autowired
	OrderDao dao;
	
	
	@Override
	public List<OrderDto> OrderDao(String cust_id) throws Exception {
		// TODO Auto-generated method stub
		return dao.OrderDao(cust_id);
	}

}
