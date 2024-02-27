package com.springlec.base.service;

import java.util.List;

import com.springlec.base.model.MemberDto;
import com.springlec.base.model.OrderDto;
import com.springlec.base.model.ReviewDto;

public interface OrderDaoService {
	public List<OrderDto> OrderList(String userId) throws Exception ;
	public MemberDto memberInfoDao(String userId) throws Exception ;
	public List<ReviewDto> ReviewList (String userId) throws Exception;

}
