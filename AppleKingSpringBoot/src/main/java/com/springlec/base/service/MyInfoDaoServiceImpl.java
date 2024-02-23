package com.springlec.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlec.base.dao.MyInfoDao;
import com.springlec.base.model.MyInfoDto;

@Service
public class MyInfoDaoServiceImpl implements MyInfoDaoService {

	@Autowired
	MyInfoDao dao;

	@Override
	public List<MyInfoDto> MyInfoDao(String cust_id) throws Exception {
		// TODO Auto-generated method stub
		return dao.MyInfoDao(cust_id);
	}
	
	
	
	
	
	
	
	
	
}
