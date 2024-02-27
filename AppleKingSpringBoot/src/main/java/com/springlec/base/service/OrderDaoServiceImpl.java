package com.springlec.base.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlec.base.dao.MemberDao;
import com.springlec.base.dao.OrderDao;
import com.springlec.base.model.MemberDto;
import com.springlec.base.model.OrderDto;
import com.springlec.base.model.ReviewDto;
@Service
public class OrderDaoServiceImpl implements OrderDaoService {

	@Autowired
	OrderDao dao;
	
	@Autowired
	MemberDao memberDao;
	
	
	@Override
	public List<OrderDto> OrderList(String userId) throws Exception {
		return dao.OrderList(userId);
	}

	@Override
	public MemberDto memberInfoDao(String userId) throws Exception {
		return memberDao.memberInfoDao(userId);
	}

	@Override
	public List<ReviewDto> ReviewList(String userId) throws Exception {
		return dao.ReviewList(userId);
	}

}
