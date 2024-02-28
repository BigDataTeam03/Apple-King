package com.springlec.base.service;

import java.util.List;

import com.springlec.base.model.MemberDto;
import com.springlec.base.model.OrderDto;
import com.springlec.base.model.ReviewDto;

/*
 * Description 	: 구매 결제 페이지 의 Dao Service interface
 * Date 		: 2024.02.27
 * Author 		: PDG, Diana 
 * Update 		:2024.02.27 		
 * 
 */
public interface OrderDaoService {
	// 구매내역에서 구매 정보 불러오는 dao 
	public List<OrderDto> OrderList(String userId) throws Exception ;
	
	// 결제 페이지에서 유저정보 불러오는 dao
	public MemberDto memberInfoDao(String userId) throws Exception ;

	
	// 결제 페이지에서 결제 버튼 눌렀을때 insert 실행 Dao
	public void orderInsertDao(String cust_id,			//1
								   String name,				//2
								   String product_code,		//3
								   String product_name,		//4
								   String price,			//5
								   String payment_method,	//6
								   String used_point,		//7
								   String order_qty			//8
								   ) throws Exception;

	public List<ReviewDto> ReviewList (String userId) throws Exception;

}

