package com.springlec.base.dao;

import java.util.List;

import com.springlec.base.model.OrderDto;

public interface OrderDao {
	public List<OrderDto> OrderDao(String cust_id) throws Exception ;

}
