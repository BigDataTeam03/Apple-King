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

	// 장바구니로 결제할 때 
	
	
	// 즉시결제할때 Order table insert!! 
	@Override
	public void orderInsertDao(     String cust_id,
									String name,
									Integer product_code,
									String product_name,
									Integer price,
									String payment_method,
									Integer used_point,
									Integer order_qty) throws Exception {
		String insertQuery= "insert into order (cust_id, name, product_code, product_name, price,"			
			+"payment_method, used_point, order_qty, orderdate )"+	
			"values (+"+"\n"+
			cust_id+","+
			name+","+
			product_code+","+
			product_name+","+
			price+","+
			payment_method+","+
			used_point+","+
			order_qty+","+
			"NOW()  )";					
		System.out.println(">> Insert Query : "+insertQuery);
		orderDao.orderInsertDao(cust_id, name, product_code, product_name, price, payment_method, used_point, order_qty);
	}

}
