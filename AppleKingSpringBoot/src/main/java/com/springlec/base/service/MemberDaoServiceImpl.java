package com.springlec.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlec.base.dao.MemberDao;
import com.springlec.base.model.MemberDto;

@Service
public class MemberDaoServiceImpl implements MemberDaoService {
	/*
	 * Description 	: DAO service interface 를 사용하는 Class 
	 * Detail 		: 
	 * 					1.
	 * Author		: pdg
	 * Date			: 2024.02.21
	 * Update 		:
	 * 
	 */
	
	@Autowired
	MemberDao dao;
	@Override
	public List<MemberDto> memberListDao() throws Exception {
		// customer 정보를 list up 하는 다오 
		return dao.memberListDao();
		 
	}


}
