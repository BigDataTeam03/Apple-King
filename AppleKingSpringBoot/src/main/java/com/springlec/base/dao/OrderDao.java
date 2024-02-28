package com.springlec.base.dao;

import java.util.List;

import com.springlec.base.model.MemberDto;
import com.springlec.base.model.OrderDto;
import com.springlec.base.model.ReviewDto;

public interface OrderDao {
	public List<OrderDto> OrderList(String userId) throws Exception;
	public MemberDto memberInfoDao(String userId) throws Exception ;
	public List<ReviewDto> ReviewList (String userId) throws Exception;
	
	//구매 정보 insert
	public void orderInsertDao(
			   String cust_id,			//1
			   String name,				//2
			   Integer product_code,		//3
			   String product_name,		//4
			   Integer price,			//5
			   String payment_method,	//6
			   Integer used_point,		//7
			   Integer order_qty			//8
			   ) throws Exception;
	
}
