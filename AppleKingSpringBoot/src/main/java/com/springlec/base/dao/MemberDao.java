package com.springlec.base.dao;

import java.util.List;

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
	//cutomer 정보를 불러오는 DAO 
	public List<MemberDto> memberListDao() throws Exception ;

}
