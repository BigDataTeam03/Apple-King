package com.springlec.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springlec.base.dao.MemberDao;
import com.springlec.base.model.MemberDto;

@Service
public class MemberDaoServiceImpl implements MemberDaoService {
	/*
	 * Description 	: memberDAO service interface 를 사용하는 implement class
	 * Detail 		: 
	 * 					1.
	 * Author		: pdg
	 * Date			: 2024.02.21
	 * Update 		:
	 * 
	 */
	
	@Autowired
	MemberDao dao; // mapper 에 있는 MemberDao.xml 연결함. 
	@Override
	public String memberChkDao(String userId, String userPw) throws Exception {
		// userId 의 패스워드를 확인하여 회원 여부 를 확인. 
		// 일치 -> true,불일치 -> false
		return dao.memberChkDao(userId, userPw);
	}
	@Override
	public MemberDto memberInfoDao(String userId) throws Exception {
		
		
		return dao.memberInfoDao(userId);
	}


}
