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
		return dao.MyInfoDao(cust_id);
	}

	@Override
	public boolean ModifyDao(String name, String cust_pw, String tel, String email, String address, String cust_id)
			throws Exception {
		return dao.ModifyDao(name, cust_pw, tel, email, address, cust_id);
	}

	@Override
	public void DeactivateDao(String deact_date,String cust_id) throws Exception {
		dao.DeactivateDao( deact_date,cust_id);
	}
	
	
	
	
	
	
	
	
	
	
	
}
