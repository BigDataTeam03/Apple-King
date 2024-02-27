package com.springlec.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlec.base.dao.MemberDao;
import com.springlec.base.dao.PurchaseDao;
import com.springlec.base.model.MemberDto;

@Service
public class PurchaseDaoServiceImpl implements PurchaseDaoService {

	@Autowired
	PurchaseDao dao;
	
	@Autowired
	MemberDao memberdao;
	
	

	@Override
	public MemberDto memberInfoDao(String userId) throws Exception {
		// TODO Auto-generated method stub
		return memberdao.memberInfoDao(userId);
	}

}
