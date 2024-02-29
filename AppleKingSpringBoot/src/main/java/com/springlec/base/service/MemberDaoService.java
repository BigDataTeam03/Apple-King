package com.springlec.base.service;
import java.util.List;
import com.springlec.base.model.MemberDto;
public interface MemberDaoService {
	/*
	 * Description 	: Customer table 이용하는 DAO interface
	 * Detail 		: 
	 * 					1.membeChkDao
	 * Author		: pdg
	 * Date			: 2024.02.23
	 * Update 		: 
	 * 
	 */
	public String memberChkDao(String userId, String userPw) throws Exception;
	
	// Member 정보 가져오기
	public MemberDto memberInfoDao(String userId) throws Exception ;

	public Integer checkDuplicateId(String userId) throws Exception;
}
