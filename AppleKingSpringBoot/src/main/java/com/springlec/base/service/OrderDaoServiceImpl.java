package com.springlec.base.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlec.base.dao.MemberDao;
import com.springlec.base.dao.OrderDao;
import com.springlec.base.dao.ProductReviewDao;
import com.springlec.base.model.MemberDto;
import com.springlec.base.model.OrderDto;
import com.springlec.base.model.ReviewDto;
@Service
public class OrderDaoServiceImpl implements OrderDaoService {

	@Autowired
	OrderDao orderDao;
	
	@Autowired
	MemberDao memberDao;
	
	@Override
	public List<OrderDto> OrderList(String userId) throws Exception {
		return orderDao.OrderList(userId);
	}

	@Override
	public MemberDto memberInfoDao(String userId) throws Exception {
		return memberDao.memberInfoDao(userId);
	}

	@Override

	public List<ReviewDto> ReviewList(String userId) throws Exception {
		return orderDao.ReviewList(userId);
	}

	// 즉시결제할때 Order table insert!! 
	@Override
	public void orderInsertDao( String cust_id,
									String name,
									String product_code,
									String product_name,
									String price,
									String payment_method,
									String used_point,
									String order_qty) throws Exception {
	}

}
