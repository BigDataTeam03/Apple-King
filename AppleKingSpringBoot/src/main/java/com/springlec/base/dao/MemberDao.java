package com.springlec.base.dao;


import com.springlec.base.model.MemberDto;

public interface MemberDao {
	/*
	 * Description 	: Customer DB 를 사용하는 DAO interface 
	 * Detail 		: 
	 * 					1.
	 * Author		:
	 * Date			:
	 * Update 		:
	 * 
	 */
	//customer 정보일치 여부 
	public String memberChkDao(String userId, String userPw) throws Exception ;
	
	// Member 정보 가져오기
	public MemberDto memberInfoDao(String userId) throws Exception ;

	// 아이디 중복 체크
	public Integer checkDuplicateId(String userId) throws Exception;
	
	// 회원 가입
	public void signUpDao(
			String userId,
			String userPw, 
			String userName, 
			String userTel, 
			String userEmail,
			String userAddress) throws Exception ;
	
}
