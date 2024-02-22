package com.springlec.base.service;

import java.util.List;

import com.springlec.base.model.OrderDto;

public interface OrderDaoService {
	public List<OrderDto> OrderDao(String cust_id) throws Exception ;


}
