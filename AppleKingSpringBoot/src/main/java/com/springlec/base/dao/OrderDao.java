package com.springlec.base.dao;

import java.util.List;

import com.springlec.base.model.MemberDto;
import com.springlec.base.model.OrderDto;

public interface OrderDao {
	public List<OrderDto> OrderList(String userId) throws Exception;
	public MemberDto memberInfoDao(String userId) throws Exception ;
}
